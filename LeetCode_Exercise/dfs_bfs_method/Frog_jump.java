package dfs_bfs_method;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

enum frog_mark{
	
	
	//  Good  from which stone can jump the river
	
	//  Unknown from which stone not to judge
	
	// Bad  from which stone can't jump the river
	
	Good,Bad,Unknown
}

public class Frog_jump {
	
	private int[] stones;
	
	private frog_mark[][] memos;
	
	public Frog_jump(int[] stones) {
		
		this.stones=stones;
		
		memos= new frog_mark[stones.length][stones.length];
		
		for(frog_mark[] row:memos) Arrays.fill(row,frog_mark.Unknown);
		
	//	frog_mark[] bb= memos[stones.length-1];
		
	//	for(frog_mark _mark:bb) Arrays.fill(_mark, frog_mark.Good);
		
	}
	
	public boolean canCross(int jump_start,int jump_size) {
		
		if (jump_start==stones.length-1) return true;
	
		for(int index=jump_start+1;index<stones.length;index++) {
			
			int gap=stones[index]-stones[jump_start];
			
			if(gap>=jump_size-1 && gap<= jump_size+1) {
				
				if(canCross(index,gap)) return true;
				
			}
			
		}
		
		//return jump_start==stones.length-1;
		
		return false;
	}
	
	public boolean canCross_v6(int jump_start,int jump_size) {
		
		if(jump_start==stones[stones.length-1]) return true;
		
		for(int new_jump_size=jump_size-1;new_jump_size<=jump_size+1;new_jump_size++) {
			
			if(new_jump_size==0) new_jump_size=1;
			
			int new_jump_start=jump_start+new_jump_size;
			
			if(Arrays.binarySearch(stones, new_jump_start)!=-1) {
				
				if(canCross_v6(new_jump_start,new_jump_size)) 
					
					return true;
				
			}
			
		}
		
		return false;
		
	}
	
	public boolean canCross_memo(int jump_start,int jump_size) {
		
		if(memos[jump_start][jump_size]!=frog_mark.Unknown) {
			
			return memos[jump_start][jump_size]==frog_mark.Good? true:false;
		}
		
		
		for(int index=jump_start+1;index<stones.length;index++) {
			
			int gap=stones[index]-stones[jump_start];
			
			if(gap>=jump_size-1 && gap<=jump_size+1) {  
				
				
				// there is no need to invalid gap --->could simplify from graph to tree,difference from jump_game
				
				if (canCross_memo(index,gap)) { memos[jump_start][jump_size]=frog_mark.Good; return true;}
			}
			
		}
		
		memos[jump_start][jump_size]=(jump_start==stones.length-1)?frog_mark.Good:frog_mark.Bad;
		
		return (memos[jump_start][jump_size]==frog_mark.Good)?true:false;
		
	}
	
	public boolean canCross_v3(int jump_start ,int jump_size) {
		
		
	if(memos[jump_start][jump_size]!=frog_mark.Unknown) {
			
			return memos[jump_start][jump_size]==frog_mark.Good? true:false;
		}
		
		int index1=Arrays.binarySearch(stones, jump_start+1, stones.length, stones[jump_start]+jump_size);
		
		if(index1>=0 && canCross_v2(index1,jump_size)) { 
			
			memos[jump_start][jump_size]=frog_mark.Good;
			
			return true;
		}
		
		int index2= Arrays.binarySearch(stones, jump_start+1,stones.length,stones[jump_start]+jump_size-1);
		
		if(index2>=0 &&canCross_v2(index2,jump_size-1)) {
			
			memos[jump_start][jump_size-1]=frog_mark.Good;
			
			return true;
		}
		
		int index3= Arrays.binarySearch(stones, jump_start+1,stones.length,stones[jump_start]+jump_size+1);
		
		if(index3>=0 &&canCross_v2(index3,jump_size+1)) {
			
			
			memos[jump_start][jump_size+1]=frog_mark.Good;
			
			return true;
		}
		
		memos[jump_start][jump_size]=(jump_start==stones.length-1)?frog_mark.Good:frog_mark.Bad;
		
		return (memos[jump_start][jump_size]==frog_mark.Good)?true:false;
	}
	
	
	public boolean canCross_v2(int jump_start ,int jump_size) {
		
		
		if(jump_start==stones.length-1) return true;
		
		int index1=Arrays.binarySearch(stones, jump_start+1, stones.length, stones[jump_start]+jump_size);
		
		if(index1>=0 && canCross_v2(index1,jump_size)) return true;
		
		int index2= Arrays.binarySearch(stones, jump_start+1,stones.length,stones[jump_start]+jump_size-1);
		
		if(index2>=0 &&canCross_v2(index2,jump_size-1)) return true;
		
		int index3= Arrays.binarySearch(stones, jump_start+1,stones.length,stones[jump_start]+jump_size+1);
		
		if(index3>=0 &&canCross_v2(index3,jump_size+1)) return true;
		
		return false;
	}

	public boolean canCross_v5(int[] stones) {
		
		Map<Integer,Set<Integer>>map= new HashMap<>();  // Set<Integer> to record which step to get the current position
		
		for(int i=0;i<stones.length;i++) {
			
			map.put(stones[i], new HashSet<Integer>());  // Use map to record each point which has three option
			
			// jump_size+1,jump_size,jump_size-1
		}
		
		map.get(0).add(0);
		
		for(int i=0;i<stones.length;i++) {
			
			for(int j:map.get(stones[i])) {
				
				for(int step= j-1;step<=j+1;step++) {
					
					if(step>0 && map.containsKey(stones[i]+step)) {
						
						map.get(stones[i]+step).add(step);
					}
				}
			}
		}
		
		return map.get(stones[stones.length-1]).size()>0;
	}
	

	public static void main(String[] args) {
		
		int[] stones = {0,1,3,5,6,8,12,17};
		
		Frog_jump fj=new Frog_jump(stones);
		
		boolean flag=fj.canCross_v3(0, 0);
		
		boolean flag2=fj.canCross_v5(stones);
		
		boolean flag3= fj.canCross_v6(0, 1);
		
		System.out.println(flag+" "+flag2+" "+flag3);
		
		
		

	}

}
