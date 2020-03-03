/**
 * 
 */
package dp_other_design;

import java.util.Arrays;

/**
 * @author qiguangqin
 *
 */
public class Lis_dp_recursive {

	/**
	 * @param args
	 */
	
	public int Lis_test_recursive_v1(int[] seq) {
		
		return _Lis_test_recursive_v1(seq, Integer.MIN_VALUE, 0);
	}
	
	private int _Lis_test_recursive_v1(int [] seq,int prev,int cur_pos) {
		
		
		// each position of the seq
		
		//use above_pre and minus_pre to recurse
		
		// like traversing the binary_tree
		
		if(cur_pos==seq.length) return 0;
		
		int above_pre=0;
		
		if(seq[cur_pos]>prev) above_pre=1+_Lis_test_recursive_v1(seq, seq[cur_pos], cur_pos+1);
		
		int minus_pre=_Lis_test_recursive_v1(seq, prev, cur_pos+1);
		
		return Math.max(above_pre, minus_pre);
		
	}
		

	public int Lis_dp_test(int[] seq) {
		
		
		int n=seq.length,max=0;
		
		if(n==0)  return 0;
		
		else if(n==1)  return 1;
					
		else {
			
			int[] dp= new int[n];
			
			 Arrays.fill(dp, 1);
			 
			 System.out.println(String.format("[%d] : %d",seq[0],dp[0]));
		
			for(int i=1;i<n;i++) {
			
				for(int j=0;j<i;j++) {
				
					if(seq[j]<seq[i])  dp[i]=Math.max(dp[i], dp[j]+1);
				}
				
				System.out.println(String.format("[%d] : %d",seq[i],dp[i]));
			
				max=Math.max(max, dp[i]);
			}
		
			return max;
		}
		
	}
	

	
	public static void main(String[] args) {
		
		int[] seq = {1,5,3,4,6,9,7,8};
	
		Lis_dp_recursive ldr = new Lis_dp_recursive ();
		
		int b=ldr.Lis_dp_test(seq);
		
		//int c=ldr.Lis_test_recursive_v1(seq);
		
		//System.out.println(b+" "+c);
		
		System.out.println(b);
		
		//int max_sum=ldr.get_lis(0,0);
		
	//	System.out.println(max_sum);
		
		

	}

}
