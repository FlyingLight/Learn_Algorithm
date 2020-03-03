package graph4_network_flow;


import java.util.Map;
import java.util.TreeMap;





 public class Sparse_Weighted_Graph_tree_map_directed implements Graph,Cloneable{
	
	
	//  Weighted Map with Direction
	
	private int V;  // Vertex of the graph
	
	private int E; // Edge of the graph
	
	private boolean directed;  //  judge whether Edge has direction
	
	private TreeMap<Integer,Integer>[] g;  // First Integer represent Edge, Second Integer represent the weight designated Edge
	
	// Array g every element is LinkedList<Integer>
	
	private int[] indegrees,outdegrees;
		
	// indegrees[i] represent the indegree of vertex i
	
	// outdegrees[i] represent the outdegree of vertex i
	
	@SuppressWarnings("unchecked")
	public Sparse_Weighted_Graph_tree_map_directed(int V,boolean directed) {
		
		if(V<0) throw new IllegalArgumentException("V must be above zero");
		
		this.V=V;
		
		this.E=0;
		 
		this.directed=directed;
		
		g=new TreeMap[this.V];
		
		// LinkedList<Integer>[V]  Array with generic ,not support 
		
		// must designate with each element,java deficiency
		
		for(int i=0;i<V;i++)
			
			g[i]=new TreeMap<Integer,Integer>();
		
		indegrees= new int[V];
		
		outdegrees= new int[V];
		
	}
	
	public Sparse_Weighted_Graph_tree_map_directed(TreeMap<Integer,Integer>[]rg,boolean directed) {
		
		//if(!this.directed) throw new IllegalArgumentException("only worked in directed map");
		
		this.g=rg;
		
		this.directed=directed;
		
		this.V=rg.length;
		
		this.E=0;
		
		indegrees= new int[V];
		
		outdegrees= new int[V];

		for(int v=0;v<V;v++) {
			
			for(int w:rg[v].keySet()) {
				
				outdegrees[v]++;
				
				indegrees[w]++;
				
				this.E++;
			}
		}
		
	}
	
	
	public int V() {
		
		return this.V;
	}
	
	
	public int E() {
		
		return this.E;
	}
	
	public void addEdge(int v,int w,int weight) { // add the weight
		
		this.validate_Vetrex(v);
		
		this.validate_Vetrex(w);
		
		if(v==w) throw new IllegalArgumentException("self Loop is detected");
		
		if(g[v].containsKey(w)) throw new IllegalArgumentException("Parallel Edge");
		
		g[v].put(w, weight);
		
		if(directed) { indegrees[w]+=1; outdegrees[v]+=1;} // add the designated in or out degrees
		
		if(!directed)  g[w].put(v, weight);
		
		this.E++;
		
	}
	
	public boolean isDirected() {
		
		return directed;
	}
	
	
	public void validate_Vetrex(int v) {
		
		
		if(v<0 || v>=V) throw new IllegalArgumentException("Vertex"+v+" is invalid");
	}
	
	
	@SuppressWarnings("unchecked")
	public Graph reverseGraph() {
		
		TreeMap<Integer,Integer>[] rg= new TreeMap[V];
		
		for(int i=0;i<V;i++)
			
			rg[i]= new TreeMap<>();
		
		for(int v=0;v<V;v++)
			
			for(int w:adj(v))
				
				rg[w].put(v, getWeight(v,w));
		
		return new Sparse_Weighted_Graph_tree_map_directed(rg,directed);
		
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
	
	
	public int getWeight(int v,int w) {
		
		// v Cur_vertex  ---->  w arrow 
		
		if(hasEdge(v,w))
		
			return g[v].get(w);
		
		throw new IllegalArgumentException(String.format("No Edge %d-%d",v,w));
	}
	
	
	public void setWeight(int v,int w,int new_weight) {
		
		if(!hasEdge(v,w)) 
		
			throw new IllegalArgumentException(String.format("No Edge %d-%d",v,w));
		
		g[v].put(w, new_weight);
		
		if(!directed) g[w].put(v, new_weight);
	}
	
	public void removeEdge(int v,int w) {
		
		validate_Vetrex(v);
		
		validate_Vetrex(w);
		
		if(hasEdge(v,w)) {
			
			g[v].remove(w);
			
			if(directed) { outdegrees[v]-=1;  indegrees[w]-=1;} // Maintain the in-degree or out-degree in remove_edge action
			
			if(!directed) g[w].remove(v);
			
			this.E--;  // Not forget 
		}
	}
	
	//  Different from the non-directed graph,
	
	// Directed Graph needed to distinguish the in_degree and out_degree
	
	
	public int degree(int v) {
		
		validate_Vetrex(v);
		
		if(directed) {throw new IllegalArgumentException("only worked in non-directed grapph");}
		
		return g[v].size();
	}
	
	
	public int indegree(int v) {
		
		validate_Vetrex(v);
		
		if(!directed) {throw new IllegalArgumentException("indegree only worked in directed grapph");}
		
		return indegrees[v];
	}
	
	public int outdegree(int v) {
		
		validate_Vetrex(v);
		
		if(!directed) {throw new IllegalArgumentException("outdegree only worked in directed grapph");}
		
		return outdegrees[v];
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Object clone() {
		
		try {
		
		Sparse_Weighted_Graph_tree_map_directed cloned= (Sparse_Weighted_Graph_tree_map_directed)super.clone();
		
		cloned.g= new TreeMap[V];
		
		for(int v=0;v<V;v++) {
			
			cloned.g[v]=new TreeMap<Integer,Integer>();
			
			for(Map.Entry<Integer, Integer>en:g[v].entrySet())
				
				cloned.g[v].put(en.getKey(), en.getValue());
			}
		
			return cloned;
			
		}catch(CloneNotSupportedException e) {
			
			e.printStackTrace();
		
	}
		
		return null;

	}
	/*
	public void show() {
		
		System.out.println("V="+V+"\t"+"E="+E+"\t"+"directed="+directed);
		
		for(int i=0;i<V;i++) {
			
			System.out.print("vertex"+i+ ":\t");
			
			//for(int j:g[i].keySet())
				
			//	System.out.print("("+j+":"+g[i].get(j)+")"+" ");
			
			for(Map.Entry<Integer, Integer> en:g[i].entrySet())
				
				System.out.print("("+en.getKey()+":"+en.getValue()+")"+" ");
			
			System.out.println();
		}
	}
	
	
	
	*/
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("V=%d, E=%d,direted=%b\n",V,E,directed));
		
		for(int i=0;i<V;i++) {
			
			sb.append(String.format("vertex%d: ", i));
			
			for(Map.Entry<Integer, Integer>en:g[i].entrySet())
				
				sb.append(String.format("(%d:%d) ", en.getKey(),en.getValue()));
			
			sb.append("\n");
			
		}
		return sb.toString();
		
		
	}
	
}
