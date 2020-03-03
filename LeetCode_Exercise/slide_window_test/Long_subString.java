/**
 * 
 */
package slide_window_test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author qiguangqin
 *
 */
public class Long_subString {
	

	
	// violence method  O(n^3)

	
	public int lengthOfLongestSubString_v1(String t) {
		
		int n=t.length();
		
		int res=0;
		
		for(int i=0;i<n;i++) 
			
			for(int j=i+1;j<=n;j++) 
				
				if (all_unique(t,i,j))  res=Math.max(res, j-i);
				
			
		return res;
		
	}
	
	
	private boolean all_unique(String t,int start ,int end) {
		
		Set<Character> set =new HashSet<>();
		
		for(int i=start;i<end;i++) {
			
			if(set.contains(t.charAt(i))) return false;
			
			set.add(t.charAt(i));
		}
		
		return true;
	}
	
	//  [I,J] HashSet window  Slide I when same element encounter O(2n)
		
	public String lengthOfLongestSubString_v22(String t ) {
		
		int n=t.length();
		
		int res=0,i=0,j=0;
		
		String s="";
	
		Set<Character> set =new HashSet<>();
		
		while(i<n && j<n) {
			
			if(!set.contains(t.charAt(j))) {
				
				set.add(t.charAt(j++));
				
				//res=Math.max(res, j-i);
				
				if(j-i>res) {
					
					s=t.substring(i, j);
					
					res=j-i;
				}
				
			}
			
			else 
				
				set.remove(t.charAt(i++));		
			
		}
		
		return s;
		
	}
	
	public int lengthOfLongestSubString_v2(String t) {
		
		int n=t.length();
		
		int res=0,i=0,j=0;
		
		Set<Character>set =new HashSet<>();
		
		while(i<n && j<n) {
			
			if(!set.contains(t.charAt(j))) {
				
				set.add(t.charAt(j++));
				
				res=Math.max(res, j-i);
				
			}
			
			
			else 
				
				set.remove(t.charAt(i++));
		}
		
		return res;
		
	}
	
	
	public int lengthOfLongestSubString_v3(String t) {
		
		
		int n=t.length(),res=0;
		
		Map<Character,Integer>map =new HashMap<>();
		
		for(int i=0,j=0;j<n;j++) {
			
			System.out.println("i="+i+" "+"j="+j);
			
			if(map.containsKey(t.charAt(j))) {
				
				i=Math.max(map.get(t.charAt(j)), i);
				
				//i=map.get(t.charAt(j));
			
			}
			
			System.out.println("i'="+i+" "+"j'="+j);
		
			res=Math.max(res, j-i+1);
			
			System.out.println("res="+res);
			
			map.put(t.charAt(j),j+1);
			
			System.out.println(map);
			
			System.out.println("------------------");
			
			
		}
		
		return res;
		
	}
	
	public static void main(String[] args) {
	
		
		String t="tmmzuxtm";
		
		Long_subString ls =new Long_subString();
		
		System.out.println(ls.lengthOfLongestSubString_v3(t));

	}

}
