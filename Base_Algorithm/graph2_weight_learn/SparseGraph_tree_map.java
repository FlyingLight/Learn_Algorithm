package graph2_weight_learn;


import java.util.Map;
import java.util.TreeMap;




public class SparseGraph_tree_map implements Graph,Cloneable{
	
	
	//  Weighted Map with NO Direction
	
	
	private int V;  // Vertex of the graph
	
	private int E; // Edge of the graph
	
	private boolean directed;  //  judge whether Edge has direction
	
	private TreeMap<Integer,Integer>[] g;  // First Integer represent Edge, Second Integer represent the weight designated Edge
	
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
			
			g[i]=new TreeMap<Integer,Integer>();
		
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
	
	public void addEdge(int v,int w,int weight) { // add the weight
		
		this.validate_Vetrex(v);
		
		this.validate_Vetrex(w);
		
		if(v==w) throw new IllegalArgumentException("self Loop is detected");
		
		if(g[v].containsKey(w)) throw new IllegalArgumentException("Parallel Edge");
		
		g[v].put(w, weight);
		
		if(!directed)  g[w].put(v, weight);
		
		this.E++;
		
	}
	
	public void validate_Vetrex(int v) {
		
		
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
	
	
	public int getWeight(int v,int w) {
		
		if(hasEdge(v,w))
		
			return g[v].get(w);
		
		throw new IllegalArgumentException(String.format("No Edge %d-%d",v,w));
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
		
		if(directed) {throw new IllegalArgumentException("only worked in non-directed grapph");}
		
		validate_Vetrex(v);
		
		return g[v].size();
	}
	

	@Override
	public Object clone() {
		
		try {
			
			 SparseGraph_tree_map cloned =( SparseGraph_tree_map)super.clone();
			
			cloned.g=new TreeMap[this.V];
			
			for(int v=0;v<this.V;v++) {
				
				cloned.g[v]=new TreeMap<Integer,Integer>();
				
				for(Map.Entry<Integer, Integer>en:g[v].entrySet()) {
					
					cloned.g[v].put(en.getKey(), en.getValue());
				}
			}
			
			return cloned;
		}
		
		catch(CloneNotSupportedException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	/*
	public void show() {
		
		System.out.println("V="+V+"\t"+"E="+E);
		
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
