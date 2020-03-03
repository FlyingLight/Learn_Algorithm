/**
 * 
 */
package graph3_directed_weight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author qiguangqin
 *
 */
public class SCC_Kosaraju {

	/**
	 * @param args
	 */
	
	private Graph graph;
	
	private int[] visited; // Each vertex(AS index i) --->SCC id
	
	private int scc_count=0;
	
	
	public SCC_Kosaraju(Graph graph) {
		
		if(!graph.isDirected())
			
			throw new IllegalArgumentException("SCC only worked in Directed Graph");
		
		this.graph=graph;
		
		visited= new int[graph.V()];
		
		Arrays.fill(visited, -1);
		
	}
	
	public boolean isStronglyConnected(int v,int w) {
		
		graph.validate_Vetrex(v);
		
		graph.validate_Vetrex(w);
		
		return visited[v]==visited[w];
	}
	
	
	public List<Integer>[] Components(){
		
		@SuppressWarnings("unchecked")
		
		List<Integer>[]res= new ArrayList[this.scc_count];
		
		for(int i=0;i<this.scc_count;i++)
			
			res[i]=new ArrayList<Integer>();
		
		for(int v=0;v<graph.V();v++)
			
			res[visited[v]].add(v);
				
		return res;	
	}
	
	public int get_Res() {
		
		return _get_Res();
	}
	
	private int _get_Res() {
		
		//List<Integer> res= new ArrayList<>();
		
		for(int v:reverse_graph_dfs_post()) {
			
			if(visited[v]==-1) {
				
				dfs_scid(v,scc_count);
				
				scc_count++;
			}
		}
		
		return scc_count;
	}
	
	
	private List<Integer>reverse_graph_dfs_post(){
		
		Graph reverse_graph= graph.reverseGraph();
		
		List<Integer> res= new ArrayList<>();
		
		DFS_BFS_directed_Map dbdm= new DFS_BFS_directed_Map (reverse_graph,0);
		
		for(int v=0;v<reverse_graph.V();v++) {
			
			if(!dbdm.getVisited()[v]) {
			
				dbdm.dfs_directed_post(v); //  consider one scc
				
				res=dbdm.getPost();
			
			}
		}
		
		Collections.reverse(res);
		
		return res;
	}
	
	private void dfs_scid(int v,int sccid) {
		
		visited[v]=sccid;
		
		for(int w:graph.adj(v))
			
			if(visited[w]==-1)
				
				dfs_scid(w,sccid);
	}

	
	public int count() {
		
		return this.scc_count;
	}

	
	public static void main(String[] args) {
		
		String fileName1="E:\\iotest\\Directed_SCC_kosaraju_Graph.txt";

		Sparse_Weighted_Graph_tree_map_directed wp1= new Sparse_Weighted_Graph_tree_map_directed(5,true);
		
		new Read_Graph(wp1,fileName1);
		
		System.out.print(wp1+"\n");
		
		SCC_Kosaraju sck = new SCC_Kosaraju(wp1);
		
		int num=sck.get_Res();
		
		System.out.println("Nums of SCC="+num);
		
		List<Integer>[]comp=sck.Components();
		
		for(int sccid=0;sccid<comp.length;sccid++) {
			
			System.out.print(sccid + " : ");
			
			for(int w:comp[sccid])
				
				System.out.print(w+" ");
			
			System.out.println();
		}
		
	}

}
