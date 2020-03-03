/**
 * 
 */
package base_dp_1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author qiguangqin
 *
 */
public class Square_Num_Count {

	/**
	 * @param args
	 */
	
	private int N;
	
	private int[]memo;
	
	private class Node{
		
		int val;
		
		int step;
		
		public Node(int val,int step) {
			
			this.val=val;
			
			this.step=step;
		}
	}
	
	
	//private List<Integer>res;
	
	public Square_Num_Count(int N) {
		
		this.N=N;
		
		//res= new ArrayList<>();
		
		memo= new int[N+1];
		
		//for(int i=0;i<=N;i++) memo[i]=i;
	}
	
	public int get_res_recursive() {
		
		return _get_res_recursive(N);
	}
	
	public int get_res_recursive_memo() {
		
		return _get_res_recursive_memo(N);
	}
	
	
	private int _get_res_recursive(int n) {
		
		int min=Integer.MAX_VALUE;
		
		if(n==0) return 0;
		
		int val=(int )Math.sqrt(n);
			
		if(val*val==n) return 1; // trim the recursion_tree;
			
		for(int i=1;i*i<=n;i++) {
			
			min=Math.min(min,_get_res_recursive(n-i*i)+1);
			
		//res.add(index);
		}
		
		return min;
		
	}
	
	private int _get_res_recursive_memo(int n) {
		
		
		if(memo[n]!=0 || n==0) return memo[n];
		
		int val= (int)Math.sqrt(n); 
		
		if(val*val==n) {return memo[n]=1; }//return 1;}
		
		int min=Integer.MAX_VALUE;
		
		for(int i=1;i*i<=n;i++) {
			
			min=Math.min(min,_get_res_recursive_memo(n-i*i)+1);
			
		//res.add(index);
		}
		
		return memo[n]=min;
		
		//return min;
		
	}
	
	// bfs  From n--->0
	
	public int get_res_bfs(int n) {
		
		Queue<Node> queue= new LinkedList<>();
		
		queue.add(new Node(n,0));
		
		boolean []visited= new boolean[n+1];
		
		//int[] pre;
		
		//pre= new int[n+1];
		
		while(!queue.isEmpty()) {
			
			int num=queue.peek().val;
			
			int step= queue.peek().step;
			
			queue.remove();
			
			for(int i=1;num-i*i>=0;i++) {
				
				if(num-i*i==0)
					
					return step+1;
				
				if(!visited[num-i*i]) {
					
					queue.add(new Node(num-i*i,step+1));
					
					
				}
			}
			
		
		}
		
		return -1;
	}
	
	public int get_res_dp() {
		
		int[] dp= new int[N+1];
		
		for(int i=0;i<=N;i++) dp[i]=i;
		
		for(int i=1;i<=N;i++) {
			
			for(int j=1;i-j*j>=0;j++) {
				
				dp[i]=Math.min(dp[i], dp[i-j*j]+1);
			}
		}
		
		return dp[N];
	}
	
	
	public static void main(String[] args) {
		
		int n=18;
		
		Square_Num_Count snc = new Square_Num_Count(n);
		
		int count=snc.get_res_recursive();
		
		int count1=snc.get_res_recursive_memo();
		
		int count2= snc.get_res_dp();
		
		System.out.println(count+" "+count1+" "+count2);

	}

}
