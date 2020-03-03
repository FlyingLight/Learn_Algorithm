/**
 * 
 */
package base_dp_1;

import java.util.Arrays;

/**
 * @author qiguangqin
 *
 */
public class Bags_Problem_Test {

	/**
	 * @param args
	 */
	
	private int MAX_WEIGHT=8;
	
	private int[] weight; // The Weight of Each Item 
	
	private int[] value; // The Value of Each Item
	
	private final int W=MAX_WEIGHT; // The Sum Weight of whole bag
		
	public Bags_Problem_Test(int[] weight,int[]value) {
			
		this.weight=weight;
		
		this.value=value;
		
		
	}
	
	public int get_Res_compression() {
		
		//
		
		return _dp_bag_value_compression();
	}
	
	public int get_Res() {
		
		return  _dp_bag_value();
	}
	
	private int _dp_bag_value() {
		
		// Item index  (0),1,2,3,4....,N
		
		// Weight Item  (0),1,2,3,4,5...W
	
		// Dp [index+1][weight+1] initiate a cycle "0"  value ==0 matter_index=0(which start from 1, to avoid initiate the first row)
		
		//Arrays.fill(dp[0], 0);
		
//		if(index_row==0 || weight_column==0) return 0;
		
		int index_row= weight.length;// (1,2,3,4)--->(2,3,4,5)
		
		int weight_column= W; // include zero kg
		
		if(index_row==0|| weight_column==0) return 0;
		
		int[][] dp= new int[index_row+1][weight_column+1];
		
		for(int i=1;i<=weight.length;i++) {
			
			// time =O(MN)  space O(MN)
			
		//	int index_i= i-1;
			
			for(int j=1;j<=W;j++) {
				
				if(j<weight[i-1]) dp[i][j]=dp[i-1][j];
				
				else {
					
					dp[i][j]=Math.max(dp[i-1][j], dp[i-1][j-weight[i-1]]+value[i-1]);
				}
				
				
				}		
			
			}
			
		return dp[weight.length][W];
		
	}
	
	

	
	/*
	 
	     0  1  2  3  4  5  6  7  8   
	  
	 0   0  0  0  0  0  0  0  0  0
	 
	 1   0  0  3  3  3  3  3  3  3
	 
	 2   0  0  3  4  4  7  7  7  7
	 
	 3   0  0  3  4  5  7  8  9  9
	  
	 4   0  0  3  4  5  7  8  9  10
	 
	 
	 */
	
	private int _dp_bag_value_compression() {
		
		int weight_column=W;
	
		if(weight_column==0) return 0;
		
		int []dp= new int [weight_column+1];
		
		// time =O(MN)  space O(N)
		
		for(int i=1;i<=weight.length;i++) {
			
			for(int j=W; j>=weight[i-1];j--) {
				
				dp[j]=Math.max(dp[j], dp[j-weight[i-1]]+value[i-1]); 
				
				//dp[i][j]=Math.max(dp[i-1][j], dp[i-1][j-weight[i-1]]+value[i-1]);
				
				/*
				 if(j-weight[i]>=0)  dp[i][j]=Math.max(dp[i-1][j], dp[i-1][j-weight[i-1]]+value[i-1]);
				 
				 ---> dp[j]=Math.max(dp[j],dp[j-weight[i-1]]+value[i])
				 
				 else 
				 
				 	dp[j]=dp[j] // NO need to update   
				 	
				 	so,   j-weight[i]
				 */
			}
		}
			
		return dp[W];	
		
	}
	
	public int get_Res_recur() {
		
		return  _get_Res_recur(weight.length-1,W);
		 
	}
	
	
	
	private int _get_Res_recur(int i,int weight_left) {
		
		//if(weight[inv]<weight_left) return 0;
		
		int res1=0,res2=0;
		
		if(i==-1) return 0;
			
		//for(int i=inv;i>=0;i--) {
		
		//no need to for loop only deal two condition
			
		if(weight_left>=weight[i]) {
					
			res1=value[i]+ _get_Res_recur(i-1,weight_left-weight[i]);
				
			res2=_get_Res_recur(i-1,weight_left);
				
			return Math.max(res1, res2);
			
		}
			return _get_Res_recur(i-1,weight_left);
	}
	
	public int get_Res_recur_1() {
		
		return _get_Res_recur_1(0,W);
	}
	
	private int _get_Res_recur_1(int index,int weight_left) {
		
		// Back_track method
		
		int res=0,cur=0;
		
		for(int i=index;i<weight.length;i++) {
			
			if(weight_left>=weight[i])

				cur=value[i]+_get_Res_recur_1(index+1,weight_left-weight[i]);
			
				res=Math.max(res,cur );
			
		}
		
		return res;
				
				
	}
	
	public int get_Res_recur_memo() {
		
		int[][] memo= new int[W+1][W+1];
		
		for(int i=0;i<=W;i++)
			
			Arrays.fill(memo[i], -1);
		
		return _get_Res_recur_memo(weight.length-1,W,memo);
		
	}
	
	public int _get_Res_recur_memo(int i,int weight_left,int[][]memo) {
		
		
		if(i!=-1 && memo[i][weight_left]!=-1)
			
			return memo[i][weight_left];
		
		if(i==-1) return 0;
		
		int res1=0,res2=0;
		
		if(weight_left>=weight[i]) {
			
			res1=value[i]+ _get_Res_recur_memo(i-1,weight_left-weight[i],memo);
			
			res2=_get_Res_recur_memo(i-1,weight_left,memo);
			
			memo[i][weight_left]= Math.max(res1, res2);
			
			return Math.max(res1, res2);
			
		}
		
		memo[i][weight_left]=_get_Res_recur_memo(i-1,weight_left,memo);
				
		return _get_Res_recur_memo(i-1,weight_left,memo);
		
	}
	
	public static void main(String[] args) {
		
		 int[] weight={2,3,4,5};
		 
		 int[] value={3,4,5,6};
		 
		 Bags_Problem_Test bpt= new Bags_Problem_Test(weight,value);
		 
		 int max0=bpt.get_Res_recur_1();System.out.println(max0);
		 
		 int max=bpt.get_Res();
		 
		 int max2=bpt.get_Res_compression();
		 
		 int max3= bpt.get_Res_recur_memo();
		 
		 int max4=bpt.get_Res_recur();
		 	 
		 System.out.println("The Max Value_2nd_dp="+max+" "+"\nThe Max Value_1st_dp="+ max2+
				 
				 " "+"\nThe Max value_recur_memo="+max3+" "+"\nThe Max value_recur="+max4);
		 
	
		 /*
		 
		for(int i=0;i<5;i++)
			
			for(int j=0;j<=8;j++)
				
				System.out.print(bpt.dp[i][j]+" ");
				
				*/	
	}

}
