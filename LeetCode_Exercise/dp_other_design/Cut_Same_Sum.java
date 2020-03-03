/**
 * 
 */
package dp_other_design;

/**
 * @author qiguangqin
 *
 */
public class Cut_Same_Sum {

	/**
	 * @param args
	 */
	
	private int[]nums;
	
	private boolean[][]dp;
	
	private boolean[] dp2;
	
	private int target=0;
	
	private int sum;
	
	public Cut_Same_Sum(int[] nums) {
		
		this.nums=nums;
		
		this.sum=0;
		
		for(int num:nums) {sum+=num;  this.target=sum/2;}
		
		dp = new boolean[nums.length][target+1];
		
		dp2= new boolean[target+1];
		
		dp2[0]=true;
		
		for(int i=1;i<=target;i++)
			
			if(nums[0]==i) dp[0][i]=true;
	}
	
	public boolean can_part() {
		
		if((sum & 1)==1) return false;
		
		for(int i=1;i<nums.length;i++) {
			
			for(int j=0;j<=target;j++) {
				
				dp[i][j]=dp[i-1][j];
				
				if(j>nums[i])
					
					dp[i][j]=dp[i-1][j] || dp[i-1][j-nums[i]];
				
				else if(j==nums[i])
					
					dp[i][j]=true;
			}
			
		//	if(dp[i][target]==true) return true;  // Last row of dp array will not judge 
		}
		
		return dp[nums.length-1][target];
	}
	
	public boolean can_Part_v2() {
		
		if((sum &1)==1) return false;
		
		if(nums[0]<=target) dp2[nums[0]]=true;
		
		for(int i=1;i<nums.length;i++) {
			
			for(int j=target;nums[i]<=j;j--) {
				
				if(dp2[target])  return true;
				
				dp2[j]=dp2[j]||dp2[j-nums[i]];
			}
		}
		
		return dp2[target];
	}
	
	
	public static void main(String[] args) {
		

		int[] nums= {1,5,11,5};
		
		Cut_Same_Sum css= new Cut_Same_Sum(nums);
		
		boolean flag=css.can_Part_v2();
		
		System.out.println(flag);
		
		for(int i=0;i<nums.length;i++) {
			
			for(int j=0;j<=css.target;j++) {
				
		//		System.out.print(css.dp[i][j]+" ");
				}
			
		//	System.out.println("\t");
		}
		
		for(int j=0;j<=css.target;j++)
			
			System.out.print(css.dp2[j]+ " ");
		
	}

}
