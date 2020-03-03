/**
 * 
 */
package palindrome;

/**
 * @author qiguangqin
 *
 */
public class Longest_Palindrom_Seq {

	/**
	 * @param args
	 */
	
	private int max_sum=0;
	
	private String ans;
	
//	private boolean[][] memo;
	
	
	
	private boolean Is_Palindrom_seq(String s) {
		
		int N=s.length();
		
		for(int i=0;i<N/2;i++) {
			
			if(s.charAt(i)!=s.charAt(N-1-i)) 
				
				return false;
				
		}
				
		return true;
	}
	
	/*
	private boolean Is_Palindrom_seq_recursive(String s,int start, int end) {
		
		if(start>=end) return true;
		
		else 
			
			return s.charAt(start)==s.charAt(end) && Is_Palindrom_seq_recursive(s, start+1, end-1);
		
	}
	
	*/
	
	public void max_palindrom_sub(String s,int start) {
		
		if(start==s.length()) return;
		
		
		for(int end=start+1;end<=s.length();end++) {
			
			if(Is_Palindrom_seq(s.substring(start, end))) { 
				
				if((end-start)>max_sum) {
					
					ans=s.substring(start,end);
					
					max_sum=Math.max(max_sum, end-start);
				}
								
			}
			
			max_palindrom_sub(s,end);//  end = the start of next seq
		}
		
	}
	
	
	
	public String Longest_common_seq(String s,String t) {
		
		
		int m=s.length();
		
		int n=t.length();
		
		//int max=0;
		
		int max_end=0;
		
		int max_len=0;
		
		
		int[][] dp= new int[m][n];
		
		for(int i=0;i<m;i++) 
			
			for(int j=0;j<n;j++) {
				
				if(s.charAt(i)==t.charAt(j)) {
					
					if(i==0|| j==0) {
						
						dp[i][j]=1;
					}
					
					else {
						
						dp[i][j]=dp[i-1][j-1]+1;
					}
					
				}
				
				//max_sum=dp[i][j]>max_sum? dp[i][j]:max_sum;
				
				//ss=s.substring(j-max_sum+1, j);
				
				if(dp[i][j]>max_len) {
					
					max_len=dp[i][j];
					
					max_end =j;
				}
				
			}
		
			return t.substring(max_end-max_len+1,max_end+1);
		
	}
	
	public String LongestPalindrome(String s) {
		
		int n=s.length();
		
		//int max=0;
		
		String res="";
		
		boolean[][] dp=new boolean[n][n];
		
		for(int i=n-1;i>=0;i--) {
			
			// dp[i][i]=true;
			
		for(int j=i;j<n;j++) {// j=i+1 ?
				
		//	for(int j=1;j<n;j++){
		
		//		for(int i=0;i<j;i++){
			
		//	for(int i=n-1;i>=0;i--) {
				
			//	for(int j=n-1;j>=i;j--) {
				
				dp[i][j]=s.charAt(i)==s.charAt(j)&&(j-i<2||dp[i+1][j-1]);
				
				if(dp[i][j] && j-i+1>res.length()) {
					
					res=s.substring(i,j+1);
					
				//	max=Math.max(max, j-i+1);
				}
			}
		}
		
		return res;
	}
	
	
	
	public int longest_Palindrome_Subseq(String s) {
		
		int n=s.length();
		
		int[][] dp= new int[n][n];
		
		for(int i=n-1;i>=0;i--) {
			
			dp[i][i]=1;
			
			for(int j=i+1;j<n;j++) {
				
				if(s.charAt(i)==s.charAt(j)) dp[i][j]=dp[i+1][j-1]+2;
				
				//  if i+1>j-1  dp[i+1][j-1]=0
				
				else  dp[i][j]=Math.max(dp[i+1][j], dp[i][j-1]);
			}
			
			
		}
		
		return dp[0][n-1];
		
	}
	/*
	public int longest_Palindrome_Subseq_v2(String s) {
		
		int n=s.length();
		
		int[] dp =new int [n];
		
		for(int i=n-1;i>=0;i--) {
			
			dp[i]=1;
			
			for(int j=i+1;j<n;j++) {
				
				if(s.charAt(i)==s.charAt(j)) dp[j]=dp[j-1]+2;
				
				else dp[j]=Math.max(dp[j], dp[j-1]);
			}
				
				
		}
		
		return dp[n-1];
	}
	
	*/
	
	
	public String LongestPalindrome2(String s) {
		
		int n=s.length();
		
		String res="";
		
		boolean []dp= new boolean[n];
		
		for(int i=n-1;i>=0;i--) {
			
			for(int j=n-1;j>=i;j--) {
				
				dp[j]=s.charAt(i)==s.charAt(j) &&(j-i<=2|| dp[j-1]);
				
				if(dp[j] && j+1-i>res.length()) {
					
					res=s.substring(i, j+1);
				}
			}
		}
		
		return res;
		
	}
	
	public static void main(String[] args) {
		
		
		Longest_Palindrom_Seq lps= new Longest_Palindrom_Seq();
		
		String s="2002";
		
		boolean flag1=lps.Is_Palindrom_seq(s);
		
		//boolean flag2=lps.Is_Palindrom_seq_recursive(s, 0,s.length()-1);
		
		//String s1="cbbcd";
		
		String s1= "cbdbc";
		
		String s3="1AB2345CD";
		
		String s4="12345EF";
		
		lps.max_palindrom_sub(s1, 0);
		
		System.out.println(lps.Longest_common_seq(s3, s4));
		
		String s6=lps.LongestPalindrome2(s1);
		
		System.out.println(flag1+" "+lps.max_sum+" "+lps.ans);
		
		System.out.println(s6);

	}

}
