package linklist_array_insert_delete;

public class My_Sqrt {
	
	public double sqrt_v1(double x) {
		
		//if(x==0.0 || x==1.0) return x;
		
		double l=0.0,r=x;
		
		while(l<=r|| Math.abs(r-l)>1e-4) {
			
			double mid= l+(r-l)/2.0;
			
			if(x/mid>mid) 
				
				l=mid+1;
			
			else if(x/mid<mid) r=mid-1.0;
			
		
			
		}
	
		return l;
	}
	
	public double sqrt_v2( double n) {
		
		//  
		
		   double x = 1.0;
		   
	        while (x * x > n || Math.abs(x*x-n)>1e-4) {
	            x = (x + n / x) / 2;
	        }
	        return x;
	    }


	

	public static void main(String[] args) {
	
		 My_Sqrt ms = new My_Sqrt();
		 
		 double s=ms.sqrt_v2(7.0);
		 
		 System.out.println(s);

	}

}
