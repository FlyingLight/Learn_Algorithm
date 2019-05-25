/**
 * 
 */
package grah_learn;

import java.util.Stack;

/**
 * @author qiguangqin
 *
 */
public class Components {
	
	private Graph graph;
	
	boolean []visited; // 记录每一个节点，是否已经被访问过了
	
	int ccount;  // 记录有多少个连通分量
	
	private int[] id; // 每个节点所对应的连通分量标记
	
	
	
	public Components(Graph graph) {
		
		this.graph=graph;
		
		visited=new boolean[graph.V()];
		
		id = new int[graph.V()];
		
		ccount=0;  // 初始化连通分量为0
		
	
		
		for(int i=0;i<graph.V();i++) {
			
			visited[i]=false;  // 初始化，所有节点都未被访问
		
			id[i]=-1;
			 
		}
		
		for(int i=0;i<graph.V();i++)
			
			if(!visited[i]) {   //  如果节点i 没有被访问过，就进行深度优先遍历
				
				dfs(i);//  depth first search,将与i相连接的节点都遍历一遍
				
				this.ccount++;  // 没有遍历到的节点，就是在新的连通分量当中
			}
	}
	
	private void dfs(int v) {  // 深度优先遍历算法
		
		
		
		visited[v]=true;
		
		id[v]=this.ccount;
		
		for(int i:graph.adj(v)) {  // 每一个adj(i)表示一个vector 向量
			
			if( !visited[i] )
				
				dfs(i);
			
		}
	}
	
	private void dfs_no_rec(int v) {
		
	
	}
	
	private int count() {
		
		return this.ccount;
		
	}
	
	public boolean isConnected(int v,int w) {  // 判断两个节点 ，通过连通分量，是否相连接
		
		assert v>=0 && v<this.graph.V();
		
		assert w>=0 && w<this.graph.V();
		
		return id[v]==id[w];
		
	}

}
