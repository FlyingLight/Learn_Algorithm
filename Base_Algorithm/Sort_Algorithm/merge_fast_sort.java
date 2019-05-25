/**
 * 
 */
package sort_learn_test;

import java.util.Arrays;

import java.util.Random;

import java.math.*;

/**
 * @author qiguangqin
 *
 */

/*
 
 选择排序，以及插入排序，都是 O(n^2)
 
归并排序 O(nlogn)  算法随着 样本量增大，速度明显，但是会消耗更多的空间

比如归并排序

归并排序，本质就是一个对各地过程
 
 */
public class merge_fast_sort {
	
	
	private void swap(int [] arr,int i,int j) {
		
		
		int temp;
		
		temp=arr[i];
		
		arr[i]=arr[j];
		
		arr[j]=temp;
	}
	
	
	private void merge(int []arr,int l,int mid,int r) {
		
		int [] aux=Arrays.copyOfRange(arr, l, r+1);
		
		for (int i=l;i<=r;i++)
			
			aux[i-l]=arr[i];  // 构建辅助排序数组
		
		
		int i=l; int j=mid+1;  // 创建两个归并索引的位置
		
		for(int k=l;k<=r;k++) {
			
			// arr[k] 位置 应该放哪一个数字
			
			if(i>mid) {
				
				arr[k]=aux[j-l]; 
				
				j++;
			}
			
			else if(j>r) {
				
				arr[k]=aux[i-l];
				
				i++;
			}
			
			
		   else if(aux[i-l]<aux[j-l]) {
				
				arr[k]=aux[i-l];
				
				i++;
			}
			
			else {
				
				arr[k]=aux[j-l];
							
				j++;
			}
		}
		
			
	}
	
	private void merge_sort(int [] arr,int l,int r) {
		
		/*
		
		if(l>=r)
			
		{
			
			return;
		}
		*/
	
		if(r-l<=8) {
			
			this.mod_insert_sort(arr, l,r); // 数据较少的时候，有序的情况 非常大，用插入排序效果会好
			
			//return ;
			
		}
		
		else {
			
			
		int mid=(l+r)/2;
		
		merge_sort(arr,l,mid);
		
		merge_sort(arr,mid+1,r);
		
		
		if(arr[mid]>arr[mid+1]) // 归并排序 保证了arr[ l mid] 和arr[ mid+1 r] 都是有序的,如果是几乎有序，那么arr[mid] <arr[mid+1] 就是有序的，不需要排序，反之需要排序
		
		merge(arr,l,mid,r);
		
		}
		
		
	}
	
	private void merge_sort_bu(int [] arr,int N) {
		
		
		for (int sz=1;sz<=N;sz+=sz) {
			
			
			// sz 按照 1,2,4,8,16 进行递增
			
			for(int i=0;i+sz<N;i+=sz+sz) {
				
				
				// 对arr[i,i+sz-1](一共有sz 个) 和arr[i+sz,i+sz+sz-1] 进行归并排序
				
				this.merge(arr, i, i+sz-1, Math.min(i+sz+sz-1,N));
				
				// 确定 i+sz+sz-1 与N-1 的最小值，保证不会越界
			}
		}
					
	}
	
	
	private boolean is_Sorted(int []arr,int N) {
		
		
		for(int i=0;i<N-1;i++) {
			
			if(arr[i]>arr[i+1]) {
				
				return false;
			}
		} 
		
		return true;
	}
	
	private void select_sort(int[] arr,int N) {
		
		
		for (int i=0;i<N;i++) { // 是一个On^2 级别的算法
			
			int min_index=i;
			
			for(int j=i+1;j<N;j++) {  // 从i 的下一个元素开始找，最小值的index，如果和i 不同就交互位置
				
				
				if(arr[j]<arr[min_index]) {  //  找到最小值 min_index
					
					
					min_index=j;
								
				}
			}
			
			
			if(min_index!=i) {  // 每一次交互都是消耗性能的
				
				/*
				int temp=arr[i];
				
				arr[i]=arr[min_index];
				
				arr[min_index]=temp;
				*/
				
				swap(arr,i,min_index);
			}
			
		}
		
	}
	
	
	private void mod_insert_sort(int[] arr,int N) {
		
		
		for(int i=1;i<N;i++) { // 如果是接近有序的数值，负责度降到 ON
			
			
			int element=arr[i];   //  需要进行插入排序的下标元素
			
			int j; // 存放应该插入位置的下标 ，确保j 位置前的元素是有序的（可以用折半查找优化）
			
			
			for(j=i;j>0 && arr[j-1]>element;j--) {
				
				
				arr[j]=arr[j-1];    // 移动元素的位置到j 
			}
			
			
			arr[j]=element;
		}
		
	}
	
