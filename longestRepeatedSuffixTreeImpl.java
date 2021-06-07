
public class longestRepeatedSuffixTreeImpl extends longestRepeatedSuffixTree {

	public longestRepeatedSuffixTreeImpl(String word) {
		super(word);
	}

	@Override
	public void createLongestRepeatedSubstring() {
		maxLength = 0;
		substringStartNode = null;
		for(int i = 0; i < root.numOfChildren; i++) {
			maxLength(root.children[i]);
		}
	}
	
	private void maxLength(SuffixTreeNode node) {
		if(node.numOfChildren == 0) {
			int totalWordLength = node.parent.totalWordLength;
			if(totalWordLength > maxLength) {
				maxLength = totalWordLength;
				substringStartNode = node.parent;
			}
		}
		for(int i = 0; i < node.numOfChildren; i++) {
			maxLength(node.children[i]);
		}
	}

	@Override
	public String getLongestRepeatedSubstring() {
		if (substringStartNode == null)
			return "X";
		return substringStartNode.restoreStringAlongPath();
	}
	
	public static void main(String[] args) {
		int[] a = {1, 2, 3};
		int[] b = {1, 2, 3};
		System.out.println(a.equals(b));
	}

}
