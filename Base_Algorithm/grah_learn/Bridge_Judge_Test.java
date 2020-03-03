/**
 * 
 */
package grah_learn;

import java.util.ArrayList;


/**
 * @author qiguangqin
 *
 */
public class Bridge_Judge_Test {

	/**
	 * @param args
	 */
	
	private Graph graph;
	
	private boolean[] visited;
	
	private int[] ord; // DFS order of the each Vertex in graph 
	
	private int[] low; // lowest ord 
	
	private int count;
	
	private ArrayList<Edge>res;
	
	public  Bridge_Judge_Test(Graph graph) {
		
		this.graph=graph;
		
		this.visited= new boolean[graph.V()];
		
		res= new ArrayList<Edge>();
		
		this.ord= new int[graph.V()];
		
		this.low= new int[graph.V()];
		
		
		
	}
	
	public void get_all_bridge() {
		
		for(int v=0;v<graph.V();v++) {
			
			if(!visited[v])
				
				dfs_bridge(v,v);
		}
		

		
	}
	
	private void dfs_bridge(int v,int parent){
		
		ord[v]=count;
		
		low[v]=count;
		
		count++;
		
		visited[v]=true;
		
		for(int w:graph.adj(v)) {
			
			if(!visited[w]) {
				
				dfs_bridge(w,v);
				
				low[v]=Math.min(low[w], low[v]);
				
				if(low[w]>ord[v]) {
					
					res.add(new Edge(w,v));
				}
				
			}
			
			else if (w!=parent){
				
				low[v]=Math.min(low[w], low[v]); // The condition that gets the loop the Graph
			}
			
		}
	}
	
	public ArrayList<Edge>get_res() {
		
		return res;
		
	}
	
	public static void main(String[] args) {
		
		String fileName4="E:\\iotest\\testG4.txt";
		
		SparseGraph_tree_map g4=new SparseGraph_tree_map(7,false);
	        
	    new Read_Graph(g4,fileName4);
	    
	    g4.show();
	    
	    Bridge_Judge_Test bjt1 = new Bridge_Judge_Test(g4);
	    
	    bjt1.get_all_bridge();
	    
	    System.out.println("The Bridge of Graph4="+bjt1.get_res());
	    
	    System.out.println("---------------------------------------");
	    
		String fileName6="E:\\iotest\\testG6.txt";
		
		SparseGraph_tree_map g6=new SparseGraph_tree_map(12,false);
		   
	    new Read_Graph(g6,fileName6);
	    
	    g6.show();
	    
	    Bridge_Judge_Test bjt2 = new Bridge_Judge_Test(g6);
	    
	    bjt2.get_all_bridge(); 
	    
	   System.out.println("The Bridge of Graph6="+bjt2.get_res());
	   
	   System.out.println("---------------------------------------");
	   
	   String fileName7="E:\\iotest\\testG7.txt";
		
	   SparseGraph_tree_map g7=new SparseGraph_tree_map(7,false);
		   
	    new Read_Graph(g7,fileName7);
	    
	    g7.show();
	    
	    Bridge_Judge_Test bjt3 = new Bridge_Judge_Test(g7);
	    
	    bjt3.get_all_bridge(); 

		System.out.println("The Bridge of Graph7="+bjt3.get_res());
	}

}