	private void insert_sort(int[] arr,int N) {
		
		
		for (int i=1;i<N;i++) {
			
			int element=arr[i];
			
			for(int j=i;j>0&&arr[j-1]>element;j--) {
				
					
					swap(arr,j,j-1);
			}
		}
		
	}
	
	
	private void mod_insert_sort(int[] arr,int l,int r) {
		
		
		for(int i=l+1;i<=r;i++) { 
			
			int element=arr[i];
			
			int j;
			
			for(j=i;j>l && arr[j-1]>element;j--) {
				
				
				arr[j]=arr[j-1];
			}
			
			arr[j]=element;
			
		}
		
	}
	
	
	private void mid_insert_sort(int[] arr,int N) {
		
		
		for(int i=1;i<N;i++) {
			
			
			int low=0;// 每一次初始化 折半查找时 low=0
			
			int high=i-1;   // 第一次初始化时 
			 
			int element=arr[i]; // 需要进行插入排序的元素，并比较的元素
			
			
			while(low<=high) {  // 找到需要 插入的位置，用low 作为index 来表示
				
				
				int mid=low+(high-low)/2;
				
				if(element<arr[mid]) {
					
					
					high=mid-1;
				}
				
				else {
					
					low=mid+1;
				}
			}
			
			
			for(int j=i;j>low;j--) {  // low index 前面元素进行移动位置，后移
				
				arr[j]=arr[j-1];
			}
			
			arr[low]=element;
		}
		
			
	}
	

	private void mid_insert_sort(int[] arr,int l,int r) {
		
		
		for (int i=l+1;i<=r;i++) {
			
			int element=arr[i];
			
			int low=l;
			
			int high=i-1;
			
			while(low<=high) {
				
				int mid=low+(high-low)/2;  // 避免由于 int 相加产生溢出
				
				if(element>arr[mid]) {
					
					
					low=mid+1;
				}
				
				else {
					
					high=mid-1;
				}
			}
			
			for(int j=i;j>low;j--) {
				
				arr[j]=arr[j-1];
			}
			
			arr[low]=element;
		}
		
		
	}
	
	
	private int partition(int [] arr,int l,int r) {
		
		
		// 对 arr[l,r]部分进行partition操作
		
		Random rnd =new Random(100);
		
		int index=rnd.nextInt(r-l+1)+l; //l,r 闭区间随机数
		
		/*
		int tmp=arr[l];
		
		arr[l]=arr[index];
		
		arr[index]=tmp;
		
		*/
		
		swap(arr,l,index);
		
		
		int v =arr[l];
		
		int j=l;
		
		//  arr[l,j]<v arr[j+1,i)>v 左闭右开, 初始化，这两个数组都是空,i是当前要考察的元素
		
		for(int i=l+1;i<=r;i++) {
			
			int flag=1;  //  对于 arr[i] 和v 相等的情况，造成树的不平衡，使用交叉换位的方法解决
			
			if(arr[i]<v) {
				
				/*
				int temp =arr[j+1];
				
				arr[j+1]=arr[i];
				
				arr[i]=temp;
				*/
				
				swap(arr,i,j+1);
				
				j++;
			}
			
			if(arr[i]==v) {
				
				flag=-flag;
				
				swap(arr,i,j+1);
				
				j++;
			}
		}

			/*
			int temp=arr[j];
			
			arr[j]=arr[l];
			
			arr[l]=temp;
			*/
			
			swap(arr,l,j);
		
		
		return j;
	}
	
	
	private int partition2(int[] arr,int l,int r) {
		
		Random rnd =new Random(100);
		
		int index=rnd.nextInt(r-l+1)+l;//l,r 闭区间随机数
		
		/*
		int tmp=arr[l];
		
		arr[l]=arr[index];
		
		arr[index]=tmp;
		
		*/
		
		swap(arr,l,index);
		
		int v =arr[l];
		
		int i=l+1;int j=r; // 初始化 l 和 j 
		
		// arr[l+1,i)<=v arr[j,r)>=v 一开始 这连个为空
		
		while(true) {
			
			while(i<=r && arr[i]<v) i++;
			
			while(j>=l+1 && arr[j]>v) j--;  // l的标定点被v占据了，只能到l+1
			
				if(i>j) break; // i>j后，表示循环遍历结束了
				
					/*
					int temp =arr[i];
					
					arr[i]=arr[j];
					
					arr[j]=temp;
					
					*/
				
					swap(arr,i,j);
					
					i++;j--;
						
		}
		
		// j最后一个小于v 的元素
		
		swap(arr,l,j);
		
	
		return j;
	}
	
private int partition20(int[] arr,int l,int r) {
		
		Random rnd =new Random(100);
		
		int index=rnd.nextInt(r-l+1)+l;//l,r 闭区间随机数
		
		/*
		int tmp=arr[l];
		
		arr[l]=arr[index];
		
		arr[index]=tmp;
		
		*/
		
		swap(arr,l,index);
		
		int v =arr[l];
		
		int i=l+1;int j=r; // 初始化 l 和 j 
		
		// arr[l+1,i)<=v arr[j,r)>=v 一开始 这连个为空
		
		while(i<=j) {
			
			while(i<=r && arr[i]<v) i++;
			
			while(j>=l+1 && arr[j]>v) j--;  // l的标定点被v占据了，只能到l+1
			
				if(i>j) continue; // i>j后，表示循环遍历结束了
				
					/*
					int temp =arr[i];
					
					arr[i]=arr[j];
					
					arr[j]=temp;
					
					*/
				
					swap(arr,i,j);
					
					i++;j--;
						
		}
		
		// j最后一个小于v 的元素
		
		swap(arr,l,j);
		
	
		return j;
}
	
