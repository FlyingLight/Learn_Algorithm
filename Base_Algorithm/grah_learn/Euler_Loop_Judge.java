/**
 * 
 */
package grah_learn;

/**
 * @author qiguangqin
 *
 */
public class Euler_Loop_Judge {

	/**
	 * @param args
	 */
	
	private Graph_Euler graph;
	
	public Euler_Loop_Judge(Graph_Euler graph) {
		
		this.graph=graph;
	}
	
	public boolean hasEulerLoop() {
		
		Components_V2 cc= new Components_V2(graph);
		
		if(cc.getCcount()>1) return false;
		
		else {
			
			for(int v=0;v<graph.V();v++) 
				
				if(graph.degree(v)%2==1)  return false;
				
			return true;
		}
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
