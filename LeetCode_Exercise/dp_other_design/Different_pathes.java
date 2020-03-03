package dp_other_design;

import java.util.Arrays;

public class Different_pathes {
	
	// O(2^(m+n)) time 
	
	// O(m+n) space
	
	private int[][]memo;
	
	private int m,n;
	
	private int sum=0;
	
	public Different_pathes(int m,int n) {
		
		this.m=m;
		
		this.n=n;
		
		memo=new int [m+1][n+1];
		
		for(int i=0;i<=m;i++)
		
			Arrays.fill(memo[i], -1);
		
	}
	
	
	public int unique_pathes(int m,int n,int sum) {
		
		if(m<=0 || n<=0) return 0;
		
		else if(m==1|| n==1) return 1;
		
		else if(m==2 && n==2) return 2;
		
		else if ((m==3 && n==2) ||(m==2 && n==3))  return 3;
		
		int right=unique_pathes(m-1,n,sum);
		
		int down=unique_pathes(m,n-1,sum);
		
		sum=down+right;
		
		return sum;
	}
	
	public int unique_pathes_memo(int m,int n) {
		
		if(memo[m][n]!=-1) return memo[m][n];
		
		else {
			
			if(m<=0 || n<=0) { memo[m][n]=0;return 0;}
			
			else if(m==1|| n==1) { memo[m][n]=1;return 1;}
			
			else if(m==2 && n==2) { memo[m][n]=2;return 2;}
			
			else if ((m==3 && n==2) ||(m==2 && n==3)) { memo[m][n]=3;return 3;}
			
			int right=unique_pathes_memo(m-1,n);
			
			int down=unique_pathes_memo(m,n-1);
			
			sum=down+right;
			
			memo[m][n]=sum;
			
			return sum;
			
		}
		
		
	}
	
	public int dp_unique_pathes(int m,int n) {
		
		int[][] dp=new int [m][n];
		
		if(m==0 || n==0) return 0;
		
		else if(m==1 && n==1 ) return 1;
			
		else {
			
			for(int i=0;i<m;i++) dp[i][0]=1;
			
			for(int j=0;j<n;j++) dp[0][j]=1;
			
			
			//  State equation 
			
			// dp[i][j]=dp[i-1][j]+dp[i][j-1];
			
			for(int i=1;i<m;i++)
				
				for(int j=1;j<n;j++)
					
					dp[i][j]=dp[i-1][j]+dp[i][j-1];
			
			return dp[m-1][n-1];
		}
	}
	
	
//	public int dp_unique_pathes_obstacles(int [][]grid_obstacles) {
		
//	}
	
	
	public static void main(String[] args) {
		
		int m=7,n=3;
		
		Different_pathes Dps= new Different_pathes(m,n);
		
		int sum1= Dps.unique_pathes(m,n,0);
		
		int sum2=Dps.unique_pathes_memo(m, n);
		
		int sum3=Dps.dp_unique_pathes(m, n);
		
		System.out.println("recursive method");
		
		System.out.println("Unique Path["+m+"]"+"["+n+"]="+sum1);
		
		System.out.println("recursive method_memo1");
		
		System.out.println("Unique Path["+m+"]"+"["+n+"]="+sum2);
		
		System.out.println("dp method");
		
		System.out.println("Unique Path["+m+"]"+"["+n+"]="+sum3);
	}

}
