/**
 * 
 */
package backtrack_Method;

/**
 * @author qiguangqin
 *
 */
public class Sudoku_Solve_test {

	/**
	 * @param args
	 * 
	 Get all the method to solve N*N Sudoku Board;
	 */
	
	private int n; // box size ;
	
	private int N;
	
	private int [][]rows;
	
	private int [][]columns;
	
	private int[][] boxes;
	
	private char[][] board;
	
	boolean sudoku_Solve=false;
	
	
	public Sudoku_Solve_test(int n,char[][] board) {
		
		this.n=n;
		
		this.N=n*n;
		
		this.rows=new int[N][N+1];
		
		this.columns=new int[N][N+1];
		
		this.boxes=new int[N][N+1];
		
		this.board=board;
	}
	
	private boolean Could_Place_Number(int num,int row,int col) {
		
		
		int box_id =(row/n)*n+col/n;
		
		return rows[row][num]+columns[col][num]+boxes[box_id][num]==0;
		
	}
	
	private void Place_Number(int num,int row,int col) {
		
		int box_id=(row/n)*n+col/n;
		
		rows[row][num]++;
		
		columns[col][num]++;
		
		boxes[box_id][num]++;
		
		board[row][col]=(char)(num+'0');
		
	}
	
	private void Remove_Number(int num,int row,int col) {
		
		int box_id =(row/n)*n+col/n;
		
		rows[row][num]--;
	
		columns[col][num]--;
		
		boxes[box_id][num]--;
		
		board[row][col]='.';
		
	}
	
	
	private void Place_Next_Number(int row,int col) {
		
		if((col==N-1) &&(row==N-1)) {
			
			this.sudoku_Solve=true;
		}
		
		else {
			
			if(col==N-1) backtrack(row+1,0);  // if one column is finished，start a new row !
			
			else backtrack(row,col+1);  // if one column is not finished , check another column
		}
	}
	
	private void backtrack(int row,int col) {
		
		
		// if the cell is empty
		
		if(board[row][col]=='.') {
			
			// iterate all the number from 1 to 9
			
			for(int num=1;num<10;num++) {
				
				if(Could_Place_Number(num, row, col)) {
					
					Place_Number(num, row, col);
					
					Place_Next_Number(row, col);
					
					
					if(!sudoku_Solve) Remove_Number(num,row,col);
				}
			}
		}
		
		else Place_Next_Number(row,col);
	}
	
	
	public void sovle_SudoKu() {
		
		
		for(int i=0;i<N;i++) {
			
			for(int j=0;j<N;j++) {
				
				char ch=board[i][j];
				
				if(ch!='.') {
					
					int num=Character.getNumericValue(ch);
					
					this.Place_Number(num, i, j);
				}
			}
		}
		
		backtrack(0,0);
	}
	
	
	public static void main(String[] args) {
		
		char[][] board= {
				
				{'5','3','.','.','7','.','.','.','.'},
				
				{'6','.','.','1','9','5','.','.','.'},
				
				{'.','9','8','.','.','.','.','6','.'},
				
				{'8','.','.','.','6','.','.','.','3'},
				
				{'4','.','.','8','.','3','.','.','1'},
				
				{'7','.','.','.','2','.','.','.','6'},
				
				{'.','6','.','.','.','.','2','8','.'},
				
				{'.','.','.','4','1','9','.','.','5'},
				
				{'.','.','.','.','8','.','.','7','9'}		
		};
		
		Sudoku_Solve_test sst =new Sudoku_Solve_test(3,board);
		
		sst.sovle_SudoKu();
		
		for(int i=0;i<9;i++) {
			
			for(int j=0;j<9;j++) {
				
				if((j+1)%9!=0)
				
				System.out.print(board[i][j]+" ");
				
				else
					
					System.out.println("\t");
			}
	}
		
	}

}
