/**
 * 
 */
package base_dp_1;

import java.util.Arrays;

/**
 * @author qiguangqin
 *
 */
public class Cow_cross_river_dp {

	/**
	 
	 Farmer John  has N cow
	 
	 boat without row(to and back from river other side :M)
	 
	 m_i(from m_i-1  m_i) add time
	 
	 one cow:   M+m_1   two cow together : M+m_1+m_2
	 
	5(5 cows) 10(without cow ,single trip)
	3  m_1
	4  m_2
	6  m_3
	100 m_4
	1   m_5
	 */
	
	private int[] cow_time;
	
	private int[]dp;
	
	private int[] weight;
	
	private final int n=5;
	
	private final int m=10;
	
	public Cow_cross_river_dp(int[] cow_time) {
		
		if (cow_time.length>n) throw new IllegalArgumentException(" number of cow is not equal");
		
		this.cow_time=cow_time;
		
		dp= new int[cow_time.length+1];
		
		weight= new int[cow_time.length+1];
		
		dp[0]=m;
		
		weight[0]=m;
		
		for(int i=1;i<=n;i++)
			
			dp[i]=dp[i-1]+cow_time[i-1];
		
		for(int i=1;i<=n;i++)
			
			weight[i]=weight[i-1]+cow_time[i-1];
	
	}
	
	public int get_res_recur() {
		
		return _get_res_recur(cow_time.length);
	}
	
	
	public int get_res_recur_memo() {
		
		int n=cow_time.length;
		
		int []memo=new int[n+1];
		
		Arrays.fill(memo, -1);
		
		return _get_res_recur_memo(n,memo);
		
	}
	
	private int _get_res_recur(int n) {
		
		if(n==0) return -m;
		
		if(n==1) return weight[n];
		
		int res=Integer.MAX_VALUE;
		
		for(int i=n;i>0;i--) 
			
			res=Math.min(res, weight[i]+_get_res_recur(n-i)+m);
			
			//System.out.println(res);
				
		return res;
	}
	
	private int _get_res_recur_memo(int n,int[]memo) {
		
		if(memo[n]!=-1) return memo[n];
		
		if(n==0) return -m;
		
		if(n==1) return weight[n];
		
		int res=Integer.MAX_VALUE;
		
		for(int i=n;i>0;i--) {
			
			res=Math.min(res, weight[i]+_get_res_recur(n-i)+m);
			
			//System.out.println(res);
		}
		
		memo[n]=res;
				
		return res;
	}
	
	
	public int get_res() {
		
		for(int i=1;i<=n;i++) {
			
			for(int j=1;j<i;j++) {
				
				dp[i]=Math.min(dp[i], dp[j]+dp[i-j]+m);
				
			}
		}
		
		return dp[n];
	}
	
	public static void main(String[] args) {
		
		int[] cow_time= {3,4,6,100,1};
		
		Cow_cross_river_dp ccrd = new Cow_cross_river_dp(cow_time);
		
		//for(int w:ccrd.cow_time)
			
			//System.out.print(w+" ");
		
		int num= ccrd.get_res();
		
		int num2= ccrd.get_res_recur_memo();
		
		System.out.println(num+" "+num2);

	}

}
