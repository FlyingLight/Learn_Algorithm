/**
 * 
 */
package dfs_bfs_method;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author qiguangqin
 *
 */
public class Max_Island_Area_Test {

	/**
	 * LeetCode 695
	 */
	
	private int row,column;
	
	private int[][] grid;
	
	private boolean[][] visited_dfs;
	
	private boolean[][]visited_dfs_1;
	
	private boolean[][] visited_bfs;
	
	private final int[][] dirs= {{1,0},{-1,0},{0,1},{0,-1}};
	
	private Union_Find_V3 uf_max_island;
	
	
	public Max_Island_Area_Test(int[][] grid) {
		
		this.grid = grid;
		
		this.row=grid.length;
		
		this.column=grid[0].length;
		
		this.visited_dfs=new boolean[row][column];
		
		this.visited_bfs=new boolean[row][column];
		
		this.visited_dfs_1= new boolean[row][column];
		
		uf_max_island= new Union_Find_V3(row*column);
		
		
	}

		
	public int get_Max_Island_uf() {
		
		_max_island_UF();
		
		return this._uf_res();
	}
	
	private void _max_island_UF() {   // Convert 1d(Fit to  Union-Find dataStructure ) to 2d  grid
		

		for(int v=0;v<row*column;v++) {
			
			int x=v/column,y=v%column; 
			
			if(grid[x][y]==1)
			
			for(int d=0;d<4;d++) {
				
				int next_x=x+dirs[d][0];
				
				int next_y=y+dirs[d][1];
				
				if(WithinArea(next_x,next_y) &&grid[next_x][next_y]==1 ){
					
					int new_v = next_x*column+next_y;
					
					uf_max_island.unionElements(v, new_v);  // Using UF to follow 
					
				}
				
			}
			
		}
		
	}
	
	private int _uf_res() {
		
		int res=0;
		
		for(int v=0;v<row*column;v++) {
			
			int x=v/column,y=v%column; 
			
			if(grid[x][y]==1)
				
				res=Math.max(res,uf_max_island.get(v));
		}
		
		return res;
	}
	
	
	public int get_max_island_dfs() {
		
		if(grid==null || row==0 || column==0) return 0;
		
		//visited= new boolean[row][column];
		
		int res=0;
		
		for(int i=0;i<row;i++)
			
			for(int j=0;j<column;j++) {
				
				if(!visited_dfs[i][j] && grid[i][j]==1)
					
					res=Math.max(res, dfs_Area_1(i,j));
			}
		
		return res;
	}
	
	
	public int get_max_island_bfs() {
		
	if(grid==null || row==0 || column==0) return 0;
		
		//visited= new boolean[row][column];
		
		int res=0;
		
		for(int i=0;i<row;i++)
			
			for(int j=0;j<column;j++) {
				
				if(!visited_bfs[i][j] && grid[i][j]==1)
					
					res=Math.max(res, bfs_Area(i,j));
			}
		
		return res;
		
	}
	
	
	private int dfs_Area(int x,int y) {
		
		
		int res=1;
		
		visited_dfs[x][y]=true;
		
		for(int d=0;d<4;d++) {
			
			int next_x=x+dirs[d][0],next_y=y+dirs[d][1];
			
			if(WithinArea(next_x,next_y) &&!visited_dfs[next_x][next_y] && grid[next_x][next_y]==1  ) {
				
				res+=dfs_Area(next_x,next_y);
			}
		}
		
		return res;
	}
	
	private int dfs_Area_1(int x,int y) {
		
		if(x<0 ||x>=row || y<0 ||y>=column || visited_dfs[x][y] ||grid[x][y]==0) return 0;
		
		int res=1;
		
		visited_dfs[x][y]=true;
		
		for(int[] dir:dirs) {
			
			int next_x=x+dir[0],next_y=y+dir[1];
			
			res+=dfs_Area_1(next_x,next_y);
		}
		
		
		return res;
	}
	
	
	private int bfs_Area(int x,int y) {
		
		Queue<Integer> queue= new LinkedList<>();
		

		//boolean[]visited_bfs= new boolean[row*column+1]; 
		
		int r=x*column+y;
		
		visited_bfs[x][y]=true;
		
		queue.add(r);
		
		int res=1;
		
		while(!queue.isEmpty()) {
			
			int cur_r=queue.poll();
			
			int cur_x=cur_r/column;
			
			int cur_y=cur_r%column;
			
			//res+=1;
			
			for(int k=0;k<4;k++) {
				
				int new_x=cur_x+dirs[k][0];
				
				int new_y=cur_y+dirs[k][1];
				
				if(WithinArea(new_x,new_y) && !visited_bfs[new_x][new_y] && grid[new_x][new_y]==1) {
					
					int new_r=new_x*column+new_y;
					
					visited_bfs[new_x][new_y]=true;
					
					res+=1;
					
					queue.add(new_r);
				}
			}
			
		}
		
		return res;
}
	
	private boolean WithinArea(int x,int y) {
		
		return x>=0 && x<row && y>=0 && y<column;
	}
	

	public static void main(String[] args) {
		
		
		//int[][] grid= {{1,1,0,0,0},{1,1,0,0,0},{0,0,1,0,0},{0,0,0,1,1}};
		
		int[][]grid= {{0,0,1,0,0,0,0,1,0,0,0,0,0},
		              {0,0,0,0,0,0,0,1,1,1,0,0,0},
		              {0,1,1,0,1,0,0,0,0,0,0,0,0},
		              {0,1,0,0,1,1,0,0,1,0,1,0,0},
		              {0,1,0,0,1,1,0,0,1,1,1,0,0},
		              {0,0,0,0,0,0,0,0,0,0,1,0,0},
		              {0,0,0,0,0,0,0,1,1,1,0,0,0},
		              {0,0,0,0,0,0,0,1,1,0,0,0,0}};

		Max_Island_Area_Test miat=new Max_Island_Area_Test(grid);
		
		//System.out.println(miat.grid.length+" "+miat.grid[0].length+" "+miat.visited.length+" "+miat.visited[0].length);
		
		int res1= miat.get_max_island_dfs();
		
		int res2= miat.get_max_island_bfs();
		
		int res3= miat.get_Max_Island_uf();
		
		System.out.println("res_dfs="+res1+" " );
		
		System.out.println("res_bfs="+res2+" " );
		
		System.out.println("res_uf="+res3+" " );
		
	}

}
