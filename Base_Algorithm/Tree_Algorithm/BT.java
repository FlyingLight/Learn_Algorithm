/**
 * 
 */
package BST_USE_TEST;

/**
 * @author qiguangqin
 *
 */
public interface BT<K,V> {
	
	public abstract int size();
	
	public abstract boolean isEmpty();
		
	public void insert(K key,V value);
	
	public boolean contain(K key);
	
	public V search(K key);

}
