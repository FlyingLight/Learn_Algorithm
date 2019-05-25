/**
 * 
 */
package grah_learn;

import java.util.Vector;

import java.util.Stack;

/**
 * @author qiguangqin
 *
 */
public class Path_test {
	
	
	private Graph graph;
	
	private int s;//起始的点
	
	private int[] from; // 记录路径
	
	private boolean[] visited;
	
	int []num;
	
	boolean isDAG=true;
	
	private void dfs(int v) {
		
		
		visited[v]=true;
		
		
		for(int i:graph.adj(v)) 
			
			if(!visited[i]) {
			
			from[i]=v; //  i 节点都是从v 过来的
			
			dfs(i);
			
			
			}
		
		
	}
	
	
	public void has_cycle(int v) {
		
		System.out.println("正在访问结点"+v);
		
		num[v]=1;
		
		for(int i:graph.adj(v)) {
			
			
				if(num[i]==1) {
					
					isDAG=false;
					
					break;
				}
				
				else if(num[i]==-1) {
					
					continue;
				}
				
				else {
					
					from[i]=v;
				
					has_cycle(i);

				}
					
		}
		
		num[v]=-1;
	}
	
	
	public Path_test(Graph graph,int s) {  // 从一个点s，到任意点的路径
		
		this.graph=graph;
		
		assert s>=0 && s<graph.V();
		
		visited=new boolean[graph.V()];
		
		num= new int[graph.V()];
		
		from =new int[graph.V()];
		
		for(int i=0;i<graph.V();i++) {
			
			visited[i]=false;
			
			from[i]=-1;
			
			num[i]=0;
		}
		
		this.s=s;
		
		dfs(s);
	}
	
	public boolean hasPath(int w) {
		
		
		//从s到w 之间是否存在一条路径
		
		assert w>=0 && w<this.graph.V();
		
		return visited[w];
		
	}
	
	private Vector<Integer>path(int w){ 	// 查询s点到w点 的路径，放入stack 当中
		
		assert hasPath(w);
		
		Stack<Integer> s=new Stack<Integer>();
		
		int p=w;
		
		while(p!=-1) {
			
			s.push(p);
			
			p=from[p];
		}
		
		Vector<Integer>res=new Vector<Integer>();
		
		while(!s.empty())
			
			res.add(s.pop());
		
		return res;
	}
	
	public void showPath(int w) {
		
		Vector<Integer> vc=path(w);
		
		for(int i=0;i<vc.size();i++) {
			
			System.out.print(vc.elementAt(i));
		
			if(i==vc.size()-1)
				
				System.out.println();
			
			else
				
					System.out.print("->");
				
		}
	}

}
