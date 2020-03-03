package grah_learn;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


public class Graph_Euler implements Graph,Cloneable{
	
	
	private int V;  // Vertex of the graph
	
	private int E; // Edge of the graph
	
	private boolean directed;  //  judge whether Edge has direction
	
	private TreeSet<Integer>[] g; 
	
	// Array g every element is LinkedList<Integer>
	
	public Graph_Euler() {
		
	}
	
	public Graph_Euler(int V,boolean directed) {
		
		if(V<0) throw new IllegalArgumentException("V must be above zero");
		
		this.V=V;
		
		this.E=0;
		
		this.directed=directed;
		
		g=new TreeSet[V];
		
		// LinkedList<Integer>[V]  Array with generic ,not support 
		
		// must designate with each element,java deficiency
		
		for(int i=0;i<V;i++)
			
			g[i]=new TreeSet<Integer>();
		
	}
	
	
	public int V() {
		
		return this.V;
	}
	
	public boolean isDirected() {
		
		return this.directed;
	}
	
	
	public int E() {
		
		return this.E;
	}
	
	public void addEdge(int v,int w) {
		
		
		Object obj = new Object();
		
		
		this.validate_Vetrex(v);
		
		this.validate_Vetrex(w);
		
		if(v==w) throw new IllegalArgumentException("self Loop is detected");
		
		if(g[v].contains(w)) throw new IllegalArgumentException("Parallel Edge");
		
		g[v].add(w);
		
		if(!directed)  g[w].add(v);
		
		this.E++;
		
	}
	
	public void validate_Vetrex(int v) {
		
		
		if(v<0 || v>=V) throw new IllegalArgumentException("Vetrex"+v+" is invalid");
	}
	
	
	public boolean hasEdge(int v,int w) {   //  whether v and w is neighbor O(degree(v))
		
		//  adj matrix is O(V)
		
		validate_Vetrex(v);
		
		validate_Vetrex(w);
		
		return g[v].contains(w);
		
	}
	
	public Iterable <Integer> adj(int v){  // O(degree(V))
		
	//	use Iterable to unify 
		
	//	TreeMap are not visible to others
		
		validate_Vetrex(v);
		
		return g[v];
		
	}
	
	
	public void removeEdge(int v,int w) {
		
		validate_Vetrex(v);
		
		validate_Vetrex(w);
		
		if(hasEdge(v,w)) {
			
			g[v].remove(w);
			
		}
			
			if(!directed) 
				
			g[w].remove(v);
		
	}
	
	
	public int degree(int v) {
		
		validate_Vetrex(v);
		
		if(directed) {throw new IllegalArgumentException("only worked in non-directed grapph");}
		
		return g[v].size();
	}
	
	@Override
	
	public Object clone() {
		
		// deep cloned
		
		//direct use,is shallow copy
		
		try {
			
			 Graph_Euler cloned= (Graph_Euler)super.clone();
			
			cloned.g=new TreeSet[V];
			
			for(int v=0;v<V;v++) {
				
				cloned.g[v]=new TreeSet<>();
				
				for(int w:this.g[v])
					
					cloned.g[v].add(w);
			}
			
			return cloned;
		}
		catch(CloneNotSupportedException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public void show() {
		
		for(int i=0;i<V;i++) {
			
			System.out.print("vertex"+i+ ":\t");
			
			for(int j:g[i])
				
				System.out.print(j+"\t");
			
			System.out.println();
		}
	}
	


}