	private void quick_sort3(int[] arr,int l,int r){

		if(r-l<=15) {
			
			this.mod_insert_sort(arr, l, r);
			
				return;
		}
		
		Random rnd =new Random(100);
		
		int index=rnd.nextInt(r-l+1)+l;//l,r 闭区间随机数
		
		
		/*
		int tmp=arr[l];
		
		arr[l]=arr[index];
		
		arr[index]=tmp;
		
		*/
		
		swap(arr,l,index);
		
		// arr[l+1,lt] the part of <v  init_ =NONE

		// arr[lt+1,i-1] the part of =v init_NONE

		// arr[gr,r] the part of >v init=NONE

		int v=arr[l];

		int i=l+1;

		int lt=l;

		int gt=r+1;

		while(i<gt ){


			if(arr[i]<v){

				swap(arr,i,lt+1);

				lt++; i++;
			}

			else if(arr[i]>v){

				swap(arr,i,gt-1);
				
				gt--;
			}
			
			else {

			i++;
			
			}
		}
		
		swap(arr,l,lt);
		
		quick_sort3(arr,l,lt-1);  // l 到 lt-1 和gt 到r 之间的元素做
		
		quick_sort3(arr,gt,r);
	}

	
	
	private void quick_sort(int[] arr,int l,int r) {
		
		// 对 arr[l,r] 的部分进行快速排序
		
		// 使得 arr[l,p-1]<arr[p]; arr[p+1,r]>arr[p]
		
		if (l>=r)
			
			return;
		/*
		
		if(r-l<=15) {
			
			this.mod_insert_sort(arr, l, r);
			
			return;
		}
		
		*/
		
		int p=partition(arr,l,r);   // 返回一个索引值 p 
		
		quick_sort(arr,l,p-1);  // 对 l到p-1 进行快速排序
		
		quick_sort(arr,p+1,r);// 对p+1 到 r 进行快速排序
	}
	
	
	private void quick_sort2(int[] arr,int l,int r) {
		
			
			if(r-l<=15) {
			
			this.mod_insert_sort(arr, l, r);
			
			return;
		}
		
		
		/*
			if(l>=r) {
				
				return;
			}
			
			*/
			
		int p=partition2(arr, l, r);
		
		quick_sort2(arr, l, p-1);
		
		quick_sort2(arr, p+1, r);
	}
	
	
	private void bubble_sort(int[] arr,int N) {
		
		
		for (int i=N-1;i>0;i--) {
			
			boolean flag=false;
			
			for(int j=0;j<i;j++) {
				
				if(arr[j]>arr[j+1]) {
					
					swap(arr,j,j+1);
					
					flag=true;
					
				}
				
			}
			
			if(!flag) {
				
				return;
			}
			
		}
			
	}
	
	private void shell_sort(int [] arr,int N){

		int d=N/2;

		while(d>=1){

			for(int i=d;i<N;i++){

				int j=i;

				while(j>=d && arr[j-d]>arr[j]){

					swap(arr,j,j-d);

					j-=d;
				}

			}
			d/=2;
		}

	}
	
