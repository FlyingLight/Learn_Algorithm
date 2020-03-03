/**
 * 
 */
package graph2_weight_learn;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiguangqin
 *
 */
public class All_Weighted_Shorted_Path {

	/**
	 * @param args
	 */
	
	private Dijkstra_Method_Test[] pathes; // Every Source Point Constructed as an Array 
	
	private Graph graph;
	
	public All_Weighted_Shorted_Path(Graph graph) {
		
		this.graph=graph;
		
		this.pathes=new Dijkstra_Method_Test[graph.V()];
		
		for(int v=0;v<graph.V();v++) {
			
			pathes[v]=new Dijkstra_Method_Test(graph,v);
		}
		
	}
	
	public boolean isConnectedTo(int s,int t) {
		
		graph.validate_Vetrex(s);
		
		return pathes[s].isConnectedTo(t);
	}
	
	
	public Iterable<Integer>get_path(int s,int t){
		
		if(isConnectedTo(s, t)) {
			
			return pathes[s].path(t);
		}
		
		else {
			
			List<Integer> list = new ArrayList<>();
			
			return list;
			
		}
		
	}
	
		
	public int dis(int s,int t) {
		
		graph.validate_Vetrex(s);
		
		return pathes[s].disTo(t);
		
	}
	
	
	
	public static void main(String[] args) {
	
		String fileName1="E:\\iotest\\Weighted_Graph_dijkstra.txt";

		SparseGraph_tree_map wp1= new SparseGraph_tree_map(5,false);
		
		new Read_Graph(wp1,fileName1);
		
		//wp1.show();
		
		System.out.print(wp1);
		
		All_Weighted_Shorted_Path aws =new  All_Weighted_Shorted_Path(wp1);
		 
		System.out.println(aws.get_path(0, 3)+" "+aws.dis(0, 3));

	}

}
