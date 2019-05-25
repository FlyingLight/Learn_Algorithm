/**
 * 
 */
package BST_USE_TEST;



import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/**
 * @author qiguangqin
 *
 */
public class BT_imp<K extends Comparable<K>,V> implements BT<K,V>{
	
	
	private class Node{   //public  内部类 通过   new outer()。new inner()  如果是内部类共有   ,内部类不允许直接创建接口
		
		private  K key;
		
		private V value;
		
		//private final static int aa=0;
		
		private Node left_child,right_child;
		
		 Node(K key,V value) {  // 成员内部类当中，不可以定义static 变量
			
			this.key=key;
			
			this.value=value;
			
			this.left_child=null;  // gc 会参与回收工作
			
			this.right_child=null;
			
			//BT_imp.this.node_root;
			
		//	BT_imp.this.contain(node, key)  可以直接访问外部类的私有变量 和私有函数
			
		//	System.out.print(BT_imp.this.count);  // 内部类 调用外部类 使用 outerclass。this。变量
			
			// 内部类可以自由地访问外部类的成员变量，无论是否是private的
			
			//  在外部类中如果要访问成员内部类的成员，必须先创建一个成员内部类的对象，再通过指向这个对象的引用来访问
			
			// 静态内部类是不需要依赖于外部类的，这点和类的静态成员属性有点类似，并且它不能使用外部类的非static成员变量或者方法
		}

		public Node(Node node) {
			
			this.key=node.key;
			
			this.left_child=node.left_child;
			
			this.right_child=node.right_child;
			
		}

		@Override
		public String toString() {
			return "[key=" + key + ", value=" + value + "]";
		}
		
		
	}
	
	private class TagNode{    //专门用于后项遍历,对于每一个节点增加一个属性，是否为第一次遍历到
		
		Node node;
		
		boolean isWatched;
		
		public TagNode(Node node){
			
			this.node=node;
			
			this.isWatched=false;
		}
	}
	
	// 局部内部类，表示在方法定义出来，只能访问代码块内的常量(不能是非final)，外部类的成员，不可以被 public protected private 修饰
	
	//只能方法内实例化，方法外不可以实例化，不能有static 变量
	
		private Node node_root; // 定义一个根节点
	
		private int count;
	
		public BT_imp() {  // 二叉树初始化构造函数
		
			node_root=null;
		
			count=0;
		}
	
		public int size() {  // 返回二叉树的大小
		
			return count;
		}
	
		public boolean isEmpty() {
		
			return count==0;
		}
	
		public void insert(K key,V value) {
		
		//	node_root=insert(node_root,key,value); // 返回插入节点后，二叉搜索树的根
			
			// 最开始向root 根插入
			
			node_root=insert_no_rec(node_root,key,value);
	
		
		}
		
		public boolean contain(K key) {
			
			
			return contain(node_root,key);
		}
		
		public boolean contain_no_rec(K key) {
			
			return this._contain_no_rec(node_root, key);
		}
		
		
		public V search(K key) {
			
			
			return search(node_root,key);
			
		}
		
		public V search_nr(K key) {
			
			return this.search_no_rec(node_root, key);
		}
		
		
		private Node insert(Node node,K key,V value) {
			
			
			if(node==null) {
				
				this.count++;
				
				return new Node(key,value);
			}
			
			if(key.compareTo(node.key)==0)
				
				//  外部类不能直接访问内部类的的成员，但可以通过内部类对象来访问
				
				node.value=value;
			
			else if(key.compareTo(node.key)<0)
				
				node.left_child=insert(node.left_child,key,value);
			
			//  如果key的值根小，向node的左子树做插入操作，node 左子树作为根
			
			else
				
				node.right_child=insert(node.right_child,key,value);
			
			return node;
			
			
		}
		
