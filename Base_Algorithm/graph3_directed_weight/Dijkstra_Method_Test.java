/**
 * 
 */
package graph3_directed_weight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author qiguangqin
 *
 */
public class Dijkstra_Method_Test {

	/**
	 * @param args
	 */
	
	private Graph graph;
	
	private int s;
	
	private int[] dis;
	
	private boolean[] visited;
	
	private int[] pre;
	
	private class Node implements Comparable<Node>{
		
		private int v,dis;
		
		public Node(int v,int dis) {
			
			this.v=v;
			
			this.dis=dis;
		}
		
		public int getV() {
			return v;
		}

		@Override
		public int compareTo(Node another) {
			
			return dis-another.dis;
		}
	}
	
	public Dijkstra_Method_Test(Graph graph,int s) {
		
		this.graph=graph;
		
		graph.validate_Vetrex(s);
		
		this.s=s;
		
		dis= new int[graph.V()];
		
		Arrays.fill(dis,Integer.MAX_VALUE); 
		
		// Max_Weight of Edge could above Integer.Max_VALUE(255---> INF+ ); 
		
		dis[s]=0;
		
		this.pre= new int[graph.V()];
		
		Arrays.fill(pre, -1);
		
		visited= new boolean[graph.V()];
		
		pre[s]=s;  // Source Vertex before is it self
		
		 _get_dijkstra(); 
		 
		// _mod_get_dijkstra();
	}
	
	private void _get_dijkstra() {
		
		// O(V^2)  Time Complexity
		
		
	//	for(int count =0;count<graph.V();count ++) {
		
		while(true) {
			
			int cur_min_dis=Integer.MAX_VALUE,cur_vex=-1;
			
			for(int v=0;v<graph.V();v++)
				
				if(!visited[v] && dis[v]<cur_min_dis) {
					
					cur_min_dis=dis[v]; 
					
					cur_vex=v;
					
				}
			
			if(cur_vex==-1) break;
			
			visited[cur_vex]=true;
			
			for(int w:graph.adj(cur_vex)) {
				
				if(!visited[w]) {
				
				int tmp_dis_w=dis[cur_vex]+graph.getWeight(cur_vex,w);
				
				if(tmp_dis_w<dis[w])
					
					dis[w]=tmp_dis_w;
				
					pre[w]=cur_vex;
				
				}
			}
			
		}
	}
	
	private void _mod_get_dijkstra() {
		
		PriorityQueue<Node>pq = new PriorityQueue();
		
		pq.add(new Node(s,0));
		
		while(!pq.isEmpty()) {
			
			int v=pq.remove().getV();  // Use Min_heap to get the each minimum dis[i]
			
			if(visited[v])  continue;
			
			visited[v]=true;
			
			for(int w:graph.adj(v)) {
				
				if(!visited[w]) {
					
					int tmp_dis_w= dis[v]+graph.getWeight(w, v);
					
					if(tmp_dis_w<dis[w]) {
						
						dis[w]=tmp_dis_w;
						
						pq.add(new Node(w,dis[w]));  // Update the Node already in Queue(New Node)
						
						// or Add the not in Queue already
						
						pre[w]=v;
					}
				}
			}
			
			
		}
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
	
	public boolean isConnectedTo(int v) {
		
		graph.validate_Vetrex(v);
		
		return visited[v];
		
	}
	
	public int disTo(int v) {
		
		graph.validate_Vetrex(v);
		
		return dis[v];
	}
	
	
	public static void main(String[] args) {
	
		
		String fileName1="E:\\iotest\\Weighted_Graph_dijkstra.txt";

		Sparse_Weighted_Graph_tree_map_directed wp1= new Sparse_Weighted_Graph_tree_map_directed(5,false);
		
		new Read_Graph(wp1,fileName1);
		
		//wp1.show();
		
		System.out.print(wp1);
		
		int s=0;
		
		Dijkstra_Method_Test dmt = new Dijkstra_Method_Test(wp1,s);
		
		for(int i=0;i<wp1.V();i++) {
			
			System.out.print(dmt.disTo(i)+" ");
			
		}
		
		System.out.println(dmt.path(3));
	
	}

}
