/**
 * 
 */
package dp_other_design;

/**
 * @author qiguangqin
 *
 */

enum Index{
	
	Good,Bad,Unknown
}

public class Dp_jump_game {

	private int[] nums;
	
	private Index[] memo;
	
	public Dp_jump_game(int[] nums) {
		
		this.nums=nums;
		
		memo =new Index[nums.length];
		
		for(int i=0;i<memo.length;i++)
			
			memo[i]=Index.Unknown;
		
		memo[memo.length-1]=Index.Good;
	}
	
	public boolean canJumpFromPosition_v0(int pos,int[] nums) {
		
		if(pos==nums.length-1) return true;
		
		int furthestJump=Math.min(nums[pos]+pos, nums.length-1);
		
		for(int i=pos+1;i<=furthestJump;i++) {
			
			if (canJumpFromPosition_v0(i,nums)) return true;
		}
		
		return false;
	}
	
	public boolean canJumpFromPosition(int pos,int[] nums) {
		
		//  use memory table
		
		//  get the last element true;
		
		if(memo[pos]!=Index.Unknown) {
			
			return memo[pos]==Index.Good ?true:false;
		}
		
		
		int furthestJump=Math.min(pos+nums[pos], nums.length-1);
		
		for(int next_pos=pos+1;next_pos<= furthestJump;next_pos++) {
		
		
		//for(int next_pos=furthestJump;next_pos>pos;next_pos--) {   //  No need to judge some middle elements
			
			if(canJumpFromPosition(next_pos, nums)) {
				
				memo[pos]=Index.Good;
				
				return true;
			
			}
		}
		
		memo[pos]=Index.Bad;
		
		return false;
		
	}
	
	public boolean canJump() {
		
		return canJumpFromPosition(0, nums);
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
		
		//int[] nums= {3,2,1,0,4};
		
		Dp_jump_game dop =new Dp_jump_game(nums);
		
		System.out.println(" "+dop.canJump()+" "+dop.canJumpFromPosition_v0(0, nums));
		
		dop.print();
		
	}

}