		private Node insert_no_rec(Node node,K key,V value){
			
			
			Node node_cur=node;

			
			if(node_cur==null) {

				node_cur = new Node(key,value);
				
				this.count++;
				
				return node_cur;
				
			}
			
						
			while(node_cur!=null){

				
				if(key.compareTo(node_cur.key)==0){

					node_cur.value=value;

					break;

				}
				
				
				else if(key.compareTo(node_cur.key)<0) {
					
					//node_cur=node_cur.left_child;

					if(node_cur.left_child==null){
						
						node_cur.left_child=new Node(key,value);
						
						this.count++;
						
						break;

					}
					
					else
						
						node_cur=node_cur.left_child;
				 }

				else {
							
				//	 node_cur=node_cur.right_child;

					 if(node_cur.right_child==null){

						 node_cur.right_child=new Node(key,value);
							
							this.count++;
							
							break;

					}
					 
					 else
						 
					 node_cur=node_cur.right_child;

					}

			}
			
			return node;
			
		}
	
		
		
		// 以node 为根的二叉树中查找key的节点
		
		private boolean contain(Node node,K key) {  // 二叉搜索树是否包含节点
			
			if(node==null)
				
				return false;
			
			
			if(key.compareTo(node.key)==0)
				
				return true;
			
			else if(key.compareTo(node.key)<0) {
				
				
				return contain(node.left_child,key);
			}
			
			else
				
				return contain(node.right_child,key);
			
		}
		
		// 在以node 为根的二叉搜索树，查找key对应的value
		
		
		private boolean _contain_no_rec(Node node,K key) {
			
			Node node_cur=node;
			
			boolean contain_ele=false;
			
			if(node_cur==null)
				
				return false;
			
			
			else {
				
				while(node_cur!=null) {
					
					if(key.compareTo(node_cur.key)==0) {
						
						contain_ele=true;
						
						break;
						
					}
						
						
					else if(key.compareTo(node_cur.key)<0)
						
						node_cur=node_cur.left_child;
					
					else
					
						node_cur=node_cur.right_child;
					
				}
				
				return contain_ele;
			}
	
			
		}
		
		
		private V search(Node node,K key) {
			
			if(node==null)
				
				return null;
			
			
			if(key.compareTo(node.key)==0)
				
				return node.value;
			
			else if(key.compareTo(node.key)<0) {
				
				
				return search(node.left_child,key);
			}
			
			else
				
				return search(node.right_child,key);
			
		}
		
		
		private V search_no_rec(Node node,K key) {
			
			
			assert node !=null &&  _contain_no_rec(node,key)==true;
			
			Node node_cur=node;
			
		
			while(node_cur!=null) {
				
			
				if(key.compareTo(node_cur.key)==0) {
					
					break;
					
				}
				
				else if(key.compareTo(node_cur.key)<0)
					
					node_cur=node_cur.left_child;
					
				else
						
					node_cur=node_cur.right_child;	
						
			}
			

			
			return node_cur.value;
			
		}
		
		public void preorder() {
			
			
			this._preorder(this.node_root);
		}
		
		
		public void preoder_no_rec() {
			
			this._preorder_no_rec(this.node_root);
			
			
		}
		
		private void _preorder_no_rec(Node root) {
			
		//	Vector<Integer> vec =new Vector<Integer>();
			
		
			
			if(root==null)
				
				return;
			
			Stack<Node>stack=new Stack<Node>();
			
			Node node_cur=root;
			
			while(node_cur!=null || !stack.isEmpty()) {
				
				while(node_cur!=null) {
					
					System.out.print(node_cur.key+"\t");  // 上下调换顺序不影响
					
					stack.push(node_cur);
					
					node_cur=node_cur.left_child;
				
				}
				
				node_cur=stack.pop(); // 将栈顶元素弹出
				
				node_cur=node_cur.right_child;
			}
		}
		
		
		
