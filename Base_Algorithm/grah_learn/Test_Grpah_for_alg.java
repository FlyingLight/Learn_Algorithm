/**
 * 
 */
package grah_learn;

/**
 * @author qiguangqin
 *
 */
public class Test_Grpah_for_alg {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String fileName="E:\\iotest\\testG1.txt";
		
		//String fileName2="E:\\iotest\\testG2.txt";
		
		//String fileName3="E:\\iotest\\testG3.txt";
		
	//	SparseGraph_linklist g1 =new SparseGraph_linklist(13,false);  // 13个节点，而且是一个五香图
		
		SparseGraph_tree_map g2= new SparseGraph_tree_map(13,false);
		
//		Read_Graph read_graph1=new Read_Graph(g1,fileName);
		
		new Read_Graph(g2,fileName);
		
		Components_V2 comp1 =new Components_V2(g2);
		
		//System.out.println("test G1 in Sparse Graph:");
		
        g2.show(); //  print adj table
        
       System.out.println("the ccount of the G1= "+ comp1.count());// get ccount of the graph
        
        //comp1.get_conn();
        
//        System.out.print(comp1.getPre());
        
       // System.out.println(comp1.getPost());
        
        
        //Path_test pt = new Path_test (g2,0);
        
       // pt.showPath(3);
        
        Single_source_path2 ssg= new Single_source_path2(g2,0);
        
        System.out.println(ssg.path_v2(4)+" ");
        

	}

}
