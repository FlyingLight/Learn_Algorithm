package graph2_weight_learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BllmanFord_Method_Test {
	
	private Graph graph;
	
	private int s;
	
	private int[] dis;
	
	private int[] pre;
	
	private boolean hasNegativeCycle;
	
	// The following data structure used for SPFA(mod_bellman_ford)
	
	private Queue<Integer> queue; 
	
	private int[]nums;
	
	public BllmanFord_Method_Test(Graph graph, int s) {
		
		this.graph = graph;
		
		graph.validate_Vetrex(s);
		
		this.s = s;
		
		this.dis= new int[graph.V()];
		
		Arrays.fill(dis, Integer.MAX_VALUE);
		
		this.pre= new int[graph.V()];
		
		dis[s]=0;
		
		Arrays.fill(pre, -1);
		
		//  Queue used as the part of SPFA(mod_bellman_ford)
		
		queue=new LinkedList<>();
		
		nums= new int[graph.V()];
		
		//_get_res_bm_method();
		
		_get_mod_bm_spfa();
	}
	
	private void _get_res_bm_method() {
		
		for(int count=1;count<graph.V();count++) {
			
			// v-1 cycle to get all distance 
			
			for(int v=0;v<graph.V();v++)   // V*E relaxation 
				
				for(int w:graph.adj(v)) 
					
					if(dis[v]!=Integer.MAX_VALUE && dis[v]+graph.getWeight(v, w)<dis[w]) {
						
						dis[w]=dis[v]+graph.getWeight(v, w);
						
						pre[w]=v;
						
					}
				
		}
		
		for(int v=0;v<graph.V();v++) 
			
			for(int w:graph.adj(v)) 
				
				if(dis[v]!=Integer.MAX_VALUE && dis[v]+graph.getWeight(v, w)<dis[w])
					
					hasNegativeCycle=true;
	}
	
	private void _get_mod_bm_spfa() {
		
		nums[s]=1;
		
		// Not all the adjacent vertex(Edge) needed to update After relaxing
		 
		queue.add(s);
		
		while(!queue.isEmpty()) {
			
			// each time, Relaxing the vertex(Connected) because needed to update
			
			int cur_vex=queue.remove();
			
			//nums[cur_vex]=nums[cur_vex]-1;
			
			for(int w:graph.adj(cur_vex)) {
				
				if( dis[cur_vex]+graph.getWeight(cur_vex, w)<dis[w] && dis[cur_vex]!=Integer.MAX_VALUE) {
					
					dis[w]=dis[cur_vex]+graph.getWeight(cur_vex, w);
					
					pre[w]=cur_vex;
					
					if(!queue.contains(w)) {
						
						queue.add(w);
						
						nums[w]++;
						
						if(nums[w]>graph.V()) {
							
							this.hasNegativeCycle=true;
							
							return ;
							
						}
								
					}
				}
		
			}
		}
		
		return ;
		
	}

	public boolean hasNegCycle() {
		
		return this.hasNegativeCycle;
	}
	
	public boolean isConnectedTo(int v) {
		
		graph.validate_Vetrex(v);
		
		return dis[v]!=Integer.MAX_VALUE;
	}
	
	public int disTo(int v) {
		
		graph.validate_Vetrex(v);
		
		if(this.hasNegativeCycle) throw new RuntimeException("Has Negative Cycle and Exit");
		
		return dis[v];
	}

	

	public Iterable<Integer>path(int t){
		
		ArrayList<Integer> res= new ArrayList<>();
		
		if(!isConnectedTo(t))  return res;
		
		int cur_vex=t;
		
		while(cur_vex!=s) {
			
			res.add(cur_vex);
			
			cur_vex=pre[cur_vex];
		}
		
		res.add(cur_vex);
		
		Collections.reverse(res);
		
		return res;
	}
	
	public static void main(String[] args) {
		
		String fileName1="E:\\iotest\\Weighted_Graph_dijkstra.txt";

		SparseGraph_tree_map wp1= new SparseGraph_tree_map(5,false);
		
		new Read_Graph(wp1,fileName1);
		
		//wp1.show();
		
		System.out.print(wp1);
		
		int s=0;
		
		BllmanFord_Method_Test bmt = new BllmanFord_Method_Test(wp1,s);
		
		if(!bmt.hasNegCycle()) {
			
			for(int i=0;i<wp1.V();i++)
				
				System.out.print(bmt.disTo(i)+" ");
			
			System.out.println(bmt.path(3)+" "+bmt.disTo(3));
		}
		
		else
			
			System.out.println("exit negative cycle");

	}

}
