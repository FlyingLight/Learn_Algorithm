/**
 * 
 */
package grah_learn;


import java.util.HashSet;
import java.util.Set;


/**
 * @author qiguangqin
 *
 */
public class CutPoint_Judge_Test {

	/**
	 * @param args
	 */
	
	private Graph graph;
	
	private boolean[] visited;
	
	private int[] ord; // DFS order of the each Vertex in graph 
	
	private int[] low; // lowest ord 
	
	private int count;
	
	private Set<Integer>res; // to prevent the repeat of the vertex
	
	public CutPoint_Judge_Test(Graph graph) {
		
		this.graph=graph;
		
		this.visited= new boolean[graph.V()];
		
		res= new HashSet<Integer>();
		
		this.ord= new int[graph.V()];
		
		this.low= new int[graph.V()];
		
	}
	
	public void get_all_CutPoint() {
		
		for(int v=0;v<graph.V();v++) {
			
			if(!visited[v])
				
				dfs_CutPoint(v,v);
		}
	}
	
	private void dfs_CutPoint(int v,int parent){
		
		ord[v]=count;
		
		low[v]=count;
		
		count++;
		
		visited[v]=true;
		
		int child=0;
		
		for(int w:graph.adj(v)) {
			
			if(!visited[w]) {
				
				child+=1; //  pre_order to update the child value;
				
				dfs_CutPoint(w,v);
				
				low[v]=Math.min(low[w], low[v]);
				
				if(v!=parent && low[w]>=ord[v]) {   // v is not the root vertex
					
					res.add(v);
					
				}
				
				//child+=1; // post_order to update the child value
				
				if(v==parent && child>=2) {
					
					res.add(v);
					
				}
				
			}
			
			else if (w!=parent){
				
				low[v]=Math.min(low[w], low[v]); // The condition that gets the loop the Graph
			}
			
		}
	}
	
	public Set<Integer>get_res() {
		
		return res;
		
	}
	
	public static void main(String[] args) {
		
		String fileName4="E:\\iotest\\testG4.txt";
		
		SparseGraph_tree_map g4=new SparseGraph_tree_map(7,false);
	        
	    new Read_Graph(g4,fileName4);
	    
	    g4.show();
	    
	    CutPoint_Judge_Test cpt1 = new CutPoint_Judge_Test(g4);
	    
	    cpt1.get_all_CutPoint();
	    
	    System.out.println("The Cut Points of Graph4="+cpt1.get_res());
	    
		System.out.println("---------------------------------------");
	    
		String fileName6="E:\\iotest\\testG6.txt";
		
		SparseGraph_tree_map g6=new SparseGraph_tree_map(12,false);
		   
	    new Read_Graph(g6,fileName6);
	    
	    g6.show();
	    
	    CutPoint_Judge_Test cpt2 = new CutPoint_Judge_Test(g6);
	    
	    cpt2.get_all_CutPoint();
	    
	    System.out.println("The Cut Points of Graph6="+cpt2.get_res());
	    
		System.out.println("---------------------------------------");
		
		String fileName7="E:\\iotest\\testG7.txt";
		
		SparseGraph_tree_map g7=new SparseGraph_tree_map(7,false);
		   
	    new Read_Graph(g7,fileName7);
	    
	    g7.show();
	    
	    CutPoint_Judge_Test cpt3 = new CutPoint_Judge_Test(g7);
	    
	    cpt3.get_all_CutPoint();
	    
	    System.out.println("The Cut Points of Graph7="+cpt3.get_res());

	}

}
