/**
 * 
 */
package sort_learn_test;

/**
 * @author qiguangqin
 *
 */


import java.util.Random;

import java.util.concurrent.locks.ReentrantLock;

public class select_sort {
	
	private int [] a;
	
	ReentrantLock lock = new ReentrantLock(); // 声明reentrant 锁
	
	public int get_length() {
		
		return a.length;
	}
	
	public boolean isSorted() {
		
		for(int i=0;i<get_length()-1;i++) {
			
			if(a[i]>a[i+1]) {
				
				return false;
			}
		}
		
		return true;
	}
	
	public void print() {
		
		for (int i=0;i<get_length();i++) {
			
			System.out.print(a[i]+" ");
			
		}
		
	}

	public int[] getA() {
		return a;
	}

	
	
	
	public void setA(int[] a) {
		
		lock.lock();
		
		try {
			
			this.a = a;
			
		}finally {
			
			lock.unlock();
		}
	
	}
	
	
	
	public void select_sort() { //  On^2  最好是 Onlogn
		
		
		lock.lock();
		
		try {
		
		for(int i=0;i<get_length();i++) {
			
			
			int min_index=i;
			
			for (int j=i+1;j<get_length();j++) {
				
				
				if(a[j]<a[min_index]) {
					
					
					min_index=j;
				}
				
			}
			
			if(min_index!=i) {
				
				int temp=a[i];
				
				a[i]=a[min_index];
				
				a[min_index]=temp;
			}
		}
		
	}finally {
		
		lock.unlock();
	}
		
	}
	
	public void insert_sort() {
		
		/*
		 
		插入排序，将插入的元素，与之前数据进行排序
		 
		 */
		
		ReentrantLock lock = new ReentrantLock();
		
		try {
		
		for(int i=1;i<get_length();i++) {
			
			
			for(int j=i;j>0;j--) {
				
				
				if(a[j]<a[j-1]) {
					
					int temp=a[j];
					
					a[j]=a[j-1];
					
					a[j-1]=temp;
				}
				
				else
					
					break;
			}	
			
		}
		
	}finally {
		
		lock.unlock();
	}
	}
	
	public void mod_insert_sort() {   // 减少交换次数，每一次都是三次赋值操作，对于有序的算法，效率更高
		
		
		try {
		
		for (int i=1;i<get_length();i++) {
			
			int element=a[i];
			
			int j;
			
			for(j=i;j>0&&a[j-1]>element;j--) { // && a[j-1]<element 可以提前终止循环
				
				a[j]=a[j-1];
				
			}
			
			a[j]=element;
		}
		
	}finally {
		
		lock.unlock();
	}
	
	}
	
	
	public static void main(String[] args) {
		
		/*
	    int N=80000;
		
		Random rand1= new Random(200); // 传入随机种子 20
		
		int [] a= new int [N];
		
		for(int i=0;i<N;i++) {
			
			a[i]=rand1.nextInt(3);
		}
		
		
		
		select_sort ss= new select_sort();
		
		ss.setA(a);
		
	    //ss.print();
		
		
		//System.out.println("\n");
		
		long start = System.currentTimeMillis();
		
		ss.select_sort();
		
		long end = System.currentTimeMillis();
		
		float second=(end-start)/1000F;
		
		System.out.println("time elapse="+ second+" second");
		
		//ss.print();
		 */
	}

	
}

class test_select_sort implements Runnable{
	
	
	
	select_sort ss1;
	
	
	public select_sort getSs1() {
		return ss1;
	}


	public void setSs1(select_sort ss1) {
		this.ss1 = ss1;
	}
	
	

	@Override
	public void run() {
		
		ss1.select_sort();
		
		ss1.print();
		
	}
}


class test_insert_sort implements Runnable{
	
	select_sort ss1;
	
	public select_sort getSs1() {
		
		
		return ss1;
	}


	public void setSs1(select_sort ss1) {
		this.ss1 = ss1;
	}
	
	@Override
	
	public void run() {
		
		ss1.mod_insert_sort();
		
		ss1.print();
		
	}
}

