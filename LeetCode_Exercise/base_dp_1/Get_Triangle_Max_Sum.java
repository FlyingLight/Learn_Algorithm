/**
 * 
 */
package base_dp_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qiguangqin
 *
 */
public class Get_Triangle_Max_Sum {

	/**
	 * @param args
	 * 
	 LeetCode 120 dp
	 */
	
	private List<List<Integer>> triangle;
	
	private int row;
	
	//private int column;
	
	private int[][]dp2;
	
	private int[]dp3;
	
	private int[][]dp1;
	
	private int[]dp4;
		
	public Get_Triangle_Max_Sum(List<List<Integer>> triangle) {
		
		this.triangle=triangle;
		
		this.row=triangle.size();  // the row of Triangle
		
		//this.column=this.row;
		
	   dp1= new int[row][row]; // top-down
		
		/*
		 2          o
		 3 4        +|
		 6 5 7      +|
		 4 1 8 3    +|
		 
		 
		dp1[i][j]=max(dp1[i-1][j],dp1[i-1][j-1])+triangle[i][j]
		 */
		
		dp1[0][0]=triangle.get(0).get(0);
		
		
		dp4= new int[row];
		
		/*
		 2          o
		 3 4        +|
		 6 5 7      +|
		 4 1 8 3    +|
		 */
		
	  dp4[0]=triangle.get(0).get(0);
		
		
		// dp2 = new int[row][column]
		
		dp2= new int[row+1][row+1];  // Add one row, use dp from bottom to top
		
		/*
		 
		 2         O
		 3 4       ^
		 6 5 7     ^  
		 4 1 8 3   ^
		 
		 need to initiate dp last line 
		 
		 	//for(int j=row-1;j>=0;j--)
			
		//	dp2[row-1][j]=triangle.get(row-1).get(j);
		          
		 2         O
		 3 4       ^
		 6 5 7     ^  
		 4 1 8 3   ^
		-0 0 0 0 0 ^
		
		dp2[i][j]=max(dp2[i+1][j+1],dp2[i+1][j])+triangle[i][j]
		
		  no need to initiate dp last line 
		 */
		
		dp3= new int[row+1];
		
		/*
		 2         O
		 3 4       ^
		 6 5 7     ^  
		 4 1 8 3   ^
		-0 0 0 0 0 ^
		 
		dp[j]=max(dp[j+1],dp[j])+triangle[i][j]
		 */
		

		
	}
	
	public int get_res_recursive(int row,int index) { // Top_down recursion
		
		/*
		 2          o
		 3 4        +|
		 6 5 7      +|
		 4 1 8 3    +|
		*/
		
		if(row==this.row-1) return triangle.get(row).get(index);
		
		int min=0;
		
		int left=get_res_recursive(row+1,index);
		
		int right=get_res_recursive(row+1,index+1);
		
		min=Math.min(left, right)+triangle.get(row).get(index);
		
		return min;
	}
	
	
	public int get_res_recursive2() {
		
		int[]res= new int[row];
				
		for(int index=0;index<row;index++) {
			
			res[index]=_get_res_recursive2(row-1,index);
		}
		
		Arrays.sort(res);
		
		return res[0];
	}
	
