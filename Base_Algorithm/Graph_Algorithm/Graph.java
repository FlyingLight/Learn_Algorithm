package grah_learn;

public interface Graph {
	
	public int V();
	
	public int E();
	
	public void addEdge(int v,int w);
	
	public boolean hasEdge(int v,int w) ;
	
	public Iterable<Integer>adj(int v);
	
	public void show();
}