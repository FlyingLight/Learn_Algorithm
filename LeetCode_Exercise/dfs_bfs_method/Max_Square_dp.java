/**
 * 
 */
package dfs_bfs_method;

/**
 * @author qiguangqin
 *
 */
public class Max_Square_dp {

	/**
	 * @param args
	 * 
	
	 LeetCode 221
	  
	  Maximal Square 
	  
	  DP recurse
	 */
	
	private char[][] matrix;
	
	private int row;
	
	private int column;
	
	public Max_Square_dp(char[][]matrix) {
		
		this.matrix=matrix;
		
		this.row=matrix.length;
		
		this.column=matrix[0].length;
		
		
	}
	
	/*
	 * 
	 dp[i][j]=min(dp[i-1][j],dp[i][j-1]
	 
	 i(1-->row)
	 
	 j(1-->column)
	 
	   dp[j-1] has been updated in j loop
	   
	  instead
	  
	  i(1-->row)
	  
	  j(column-->1) dp[j-1] has not been updated in j loop
	  
	  pre  dp[i-1][j-1]
	 
	 */
	
	
	public int get_max_square_dp1() {
		
		int cur_max=0;
		
		int[][]dp1 = new int[row][column];
		
		for(int j=0;j<column;j++) dp1[0][j]=matrix[0][j]-'0';
		
		for(int i=0;i<row;i++) dp1[i][0]=matrix[i][0]-'0';
		
		for(int i=1;i<row;i++) {
			
			for(int j=1;j<column;j++) {
				
				if(matrix[i][j]-'1'==0) {
					
					dp1[i][j]=Math.min(Math.min(dp1[i-1][j], dp1[i][j-1]),dp1[i-1][j-1])+1;
					
					cur_max=Math.max(cur_max, dp1[i][j]);
				}
				
				
			}
		}
		
		return cur_max*cur_max;
		
	}
	
	
	public int get_max_square_dp2() {
		
		int cur_max=0;
		
		int []dp2= new int[column+1]; // must add one element before the first for "pre"
		
		for(int i=1;i<=row;i++) {
			
			dp2[0]=0;
			
			int pre=0;
			
			for(int j=1;j<=column;j++) {
				
				int temp=dp2[j];
				
				if(matrix[i-1][j-1]-'0'==1) {
					
					dp2[j]=Math.min(Math.min(dp2[j-1], dp2[j]),pre)+1;
					
					// dp2[i][j]=min(dp2[i][j-1],dp2[i-1][j-1],dp2[i-1][j-1])+1	
					
					// dp2[j-1] (dp2[i][j-1] has been updated) 
					cur_max=Math.max(cur_max, dp2[j]);
					
				}
				
				else dp2[j]=0;
				
				pre=temp;
				
			}
		}
		
		return cur_max*cur_max;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		char[][] matrix= {{'0','1','1','1','0'},{'1','1','1','1','0'},
				{'0','1','1','1','1'},{'0','1','1','1','1'},{'0','0','1','1','1'}
		};
		
		Max_Square_dp msd= new Max_Square_dp(matrix);
		
		int res= msd.get_max_square_dp2();
		
		System.out.println(res);

	}

}
