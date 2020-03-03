/**
 * 
 */
package dfs_bfs_method;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

/**
 * @author qiguangqin
 *
 */
public class Water_Puzzle_Test {

	/**
	 * @param args
	 */
	
	private final int FULL_A=8,FULL_B=6;
	
	private boolean[] visited;
	
	private int[] pre;
	
	private int target;
	
	private int end_state=0;
	
	public Water_Puzzle_Test(int target) {
		
		visited= new boolean[100];
		
		pre= new int[100];
		
		pre[0]=-1;
		
		this.target=target;
		
	}
	
	public boolean Sove_puzzle() {
		
		Queue<Integer>queue = new LinkedList<>();
		
		queue.add(0);
		
		visited[0]=true;
		
		while(!queue.isEmpty()) {
			
			int cur=queue.remove();
			
			int a=cur/10,b=cur%10;
			
			// cur_state of two barrel(a and b)
			
			// max a=5 max_b=3
			
			/* Full one of two barrels a or b 
			
				Empty one of two barrel a or b
			*/
			
			List<Integer> nexts=get_nexts(a,b);
			
			for(int next:nexts) {
				
				if(!visited[next]) {
					
					queue.add(next);
					
					pre[next]=cur;
					
					visited[next]=true;
					
					if(next/10==target || next%10==target) {
						
						end_state=next;
						
						return true;
						
					}
				}
			}
		}
		
		return false;
		
	}
	
	private List<Integer>get_nexts(int a,int b){
		
		// a: current_level of barrel A
		
		// b: current_level of barrel B
		
		
		List<Integer> res= new ArrayList<>();
		
		int full_a= FULL_A*10+b,full_b=a*10+FULL_B;
		
		res.add(full_a);res.add(full_b);
		
		int empty_a=0*a+b,empty_b=a*10+0*b;
		
		res.add(empty_a);res.add(empty_b);
		
		int pour_to_b= Math.min(a, FULL_B-b),pour_to_a=Math.min(b, FULL_A-a); 
		
		// Consider the Overflow of A and B barrel, water left contain is FULL-a or -b
		
		res.add(pour_to_b+b+(a-pour_to_b)*10);
		
		res.add((a+pour_to_a)*10+b-pour_to_a);
		
		
		return res;
	
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int target=4;
		
		Water_Puzzle_Test wpt = new Water_Puzzle_Test (target);
		
		Vector<Integer> res= new Vector<>();
		
		if(wpt.Sove_puzzle()) {
			
			int s=wpt.end_state;
			
			while(s!=-1) {
				
				res.add(s);
				
				s=wpt.pre[s];
			
			}
		}
		
		Collections.reverse(res);
		
		int count=res.size()-1;
		
		System.out.println(res+" "+count);

	}
	
}
