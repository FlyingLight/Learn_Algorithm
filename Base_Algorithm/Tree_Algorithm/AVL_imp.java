/**
 * 
 */
package BST_USE_TEST;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
//import java.util.Stack;







/**
 * @author qiguangqin
 *
 */
public class AVL_imp<K extends Comparable<K>,V> implements BT<K,V> {

	/**
	 * @param args
	 */
	
	private class Node{
		
		private K key;
		
		private V value;
		
		private int height;
		
		private Node left_child,right_child;
		
		public Node(K key,V value) {
			
			this.key=key;
			
			this.value=value;
			
			this.left_child=null;
			
			this.right_child=null;
			
			this.height=1; 
		}
		
		public Node(Node node) {
			
			this.key=node.key;
			
			this.left_child=node.left_child;
			
			this.right_child=node.right_child;
			
		}
		
	}
	
	private Node node_root;
	
	private int count;
	
	//private boolean is_balanced=false;
	
	
	public AVL_imp() {
		
		this.node_root=null;
		
		this.count=0;
	}
	
	
	
	public int size() {
		
		return this.count;
		
	}
	
	public boolean isEmpty() {
			
		return this.count==0;
	}
	
	
	public boolean isBST() {
		
		List<K> keys=new ArrayList<K>();
		
		_inorder(this.node_root,keys);
		
		for (int i=0;i<keys.size()-1;i++)
			
			if(keys.get(i).compareTo(keys.get(i+1))>0)
				
				return false;
		
		return true;
		
	}
	
	/*
	public boolean isBST() {
		
		if (node_root==null)
			
			return false;
		
		else
		
		return isBST(node_root);
	}
	
	private boolean isBST(Node root) {
        
        Node node_cur=root;
        
         if(node_cur.left_child==null &&node_cur.right_child==null)
            
            
            return true;
        
         else if(node_cur.left_child==null && node_cur.key.compareTo(node_cur.right_child.key)<0 || node_cur.right_child==null &&node_cur.key.compareTo(node_cur.left_child.key)>0)
            
            
            return true;
        
        
        else if(node_cur.key.compareTo(node_cur.right_child.key)<0 &&node_cur.key.compareTo(node_cur.left_child.key)>0)
            
            
           return isBST(node_cur.left_child) &&isBST(node_cur.right_child);
            
            
        else
            
            return false;
        
    }

	*/
	public boolean isBalanced() {
		
		return isBalanced(this.node_root);
	}
	
	private boolean isBalanced(Node node) {
		
		if(node==null)  //  recursive end
			
			return true;
		
		if (Math.abs(get_balance_factor(node))>1)
			
			return false;
		
		return isBalanced(node.left_child) && isBalanced(node.right_child);
		
		
	}
	
	private int get_height(Node node) {  // calculate each node's height
		
		if(node==null)
			
			return 0;
		
		else
			
			return node.height;
	}
	
	private int get_balance_factor(Node node) {   // calculate each node's balance_factor
		
		if (node==null)
			
			return 0;
		
		else
			
			return get_height(node.left_child)-get_height(node.right_child);
	}
	
	public void insert(K key,V value) {
		
		node_root=_insert(node_root,key,value);
	}
	
