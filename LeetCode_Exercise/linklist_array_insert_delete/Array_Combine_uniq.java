package linklist_array_insert_delete;

import java.util.HashSet;
import java.util.Set;

public class Array_Combine_uniq {
	
	
	public int[] combine(int []a,int[] b) {
		
		int m=a.length;
		
		int n=b.length;
		
		int []c =new int[m+n];
		
		int k=m;
		
				
		Set<Integer> temp_set =new HashSet<>();
		
		for(int i=0;i<m;i++) {
			
			c[i]=a[i];
			
			temp_set.add(a[i]);
		
		}
		
		for(int j=0;j<n;j++) {
			
			if(!temp_set.contains(b[j])) {
				
				c[k++]=b[j];
			}
		}
		
	
		
		return c;
	}

	public static void main(String[] args) {
		
		
		int[] a= {5,6,7,8,9};
		
		int[] b= {8,9,10,12};
		
		Array_Combine_uniq au= new Array_Combine_uniq();
		
		int[] c= au.combine(a, b);
		
		for(int i:c)
			
			if(i!=0)
			
			System.out.println(i);

	}

}
