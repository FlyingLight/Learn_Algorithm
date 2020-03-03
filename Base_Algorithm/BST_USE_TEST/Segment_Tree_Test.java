/**
 * 
 */
package BST_USE_TEST;

import java.util.Arrays;

/**
 * @author qiguangqin
 *
 */
public class Segment_Tree_Test<E> {

	/**
	 * @param args
	 */
	
	private E[]data; // The Copy of arr 
	
	private E[]Tree;
	
	private Merger<E>merger;
	
	public Segment_Tree_Test(E[]arr,Merger<E>merger) {
		
		this.merger=merger;
		
		data=(E[])new Object[arr.length];
		
		for(int i=0;i<arr.length;i++) {
			
			data[i]=arr[i];
			
		Tree=(E[])new Object[4*arr.length]; // data array and include the segment Tree
		
			// Max_Capacity is 4*N
		
		//Arrays.fill(Tree,-1);
		
		buildSegmentTree(0, 0, arr.length-1);
		
		}
	}
	
	
	public int getSize() {
		
		return data.length;
	}
	
	public E get(int index) {
		
		if(index<0|| index>=data.length)
			
			throw new IllegalArgumentException("Index is Invalid");
		
		return data[index];
	}
	
	
	
	private void buildSegmentTree(int treeIndex,int l,int r) {
		
		
		/*
		 
		 Tree_Index 0 Sum_A[0-9]
		 
		       Tree_Index 1 Sum_A[0-4]    
		       
		           Tree_Index 3 Sum_A[0-1]    Tree_Index 4 Sum_A[2-4]
		       
		     			 Tree_Index 7 Array_A[0]     Tree_Index 9 Array_A[2]  
		     							
		     			  ree_Index 8 Array_A[1]     Tree_Index 10 Sum_A[3-4]
		     							   
		     							                Tree_Index 21 Array_A[3]
		     							                                    
		     							                 Tree_Index 22 Array_A[4]    
		     
		       Tree_Index 2 Sum_A[5-9]
		       
		       		 Tree_Index 5 Sum_A[5-6]      Tree_Index 6 Sum_A[7-9]
					
					   Tree_Index 11 Array_A[5]		   Tree_Index 13 Array_A[7]
					   
					   Tree_Index 12 Array_A[6]        Tree_Index 14 Sum_A[8-9]
					     
					                                         Tree_Index 29 Array_A[8]
		     							                                    
		     							                     Tree_Index 30 Array_A[9]  
		 
		 
		 */
		
		if(l>=r) {
			
			Tree[treeIndex]=data[l];
			
			return;
		
		}
		
		int mid=l+(r-l)/2;
		
		int left_index=this.leftChild(treeIndex);
		
		int right_index=this.rightChild(treeIndex);
		
		this.buildSegmentTree(left_index, l, mid); // Tree_index,(Arr_index,arr_index)
		
		this.buildSegmentTree(right_index, mid+1, r);
		
		//Tree[treeIndex]=Tree[left_index]+Tree[right_index];
		
		Tree[treeIndex]=merger.merge(Tree[left_index], Tree[right_index]);
		
	}
	
	
	//Tree(Array) left child index in the Tree Array
	
	private int leftChild(int index) {
		
		return 2*index+1;
	}
	
	//Tree(Array) right child index in the Tree Array
	
	private int rightChild(int index) {
		
		return 2*index+2;
	}
	
	
	
	private E _query(int tree_index,int query_L,int query_R,int l,int r) {
		
		if(query_L==l && query_R==r)
			
			return Tree[tree_index];
		
			
		int mid=l+(r-l)/2;
			
		int left_index=this.leftChild(tree_index);
			
		int right_index=this.rightChild(tree_index);	
		
		if(query_L>=mid+1) 
			
			return _query(right_index,query_L,query_R,mid+1,r);
			
		else if(query_R<=mid)
			
			return _query(left_index,query_L,query_R,l,mid);
		
		E left_Res=_query(left_index,query_L,query_R,l,mid);
		
		E right_Res=_query(right_index,query_L,query_R,mid+1,r);
		
		return merger.merge(left_Res, right_Res);
		
		}
		
	@Override
	 public String toString(){
		
        StringBuilder res = new StringBuilder();
        
        res.append('[');
        
        for(int i = 0 ; i < Tree.length ; i ++){
        	
            if(Tree[i] != null)
            	
                res.append(Tree[i]);
            
            else
                res.append("null");

            if(i != Tree.length - 1)
            	
                res.append(", ");
        }
        
        res.append(']');
        
        return res.toString();
    }
	
		
	
	
	public static void main(String[] args) {
		
		Integer[] nums= {-2,0,3,-5,2};
		
		Segment_Tree_Test<Integer> segtree=new Segment_Tree_Test<>(nums,
				
				new Merger<Integer>() {	
				
				public Integer merge(Integer a,Integer b){
			
					return a+b;
				}
				
		});
	}
	
	
}