	private Node _insert(Node node,K key,V value) {
		
		// RetNode=node;
		
		if(node==null) {
			
			return new Node(key,value);  //  new node, the height =1,  via construction function
		}
		
		
		
		if(key.compareTo(node.key)==0)
			
			node.value=value;
		
		
		//int height_node=node.height
		
		else if(key.compareTo(node.key)<0)
			

			node.left_child=_insert(node.left_child,key,value);
		
			// RetNode=node;
		
			//  if (RetNode.height==height_node )  is_balanced=true;
		else
			
			node.right_child=_insert(node.right_child,key,value);
		
		// RetNode=node;
		
		//  if (is_balance || RetNode.height==height_node )  is_balanced=true;
		
		// via recursive, update each node's height
		
		// 
		
		/*
		 *  	
		  if(is_balanced)
		  
		  	return RetNode;
		  	 	
		  	else{
		  	
		  	}

		*/
		node.height=1+Math.max(this.get_height(node.left_child), this.get_height(node.right_child));
		
		
		// calculate the balance_factor
		
		int balance_factor=this.get_balance_factor(node);
		
		
		//if(Math.abs(balance_factor)>1)
			
		//	System.out.println(balance_factor);
		
		// maintain the balance of AVL Tree,to rotate right(insert the node from the left of the unbalanced node)
		
		if(balance_factor>1 && get_balance_factor(node.left_child)>=0)
			
			return rightRotate(node);
		
		if(balance_factor<-1 && get_balance_factor(node.right_child)<=0)
			
			return leftRotate(node);
		
		//  LR
		
		if(balance_factor>1 && get_balance_factor(node.left_child)<0) {
			
			//  node :y ; node.left_child=x
			
			node.left_child=leftRotate(node.left_child);
			
			return rightRotate(node);
			
			
		}
		
		// RL
		
		if(balance_factor<-1 && get_balance_factor(node.right_child)>0) {
			
			// node :y; node.right_child=x
			
			node.right_child=rightRotate(node.right_child);
			
			return leftRotate(node);
			
			
		}
		
		return node;
				
	}
	
	
	private Node rightRotate(Node y) {
		
		// the first node "y", balance factor>1 =2
		
		// right rotate from node "y"
		
		Node x=y.left_child;
		
		Node T3 =x.right_child;
		
		x.right_child=y;
		
		y.left_child=T3;
		
		//update the height of x,y  
		
		y.height=Math.max(get_height(y.left_child), get_height(y.right_child))+1;
		
		x.height=Math.max(get_height(x.left_child), get_height(x.right_child))+1;
		
		return x;
		
	}
	
	
	private Node leftRotate(Node y) {
		
		// the first node "y", balance factor>1 =2
		
		// right rotate from node "y"
				
		Node x=y.right_child;
				
		Node T2 =x.left_child;
				
		x.left_child=y;
				
		y.right_child=T2;
				
		//update the height of x,y  
				
		y.height=Math.max(get_height(y.left_child), get_height(y.right_child))+1;
				
		x.height=Math.max(get_height(x.left_child), get_height(x.right_child))+1;
				
		return x;
		
	}
	/*
	public boolean contain(K key) {
		
		return _contain(this.node_root,key);
		
	}
	
	private boolean _contain(Node node,K key) {
		
		if(node==null)
			
			return false;
		
		if(key.compareTo(node.key)==0)
			
			return true;
		
		else if(key.compareTo(node.key)<0)
			
			return _contain(node.left_child,key);
		
		else
			
			return _contain(node.right_child,key);
		
	}
	*/
	
	private Node getNode(Node node,K key) {
		
		
		if(node==null)
			
			return null;
		
		if(key.compareTo(node.key)==0)
			
			return node;
		
		else if(key.compareTo(node.key)<0)
			
			return node.left_child;
		
		else 
			
			return node.right_child;
	}
	
	
	public int maxDepth() {
		
		return _maxDepth(node_root);
	}
	
	private int _maxDepth(Node node) {
		
		if(node ==null)
			
			return 0;
		
		else return Math.max(_maxDepth(node.left_child),_maxDepth(node.right_child))+1;
	}
	
	
	public boolean contain(K key) {
		
		return getNode(node_root,key)!=null; // high level expression
			
	}
	
	
	
	public V search(K key) {
		
	//	return _search(this.node_root,key);
		
		Node node;
		
		node=getNode(node_root,key);
		
		return node==null? null:node.value;
	}
	
	/*
	private V search(Node node,K key) {
		
		if(node==null)
			
			return null;
		
		
		if(key.compareTo(node.key)==0)
			
			return node.value;
		
		else if(key.compareTo(node.key)<0) {
			
			
			return _search(node.left_child,key);
		}
		
		else
			
			return _search(node.right_child,key);
		
	}
	
	*/
	
	public void set(K key,V Newvalue) {
		
		Node node;
		
		node=this.getNode(node_root, key);
		
		if(node==null)
			
			throw new IllegalArgumentException(key+"does not exists");
		
		else
			
			node.value=Newvalue;
	}
	
	private void _inorder(Node node,List<K> keys) {
		
		
		if (node==null)
			
			return;
		
		_inorder(node.left_child,keys);
		
		keys.add(node.key);
		
		_inorder(node.right_child,keys);
		
	}
	/*
	private void _inorder_no_rec(Node node,List<K>keys) {
		
		
		Node node_cur=node;
		
		if (node_cur==null)
			
			return;
		
		Stack<Node>stack= new Stack<Node>();
		
		while(node_cur!=null || !stack.isEmpty()) {
			
			while(node_cur!=null) {
				
				
			stack.push(node_cur);
			
			node_cur=node_cur.left_child;
			
			}
		
			node_cur=stack.pop();
			
			keys.add(node_cur.key);
		
			node_cur=node_cur.left_child;
		
		}
	}
	
	*/
	
