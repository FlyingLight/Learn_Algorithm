package grah_learn;

import java.util.Vector;

public class DenseGraph_learn implements Graph{
	
	// 稠密图-邻接矩阵
	
	private int n;  // 表示图的点数
	
	private int m; // 表示图的边数
	
	boolean  directed; // 表示是否为有向图
	
	private boolean[][]g; // 图的具体数据
	
	public DenseGraph_learn(int n,boolean directed) {
		
		assert(n>=0);
		
		this.n=n; // 初始化节点数为n
		
		this.m=0; // 初始化边为0
		
		this.directed=directed;
		
		g=new boolean[n][n];  // 每一个元素初始为false 
		
		// n*n boolean 矩阵，g[i][i]=false (无向图，A.T=A)
		
	}
	
	public int V() {
		
		// 返回节点的数量
		
		return this.n;
	}
	
	public int E() {
		
		// 返回变得数量
		
		return this.m;
	}
	
	public boolean isDirected() {
		
		return this.directed;
	}
	
	
	public boolean hasEdge(int v,int w) {// 判断v和w之间是否存在边
		
		assert (v>=0 && v<this.n);
		
		assert (w>=0 && w<this.n);
		
		return g[v][w];
	}
	
	public void addEdge(int v,int w) {  // v,w节点是否有边
		
		// v,w 小于n， O(1) 判断 v w 之间是否有边
	
		assert (v>=0 && v<this.n);
		
		assert (w>=0 && w<this.n);
		
		if(hasEdge(v,w)) // 不考虑的平行边的情况，但是考虑自环边
			
			return;
		
		g[v][w]=true;  //  在节点 v和 节点w 之间增加一条边
		
		if(!directed ) {
			
			g[w][v]=true;
			
		this.m++;  //   将 边m的数量++ ，平行边不影响m 数量
		
		}
		
	}
	
	public Iterable<Integer>adj(int v){
		
		Vector<Integer> adjV= new Vector<Integer>(); // 构建一个vector 返回 迭代结果
		
		for(int i=0;i<n;i++)
			
			if(g[v][i])
				
				adjV.add(i);
		
		return adjV;
	}
	
	public void validate_Vetrex(int v) {
		
		
		if(v<0 || v>=this.V()) throw new IllegalArgumentException("Vetrex"+v+" is invalid");
	}
	
	public int degree(int v) {
		
		int res=0;
		
		for(int i=0;i<n;i++) {
			
			if(g[v][i])
				
				res++;
		}
		
		return res;
	}
	
	public void removeEdge(int v,int w) {
		
		assert (v>=0 && v<this.n);
		
		assert (w>=0 && w<this.n);
		
		if(hasEdge(v,w)) {
			
			g[v][w]=false;
			
			if(!directed) g[w][v]=false;
			
			this.m--;
		}
	}
	
	public void show() {
		
		for(int i=0;i<n;i++) {
			
			for(int j=0;j<n;j++)
				
				System.out.print(g[i][j]+"\t");
			
			System.out.println();
		}
	}

	
}