	private void shiftdown(int[] arr,int N,int k) {
		
		
		while((2*k+1)<N) {
			
			int j=2*k+1;
			
			if(j+1<N &&arr[j]<arr[j+1])
				
				j+=1;
			
			if(arr[k]>=arr[j]) break;
			
			swap(arr,k,j);
			
			k=j;
		}
		
	}
	
	
	public void use_heap_sort(int[] arr,int N) {
		
		if(!is_Sorted(arr,N)) {
		
		heap_sort_test hst =new heap_sort_test(N);
		
		for(int i=0;i<N;i++)
			
			hst.insert(arr[i]);
		
		for(int i=N-1;i>=0;i--)
			
			arr[i]=hst.extractMax();
		
		}
		
		else
			
			return;
	}
	
	public void use_heap_sort2(int[] arr,int N) {
		
		if(!is_Sorted(arr,N)) {
			
			heap_sort_test hst= new heap_sort_test(arr,N);
			
			for(int i=N-1;i>=0;i--)
				
				arr[i]=hst.extractMax();
		}
		
		else
			
			return;	
	}
	
	public void use_heap_sort3(int [] arr,int N) { // 原地堆排序
		
		
		// heapify
		
		if(!this.is_Sorted(arr, N)) {
		
		for(int i=(N-1)/2;i>=0;i--)
			
			shiftdown(arr,N,i);
		
		// swap and shiftdown get sorted sequence
		
		for(int i=N-1;i>0;i--) { // >=1 的位置与arr[0]进行交换，然后shiftdown操作
			
			swap(arr,0,i);
			
			shiftdown(arr,i,0);
			
			}
		}
		
		else
			
			return;
	}
	
	public void use_bubble_sort(int[] arr,int N) {
		
		if (!is_Sorted(arr,N)) {
			
			this.bubble_sort(arr, N);
				
		}
		
		else
			
			return;
	}
		
		
	public void use_merge_sort(int[] arr,int N) {
		
		
		if (!is_Sorted(arr,N)) {
			
			
			merge_sort(arr,0,N-1);
			
			//merge_sort_bu(arr, N-1);
			
		}
		
		else
			return;
	}
	
	
	public void use_select_sort(int []arr,int N) {
		
		
	if (!is_Sorted(arr,N)) {
			
			
			this.select_sort(arr,N);
			
		}
		
		else
			return;
	}
	
	
	public void use_shell_sort(int[] arr,int N) {
		
		if (!is_Sorted(arr,N)) {
			
			
			this.shell_sort(arr,N);
			
		}
		
		else
			return;
		
	}
	
	public void use_insert_sort(int []arr,int N) {
		
		
		if (!is_Sorted(arr,N)) {
				
				
				this.mid_insert_sort(arr, N);
				
				//this.mod_insert_sort(arr, N);
				
			}
			
			else
				
				return;
		}
	
	
	public void use_quick_sort(int [] arr,int N) {
		
		
		if (!is_Sorted(arr,N)) {
			
			
			this.quick_sort(arr, 0, N-1);
			
		}
		
		else
			
			return;
		
	}
	
	
	public void use_quick_sort_du(int [] arr,int N) {
		
		
		if (!is_Sorted(arr,N)) {
			
			
			this.quick_sort2(arr, 0, N-1);
			
		}
		
		else
			
			return;
		
	}
	
	public void use_quick_sort_tri(int[] arr,int N) {
		
		
		if (!is_Sorted(arr,N)) {
			
			
			this.quick_sort3(arr, 0, N-1);
			
		}
		
		else
			
			return;
	}
		

	public void print(int []arr) {
		
		for(int j:arr)
			
			System.out.print(j+" ");
		
	}
	
	
	public static void main(String[] args) {
		
		int N=20;
		
		Random random =new Random(20);
		
		int []b= new int[N];
		
		
		for(int i=0;i<N;i++) {
			
			b[i]=random.nextInt(100);
		}
		
		
	//	long start =System.currentTimeMillis();
		
		System.out.print(" before sorted\n ");
		
		for(int j:b) // 排序前的数组
			
			System.out.print(j+" ");
		
		merge_fast_sort mfs=new merge_fast_sort();
		
		//mfs.use_select_sort(b, N);
		
		//mfs.use_merge_sort(b, N);
		
		ts.use_quick_sort_du(b, N);
		
		//mfs.use_insert_sort(b, N);
		
		//mfs.use_quick_sort_tri(b, N);
		
		//ts.use_heap_sort3(b, N);
		
		//mfs.use_shell_sort(b, N);
		
		//mfs.use_shell_sort(b, N);
		
		long end =System.currentTimeMillis();
	
		System.out.print(" \n after sorted \n");
		
	//	System.out.print(end-start+"ms\n");
		
		mfs.print(b);
	
	}
	
}
