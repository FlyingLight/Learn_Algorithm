package dp_other_design;

public class Different_Path_Sum {  // find the minimum path sum
	
	// Top_down recursive
	
	// time complexity O(2^(m+n))  grid[m][n]
	
	// space complexity O(m+n)
	
	public int calculate_td(int[][] grid,int i,int j) {
		
		//if((i<0 || i>=grid.length) &&(j<0|| j>=grid[0].length)) throw new IllegalArgumentException("invald grid index");
				
		if(i==0 && j==0) 
			
			return grid[i][j];
		
		else if(i==0 &&j!=0) 
			
			return grid[i][j]+calculate_td(grid,i,j-1);
		
		
		else if(j==0 &&i!=0)
			
			return grid[i][j]+calculate_td(grid,i-1,j);
		
		else
			
			return grid[i][j]+Math.min(calculate_td(grid,i-1,j),calculate_td(grid,i,j-1));
		
		 /*
		  *
		 int left=Integer.MAX_VALUE;
		 
		 int right=Integer.MAX_VALUE;
		 
		 if(i!=0)
		 
		   left=calculate(grid,i-1,j);
		  
		  if(j!=0)
		  
		  right =calculate(grid,i,j-1);
		  
		  return grid[i][j]+Math.min(left,right);
		 
		 */
	}
	
	
	
	// Top_down recursive memo
	
	// time complexity O(mn)  grid[m][n]
	
	// space complexity O(2*(mn))
	
	public int calculate_td_memo(int[][] grid,int [][]memo,int i,int j) {
		
		if(memo[i][j]!=0) return memo[i][j];
		
		else {
			
			int new_min=0;
		
		 if(i==0 && j==0) {
			 
			 new_min=grid[i][j];
			
			return new_min;
			
		 }
		
		else if(i==0 &&j!=0) {
			
			new_min=grid[i][j]+calculate_td(grid,i,j-1);
		 
		 	memo[i][j]=new_min; // use back-track to fill the memo
		 	
		 	return new_min;
		 	
		}
		
		else if(j==0 &&i!=0) {
			
			new_min=grid[i][j]+calculate_td(grid,i-1,j);
		 
			memo[i][j]=new_min;
	 	
			return new_min;
		}
		
		else {
			
			new_min= grid[i][j]+Math.min(calculate_td(grid,i-1,j),calculate_td(grid,i,j-1));
		
			memo[i][j]=new_min;
		 	
			return new_min;
		}
		 
		}
		
		 /*
		  *
		 int left=Integer.MAX_VALUE;
		 
		 int right=Integer.MAX_VALUE;
		 
		 if(i!=0)
		 
		   left=calculate(grid,i-1,j);
		  
		  if(j!=0)
		  
		  right =calculate(grid,i,j-1);
		  
		  new_min= grid[i][j]+Math.min(left,right);
		  
		  memo[i][j]=new_min;
		  
		  return new_min;
		 
		 */
	}
	
	public int calculate_bu(int[][]grid,int i,int j) {
		
		// Bottom_up recursive
		
		// time complexity O(2^(m+n))  grid[m][n]
		
		// space complexity O(m+n)
		
		if(i==grid.length-1 && j==grid[0].length-1) return grid[i][j];
		
		else if(i==grid.length-1 && j!=grid[0].length-1)
			
			return grid[i][j]+calculate_bu(grid,i,j+1);
		
		else if(i!=grid.length-1 && j==grid[0].length-1)
			
			return grid[i][j]+calculate_bu(grid,i+1,j);
		
		else 
			
			return grid[i][j]+Math.min(calculate_bu(grid,i,j+1),calculate_bu(grid,i+1,j));
	}
	
	
	public int calculate_bu_memo(int[][]grid,int[][] memo,int i,int j) {
		
		// Bottom_up recursive
		
		// time complexity O(2^(m+n))  grid[m][n]
		
		// space complexity O(m+n)
		
		if(memo[i][j]!=0) return memo[i][j];
		
		else {
			
			int new_memo=0;
			
			if(i==grid.length-1 && j==grid[0].length-1) {
				
				new_memo=grid[i][j];
				
				memo[i][j]=new_memo;
				
				return new_memo;
				
			}
		
			else if(i!=grid.length-1 && j==grid[0].length-1) {
		
				new_memo=grid[i][j]+calculate_bu(grid,i+1,j);
			
				memo[i][j]=new_memo;
			
				return new_memo;
			
			
		}
		
		else if(i==grid.length-1 && j!=grid[0].length-1) {
			
			new_memo=grid[i][j]+calculate_bu(grid,i,j+1);
			
			memo[i][j]=new_memo;
			
			return new_memo;
			
		}
		
		else {
			
			new_memo=grid[i][j]+Math.min(calculate_bu(grid,i,j+1),calculate_bu(grid,i+1,j));
			
			memo[i][j]=new_memo;
			
			return new_memo;
			
		}
			}
				}
	
	
	
	public int calculate_dp_td(int[][]grid) {
		
		
		int m=grid.length;
		
		int n=grid[0].length;
		
		int[][] dp_td=new int[m][n];
		
		
		dp_td[0][0]=grid[0][0];
		
		for(int i=1;i<m;i++)
			
			dp_td[i][0]=grid[i][0]+dp_td[i-1][0];
		
		
		for(int j=1;j<n;j++)
			
			dp_td[0][j]=grid[0][j]+dp_td[0][j-1];
		
		
		for(int i=1;i<m;i++)
			
			for(int j=1;j<n;j++)
				
				dp_td[i][j]=grid[i][j]+Math.min(dp_td[i-1][j], dp_td[j-1][i]) ;
		
		
		return dp_td[m-1][n-1];
		
		
	}
	
	
	public static void main(String[] args) {
	
		Different_Path_Sum dps =new Different_Path_Sum();
		
	
		int[][] grid={{1,3,1},{1,5,1},{4,2,1}};
		
		int [][]memo=new int[grid.length][grid[0].length];
		
		int num1=dps.calculate_td(grid, grid.length-1, grid[0].length-1);
		
		int num2=dps.calculate_td_memo(grid, memo, grid.length-1, grid[0].length-1);
		
		int num3=dps.calculate_bu(grid,0,0);
		
		int num4=dps.calculate_bu_memo(grid, memo, 0,0);
		
		int num5=dps.calculate_dp_td(grid);
		
		System.out.print(num1+" "+num2+" "+num3+" "+num4+" "+num5);

	}

}
