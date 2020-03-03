package BT_tree;

import java.util.LinkedList;
import java.util.Queue;



public class Path_Sum {
	
	private class TreeNode{
		
		int val;
		
		TreeNode left_child;
		
		TreeNode right_child;
		
		public TreeNode(int val) {
			
			this.val=val;
			
			left_child=null;
			
			right_child=null;
		}
		
		public TreeNode(TreeNode node) {
			
			this.val=node.val;
			
			this.left_child=node.left_child;
			
			this.right_child=node.right_child;
		}
		
	}
	
	TreeNode node_root;
	
	int size;
	
	public Path_Sum() {
		
		node_root=null;
		
		size=0;
	}
	
	
	public void insert(int val) {
		
		node_root= _insert(node_root,val);
	}
	
	private TreeNode _insert(TreeNode node,int val) {
		
		if(node==null) {
			
			this.size++;
			
			return new TreeNode(val);
		}
		
		if(val<node.val) {
			
			node.left_child=_insert(node.left_child,val);
			
		}
			
		else if(val>node.val) {
			
			node.right_child=_insert(node.right_child,val);
		}
		
		else {
			
			node.val=val;
		}
		
		return node;
			
	}
	
	public boolean path_sum_cal(TreeNode node,int sum) {
		
		
		if(node==null) // null node return false;
			
			return false;
		
		sum-=node.val;
		
		if(node.left_child==null && node.right_child==null)
			
			return sum==0;
		
		else
			
			return path_sum_cal(node.left_child,sum)||path_sum_cal(node.right_child,sum);
	}
	
	public void Level_order() {
		
		this._Level_order(node_root);
		
	}
	
	
	private void _Level_order(TreeNode root) {
		
		if (root==null)

			return;

		TreeNode node_cur=root;

		Queue<TreeNode> queue=new LinkedList<TreeNode>();

		queue.add(root);


		while(!queue.isEmpty()){
			
			node_cur=queue.remove();

			System.out.print(node_cur.val+"\t");

			if(node_cur.left_child!=null)

				queue.add(node_cur.left_child);

			if (node_cur.right_child!=null)

				queue.add(node_cur.right_child);

		}
	}
	
	
	public static void main(String[] args) {
		
		Path_Sum ps=new Path_Sum();
		
		ps.insert(2);
		ps.insert(1);
		ps.insert(4);
		ps.insert(6);
		ps.insert(3);
		
		boolean res=ps.path_sum_cal(ps.node_root,12);
		
		ps.Level_order();

		System.out.println(res);
	}
	

}
