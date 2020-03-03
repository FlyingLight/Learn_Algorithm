/**
 * 
 */
package dfs_bfs_method;

/**
 * @author qiguangqin
 *
 */
public class Inter_Leave_String {

	/**
	 * @param args
	 * 
	 * 
	 Leetcode
	 
	 */
	
	private String s1;
	
	private String s2;
	
	private String target;
	
	public Inter_Leave_String(String s1,String s2,String target) {
		
		this.s1=s1;
		
		this.s2=s2;
		
		this.target=target;
	}
	
	public boolean is_Inter_leave(int i,int j) {
		
		return _is_inter_leave_recur(i,j,"");
	}
	
	private boolean _is_inter_leave_recur(int i,int j ,String res) {
		
		if(res.equals(target) && i==s1.length() && j==s2.length())
			
			return true;
		
		boolean flag=false;
		
		if(i<s1.length())
			
			flag|=_is_inter_leave_recur(i+1, j, res+s1.charAt(i));
		
		if(j<s2.length())
			
			flag|=_is_inter_leave_recur(i, j+1, res+s2.charAt(j));
		
		return flag;
		
	}
	
	
	public boolean isInter_leave_by_memo() {
		
		int [][] memo= new int[s1.length()][s2.length()];
		
		for(int i=0;i<s1.length();i++)
			
			for(int j=0;j<s2.length();j++)
				
				memo[i][j]=-1;
		
		return _is_Inter_leave_recur_memo(0,0,0,memo);
		
	}
	
	
	
	private boolean _is_Inter_leave_recur_memo(int i,int j,int k,int [][] memo) {
		
		if(i==s1.length()) 
			
			return s2.substring(j).equals(target.substring(k));
		
		
		if(j==s2.length()) 
			
			return s1.substring(i).equals(target.substring(k));
		
		
		if(memo[i][j]>=0) 
			
			return memo[i][j]==1? true:false;
		
		boolean res=false;
		
		if(target.charAt(k)==s1.charAt(i) && 
				
		_is_Inter_leave_recur_memo(i+1,j,k+1, memo) ||
		
		target.charAt(k)==s2.charAt(j) && _is_Inter_leave_recur_memo(i, j+1, k+1, memo)) {
			
			res=true;
			
		}
		
		memo[i][j]=res?1:0;
		
		return res;
				
	}
	
	
	public boolean is_inter_leave_dp() {
		
	    if( target.length()!=s1.length()+s2.length()) return false;
		
		int m=s1.length(),n=s2.length();
		
		boolean[][]dp= new boolean[m+1][n+1];
		
		//dp[0][0]=true;
		
		for(int i=0;i<=s1.length();i++) {
			
			for(int j=0;j<=s2.length();j++) {
				
				if(i==0 && j==0) dp[i][j]=true;
				
				else if(j==0)
					
					dp[i][j]=dp[i-1][j] && target.charAt(i+j-1)==s1.charAt(i-1);
				
				else if(i==0) // dp[i][j]  s1.substring(0,i)+ s2.charAt(j-1)  || substring(0,j)
 					
					dp[i][j]=dp[i][j-1] && target.charAt(i+j-1)==s2.charAt(j-1);
				
				else 
					
					dp[i][j]=(dp[i-1][j] && target.charAt(i+j-1)==s1.charAt(i-1)) 
					
					|| (dp[i][j-1] && target.charAt(i+j-1)==s2.charAt(j-1));
				
			}
		}
		
		return dp[m][n];
		
	}
	
	public boolean is_inter_leave_dp2() {
		
		if(target.length()!=s1.length()+s2.length()) return false;
		
		int m=s1.length(),n=s2.length();
		
		boolean []dp2= new boolean[n+1];
		
		for(int i=0;i<=m;i++) {
			
			for(int j=0;j<=n;j++) {
				
				if(i==0 && j==0) dp2[j]=true;
				
				else if(j==0) 
					
					dp2[j]=dp2[j] && target.charAt(i+j-1)==s1.charAt(i-1);
				
				else if(i==0) 
					
					dp2[j]=dp2[j-1] && target.charAt(i+j-1)==s2.charAt(j-1);
				
				else 
					
					dp2[j]=(dp2[j] && target.charAt(i+j-1)==s1.charAt(i-1)) 
					
					|| (dp2[j-1] && target.charAt(i+j-1)==s2.charAt(j-1));
				
			}
		}
		
		return dp2[n];
		
	}
	
	public static void main(String[] args) {
		
		//String s1= "aabcc",s2="dbbca",target="aadbbcbcac";
		
		String s1= "abc",s2="bcd",target="abcbdc";
		
		Inter_Leave_String ils =new Inter_Leave_String(s1,s2,target);
		
		boolean res=ils.isInter_leave_by_memo();
		
		boolean res1=ils.is_inter_leave_dp2();
		
		System.out.println(res+" "+res1);

	}

}
