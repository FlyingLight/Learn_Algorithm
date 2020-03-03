/**
 * 
 */
package dfs_bfs_method;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author qiguangqin
 *
 */
public class Solve_Slide_Puzzle {

	/**
	 * @param args
	 */
	
	private final int[][] dirs= {{-1,0},{0,1},{1,0},{0,-1}};
	
	private String target="123450";
	
	private Map<String,String> pre;
	
	private String initial_state;
	
	private int row,column;
	
	public int slide_puzzle(int[][] board) {
		
		
		this.row=board.length;
		
		this.column=board[0].length;
		
		Queue<String> queue= new LinkedList<>();
		
		// visited array to record vertex state,  String as the index to use HashMap 
		
		HashMap<String,Integer> visited=new HashMap<>();
		
		pre= new HashMap<>();
		
		this.initial_state =boardToString(board);
		
		if(initial_state.equals(target)) return 0;
		
		pre.put(initial_state, initial_state);
		
		//BFS
		
		queue.add(initial_state);
		
		visited.put(initial_state, 0); // init_state ==0
		
		while(!queue.isEmpty()) {
			
//			if(next.equals(target))
			
			//		return visited.get(next);
			
			String cur= queue.remove();
			
		//	if(cur.equals(target)) return visited.get(cur);
			
			ArrayList<String>nexts= getNexts(cur); // List to save next all states
			
			// Find the next state (all possibilities)
			
			// Actually swap the "0" elements with its surrounding element 
			
			for(String next:nexts) {
				
				if(!visited.containsKey(next)) {
					
					//if(!next.equals(target)) {
					
					queue.add(next);
					
					visited.put(next, visited.get(cur)+1);
					
					pre.put(next, cur);
					
				}
					
					//else 
						
					//	 return visited.get(cur)+1;
					
				if(next.equals(target))
						
						return visited.get(next);
				}
			//}
		}
		
		return -1;
		
	}
	
	public Iterable<String> solvePuzzle_count() {
		
		
		List<String> res= new ArrayList<>();
		
		String s= target;
		
		res.add(s);
		
		while(!s.equals(initial_state)) {
			
			s=pre.get(s);
			
			res.add(s);
		}
		
		Collections.reverse(res);
		
		return res;
	}
	
	private ArrayList<String>getNexts(String s){
		
		int[][]cur=stringToBoard(s);//  s convert to the 2 dimension matrix
		
		// find the zero index
		
		int zero;
		
		for(zero=0;zero<row*column;zero++)
			
			if(cur[zero/column][zero%column]==0) break;
		
		ArrayList<String>res = new ArrayList<>();
		
		int zx= zero/column,zy=zero%column;
		
		for(int d=0;d<4;d++) {
			
			int next_x=zx+dirs[d][0],next_y=zy+dirs[d][1];
			
			if(inArea(next_x,next_y)) {
				
				swap(cur,zx,zy,next_x,next_y);
				
				res.add(boardToString(cur));
					
				//restore the state
				
				swap(cur,zx,zy,next_x,next_y);  // Each loop end,need to restore the state that in 
			}
			
		}
		return res;
	}
	
	private void swap(int[][] board ,int x1,int y1,int x2,int y2) {
		
		int temp=board[x1][y1];
				
		board[x1][y1]=board[x2][y2];
		
		board[x2][y2]=temp;
	}
	
	private boolean inArea(int x,int y) {
		
		return x>=0 && x<row  && y>=0 &&y<column;
	}
	
	private String boardToString(int[][] board) {
		
		StringBuilder sb= new StringBuilder();
		
		for(int i=0;i<2;i++)
			
			for(int j=0;j<3;j++)
				
				sb.append(board[i][j]);
		
		return sb.toString();
		
	}
	
	private int[][] stringToBoard(String s){
		
		int[][] board= new int[row][column];
		
		for(int i=0;i<row*column;i++) {
			
			board[i/column][i%column]=s.charAt(i)-'0'; // 2nd dimension to one dimension
			
		}
		
		return board;
		
	}
	
	public static void main(String[] args) {
		
		Solve_Slide_Puzzle ssp = new Solve_Slide_Puzzle();
	
		int[][] board = {{4,1,2},{5,0,3}};
		
		int count= ssp.slide_puzzle(board);
		
		System.out.println(ssp.solvePuzzle_count()+" "+count);

	}

}
