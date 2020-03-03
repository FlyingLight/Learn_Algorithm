/**
 * 
 */
package base_dp_1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiguangqin
 *
 */
public class Num_distinct_sub_String_dp {

	/**
	 * @param args
	 * 
	 Leetcode 115
	 */
	
	private String s,t;
	
	//private int[][]memo;// use memo condense the period of recursion
	
	public Num_distinct_sub_String_dp(String s,String t){
		
		this.s=s;
		
		this.t=t;
		
		//this.memo=new int[s.length()+1][t.length()+1];
		
		//for(int i=0;i<=s.length();i++)
		
		//Arrays.fill(memo[i], -1);
	
	}
	
	public int get_Res_dp_1() { //dp1
		
	   int[][] dp1=new int[t.length()+1][s.length()+1];
		
	   Arrays.fill(dp1[0], 1);
		
		
		/*
		     ""  b  a  b  g  b  a  g (s) (j)
		-------------------------------
		 ""   1  1  1  1  1  1  1  1
		-------------------------------
		  b   0  1  1  2  2  3  3  3
		-------------------------------
		  a   0  1  1  1  1  1  4  4
	    -------------------------------
		  g   0  0  0  0  1  1  1  5
		------------------------------- 
		 (t)(i)
		 
		 dp1[i][j]=dp1[i-1][j-1]+dp1[i][j-1](if s.charAt(i-1) ==t.charAt(j-1) )
		 
		 dp1[i][j]=dp1[i][j-1](if s.charAt(i-1)!=t.charAt(j-1) )
		 */
	   
		for(int i=1;i<=t.length();i++) {
			
			for(int j=1;j<=s.length();j++) {
				
				if(t.charAt(i-1)==s.charAt(j-1))
					
					dp1[i][j]=dp1[i-1][j-1]+dp1[i][j-1];
				
				else
					
					dp1[i][j]=dp1[i][j-1];
			}
		}
		
		return dp1[t.length()][s.length()];
	}
	
	public int get_Res_dp_2() {
		
		/*
	        "" b  a  b  g  b  a  g (s) (j)
		-----------------------------------
		""  1  1  1  1  1  1  1  1 _init 
		 --->
		b   0  1  1  2  2  3  3  3
		 --->
		a   0  0  1  1  1  1  4  4
		 --->
		g   0  0  0  0  1  1  1  5   
		  	  
		*/
	   int[]dp2=new int[s.length()+1];;
		
		Arrays.fill(dp2, 1);
		
		int pre=1; // previous row -->dp[i-1][j-1]
		
		for(int i=1;i<=t.length();i++) {
			
			for(int j=0;j<=s.length();j++) {
				
				int temp=dp2[j];
				
				if(j==0) dp2[j]=0;
				
				else {
					
					if(t.charAt(i-1)==s.charAt(j-1))
						
						dp2[j]=dp2[j-1]+pre;
					
					else
						
						dp2[j]=dp2[j-1];
				}
				
				pre=temp;
			}
		}
		return dp2[s.length()];
	}
	
