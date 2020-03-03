/**
 * 
 */
package base_dp_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author qiguangqin
 *
 */
public class Wiggle_Max_Seq {

	/**
	 * @param args
	 */
	
	private int[]nums;
	
	private List<Integer>res;
	

	public Wiggle_Max_Seq(int []nums) {
		
		
		this.nums=nums;
		
		this.res=new ArrayList<>();
		
	}
	
	public int wiggleMaxLength_recur1() {
		
		//boolean up=true;
		
		if(nums.length<2) return nums.length;
		
		return 1+Math.max(_wiggle_max_length_recur1(0,true),_wiggle_max_length_recur1(0,false));
	}
	
	private int _wiggle_max_length_recur1(int index,boolean isUp) {
		
		int max=0;
		
		for(int i=index+1;i<nums.length;i++) {  // isUp is contrary  nums[i]<nums[index]
			
			if((isUp && nums[i]<nums[index])||(!isUp && nums[i]>nums[index])) 
				
				max=Math.max(max,1+_wiggle_max_length_recur1(i,!isUp));
			
		}
		
		return max;
	}
	
	public int wiggleMaxLength_recur1_1() {
		
		//boolean up=true;
		
		if(nums.length<2) return nums.length;
		
		return 1+Math.max(_wiggle_max_length_recur1_1(0,true),_wiggle_max_length_recur1_1(0,false));
	}
	
	private int _wiggle_max_length_recur1_1(int index,boolean isUp) {
		
		int max=0;
		
		for(int i=index+1;i<nums.length;i++) { // isUp is same  nums[i]>nums[index]
			
			if((isUp && nums[i]>nums[index])||(!isUp && nums[i]<nums[index])) 
				
				/*
				 int p=_wiggle_max_length_recur1_1(i,!isUp,list1);
				 
				 if(p>max){
				 
				 	max=p;
				 	
				 	list1.add(i);
				 }
				 
				 list1.remove(i);
				
				*/
				
				max=Math.max(max,1+_wiggle_max_length_recur1_1(i,!isUp));
			
		}
		
		return max;
	}
	
	
	public int wiggle_max_length_recur_list() {
		
		
		
		int p=_wiggle_max_length_recur_list(0, false, new ArrayList<Integer>());
		
		
		
		int q=_wiggle_max_length_recur_list(0, true, new ArrayList<Integer>());
		
		//System.out.print(q+" "+p);
		
		Collections.reverse(res); res.add(nums[0]);
		
		Collections.reverse(res);
		
		return Math.max(p, q)+1;
	}
	
	private int _wiggle_max_length_recur_list(int index,boolean isUp,List<Integer>temp) {
		
		int max=0;
	
		if(index==nums.length-1) { if(temp.size()>res.size())  res= new ArrayList<>(temp);}
			
		for(int i=index+1;i<nums.length;i++) { // isUp is same  nums[i]>nums[index]
			
			if((isUp && nums[i]>nums[index])||(!isUp && nums[i]<nums[index])) {
				
				temp.add(nums[i]);
				
				max=Math.max(max, _wiggle_max_length_recur_list(i,!isUp,temp)+1);
				
				//System.out.println(temp+" "+temp.size()+" "+res.size());
				
				temp.remove(temp.size()-1);
				
		}
			
		}
		
		return max;
	}
		

	public int wiggleMaxLength_recur2() {
		
		//boolean up=true;
		
		if(nums.length<2) return nums.length;
		
		return 1+Math.max(_wiggle_max_length_recur2(0,true),_wiggle_max_length_recur2(0,false));
	}
	
	private int _wiggle_max_length_recur2(int cur_index,boolean isUp) { // 
		
		int max_count=0,cur_max=0;
		
		for(int i=cur_index+1;i<nums.length;i++) {
			
			if(isUp && nums[i]>nums[cur_index])  // Use the || to combine 
				
				cur_max=_wiggle_max_length_recur2(i,!isUp)+1;
			
			else if(!isUp && nums[i]<nums[cur_index])
				
				cur_max=_wiggle_max_length_recur2(i,!isUp)+1;
			
			max_count=Math.max(max_count, cur_max);
		}
		
		return max_count;
	}
	
