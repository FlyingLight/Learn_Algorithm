/**
 * 
 */
package grah_learn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author qiguangqin
 *
 */


public class Components_V2 {
	
	private Graph graph;
	
	int []visited; // 记录每一个节点，是否已经被访问过了，并注明连通分量
	
	int ccount;  // 记录有多少个连通分量
	
	//private int[] id; // 每个节点所对应的连通分量标记

	private List<Integer> pre =new ArrayList<>();
	
	private List<Integer> post =new ArrayList<>();
	
	public Components_V2(Graph graph) {
		
		this.graph=graph;
		
		visited=new int[graph.V()];
		
		//id = new int[graph.V()];
		
		ccount=0;  // 初始化连通分量为0
			
		for(int i=0;i<graph.V();i++) {
			
			visited[i]=-1;  // 初始化，所有节点都未被访问
		
			//id[i]=-1;
			 
		}
		
	
	if(!graph.isDirected()) {
		
	for(int i=0;i<graph.V();i++) { // Take into account the mutiple-ccount;
			
			if(visited[i]==-1) {   //  如果节点i 没有被访问过，就进行深度优先遍历
				
			//if(!visited[i]) // get another ccid
				
				_dfs_pre(i,ccount);//  depth first search,将与i相连接的节点都遍历一遍
				
				this.ccount++;  // 没有遍历到的节点，就是在新的连通分量当中
			}
			
		}
	
	}
		
	}

	private void _dfs_pre(int v,int ccount) {  // 深度优先遍历算法
		
		
		visited[v]=ccount;
		
		//id[v]=this.ccount;
		
		pre.add(v);
		
		for(int i:graph.adj(v)) {  // 每一个adj(i)表示一个vector 向量
			
			if( visited[i]==-1 )
				
				_dfs_pre(i,ccount);
			
		}
		
	//	post.add(v);
	}
	
	private void _dfs_post(int v,int ccount) {  // 深度优后遍历算法
		
		
		visited[v]=ccount;
		
		//id[v]=this.ccount;
			
		for(int i:graph.adj(v)) {  // 每一个adj(i)表示一个vector 向量
			
			//O(V+E) 时间复杂度，如果 E>>v O(E) 
			
			//  都不想连  ---> O(V)
			
			if( visited[i]==-1 )
				
				_dfs_post(i,ccount);
			
		}
		
		post.add(v);
	}
	
		
	private void _dfs_no_recursive(int v,int ccount) {
		
		Stack<Integer> stack = new Stack<>();
		
		visited[v]=ccount;
		
		stack.push(v);
		
		while(!stack.isEmpty()) {
			
			int cur=stack.pop();
			
			pre.add(cur);
			
			for(int i:graph.adj(cur)){
				
				if(visited[i]==-1) {
					
					stack.push(i);
					
					visited[i]=ccount;  // different from the recursive ,visited[i] must maintain at the loop 
					
				}
				
			}
		}
	}
	
	
	public List<Integer> getPre() {
		return pre;
	}

	public List<Integer> getPost() {
		return post;
	}
	
	public int get_ccid() {
		
		return ccount;
	}
	
	public int count() {
		
		for(int e:visited)
			
			System.out.print(e+" ");
		
		System.out.println();
		
		return this.ccount;
		
	}
	
	
	
	public int getCcount() {
		return ccount;
	}

	
	public List<Integer>[] get_Spec_Components(){
		
		List<Integer> []res= new ArrayList[ccount];
		
		for(int i=0;i<ccount;i++) res[i]= new ArrayList<>();
		
		for(int v=0;v<graph.V();v++) {
				
				res[visited[v]].add(v);
		}
		
		return res;
	}
	

	public boolean isConnected(int v,int w) {  // 判断两个节点 ，通过连通分量，是否相连接
		
		assert v>=0 && v<this.graph.V();
		
		assert w>=0 && w<this.graph.V();
		
		return visited[v]==visited[w];
		
	}
	
	
	public static void main(String[] args) {
		
		String fileName1="E:\\iotest\\testG1.txt";
		
		SparseGraph_tree_map  g1 =new SparseGraph_tree_map (13,false);
		
		new Read_Graph(g1,fileName1);
		
        g1.show();
        
        Components_V2 cmp1= new Components_V2(g1); // 求出g2 的连通分量
        
        System.out.println("The pre DFS of G1="+cmp1.pre);
        
        System.out.println("the count of graph1="+cmp1.ccount);
        
        cmp1.count();
        
        System.out.println("--------------------------------------------------------------");
	    

	    String fileName3="E:\\iotest\\testG3.txt";
		
		SparseGraph_tree_map g3=new SparseGraph_tree_map(7,false);
	        
	    new Read_Graph(g3,fileName3);
	    
	    g3.show();
	    
	    Components_V2 cmp3= new Components_V2(g3); // 求出g2 的连通分量
        
        System.out.println("The pre DFS of G3="+cmp3.pre);
        
        System.out.println("the count of graph3="+cmp3.ccount);
        
        cmp3.count();
		
	}
	

}
