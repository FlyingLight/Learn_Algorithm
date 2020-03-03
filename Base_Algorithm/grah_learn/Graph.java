package grah_learn;

public interface Graph {
	
	public int V();
	
	public int E();
	
	public void addEdge(int v,int w);
	
	public boolean hasEdge(int v,int w) ;
	
	public void removeEdge(int v,int w);
	
	public Iterable<Integer>adj(int v);
	
	public int degree(int v);
	
	public void show();
	
	public void validate_Vetrex(int v);
	
	public boolean isDirected();
	
	
}
