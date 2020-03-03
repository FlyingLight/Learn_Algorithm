/**
 * 
 */
package base_dp_1;

/**
 * @author qiguangqin
 *
 */
public class Num_decode {

	/**
	 * @param args
	 * 
	 * 
	 */
	
	private String s;
	
	private int[] dp1;
	
	public Num_decode(String s) {
		
		this.s=s;
		
		dp1= new int[s.length()+1];
		
		dp1[s.length()]=1;
	}
	
	public int get_decode_dp() {
		
		int pre=1,cur=1;
		
		
		for(int i=1;i<s.length();i++) {
			
			int temp=cur;
			
			if(s.charAt(i)=='0') 
				
				if(s.charAt(i-1)=='1' || s.charAt(i-1)=='2') // dp[i]=dp[i-2];
					
					cur=pre;
				
				else  //dp[i]=0;
					
					return 0;
			
			
			else if (s.charAt(i-1)=='1'||( s.charAt(i-1)=='2'&&( s.charAt(i)>='1' && s.charAt(i)<='6')))
				
				
				//dp[i]=dp[i-2]+dp[i-1];
				
				cur=pre+cur;
			
			//dp[i-1]=dp[i];
				
			pre=temp;
		}
		
		//return dp[s.length()-1];
		
		return cur;
		
	}
	
	
	public int get_decode_recur_v2(int start) {
		
		
		if(start==s.length())  return 1;
		
		if(s.charAt(start)=='0')
			
			return 0;
		
		int res2=0;
		
		int res1=get_decode_recur_v2(start+1);
		
		if(start<s.length()-1) {
			
			int ten=(s.charAt(start)-'0')*10;
			
			int one =s.charAt(start+1)-'0';
			
			if(one+ten<=26)  res2=get_decode_recur_v2(start+2);
		}
		
		return res1+res2;
		
		
	}
	
	
	public int get_decode_dp2() {
		
	
		int len=s.length();
		
		if(s.charAt(len-1)!='0')
			
			dp1[len-1]=1;
		
		for(int i=len-2;i>=0;i--) {
			
			// temp=pre;
			
			if(s.charAt(i)=='0') {
				
				dp1[i]=0;
				
				//cur=0;
				
				continue;
				
			}
				
			if((s.charAt(i)-'0')*10+s.charAt(i+1)-'0'<=26) {
				
				dp1[i]=dp1[i+1]+dp1[i+2];
				
				//cur=cur+end;
			}
			
			else {    //s.charAt(i)-'0'*10+s.charAt(i+1)-'0'>26
				
				dp1[i]=dp1[i+1];
				
				//cur=temp;
			}
		
			
		}
		
		return dp1[0];
		
	}
	
	
	public static void main(String[] args) {
		
		String s="27";
		
		Num_decode nd = new Num_decode(s);
		
		//int num=nd.get_decode_dp();
		
		int num= nd.get_decode_recur_v2(0);
		
		int num2=nd.get_decode_dp2();
		
		System.out.println(num+" "+num2);
		
		

	}

}