		private void _preorder_no_rec2(Node root) {
			
			
			if(root==null)
				
				return;
			
			Stack<Node>stack=new Stack<Node>();
			
			stack.push(root);
			
			while(!stack.isEmpty()) {
				
				Node node_cur=stack.pop();
				
				System.out.print(node_cur.key);
				
				
				if(node_cur.right_child!=null)
					
					stack.push(node_cur.right_child);
				
				else if(node_cur.left_child!=null)
					
					stack.push(node_cur.left_child);
				
			}
			
			
		}
		
		private void _preorder_no_rec3(Node root) {
			
			
			if(root==null)
				
				return;
			
			Stack<Node>stack=new Stack<Node>();
			
			Node node_cur=root;
			
			while(node_cur!=null||!stack.isEmpty()) {
				
				
				if(node_cur!=null) {
					
					
					System.out.print(node_cur.key+"\t");  // 打印之后依然需要入栈
					
					stack.push(node_cur);
					
					node_cur=node_cur.left_child;
					
					
				}
				
				else {
					
					node_cur=stack.pop();
					
					node_cur=node_cur.right_child;
					
				}
				
			}
			
		}
		
		
		private void _preorder(Node node) {
			
			if (node!=null) {
				
				System.out.print(node.key+"\t");
			
				_preorder(node.left_child);
				
				_preorder(node.right_child);
				
			}
			
		}
		
		 
		
		public void inorder() {
			
			
			this._inorder(this.node_root);
		}
		
		
		private void _inorder(Node node) {  // 中序遍历，二分搜索所树排序  小-》大
			
			if (node!=null) {
				
				
				_inorder(node.left_child);
			
				System.out.print(node.key+"\t");
				
				_inorder(node.right_child);
				
			}
			
		}
		
		
		public void inoder_no_rec() {
			
			this._inorder_no_rec(this.node_root);
			
			
		}
		
		private void _inorder_no_rec(Node root) {
			
			if(root==null)
				
				return;
			
			Stack<Node> stack=new Stack<Node>();
			
			Node node_cur=root;
			
			while(node_cur!=null || !stack.isEmpty()) {
				
				while(node_cur!=null) {
					
					stack.push(node_cur);
					
					node_cur=node_cur.left_child;
				}
				
				node_cur=stack.pop();
				
				System.out.print(node_cur.key+"\t");
				
				node_cur=node_cur.right_child;
				
			}
		}
		
		
		private void _inorder_no_rec2(Node root) {
			
			if (root==null)
				
				return;
			
			Stack<Node> stack=new Stack<Node>();
			
			Node node_cur=root;
			
			while(node_cur!=null || !stack.isEmpty()) {
				
				if(node_cur!=null) {
					
					stack.push(node_cur);
					
					node_cur=node_cur.left_child;
				}
				
				else {
				
				node_cur=stack.pop();
				
				System.out.print(node_cur.key+"\t");
				
				node_cur=node_cur.right_child;
				
			
			}
		}
			
		}
		
	public void postorder() {
			
			
			this._postorder(this.node_root);
		}
		
		
		private void _postorder(Node node) {
			
			if (node!=null) {
				
				
				_postorder(node.left_child);
			
				_postorder(node.right_child);
				
				System.out.print(node.key+"\t");
				
			}
			
		}
		
		public void postorder_no_rec() {
			
			this._postorder_no_rec5(this.node_root);
		}
		
		
		private void _postorder_no_rec(Node root) {  
			
			if(root==null)
				
				return;
			
			Stack<TagNode> stack=new Stack();
			
			Node node_cur=root;
			
			while(node_cur!=null || !stack.isEmpty()) {
				
				while(node_cur!=null) {
					
					stack.push(new TagNode(node_cur));
					
					node_cur=node_cur.left_child;
				}
				
				TagNode tagNode=stack.pop();
				
				node_cur=tagNode.node;
				
				if(tagNode.isWatched==false) {
					
					tagNode.isWatched=true;
					
					stack.push(tagNode);
					
					node_cur=node_cur.right_child;
				}
				
				else {
					
					System.out.print(node_cur.key+"\t");
					
					node_cur=null;
				}
			}
			
		}
		
