/**
 * 
 */
package Union_Find;

import java.util.Arrays;

/**
 * @author qiguangqin
 *
 */
public class Union_Find_V3 implements UF{

	/**
	 * @param args
	 * 
	 *
	 */
	
	private int [] parent; // parent[i],i --> which node
	
	private int[]size_node; // size[i]: The size_sub SUM nodes(not height) of tree whose root is "i"
	
	public Union_Find_V3(int size) {
		
		parent=new int [size];
		
		size_node=new int[size];
		
		for(int i=0;i<size;i++)
			
			parent[i]=i;//  each node point to it self
		
		// each node represent a tree
		
		Arrays.fill(size_node, 1);
			
		
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
			
			//parent[p]=parent[parent[p]];
			
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
		
		if(size_node[pRoot]>size_node[qRoot]) {  //  the collection which has less node points to the collection which have more node
				
				parent[qRoot]=pRoot;
				
				// less Node ---> more Node
				
				// Plus the Node(more Node )
			
				size_node[pRoot]+=size_node[qRoot];		
				
		}	
		
		
		else {
			
			parent[pRoot]=qRoot;
			
			size_node[qRoot]+=size_node[pRoot];	
			
			}
		}
	

	
	public static void main(String[] args) {
		
		

	}

}
