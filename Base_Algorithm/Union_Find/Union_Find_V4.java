/**
 * 
 */
package Union_Find;

import java.util.Arrays;

/**
 * @author qiguangqin
 *
 */
public class Union_Find_V4 implements UF{

	/**
	 * @param args
	 * 
	 *
	 */
	
	private int [] parent; // parent[i],i --> which node
	
	private int[]rank; // size[i]: The depth of tree whose root is "i"
	
	public Union_Find_V4(int size) {
		
		parent=new int [size];
		
		rank=new int[size];
		
		for(int i=0;i<size;i++)
			
			parent[i]=i;//  each node point to it self
		
		// each node represent a tree
		
		Arrays.fill(rank, 1); // similar to size ,init_state all the node ,rank=1
			
		
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
		
		if(rank[pRoot]>rank[qRoot]) {  //  the collection which has less node points to the collection which have more node
				
				parent[qRoot]=parent[pRoot];//  pRoot depth >qRoot depth so,Depth(qRoot+1)<=Depth( pRoot) ,no need to update
					
		}	
		
		
		else if(rank[pRoot]<rank[qRoot]){
			
			parent[pRoot]=parent[qRoot];
			
		}
		
		else {
			
			parent[qRoot]=pRoot;
			
			rank[pRoot]+=1;  //  After qRoot-->pRoot  rank of pRoot must plus 1
		}
		
	}
	
	
	public static void main(String[] args) {
		
		

	}

}
