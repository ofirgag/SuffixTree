
public class CharLinkedListImpl extends CharLinkedList{

	@Override
	public void add(char c) {
		if(last == null) {
			first = new CharLinkedListNodeImpl(c);
			last = first;
		} else {
			last.setNext(new CharLinkedListNodeImpl(c));
			last = last.getNext();
		}
	}

	@Override
	public char firstChar() {
		return first.getChar();
	}

	@Override
	public int size() {
		int count = 0;
		for(ICharLinkedListNode curr = first; curr != null; curr = curr.getNext()) {
			count++;
		}
		return count;
	}

	@Override
	public void append(CharLinkedList toAppend) {
		last.setNext(toAppend.first);
		last = toAppend.last;
	}
	
	public static void main(String[] args) {
		CharLinkedListImpl list = new CharLinkedListImpl();
		list.add('a');
		list.add('b');
		list.add('c');
		list.add('d');
		
		
		System.out.println(list);
		System.out.println(list.size());
		
		CharLinkedListImpl list2 = new CharLinkedListImpl();
		list.add('a');
		list.add('b');
		list.add('c');
		list.add('d');
		
		list.append(list2);
		
		System.out.println(list.toString());
		System.out.println(list.size());
	}
}
