package LinkList_Learn;

public interface stack_simple<K extends Comparable<K>> {
	
	public void push(K key);
	
	public K pop();
	
	public K peek();
	
	public int getSize();
	
	public boolean isEmpty();

}
