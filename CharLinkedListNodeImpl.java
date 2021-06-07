
public class CharLinkedListNodeImpl implements ICharLinkedListNode{
	
	private char data;
	private ICharLinkedListNode next;
	
	public CharLinkedListNodeImpl() {
		next = null;
	}
	
	public CharLinkedListNodeImpl(char data, ICharLinkedListNode next) {
		this.data = data;
		this.next = next;		
	}
	
	public CharLinkedListNodeImpl(char data) {
		this(data, null);
	}

	@Override
	public char getChar() {
		return data;
	}
	
	@Override
	public ICharLinkedListNode getNext() {
		return next;
	}
	
	@Override
	public void setNext(ICharLinkedListNode next) {
		this.next = next;
	}
}
