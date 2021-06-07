
public class SuffixTreeNodeImpl extends SuffixTreeNode {

	public SuffixTreeNodeImpl(CharLinkedList chars, SuffixTreeNode parent) {
		super(chars, parent);
	}

	public SuffixTreeNodeImpl() {
		super();
	}

	@Override
	public SuffixTreeNode search(char c) {
		// TODO Auto-generated method stub
		return binarySearch(c, 0, numOfChildren - 1);
	}

	@Override
	public SuffixTreeNode binarySearch(char target, int left, int right) {
		while (right >= left){
			int middle = (left + right) / 2;
			char candidate = children[middle].chars.firstChar();
			if (candidate > target){
				return binarySearch(target, left, middle - 1);
			}
			else if (candidate < target){
				return binarySearch(target, middle + 1, right);
			}
			else {
				return children[middle];
			}
		}
		return null;
	}

	@Override
	public void shiftChildren(int until) {
		for(int i = numOfChildren - 1; i >= until; i--) {
			this.children[i + 1] = this.children[i];
		}
	}

	@Override
	public void addChild(SuffixTreeNode node) {
		int until = 0;
		//check if empty 
		if (numOfChildren == 0){
			children[0] = node;	
		}
		else {
			until = shiftIfNedded(node, until);

			//insert new son in the right place.
			children[until] = node;
		}

		//update fields
		updateFields(node);
	}

	private int shiftIfNedded(SuffixTreeNode node, int until) {
		//find until index. To keep lexicographic order.
		char toAddFirstChar = node.chars.firstChar();
		while(children[until] != null && children[until].chars.firstChar() < toAddFirstChar) {
			until++;
		}

		//shift right all elements until index until.
		shiftChildren(until);
		return until;
	}

	private void updateFields(SuffixTreeNode node) {
		numOfChildren++;
		descendantLeaves += node.descendantLeaves;
		node.totalWordLength = totalWordLength + 1;
		node.parent = this;
	}

	@Override
	public void addSuffix(char[] word, int from) {
		if (from < word.length){
			char c = word[from];
			// search a node containing c as first char
			SuffixTreeNode suffixNode = search(c);
			if (suffixNode == null){
				CharLinkedList charNode = CharLinkedList.from(c);
				suffixNode = new SuffixTreeNodeImpl(charNode, this);
				addChild(suffixNode);
			}
			suffixNode.addSuffix(word, from + 1);
			descendantLeaves++;
		}
	}

	@Override
	public void compress() {
		if (numOfChildren > 1) {
			for(int i = 0; i < numOfChildren; i++) {
				children[i].compress();
			}
		} else if (numOfChildren == 1) {
			SuffixTreeNode child = children[0];
			CharLinkedList childChars = copy(child.chars);
			CharLinkedList chars = copy(this.chars);
			chars.append(childChars);
			child.chars = chars;
			child.parent = parent;
			((SuffixTreeNodeImpl) parent).replaceChild(this, child);
			child.compress();			
		}
	}

	private CharLinkedList copy(CharLinkedList chars) {
		CharLinkedList res = new CharLinkedListImpl();
		for(Character c : chars) {
			res.add(c);
		}
		return res;
	}

	private void replaceChild(SuffixTreeNodeImpl toReplace, SuffixTreeNode child) {
		for (int i = 0; i < numOfChildren; i++) {
			if (children[i].equals(toReplace))
				children[i] = child;
		}
	}

	@Override
	public int numOfOccurrences(char[] subword, int from) {
		if (from >= subword.length){
			return descendantLeaves;
		}
		SuffixTreeNode node = search(subword[from]);
		if (node == null){
			return 0;
		}
		for(char c : node.chars) {
			if(from >= subword.length)
				break;
			if (c != subword[from]){
				return 0;
			}
			from ++;
		}
		if (from < subword.length){
			return node.numOfOccurrences(subword, from + 1);
		}
		else{
			return node.descendantLeaves;
		}
	}

	public static void main(String[] args) {
		SuffixTree tree = new SuffixTreeImpl("he");
		System.out.println(tree.root.children[2]);
	}
}
