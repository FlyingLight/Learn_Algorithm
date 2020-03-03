package linklist_array_insert_delete;

public class insert_array_index {
	

    public int searchInsert(int[] nums, int target) {
        
        
    	//int index=Find(target,0,nums);
    	
    	int index=Find(nums,target);
    	
    	int index2=Find_BU(target,0,nums);
    	
    	return index2;
    }
    
    private int Find(int e,int i,int[]nums){
           
         if (i >=nums.length)
            
            return nums.length;
        
         else if((nums[i]>=e)&& i<nums.length)
            
            return i;
            
        else 
            
            return Find(e,i+1,nums);
    }
    
    private int Find_BU(int element, int i,int[]nums) {
    	
    	if(i>=nums.length) return nums.length;
    	
    	int index=Find_BU(element,i+1,nums);
    	
    	if(nums[i]==element) 
    		
    		return i;
    	
    	return index;
    	
    }
    
    private int Find(int[]nums,int target) {
    	
    	int left=0;
    	
    	int right=nums.length-1;
    	
    	while(left<=right) {
    		
    		int mid=left+(right-left)/2;
    		
    		if(nums[mid]<target)
    			
    			left=mid+1;
    		
    		else
    			
    			right=mid-1;
    		
    	}
    	
    	return left;
    }


	public static void main(String[] args) {
		
		int[] array= {1,2,3,3,5,5,6};
			
		int target=5;
		
		insert_array_index ii= new insert_array_index();
		
		int index=ii.searchInsert(array, target);
		
		System.out.print("index="+index);
	}

}
