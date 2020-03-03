/**
 * 
 */
package dfs_bfs_method;

/**
 * @author qiguangqin
 *
 */
public class Split_Array_Test {

	/**
	 * @param args
	 */
	
	private int[]nums;
	
	private int n,m,res;
	
	public Split_Array_Test(int[]nums,int m) {
		
		
		this.nums=nums;
		
		this.m=m;
		
		this.n=nums.length;
		
		this.res=Integer.MAX_VALUE;
	}
	
	public int splitArray_recur() {
		
		this._split_array_direct_recur(0, 0, 0, 0);
		
		return res;
		
	}
	
	
	private void _split_array_direct_recur(int i,int cnt,int cur_sum,int cur_max) {
		
		
		if(i==n && cnt==m)  {  res=Math.min(res, cur_max);  return ;}
		
		if(i==n)  return ;
		
		if(i>0)
			
		_split_array_direct_recur(i+1,cnt,cur_sum+nums[i],Math.max(cur_max, cur_sum+nums[i]));
		
		if(cnt<m);
		
		_split_array_direct_recur(i+1,cnt+1,nums[i],Math.max(cur_max, nums[i]));
	}
	
	
	public int split_array_dp() {
		
		int n=nums.length;
		
		int[][]dp= new int[n+1][m+1];
		
		int[]cnt_sub =new int[n+1];
		
		for(int i=0;i<=n;i++)
			
			for(int j=0;j<=m;j++) dp[i][j]=Integer.MAX_VALUE;
		
		for(int i=0;i<n;i++)
			
			cnt_sub[i+1]=cnt_sub[i]+nums[i];
		
		
		dp[0][0]=0;
		
		for(int i=1;i<=n;i++) {
			
			// i marked the gap between each number
			
			// so the array has n+1 gap(n=length of Array)
			
			for(int j=1;j<=Math.min(i, m);j++) {
				
				for(int k=0;k<i;k++) {
					
					dp[i][j]=Math.min(dp[i][j],Math.max(dp[k][j-1],cnt_sub[i]-cnt_sub[k]));
				}
			}
		}
		
		return dp[n][m];
			
	}
	
	public static void main(String[] args) {
		
		int[] nums={7,2,5,10,8};
		
		int m=2;

		Split_Array_Test sat = new Split_Array_Test (nums,m);
		
		int count1 = sat.splitArray_recur();
		
		System.out.println("recur_method="+count1);
		
		
		int count2= sat.split_array_dp();
		
		System.out.println("dp_method="+count2);

	}

}
