/**
 * 
 */
package dp_other_design;

import java.util.Arrays;

/**
 * @author qiguangqin
 *
 */
public class Dp_tree_test {

	/**
	 * @param args
	 */
	
	
	private int[]memo;
	
	public int numTrees_dp(int n) {
		
		// f(i)=G(i-1)*G(n-i)  // i number as the root of the tree
		
		// f(n)=f(1)+f(2)+...+f(n)=G(0)*G(n-1)+G(1)*G(n-2)+...+G(n-1)*G(0)
		
		int[] G = new int[n+1];
		
		G[0]=1;
		
		G[1]=1;
		
		for(int i=2;i<=n;i++) {
			
			for(int j=1;j<=i;j++) {
				
				
				G[i]+=G[j-1]*G[i-j];
			}
		}
		
		return G[n];
	}
	
	public int num_trees_recur(int n) {
		
		return _num_trees_recur(1,n);
	}
	
	
	
	
	private int _num_trees_recur(int start,int end) {
		
		int sum=0;
		
		if(start>=end) return 1;
		
		for(int i=start;i<=end;i++) {
													//          i
			int left=_num_trees_recur(start,i-1);;//[start,i-1]    [i+1,end]
			
			int right=_num_trees_recur(i+1,end);
			
			sum+=left*right;
			
		}
		
		return sum;
		
	}
	
	public int num_trees_recur2(int n) {
		
		return _num_trees_recur2(n);
	}
	
	private int _num_trees_recur2(int n) {
		
		if(n==0 || n==1 ) return 1;
		
		int sum=0;
		
		for(int i=1;i<=n;i++) {
			
			int left=_num_trees_recur2(i-1);
			
			int right=_num_trees_recur2(n-i);
			
			sum+=left*right;
			
			
		}
		
		return sum;
	}
	
	
	public int num_trees_recur3_memo(int n) {
		
		
		memo=new int[n+1];
		
		Arrays.fill(memo, -1);
		
		return _num_trees_recur3_memo(n,memo);
	}
	
	private int _num_trees_recur3_memo(int n,int[] memo) {
		
		
		if(memo[n]!=-1) return memo[n];
		
		if(n==0 || n==1 ) return memo[n]=1;
		
		int sum=0;
		
		for(int i=1;i<=n;i++) {
			
			int left= _num_trees_recur3_memo(i-1,memo);
			
			int right= _num_trees_recur3_memo(n-i,memo);
			
			sum+=left*right;
			
			
		}
		
		return memo[n]=sum;
	}
	
	
	public static void main(String[] args) {
	
		Dp_tree_test dtt = new Dp_tree_test();
		
		int n=10;
		
		int res1= dtt.numTrees_dp(n);
		
		System.out.println("num_Trees_dp="+" "+res1);
		
		int res2=dtt.num_trees_recur(n);
		
		System.out.println("num_Trees_recur1="+" "+res2);
		
		int res3=dtt.num_trees_recur(n);
		
		System.out.println("num_Trees_recur2="+" "+res3);
		
		int res4=dtt.num_trees_recur3_memo(n);
		
		System.out.println("num_Trees_recur_memo_1="+" "+res4);
		
		
	}

}
