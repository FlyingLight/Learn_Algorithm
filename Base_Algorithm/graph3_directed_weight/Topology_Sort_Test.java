/**
 * 
 */
package graph3_directed_weight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author qiguangqin
 *
 */
public class Topology_Sort_Test {

	/**
	 * @param args 
	 */
	
	private List<Integer>res;
	
	private List<Integer>res2;
	
	private boolean[] visited;
	
	private boolean hasCycle;
	
	private int[]indegrees;
	
	private Graph graph;
	
	
	public Topology_Sort_Test(Graph graph) {
		
		this.graph=graph;
		
		this.hasCycle=false;
		
		res= new ArrayList<>();
		
		res2= new ArrayList<>();
		
		this.indegrees= new int[graph.V()];
		
		visited= new boolean[graph.V()];
	}
	
	public List<Integer> get_result(){
		
		this._get_Res();
		
		return res;
	}
	
	private void _get_Res() {
		
		Queue<Integer> queue =new LinkedList<>();
		
		for(int i=0;i<graph.V();i++)  {// Record the in_degree of each vertex
			
			indegrees[i]=graph.indegree(i);
			
			if(indegrees[i]==0) queue.add(i);
			
		}
		
		while(!queue.isEmpty()) {
			
			int v=queue.remove();
			
			res.add(v);
			
			for(int w:graph.adj(v)) {
				
				indegrees[w]-=1;
				
				if(indegrees[w]==0)
					
					queue.add(w);
			}
		}
		
		if(res.size()!=graph.V()) {
			
			this.hasCycle=true;
			
			res.clear();
			
		}
		
	}
	
	public List<Integer>get_result2_dfs_result(){
		
		int s=0;
	
		DFS_BFS_directed_Map dbdm = new DFS_BFS_directed_Map(graph,s);
		
		if(!dbdm.dfs_directed_cycle(s, s)) {
		
		this._get_Res2_dfs_post(s);
		
		Collections.reverse(res2);
		
		return res2;
		
		}
		
		else 
			
			return res2;
	}
	
	
	private void _get_Res2_dfs_post(int v) {
		
		visited[v]=true;
		
		for(int w:graph.adj(v)) {
			
			if(!visited[w])
			
				this._get_Res2_dfs_post(w);
		}
		
		res2.add(v);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//String fileName1="E:\\iotest\\Directed Top_Graph.txt";
		
		String fileName1="E:\\iotest\\Directed Graph_Cycle.txt";

		Sparse_Weighted_Graph_tree_map_directed wp1= new Sparse_Weighted_Graph_tree_map_directed(5,true);
		
		new Read_Graph(wp1,fileName1);
		
		//wp1.show();
		
		System.out.println(wp1);
		
		Topology_Sort_Test tst= new Topology_Sort_Test(wp1);
		
		System.out.println(tst.get_result()+"Has Cycle or not="+tst.hasCycle);
		
		System.out.println(tst.get_result2_dfs_result()+"Has Cycle or not="+tst.hasCycle);

	}

}
