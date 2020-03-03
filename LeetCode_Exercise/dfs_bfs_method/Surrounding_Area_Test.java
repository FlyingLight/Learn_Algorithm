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
public class Surrounding_Area_Test {

	/**
	 * @param args
	 */
	
	private final int[][] dirs= {{1,0},{-1,0},{0,1},{0,-1}};
	
	private char [][] board;
	
	private int m,n;
	
	public Surrounding_Area_Test(char[][] board) {
		
		this.board=board;
		
		this.m=board.length;
		
		this.n=board[0].length;
	}
	
	public void solve_dfs() {
		
	
		if(board==null || board.length==0 || board[0]==null || board[0].length==0) return;  // Judge board Array is valid or not 
		
		// Judge the row=0 and row=m-1
		
		for(int col=0;col<n;col++) {
			
			if(board[0][col]=='O')  dfs(0,col,board);
			
			if(board[m-1][col]=='O') dfs(m-1,col,board);
			
		}
		
		// Judge the column=0,and column=n-1
		
		for(int row=0;row<m;row++) {
			
			if(board[row][0]=='O')  dfs(row,0,board);
			
			if(board[row][n-1]=='O')  dfs(row,n-1,board);
		}
		
		for(int i=0;i<m;i++) {
			
			for(int j=0;j<n;j++) {
				
				if(board[i][j]=='O')  board[i][j]='X';
				
				if(board[i][j]=='B')  board[i][j]='O';
				
				
			}
		
		}
	}
	
	
	public void solve_bfs() {
		
		if(board==null || board.length==0 || board[0]==null || board[0].length==0) return;
		
		// Judge the row=0 and row=m-1
		
		for(int col=0;col<n;col++) {
					
			if(board[0][col]=='O')  bfs(0,col,board);
					
			if(board[m-1][col]=='O') bfs(m-1,col,board);
					
		}
		
		// Judge the column=0,and column=n-1
		
		for(int row=0;row<m;row++) {
					
			if(board[row][0]=='O')  bfs(row,0,board);
					
			if(board[row][n-1]=='O')  bfs(row,n-1,board);
		}
		
		for(int i=0;i<m;i++) {
			
			for(int j=0;j<n;j++) {
				
				if(board[i][j]=='O')  board[i][j]='X';
				
				if(board[i][j]=='B')  board[i][j]='O';
				
				
			}
		
		}
		
	}
	
	private void dfs(int x,int y,char[][] board) {
		
		board[x][y]='B';  // The 'O' in boundary is CONVERTED to 'B'
		
		for(int k=0;k<3;k++) {
			
			int new_X= x+dirs[k][0]; //  dfs the 4-connected area
			
			int new_Y= y+dirs[k][1];
			
			if(inArea(new_X,new_Y) && board[new_X][new_Y]=='O')  dfs(new_X,new_Y,board);
		}
		
	}
	
	private void bfs(int x,int y,char[][]board) {
		
		Queue<Integer> queue= new LinkedList<>();
		
		int r=x*n+y;
		
		queue.add(r);
		
		board[x][y]='B';
		
		while(!queue.isEmpty()) {
			
			int cur_r=queue.poll();
			
			int cur_x=cur_r/n;
			
			int cur_y=cur_r%n;
			
			//board[cur_x][cur_y]='B';
			
			for(int k=0;k<4;k++) {
				
				int new_x=cur_x+dirs[k][0];
				
				int new_y=cur_y+dirs[k][1];
				
				if(inArea(new_x,new_y) && board[new_x][new_y]=='O') {
					
					int new_r=new_x*n+new_y;
					
					board[new_x][new_y]='B';
					
					queue.add(new_r);
				}
			}
			
		}
		
	}
	
	public void solve_uf_find() {
		
		if(this.board==null || board.length==0) return ;
		
		Union_Find_V3 uf = new Union_Find_V3(m*n+1);
		
		int dummy_node=m*n;
		
		for(int i=0;i<m;i++) {
			
			for(int j=0;j<n;j++) {
				
				int cur_node=i*n+j;
				
				if(board[i][j]=='O') {
					
					if(i==0|| i==m-1 || j==0||j==n-1) {
							
						uf.unionElements(cur_node, dummy_node);
					}
				
				
				else {
					
					for(int[]dir:dirs) {
						
						int new_i=i+dir[0];
						
						int new_j=j+dir[1];
						
						if(inArea(new_i,new_j) && board[new_i][new_j]=='O') {
							
							int new_uf_node=new_i*n+new_j;
							
							uf.unionElements(new_uf_node,cur_node);
							
						}
					}
					
				}
					
				}
			}
		}
		
		for(int i=0;i<m;i++) {
			
			for(int j=0;j<n;j++) {
				
				if(uf.isConnected(i*n+j, dummy_node)) 
					
					board[i][j]='O';
				
				else 
					
					board[i][j]='X';
				
			}
		}
		
		
	}
	
	private boolean inArea(int x,int y) {
		
		return x>=0 && x<m && y>=0 && y<n;
	}
	
	public static void main(String[] args) {
	
		
		//char [][]board= {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
		
		char [][]board= {{'O','X','X','O','X'},{'X','O','O','X','O'},{'X','O','X','O','X'},{'O','X','O','O','O'},{'X','X','O','X','O'}};
		
		Surrounding_Area_Test sat = new Surrounding_Area_Test(board);
		
		//System.out.println(sat.m+" "+sat.n+board[0].length);
		
		sat.solve_uf_find();
		
		for(int i=0;i<board.length;i++) {
			
			for(int j=0;j<board[0].length;j++) {
				
				if((j+1)%(board[0].length)!=0)
				
					System.out.print(board[i][j]+" ");
				
				else
					
					System.out.println(board[i][j]+"\t");
				
			}
		
		}
		
		

	}

}
