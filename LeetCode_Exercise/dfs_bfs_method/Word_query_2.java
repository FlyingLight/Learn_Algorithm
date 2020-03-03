package dfs_bfs_method;

import java.util.ArrayList;
import java.util.List;

public class Word_query_2 {
	
	
	private char[][]board;
	
	private String[] words;
	
	
	public Word_query_2(char[][]board,String[]words) {
		
		this.board=board;
		
		this.words=words;
	}
	
	public List<String>findWords(){

		List<String> res= new ArrayList<>();
		
		for(String s:words) {
			
			Word_query wq= new Word_query(s,board);

			if (wq.exist()) 
				
			res.add(s);
			
		}

		return res;
		
	}
	public static void main(String[] args) {
		
		char[][] board= {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
		
		String []words= {"oath","pea","eat","rain"};
		
		Word_query_2 wq= new Word_query_2(board,words);
		
		System.out.println(wq.findWords());
	}

}
