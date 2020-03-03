/**
 * 
 */
package dp_other_design;

/**
 * @author qiguangqin
 *
 */
public class Integer_Max_Break {

	/**
	 * @param args
	 */
	
	// Leetcode 343
	
	private int n;
	
	private int[]dp;
	
	public Integer_Max_Break(int n) {
		
		this.n=n;
		
		if(n<=0) throw new IllegalArgumentException(" Invalid Argument N must above zero");
		
		dp= new int[n+1];
		
		dp[1]=1;
	}
	
	public int get_max_res() {
		
		for(int i=2;i<=n;i++) {
			
			for(int j=1;j<i;j++) {
				
				/*
				 
				 dp[i]=max(dp[i],max(dp[j],j)*(i-j))
				
				*/
		
				dp[i]=Math.max(dp[i],Math.max(dp[j], j)*(i-j));
		}
		
		}
		
		return dp[n];
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int n=20;
		
		Integer_Max_Break imb= new Integer_Max_Break(n);
		
		int res= imb.get_max_res();
		
		System.out.println(res);

	}

}
