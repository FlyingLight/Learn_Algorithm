package dfs_bfs_method;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Word_break {
	
	private List<String>res;
	
	public boolean wordBreak_recursive(String s,List<String> wordDict) {
		
		
		return _wordBreak_recursive(s,new HashSet<String>(wordDict),0);
	}
	
	
	public boolean wordBreak_recursive_memo(String s,List<String> wordDict) {
		
		Boolean []memo= new Boolean[s.length()];
		
		return _wordBreak_recursive_memo(s, new HashSet<String>(wordDict),0,memo );
		
	}
	
	private boolean _wordBreak_recursive(String s,Set<String> wordDict,int start) {
		
		if(start==s.length())  return true;
		
		for(int end=start+1;end<=s.length();end++) {
			
			// substring method [beginIndex,endIndex)
			
			if(wordDict.contains(s.substring(start,end)) && _wordBreak_recursive(s,wordDict,end) ) {
				
				return true;
			}
		}
		
		return false;
		
	}
	
	// use memo to simplify
	
	private boolean _wordBreak_recursive_memo(String s,Set<String> wordDict,int start,Boolean[] memo) {
		
		if(start==s.length()) return true;
		
		if(memo[start]!= null) return memo[start]; // use Boolean Array
				
		for(int end=start+1;end<=s.length();end++) {
			
			if(wordDict.contains(s.substring(start, end)) && _wordBreak_recursive_memo(s,wordDict,end,memo)) {
				
				
				memo[start]=true;
				
				return true;
				
			}
			
		}
		
		memo[start]=false;
		
		return false;
	}
	
	
	public boolean WordBreak_dp(String s,List<String> wordDict) {
		
		res= new ArrayList<>();
		
		Set<String> wordDictSet =new HashSet<>(wordDict);
		
		boolean[] dp = new boolean[s.length()+1];
		
		dp[0]=true;
		
		/*
		 
		 S[0,j] and S[j+1,i]
 		 
		 */
		
		for(int i=1;i<=s.length();i++) {
		
	//	for(int i=0;i<s.length();i++) {
			
			for(int j=0;j<i;j++) {
			
			//for(int j=i;j<=s.length();j++) {
				
				if(dp[j] &&wordDictSet.contains(s.substring(j, i))) {
					
			//		if(dp[i] && wordDictSet.contains(s.substring(i,j))) { dp[j]=true;break; 
					
					dp[i]=true;
					
					res.add(s.substring(j)+" "+s.substring(j,i));
					
					
					
					//break;
				}
			}
		}
		
		return dp[s.length()];
	}
	
	public boolean WordBreak_BFS(String s,List<String>wordDict) {
		
		// From beginning state to the end state
		
		Queue<Integer> queue= new LinkedList<>(); // queue to record the end of the string
		
		// which is in the 
		
		Set<String> wordDictSet= new HashSet<>(wordDict);
		
		int []visited= new int[s.length()];
		
		queue.add(0);
		
		while(!queue.isEmpty()) {
			
			int start= queue.remove();
			
			if(visited[start]==0) {
			
				for(int end=start+1;end<=s.length();end++) {
					
					if(wordDictSet.contains(s.substring(start, end))) {
						
						queue.add(end);
				
						if(end==s.length()) {
							
							return true;
						}
					}
				
			}
				
			visited[start]=1;
				
		}
		
		}
		
		return false;
		
	}
	
	/*
	public boolean WordBreak_dp2(String s,List<String> wordDict){
		
		int n=s.length();

		Set<String> word_dict_set= new HashSet<>(wordDict);

		boolean[]dp= new boolean[n+1]; 

		dp[0]=true;

		int start=0;

		boolean flag=true;

		while(start<n && flag){

			flag=false;

			for(int end=n;end>=start+1;end--){
				
				
				if(word_dict_set.contains(s.substring(start,end))){

					dp[end]=true;   //  end element is true

					flag=true;

					start=end;
			
					break;
				}

			}

			if(flag==false)  break;
		}
		
		for(int i=0;i<=n;i++) System.out.println("dp["+i+"]="+dp[i]);

		return dp[n];
		
		
		
		boolean flag =dp2_help1(s,wordDict) || dp2_help2(s,wordDict);
		
		System.out.println(flag);
		
		return flag;
		
		}
		
		*/
	
	/*
	private boolean dp2_help1(String s,List<String> wordDict) {
		
		int n=s.length();
		
		Set<String> word_dict_set= new HashSet<>(wordDict);

		boolean[]dp= new boolean[n+1]; 

		dp[0]=true;

		int start=0;
		
		

		boolean flag=true;

		while(start<n && flag){

			flag=false;

			for(int end=n;end>=start+1;end--){
				
				if(word_dict_set.contains(s.substring(start,end))){

					dp[end]=true;   //  end element is true

					flag=true;

					start=end;
			
					break;
				}

			}

			if(flag==false)  break;
		}
		
		for(int i=0;i<=n;i++) System.out.println("dp["+i+"]="+dp[i]);

		return dp[n];
	}
	
	
	private boolean dp2_help2(String s,List<String> wordDict) {
		
		int n=s.length();
		
		Set<String> word_dict_set= new HashSet<>(wordDict);

		boolean[]dp= new boolean[n+1]; 

		dp[0]=true;

		int start=0;

		boolean flag=true;

		while(start<n && flag){

			flag=false;

			for(int end=start+1;end<=n;end++){
				
				if(word_dict_set.contains(s.substring(start,end))){

					dp[end]=true;   //  end element is true

					flag=true;

					start=end;
			
					break;
				}

			}

			if(flag==false)  break;
		}
		
		for(int i=0;i<=n;i++) System.out.println("dp["+i+"]="+dp[i]);

		return dp[n];
	}
	
	*/
	public static void main(String[] args) {
		
		
		Word_break wb = new Word_break ();
		
		List<String> list= new ArrayList<>();
		
		list.add("cat");
		
		list.add("cats");
		
		list.add("and");
		
		list.add("sand");
		
		list.add("dog");
		
		String s="catsanddog";
		
		String s1="abcd";
		
		List<String> list1= new ArrayList<>();
		
		list1.add("a");
		list1.add("b");
		list1.add("cd");
		list1.add("abc");
		
		//boolean flag=wb.wordBreak_recursive_memo(s, list);
		
		boolean flag1=wb.WordBreak_dp(s, list);
		
		//boolean flag2=wb.WordBreak_dp2(s1, list1);
		
		System.out.println(flag1+" "+wb.res);
	}

}
