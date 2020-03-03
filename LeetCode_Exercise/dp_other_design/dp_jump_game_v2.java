/**
 * 
 */
package dp_other_design;

/**
 * @author qiguangqin
 *
 */


public class dp_jump_game_v2 {
	
	private int[] nums;
	
	private Index[] memo;
	
	public dp_jump_game_v2(int[] nums) {
		
		this.nums=nums;
		
		memo =new Index[nums.length];
		
		for(int i=0;i<memo.length;i++)
			
			memo[i]=Index.Unknown;
		
		memo[memo.length-1]=Index.Good;
	}

	public boolean CanJump() {
		
		for(int i=nums.length-2;i>=0;i--) {  // From the right end to dynamically programming
			
			//  Dp to decide whether n-1,n-2,...,1,0 is good or not
			
			int furthestJump=Math.min(i+nums[i], nums.length-1);
			
			for(int j=i+1;j<=furthestJump;j++) {  
				
				if(memo[j]==Index.Good) {
					
					memo[i]=Index.Good;   
					
					break;
							
				}
			}
		}
		
		return memo[0]==Index.Good;  // finally to decide the first element is good or bad
		
	}
	
	public boolean Can_jump_greedy(int[] nums) {
		
		int last_good_pos=nums.length-1;
		
		// NO need to decide whether n-1,n-2,...,1,0 is good or not
		
		// decide to the the farthest left good position
		
		for(int j=nums.length-1;j>=0;j--) {
			
			if(nums[j]+j>=last_good_pos)  last_good_pos=j;
		}
		
		return last_good_pos==0;
	}
	
	public void print() {
		
		for(int i:nums)
			
			System.out.print(" "+i);
		
		System.out.println(" ");
		
		for(Index index:memo)
			
			System.out.print(" "+index);
		
	}
	
	public static void main(String[] args) {
		
		int[] nums= {1,5,2,1,0,2,0};
	
		dp_jump_game_v2 djg2 =new dp_jump_game_v2(nums);
		
		System.out.println(" "+djg2.CanJump());
		
		djg2.print();
		
		System.out.println();
		
		System.out.println(djg2.Can_jump_greedy(nums));

	}

}
