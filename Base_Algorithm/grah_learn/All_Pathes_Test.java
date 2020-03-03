/**
 * 
 */
package grah_learn;

import java.util.ArrayList;

import java.util.List;

/**
 * @author qiguangqin
 *
 */
public class All_Pathes_Test {

	/**
	 * @param args
	 */
	
	private Single_source_path2[] pathes;
	
	private Graph graph;
	
	public All_Pathes_Test(Graph graph) {
		
		
		this.graph=graph;
		
		pathes= new Single_source_path2[graph.V()];
		
		for(int v=0;v<graph.V();v++)
			
			pathes[v]=new Single_source_path2(graph,v);
		
	}
	
	public boolean isConnectedTo(int s,int t) {
		
		graph.validate_Vetrex(s);
		
		return pathes[s].isConnectedTo(t);
	}
	
	public Iterable<Integer>get_path(int s,int t){
		
		if(isConnectedTo(s, t)) {
			
			return pathes[s].path_v2(t);
		}
		
		else {
			
			List<Integer> list = new ArrayList<>();
			
			return list;
			
		}
		
	}
	
	public int dis(int s,int t) {
		
		graph.validate_Vetrex(s);
		
		return pathes[s].dis(t);
	}
	
	public static void main(String[] args) {
		
		 String fileName3="E:\\iotest\\testG3.txt";
			
		 SparseGraph_tree_map g3=new SparseGraph_tree_map(7,false);
		        
		 new Read_Graph(g3,fileName3);
		    
		 g3.show();
		 
		 All_Pathes_Test apt = new  All_Pathes_Test(g3);
		 
		System.out.println(apt.get_path(0, 6)+" "+apt.dis(0, 6));

	}

}
