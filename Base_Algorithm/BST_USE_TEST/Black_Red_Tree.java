/**
 * 
 */
package BST_USE_TEST;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author qiguangqin
 *
 */
public class Black_Red_Tree<K extends Comparable<K>,V> {
	
	private static final boolean RED=true;  //  RED or BLACK =1 or 0
	
	private static final boolean BLACK =false;

	private class Node{
		
		private K key;
		
		private V value;
		
		private Node left_child,right_child;
		
		private boolean color;
		
		public Node(K key,V value) {
			
			this.key=key;
			
			this.value=value;
			
			this.left_child=null;
			
			this.right_child=null;
			
			this.color=RED;  // 新添加的节点，总是要和原来的某一个节点（2-3树中的一个节点）融合，要么形成一个临时的3节点，或者4节点
			
			// 融合的节点，一般是红色的
		}

		@Override
		public String toString() {
			return "Node [key=" + key + ", value=" + value + "]";
		}
		
	}
	
	private Node node_root;
	
	private int size;
	
	public Black_Red_Tree() {
		
		node_root=null;
		
		size=0;
	}
	
	public int getSize() {return this.size;}

	public boolean isEmpty() {return size==0;}
	
	private boolean isRed(Node node) {
		
		return node==null ? BLACK:node.color;
	}
	
	private Node leftRotate(Node node) {
		
		Node x=node.right_child;
		
		node.right_child=x.left_child;
		
		//  left rotate 
		x.left_child=node;
		
		//	change color 
		x.color=node.color;
		
		node.color=RED;  // new -3 node ,become red
		
		return x;
		
	}
	
	private Node rightRotate(Node node) {
		
		Node x=node.left_child;
		
		node.left_child=x.right_child;
		
		// left rotate 
		x.right_child=node;
		
		x.color=node.color;
		
		node.color=RED; // fill the color RED for node,-3 tree node
				
		return x;
		
		
	}
	
	private void flipColor(Node node) {
		
		node.color=RED;
		
		node.left_child.color=BLACK;
		
		node.right_child.color=BLACK;
		 	
		
	}
	
	public void add(K key,V value) {
		
		node_root=add(node_root,key,value);
		
		node_root.color=BLACK; // 2-3 -4节点想上融合，变成一个红色的节点，直接到根节点
	}
	
	private Node add(Node node,K key,V value) {
		
		if(node==null) {
			
			this.size++;
			
			return new Node(key,value);
			
		}
		
		else if(key.compareTo(node.key)<0)
			
			node.left_child=add(node.left_child,key,value);
		
		
		else if(key.compareTo(node.key)>0)
			
			node.right_child=add(node.right_child,key,value);
		
		else
			
			node.value=value;
		
		if(isRed(node.right_child) && !isRed(node.left_child)) {
			
			// node -2 is black, and node's right =red ,  node -3 black  left red  insert red at right
			
			node=leftRotate(node);
		}
		
		if(isRed(node.left_child)&&isRed(node.left_child.left_child)) {
			
			node=rightRotate(node);
		}
		
		if(isRed(node.left_child) &&isRed(node.right_child)) {
			
			flipColor(node);
		}
		
		return node;
	}
	
	public void level_order() {
		
		_level_order(node_root);
	}
	
	private void _level_order(Node node) {
		
		
		Node node_cur=node;
		
		Queue <Node>list=new LinkedList<Node>();
		
		if (node_cur==null)
			
			System.out.println("empty tree");
		
		list.add(node_cur);
		
		
		while(!list.isEmpty()) {
			
			
			node_cur =list.remove();
			
			System.out.println(node_cur);
			
			if(node_cur.left_child!=null) {
				
				list.add(node_cur.left_child);
			}
			
			if(node_cur.right_child!=null) {
				
				list.add(node_cur.right_child);
			}
		}
			
	}
	
	public static void main(String[] args) {
		
		int a=-3; //  
		
		//  >>　右移运算  (有符号运算) /2和取整数
		
		// << 左移动运算(有符号运算) *2 
		
		// >>> 无符号(有符号运算) /2 取整数
		
		System.out.println(Integer.toBinaryString(a<<2));
		

	}

}
