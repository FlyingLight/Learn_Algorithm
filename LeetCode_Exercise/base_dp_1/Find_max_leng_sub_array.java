package base_dp_1;

public class Find_max_leng_sub_array {
	
	private int []A;
	
	private int[]B;
	
	public Find_max_leng_sub_array(int[]A,int[]B) {
		
		this.A=A;
		
		this.B=B;
	}
	
	public int findLength_dp1() {
		
		
		/*
		 
		    *   0   1   1   1   1
		-------------------------------
		*   0   0   0   0   0   0      
		-------------------------------
		1   0   0   1   1   1   1
		-------------------------------
		0   0   1   0   0   0   0
		-------------------------------
		1   0   2   1   1   1   1
		-------------------------------
		0   0   1   0   0   0   0
		-------------------------------
		1   0   0   2   1   1   1
		 
		 *
		 */
		
		int m=A.length,n=B.length;
		
		int res=0;
		
		int[][]dp1= new int[m+1][n+1];
		
		for(int i=1;i<=m;i++) {
			
			for(int j=1;j<=n;j++) {
				
				if(A[i-1]==B[j-1]) {
					
					dp1[i][j]=dp1[i-1][j-1]+1;
					
					res=Math.max(res, dp1[i][j]);
					
				}
			}
			
		}
		
		return res;
	}
	
	public int findLength_dp2() {
		
		int m=A.length,n=B.length;
		
		int res=0;
		
		int[]dp2= new int[n+1];
		
		for(int i=1;i<=m;i++) {
			
			int pre=0;dp2[0]=0;
			
			for(int j=1;j<=n;j++) {
				
				int temp=dp2[j];
				
				if(A[i-1]==B[j-1]) 
					
					dp2[j]=pre+1;
					
				else
					
					dp2[j]=0;  // Not forget
				

				res=Math.max(res, dp2[j]);
				
				pre=temp;
			}
		}
		
		return res;
	}
	

	public static void main(String[] args) {
		
		//int[]A= {1,2,3,2,1};
		
		int[]A={1,0,1,0,1};
		
	//	int[]B= {3,2,1,4,7};
		
		int[]B={0,1,1,1,1};
		
		Find_max_leng_sub_array fmlsb= new Find_max_leng_sub_array(A,B);
		
		int count= fmlsb.findLength_dp1();
		
		System.out.println(count);

	}

}
