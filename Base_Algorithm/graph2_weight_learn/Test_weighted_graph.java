package graph2_weight_learn;

public class Test_weighted_graph {

	public static void main(String[] args) {
		
		String fileName1="E:\\iotest\\Weighted_Graph_1.txt";

		SparseGraph_tree_map wp1= new SparseGraph_tree_map(7,false);
		
		new Read_Graph(wp1,fileName1);
		
		//wp1.show();
		
		System.out.println(wp1);

	}

}
