/**
 * 
 */
package dfs_bfs_method;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * @author qiguangqin
 *
 */
public class Generate_BST_1_2 {

	/**
	 * @param args
	 * 
	 */
	
	private int n;
	
	 class TreeNode{
		
		int val;
		
		private TreeNode left;
		
		private TreeNode right;
		
		public TreeNode(int x) {
			
			this.val=x;
			
			this.left=null;
			
			this.right=null;
		}
		
		@Override
		public String toString(){
					
			return val+" ";
			
		}
		
	}
	
	//private List<TreeNode>res;
	
	public Generate_BST_1_2(int n) {
		
		this.n=n;
		
	//	res=new LinkedList<>();
	}
	
	public List<TreeNode> generate_bst(){
		
		if (n == 0) {
			      return new LinkedList<TreeNode>();
			    }
		
		return  _Generate_BST(1,n);
	}
	
	public List<TreeNode> _Generate_BST(int start,int end){
		
		List<TreeNode> res= new LinkedList<>();
		
		if(start>end) {
			
			res.add(null);
			
			return res;
		}
		
		for(int i=start;i<=end;i++) {
			                                                          //i
			List<TreeNode>left_tr=_Generate_BST(start,i-1);//[start,i-1]    [i+1,end]
			
			List<TreeNode>right_tr=_Generate_BST(i+1,end);
			
			//Connect left and right trees to the root i
			
			for(TreeNode left_node:left_tr) {
				
				for(TreeNode right_node:right_tr) {
					
					TreeNode cur_tree=new TreeNode(i);
					
					cur_tree.left=left_node;
					
					cur_tree.right=right_node;
					
					res.add(cur_tree);
				}
			}
		}
		
		return res;
	}
	
	
	// Tree dp
	/*
	public List<TreeNode> generateTrees_dp(int n){
		
		
		// pre to save the backward result
		
		List<TreeNode>pre =new ArrayList<>();
		
		if(n==0) return pre;
		
		pre.add(null);
		
		for(int i=1;i<=n;i++) {
			
			List<TreeNode>cur= new ArrayList<>();
			
			// Traverse all the back-ward answer
			
			for(TreeNode root:pre) {
				
				TreeNode insert= new TreeNode(i);
				
				insert.left=root;
				
			}
			
			
			
			
		}
		
		
		
	}
	*/

	
	private List<TreeNode> _Level_order(TreeNode root) {
		
		List<TreeNode>res= new LinkedList<>();
		
		if (root==null)

			return res;

		TreeNode node_cur=root;

		Queue<TreeNode> queue=new LinkedList<TreeNode>();
		
	
		queue.add(root);


		while(!queue.isEmpty()){
			
			node_cur=queue.remove();

			//System.out.print(node_cur.val+"\t");
			
			res.add(node_cur);

			if(node_cur.left!=null)

				queue.add(node_cur.left);

			if (node_cur.right!=null)

				queue.add(node_cur.right);

		}
		
		return res;
	}
	
	public static void main(String[] args) {
	
		int n=5;
		
		Generate_BST_1_2 gb12=new Generate_BST_1_2(n);
		
		List<TreeNode>res_root=gb12.generate_bst();
		
		List<List<TreeNode>> final_res=new LinkedList<>();
		
		for(TreeNode node:res_root) {
			
			List<TreeNode>temp=new LinkedList<>();
			
			temp=gb12._Level_order(node);
			
			final_res.add(temp);
		}
		
		System.out.println(final_res);
	}
	
}
