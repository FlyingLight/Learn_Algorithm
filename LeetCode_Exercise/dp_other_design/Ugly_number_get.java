/**
 * 
 */
package dp_other_design;

import java.util.PriorityQueue;

/**
 * @author qiguangqin
 *
 */
public class Ugly_number_get {

	/**
	 * @param args
	 */
	
	public int nthUglyNumber_v1(int n) {
		
		if(n<=0) throw new IllegalArgumentException("n must above zero");
		
		int[] dp= new int[n];
		
		dp[0]=1;
		
		int l2=0,l3=0,l5=0;
		
		for(int i=1;i<n;i++) {
			
			// dp[i]=min{ 5*dp[l5],3*dp[l3],2*dp[l2]}
			
			dp[i]=Math.min(5*dp[l5], Math.min(2*dp[l2], 3*dp[l3]));
			
			if(dp[i]>=2*dp[l2]) l2+=1;
			
			if(dp[i]>=3*dp[l3]) l3+=1;
			
			if(dp[i]>=5*dp[l5]) l5+=1;
		}
		
		
		return dp[n-1];
	}
	
	public int nthUglyNumber_v2(int n) {
		
		int[] elements= {2,3,5};
		
		long[] res =new long [n];
		
		res[0]=1;
		
		PriorityQueue<Long> pro_queue= new PriorityQueue<>();
		
		// use the min_heap to get the ugly_number 
		
		// res[0]=1  {2,3,5}  (2)3,5 --->poll 2  3,5
		
		// res[1]=2  {2,3,5} (3)4,5,6,10--->poll 3   4,5,6,10
		
		// res[2]=3  {2,3,5} (4)5,6,9,10,15--->poll 4   5,6,9,10,15
		
		// res[3]=5  {2,3,5} (5)6,9,10,15,25--->poll 5   6,9,10,15,25
		
		for(int i=0;i<n;i++) {
			
			for(int j=0;j<3;j++) {
				
				if(!pro_queue.contains((long)(res[i]*elements[j]))) {
					
					pro_queue.add((long)(res[i]*elements[j]));
					
				}
				
			}
			
			if(i+1<n)
				
				res[i+1]=pro_queue.poll();
		}
		
		return (int)res[n-1];
	}
	
	public static void main(String[] args) {
		
		Ugly_number_get ung = new Ugly_number_get();
		
		int n=10;
		
		System.out.println(ung.nthUglyNumber_v1(n));
	}

}
