/**
 * 
 */
package sort_learn_test;

import java.util.Random;

/**
 * @author qiguangqin
 *
 */
public class mod_index_max_heap {
	
	
	private int[]data;
	
	private int[] index; // 和最大堆一致，从1开始计数到Cap，一共需要有Cap+1 的空间
	
	private int[] reverse; // reverse[i] 表示i,在索引堆中的位置
	
	//index[reverse[i]]=i   reverse[index[i]]=i reverse[j]=i  index[i]=j
	
	private int count; // 初始化值为0，从1开始计数到Cap
	
	private int Cap;
	
	
	private void swap(int[] arr,int i,int j) {
		
		int temp;
		
		temp=arr[i];
		
		arr[i]=arr[j];
		
		arr[j]=temp;
	}
	
	
	public mod_index_max_heap(int Cap) {
		
		
		this.Cap=Cap;
		
		this.count=0;
		
		index=new int[Cap+1];
		
		data=new int[Cap+1];
		
		reverse=new int[Cap+1]; 
		
		// reverse 需要初始化，全部为0，表示在index 的0号位置，表示不存在

		for(int i=0;i<=this.Cap;i++) {
			
			reverse[i]=0;
			
		}
	}
	
	
	public boolean isEmpty() {
	
		if(count==0)
		
			return true;
	
		else
		
			return false;
	}
	
	public int size() {
		
		return this.count;
	}
	
	
	// ind 和数组一致，从0开始 
	
	public void insert(int ind,int item) {
		
		
		assert(count+1<=this.Cap);
		
		assert(ind+1>=1&& ind+1<=this.Cap);
		
		ind+=1;
		
		data[ind]=item; // 只是交换索引的位置，对应的元素位置不变
		
		index[count+1]=ind;
		
		reverse[ind]=count+1;
		
		count++;
		
		shiftup(count);
	}
	
	private void shiftup(int k) {
		
		while(k>1&& data[index[k/2]]<data[index[k]]) {
			
			swap(index,k/2,k);// 交换index 的索引位置
			
			reverse[index[k/2]]=k/2; // 对应的reverse[]值进行了修改
			
			reverse[index[k]]=k;
			
			k/=2;
			
		}
	}
	
	public void shiftdown(int k) {
		
		
		while(2*k<=count) {
			
			int j=2*k;
			
			if(j<=count&& data[index[j]]<data[index[j+1]])
				
				j+=1;
			
			if(data[index[k]]>=data[index[j]]) break;
			
			swap(index,k,j); // swap the position of index k and j
			
			reverse[index[k]]=k;
			
			reverse[index[j]]=j;
			
			k=j;
		}
		
	}
	
	public void shiftdown2(int k) {
		
		
		int element=data[index[k]];
		
		while(2*k<=count) {
			
			int j=2*k;
			
			if(j<=count&& data[index[j]]<data[index[j+1]])
				
				j+=1;
			
			if(data[index[j]]<=element) break;
			
			data[index[k]]=data[index[j]];
			
			k=j;
		}
		
		data[index[k]]=element;
	}
	
	public int extractMax() {
		
		assert(count>=0);// 不能为空
		
		int ret= data[index[1]];
		
		swap(index,1,count);
		
		reverse[index[1]]=1;
		
		reverse[index[count]]=0;
		
		count--;
		
		shiftdown2(1);
		
		return ret;
		
	}
	
	public int ind_extract_max() { // 取出最大元素的索引
		
		assert(count>=0);// 不能为空
		
		int ret= index[1]-1; // 将堆顶的元素 index 取出来
		
		swap(index,1,count);
		
		count--;
		
		shiftdown(1);
		
		return ret;
		
	}
	
	public int getItem(int i) { // 直接查询data中的元素
		
		assert(contain(i));
		
		return data[i+1];
	}
	
	public boolean contain(int i) {
		
		assert(i+1>1 &&i+1<=count);
		
		return reverse[i+1]==0;
	}
	
	public void change(int i,int item) {
		
		assert(contain(i));
		
		i+=1;  
		
		data[i]=item;
		
		for(int j=1;j<=count;j++)  // 由于需要遍历，就是一个ON+logN
			
			if(index[j]==i) {
				
				shiftup(j);
				
				shiftdown(j);
				
				return;
			}
		
	}
	
	public void change2(int i,int item) {
		
		i+=1;
		
		data[i]=item;
		
		int j=reverse[i];
		
		shiftup(j);
		
		shiftdown(j);
		

		
	}
	
	public static void main(String[] args) {
		
		int Cap=20;
		
		Random random =new Random(20);
		
		index_max_heap imh =new index_max_heap(Cap);
		
		for(int i=0;i<Cap;i++) {
		
		imh.insert(i,random.nextInt(100));
		
		}
		
		//hst.print();
		
		//hst.treePrint();
		
		//imh.change(4, 156);
		
		while(!imh.isEmpty()) {
		
		int b=imh.extractMax();
		
		System.out.print(b+" ");
		
		}
	
	}

}
