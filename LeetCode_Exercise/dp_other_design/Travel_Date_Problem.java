/**
 * 
 */
package dp_other_design;

/**
 * @author qiguangqin
 *
 */
public class Travel_Date_Problem {

	/**
	 * @param args
	 */
	
	private int[] travel_days;
	
	/*
	 1,2,3,4,...20 calendar date
	 
	 travel_days[1,4,6,7,8,20]  marked the traveling date
	 * 
	 */
	
	private int[]costs; // 
	
	private int[]dp;
	
	/*
	 dp[i]
	 
	 After i day,
	 
	 each days(regardless of traveling or not) ; min_sum money 
	 
	 */
	
	public Travel_Date_Problem(int[] travel_days,int[] costs) {
		
		this.travel_days=travel_days;
		
		this.costs=costs;
		
		this.dp=new int[travel_days[travel_days.length-1]+1];  // new int[21];last day =20  
		
		for(int num:travel_days) {
			
			dp[num]=Integer.MAX_VALUE;
		}
	}
	
	
	public int get_Res_dp() {
		
		if(travel_days==null || travel_days.length<1 || costs==null || costs.length<1)
			
			return 0;
		
		for(int i=1;i<dp.length;i++) {
			 
			if(dp[i]==0) { dp[i]=dp[i-1];  continue;}// if NOT Traveling , min_cost is pre_day
			
			int num1=dp[i-1]+costs[0];
			
			int num2=i>7? dp[i-7]+costs[1]:costs[1];
			
			int num3=i>30? dp[i-30]+costs[2]:costs[2];
			
			dp[i]=Math.min(num1, Math.min(num2, num3));
			
		}
		
		return dp[dp.length-1];
	}
	
	public static void main(String[] args) {
		
		int[] travel_days= {1,4,6,7,8,20};
		
		int[] costs= {2,7,15};
		
		Travel_Date_Problem tdp= new Travel_Date_Problem(travel_days,costs);
		
		int count=tdp.get_Res_dp();
		
		System.out.println(count);
		

	}

}
