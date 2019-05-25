/**
 * 
 */
package sort_learn_test;



import java.util.Random;

import org.junit.Before;

import org.junit.Test;

/**
 * @author qiguangqin
 *
 */
public class sort_get {

	
	private int []b;
	
	private merge_fast_sort mfs;
	
	@Before
	
	public void setUp() throws Exception {
		
		
		mfs =new merge_fast_sort();
		
		int N=20;
		
		Random random =new Random(20);
		
		b= new int[N];
		
		for(int i=0;i<N;i++) {
			
			b[i]=random.nextInt(100);
		}
	}

	@Test
	
	public void testUse_merge_sort() {
		
		System.out.println(" MERGE SORT");
		
		mfs.use_merge_sort(b, b.length);
		
		mfs.print(b);
		
	}
	
	@Test
	
	public void testUse_select_sort() {
		
		
		System.out.println("\n"+" SELECT SORT");
		
		mfs.use_select_sort(b, b.length);
		
		mfs.print(b);
		
	}
	
	@Test
	
	public void testUse_quick_sort() {
		
		
		System.out.println("\n"+" QUICK SORT"); // 快速派苏，对于近乎有序，以及重复元素的执行效率不高，主要是会导致递归树不平衡
		
		mfs.use_quick_sort(b, b.length);
		
		mfs.print(b);
		
	}
	
	@Test
	
	public void testUse_quick_sortd_du() {
		
		System.out.println("\n"+" QUICK SORT DU");// 使用双路快速排序，解决重复元素的元素问题
		
		mfs.use_quick_sort_du(b, b.length);
		
		mfs.print(b);
		
	}
	
	@Test
	
	public void testUse_quick_sort_tri() {
		
		
		System.out.println("\n"+" QUICK SORT TRI");
		
		mfs.use_quick_sort_tri(b, b.length);
		
		mfs.print(b);
		
	}
	
	@Test

	public void testUse_bubble_sort() {
		
		System.out.println("\n"+" BUBBLE SORT");
		
		mfs.use_bubble_sort(b, b.length);
		
		mfs.print(b);
		
	}
	
	@Test
	
	public void testUse_heap_sort() {
		
		System.out.println("\n"+" HEAP SORT");
		
		mfs.use_heap_sort3(b, b.length);
		
		mfs.print(b);
		
	}
	

}
