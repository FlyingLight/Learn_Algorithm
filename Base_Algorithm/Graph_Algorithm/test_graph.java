/**
 * 
 */
package grah_learn;

/**
 * @author qiguangqin
 *
 */
public class test_graph {
	
	public static void main(String[]args) {
		
		String fileName="E:\\iotest\\testG1.txt";
		
		String fileName2="E:\\iotest\\testG2.txt";
		
		String fileName3="E:\\iotest\\testG3.txt";
		
		SparseGraph g1 =new SparseGraph(13,false);  // 13个节点，而且是一个五香图
		
		Read_Graph read_graph1=new Read_Graph(g1,fileName);
		
		System.out.println("test G1 in Sparse Graph:");
		
        g1.show(); //  print adj table
        
        
        //------------------------------------------------------------------------
        Components cmp1= new Components(g1); // 求出g1 的连通分量
        
        System.out.println("the count of graph1="+cmp1.ccount);
        
       // System.out.println(g1.adj(6));

        System.out.println();
        
        // ---------------------------------------------------------------------------------
        
        Path_test pt= new Path_test(g1,5);
        
        pt.showPath(6);
        
        
        //***************************************************************************
        
        
        DenseGraph_learn g2= new DenseGraph_learn(13,false);
        
        Read_Graph read_graph2=new Read_Graph(g2,fileName);
        
        
       // System.out.println("test G1 in Dense Graph:");
        
      //  g2.show();
        
     // ---------------------------------------------------------------------------
       
    
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
   
        
		
		SparseGraph g3 =new SparseGraph(7,false);
		
		Read_Graph read_graph3=new Read_Graph(g3,fileName2);
		
		System.out.println("test G2 in Sparse Graph:");
		
        g3.show();
        
        Components cmp3= new Components(g3); // 求出g2 的连通分量
        
        System.out.println("the count of graph2="+cmp3.ccount);
         

        System.out.println();
        
        Path_test pt3= new Path_test(g3,0);
        
        pt3.showPath(6);
        
        DenseGraph_learn g4= new DenseGraph_learn(7,false);
        
        Read_Graph read_graph4=new Read_Graph(g4,fileName2);
        
       // System.out.println("test G2 in Dense Graph:");
        
       // g4.show();
        
        
        
        SparseGraph  g6=new SparseGraph (7,true);
        
        Read_Graph read_graph6= new Read_Graph(g6,fileName3);
        
        g6.show();
        
        Path_test pt6= new Path_test(g6,0);
        		
        pt6.showPath(6);
        
        Components cmp6= new Components(g6); // 求出g2 的连通分量
        
        System.out.println("the count of graph3="+cmp6.ccount);
        
      
        /*
      
       for (int j=0;j<g6.V();j++) {
    	   
    	   
    	   if(pt6.num[j]==-1) {
    		   
    		   continue;
    	   }
    	   
    	   pt6.has_cycle(j);
    	   
    	   if(!pt6.isDAG) {
    		   
    		   System.out.println("ring");
    		   
    		   break;
    	   }
       }
       
       if(pt6.isDAG)
    	   System.out.println("not ring");
    	   */
        
        shortest_path pt7=new shortest_path(g6,0);
        
        pt7.BFS();
        
        pt7.showPath(6);   
        
       
	}
	
	
	

}
