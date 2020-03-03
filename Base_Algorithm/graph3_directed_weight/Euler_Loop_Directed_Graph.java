/**
 * 
 */
package graph3_directed_weight;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import grah_learn.Edge;





/**
 * @author qiguangqin
 *
 */
public class Euler_Loop_Directed_Graph {

	/**
	 * @param args
	 */
	
	private Graph graph;
	
	private List<Integer>res;
	
	private ArrayList<ArrayList<Edge>>res2;
	
	private boolean isLoop;
	
	private int special_w=0;
		
	
	public Euler_Loop_Directed_Graph(Graph graph) {
		
		this.graph = graph;
	}


	public boolean HasEulerLoop() {
		
		if(!graph.isDirected()) throw new IllegalArgumentException("only worked in non_directed Euler_Loop judge");
		
		for(int v=0;v<graph.V();v++) {
			
			if(graph.indegree(v)!=graph.outdegree(v)) 
				
				return false;
				
		}
		
		return true;	
	}
	
	
	public List<Integer> get_EulerLoop_directed_graph() {
		
		Stack<Integer> stack = new Stack<>();
		
		List<Integer> res= new ArrayList<>();
		
		if(!this.HasEulerLoop())  return res;
		
		int cur_vertex=0;
		
		stack.push(cur_vertex);
		
		while(!stack.isEmpty()) {
			
			if(graph.outdegree(cur_vertex)!=0) {
				
			stack.push(cur_vertex);
			
			int w=graph.adj(cur_vertex).iterator().next();
			
			graph.removeEdge(cur_vertex, w);
			
			cur_vertex=w;
			
			}
			
			else {
			
				res.add(cur_vertex);
				
				cur_vertex=stack.pop();
			}
			
		}
		
		Collections.reverse(res);
		
		return res;
		
	}
	
	
	public static void main(String[] args) {
		
		String fileName1="E:\\iotest\\Test_Euler_Graph10.txt";

		Sparse_Weighted_Graph_tree_map_directed wp1= new Sparse_Weighted_Graph_tree_map_directed(5,true);
		
		new Read_Graph(wp1,fileName1);
		
		//wp1.show();
		
		System.out.println(wp1);
		
		Euler_Loop_Directed_Graph eldg= new Euler_Loop_Directed_Graph(wp1);
		
        System.out.println(eldg.get_EulerLoop_directed_graph());
        
      

	}

}
