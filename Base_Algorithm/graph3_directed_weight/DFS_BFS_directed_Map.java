/**
 * 
 */
package graph3_directed_weight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



/**
 * @author qiguangqin
 *
 */
public class DFS_BFS_directed_Map {

	/**
	 * @param args
	 */
	
	/*
	 In the Directed Map
	 
	 Not to consider component connection 
	 
	 bi_graph
	 
	 bridge and cut-point 
	 
	 minimum spanning tree
	 
	 flood-fill
	 
	 */
	
	private Graph graph;
	
	private boolean[]visited;
	
	private List<Integer> pre,post;
	
	private boolean[] onPath;
	
	private int[]from;
	
	private int s;
	
	private class min_path{
		
		//private int v;
		
		private int shortest_distance;
		
		private Iterable<Integer>path;
		
		public min_path(int shortest_distance,Iterable<Integer>path) {
			
			//this.v=v;
			
			this.shortest_distance=shortest_distance;
			
			this.path=path;
			
			
		}
		
		@Override
		public String toString() {
			
			StringBuilder sb = new StringBuilder();
			
			sb.append(String.format("Shortest distance=%d\n", shortest_distance));
			
			sb.append("[");
			
			for(int w:path) {
				
				sb.append(String.format("%d ", w));
			}
			
			sb.append("]");
			
			return sb.toString();
			
		}
		
	}

	public DFS_BFS_directed_Map(Graph graph,int s) {
		 
		 this.graph=graph;
		 
		 this.visited= new boolean[graph.V()];
		 
		 pre= new ArrayList<>();
		 
		 post= new ArrayList<>();
		 
		 //this.s=s;
		 
		 onPath= new boolean[graph.V()];
		 
		 from = new int [graph.V()];
		 
		 Arrays.fill(from, -1);
		 
		 this.s=s;
		 	 
	 }
	 
	 
	 public void dfs_directed_pre(int v) {
		 
		 visited[v]=true;
		 
		 pre.add(v);
		 
		 for(int w:graph.adj(v)) {
			 
			 if(!visited[w])  dfs_directed_pre(w);
			 
		 }
		 
	 }
	 
	 public void dfs_directed_post(int v) {
		 
		 visited[v]=true;
		 
		 for(int w:graph.adj(v)) {
			 
			 if(!visited[w])  dfs_directed_post(w);
		 }
		 
		 post.add(v);
		 
	 }
	 
	 
	 public void bfs_directed(int s) {
		 
		 Queue<Integer> queue= new LinkedList<>();
		 
		 visited[s]=true;
		 
		 queue.add(s);
		 
		 while(!queue.isEmpty()) {
			 
			 int v= queue.remove();
			 
			 pre.add(v);
			 
			 //visited[v]=true;
			 
			 for(int w:graph.adj(v)) {
				 
				 if(!visited[w]) {
					 
					 visited[w]=true;
					 
					 queue.add(w);
				 }
			 }
			
		 }
		 
	 }
	 
	 public boolean dfs_directed_cycle(int v,int parent) {
		 
		 // Visited is not enough
		 
		if(!graph.isDirected()) {throw new IllegalArgumentException("only worked in directed graph");}
		 
		 visited[v]=true;
		 
		 onPath[v]=true;
		 
		 from[v]=parent;
		 
		 for(int w:graph.adj(v)) {
			 
			 if(from[w]==-1) {
				 
				 if(dfs_directed_cycle(w,v)) 
					 
					 return true;
				 }
				 
			else if(onPath[w])  return true;
			 
			 	// else if(this.onPaht[w]==true && w!=parent)  // 1-->0  0--->1 two path
		 }
		 
		 this.onPath[v]=false;
		 
		 return false;
	 }
	 
	 public min_path get_shorted_path_dijkstra(int des) {
		 
		 Dijkstra_Method_Test dmt = new Dijkstra_Method_Test(graph,this.s);
		 
		 min_path mp = new min_path(dmt.disTo(des),dmt.path(des));
			 
		 return mp;
	 }
	 
	 public min_path get_shorted_path_bellmanFord(int des) {
		 
		 BllmanFord_Method_Test bmt = new BllmanFord_Method_Test(graph,s);
		 
		 min_path mp = new min_path(bmt.disTo(des),bmt.path(des));
		 
		 return mp;
	 }
	 
	 public int[][] get_shorted_path_floyed() {
		 
		 Floyed_Method_Test fmt= new Floyed_Method_Test(graph);
		 
		 if(!fmt.get_hasCycle())
		 
			 return fmt.getDis();
		 
		 throw new IllegalArgumentException(" Has Negative Cycle and exit");
		 
	 }
	 
	  public List<Integer> getPre() {
		  
			return pre;
		}


		public List<Integer> getPost() {
			
			return post;
		}
	 
		public boolean[] getVisited() {
			return visited;
		}


	public static void main(String[] args) {

		String fileName1="E:\\iotest\\simple_wg1.txt";

		Sparse_Weighted_Graph_tree_map_directed wp1= new Sparse_Weighted_Graph_tree_map_directed(3,true);
		
		new Read_Graph(wp1,fileName1);
		
		//wp1.show();
		
		System.out.println(wp1);
		
		int s=0;
		
		int des=2;
		
		DFS_BFS_directed_Map dbdm= new DFS_BFS_directed_Map(wp1,s);
		
		//dbdm.dfs_directed_pre(s);
		
		//dbdm.dfs_directed_post(s);
		 
		dbdm.bfs_directed(s);
		
		System.out.println("BFS Result="+dbdm.pre);
			
		boolean hasCycle= dbdm.dfs_directed_cycle(0, 0);
		
		//System.out.println(dbdm.post);
		
		System.out.println("Graph Has Cycle or not="+hasCycle);
		
		//System.out.println(dbdm.get_shorted_path_bellmanFord(des));
		
		System.out.println("--------------------------");
		
		for(int i=0;i<dbdm.graph.V();i++) {
			
			for(int j=0;j<dbdm.graph.V();j++) {
				
				System.out.print(dbdm.get_shorted_path_floyed()[i][j]+" ");
		
		}
			System.out.println();
			
		}
		
	}

}
