/**
 * 
 */
package dp_other_design;

import java.util.Arrays;

/**
 * @author qiguangqin
 *
 */
public class Arithmetic_Slice_Test {

	/**
	 * @param args
	 */
	
	private int[]nums;
	
	private int n;
	
	private int res=0;
	
	private int[]memo;
	
	public Arithmetic_Slice_Test(int[]nums) {
		
		this.nums=nums;
		
		n=nums.length;
		
		this.memo=new int[n];
		
		Arrays.fill(memo, -1);
	}
	
	public int Arithmetic_slice_direct() {
		
		int count=0;
		
		/*
		
		start [0,len-3] i[start+1 end]  end [start+2,length-1]
		
		*/
		
		for(int start=0;start<n-2;start++) {
			
			int d=nums[start+1]-nums[start];
			
			for(int end=start+2;end<nums.length;end++) {
				
				int i=0;
				
				for(i=start+1;i<=end;i++)
					
					if(nums[i]-nums[i-1]!=d) break;
				
				if(i>end) count++;
			}
		}
		
		return count;
	}
	
	
	public int Arithmetic_slice_direct_mod() {
		
		int count=0;
		
		for(int start=0;start<n-2;start++) {
			
			int d= nums[start+1]-nums[start];
			
			for(int end=start+2;end<n;end++) {
				
				if(nums[end]-nums[end-1]==d) count++;
				
				else
				
				break;
				
			}
		}
		
		return count;
	}
	
	public int Arithmetic_slice_recur(int index) {
		
		/*
		 A
		          1  3  5  7  9  10  15  20  25  28  29
		         -----------------------------------------
recur(delta)	  0  0  1  2  3  0   0   1   2   0   0
		 * 
		 */
		int cur=0;
		
		if(index<2) return 0;
		
		if(nums[index]-nums[index-1]==nums[index-1]-nums[index-2]) 
			
			cur=Arithmetic_slice_recur(index-1)+1;
			
		else 
			
		{
			//cur=0;
			
			Arithmetic_slice_recur(index-1); // must go on with 
			
		}
		
		res+=cur;
		
		return cur;
			
	}
	
	
	public int Arithmetic_slice_recur_memo(int index) {
		
		/*
		 
		 A
		 
		          1  3  5  7  9  10  15  20  25  28  29
		         -----------------------------------------
recur(delta)	  0  0  1  2  3  0   0   1   2   0   0
		 * 
		 */
		
		if(memo[index]!=-1) return memo[index];
		
		int cur=0;
		
		if(index<2) return 0;
		
		if(nums[index]-nums[index-1]==nums[index-1]-nums[index-2]) 
			
			cur=Arithmetic_slice_recur_memo(index-1)+1;
			
		else 
			
		{
			//cur=0;
			
			Arithmetic_slice_recur(index-1); // must go on with 
			
		}
		
		res+=cur;
		
		return memo[index]=cur;
		
	}

	
	public int Arithmetic_slice_dp_1() {
		
		/*
		 * 
		 A
		 
		      1  3  5  7  9  10  15  20  25  28  29
		  -----------------------------------------
dp(delta)	  0  0  1  2  3  0   0   1   2   0   0
		 * 
		 */
			
		int sum=0;
		
		int[]dp= new int[n];
			
		for(int i=2;i<n;i++) {
			
			if(nums[i]-nums[i-1]==nums[i-1]-nums[i-2]) {
				
				dp[i]=dp[i-1]+1;
				
				sum+=dp[i];
				
				System.out.println(sum);
				
			}
			
		}
		
		return sum;
	}
	
	public int Arithmetic_slice_dp_2() {
		
		int sum=0,cur=0;
		
		for(int i=2;i<n;i++) {
			
			if(nums[i]-nums[i-1]==nums[i-1]-nums[i-2]) 
			
				cur+=1;
			
			else cur=0;

			sum+=cur;	
		}
		
		return sum;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[]nums= {1,3,5,7,9,15,20,25,28,29};
		
		Arithmetic_Slice_Test ast =new Arithmetic_Slice_Test(nums);
		
		int res1= ast.Arithmetic_slice_direct();
		
		int res2= ast.Arithmetic_slice_direct_mod();
		
		int res4= ast.Arithmetic_slice_dp_2();
		
		ast.Arithmetic_slice_recur_memo(nums.length-1);
		
		System.out.println("direct_method="+res1);
		
		System.out.println("direct_method_mod="+res2);
		
		System.out.println("dp_method="+res4);
		
		System.out.println("recur_method="+ast.res);

	}

}