		private void _postorder_no_rec2(Node root) {
			
			if (root==null) 
				
				return;
				
			Stack<Node>stack=new Stack<Node>();
			
			Stack<K> output=new Stack<K>();
			
			stack.push(root);
			
			while(!stack.isEmpty()) {
				
				Node node_cur=stack.pop();
				
				output.push(node_cur.key);
				
				if(node_cur.left_child!=null)
					
					stack.push(node_cur.left_child);
				
				if(node_cur.right_child!=null)
					
					stack.push(node_cur.right_child);
				
			}
			
			while(!output.isEmpty()) {
				
				System.out.print(output.pop()+"\t");
			}
			
		}
		
		private void _postorder_no_rec3(Node root) {
			
			
			if (root==null)
				
				return;
			
			Node node_cur=root;
			
			Stack<Node> stack =new Stack<Node>();
			
			Stack<K> output=new Stack<K>(); 
			
			while(node_cur!=null || !stack.isEmpty()) {
				
				if(node_cur!=null) {
					
					stack.push(node_cur);
					
					output.push(node_cur.key);
					
					node_cur=node_cur.right_child;
				}
				
				else {
					
					node_cur=stack.pop();
					
					node_cur=node_cur.left_child;
					
				}
			}
			
			while(!output.isEmpty()) {
				
				System.out.print(output.pop()+"\t");
			}
			
		}
		
		private void _postorder_no_rec4(Node root) {
			
			if(root==null)
				
				return;
			
			Node node_cur=root;
			
			Node pre=null;
			
			Stack<Node>stack= new Stack<Node>();
			
			while(node_cur!=null ||!stack.isEmpty()) {
				
				while(node_cur!=null) {
				
				stack.push(node_cur);
				
				node_cur=node_cur.left_child;
				
				}
			
			
			node_cur=stack.pop();
			
			if(node_cur.right_child==null || pre==node_cur.right_child)
				
			{
				System.out.print(node_cur.key+"\t");
				
				pre=node_cur;
				
				node_cur=null;
			}
			
			else {
				
				stack.push(node_cur);
				
				node_cur=node_cur.right_child;
			}
		}

		}
		
		
		private void _postorder_no_rec5(Node root) {
			
			if(root==null)
				
				return;
			
			Node node_cur=root;
			
			Node pre=null;
			
			Stack<Node>stack= new Stack<Node>();
			
			stack.push(root);
			
			while(!stack.isEmpty()) {
				
				node_cur=stack.pop();
				
				if((node_cur.left_child==null && node_cur.right_child==null) || (pre!=null && pre==node_cur.left_child && node_cur.right_child==null)|| (pre!=null && pre==node_cur.right_child)) {
					
					System.out.print(node_cur.key+"\t");
				
					pre=node_cur;
					
			}
			
			else {
				
				stack.push(node_cur);
				
				if(node_cur.right_child!=null)
					
					stack.push(node_cur.right_child);
				
				if(node_cur.left_child!=null)
					
					stack.push(node_cur.left_child);
				
			}
			
			}
		
		}
		
		
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
		
		
		public void removeMin1() {
			
			this._removeMin_no_rec(node_root);
			
		}
		
