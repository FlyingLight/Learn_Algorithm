/**
 * 
 */
package base_dp_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author qiguangqin
 *
 */
public class Largest_Divisible_Subset {

	/**
	 * @param args
	 */
	
	// Leet_code 368
	
	private int[]nums;
	
	private List<Integer> res;
	
	private int[] dp; // dp[i]  last element with nums[i] contains the most elements
	
	private int[] pre;  // To record the last nums[i] 
	
	private int end=0;
	
	public Largest_Divisible_Subset(int[]nums) {
		
		this.nums=nums;
		
		this.dp= new int[nums.length];
		
		this.pre= new int[nums.length];
		
		this.res=new ArrayList<Integer>();
		
		pre[0]=-1;
	}
	
	public void get_dp() {
		
		Arrays.sort(nums);
		
		for(int i=1;i<nums.length;i++) {
			
			for(int j=0;j<i;j++) {
				
				// nums[i] //divide the j(before i) is OK ,Then plus the j at the dp[i]
				
				//if(nums[i]%nums[j]==0 &&dp[j]<=dp[i]) {
					
				if(nums[i]%nums[j]==0) {
					
					//dp[i]=dp[j]+1;
					
					dp[i]=Math.max(dp[i],dp[j]+1);
					
					pre[i]=j;
				}
			}
		}
		
	}

	public int get_Res() {
		
		int max=0,max_i=0;
		
		for(int i=0;i<nums.length;i++)
			
			if(max<dp[i]) {max=dp[i];max_i=i;}
		
	
		while(max_i!=-1) {
			
			res.add(nums[max_i]);
			
			max_i=pre[max_i];
			
			
		}
		
		Collections.reverse(res);
		
		return max+1;
	}
	
	public int get_dp_1() {
		
		Arrays.sort(nums);
		
		int ans=0,max_i=0;
		
		for(int i=1;i<nums.length;i++) {
			
			for(int j=0;j<i;j++) {
				
				// nums[i] //divide the j(before i) is OK ,Then plus the j at the dp[i]
				
				//if(nums[i]%nums[j]==0 &&dp[j]<=dp[i]) {
					
				if(nums[i]%nums[j]==0) {
					
					//dp[i]=dp[j]+1;
					
					dp[i]=Math.max(dp[i],dp[j]+1);
					
					pre[i]=j;
				}
			}
			
			ans=Math.max(ans,dp[i]);
			
			end=i;
		}
		
		max_i=end;
		
		System.out.println(max_i+" "+nums[max_i]);
			
		while(max_i!=-1) {
			
			res.add(nums[max_i]);
			
			max_i=pre[max_i];
			
			
		}
		
		Collections.reverse(res);
		
		return ans+1;
	}
	
	public static void main(String[] args) {
		
		
		int[] nums= {1,2,3,6,18,36,72};
		
		 Largest_Divisible_Subset lds =new  Largest_Divisible_Subset (nums);
		
		//lds.get_dp();
		
		//int res=lds.get_Res();
		
		int dd= lds.get_dp_1();
		
		//System.out.print(res+" "+lds.res);
		
		System.out.println(dd+" "+lds.res);
	}

}
