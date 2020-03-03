package palindrome;

public class ispardome {
	
	public boolean isPardome(int s) {
		
		if(s<0)
			
			return false;
		
		else {
		
			int num,y=0;
		
			num=s;
		
			while (num>0) {
			
				y=y*10+num%10;
					
				num/=10;
			
			}
			
			if(s==y)
				
				return true;
			
			else
				 return false;
		
		}
					
	}
	
	public static void main(String[] args) {
		
		ispardome pp =new ispardome();
		
		int s=-121;
		
		System.out.print(pp.isPardome(s));
	}

}
