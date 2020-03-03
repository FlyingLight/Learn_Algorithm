/**
 * 
 */
package graph2_weight_learn;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author qiguangqin
 *
 */
public class Prim_Get {

	/**
	 * @param args
	 */
	
	private Graph graph;
	
	private ArrayList<WeightedEdge>mst;
	
	public Prim_Get(Graph graph) {
		
		this.graph=graph;
		
		mst=new ArrayList<>();
		
	}
	
	
	public ArrayList<WeightedEdge> get_mst(){
		
		CC cc1 = new CC(graph);
		
		if(cc1.get_ccount()>1) return mst;
		
		else {
			
			get_result();
			
			return mst;
		}
		
	}
	
	public ArrayList<WeightedEdge> get_mod_mst(){
		
		CC cc1 = new CC(graph);
		
		if(cc1.get_ccount()>1) return mst;
		
		else {
			
			mod_get_result();
			
			return mst;
		}
		
	}
	
	private void get_result() {
		
		// trim_seq represent the vertex has the cross-cutting edge
		
		// O((V+E)(V-1))=O(VE)
		
		boolean[] trim_seq= new boolean[graph.V()];
		
		trim_seq[0]=true;
		
		// find the V-1 Cross-cutting edge 
		
		for(int i=1;i<graph.V();i++) {
			
			// find the minimum Cutting_Edge
			
			WeightedEdge min_edge= new WeightedEdge(-1,-1,Integer.MAX_VALUE);
			
			for(int v=0;v<graph.V();v++) 
				
				if(trim_seq[v]) 
					
					for(int w:graph.adj(v)) 
						
						if(!trim_seq[w] && graph.getWeight(v, w)<min_edge.getWeight()) 
							
							min_edge=new WeightedEdge(v,w,graph.getWeight(v, w));
			
			mst.add(min_edge);
			
			trim_seq[min_edge.getV()]=true;
			
			trim_seq[min_edge.getW()]=true;
			
		}
	}
	
	
	private void mod_get_result() {
		
		
		boolean[] trim_seq = new boolean[graph.V()];
		
		trim_seq[0]=true;
		
		// find the minimum Cutting Edge
		
		PriorityQueue<WeightedEdge> prio_queue= new PriorityQueue<>();
		
		// initial state ---> adj(0)
		
		//O(ElogE)
		
		for(int w:graph.adj(0)) {
			
			prio_queue.add(new WeightedEdge(0,w,graph.getWeight(0, w)));
		}
		
		while(!prio_queue.isEmpty()) {
			
			WeightedEdge min_edge= prio_queue.remove();
			
			if(trim_seq[min_edge.getV()] && trim_seq[min_edge.getW()])
				
				continue; // Drop out if not the Crossing-Cutting Edge
			
			mst.add(min_edge);
			
			int vex= trim_seq[min_edge.getW()]?min_edge.getV():min_edge.getW(); // find the Non-Cutting Edge Point
			
			trim_seq[vex]=true;
			
			for(int w:graph.adj(vex)) {
				
				if(!trim_seq[w])
					
					prio_queue.add(new WeightedEdge(vex,w,graph.getWeight(vex, w)));
			}
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String fileName1="E:\\iotest\\Weighted_Graph_1.txt";

		SparseGraph_tree_map wp1= new SparseGraph_tree_map(7,false);
		
		new Read_Graph(wp1,fileName1);
		
		//wp1.show();
		
		System.out.print(wp1);
		
		Prim_Get pg = new Prim_Get(wp1);
	
		System.out.println(pg.get_mst());

	}

}
