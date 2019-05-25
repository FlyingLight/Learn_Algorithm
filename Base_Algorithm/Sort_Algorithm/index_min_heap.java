/**
 * 
 */
package sort_learn_test;

import java.util.Random;

/**
 * @author qiguangqin
 *
 */
public class index_min_heap {
	
	
	private int[] data; // 堆数组
	 
	private int[] index; // index[i] 表示 count=i处，index 索引值，data[index[i]]
	
	private int[] reverse;
	
	private int count;
	
	private int Cap;
	
	private void swap(int[] arr,int i,int j) {
		
		int temp;
		
		temp=arr[i];
		
		arr[i]=arr[j];
		
		arr[j]=temp;
		
		}
	
	public index_min_heap(int Cap) {
		
		this.Cap=Cap;
		
		this.count=0;
		
		data=new int[Cap+1];
		
		index=new int[Cap+1];
		
		reverse=new int[Cap+1];
		
		for(int i=0;i<=Cap;i++)
			
			reverse[i]=0; // index[0] 作为初始值
		
		
	}
	
	public index_min_heap(int[] arr ,int n) {
		
		
		// heapify 操作
		
		data=new int[n+1];
		
		index=new int[n+1];
		
		reverse=new int[n+1];
		
		for(int i=0;i<n;i++) {
			
			data[i+1]=arr[i];
			
			index[i+1]=i;
			
			reverse[index[i+1]]=i+1;
			
		}
		
		this.Cap=n;
		
		this.count=Cap;
		
		for(int i=count/2;i>0;i--)
			
			shiftDown(i);
		
	}
	
	public void insert(int ind,int item) {  // ind 堆中索引数组的值，默认和count一致
		
		
		assert(ind+1<=Cap &&ind+1>1);
		
		assert(count+1<=Cap);
		
		ind+=1;
		
		data[ind]=item;
		
		index[count+1]=ind;
		
		reverse[ind]=count+1;
				
		count++;
		
		shiftUp(count);
		
	}
	
	public void shiftUp(int k) {  // 在叶子节点增加元素，如果此叶子节点，比父节点值要小
		
		while(k>1 && data[index[k]]<data[index[k/2]]) {
			
			swap(index,k,k/2);
			
			reverse[index[k]]=k;
			
			reverse[index[k/2]]=k/2;
		
			k/=2;
			
		}
	}
	
	public int extractMin() {
		
		
		assert(count>0);
		
		int ret =data[index[1]];
		
		swap(index,1,count);
		
		reverse[index[count]]=0;
		
		reverse[index[1]]=1;
		
		count--;
	
		shiftDown(1);
		
		return ret;
	}
	
public int ind_extractMin() {
		
		
		assert(count>0);
		
		int ret =index[1]-1;
		
		swap(index,1,count);
		
		reverse[index[count]]=0;
		
		reverse[index[1]]=1;
		
		count--;
	
		shiftDown(1);
		
		return ret;
	}
	
	public boolean isEmpty() {
		
		return count==0;
	}
	
	public int size() {
		
		return count;
	}
	
	
	public void shiftDown(int k) {
		
		while(2*k<=count) {
			
			int j=2*k;
			
			if(j+1<=count && data[index[j]]>data[index[j+1]])
				
				j+=1;
			
			if(data[index[k]]<=data[index[j]]) break;
			
			swap(index,k,j);
			
			reverse[index[k]]=k;
			
			reverse[index[j]]=j;
			
			k=j;
		}
	}
	
	public void print(){
		
		for(int i=1;i<=this.count;i++)
			
			System.out.print(this.data[i]+" ");
	}
	
	
	public int get_item(int j) {
		
		return data[j+1];
	}
	
	public void change_item(int j,int new_item) {
		
		
		data[j+1]=new_item;
		
		
		for(int i=1;i<=count;i++) {
			
			if(index[i]==j+1) {
				
				
				shiftDown(i);
				
				shiftUp(i);
				
			}
		}
		
	
	}
	
	public void change_item2(int j,int new_item) {
		
		j+=1;
		
		data[j]=new_item;
		
		int i=reverse[j]; 
		
		shiftDown(i);
		
		shiftUp(i);
		
		
	}
	
	
	public static void main(String[] args) {
		
		int Cap=20;
		
		Random random =new Random(20);
		
		index_min_heap imh =new index_min_heap(Cap);
		
		for(int i=0;i<Cap;i++) {
		
		imh.insert(i,random.nextInt(100));
		
		}
		
		
		imh.print();
		
		System.out.println("\n");
		
		//imh.treePrint();
		
		imh.change_item(4,156);
		
		while(!imh.isEmpty()) {
		
		//int b=imh.ind_extractMin();
		
		int b=imh.extractMin();
		
		
		System.out.print(b+" ");
		
		}
	
   }


}
