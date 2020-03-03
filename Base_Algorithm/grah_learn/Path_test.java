/**
 * 
 */
package grah_learn;

import java.util.Vector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author qiguangqin
 *
 */
public class Path_test {
	
	
	private Graph graph;
	
	private int s;//起始的点
	
	private int[] from; // record the path and replace the function of visited[]
	
	//private boolean[] visited;
	
	//private int ccount=0;
	
	private int[] colors;  // 0 blue  1  green
	
	boolean hasCycle=false;
	
	int [] memo;
	
	int[] dis;
	
	
	public Path_test(Graph graph,int s) {  // 从一个点s，到任意点的路径
		
		
		assert s>=0 && s<graph.V();
		
		this.graph=graph;
		
		assert s>=0 && s<graph.V();
		
		//visited=new boolean[graph.V()];  
		
		from =new int[graph.V()];
		
		colors= new int[graph.V()];
		
		memo=new int[graph.V()];
		
		dis= new int[graph.V()];
		
		Arrays.fill(from, -1);
		
		Arrays.fill(colors, -1);
		
		Arrays.fill(memo,-1);
		
		Arrays.fill(dis, -1);
		
		this.s=s;
		
		dis[s]=0;
		
		// dfs_single_connect(s,-2); // -2 to mark the pre-vertex of the source point;
		 
		 //and -1 to mark vertex have not been visited
		
		//dfs_cycle(s,0 );
		
		dfs_single_connect(s,s);
		
	}
	
	public boolean Cycle_detection() {
		
		
		if(graph.isDirected()) { throw new IllegalArgumentException(" only worked in non-weighted graph");}
		
		for(int v=0;v<graph.V();v++) { // detect the different ccount of the graph
			
			if(from[v]!=-1) {
				
				if(dfs_cycle(v, v)) return true;
				
			}
		}
		
		return false;
	}
	
	/*
	private void dfs_single_connect(int v) {
		
		
		//visited[v]=true;
		
		for(int i:graph.adj(v)) 
			
			//if(!visited[i]) {
			
			if(from[i]==-1) {
			
				from[i]=v; //  i 节点都是从v 过来的
			
				dfs_single_connect(i);
			
			}
	}
	
	*/
	
	private void dfs_single_connect(int v,int parent) {
		
		from[v]=parent;
		
		for(int i:graph.adj(v)) {   //  dfs_single_connect(0,-1),dead loop
			
			if(from[i]==-1) {  dis[i]=dis[v]+1;dfs_single_connect(i,v);}
		}
	}
	
	public int dis(int t) {
		
		graph.validate_Vetrex(t);
		
		return dis[t];
		
	}
	
	private boolean dfs_cycle(int v,int parent) {
		
		
		//visited[v]=true;
		
		from[v]=parent;
		
		for(int i:graph.adj(v)) {
			
			//if(!visited[i]) 
				
			if(from[i]==-1) { // encounter the ring, then quit 
				
				 if (dfs_cycle(i,v)) return true;  // appear the exception 
				
				}
				 //  if return dfs_cycle(i,v) k>i will never be detected !
			
				// i is false but k>i might be true;
		
			else if(i!=parent)  // i vertex is from v's parent
			
				return true;
		}
		
		return false;
		
	}
	
	
	public void HasCycle_long_time(int v,int parent) {
		
		
		from[v]=parent;
		
		for(int i:graph.adj(v)) {
			
			if(from[i]==-1) {
				
				this.HasCycle_long_time(i, v);
			}
			
			else if (i!=parent) this.hasCycle=true;  // i vetrex is from v's parent
			
			// it must has a cycle
		}
		
		this.hasCycle=false;
	}
	
	
	public boolean is_bi_graph(int v,int color) {
		
		//  The color of the current vetrex
		
		colors[v]=color; // paint the current vetrex as color
		
		for(int i:graph.adj(v)) {
			
			if(colors[i]==-1) {
				
				//from[i]=v;
				
				if(!is_bi_graph(i,1-color))   // list the exception 
					
					return false;
				
			}
			
			else if (colors[i]==colors[v])
					
					return false;	
		}
		
		return true;
		
	}
	
	
	
	public boolean is_bi_graph_memo(int v,int color) {
		
		if(memo[v]!=-1) {
			
			return memo[v]==1?true:false; //memo[v]==1 is a bi_graph ,memo[v]==0 is not a bi_graph
		}
		
		//  Because of the for loop ,so can state " return memo[v]==1"
		
		colors[v]=color;
		
	for(int i:graph.adj(v)) {
			
			if(colors[i]==-1) {
				
				//from[i]=v;
				
				if(!is_bi_graph(i,1-color))   // list the exception 
					
					memo[v]=0; // check parameter v;
				
					return false;
				
				
			}
			
			else if (colors[i]==colors[v]) {
				
					memo[v]=0;
					
					return false;	
					
			}
		}
		
		return true;
	}
	
	public boolean hasPath(int w) {
		
		
		//从s到w 之间是否存在一条路径
		
		assert w>=0 && w<this.graph.V();
		
		return from[w]!=-1;
		
		// return from[w]!=-1;
		
	}
	
	private Vector<Integer>path(int w){ 	// 查询s点到w点 的路径，放入stack 当中
		
		Stack<Integer> s=new Stack<Integer>();
		
		Vector<Integer>res=new Vector<Integer>();
		
		if(!hasPath(w)) return res;
		
		int p=w;
		
		while(p!=-2) {
			
			s.push(p);
			
			p=from[p];
		}
		
		
		while(!s.empty())
			
			res.add(s.pop());
		
		return res;
	}
	
	public void showPath(int w) {
		
		Vector<Integer> vc=path(w);
		
		for(int i=0;i<vc.size();i++) {
			
			System.out.print(vc.elementAt(i));
		
			if(i==vc.size()-1)
				
				System.out.println();
			
			else
				
					System.out.print("->");
				
		}
	}
	
	public Iterable<Integer> path_v2(int v){
		
		List<Integer> res= new ArrayList<>();
		
		if(!hasPath(v)) return res;
		
		int cur_vetrex=v;
		
		while(cur_vetrex!=s) {
			
			res.add(cur_vetrex);
			
			cur_vetrex=from[cur_vetrex];
		}
		
		res.add(s);
		
		Collections.reverse(res);
		
		return res;
	}
	
	public static void main(String[] args) {
		
		 String fileName3="E:\\iotest\\testG3.txt";
			
		 SparseGraph_tree_map g3=new SparseGraph_tree_map(7,false);
		        
		 new Read_Graph(g3,fileName3);
		    
		 g3.show();
		 
		 Path_test pt3 = new Path_test(g3,0);
		 
		System.out.println(pt3.path_v2(6)+" "+pt3.dis(6));
		
		//System.out.println(pt3.hasPath(6));
		
		//pt3.showPath(4);
		
		System.out.println("Cycel of the G3="+pt3.Cycle_detection());
		
		System.out.println("is a bi_graph="+pt3.is_bi_graph_memo(0, 0));
		
		
		
	}

}
