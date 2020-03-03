/**
 * 
 */
package dfs_bfs_method;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author qiguangqin
 *
 */
public class Word_Break2 {

	/**
	 * @param args
	 */
	
	private String s;
	
	private Set<String>wordDict;
	
	public Word_Break2(String s,Set<String>wordDict) {
		
		this.s=s;
		
		this.wordDict=wordDict;
	}
	
	public List<String> word_break_backTrack1(){
		
		return _word_break_backTrack1(0,new ArrayList<String>());
	}
	
	
	private List<String>_word_break_backTrack1(int start,List<String>temp){
		
		//List<String>res=new ArrayList<>();
		
		//if(start==s.length()) temp.add("");//res.add("");
		
		for(int end=start+1;end<=s.length();end++) {
			
			if(wordDict.contains(s.substring(start, end))) {
				
				temp=_word_break_backTrack1(end,temp);
				
				//System.out.print("list="+list);
				
				//for(String i:list)
				
				//res.add(s.substring(start,end)+(i.equals("")?"":" ")+i);
					
				temp.add(s.substring(start,end));
				
				//System.out.println(" res="+res);
			}
		}
		
		return temp;
	}
	
	
	
	public List<String> word_break_backTrack2(){
		
		return _word_break_backTrack2(0);
	}
	
	
	private List<String>_word_break_backTrack2(int start){
		
		List<String>res=new ArrayList<>(); // each recursion to restart a list 
		
		if(start==s.length()) res.add("");
		
		for(int end=start+1;end<=s.length();end++) {
			
			if(wordDict.contains(s.substring(start, end))) {
				
				List<String> list=_word_break_backTrack2(end);
				
				System.out.print("list="+list);
				
				for(String i:list) {
				
				
					res.add(s.substring(start,end)+(i.equals("")?"":" ")+i);}
					
					System.out.println(" res="+res);
			}
		}
		
		return res;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Set<String> list= new HashSet<>();
		
		list.add("cat");
		
		list.add("cats");
		
		list.add("and");
		
		list.add("sand");
		
		list.add("dog");
		
		String s="catsanddog";
		
		Word_Break2 wb = new Word_Break2 (s,list);
		
		List<String> res1= wb.word_break_backTrack1();
		
		List<String> res2= wb.word_break_backTrack2();
		
		Collections.reverse(res2);Collections.reverse(res1);
		
		System.out.println("back_track_1="+res1);
		
		System.out.println("back_track_2="+res2);
	}

}
