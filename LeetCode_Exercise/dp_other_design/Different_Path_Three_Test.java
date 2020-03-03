/**
 * 
 */
package dp_other_design;

/**
 * @author qiguangqin
 *
 */
public class Different_Path_Three_Test {

	/**
	 * @param args
	 */
	
	private int[][] dirs= {{-1,0},{0,1},{1,0},{0,1}};
	
	private int[][] grid;
	
	private int row,column;
	
	private boolean[][] visited;
	
	private int start,end;
	
	private int left;
	
	public Different_Path_Three_Test(int[][] grid) {
		
		this.grid=grid;
		
		row=grid.length;
		
		column=grid[0].length;
		
		visited= new boolean[row][column];
		
		left= row*column;
		
		init_grid();
	}
	
	
	private void init_grid() {
		
		for(int i=0;i<row;i++) {
			
			for(int j=0;j<column;j++) {
				
				if(grid[i][j]==1) {
					
					start=i*column+j;
					
					grid[i][j]=0;
				}
				
				else if(grid[i][j]==2) {
					
					end=i*column+j;
					
					grid[i][j]=0;
				}
				
				else if(grid[i][j]==-1) {
					
					left--;
				}
		
			}
			
		}
	}
	
	public int solve_differ_path3() {
		
		int res=0;
		
		int left=this.left;
		
		res=_DFS_Path(start,left);
		
		return res;
		
	}
	
	
	private int _DFS_Path(int v,int left) {
		
		int x=v/column, y=v%column;
		
		visited[x][y]=true;
		
		left--;
		
		if(left==0 && v==end) {
			
			visited[x][y]=false;
			
			return 1;
		}
		
		int res=0;
		
		for(int d=0;d<4;d++) {
			
			int new_X=dirs[d][0], new_Y=dirs[d][1];
			
			if(Within_Area(new_X,new_Y) && grid[new_X][new_Y]==0 && !visited[new_X][new_Y]) {
				
				res+=_DFS_Path(new_X*column+new_Y,left);
			}
		}
		
		//left++;
		
		visited[x][y]=false;
		
		return res;
	}
	
	private boolean Within_Area(int x,int y) {
		
		return x>=0 && x<row && y>=0 && y<column;
	}
	
	public static void main(String[] args) {


		int[][] grid= {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
		
		Different_Path_Three_Test dpt3= new Different_Path_Three_Test(grid);
		
		int num=dpt3.solve_differ_path3();
		
		//System.out.println(num);
		
		System.out.println(dpt3.start);
		

	}

}
