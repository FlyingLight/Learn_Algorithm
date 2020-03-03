package backtrack_Method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Combination_sum_method2 {
	
	private List<List<Integer>> res = new ArrayList<>();
	
	 
		public List<List<Integer>> combinationSum_v2(int[] candidates, int target) {
				 
		    //List<List<Integer>> res = new ArrayList<>();
			        
			Arrays.sort(candidates);
			        
			//System.out.println(candidates);
			        
			backtrack(candidates, target ,0, new ArrayList<Integer>());
			        
			return res;

		}

		private void backtrack(int[] candidates, int target, int i, ArrayList<Integer> tmp_list) {
			    	
			//boolean flag=false;
			    	
			if (target < 0) return;
			        
			if (target == 0) {
			        	
				res.add(new ArrayList<>(tmp_list));
			            
				return;
			}
			        
			        
			for (int start = i; start < candidates.length; start++) {
			        	
			     
				if (target < candidates[start])    break;
			            
			    tmp_list.add(candidates[start]);
			            
			    System.out.println("start="+start+" "+tmp_list);
			            
			    backtrack(candidates, target - candidates[start],start, tmp_list);
			            
			    tmp_list.remove(tmp_list.size() - 1);
			    
			}
		}
			    
			    
				

	public static void main(String[] args) {
		
		Combination_sum_method2 csm1 =new Combination_sum_method2();
		
		//int []candidates= {10,1,2,7,6,1,5};
		
		int []candidates= {2,3,6,5,7};
				
		//int []candidates= {1,2,3};
				
		int target=10;
				
	
		//mp1.combinationSum(candidates, target);
			
		//System.out.println("Result1="+mp1.res+" ");
				
		//System.out.println();		
		
		//csm1.combinationSum(candidates, target);
		
		//System.out.println("Result0="+csm1.res);
		
		System.out.println("Result2="+csm1.combinationSum_v2(candidates, target));
				
		//System.out.println("Result3="+csm1.combinationSum_v3(candidates, target));
		
	
		
		/*
		List<Integer> list= new ArrayList<>();
				
		list.add(1);
				
		list.add(2);
				
		List<Integer> list2= new ArrayList<>();
				
		list2.add(1);
				
		list2.add(2);
				
		mp1.res_set.add(list);
				
		mp1.res_set.add(list2);
				
		System.out.println(mp1.res_set);
				
		*/

	}

}