		private Node _removeMin(Node node) {
			
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
		
		
		private void _removeMin_no_rec(Node root) {
			
			Node node_cur=root;
			
			Node node_min;
			
			node_min=_findMin_no_rec(root);
				
				
			while(node_cur!=null) {    // 最小值 父节点
				
				
				if(node_cur.left_child==node_min)
					
					break;
				
				else
					
					node_cur=node_cur.left_child;
				
			}
			
			if(node_min.right_child==null) {
				
				node_cur.left_child=null;
				
				this.count--;
				
			}
			
			else {
				
				node_cur.left_child=node_min.right_child;
				
				node_min.right_child=null;
				
				this.count--;
				
			}
			
		}
		
		
		public K findMin() {
			
			
			assert(count!=0);
			
			Node min_node;
			
			min_node=_findMin(node_root);
			
			//min_node=_findMin_no_rec(node_root);
			
			return min_node.key;
		}
		
		
		public K findMax() {
			
			assert(count!=0);
			
			Node max_node;
			
			max_node=_findMax_no_rec(node_root);
			
			return max_node.key;
			
		}
		
		
		private Node _findMin(Node node) {
			
			
			if(node.left_child!=null)
					
				node=_findMin(node.left_child);
			
			return node;
				
		}
		
		private Node _findMax(Node node) {
			
			if(node.right_child!=null) 
				
				node=_findMax(node.right_child);
			
			return node;
			
			
		}
		
		private Node _findMin_no_rec(Node node) {
			
			assert (node!=null);
			
			Node node_cur=node;
			
			while(node_cur.left_child!=null)
				
				node_cur=node_cur.left_child;
			
			return node_cur;
			
		}
		
		
		private Node _findMax_no_rec(Node node) {
			
			assert (node!=null);
			
			Node node_cur=node;
			
			while(node_cur.right_child!=null)
				
				node_cur=node_cur.right_child;
			
			
			return node_cur;
			
		}
		
		private Node _removeMax(Node node) {
			
			if(node.right_child==null) {
				
				Node leftNode =node.left_child;
			
				node.left_child=null;
				
				this.count--;
				
				return leftNode;
			}
			
			node.right_child=_removeMax(node.right_child);
			
			return node;
		}
		
		public void remove(K key ) {
			
			node_root=_remove(node_root,key);
			
		
		}
		
		
		private Node _remove(Node node,K key) {
			
			
			if(node==null)
				
				return null;
						
			if(key.compareTo(node.key)<0) {
				
				node.left_child=_remove(node.left_child,key);
			
				return node;
				
			}
			
			else if(key.compareTo(node.key)>0) {
				
				
				node.right_child=_remove(node.right_child,key);
				
				return node;
			}
				
			else {
				
				
					if(node.left_child==null) {
						
						
						Node right_node=node.right_child;
						
						node.right_child=null;
						
						this.count--;
						
						return right_node;
					}
					
					
					if(node.right_child==null) {
						
						Node left_node=node.left_child;
						
						node.left_child=null;
						
						this.count--;
						
						return left_node;
					}
					
					Node successor= new Node(_findMin(node.right_child));  
					
					// 新建一个successor 节点，等于  node(要删除的节点) 右子树中的最小值
					
					// 增加了一个节点
					
					this.count++;
					
					successor.right_child=_removeMin(node.right_child);
					
					//  node.right中删除 原来的S节点，并把 successor的right 指向  min节点的右孩子
					
					successor.left_child=node.left_child;
					
					this.count--;
					
					return successor;
						
					}
					
			}
		
		
		
		public static void main(String[] args) {
			
			BT_imp<Integer,String> bt_imp=new BT_imp<Integer,String>();
			
			bt_imp.insert(28,"a");
			
			bt_imp.insert(16,"b");
			
			bt_imp.insert(30,"c");
			
			bt_imp.insert(13,"d");
			
			bt_imp.insert(22,"e");
			
			bt_imp.insert(29,"f");
			
			bt_imp.insert(42,"g");
			
		
		//	boolean f=bt_imp.contain(3);
			
	//		int Age=bt_imp.search_nr(3);
			
	//		System.out.println(Age);
			
			//bt_imp.preoder_no_rec();
			
		//	bt_imp.remove(22);
			
			bt_imp.postorder_no_rec();
			
			System.out.println();
			
			bt_imp.Level_order();
			
			System.out.println();
			
			System.out.println("Size of BST tree="+bt_imp.size()+"\t"+bt_imp.contain_no_rec(29)+"\t"+"  min value of tree="+bt_imp.findMin());
			
		}

}