	public void Level_order() {
		
		this._Level_order(node_root);
		
	}
	
	
	private void _Level_order(Node root) {
		
		if (root==null)

			return;

		Node node_cur=root;

		Queue<Node> queue=new LinkedList<Node>();

		queue.add(root);

		while(!queue.isEmpty()){
			
			node_cur=queue.remove();

			System.out.print(node_cur.key+"\t");

			if(node_cur.left_child!=null)

				queue.add(node_cur.left_child);

			if (node_cur.right_child!=null)

				queue.add(node_cur.right_child);

		}
	}
	
	private Node _findMin(Node node) {
		
		
		if(node.left_child!=null)
				
			node=_findMin(node.left_child);
		
		return node;
			
	}
	
	public void remove(K key ) {
		
		node_root=_remove(node_root,key);
		
	}
	
	/*
	private Node _removeMin(Node node) {
		
		
		if(node==null)
			
			return null;
		
		if(node.left_child==null) {
			
			Node rightNode =node.right_child;
			
			node.right_child=null;
			
			this.count--;
			
			return rightNode;
		}
			
			node.left_child=_removeMin(node.left_child);  //  
			
			// 被删除节点的  parent的左孩子 指空，或者指向 被删除节点的右孩子
		
			
			
			return node;
	}
	
	*/
	
	private Node _remove(Node node,K key) {
		
		if(node==null)
			
			return null;
		
		Node RetNode;
					
		if(key.compareTo(node.key)<0) {
			
			node.left_child=_remove(node.left_child,key);
		
			RetNode= node;
			
		}
		
		else if(key.compareTo(node.key)>0) {
			
			node.right_child=_remove(node.right_child,key);
			
			RetNode=node;
		}
			
		else { //  key.compareTo(node.key)==0
			
				if(node.left_child==null) {
					
					Node right_node=node.right_child;
					
					node.right_child=null;
					
					this.count--;
					
					RetNode=right_node;
				}
				
				else if(node.right_child==null) {
					
					Node left_node=node.left_child;
					
					node.left_child=null;
					
					this.count--;
					
					RetNode=left_node;
				}
				
				else {
				
				Node successor= new Node(_findMin(node.right_child));  
				
				// 新建一个successor 节点，等于  node(要删除的节点) 右子树中的最小值
				
				// 增加了一个节点
				
				this.count++;
				
				//successor.right_child=_removeMin(node.right_child);
				
				//  node.right中删除 原来的S节点，并把 successor的right 指向  原来node节点的右孩子
				
				successor.right_child=_remove(node.right_child,successor.key);
				
				// to maintain the balance;
				
				successor.left_child=node.left_child;
				
				node.left_child=node.right_child=null; // delete origin point
				
				this.count--;
				
				RetNode= successor;
				
				}
					
		}
		
			if(RetNode==null)
				
				return null;  // leaves nodes
		
			RetNode.height=1+Math.max(get_height(RetNode.left_child), get_height(RetNode.right_child));
			
			int balance_factor =get_balance_factor(RetNode);
			
			// LL
			
			if(balance_factor>1 && get_balance_factor(RetNode.left_child)>=0)
				
				return rightRotate(RetNode);
		
			// RR
			
			if(balance_factor<-1 && get_balance_factor(RetNode.right_child)<=0)
				
				return leftRotate(RetNode);
			
			
			//  LR
			
			if(balance_factor>1 && get_balance_factor(RetNode.left_child)<0) {
				
				RetNode.left_child=leftRotate(RetNode.left_child);
				
				return rightRotate(RetNode);
				
				
			}
			
			// RL
			
			if(balance_factor<-1 && get_balance_factor(node.right_child)>0) {
				
				RetNode.right_child=leftRotate(node.right_child);
				
				return leftRotate(RetNode);
				
			}
			
			return RetNode;
			
		}
	
			
	
	
	public static void main(String[] args) {
		
		AVL_imp<Integer,String> avl_imp=new AVL_imp<Integer,String>();
		
		avl_imp.insert(28,"a");
		
		avl_imp.insert(16,"b");
		
		//avl_imp.insert(30,"c");
		
		avl_imp.insert(13,"d");
		
		//avl_imp.insert(22,"e");
		
		//avl_imp.insert(29,"f");
		
		//avl_imp.insert(42,"g");
		
		System.out.println("Is BST Tree="+" "+avl_imp.isBST());
		
		System.out.println("Is Balanced Tree="+" "+avl_imp.isBalanced());
		
		avl_imp.remove(28);
		
		avl_imp.Level_order();
		
		System.out.println(avl_imp.search(16));
		
		System.out.println("max_depth="+avl_imp.maxDepth());
	
	}

}
