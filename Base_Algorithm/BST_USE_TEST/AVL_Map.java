package BST_USE_TEST;

public class AVL_Map<K extends Comparable<K>,V> implements Map_inter<K,V> {

	private  AVL_imp<K,V>avl;
	
	public AVL_Map() {
		
		avl=new AVL_imp<K,V>();
	}
	
	@Override
	public int getSize() {
		
		return avl.size();
	}
	
	@Override
	public boolean isEmpty() {
		
		return avl.isEmpty();
	}
	
	@Override
	public void add(K key,V value) {
		
		avl.insert(key, value);
	}
	
	@Override
	public boolean contains(K key) {
		
		return avl.contain(key);
	}
	

	@Override
	public V get(K key) {
		
		return avl.search(key);
	}
	
	@Override
	public void set(K key,V value) {
		
		avl.set(key, value);
	}
	
	@Override
	public void remove(K key) {
		
		avl.remove(key);
	}
	
	public void print() {
		
		avl.Level_order();
	}
	
	
	public static void main(String[] args) {
		
		AVL_Map<Integer,String> avl_map=new AVL_Map<Integer,String>();
		
		avl_map.add(28,"a");
		
		avl_map.add(16,"b");
		
		avl_map.add(30,"c");
		
		avl_map.add(13,"d");
		
		avl_map.add(22,"e");
		
		avl_map.add(29,"f");
		
		avl_map.add(42,"g");
		
		avl_map.remove(30);
		
		avl_map.print();

	}

}
