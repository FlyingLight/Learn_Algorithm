/**
 * 
 */
package dfs_bfs_method;


/**
 * @author qiguangqin
 *
 */



public class Word_query {

	/**
	 * @param args
	 */
	
	
	private boolean[][]marked; // Use to the character in word(String) has been considered 
	
	private final int[][] direction= {{-1,0},{0,-1},{0,1},{1,0}};
	
	// direction of the DFS
	
	private int m; // the row the matrix
	
	private int n; // the column the matrix
	
	private String word;
	
	private char[][] board;
	
	//private List<Character>res;
	
	
	
	public Word_query(String word,char[][]board) {
		
		this.word=word;
		
		this.board=board;
		
		this.m=board.length;
		
		this.n=board[0].length;
		
		this.marked= new boolean[m][n];
		
		//res= new ArrayList<>();
		
		
	}
	
	public boolean exist() {
		
		if(m==0 || n==0) return false;
		
		for(int i=0;i<m;i++) {
			
			for(int j=0;j<n;j++) {
				
				if(dfs_query(i,j,0)) return true;
				
			}
			
		}
		
		return false;
	}
	

	

	private boolean dfs_query(int i,int j,int start) {
		
		if(start==word.length()-1) {
			
			return board[i][j]==word.charAt(start);
			
			//return true;
		
		}
		
		if(board[i][j]==word.charAt(start)) {
			
			marked[i][j]=true;
			
			for(int direct=0;direct<4;direct++) {
				
				int newX= i+this.direction[direct][0];
				
				int newY=j+this.direction[direct][1];
				
				if(withinArea(newX,newY) && !marked[newX][newY]) {
					
					if(dfs_query(newX,newY,start+1)) return true;
				}
			}
			
			marked[i][j]=false;
		}
		
		return false;
	}
	
	private boolean withinArea(int x,int y) {
		
		return (x>=0 && x<=m-1) &&(y>=0 && y<=n-1);
	}
	
	public static void main(String[] args) {
		
		char[][] board= {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
		
		String word="ABCCED";
		
		Word_query wq =new Word_query(word,board);
		
		System.out.println(wq.exist());
		
		//wq.print();
		
		//System.out.println(wq.res);
		

	}

}