	public int wiggleMaxLength_recur_memo() {
		
		int[] up= new int[nums.length];
		
		int[]down= new int[nums.length];
		
		Arrays.fill(up, -1);
		
		Arrays.fill(down, -1);
		
		int up_count=_wiggleMaxLength_recur_memo(0,true,up,down);
		
		int down_count=_wiggleMaxLength_recur_memo(0,false,up,down);
		
		return Math.max(up_count, down_count)+1;
	}
	
	private int _wiggleMaxLength_recur_memo(int cur_index,boolean isUp,int[]up,int[]down) {
		
		if(isUp &&up[cur_index]!=-1) return up[cur_index];
		
		if(!isUp &&down[cur_index]!=-1) return down[cur_index];
		
		int max=0;
		
		for(int i=cur_index+1;i<nums.length;i++) {
			
			if((isUp && nums[i]>nums[cur_index])||(!isUp && nums[i]<nums[cur_index])) 
				
				max=Math.max(max,1+_wiggle_max_length_recur1_1(i,!isUp));
			
		}
		
		if(isUp)
		
			return up[cur_index]=max;
		
		else 
			
			return down[cur_index]=max;
		
		}
		
	
	public int wiggleMaxLength_dp() {
		
		if(nums.length<2) return nums.length;
		
		int[] up= new int[nums.length];
		
		int[] down= new int[nums.length];
		
		for(int i=1;i<nums.length;i++) {
			
			for(int j=0;j<i;j++) {
				
				if(nums[j]<nums[i])
					
					up[i]=Math.max(up[i], down[j]+1);
				
				else if(nums[j]>nums[i])
					
					down[i]=Math.max(down[i], up[j]+1);
				
			//	else {
					
				//	up[i]=up[j];
				
					//down[i]=up[j];
					
				//}
			}
		}
		
		return Math.max(up[nums.length-1], down[nums.length-1])+1;
	}
	
	
	public int wiggleMaxLength_dp1() {
		
		if(nums.length<2) return nums.length;
		
		int[] up= new int[nums.length];
		
		int[] down= new int[nums.length];
		
		up[0]=1;down[0]=1;
		
		for(int i=1;i<nums.length;i++) {
			
			for(int j=0;j<i;j++) {
				
				if(nums[j]<nums[i])
					
					up[i]=Math.max(up[i], down[j]+1);
				
				else if(nums[j]>nums[i])
					
					down[i]=Math.max(down[i], up[j]+1);
				

				else {
					
					down[i]=down[j]; 
					
					up[i]=up[j];
				}
			}
			
		}
		
		return Math.max(up[nums.length-1], down[nums.length-1]);
	}
	
	public int wiggleMaxLength_dp2() {
		
		if(nums.length<2) return nums.length;
		
		int[]up=new int[nums.length];
		
		int[]down= new int[nums.length];
		
		
		up[0]=1;down[0]=1;
		
		for(int i=1;i<nums.length;i++) {
			
			if(nums[i]-nums[i-1]>0) {
				
				up[i]=down[i-1]+1;
				
				down[i]=down[i-1];
				
			}
				
			else if(nums[i]-nums[i-1]<0) {
				
				down[i]=up[i-1]+1;
				
				up[i]=up[i-1];
				
			}
			
			else {
				
				up[i]=up[i-1];
				
				down[i]=down[i-1];
		}
		
		}
		
		return Math.max(up[nums.length-1],down[nums.length-1]);
	}
	
	public int wiggleMaxLength_dp3() {
		
		if(nums.length<2) return nums.length;
		
		int up=1;int down=1;
		
		for(int i=1;i<nums.length;i++) {
			
			if(nums[i]-nums[i-1]>0)
				
				up=down+1;
			
			else if(nums[i]-nums[i-1]<0)
				
				down=up+1;
		}
		
		return Math.max(up,down);
		
	}
	
	public static void main(String[] args) {
		
		//int[]nums= {6,17,5,10,13,15,10,5,16,8};
		
		//int []nums={1,2,3,4,5,6,7,8,9};
		
		int []nums= {1,7,4,9,2,5};
		
		Wiggle_Max_Seq wms= new Wiggle_Max_Seq(nums);
		
		int count =wms.wiggleMaxLength_dp();
		
		int count1=wms. wiggleMaxLength_recur_memo();
		
		System.out.println(count+" "+count1);
		
		System.out.println(" cur_max="+wms.wiggle_max_length_recur_list()+" "+wms.res);
		
	}

}
