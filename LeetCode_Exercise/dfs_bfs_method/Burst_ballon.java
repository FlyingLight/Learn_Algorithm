/**
 * 
 */
package dfs_bfs_method;

import java.util.Arrays;

/**
 * @author qiguangqin
 *
 */
public class Burst_ballon {

	/**
	 * LeetCode 312
	 */
	
	private int[]nums;
	
	private int max_value=0;
	
	public Burst_ballon(int[] nums) {
		
		this.nums=nums;
	}
	
	
	public int MaxCoins_recur() {
		
		if(nums==null || nums.length==0 ) return 0;
		
		int[]nums2= new int[nums.length+2];
		
		System.arraycopy(nums, 0, nums2, 1, nums.length);
		
		nums2[0]=1;
		
		nums2[nums.length+1]=1;
		
		return _MaxCoins_recur(nums2,0,nums2.length-1);
		
	}
	
	private int _MaxCoins_recur(int[]new_nums,int start,int end) {
		
		// end= length+1;
		// end-1=length
		
		if(start==end-1) return 0;
		
		int max=0;
		
		for(int i=start+1;i<end;i++) {
			
			int cur_coins=_MaxCoins_recur(new_nums,start,i)+
					
					_MaxCoins_recur(new_nums,i,end)+
					
					new_nums[start]*new_nums[i]*new_nums[end];
			
			max=Math.max(max, cur_coins);
		}
		
		return max;
	}
	
	public int MaxCoins_recur_memo() {
		
		if(nums==null || nums.length==0) return 0;
		
		int[]nums2= new int[nums.length+2];
		
		int[][]memo=new int[nums.length+2][nums.length+2];
		
		for(int i=0;i<=nums.length+1;i++) {
			
			Arrays.fill(memo[i], -1);
		}
		
		System.arraycopy(nums, 0, nums2, 1, nums.length);
		
		nums2[0]=1;
		
		nums2[nums.length+1]=1;
		
		return _MaxCoins_recur_memo(nums2,0,nums2.length-1,memo);
		
	}
	
	
	private int _MaxCoins_recur_memo(int[]new_nums,int start,int end,int[][]memo) {
		
		if(memo[start][end]!=-1) return memo[start][end];
		
		if(start==end-1) return 0;
		
		int max=0;
		
		for(int i=start+1;i<end;i++) {
			
			int cur_coins=_MaxCoins_recur_memo(new_nums,start,i,memo)+
					
					_MaxCoins_recur_memo(new_nums,i,end,memo)+new_nums[start]*new_nums[i]*new_nums[end];
			
			
			
			max=Math.max(max, cur_coins);			
		}
		
		return memo[start][end]=max;
		
	}
	
	
	public int get_back_track() {
		
		if(nums==null || nums.length==0) return 0;
		
		 _Max_coins_backtrack(0,0);
		 
		 return this.max_value;
	}
	
	private void _Max_coins_backtrack(int cur ,int before_max_value) {
		
		if(cur==nums.length)  //return before
		
			{
			
			if(this.max_value<before_max_value) {
				
				max_value=before_max_value;
			}
			
			return;
		}
		
		
		for(int i=0;i<nums.length;i++) {
			
			if(nums[i]==-1) continue;
			
			int cur_ballon_num =nums[i];
			
			nums[i]=-1;  // get cur_ballon value
			
			// get_before_ballon
			
			int before_ballon_index=i-1,before_ballon_num=0;
			
			while(before_ballon_index>=0 && nums[before_ballon_index]==-1) before_ballon_index--;
			
			if(before_ballon_index<0) before_ballon_num=1; // get the first number 1
			
			else before_ballon_num=nums[before_ballon_index];
			
			// get_next_ballon
			
			int next_ballon_index=i+1, next_ballon_num=0;
			
			while(next_ballon_index<nums.length && nums[next_ballon_index]==-1) next_ballon_index++;
			
			if(next_ballon_index>=nums.length) next_ballon_num=1;
			
			else next_ballon_num=nums[next_ballon_index];
			
			int cur_product= cur_ballon_num*before_ballon_num*next_ballon_num;
			
			_Max_coins_backtrack(cur+1,cur_product+ before_max_value);
			
			nums[i]=cur_ballon_num;
			
		}
		
	}
	
	public int Max_Coins_dp() {
		
		if(nums==null || nums.length==0) return 0;
		
		int []new_num =new int[nums.length+2];
		
		System.arraycopy(nums, 0, new_num, 1,nums.length);
		
		new_num[0]=1;new_num[nums.length+1]=1;
		
		int length=new_num.length;
		
		int[][] dp= new int[length][length];
		
		/*
		 * 
		 dp(i,j)=dp[i-1,k-1](dp[i][k])+dp[k+1,j-1](dp[k][j])+nums[i]*nums[k]*nums[j]
		 
		 max_i(index)=length-3(l'-1)  max_k(index)=length-2(l') max_j(index)=length-1(l'+1)
		
		*/
		
		/*
		 
		 choose the length-3, start  to burst   
		 
		 */
		
		for(int i=length-3;i>=0;i--) { // from end to dp,so not to influence 
			
			for(int j=i+2;j<length;j++) {
				
				int cur_max=0;
				
				for(int k=i+1;k<j;k++) {
					
					cur_max=Math.max(cur_max, dp[i][k]+dp[k][j]+new_num[i]*new_num[k]*new_num[j]);
				
					dp[i][j]=cur_max;
				}
				
				
			}
		}
		
		return dp[0][length-1];
	}
	
	
	public static void main(String[] args) {
		
		int[]nums= {3,1,5,8,7,6,2};
		
		Burst_ballon bb =new Burst_ballon(nums);
		
		int max1= bb.MaxCoins_recur();
		
		System.out.println("recur="+max1);
		
		int max2=bb.MaxCoins_recur_memo();
		
		System.out.println("recur_memo="+max2);
		
		int max3=bb.get_back_track();
		
		System.out.println("back_track="+max3);
		
		int max4= bb.Max_Coins_dp();
		
		System.out.println("dp="+max4);
	}

}
