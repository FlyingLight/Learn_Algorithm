package grah_learn;


import java.util.TreeMap;

public class SparseGraph_tree_map implements Graph{
	
	
private int V;  // Vertex of the graph
	
	private int E; // Edge of the graph
	
	private boolean directed;  //  judge whether Edge has direction
	
	private TreeMap<Integer,Object>[] g; 
	
	// Array g every element is LinkedList<Integer>
	
	public SparseGraph_tree_map() {
		
	}
	
	public SparseGraph_tree_map(int V,boolean directed) {
		
		if(V<0) throw new IllegalArgumentException("V must be above zero");
		
		this.V=V;
		
		this.E=0;
		
		this.directed=directed;
		
		g=new TreeMap[this.V];
		
		// LinkedList<Integer>[V]  Array with generic ,not support 
		
		// must designate with each element,java deficiency
		
		for(int i=0;i<V;i++)
			
			g[i]=new TreeMap<Integer,Object>();
		
	}
	
	
	public int V() {
		
		return this.V;
	}
	
	
	public int E() {
		
		return this.E;
	}
	
	public boolean isDirected() {
		
		return this.directed;
	}
	
	public void addEdge(int v,int w) {
		
		
		Object obj = new Object();
		
		
		this.validate_Vetrex(v);
		
		this.validate_Vetrex(w);
		
		if(v==w) throw new IllegalArgumentException("self Loop is detected");
		
		if(g[v].containsKey(w)) throw new IllegalArgumentException("Parallel Edge");
		
		g[v].put(w, obj);
		
		if(!directed)  g[w].put(v, obj);
		
		this.E++;
		
	}
	
	public  void validate_Vetrex(int v) {
		
		
		if(v<0 || v>=V) throw new IllegalArgumentException("Vetrex"+v+" is invalid");
	}
	
	
	public boolean hasEdge(int v,int w) {   //  whether v and w is neighbor O(degree(v))
		
		//  adj matrix is O(V)
		
		validate_Vetrex(v);
		
		validate_Vetrex(w);
		
		return g[v].containsKey(w);
		
	}
	
	public Iterable <Integer> adj(int v){  // O(degree(V))
		
		
	//	use Iterable to unify 
		
	//	TreeMap are not visible to others
		
		validate_Vetrex(v);
		
		return g[v].keySet();
		
	}
	
	
	public void removeEdge(int v,int w) {
		
		validate_Vetrex(v);
		
		validate_Vetrex(w);
		
		if(hasEdge(v,w)) {
			
			g[v].remove(w);
			
			if(!directed) g[w].remove(v);
			
			this.E--;
		}
	}
	
	
	public int degree(int v) {
		
		validate_Vetrex(v);
		
		if(directed) {throw new IllegalArgumentException("only worked in non-directed grapph");}
		
		return g[v].size();
	}
	
	
	public void show() {
		
		for(int i=0;i<V;i++) {
			
			System.out.print("vertex"+i+ ":\t");
			
			for(int j:g[i].keySet())
				
				System.out.print(j+"\t");
			
			System.out.println();
		}
	}
	


}
