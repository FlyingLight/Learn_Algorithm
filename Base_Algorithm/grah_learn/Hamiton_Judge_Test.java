/**
 * 
 */
package grah_learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author qiguangqin
 *
 */
public class Hamiton_Judge_Test {

	/**
	 * @param args
	 */
	
	private Graph graph;
	
	boolean[] visited;
	
	private int[] from;
	
	int s,end,left; // if left is a global variable ,in the recursion must pay attention to back_track
	
	
	public Hamiton_Judge_Test(Graph graph,int s) {
		
		this.graph=graph;
		
		this.s=s;
		
		visited=new boolean[graph.V()];
		
		from=new int[graph.V()];
		
		Arrays.fill(from, -1);
		
		left=graph.V();
		
		end=-1;
	}
	
	
	public boolean dfs_haminton_judge() {
		
		for(int i=0;i<graph.V();i++) {
			
			if(!visited[i]) {
				
				if(!_DFS_Hamintion_Judge_v2(i,i)) {
					
					return false;
				}
				
				//return _DFS_Hamintion_Judge(i, i);
				
				//return _DFS_Hamintion_Judge_v2(i, i);
			}
		}
		
		return true;
	}
	
	private boolean _DFS_Hamintion_Judge(int v,int parent) {
		
		visited[v]=true;
		
		from[v]=parent;
		
		for(int w:graph.adj(v)) {
			
			if(!visited[w]) {
				
				if(_DFS_Hamintion_Judge(w, v)) {
						
					return true;
				}
			}
			
			else if(w!=parent){
				
				if(get_all_visited()) {
					
					end=v;
					
					return true;
					
				}
			}
		}
		
		visited[v]=false; // during the back_track 
		
		return false;
	}
	
	private boolean _DFS_Hamintion_Judge_v2(int v,int parent) {
		
		visited[v]=true;
		
		from[v]=parent;
		
		left--;
		
		for(int w:graph.adj(v)) {
			
			if(!visited[w]) {
				
				if(_DFS_Hamintion_Judge_v2(w, v)) {
					
					return true;
				}
			}
			
			else if(w!=parent && left==0) {
				
				end=v;
				
				return true;
			}
		}
		
		visited[v]=false;
		
		left++;
		
		return false;
	}
	
	private boolean _DFS_Hamintion_Judge_v3(int v,int parent,int left) {
		
		visited[v]=true;
		
		from[v]=parent;
		
		left--;
		
		for(int w:graph.adj(v)) {
			
			if(!visited[w]) {
				
				if(_DFS_Hamintion_Judge_v4(w, v, left)) {
					
					return true;
				}
			}
			
			else if(w!=parent &&left ==0) {
				
				end=v;
				
				return true;
			}
		}
		
		visited[v]=false;
		
		return false;
	}
	
	private boolean _DFS_Hamintion_Judge_v4(int v,int parent,int left) {
		
		visited[v]=true;
		
		from[v]=parent;
		
		left--;
		
		if(graph.hasEdge(v, s) && left==0) {
			
			end=v;
			
			return true;
		}
		
		for(int w:graph.adj(v)) {
			
			if(!visited[w]) {
				
				if(_DFS_Hamintion_Judge_v4(w, v, left)) {
					
					return true;
				}
			}
			
			
		}
		
		visited[v]=false;
		
		return false;
	}
	
	
	private boolean get_all_visited() {
		
		for(int i=0;i<graph.V();i++) {
			
			if(!visited[i])
				
				return false;
			
		}
		
		return true;
		
	}
	
	public ArrayList<Integer> get_res(){
		
		ArrayList<Integer> res= new ArrayList<>();
		
		if(end==-1) return res;
		
		while(end!=s) {
			
			res.add(end);
			
			end=from[end];
		}
		
		res.add(s);
		
		Collections.reverse(res);
		
		return res;
	}
	
	public static void main(String[] args) {
	
		String fileName8="E:\\iotest\\testG8.txt";
		
		SparseGraph_tree_map g8=new SparseGraph_tree_map(4,false);
		   
	    new Read_Graph(g8,fileName8);
	    
	    g8.show();
	    
	    Hamiton_Judge_Test hjt1 = new Hamiton_Judge_Test(g8,0);
	    
	    boolean flag=hjt1.dfs_haminton_judge();
	    
	    System.out.println("Graph8 has a Haminton_Loop="+flag);
	    
	    System.out.println("The Haminton_Loop of Graph8= "+hjt1.get_res());

	}

}
