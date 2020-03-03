/**
 * 
 */
package graph2_weight_learn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author qiguangqin
 *
 */
public class Kruskal_test {
	
	private Graph graph; // WeightedGraph graph;
	
	private List<WeightedEdge> mst;//  Minimum Spanning Tree
	
	// By using Kruskal_Method
	

	public Kruskal_test(Graph graph) {
		
		this.graph = graph;
		
		mst= new ArrayList<>();
		
		CC cc1= new CC(graph);
		
		if(cc1.get_ccount()>1) return;
		
		// Kruskal
		
		ArrayList<WeightedEdge> edges=new ArrayList<>();
		
		for(int v=0;v<graph.V();v++) 
			
			for(int w:graph.adj(v)) 
				
				if(v<w) 
					
					edges.add(new WeightedEdge(v,w,graph.getWeight(v, w)));
					
		Collections.sort(edges);
					
		// Judge if there is loop in the graph
					
		Union_Find_V2 uf2 = new Union_Find_V2(graph.V());
					
		for(WeightedEdge edge:edges) {
						
			int v=edge.getV();
						
			int w=edge.getW();
						
			if(!uf2.isConnected(v, w)) {
							
				mst.add(edge);
							
				uf2.unionElements(v, w);
			}
						
				}
		
	}

	public List<WeightedEdge> result(){
		
		return mst;
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		String fileName1="E:\\iotest\\Weighted_Graph_1.txt";

		SparseGraph_tree_map wp1= new SparseGraph_tree_map(7,false);
		
		new Read_Graph(wp1,fileName1);
		
		//wp1.show();
		
		System.out.print(wp1);
		
		Kruskal_test kt= new Kruskal_test(wp1);
		
		System.out.println("Kruskal Minimum Spanning Tree="+kt.result());
	}

}
