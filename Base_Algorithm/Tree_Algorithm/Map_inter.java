package BST_USE_TEST;

public interface Map_inter<K,V> {
	
	void add(K key, V value);
	
	void remove(K key);
	
	boolean contains(K key);
		
	int getSize();
	
	V get(K key);
	
	void set(K key,V value);
	
	boolean isEmpty();

}
