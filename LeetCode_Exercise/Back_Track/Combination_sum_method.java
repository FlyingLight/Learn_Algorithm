package backtrack_Method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Combination_sum_method {
	
	 private List<List<Integer>> res = new ArrayList<>();
	 
	 private Set<List<Integer>> res_set= new HashSet<>();
	 
	 //private boolean flag=false;
	 
	 
		public List<List<Integer>> combinationSum(int[] candidates, int target) {
			
			
			//	int sum=0;
				
				//int k=3;
				
		        Arrays.sort(candidates);
		        
		        List<Integer> list = new ArrayList<>();
		        
		        addElement(candidates,list,target,0);
		        
		        //System.out.println(candidates);
		        
		        return res;
		    
		    }
		

		private void addElement(int[] candidates,List<Integer> list,int target,int index) {
			
			if(target<0 )  return ;
			
			if(target==0 ) {
				
				if(!res_set.contains(list)) {
					
				res_set.add(list);
					
				res.add(list);
				
				}
				
				return;
			}
			
			for(int i=index;i<candidates.length;i++) {
				
				if (target < candidates[i])    break;
				
				List<Integer> tmp_list = new ArrayList<>(list);
				
				tmp_list.add(candidates[i]);
				
				System.out.println("start="+i+" "+tmp_list);
				
				addElement(candidates,tmp_list,target-candidates[i],i+1);
			}
			
		}
			
			
			public List<List<Integer>>combinationSum_v1(int[] candidates,int target){
				
				Arrays.sort(candidates);
				
				List<Integer> list = new ArrayList<>();
				 
				addElement_v1(candidates,list,target,0);
				
				return res;
				
			}
			
			private void addElement_v1(int[] candidates,List<Integer>list,int target,int index) {
				
				if(target<0) return ;
				
				if(target==0) {
					
					if(!res_set.contains(list)) {
						
						res_set.add(list);
						
						res.add(list);
					}
					
					return;
					
				}
				
				for(int start =index;start<candidates.length;start++) {
					
					/*
					if(target<candidates[start]) break;
					
					list.add(candidates[start]);
					
					System.out.println("start="+start+" "+list);
					
					addElement(candidates,list,target-candidates[start],start);
				
					list.remove(list.size()-1);
					
					*/
					
					if(target<candidates[start]) break;
					
					List<Integer>tmp_list = new ArrayList<>(list);
					
					tmp_list.add(candidates[start]);
					
					System.out.println("start="+start+" "+tmp_list);
					
					addElement(candidates,tmp_list,target-candidates[start],start+1);
					
				}
				
			}
			

			 public List<List<Integer>> combinationSum_v2(int[] candidates, int target) {
				 
			        List<List<Integer>> res = new ArrayList<>();
			        
			        Arrays.sort(candidates);
			        
			        //System.out.println(candidates);
			        
			        backtrack(candidates, target, res, 0, new ArrayList<Integer>());
			        
			        return res;
			    }

			    private void backtrack(int[] candidates, int target, List<List<Integer>> res, int i, ArrayList<Integer> tmp_list) {
			    	
			    	// ArrayList 引用类型，传值会改变！
			    	
			    	//boolean flag=false;
			    	
			        if (target < 0) return;
			        
			        if (target == 0) {
			        	
			            res.add(new ArrayList<>(tmp_list));
			            
			         //   /*
			          //   * 
			         //  if(i!=candidates.length-1 ) flag=true;
			           //  */
			            
			            return;
			        }
			        
			        
			        for (int start = i; start < candidates.length; start++) {
			        	
			            
			            //System.out.println(start);
			           
			          //  if(flag==true) {flag=false;System.out.println("ok");break;}
			            
			          if (target < candidates[start])    break;
			            
			            tmp_list.add(candidates[start]);
			            
			           System.out.println("start="+start+" "+tmp_list);
			            
			            backtrack(candidates, target - candidates[start], res, start, tmp_list);
			            
			            tmp_list.remove(tmp_list.size() - 1);
			        }
			    }
			    
			    
				 public List<List<Integer>> combinationSum_v3(int[] candidates, int target) {
					 
				        List<List<Integer>> res = new ArrayList<>();
				        
				        Arrays.sort(candidates);
				        
				        //System.out.println(candidates);
				        
				        backtrack_v2(candidates, target, res, new ArrayList<Integer>());
				        
				        return res;
				    }
			    
				 private void backtrack_v2(int[] candidates, int target, List<List<Integer>> res, ArrayList<Integer> tmp_list) {
			    	
			    	//boolean flag=false;
			    	
			        if (target < 0) return;
			        
			        if (target == 0) {
			        	
			            res.add(new ArrayList<>(tmp_list));
			            
			            //flag=true;
			            
			            return;
			        }
			        
			        
			        for (int start =0 ; start < candidates.length; start++) {
			        	
			           if (target < candidates[start])    break;
			            
			            //System.out.println(start);
			            
			            tmp_list.add(candidates[start]);
			            
			           System.out.println("start="+start+" "+tmp_list);
			            
			            backtrack_v2(candidates, target - candidates[start], res, tmp_list);
			            
			            tmp_list.remove(tmp_list.size() - 1);
			        }
			    }
			    
		
					public List<List<Integer>> combinationSum_v4(int target) {
						 
				        List<List<Integer>> res = new ArrayList<>();
				        
				      //  Arrays.sort(candidates);

				        int k=3;
				        
				        //System.out.println(candidates);
				        
				        backtrack_v3(k, target, res,1, new ArrayList<Integer>());
				        
				        return res;
				    }
			    
				 private void backtrack_v3(int k, int target, List<List<Integer>> res,int i, ArrayList<Integer> tmp_list) {
			    	
			    	//boolean flag=false;
			    	
			        if (target < 0 || k<0) return;
			        
			        if (target == 0 && k==0) {
			        	
			            res.add(new ArrayList<>(tmp_list));
			            
			            //flag=true;
			            
			            return;
			        }
			        
			        
			        for (int start =i ; start < 10; start++) {
			        	
			           if (target < start)    break;
			            
			            //System.out.println(start);
			            
			            tmp_list.add(start);
			            
			           System.out.println("start="+start+" "+tmp_list);
			            
			            backtrack_v3(k-1, target - start, res,start+1, tmp_list);
			            
			            tmp_list.remove(tmp_list.size() - 1);
			        }
			    }
				 
	
	public List<List<Integer>> Permutation(int[] candidates){
		
		List<List<Integer>> res= new ArrayList<>();
		
		List<Integer> tmp_list =new ArrayList<>();
		
		for(int i:candidates)
			
			tmp_list.add(i);
			
		permute_backtrack(candidates,res,0,tmp_list);
		
		return res;
		
	}
	
	private void permute_backtrack(int[] candidates,List<List<Integer>>res,int first,List<Integer>tmp_list) {
		
		
		// tmp_list ---> [1 2 3 ] initial sequence
		
		if(first==candidates.length) {
			
			res.add(new ArrayList<>(tmp_list));
			
			return ;
		}
		
		for(int i=first;i<candidates.length;i++) {
			
			Collections.swap(tmp_list, first, i);
			
			this.permute_backtrack(candidates, res, first+1, tmp_list);
			
			Collections.swap(tmp_list, first, i);
	
		}
	}
	
	public List<List<Integer>> subsetWithDup(int[] nums){
		
		List<List<Integer>> res= new ArrayList<>();
		
		Arrays.sort(nums);
		
		subset_helper(nums,res,0,new ArrayList<Integer>());
		
		return res;
	}
	
	public List<List<Integer>> subsetWithDup_v2(int[] nums){
		
		//List<List<Integer>> res= new ArrayList<>();
		
		Arrays.sort(nums);
		
		subset_helper_v2(nums,0,new ArrayList<Integer>());
		
		return res;
	}
	
	private void subset_helper(int[] nums,List<List<Integer>> res,int i,ArrayList<Integer> tmp_list) {
		
		res.add(new ArrayList<>(tmp_list));
		
		for(int index=i;index<nums.length;index++) {
			
			if(index>i && nums[index]==nums[index-1]) continue;
			
			tmp_list.add(nums[index]);
			
			subset_helper(nums,res,index+1,tmp_list);
			
			tmp_list.remove(tmp_list.size()-1);
		}
		
	}
	
	private void subset_helper_v2(int[] nums,int i,List<Integer> list) {
		
	
		res.add(list);
		
		for(int index=i;index<nums.length;index++) {
			
			if(index>i && nums[index]==nums[index-1]) continue;
			
			List<Integer> tmp_list=new ArrayList<>(list);
			
			tmp_list.add(nums[index]);
			
			subset_helper_v2(nums,index+1,tmp_list);
			
			//tmp_list.remove(tmp_list.size()-1);
		}
		
	}
	
	public int combinationSum4(int []nums,int target) {
		
		
		//  nums must be unique elements
		
		int n=nums.length;
		
		if(n==0) return 0;
		
		int []dp = new int[target+1];
		
		dp[0]=1;
		
		for(int i=1;i<=target;i++) {  
			
		// dp[target]   = dp[i]={sum dp[i-num[j]]  if i-num[j]>=0  }
			
			for(int j=0;j<n ;j++) {
				
				if(i-nums[j]>=0)
					
					// dp[4]=dp[3]+dp[2]+dp[1]
					
					// dp[3]=dp[2]+dp[1]+dp[0]
					
					dp[i]+=dp[i-nums[j]];
			}
		
		}
		
		return dp[target];
		
	}


	public static void main(String[] args) {
		
		Combination_sum_method csm1 =new Combination_sum_method();
		
		//int []candidates= {10,1,2,7,6,1,5};
		
		int []candidates= {2,3,6,5,7};
				
		//int []candidates= {1,2,3};
				
		int target=10;
				
	
		//mp1.combinationSum(candidates, target);
			
		//System.out.println("Result1="+mp1.res+" ");
				
		//System.out.println();		
		
		//csm1.combinationSum(candidates, target);
		
		//System.out.println("Result0="+csm1.res);
		
		
				
		csm1.combinationSum_v1(candidates, target);
				
		System.out.println("Result1="+csm1.res+" ");
		
		System.out.println("Result2="+csm1.combinationSum_v2(candidates, target));
				
		//System.out.println("Result3="+csm1.combinationSum_v3(candidates, target));
		
		System.out.println("Result4="+csm1.combinationSum_v4( target));
		
		System.out.println(csm1.Permutation(candidates).size());
		
		System.out.println(csm1.subsetWithDup(candidates));
		
		//System.out.println(csm1.subsetWithDup_v2(candidates));
		
		
		System.out.println(csm1.combinationSum4(candidates, target));
		
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
