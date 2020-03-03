/**
 * 
 */
package graph2_weight_learn;

import java.util.Arrays;




/**
 * @author qiguangqin
 *
 */
public class Floyed_Method_Test {

	/**
	 * @param args
	 */
	
	private Graph graph;
	
	private int[][]dis; // multi-s  vs  multi-t
	
	private boolean HasNegativeCycle;
	
	
	public Floyed_Method_Test(Graph graph) {
		
		this.graph=graph;
		
		dis= new int[graph.V()][graph.V()];
		
		for(int i=0;i<graph.V();i++)
		
		Arrays.fill(dis[i], Integer.MAX_VALUE);
		
		for(int v=0;v<graph.V();v++) {
			
			dis[v][v]=0;
		
			for(int w:graph.adj(v)) {
				
				dis[v][w]=graph.getWeight(v, w);
				
			}
			
		}
		
		_run_floyed();		
	}
	
	
	private void _run_floyed() {
		
		//Use Dynamic Programming  to judge  (u,k)[use vertex but not k]  +(k,v) [use vertex but not k] < (u,v)[use vertex but not k] 
		
		// Update 
		
		for(int t=0;t<graph.V();t++)
			
			for(int v=0;v<graph.V();v++)
				
				for(int w=0;w<graph.V();w++)
					
					if( dis[v][t]!=Integer.MAX_VALUE && dis[t][w]!=Integer.MAX_VALUE 
					
							&&dis[v][t]+dis[t][w]<dis[v][w])
						
						dis[v][w]=dis[v][t]+dis[t][w];
		
		
		// Judge Negative Cycle
		
		for(int i=0;i<graph.V();i++)
			
			if(dis[i][i]<0) HasNegativeCycle=true;
		
	}
	
	public boolean isConnected(int v,int w) {
		
		graph.validate_Vetrex(v);
		
		graph.validate_Vetrex(w);
		
		return dis[v][w]!=Integer.MAX_VALUE;
		
	}
	
	
	public int disTo(int v,int w) {
		
		graph.validate_Vetrex(v);
		
		graph.validate_Vetrex(w);
		
		return dis[v][w];
	}
	
	public static void main(String[] args) {
		
		
		String fileName1="E:\\iotest\\Weighted_Graph_dijkstra.txt";

		SparseGraph_tree_map wp1= new SparseGraph_tree_map(5,false);
		
		new Read_Graph(wp1,fileName1);
		
		//wp1.show();
		
		 System.out.print(wp1);
		
		 Floyed_Method_Test fmt= new  Floyed_Method_Test(wp1);
		 
		 if(!fmt.HasNegativeCycle) {
		 
			 for(int v=0;v<wp1.V();v++) {
				 
				 for(int w=0;w<wp1.V();w++) {
					 
					 System.out.print(fmt.dis[v][w]+" ");
			 
		 }
		 
				 System.out.println(" ");
		 
		 }
			 
		 }
		 
		 else {
			 
			 System.out.println("has negative cycle");
			 
		 }

	}

}
