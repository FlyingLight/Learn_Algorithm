package BST_USE_TEST;

public class test_recursive {
	
	
	private int count=0;
	
	public long get_Step(int n) {
		
		if(n<=2)
			
			return n;
			
		long w;
			
		w= get_Step(n-1)+get_Step(n-2);
		
		// reuturn the number of recurse
	
		count+=1;  //  automatic back, this not run
		
		
		return w;
		
	}

	public static void main(String[] args) {
		
		int Step=10;
		
		test_recursive tr =new test_recursive();
		
		long methd=tr.get_Step(Step);
		
		System.out.println(methd+" "+tr.count);
		

	}

}
