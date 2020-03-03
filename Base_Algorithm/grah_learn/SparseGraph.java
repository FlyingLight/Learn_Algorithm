package grah_learn;

import java.util.Vector;

public class SparseGraph implements Graph{
	
	// 稀疏图，使用邻接表，来实现
	
	private int n; // 表示图的点数
	
	private int m; // 表示图的边数
	
	private boolean directed;    // 表示是否为有向图
	
	private Vector<Integer>[]g;  // 和节点i 相邻的所有节点编号
	
	public SparseGraph(int n,boolean directed) {
		
		assert(n>=0);
		
		this.m=0;// 初始化边为0
		
		this.n=n;
		
		this.directed=directed;
		
		// g表示一个数组，大小n，每一个元素都是一个Vector
		
		g=(Vector<Integer>[])new Vector[n];
		
		for (int i=0;i<n;i++)
			
			g[i]=new Vector<Integer>();
		
	}
	
	public int V() {
		
		return this.n; // 返回图的节点数 n
	}

	
	public int E() {
		
		return this.m; // 返回边的数目m
	}
	
	public boolean isDirected() {
		
		return this.directed;
	}
	
	public boolean hasEdge(int v,int w) {
		
		assert (v>=0 && v<this.n);
		
		assert (w>=0 && w<this.n);
		
		for(int i=0;i<g[v].size();i++)
			
			if(g[v].elementAt(i)==w)
				
				return true;
		
		return false;
		
	}
	
	public void addEdge(int v,int w) {
		
		assert (v>=0 && v<this.n);
		
		assert (w>=0 && w<this.n);
		
		g[v].add(w); // v 和w 相连后，判断 是否为自环边
		
		if(v!=w && !directed) // 由于处理平行边，成本很高，O(n)，在addEdge方法不考虑
			
			g[w].add(v);
		
		m++;
	}
	
	public Iterable<Integer> adj(int v){  // 返回节点V的临边，使用一个迭代器  O(E) 级别的算法复杂度
		
		assert v>=0 && v<n;
		
		return g[v];
		
	}
	
	public void removeEdge(int v,int w) {
		
		assert (v>=0 && v<this.n);
		
		assert (w>=0 && w<this.n);
		
		if(hasEdge(v,w)) {
			
			g[v].remove(w);
			
			if(!directed) g[w].remove(v);
		}
	}
	
	public void validate_Vetrex(int v) {
		
		
		if(v<0 || v>=this.V()) throw new IllegalArgumentException("Vetrex"+v+" is invalid");
	}
	
	public int degree(int v) {
		
		assert v>=0 && v<n;
		
		return g[v].size();
	}
	
	public void show() {
		
		for(int i=0;i<n;i++) {
			
			System.out.print("vertex"+i+ ":\t");
			
			for(int j=0;j<g[i].size();j++)
				
				System.out.print(g[i].elementAt(j)+"\t");
			
			System.out.println();
		}
	}
}
