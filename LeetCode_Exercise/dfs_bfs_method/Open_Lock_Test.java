/**
 * 
 */
package dfs_bfs_method;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author qiguangqin
 *
 */
public class Open_Lock_Test {

	/**
	 * @param args
	 * 
	 LeetCode 752 
	 */
	
    private String[] deadends;
    
    private String target;
    
    private String init_state;
    
    private Map<String,String >pre;
	
	public Open_Lock_Test(String[] deadends,String target,String init_state) {
	
		
		this.deadends=deadends;
		
		this.target=target;
		
		this.init_state=init_state;
		
		this.pre= new HashMap<>();
		
		this.pre.put(init_state, init_state);

	}
	
	public Iterable<String> solve_open_lock() {
		
		List<String> res= new ArrayList<>();
		
		String s= target;
		
		res.add(s);
		
		while(!s.equals(init_state)) {
			
			//res.add(s);
			
			s=pre.get(s);
			
			res.add(s);
		}
		
		//res.add(init_state);
		
		Collections.reverse(res);
		
		return res;
	}

	public int openLock() {
		
		Set<String> deadset= new HashSet<>();
		
		for(String s:deadends)
			
			deadset.add(s);
		
		if(deadset.contains(target)|| deadset.contains(init_state)) return -1;
		
		if(target.equals(init_state)) return 0;
		
		Queue<String> queue = new LinkedList<>();
		
		Map<String,Integer> visited = new HashMap<>();
		
		queue.add(init_state);
		
		visited.put(init_state, 0);
		
		while(!queue.isEmpty()) {
			
			 String cur= queue.remove();
			 
			 List<String> nexts = new ArrayList<>();
			 
			 nexts=_get_nexts(cur);
			 
			 for(String next_cur:nexts) {
				 
				 // Not visited AND not dead_end
				 
				 if(!visited.containsKey(next_cur) && !deadset.contains(next_cur)) {
				
					 queue.add(next_cur);
							 
					 visited.put(next_cur, visited.get(cur)+1);
					 
					 pre.put(next_cur, cur);
					 
					 if(next_cur.equals(target))
						 
						 return visited.get(next_cur);
						 
				 }
			 }
		}
		
		return -1;
		
	}
	
	private List<String> _get_nexts(String cur){
		
		char[] cur_array = cur.toCharArray();
		
		List<String>nexts= new ArrayList<>();
		
		for(int i=0;i<4;i++) {
			
			char o= cur_array[i];
			
			cur_array[i]=Character.forDigit((cur_array[i]-'0'+1)%10, 10);
	
			nexts.add(new String(cur_array)); // Construct new String // forward 
			
			cur_array[i]=o; // Each time change one pos(+1 ),and then back_track
			
			cur_array[i]=Character.forDigit((cur_array[i]-'0'+9)%10, 10); //Back_forward
			
			nexts.add(new String(cur_array));
			
			cur_array[i]=o;  // Each time change one pos(-1 ),and then back_track
		}
		
		return nexts;
	}
	
	public static void main(String[] args) {
		
		String[] deadends = {"0201","0101","0102","1212","2002"};
		
		String target="0202";
		
		String init_state="0000";
		
		Open_Lock_Test olt =new Open_Lock_Test(deadends,target,init_state);
		
		int num=olt.openLock();
		
		System.out.println(olt.solve_open_lock()+" "+num);

	}

}
