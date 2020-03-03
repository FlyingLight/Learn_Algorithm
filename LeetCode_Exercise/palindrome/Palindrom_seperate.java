/**
 * 
 */
package palindrome;

import java.util.Arrays;

/**
 * @author qiguangqin
 *
 */
public class Palindrom_seperate {

	/**
	 * @param args
	 */
	
	public int LongestPalindrome_recur(String s) {
		
		int n=s.length();
			
		boolean[][] dp=new boolean[n][n];
		
		for(int i=n-1;i>=0;i--) 
			
			for(int j=i;j<n;j++) 
				
				dp[i][j]=s.charAt(i)==s.charAt(j)&&(j-i<2||dp[i+1][j-1]);
				
		return  _long_seperate_recur(s,0,dp);
	}
	
	
	private int _long_seperate_recur(String s,int start,boolean[][]dp) {
		
		
		if(dp[start][s.length()-1]) return 0;
		
		int res=Integer.MAX_VALUE;
		
		for(int i=start;i<s.length();i++) {
			
			if(dp[start][i]) {
				
				res=Math.min(res,_long_seperate_recur(s,i+1,dp)+1);
			}
			
			
		}
		
		return res;
		
	}
	
	public int LongestPalindrome_recur_memo(String s) {
		
		int n=s.length();
		
		boolean[][] dp=new boolean[n][n];
		
		int[]memo=new int[s.length()];
		
		Arrays.fill(memo, -1);
		
		for(int i=n-1;i>=0;i--) 
			
			for(int j=i;j<n;j++) 
				
				dp[i][j]=s.charAt(i)==s.charAt(j)&&(j-i<2||dp[i+1][j-1]);
				
		return  _long_seperate_recur(s,0,dp,memo);
		
	}
	
	
	private int _long_seperate_recur(String s,int start,boolean[][]dp,int[]memo) {
		
		if(memo[start]!=-1) return memo[start];
	
		if(dp[start][s.length()-1]) return memo[start]=0;
		
		int res=Integer.MAX_VALUE;
		
		for(int i=start;i<s.length();i++) {
			
			if(dp[start][i]) {
				
				res=Math.min(res,_long_seperate_recur(s,i+1,dp)+1);
			}
			
			
		}
		
		return memo[start]=res;
		
	}
	
	
	
	public int long_seperate_dp(String s) {
		
		int[] dp= new int[s.length()];
		
		dp[0]=0;
		
		boolean [][]judge =new boolean[s.length()][s.length()];
		
		for(int i=0;i<s.length();i++){
			
			dp[i]=i;
			
			for(int j=0;j<=i;j++) {
				
				if(s.charAt(i)==s.charAt(j) && (i-j<2 || judge[j+1][i-1])) {
					
					judge[j][i]=true;
					
					dp[i]=j==0?0:Math.min(dp[i], dp[j-1]+1);
					
				}
				
			}
			
		}
		
		return dp[s.length()-1];
		
	}
	

	
	public static void main(String[] args) {

		 Palindrom_seperate ps= new  Palindrom_seperate();
		 
		 String s="bqabbacdcxd";
		 
		 int count= ps.LongestPalindrome_recur(s);
		 
		 int count1=ps.LongestPalindrome_recur_memo(s);
		 
		 System.out.println("recur_method="+count);
		
		 System.out.println("recur_method_memo="+count1);
		 
		 int count2=ps.long_seperate_dp(s);
		 
		 System.out.println("dp_method="+count2);

	}

}
