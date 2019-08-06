package LinkList_Learn;

public interface Stack_inter<E extends Comparable<E>> {
	
	void push(E e);
	
	E pop();
	
	E peek();
	
	int getSize();
	
	boolean isEmpty();

}
