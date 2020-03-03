/**
 * 
 */
package backtrack_Method;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiguangqin
 *
 */
public class N_Queen_test2 {

	/**
	 * @param args
	 */
	
	private int []rows; // rows[col]=1 to demonstrate the queens in (row-col)
	
	private int []hills; // to demonstrate hills diagonals, is underAttack or not
	
	// row+column=Const.
	
	private int []dales;// to demonstrate dale diagonals, is underAttack or not
	
	// row-column=Const.
	
	private int n; // need to place n queuen;
	
	private int count=0;
	
	List<List<String>> output = new ArrayList<>();
	
	private int []queens;  // demonstrate the place of queens, queens[row]=col  (row-col) has a queen
	
	public N_Queen_test2(int n) {
		
		this.n=n;
		
		rows= new int[n];
		
		hills= new int[2*n-1];
		
		dales= new int [3*n];
		
		queens= new int[n];
	}
	
	private boolean isNotUnderAttack(int row,int col) {
		
		return rows[col]+hills[row+col]+dales[row-col+2*n]==0;
		
	}
	
	private void place_queeen(int row,int col) {
		
		queens[row]=col;
		
		rows[col]=1;
		
		hills[row+col]=1;
		
		dales[row-col+2*n]=1;
	}
	
	private void remove_queen(int row,int col) {
		
		queens[row]=0;
		
		rows[col]=0;
		
		hills[row+col]=0;
		
		dales[row-col+2*n]=0;
		
	}
	
	private void add_solution() {
		
		List<String> solution = new ArrayList<>();
		
		for(int i=0;i<n;i++) {
			
			int col=queens[i];
			
			StringBuilder chess= new StringBuilder();
			
			for(int j=0;j<col;j++) chess.append(".");
			
			chess.append("Q");
			
			for(int j=0;j<n-col-1;j++) chess.append(".");
			
			solution.add(chess.toString());
		}
		
		output.add(solution);
	}
	
	
	private void back_track_search(int row) {
		
		for(int col=0;col<n;col++) {
			
			if(isNotUnderAttack(row, col)) {
				
				place_queeen(row, col);
				
				if(row+1==n) {
					
					add_solution(); count++;
					
				} // if n queens are already placed; add the solution
				
				else back_track_search(row+1);  //  if the all the place is underAttack,back_track
				
				remove_queen(row, col);  // 
			}
		}
		
	}
	
	
	private void back_track_search_2(int row) {
		
	
		
		if(row==n) { add_solution();count++; return ;}
		
		/*
		 
		  . . Q .
		  
		  Q. . .
		 
		 

		 */
		
		
		
		
		for(int col=0;col<n;col++) {
			
			if(isNotUnderAttack(row, col)) {
				
				place_queeen(row, col);
				
				back_track_search_2(row+1);  //  if the all the place is underAttack,back_track
				
				remove_queen(row, col);  // 
			}
		}
		
	}

	
	
	public List<List<String>> solveNQueens(){
		
		
		back_track_search(0);
		
		return output;
	}
	
	public List<List<String>> solveNQueens_2(){
		
		
		back_track_search_2(0);
		
		return output;
	}

	
	public static void main(String[] args) {
		
		int n=12;
		
		N_Queen_test2 nt = new N_Queen_test2(n);
		
		//System.out.println(nt.solveNQueens());
		
		//System.out.println(nt.count);
		
		System.out.println(nt.solveNQueens_2());
		
		System.out.println(nt.count);

	}

}
