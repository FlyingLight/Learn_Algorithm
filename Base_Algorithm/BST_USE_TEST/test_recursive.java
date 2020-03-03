package BST_USE_TEST;

import java.util.HashMap;

public class test_recursive {
	
	
	private int count;
		
	private HashMap<Integer,Long> m;
	
	
	public test_recursive() {
		
		this.count=0;
		
		m=new HashMap<>();
		
	}
	
	
	
	public long get_Step(int n) {
		
		if(n<=2)
			
			return n;
		
		
		if(m.containsKey(n)) {
			
			return m.get(n);
	}
		
		else {
		
			long w;
					
			w= get_Step(n-1)+get_Step(n-2);
		
			// return the number of recurse
	
			count+=1;  //  automatically back
		
			m.put(n, w);
		
			return w;
		
		}
		
	}
	
	
	public long get_Step2(int n) {
		
		
		if(n<=2)
			
			return n;
		
		else {
		
		
		long left=get_Step(n-1);
		
		long right= get_Step(n-2);
		
		long sum=left+right;
		
		count+=1; 
		
		return sum;
		
		}
		
	}
	
	
	public int square_num(int n) {
		
		int min=Integer.MAX_VALUE;
		
		if(n==0) return 0;
		
		else {
		
		for(int i=1;i*i<=n;i++) {
			
			
			min=Math.min(square_num(n-i*i)+1,min);
		}
		
		return min;
		
		}
		
	}
	


	public static void main(String[] args) {
		
		//int Step=100;
		
		test_recursive tr =new test_recursive();
		
		//long methd=tr.get_Step2(Step);
		
		//System.out.println(methd+" "+tr.count);
		
		int num=tr.square_num(17);
		
		System.out.print(num);
		

	}

}