	private int _get_res_recursive2(int row,int index) { // Bottom_up recursion
		
		/*
		 2          o
		 3 4        ^
		 6 5 7      ^
		 4 1 8 3    ^
		*/
		
		int cur=triangle.get(row).get(index);
	
		if(row==0 && index==0)
			
			return triangle.get(row).get(index);
		
		else if(index==0 &&row!=0)
			
			return _get_res_recursive2(row-1, index)+cur;
		
		else if(index==triangle.get(row).size()-1 && row!=0) {
			
			return _get_res_recursive2(row-1,index-1)+cur;
		}
		
		else {
			
			int left=_get_res_recursive2(row-1, index);
			
			int right=_get_res_recursive2(row-1,index-1);
			
			return Math.min(left, right)+cur;
		}
	}
	
	
	public int get_res_dp1() { //top-donw dp ,2 dimension
		
		for(int i=1;i<row;i++) {
			
			for(int j=0;j<triangle.get(i).size();j++) {
				
				if(j==0)  dp1[i][j]=dp1[i-1][j]+triangle.get(i).get(j);
				
				else if(j==triangle.get(i).size()-1) {
				
				dp1[i][j]=dp1[i-1][j-1]+triangle.get(i).get(j);
				
				}
				
				else {
				
				dp1[i][j]=Math.min(dp1[i-1][j-1], dp1[i-1][j])+triangle.get(i).get(j);
				
				}
			}
		}
		
		Arrays.sort(dp1[row-1]);
		
		return dp1[row-1][0];
		
	}
	
	
	public int get_res_dp4() { // top-down 1 dimension
		
		for(int i=1;i<row;i++) {
			
			for(int j=triangle.get(i).size()-1;j>=0;j--) {
				
				if(j==triangle.get(i).size()-1 ) dp4[j]=dp4[j-1]+triangle.get(i).get(j);
				
				else if(j==0) dp4[j]+=triangle.get(i).get(j);
				
				else {
					
					dp4[j]=Math.min(dp4[j],dp4[j-1])+triangle.get(i).get(j);
				}
				
				//System.out.print("["+dp4[j]+" j= "+j+"]"+" ");
				
			}
			
			//System.out.println("");
			
		}
		
		Arrays.sort(dp4);
		
		return dp4[0];
		
	}
	
	
	public void show() {
		
		for(int j=0;j<row;j++)
			
			System.out.print("["+dp4[j]+" j= "+j+"]"+" ");
	}
	
	
	public int get_res_dp2() {  // Bottom-up dp ,2 dimension
		
		
		for(int i=row-1;i>=0;i--) {
			
		//for(int =row-2;i>=0;i--){
			
			for(int j=triangle.get(i).size()-1;j>=0;j--) {
				
				// if not judge j=triangle.size() dp[*][size(i)]=0 
				
				dp2[i][j]=Math.min(dp2[i+1][j], dp2[i+1][j+1])+triangle.get(i).get(j);
			}
		}
		
		return dp2[0][0];
		
	}
	
	public int get_res_dp3() {  // Bottom-up dp ,1 dimension
		
		for(int i=row-1;i>=0;i--) {
			
			for(int j=0;j<=i;j++) {
				
				dp3[j]=Math.min(dp3[j], dp3[j+1])+triangle.get(i).get(j);
			}
		}
		
		return dp3[0];
		
	}
	
	public static void main(String[] args) {
		
		List<List<Integer>> res= new ArrayList<>();
		
		List<Integer> list1= new ArrayList<>();
		
		list1.add(2);
		
		List<Integer> list2= new ArrayList<>();
		
		list2.add(3);list2.add(4);
		
		List<Integer> list3= new ArrayList<>();
		
		list3.add(6);list3.add(5);list3.add(7);
		
		List<Integer> list4= new ArrayList<>();
		
		list4.add(4);list4.add(1);list4.add(8);list4.add(3);
		
		res.add(list1);res.add(list2);res.add(list3);res.add(list4);
		
		System.out.println(res);
		
		Get_Triangle_Max_Sum gtms= new Get_Triangle_Max_Sum(res);
		 
		int ans1=gtms.get_res_dp1();
		
		//int ans=gtms.get_res_dp2();
		
		//int ans= gtms.get_res_dp3();
		
		int ans4=gtms.get_res_dp4();
		
		//int ans2= gtms.get_res_recursive(0, 0);
		
		int ans3= gtms.get_res_recursive2();
		
		System.out.println("Min_Triangle_Sum by dp="+ans4);
		
		System.out.println("Min_Triangle_Sum by recursion="+ans3);
		
		//gtms.show();
		
		
		
	}

}
