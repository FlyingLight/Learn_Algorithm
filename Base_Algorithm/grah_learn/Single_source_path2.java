/**
 * 
 */
package grah_learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


/**
 * @author qiguangqin
 *
 */
public class Single_source_path2 {

	
	private Graph graph;  // the quote of the Graph
	
	private int s;  // the beginning of the map;must validate the possibility
	
	private boolean[] visited;  // in DFS or BFS , Whether Vertex is visited or not
	
	private int[] from; //from[i] get the parent of the vertex
	
	int[] dis;
	
	public Single_source_path2(Graph graph,int s) {
		
		
		this.graph=graph;
		
		validate_Vetrex(s);
		
		this.s=s;
		
		visited= new boolean[graph.V()]; // no need to get the ccount;
		
		from= new int[graph.V()];
		
		dis= new int[graph.V()];
		
		Arrays.fill(from, -1);
		
		Arrays.fill(dis, -1);
		
		dis[s]=0;
		
		//dfs_single_connect(s,s);// must match the path_v2 method
		
		dfs_single_connect(s,-1); // must match the path method 
		
		// dfs_single_connect(s)  // must match the path method
		
		//dfs_is_connect(s, s,4); 
		
		//for(boolean e: visited) System.out.print(e+" ");
		
	}
	
	public boolean Cycle_detection() {
		
		for(int v=0;v<graph.V();v++) { // detect the different ccount of the graph
			
			// if(!visited[v]) { 
			
				//if(dfs_cycle(v, v)) return true;
			
				if(dfs_cycle(v)) return true;
				
				//}
		}
		
		return false;
	}
	
	
	private void dfs_single_connect(int v) {
		
		visited[v]=true;
		
		for(int i:graph.adj(v))
			
			if(!visited[i]) { 
				
				from[i]=v; 
				
				dis[i]=dis[v]+1;
				
				dfs_single_connect(i);
				
			}
	}
	
	/*
	public boolean dfs_is_connect(int v ,int t) {
		
		visited[v]=true;
		
		for(int i:graph.adj(v)) {
			
			if(i==t) { from[i]=v; return true;}
			
			else if(i!=t && !visited[i]) {
				
				from[i]=v; 
				
				dfs_is_connect(i,t);
				
				}
			}
		
		return false;
	}
	
	public boolean dfs_is_connect(int v ,int parent,int t) {
		
		visited[v]=true;
		
		from[v]=parent;
		
		if(v==t) return true;
			
		for(int i:graph.adj(v)) {
			
			if(i!=t && !visited[i]) 
				
				dfs_is_connect(i,v,t);
			
			}
		
		return false;
	}
	*/
	
	
	private void dfs_single_connect(int v,int parent) {
		
		/*
		 * 
		 Time  O(V+E)
		 
		 Space O(V)
		 */
		
		visited[v]=true;
		
		from[v]=parent;
		
		for(int i:graph.adj(v)) {
			
			if(!visited[i]) { dis[i]=dis[v]+1; dfs_single_connect(i,v);}
		}
	}
	
	private void validate_Vetrex(int v) {
		
		
		if(v<0 || v>=graph.V()) throw new IllegalArgumentException("Vetrex"+v+" is invalid");
	}
	
	
	public boolean isConnectedTo(int v) {
		
		this.validate_Vetrex(v);
		
		return visited[v];
	}
	
	public Iterable<Integer> path(int v){
		
		Stack<Integer> ss=new Stack<Integer>();
		
		List<Integer> res= new ArrayList<>();
		
		if(!isConnectedTo(v)) return res;
		
		else {
		
		int pre_vetrex=v;
		
		while(pre_vetrex!=-1) {
			
			ss.push(pre_vetrex);
			
			pre_vetrex=from[pre_vetrex];
		}
		
		
		while(!ss.isEmpty()) {
			
			res.add(ss.pop());
		}
		
		return res;
		
		}
	}
	
	public int dis(int t) {
		
		graph.validate_Vetrex(t);
		
		return dis[t];
		
	}
	
	public Iterable<Integer> path_v2(int v){
		
		List<Integer> res= new ArrayList<>();
		
		if(!isConnectedTo(v)) return res;
		
		int cur_vetrex=v;
		
		while(cur_vetrex!=s) {
			
			res.add(cur_vetrex);
			
			cur_vetrex=from[cur_vetrex];
		}
		
		res.add(s);
		
		Collections.reverse(res);
		
		return res;
	}
	
	
	private boolean dfs_cycle(int v,int parent) {
		
		
		visited[v]=true;
		
		//from[v]=parent;
		
		for(int i:graph.adj(v)) {
			
			if(!visited[i]) {
				
			//if(from[i]==-1)  // encounter the ring, then quit 
				
				 if (dfs_cycle(i,v)) return true;  // appear the exception 
			
			}
				 //  if return dfs_cycle(i,v) k>i will never be detected !
			
				// i is false but k>i might be true;
		
			else if(i!=parent)  // i vetrex is from v's parent
			
				return true;
		}
		
		return false;
		
	}
	
	private boolean dfs_cycle(int v) {
		
		visited[v]=true;
		
		for(int i:graph.adj(v)) {
			
			if(!visited[i]) {
				
				from[i]=v;
				
				if(dfs_cycle(i)) return true;
			}
			
			else if(from[v]!=i) return true;
		}
		
		return false;
		
	}
	
	public static void main(String[] args) {
		
		 String fileName3="E:\\iotest\\testG3.txt";
			
		 SparseGraph_tree_map g3=new SparseGraph_tree_map(7,false);
		        
		 new Read_Graph(g3,fileName3);
		    
		 g3.show();
		 
		 Single_source_path2 ssp2 = new Single_source_path2(g3,0);
		 
		System.out.println(ssp2.path_v2(6)+" "+ssp2.dis(6));
		 
		System.out.println(ssp2.path(6)+" "+ssp2.dis(6));
		 
		
		//System.out.println(ssp2.dfs_cycle(0, 0));
		
		System.out.println(ssp2.Cycle_detection());

	}

}
