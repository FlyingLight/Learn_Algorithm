/**
 * 
 */
package graph2_weight_learn;

import java.util.Arrays;

/**
 * @author qiguangqin
 *
 */
public class CC {
	
	private Graph graph;
	
	private int[] visited;
	
	private int ccount=0;

	/**
	 * @param args
	 */
	
	public CC(Graph graph) {
		
		this.graph=graph;
		
		visited = new int[graph.V()];
		
		Arrays.fill(visited, -1);
		
		for(int i=0;i<graph.V();i++) {
			
			if(visited[i]!=-1) {
				
				dfs(i,ccount);
				
				ccount++;
			}
		}
		
	}
	
	public int get_ccount() {
		
		return ccount;
	}
	
	private void dfs(int v,int ccid){
		
		visited[v]=ccid;
		
		for(int w:graph.adj(v)) {
			
			if(visited[w]==-1) {
				
				dfs(w,ccid);
			}
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

}