	public int get_Res_dp_2_1() {
		
		/*
	      ""  b  a  b  g  b  a  g (s) (j)
		-----------------------------------
	   ""  1  1  1  1  1  1  1  1 _init 
		 --->
		b  0  1  1  2  2  3  3  3
		 --->
		a  0  0  1  1  1  1  4  4
		 --->
		g  0  0  0  0  1  1  1  5   
		  	  
		*/
		
		int []dp2_1= new int[s.length()+1];
		
		Arrays.fill(dp2_1, 1);
		
		for(int i=1;i<=t.length();i++) {
			
			int pre=dp2_1[0]; // initiate left column
			
			dp2_1[0]=0;
			
			for(int j=1;j<=s.length();j++) {
				
				int temp=dp2_1[j];
				
				if(s.charAt(j-1)==t.charAt(i-1))
					
					dp2_1[j]=dp2_1[j-1]+pre;
				
				else
					
					dp2_1[j]=dp2_1[j-1];
				
				pre=temp;
			}
		}
		
		return dp2_1[s.length()];
	}
	
	
	public int get_Res_dp_2_2() {
		
		/*
	       b  a  b  g  b  a  g  "" (s) (j)
	 -----------------------------------
	    b  5  2  2  1  1  0  0  0 
	 -----------------------------------                     
		a  3  3  1  1  1  1  0  0
	 -----------------------------------                       
		g  2  2  2  2  1  1  1  0
     -----------------------------------                        
	   ""  1  1  1  1  1  1  1  1   _init 
	   
	   (i)
	   
		*/
		
		int [][]dp2_2 =new int[t.length()+1][s.length()+1]; 
		
		Arrays.fill(dp2_2[t.length()], 1);
		
		for(int i=t.length()-1;i>=0;i--) {
			
			for(int j=s.length()-1;j>=0;j--) {
				
				if(t.charAt(i)==s.charAt(j))
					
					dp2_2[i][j]=dp2_2[i][j+1]+dp2_2[i+1][j+1];
				
				else
					
					dp2_2[i][j]=dp2_2[i][j+1];
			}
		}
		
		return dp2_2[0][0];
	}
	
	
	public int get_Res_dp_2_3() {
		
		/*
	       b  a  b  g  b  a  g  "" (s) (j)
		-----------------------------------
	    b  5  2  2  1  1  0  0  0 
		                        <---
		a  3  3  1  1  1  1  0  0
		                        <---
		g  2  2  2  2  1  1  1  0
		                        <---
		"" 1  1  1  1  1  1  1  1   _init 
		*/
		
		int []dp2_3 = new int[s.length()+1];
		
		Arrays.fill(dp2_3, 1);
		
		for(int i=t.length()-1;i>=0;i--) {
			
			int pre=dp2_3[s.length()];
			
			dp2_3[s.length()]=0;
			
			for(int j=s.length()-1;j>=0;j--) {
				
				int temp=dp2_3[j];
				
				if(s.charAt(j)==t.charAt(i))
					
					dp2_3[j]=dp2_3[j+1]+pre;
				
				else 
					dp2_3[j]=dp2_3[j+1];
				
				pre=temp;
				
			}
		}
		
		return dp2_3[0];
	}
	
	
	 public int get_Res_dp_3() { //dp3
		
		 int [][]dp3=new int[s.length()+1][t.length()+1];
		 
	   /*
		 
	     ""  b  a  g  (t)(j)
	--------------------
	 ""   1  0  0  0 
	--------------------
	  b   1  1  0  0 
	--------------------
	  a   1  1  1  0 
    --------------------
	  b   1  2  1  0  
	--------------------
	  g   1  2  1  1  
	--------------------
	  b   1  3  1  1
	--------------------
	  a   1  3  4  1 
	--------------------
	  g   1  3  4  5    
	--------------------
	 (t)(i)
	 
	 dp3[i][j]=dp3[i-1][j-1]+dp3[i-1][j](if s.charAt(i-1) ==t.charAt(j-1) )
	 
	 dp3[i][j]=dp3[i-1][j](if s.charAt(i-1)!=t.charAt(j-1) )
	 */
		for(int i=0;i<s.length();i++)
			
			dp3[i][0]=1;
		
		//for(int i=0;i<s.length();i++) {
			
		for(int i=1;i<=s.length();i++){	
			
			//for(int j=0;j<t.length();j++) {
				
			for(int j=1;j<=t.length();j++){	
				
				//if(s.charAt(i)==t.charAt(j)) {
					
				if(s.charAt(i-1)==t.charAt(j-1)){
					//dp3[i+1][j+1]=dp3[i][j]+dp3[i][j+1];
					
					dp3[i][j]=dp3[i-1][j-1]+dp3[i-1][j];
				}
				
				else {
					
					//dp3[i+1][j+1]=dp3[i][j+1];
					
					dp3[i][j]=dp3[i-1][j];
				}
			}
		}
		
		return dp3[s.length()][t.length()];
		
	}
	
	
	public int get_Res_dp_4() {  // T main sequence
		
		int[] dp4= new int[t.length()+1];
		
		dp4[0]=1;
		
		/*
		
	     ""  b  a  g  (t)(j)
	 --------------------
	 ""   1  0  0  0  init
	                <----
	  b   1  1  0  0 
	                <----
	  a   1  1  1  0 
                    <----
	  b   1  2  1  0  
	                <----
	  g   1  2  1  1  
	                <----
	  b   1  3  1  1
	                <----
	  a   1  3  4  1 
	                <----
	  g   1  3  4  5    
	                <----
		dp4[0]=1;
		
		dp4[j]+=dp[j-1](if s.charAt(i-1)==t.charAt(j-1) )
		
		dp4[j]=dp4[j] (if s.charAt(i-1)!=t.charAt(j-1) ) no need to express
		
		*/
		
		for(int i=1;i<=s.length();i++) {
			
			for(int j=t.length();j>0;j--) {
				
				if(t.charAt(j-1)==s.charAt(i-1))
				
				dp4[j]+=dp4[j-1];
			}
		}
		
		return dp4[t.length()];
	}
	
	
	
	
	public int get_Res_dp_5() {
		
		int[][]dp5=new int[s.length()+1][t.length()+1];
		
		/*
		 
	      b  a  g  "" (t)(j)
	 -----------------
	  b   5  3  2  1  
      -----------------              
	  a   2  3  2  1 
	 ------------------           
	  b   2  1  2  1
     -------------------              
	  g   1  1  2  1  
	 -------------------               
	  b   1  1  1  1  
	 -------------------             
	  a   0  1  1  1
	 -------------------              
	  g   0  0  1  1 
	 -------------------               
	 ""   0  0  0  1    _init 
	 -------------------               
		
		dp5[i][j]=dp5[i+1][j+1]+dp5[i+1][j] (if s.charAt(i)==t.charAt(j) )
		
		dp5[i[j]=dp5[i+1][j]
		
	 */
		
		for(int i=0;i<=s.length();i++)
			
			dp5[i][t.length()]=1;
		
		for(int i=s.length()-1;i>=0;i--) {
			
			for(int j=t.length()-1;j>=0;j--){
				
				if(t.charAt(j)==s.charAt(i)) 
					
					dp5[i][j]=dp5[i+1][j+1]+dp5[i+1][j];
				
				else 
					
					dp5[i][j]=dp5[i+1][j];
			}
		}
		
		return dp5[0][0];
	}
	
	
	
