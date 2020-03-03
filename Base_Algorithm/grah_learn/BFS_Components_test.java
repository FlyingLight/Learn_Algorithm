/**
 * 
 */
package grah_learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author qiguangqin
 *
 */
public class BFS_Components_test {

	/**
	 * @param args
	 */
	
	private Graph graph;
		
	private int [] visited;
	
	private List<Integer>res;
	
	private int ccount=0;;
	
	
	public BFS_Components_test(Graph graph) {
		
		this.graph=graph;
		
		visited= new int[graph.V()];
		
		Arrays.fill(visited, -1);
		
		res= new ArrayList<>();
		
		//BFS(0);//  one Components
		
		
		/*
		 * 
		 * If there is multiple connection 
		 * */
		
		
		for(int v=0;v<graph.V();v++) {
			
			//if(!visited[v]) BFS(v);
			
			if(visited[v]==-1) {
				
				
				BFS(v,ccount); ccount++ ;
				
			
			}  // bfs could be also be used to calculate ccount;
		}
		
		
		//BFS(s);
		
	}


	private void BFS(int s,int ccount) {  //  start the BFS from the vertrex v 
		
		
		Queue<Integer> queue=new LinkedList<>();
		
		visited[s]=ccount;
		
		queue.add(s);
		
		while(!queue.isEmpty()) {
			
			int v=queue.remove();
			
			res.add(v);
			
			for(int w:graph.adj(v)) {
				
				//if(!visited[w]) {
				
					if(visited[w]==-1) {
									
						queue.add(w);
					
						visited[w]=ccount;
					
					}
				
						//}
			}
		}
		
	}
	
	
	public void get_visted() {
		
		for(int i:visited)
			
			System.out.print(i+" ");
	}
	

	
	private void validate_Vetrex(int v) {
		
		
		if(v<0 || v>=graph.V()) throw new IllegalArgumentException("Vetrex"+v+" is invalid");
	}
	
	public boolean isConnected(int s,int t) {
		
		validate_Vetrex(t);
		
		return visited[t]==visited[s];
		
	}
	

	
	
	public static void main(String[] args) {
		
		String fileName1="E:\\iotest\\testG1.txt";
		
		SparseGraph_tree_map g1=new SparseGraph_tree_map(13,false);
	        
	    new Read_Graph(g1,fileName1);
	    
	    g1.show();
	    
	   
	    
	    BFS_Components_test bpt1=new  BFS_Components_test(g1);
	    
	    System.out.println("The BFS of G1="+bpt1.res);
	    
	    System.out.println("The ccount of G1="+bpt1.ccount);
	    
	    bpt1.get_visted();
	    
	    System.out.println(" ");
	        
	    System.out.println("--------------------------------------------------------------");
	    

	    String fileName3="E:\\iotest\\testG3.txt";
		
		SparseGraph_tree_map g3=new SparseGraph_tree_map(7,false);
	        
	    new Read_Graph(g3,fileName3);
	    
	    g3.show();
	    
	    BFS_Components_test bpt3=new BFS_Components_test(g3);
	    
	    System.out.println("The BFS of G3="+bpt3.res);
	    
	    System.out.println("The ccount of G1="+bpt3.ccount);
	    
	    bpt3.get_visted();
	    
	 
	  

	}

}
