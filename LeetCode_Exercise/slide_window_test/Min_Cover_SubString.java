/**
 * 
 */
package slide_window_test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qiguangqin
 *
 */
public class Min_Cover_SubString {

	/**
	 * @param args
	 */
		
	private String s;
	
	private String t;
	
	private Map<String,Integer> res;
	
	public Min_Cover_SubString(String s,String t) {
		
		this.s=s;
		
		this.t=t;
		
		res= new HashMap<>();
		
	}
	
	public Map<String,Integer> getMinString() {
		
		 _minWindow();
		 
		 return res;
	}
	
	private void _minWindow() {
		
		if(s.length()==0 || s.length()==0) {
			
			//return "";
			
			res.put("", 0);
		}
		
		Map<Character,Integer>Dict=new HashMap<>();
		
		for(int i=0;i<t.length();i++) {
			
			int count=Dict.getOrDefault(t.charAt(i), 0); // character of t string--->HashMap
			
			Dict.put(t.charAt(i), count+1); // initiate the sequence of t by HashMap(Dict)
			
		}
		
		int required_len=Dict.size();
		
		int formed=0; // already formed the unique window length
		
		int l=0,r=0;
		
		Map<Character,Integer> win_Counts=new HashMap<>();
		
		int[] ans= {-1,0,0}; //{window_length,left_index,right_index}
		
		while(r<s.length()) {
			
			char c_r=s.charAt(r); // extend the window in the right direction
			
			int count=win_Counts.getOrDefault(c_r, 0);
			
			win_Counts.put(c_r, count+1);
			
			if(Dict.containsKey(c_r) && Dict.get(c_r).intValue()==win_Counts.get(c_r).intValue()) {
				
				formed++;
			}
			
			while(l<=r && formed==required_len) {
				
				char c_l=s.charAt(l);
				
				if(ans[0]==-1|| r-l+1<ans[0]) {
					
					ans[0]=r-l+1;
					
					ans[1]=l;
					
					ans[2]=r;
				}
				
				win_Counts.put(c_l, win_Counts.get(c_l)-1);
				
				if(Dict.containsKey(c_l) && Dict.get(c_l)>win_Counts.get(c_l)) {
					
					formed--;
				}
				
				l++;
				
			}
			
			r++;
		}
		
		//return ans[0]==-1? "":s.substring(ans[1], ans[2]+1);
		
		if(ans[0]==-1) {
			
			res.put("", 0);
		}
		
		else {
			
			res.put(s.substring(ans[1],ans[2]+1), ans[2]-ans[1]+1);
		}
	}
	
	
	
	@Override
	public String toString() {
		return "[res=" + res + "]";
	}

	public static void main(String[] args) {
	
		String s="ABAACBAB";
		
		String t="ABC";
		
		Min_Cover_SubString mct = new Min_Cover_SubString(s,t);
		
		mct.getMinString();
		
		System.out.println(mct);
	}

}
