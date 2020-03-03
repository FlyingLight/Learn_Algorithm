/**
 * 
 */
package graph3_directed_weight;

import java.util.ArrayList;
import java.util.List;



/**
 * @author qiguangqin
 *
 */
public class Longest_Path_back_track {

	/**
	 * @param args
	 */
	
	private Graph graph;
	
	private int s,d;

	private int res=0;
	
	private ArrayList<Integer>P;
	
	public Longest_Path_back_track(Graph graph,int s,int v) {
		
		this.graph=graph;
		
		this.s=s;
		
		this.d=v;
		
		this.P=new ArrayList<>();
		
	}
	
	public void get_back_track() {
		
		DFS_BFS_directed_Map dbf= new DFS_BFS_directed_Map(graph,s);
		
		if(dbf.dfs_directed_cycle(s, s)) throw new IllegalArgumentException("cycle graph exception");
		
		List<Integer>path=new ArrayList<>();
		
		path.add(s);
		
		back_track(graph,0,0,path,0);
	}
	
	private void back_track(Graph graph,int parent,int v,List<Integer> path,int path_count) {
		
		//System.out.println("das");
		
		if(v!=s) {
			
			//System.out.println(v);
		
		path_count+=graph.getWeight(parent, v);
		
		path.add(v);
		
		}
		
		if(v==this.d) { 
			
			if(path_count>res) {
				
				this.P=new ArrayList<Integer>(path);
				
				res=path_count;
				
			}
			
			return ;
			
		}
			
		for(int w:graph.adj(v)) {
			
			//System.out.println("pp");
			
			back_track(graph,v,w,path,path_count);
				
			path.remove(path.size()-1);
			
		}
		
	}
	
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String fileName1="E:\\iotest\\DAG_longest_1.txt";

		Sparse_Weighted_Graph_tree_map_directed wp1= new Sparse_Weighted_Graph_tree_map_directed(6,true);
		
		new Read_Graph(wp1,fileName1);
		
		//wp1.show();
		
		System.out.print(wp1);
		
		Longest_Path_back_track  lpbt = new Longest_Path_back_track(wp1,0,5);
		
		lpbt.get_back_track();
		
		System.out.println(lpbt.res+" "+lpbt.P);
	
	
	}

}
