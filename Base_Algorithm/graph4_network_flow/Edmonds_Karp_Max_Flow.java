/**
 * 
 */
package graph4_network_flow;

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
public class Edmonds_Karp_Max_Flow {

	/**
	 * @param args
	 */
	
	private Graph network;
	
	private Graph residue_graph; // the residue_graph of the network
	
	private int s,t; // s source point 
	
	private int maxFlow=0;// finally result
	
	public Edmonds_Karp_Max_Flow(Sparse_Weighted_Graph_tree_map_directed network,int s,int t) {
		
		if(!network.isDirected()) throw new IllegalArgumentException("Only worked in directed graph");
		
		if(network.V()<2) 	throw new IllegalArgumentException("Network has at least 2 vertex");
		
		network.validate_Vetrex(s);
		
		network.validate_Vetrex(t);
		
		if(s==t) throw new IllegalArgumentException("s and t must be different");
		
		this.network=network;
		
		this.s=s;
		
		this.t=t;
		
		this.residue_graph=new  Sparse_Weighted_Graph_tree_map_directed(network.V(),network.isDirected());
	
		for(int v=0;v<network.V();v++) {
			
			for(int w:network.adj(v)) {
				
				int capacity =network.getWeight(v, w);
				
				residue_graph.addEdge(v, w, capacity); // Positive direction =Max Flow
			
				residue_graph.addEdge(w, v, 0);//  positive flow=0, so there is no negative flow =0
			}
		}
	}
	
	
	public int get_res() {
		
		_get_Res();
		
		return maxFlow;
	}
	
	public int flow(int v,int w) {
		
		if(!network.hasEdge(v, w)) throw new IllegalArgumentException(String.format("No Edge %d-%d",v,w));
		
		return residue_graph.getWeight(w, v);
	}
	
	public void show_flow() {
		
		System.out.println("v-w : f / Ca.");
		
		for(int v=0;v<network.V();v++)
			
			for(int w:network.adj(v))
				
				System.out.println(String.format("%d-%d : %d / %d",v,w,flow(v,w),network.getWeight(v, w)));
	}
	
	private void _get_Res() {  // Find Augment_path in the residue graph
		
		while(true) {
			
			List<Integer> augPath= _get_augment_path();
			
			// confirm and find one augment_path
			
			if(augPath.size()==0) break;
			
			int flow=Integer.MAX_VALUE;
			
			//TODO calculate to min_value of augment_path
	
			for(int i=1;i<augPath.size();i++) {
					
				int v=augPath.get(i-1);
					
				int w=augPath.get(i);
					
				flow=Math.min(flow, residue_graph.getWeight(v, w));
			}
				
			this.maxFlow+=flow;
			
			//TODO update the residue graph according to new augment_path
				
			for(int i=1;i<augPath.size();i++) {
					
				int v=augPath.get(i-1);
					
				int w=augPath.get(i);
					
				//if(network.hasEdge(v, w)) {
						
					// positive edge
				residue_graph.setWeight(v, w,residue_graph.getWeight(v, w)-flow);
						
					// negative edge
				residue_graph.setWeight(w, v,residue_graph.getWeight(w, v)+flow);
						
						
				//}
					
				//else {
						
					//residue_graph.setWeight(w, v,residue_graph.getWeight(w, v)+flow);
						
					//residue_graph.setWeight(v, w,residue_graph.getWeight(v, w)-flow);
					
				}
			}
		}
		
	private List<Integer> _get_augment_path(){
		
		// Using BFS to get augment_path
			
		Queue<Integer> queue= new LinkedList<>();
		
		int[] from= new int[network.V()];
			
		Arrays.fill(from, -1);
			
		queue.add(s);
			 
		from[s]=s;
			
		while(!queue.isEmpty()) {
				
			int cur=queue.remove();
				
			if(cur==t) break;
				
			for(int next:residue_graph.adj(cur)) 
					
				if(from[next]==-1 && residue_graph.getWeight(cur, next)>0) {
						
					from[next]=cur;
						
					queue.add(next);
				}
		}
			
		List<Integer>res= new ArrayList<>();
			
		if(from[t]==-1) return res; // No other Augment_Path
			
		int cur=t;
			
		while(cur!=s) {
				
			res.add(cur);
				
			cur=from[cur];
		}
			
		res.add(s);
			
		Collections.reverse(res);
			
		return res;
			
		}
	

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			
			String fileName1="E:\\iotest\\Max_flow_1.txt";
			
			int vertex_sum=4,s=0,t=3;
			
			//String fileName1="E:\\iotest\\Max_flow_2.txt";

			Sparse_Weighted_Graph_tree_map_directed wp1= new Sparse_Weighted_Graph_tree_map_directed(vertex_sum,true);
			
			new Read_Graph(wp1,fileName1);
			
			System.out.print(wp1);
					
			Edmonds_Karp_Max_Flow ekmf= new Edmonds_Karp_Max_Flow(wp1,s,t);
			
			int num=ekmf.get_res();
			
			ekmf.show_flow();
			
			System.out.println("Max_Flow="+num);

		}

}
