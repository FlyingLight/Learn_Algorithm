package base_dp_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class money_pay_v1 {
	
	int count=0;
	
	 private List<List<Integer>> res = new ArrayList<>();
	 
	 private Set<List<Integer>> res_set= new HashSet<>();
	 
	 //boolean flag=false;
	
	public void get_pay_method(int n) {
		
		if(n==0) {
			
			count ++;
			
			return;
			
		}
		
		 if(n>=1)
			
			 get_pay_method(n-1);
		 
		 if(n>=5)
			 
			 get_pay_method(n-5);
		 
		 if(n>=10)
			 
			 get_pay_method(n-10);
		 
		 if (n>=20)
			 
			 get_pay_method(n-20);
	}
	
	
	
	
	public int min_num_pay(int n){
		
		// MIN number of cash to pay
		
		if(n<0 || n>=101) 
			
			throw new IllegalArgumentException("Invalid n");
		
		int [] nums=new int [n+1];
		
		for(int i=1;i<=n;i++) {  //  from a--->B
			
			// Use Dynamic programming so as to avoid enumerating
			
			//  dp[i]=min(dp[i-1],dp[i-5],dp[i-10],dp[i-20],dp[i-50],dp[i-100])+1
			
			int min_num=Integer.MAX_VALUE;
			
			if(i>=1)  min_num=Math.min(min_num, nums[i-1]+1);
			
			if(i>=5)  min_num=Math.min(min_num, nums[i-5]+1);
			
			if(i>=10)  min_num=Math.min(min_num, nums[i-10]+1);
			
			if(i>=20)  min_num=Math.min(min_num, nums[i-20]+1);
			
			if(i>=50)  min_num=Math.min(min_num, nums[i-50]+1);
			
			if(i>=100)  min_num=Math.min(min_num, nums[i-100]+1);
			
			nums[i]=min_num;
			
				
		//	System.out.println("nums"+"["+i+"]="+nums[i]);
		}
		
		return nums[n];
		
	}
	
	public int getMaxProfit_from_stock(int[] nums) {
		
		int maxprofit=0;
		
		int [] max_profit=new int[nums.length];
		
		if(nums.length==0) return 0;
		
		// State equation
		
	//	dp[i+1]=dp[i]+Math.max(0,price[i+1]-price[i]);
		
		else {
		
			for(int i=0;i<nums.length-1;i++) {
				
				maxprofit+=Math.max(0, nums[i+1]-nums[i]);
				
				// dp[i+1]=dp[i]+Math(0,nums[i+1]-nums[i]);
				
				max_profit[i]=maxprofit;
				
			//	System.out.println("max_profit"+"["+(i+1)+"]="+max_profit[i]);
			}
			
			return maxprofit;
		}
		
	}
	
	
	public int max_rob_v1(int []nums) {
		
		
		return _max_rob_v1(nums);
	}
	
	
	private int _max_rob_v1(int []nums) {
		
		if (nums.length==0) return 0;
		
		else {
		
		int [] max_rob=new int[nums.length];
		
		if(nums.length==1) {
		
		max_rob[0]=nums[0];
		
		return max_rob[0];
		
		}
		
		else if (nums.length==2) {
			
			max_rob[1]=Math.max(nums[0], nums[1]);
			
			return max_rob[1];
			
		}
		
		else {
			
			max_rob[0]=nums[0];
			
			max_rob[1]=Math.max(nums[0], nums[1]);
			
			
			//  dp[i]=max(dp[i-1],dp[i-2]+num[i-1])
			
			for(int i=2;i<nums.length;i++) 
				
				max_rob[i]=Math.max(max_rob[i-1], max_rob[i-2]+nums[i]);
			
			
			return max_rob[nums.length-1];
			
			
		}
			
		}
	}
	
	
	private int _max_rob_v2(int []nums,int start,int end) {
		
		
		int [] max_rob=new int[end-start+1];
		
		max_rob[0]=nums[start];
		
		max_rob[1]=Math.max(nums[start+1], nums[start]);
		
		
		for(int i=start+2;i<=end;i++) 
			
			max_rob[i-start]=Math.max(max_rob[i-start-1], max_rob[i-start-2]+nums[i]);
		
		return max_rob[end-start];
		
	}
	
	
	
	public int max_rob_v2(int [] nums) {
		
		
		if (nums.length==0) return 0;
		
		else if(nums.length==1) return nums[0];
		
		else if(nums.length==2) return nums[0]>nums[1] ? nums[0]:nums[1];
		
		else {
		
		int left_max=_max_rob_v2(nums,0,nums.length-2);  
		
		int right_max=_max_rob_v2(nums,1,nums.length-1);  
		
		return Math.max(left_max, right_max);
		
		}
	
	
	//  Binary Search Method
	
	//  Search dp[0,n-1]  dp[1,n]
	
	// Compare dp[0,n-1] dp[1,n]
				
				
	}
	

	
	
	public static void main(String[] args) {
		
		
		money_pay_v1 mp1= new money_pay_v1();
		
		int []nums= {7,1,5,3,6,4};
		
		//mp1.get_pay_method(6);
		
		//System.out.println(mp1.count);
		
		System.out.println(mp1.min_num_pay(100));

		int profit=mp1.getMaxProfit_from_stock(nums);
		
		System.out.println(profit);
		
		
		int []nums1= {1,2,3,1};
		
		//int []nums1= {2,7,9,3,1};
		
		int[] nums2= {1,2,1,1};
		
		int max_rob1=mp1.max_rob_v1(nums1);
		
		int max_rob2=mp1.max_rob_v2(nums2);
		
		System.out.println("max_rob1="+max_rob1);
		
		System.out.println("max_rob2="+max_rob2);
		
		//int []coins= {357,239,73,52};
		
		
		
	}

}
