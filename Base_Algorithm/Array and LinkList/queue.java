package LinkList_Learn;

public interface queue<K > {
	
	boolean isEmpty();
	
	int getSize();
	
	void enqueue(K key);
	
	K dequeue();
	
	K getFront();

}
