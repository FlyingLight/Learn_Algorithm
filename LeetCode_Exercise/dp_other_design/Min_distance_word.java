/**
 * 
 */
package dp_other_design;

import java.util.Arrays;

/**
 * @author qiguangqin
 *
 */
public class Min_distance_word {

	/**
	 * @param args
	 */
	
	private String s1,s2;
	
	private int[][]dp;
	
	private int[]dp1;
	
	private int[][] memo;
	
	public Min_distance_word(String s1,String s2) {
		
		this.s1=s1;
		
		this.s2=s2;
		
		dp= new int[s1.length()+1][s2.length()+1];
		
		dp1= new int[s2.length()+1];
		
		memo=new int[s1.length()+1][s2.length()+1];
		
		for(int i=0;i<=s1.length();i++)
		
			Arrays.fill(memo[i], -1);
		
		/*
		 
		       "" r  o  s  (j)// word2=ros  word1=horse
		   ""  0  1  2  3
		   h   1  1  2  3
		   o   2  2  1  2
		   r   3  2  2  2
		   s   4  3  3  2
		   e   5  4  4  3
		   
		  (i)
		   
		  dp[i-1][j-1] // replace 
		  
		  dp[i-1][j] //delete word1  index=i 
		  
		  dp[i][j-1] // insert word2 at index=j
		   
		   i= horse  6
		   
		   j=ros 4
		 
		 */
		
		for(int i=1;i<=s1.length();i++)
			
			dp[i][0]=i;
		
		for(int j=1;j<=s2.length();j++)
			
			dp[0][j]=j;
	}
	
	public int get_Res_dp() {
		
		for(int i=1;i<=s1.length();i++) {
			
			for(int j=1;j<=s2.length();j++) {
				
				if(s1.charAt(i-1)==s2.charAt(j-1)) 
					
					dp[i][j]=dp[i-1][j-1];
					
					else 
						
						dp[i][j]=Math.min(Math.min(dp[i-1][j-1], dp[i][j-1]),dp[i-1][j])+1;
					
				}
			}
		
		return dp[s1.length()][s2.length()];
		
	}
	
	
	public int get_Res_dp_1() {
		
		
		/*
		 
	       "" r  o  s  (j)// word2=ros  word1=horse
	   ""  0  1  2  3
	   h   1  1  2  3
	   o   2  2  1  2
	   r   3  2  2  2
	   s   4  3  3  2
	   e   5  4  4  3
	   
	 */
		
		for(int j=1;j<=s2.length();j++) dp1[j]=j;
	
		for(int i=1;i<=s1.length();i++) {
			
			dp1[0]=i;
			
			int pre=dp1[0]-1;
			
			for(int j=1;j<=s2.length();j++){
				
				int temp=dp1[j];
				
				if(s1.charAt(i-1)==s2.charAt(j-1)) 
					
					dp1[j]=pre;
				
				else 
					
					dp1[j]=Math.min(Math.min(dp1[j], dp1[j-1]),pre)+1;
							
				pre=temp;		
			}
		}
		
		return dp1[s2.length()];
		
	}
	

	public int get_min_distance_recur() {
		
		return _get_min_distance_recur(0,0);
	}
	
	
	private int _get_min_distance_recur(int i,int j) {
		
		if(i==s1.length() || j==s2.length())
			
			return s1.length()-i+s2.length()-j;
		
		if(s1.charAt(i)==s2.charAt(j)) return _get_min_distance_recur(i+1,j+1);
		
		else {
			
			// from index i (word s1) ---> to index j(word s2)
			
			// i+1 ,j+1 replace;
			
			// i+1,j delete (index i) 
			
			// i,j+1 insert 
			
			int replaced=_get_min_distance_recur(i+1,j+1);
			
			int inserted=_get_min_distance_recur(i,j+1);
			
			int deleted= _get_min_distance_recur(i+1,j);
			
			return Math.min(Math.min(replaced, deleted),inserted)+1;
		}
	}
	
	public int get_min_distance_recur_memo() {
		
		return _get_min_distance_recur_memo(0,0);
	}
	
	
	private int _get_min_distance_recur_memo(int i,int j) {
		
		if(memo[i][j]!=-1) return memo[i][j];
		
		if(i==s1.length()||j==s2.length()) 
			
			return memo[i][j]=s1.length()+s2.length()-i-j;
		
		if(s1.charAt(i)==s2.charAt(j)) {
			
			return _get_min_distance_recur_memo(i+1,j+1);
			
		}
		
		else {
			
			int replace=_get_min_distance_recur_memo(i+1,j+1);
			
			int delete=_get_min_distance_recur_memo(i+1,j);
			
			int insert=_get_min_distance_recur_memo(i,j+1);
			
			return memo[i][j]=Math.min(Math.min(replace, delete),insert)+1;
		}
		
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String s1="horse",s2="ros";
		
		Min_distance_word mdw= new Min_distance_word(s1,s2);
		
		int res=mdw.get_Res_dp();
		
		System.out.println("use_dp_2="+res);
		
		/*
		for(int i=0;i<=s1.length();i++) {
			
			for(int j=0;j<=s2.length();j++)
				
				System.out.print(mdw.dp[i][j]+" ");
		
		System.out.print("\n");
		
		}
		*/
		
		
		int count =mdw.get_min_distance_recur();
		
		System.out.println("use_recur="+count);
		
		int ans= mdw.get_Res_dp_1();
		
		System.out.println("use dp 2="+ans);
		
	

	}

}
