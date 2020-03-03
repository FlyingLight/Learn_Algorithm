/**
 * 
 */
package dfs_bfs_method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author qiguangqin
 *
 */
public class Money_Coins_Change {

	/**
	 * @param args
	 */
	
	private int[]coins;
	
	private int amount;
	
	private List<Integer>res;
	
	
	public Money_Coins_Change(int[]coins,int amount) {
		
		
		this.amount=amount;
		
		this.coins=coins;
		
		this.res=new ArrayList<>();
	}
	
	
	public int coinChange_dp() {

        Arrays.sort(coins);

        int[]dp= new int[amount+1];

        for(int i=1;i<=amount;i++){

                int min=Integer.MAX_VALUE;

               // int k=0;

               for(int j=0;j<coins.length && i-coins[j]>=0;j++){
                
                    if(dp[i-coins[j]]!=Integer.MAX_VALUE)  
                    	
                        min = Math.min(min,dp[i-coins[j]]+1);
                
            } 
                dp[i]=min;
                
            }

    return dp[amount]==Integer.MAX_VALUE?-1:dp[amount];
        
    }
	
	public int coinChange_bfs() {

        Arrays.sort(coins);
        
        int[] pre=new int[amount+1];

        Queue<Integer> queue=new LinkedList<>();
        
        Map<Integer,Integer>map=new HashMap<>();
        
        map.put(amount, 0);
        
        queue.add(amount);
        
        while(!queue.isEmpty()) {
        	
        	int temp=queue.remove();
        	
        	//for(int w:coins) {
        	
        	for(int i=0;i<coins.length && (temp-coins[i])>=0;i++) {
        		
        		//if((temp-w)>=0 && !queue.contains(temp-w)) {
        		
        		if(!queue.contains(temp-coins[i])) {
        			
        			//map.put(temp-w, map.get(temp)+1);
        			
        			map.put(temp-coins[i], map.get(temp)+1);
        			
        			//pre[temp-w]=temp;
        			
        			pre[temp-coins[i]]=temp;
        			
        			//queue.add(temp-w);
        			
        			queue.add(temp-coins[i]);
        			
        			//if(temp-w==0)
        			
        			if(temp-coins[i]==0)
        				
        				return map.get(temp)+1;
        		}
        		
        	}
        }
        
        return -1;
        
    }
	
	
	public int coinsChange_recur_memo() {
		
		Arrays.sort(coins);
		
		int[] memo= new int[amount+1];
		
		int res=_coinsChange_recur_memo(amount,memo);
		
		return res==Integer.MAX_VALUE? -1:res;
	}
	
	private int _coinsChange_recur_memo(int cur_amount,int[]memo) {
		
		if(memo[cur_amount]!=0) return memo[cur_amount];
		
		if(cur_amount==0)  return memo[cur_amount]=0; 
		
		int res=Integer.MAX_VALUE;
		
		if(cur_amount<coins[0]) return memo[cur_amount]=res;
		
		for(int i=0;i<coins.length && cur_amount>=coins[i];i++) {
			
			int cur_res=_coinsChange_recur_memo(cur_amount-coins[i],memo);
			
			if(cur_res!=Integer.MAX_VALUE) 
				
				res=Math.min(res, cur_res+1);
				
				
		}
		
		return memo[amount]=res;
	}
	
	
	public int coinsChange_recur() {
		
		Arrays.sort(coins);
		
		int res= _coinsChange_recur(amount);
		
		return res==Integer.MAX_VALUE? -1:res;
	}
	
	private int _coinsChange_recur(int cur_amount) {
		
		if(cur_amount==0) return 0;
		
		int res=Integer.MAX_VALUE;
		
		if(cur_amount<coins[0]) return res;
		
		for(int i=0;i<coins.length && cur_amount>=coins[i];i++) {
			
			int cur_res=_coinsChange_recur(cur_amount-coins[i]);
			
			if(cur_res!=Integer.MAX_VALUE) 
				
				res=Math.min(res, cur_res+1);
				
		}
		
		return res;
	}
	
	
	public List<Integer> coinsChange_recur_list() {
		
		
		Arrays.sort(coins);
		
		int ans=_coinsChange_recur_list(amount,new ArrayList<Integer>());
		
		System.out.println(ans);
		
		if(ans==Integer.MAX_VALUE) res.clear();
		
		return res;
	}
	
	
	private int _coinsChange_recur_list(int cur_amount,List<Integer>temp) {
		
		if(cur_amount==0 ) {
			
			this.res= new ArrayList<Integer>(temp);
				
			//System.out.println(res);
				
			return 0;
					
		}
		
		int res=Integer.MAX_VALUE;
		
		if(cur_amount<coins[0]) return res;
		
		for(int i=0;i<coins.length && cur_amount>=coins[i];i++) {
			
			temp.add(coins[i]);
				
			int cur_res=_coinsChange_recur_list(cur_amount-coins[i], temp);
			
			temp.remove(temp.size()-1);
			
			if(cur_res!=Integer.MAX_VALUE) { 
				
				res=Math.min(res, cur_res+1);
							
			}
		}
		
		return res;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int []coins= {5,2,1};
		
		//int []coins= {357,239,73,52};
		
		int amount=18;
		
		Money_Coins_Change mcc= new Money_Coins_Change(coins,amount);
		
		int count1=0,count2=0,count3=0,count4=0;
		
		count1=mcc.coinChange_dp();
		System.out.println("CoinChange_dp="+count1);
		
		count2=mcc.coinChange_bfs();
		System.out.println("CoinChange_bfs="+count2);
		
		count3=mcc.coinsChange_recur();
		System.out.println("CoinChange_recur="+count3);
		
		count4=mcc.coinsChange_recur_memo();
		System.out.println("CoinChange_recur_memo="+count4);
		
		System.out.println("specific_method_backtrack=" +mcc.coinsChange_recur_list());
		
		
	}

}
