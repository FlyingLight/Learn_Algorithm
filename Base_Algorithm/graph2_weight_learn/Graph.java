package graph2_weight_learn;

public interface Graph {
	
	
	public int V();
	
	public int E();
	
	public void addEdge(int v,int w,int weight);
	
	public boolean hasEdge(int v,int w) ;
	
	public void removeEdge(int v,int w);
	
	public Iterable<Integer>adj(int v);
	
	public int getWeight(int v,int w);  
	
	public int degree(int v);
	
	//public void show();
	
	public void validate_Vetrex(int v);
	
	public boolean isDirected();
	
	// 
	
	//public int indegree(int v);
	
	//public int outdegree(int v);
	
	
}
