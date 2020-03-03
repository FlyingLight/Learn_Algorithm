/**
 * 
 */
package base_dp_1;

import java.util.Arrays;



/**
 * @author qiguangqin
 *
 */
public class LIS_test {

	/**
	 * @param args
	 */
	
	private int[]seq;
	
	private int[]memo;
	
	public LIS_test(int []seq) {
		
		this.seq=seq;
		
		this.memo=new int[seq.length];
		
		Arrays.fill(memo, -1);
		
	}
	
	public int Lis_test_recur() {
		
		int max=0;
		
		for(int i=seq.length-1;i>=0;i--) 
			
			max=Math.max(max, _get_lis_recur(i));
			
		return max;
	}
	
	private int _get_lis_recur(int p) { // LIS end with index p
		
		
		if(p==0) return 1;
		
		int max=0;
		
		for(int j=p;j>=0;j--) {
			
			if(seq[p]>seq[j]) {
				
				max=Math.max(max,_get_lis_recur(j)+1);
				
			}
		}
		
		return max;
	}
	
	public int Lis_test_recur_memo() {
		
		int max=0;
		
		for(int i=seq.length-1;i>=0;i--) 
			
			max=Math.max(max, _get_lis_recur_memo(i));
			
		return max;
	}
	
	private int _get_lis_recur_memo(int p) { // LIS end with index p
		
		if(memo[p]!=-1)  return memo[p];
		
		if(p==0) { memo[p]=1;return 1;}
		
		int max=0;
		
		for(int j=p;j>=0;j--) {
			
			if(seq[p]>seq[j]) {
				
				max=Math.max(max,_get_lis_recur(j)+1);
				
			}
		}
		
		memo[p]=max;
		
		return max;
	}
	
	
	public int Lis_dp_test() {
		
		
		int n=seq.length,max=0;
		
		if(n==0)  return 0;
		
		else if(n==1)  return 1;
					
		else {
			
			int[] dp= new int[n];
			
			 Arrays.fill(dp, 1);
			 
			 System.out.println(String.format("[%d]-->%d : %d",0,seq[0],dp[0]));
		
			for(int i=1;i<n;i++) {
			
				for(int j=0;j<i;j++) {
				
					if(seq[j]<seq[i])  dp[i]=Math.max(dp[i], dp[j]+1);
				}
				
				System.out.println(String.format("[%d]-->%d : %d",i,seq[i],dp[i]));
			
				max=Math.max(max, dp[i]);
			}
		
			return max;
		}
		
	}
	
	public int FindNumberOfLIS() {
		
		int n=seq.length;
		
		if(n==0 || n==1) return 0;
		
		int[]lengths= new int[n];
		
		int[]counts=new int[n];
		
		Arrays.fill(counts, 1);
		
		int max=0;
		
		for(int i=1;i<n;i++) {
			
			for(int j=0;j<i;j++) {
				
				if(seq[j]<seq[i]) {
					
					if(lengths[i]<=lengths[j]) {
					
					lengths[i]=lengths[j]+1;
					
					counts[i]=counts[j];}
					
					else if(lengths[j]+1==lengths[i])
						
						counts[i]+=counts[j];
					
					
				}
		}
			
		max=Math.max(max, lengths[i]);
		}
		
		int res=0;
		
		for(int i=seq.length-1;i>=0;i--) {
			
			if(lengths[i]==max)
				
				res+=counts[i];
		}
	
		return res;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//int[] seq = {1,5,3,9,6,4,7,2};
		
		//int[]seq={1,7,3,5,9,4,8};
		
		int[]seq= {1,3,5,4,7};
		
		LIS_test lt = new LIS_test (seq);
		
		int b=lt.Lis_dp_test();
		
		int c=lt.Lis_test_recur_memo();
		
		System.out.println("result_dp="+b);
		
		System.out.println("--------split--------");
		
		int i=0;
		
		for(int w:lt.memo) {
			
			 System.out.println(String.format("[%d]-->%d : %d",i,seq[i],w));
			 
			 i++;
			 
		}
		
		System.out.println("result_recursion="+c);
		
		System.out.println(lt.FindNumberOfLIS());

	}

}