	public int get_Res_dp_7() {
		
		/*
		  b  a  g  "" (t)(j)
	 -------------------
	  b   5  3  2  1  
                   ---->   ^            
	  a   2  3  2  1 
                   ---->   ^        
	  b   2  1  2  1
                   ---->   ^         
	  g   1  1  2  1  
	               ---->   ^            
	  b   1  1  1  1  
	               ---->   ^
	  a   0  1  1  1
	               ---->   ^            
	  g   0  0  1  1 
	               ---->   ^            
	 ""   0  0  0  1 (init)    
	               ---->   ^
	*/
		
		int[] dp7= new int[t.length()+1];
		
		dp7[t.length()]=1;
		
		for(int i=s.length()-1;i>=0;i--) {
			
			for(int j=0;j<t.length();j++) {
				
				if(t.charAt(j)==s.charAt(i))
					
					dp7[j]+=dp7[j+1];
			}
		}
		
		return dp7[0];
	}
	
	public int get_Res_recur() {
		
		if(t.length()==0) return 1;
		
		if(s.length()==0) return 0;
		
		return _get_Res_recur(0,0);
	}
	
	private int _get_Res_recur(int s_i,int t_j) {
		
		
		if(t_j==t.length()) return 1; // must put first,because if s is null and t is null too return 1;
		
		if(s_i==s.length()) return 0;
		
		int res=0;

		if(s.charAt(s_i)==t.charAt(t_j)) 
			
			 res=_get_Res_recur(s_i+1,t_j+1)+_get_Res_recur(s_i+1,t_j);
		
		else 
			
			res=_get_Res_recur(s_i+1,t_j);
		
		return res;
		
	}
	
	
	public int get_Res_recur_memo1() {
		
		if(t.length()==0) return 1;
		
		// must put first,because if s is null and t is null too return 1;
		
		// t is finished  return 1
		
		if(s.length()==0) return 0;
		
		Map<String,Integer>memo= new HashMap<>();
		
		return _get_Res_recur_memo1(0,0,memo);
		
		
	}
	
	private int _get_Res_recur_memo1(int s_i,int t_j,Map<String,Integer>memo) {
		

		if(t_j==t.length()) return 1; 
		
		if(s_i==s.length()) return 0;
			
		String key= s_i+" "+t_j;
		
		if(memo.containsKey(key)) {
			
			return memo.get(key);
		}
		
		int res=0;
			
		if(t.charAt(t_j)==s.charAt(s_i)) 
					
			res=_get_Res_recur_memo1(s_i+1,t_j+1,memo)+_get_Res_recur_memo1(s_i+1,t_j,memo);
					
				
		else 
			
			res=_get_Res_recur_memo1(s_i+1,t_j,memo);
					
		memo.put(key, res);
				
		return res;
		
	}
	
	public int get_Res_recur_memo2() {
		
		if(t.length()==0) return 1;
		
		if(s.length()==0) return 0;
		
		int[][]memo=new int[s.length()+1][t.length()+1];
		
		for(int i=0;i<s.length()+1;i++)
			
			Arrays.fill(memo[i], -1);
		
		return _get_Res_recur_memo2(0,0,memo);
	}
		
	private int _get_Res_recur_memo2(int s_i,int t_j,int[][]memo) {
		
		if(t_j==t.length()) return 1;
		
		if(s_i==s.length()) return 0;
		
		if(memo[s_i][t_j]!=-1) return memo[s_i][t_j];
		
		int res=0;
		
		if(t.charAt(t_j)==s.charAt(s_i)) 
				
			res=_get_Res_recur_memo2(s_i+1,t_j+1,memo)+_get_Res_recur_memo2(s_i+1,t_j,memo);
		
		else
			
        res=_get_Res_recur_memo2(s_i+1,t_j,memo);
			
	    memo[s_i][t_j]=res;
			
	    return res;
		
	}
	
	public static void main(String[] args) {
		
		String s="babgbag";
		
		String t="bag";
		
		Num_distinct_sub_String_dp ndsp= new Num_distinct_sub_String_dp(s,t);
		
		int num1=ndsp.get_Res_dp_7();
		
		int num2=ndsp.get_Res_recur_memo2();
		
		System.out.println(num1+" "+num2);
	}

}
