package graph3_directed_weight;

public class Test_directed_weighted_graph2 {

	public static void main(String[] args) {
		
		String fileName1="E:\\iotest\\Directed Top_Graph.txt";

		Sparse_Weighted_Graph_tree_map_directed wp1= new Sparse_Weighted_Graph_tree_map_directed(5,true);
		
		new Read_Graph(wp1,fileName1);
		
		//wp1.show();
		
		System.out.print(wp1);
		
		for(int i=0;i<wp1.V();i++)
		
		System.out.println(wp1.indegree(i)+" "+wp1.outdegree(i));
		
		
	}

}
