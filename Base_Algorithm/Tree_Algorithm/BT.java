/**
 * 
 */
package BST_USE_TEST;

/**
 * @author qiguangqin
 *
 */
public interface BT<K,V> {
	
	
	//  >>　右移运算  (有符号运算) /2和取整数
	
	// << 左移动运算(有符号运算) *2 
	
	// >>> 无符号(有符号运算) /2 取整数
	
	public abstract int size();
	
	public abstract boolean isEmpty();
		
	public void insert(K key,V value);
	
	public boolean contain(K key);
	
	public V search(K key);
	
	public void remove(K key);

}
