package graph2_weight_learn;

public interface UF {
	
	// map the index of Array
	
	// not consider,insert an element 
	
	int getSize();
	
	boolean isConnected(int p,int q);
	
	void unionElements(int p,int q);

}
