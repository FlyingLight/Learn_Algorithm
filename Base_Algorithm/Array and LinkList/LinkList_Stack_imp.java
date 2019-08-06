package LinkList_Learn;

public class LinkList_Stack_imp <K extends Comparable<K>> implements stack_simple<K>{

	private dummy_head_linklist<K> list;
	
	
	public LinkList_Stack_imp() {
		
		list=new dummy_head_linklist();
	}
	
	@Override
	public int getSize() {
		
		return this.list.size();
	}
	
	@Override
	public boolean isEmpty() {
		
		return list.isEmpty();
	}
	
	@Override
	public void push(K key) {
		
		list.addFirst(key);
	}
	
	@Override
	public K pop() {
		
		K element;
		
		element=list.getFirstNode().key;
		
		list.removeFirst();
		
		return element;
	}
	
	@Override
	public K peek() {
		
		return list.getFirstNode().key; // GET the stack peek element;
	}
	
	public void print_stack() {
		
		
		this.list.print();
	}
	
	public static void main(String[] args) {
	
		LinkList_Stack_imp<Integer> lsi=new LinkList_Stack_imp();
		
		for(int i=0;i<5;i++) {
			
			lsi.push(i);
			
			lsi.print_stack();
		}
	}
	
	

}
