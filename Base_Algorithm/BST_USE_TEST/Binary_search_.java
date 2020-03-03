/**
 * 
 */
package BST_USE_TEST;

/**
 * @author qiguangqin
 *
 */
public class Binary_search_ {
	

	private int binary_test(int[] arr,int l,int r,int element) {
		
		while(l<=r) {
			
			//在arr[l,r]中查找元素 elemenet
			
			int mid=l+(r-l)/2;   // 避免(l+r)/2 产生溢出
			
			if(arr[mid]==element)
				
				return mid;
			
			else if(arr[mid]>element)
				
				// 在 arr[l,mid-1] 中继续进行查找，mid 在上一轮已经查找过了
				
				r=mid-1;
				
			else
				
				l=mid+1;
		
	}
		
	return -1;
	
	}
	
	private int binary_test_incr(int []arr ,int l,int r,int element) {  // logN 
		
		int index;
		
		int t;
		
		int mid=l+(r-l)/2;
		
		if(l>r)
			
			return -1;
		
		else if(arr[mid]>element) {
			
			r=mid-1;
		
			t=binary_test_incr(arr,l,r,element);
			
			return t;
			
			}
			
		else if(arr[mid]<element) {
			
			l=mid+1;
			
			t=binary_test_incr(arr,l,r,element);
			
			return t;
			
		}
		
		else {
			
			index=mid;
			
		//	System.out.print(index);
			
			return index;
		}
		
	}
		
		
	public void use_binary_test(int [] arr,int element) {
		
		int index=0;
		
		if((this.binary_test(arr, 0, arr.length-1, element)==-1))
				
				System.out.println("no such element");
		
		else 
			
			this.binary_test(arr, 0, arr.length-1, element);
		
		//System.out.println("index="+index);
	}
	
	public static void main(String[] args) {
		
		int []arr= {1,2,3,4,5,6,7,8,9,0};
		
		Binary_search_  bs= new Binary_search_ ();
		
		int index=bs.binary_test(arr, 0, arr.length-1, 6);
		
		int index_2=bs.binary_test_incr(arr, 0, arr.length-1, 6);
		
		System.out.print(index+" "+index_2);
	}
	
	
}
