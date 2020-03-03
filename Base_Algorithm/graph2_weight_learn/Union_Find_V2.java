/**
 * 
 */
package graph2_weight_learn;

/**
 * @author qiguangqin
 *
 */
public class Union_Find_V2 implements UF{

	/**
	 * @param args
	 */
	
	private int [] parent; // parent[i],i --> which node
	
	public Union_Find_V2(int size) {
		
		parent=new int [size];
		
		for(int i=0;i<size;i++)
			
			parent[i]=i;//  each node point to it self
		
		// each node represent a tree
		
		}
	@Override
	public int getSize() {
		
		return parent.length;
	}
	
	
	private int find(int p) {
		
		 //  find the root of p  because the tree structure of union elements
		
		if(p<0||p>=getSize())
			
			throw new IllegalArgumentException("invalid argument");
		
		while(p!=parent[p]) {
			
			p=parent[p];
		}
		
		return p;
	}
	
	@Override
	public boolean isConnected(int p,int q) {
		
		return find(p)==find(q);
		
	}
	
	@Override
	public void unionElements(int p,int q) {
		
		int pRoot=find(p);
		
		int qRoot=find(q);
		
		if(pRoot==qRoot)
			
			return;
		else
			
			parent[pRoot]=qRoot;
	}
	
	public static void main(String[] args) {
		
		

	}

}
