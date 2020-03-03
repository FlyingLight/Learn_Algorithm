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
public class BFS_Path_test {

	/**
	 * @param args
	 */
	
	private Graph graph;
	
	private int[] from;
	
	//private boolean[] visited;
	
	private int [] colors;
	
	private List<Integer>res;
	
	private int[] distance;
	
	private int s;
	
	
	public BFS_Path_test(Graph graph,int s) {
		
		this.graph=graph;
		
		this.s=s;
		
		//visited= new boolean[graph.V()];
		
		from= new int[graph.V()];
		
		distance= new int[graph.V()];
		
		colors= new int[graph.V()];
		
		Arrays.fill(from, -1);
		
		Arrays.fill(colors, -1);
		
		Arrays.fill(distance, -1);
		
		res= new ArrayList<>();
		
		//BFS(0);//  one Components
		
		
		/*
		 * 
		 * If there is multiple connection 
		 * 
		for(int v=0;v<graph.V();v++) {
			
			//if(!visited[v]) BFS(v);
			
			if(from[v]==-1) BFS(v);
		}
		*/
		
		BFS(s);
		
	}

	public boolean cycle_detection_single_conn() {
		
		for(int v=0;v<graph.V();v++) {
			
			//if(!visited[v]) {
			
			//if(from[v]==-1) {
				
				if(this.bfs_cyle(v)) return true;
			//}
		}
		
		return false;
	}
	
	public boolean cycle_detection_multi_conn() {
		
		for(int v=0;v<graph.V();v++) {
			
			//if(!visited[v]) {
			
			if(from[v]==-1) {
				
				if(bfs_cyle(v)) return true;
			}
		}
		
		return false;
	}
	
	private void BFS(int s) {  //  start the BFS from the vertrex v 
		
		
		Queue<Integer> queue=new LinkedList<>();
		
		//visited[s]=true;
		
		from[s]=s;
		
		queue.add(s);
		
		while(!queue.isEmpty()) {
			
			int v=queue.remove();
			
			res.add(v);
			
			for(int w:graph.adj(v)) {
				
				//if(!visited[w]) {
				
					if(from[w]==-1) {
									
						queue.add(w);
					
						from[w]=v;
					
						//visited[w]=true;
					
					}
				
						//}
			}
		}
		
	}
	
	public int dis(int t) {
		
		validate_Vetrex(t);
		
		BFS_dis(s);
		
		return distance[t];
	}
	
	private void BFS_dis(int s) {
		
		Queue<Integer> queue= new LinkedList<>();
		
		queue.add(s);
		
		//from[s]=s;
		
		distance[s]=0;// s->s  initiate the distance ==0 
		
		while(!queue.isEmpty()) {
			
			int v=queue.remove();
			
			//res.add(v);
			
			for(int w:graph.adj(v)) {
				
				if(distance[w]==-1) {
					
					queue.add(w);
					
					distance[w]=distance[v]+1;
				}
			}
		}
	}
	
	
	private boolean bfs_cyle(int s) {  //  start the BFS from the vertrex v 
		
		
		Queue<Integer> queue=new LinkedList<>();
		
		//visited[s]=true;
		
		from[s]=s;
		
		queue.add(s);
		
		while(!queue.isEmpty()) {
			
			int v=queue.remove();
			
			res.add(v);
			
			for(int w:graph.adj(v)) {
				
			//	if(!visited[w]) {
				
				if(from[w]==-1) {
								
					queue.add(w);
					
					from[w]=v;
					
				//	visited[w]=true;
			
				}
				
				else if(from[v]!=w)   // from[w]!=-1 &&from[v]!=w
					
					return true;
				
			}
		}
		
		return false;
		
	}
	
	public boolean decide_bi_graph() {
		
		
		for(int w=0;w<graph.V();w++) {
			
			if(colors[w]==-1) {
				
				//  All the point(components) being watched ,one is the not  return false  ,one point yes need to watch other points to judege
				
				if(!Is_Bi_Graph(w, 0)) return false;
			}
			
		}
		
		return true;
	}
	
	
	private boolean Is_Bi_Graph(int s,int color) {  // iterating not the recursion
		
		
		Queue<Integer> queue = new LinkedList<>();
		
		colors[s]=color;   // The color of the initial s point is "0"
		
		queue.add(s);
		
		while(!queue.isEmpty()) {
				
			int v= queue.remove();
			
			for(int w:graph.adj(v)) {
				
				if(colors[w]==-1) {
					
					queue.add(w);
					
					colors[w]=1-colors[v];  // In this cycle to decide the color the point
				}
				
				else if(colors[w]==colors[v])
					
					return false;
			}
		}
		
		return true;
		
	}
	
	private void validate_Vetrex(int v) {
		
		
		if(v<0 || v>=graph.V()) throw new IllegalArgumentException("Vetrex"+v+" is invalid");
	}
	
	public boolean isConnectedTo(int t) {
		
		validate_Vetrex(t);
		
		return from[t]!=-1;
		
	}
	
	public Iterable<Integer>path_v2(int t){
		
		this.validate_Vetrex(t);
		
		List<Integer> res= new ArrayList<>();
		
		int cur_vet=t;
		
		while(cur_vet!=s) {
			
			res.add(cur_vet);
			
			cur_vet=from[cur_vet];
		}
		
		res.add(s);
		
		Collections.reverse(res);
		
		return res;
	}
	
	
	
	public static void main(String[] args) {
		
		String fileName1="E:\\iotest\\testG1.txt";
		
		SparseGraph_tree_map g1=new SparseGraph_tree_map(13,false);
	        
	    new Read_Graph(g1,fileName1);
	    
	    g1.show();
	    
	    int s=0;
	    
	    BFS_Path_test bpt1=new  BFS_Path_test(g1,s);
	    
	    System.out.println("The BFS of G1="+bpt1.res);
	    
	    System.out.println("Cycel of the G1="+bpt1.cycle_detection_multi_conn());
	    
	    System.out.println("--------------------------------------------------------------");
	    

	    String fileName3="E:\\iotest\\testG3.txt";
		
		SparseGraph_tree_map g3=new SparseGraph_tree_map(7,false);
	        
	    new Read_Graph(g3,fileName3);
	    
	    g3.show();
	    
	    BFS_Path_test bpt3=new  BFS_Path_test(g3,s);
	    
	    System.out.println("The BFS of G3="+bpt3.res);
	    
	    System.out.println(bpt3.path_v2(6)+" "+bpt3.dis(3));
	    
	    System.out.println("Cycle of the G3="+bpt3.cycle_detection_single_conn());
	    
	    System.out.println("is bi_graph of the G3="+bpt3.decide_bi_graph());

	}

}
