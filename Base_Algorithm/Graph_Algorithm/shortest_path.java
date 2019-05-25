/**
 * 
 */
package grah_learn;

import java.util.LinkedList;

import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

/**
 * @author qiguangqin
 *
 */
public class shortest_path {

	
	private Graph graph;  // 图的引用变量
	
	private int s;  // 图的起始点
	
	private boolean[] visited;  // 在DFS或者BFS中，标记该节点是否被访问了
	
	private int[] from; //from[i] 得到 路径i 的上一个节点
	
	private int[] ord; // 记录路径中节点的次序，ord[i]表示i节点在路径中的次序
	
	public shortest_path(Graph graph,int s ) {
		
		this.graph=graph;
		
		assert s>=0 && s<graph.V();
		
		visited=new boolean[graph.V()];
		
		this.s=s;
		
		ord=new int[graph.V()];
		
		from =new int[graph.V()];
		
		for(int i=0;i<graph.V();i++) {
			
			this.visited[i]=false;
			
			this.from[i]=-1;
			
			this.ord[i]=-1;
		}
	}
	
	
	public void BFS() {
		
		_bfs(this.graph,this.s);
	}
	
	
	private void _bfs(Graph graph,int s) {
		
		Queue<Integer> queue=new LinkedList<Integer>();
		
		visited[s]=true;
		
		queue.add(s);
		
		while(!queue.isEmpty()) {
			
			int v=queue.remove();
			
			//System.out.println(v);
			
			for(int i:graph.adj(v)) 
				
				if(this.visited[i]==false) {  // 判断是否加入过队列，或者被访问了
					
					visited[i]=true; // 被访问过，已经遍历过合在一起
					
					queue.add(i);
					
					from[i]=v; // 从v节点走到i节点
					
					ord[i]=ord[v]+1;
				}
		}
		
	}
	
	private Vector<Integer> path(int w){
		
		Stack<Integer> s=new Stack<Integer>();
		
		Vector<Integer>res=new Vector<Integer>();
		
		int p=w;
		
		while(p!=-1) {
			
			s.push(p);
			
			p=from[p];  // 通过from 一路回溯
		}
		
		while(!s.isEmpty()) {
			
			res.add(s.pop());
		}
		
		return res;
	}
	
	
	private boolean hasPath(int w) {
		
		assert w>=0 && w<this.graph.V();
		
		return visited[w];
	}
	
	public void showPath(int w) {
		
		assert this.hasPath(w);
		
		Vector<Integer>vec =path(w);
		
		for(int i=0;i<vec.size();i++) {
		
			System.out.print(vec.elementAt(i));
			
			if(i==vec.size()-1)
				
				System.out.println();
			
			else
				
				System.out.print("->");
		}
	}
	
	public int length(int w) {
		
		assert w>=0 && w<this.graph.V();
		
		return this.ord[w];
	}
	
	

}
