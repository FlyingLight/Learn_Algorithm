package linklist_array_insert_delete;

public class LCP_cal {
	
	public String LongestCommonPrefix(String[] strs) {
		
		if(strs.length==0) return "";
		
		String prefix =strs[0];
		
		for(int i=1;i<strs.length;i++)
			
			while(strs[i].indexOf(prefix)!=0) {
				
				prefix=prefix.substring(0,prefix.length()-1);
			
				if(prefix.isEmpty()) return "";
			}
		
		return prefix;
	}
	
	public String LongCommonPrefix2(String[] strs){

		if(strs.length==0) return "";

		String prefix= get_Long_prefix(strs,0,strs.length-1);

		return prefix;
	}


	private String get_Long_prefix(String [] strs,int l,int r){

		if(l>=r)  return strs[l];

		else{

			int mid = (r-l)/2+l; 

			String lcpLeft=get_Long_prefix(strs,l,mid);

			String lcpRight=get_Long_prefix(strs,mid+1,r);

			return CommonPrefix(lcpLeft,lcpRight);
		}

	}

	private String CommonPrefix(String left,String right){

		int str_len=Math.min(left.length(),right.length());

		for(int i=0;i<str_len;i++){

			if(left.charAt(i)!=right.charAt(i))

				return left.substring(0,i);
		}

		return left.substring(0,str_len);

	}
	
	
	public String LongestCommonPrefix3(String[] strs) {
		
		if(strs.length==0) return "";
		
		else if(strs.length==1) return strs[0];
		
		else {
			
			String q=strs[0];
			
			Trie_map tm =new Trie_map();
			
			for(int i=1;i<strs.length;i++)
				
				tm.add(strs[i]);
			
			return tm.search_LCP(q);
				
		}
	}
	
	
	public String LongestCommonPrefix4(String[] strs) {
		
		if(strs.length==0) return "";
		
		else if(strs.length==1) return strs[0];
		
		else {
			
			String q=strs[0];
			
			Trie_map tm =new Trie_map();
			
			for(int i=1;i<strs.length;i++)
				
				tm.add(strs[i]);
			
			int p= tm.Search_LCP(q);
			
			return q.substring(0,p);
				
		}
	}
	
	public String LongestCommonPrefix5(String[] strs) {  // find the lowest length of strs,then use the
		
		// binary search 
		
		if(strs.length==0 || strs==null) return "";
		
		int min_len=Integer.MAX_VALUE;
		
		for(String str:strs) min_len=Math.min(min_len, str.length());
		
		int low=1,high=min_len;
		
		while(low<=high) {  // low must above high to break the loop
			
			int mid=(high-low)/2+low;
			
			if(isCommonPrefix(strs,mid))
				
				low=mid+1;
			
			else
				
				high=mid-1;
		}
		
		return strs[0].substring(0,(low+high)/2);
		
	}
	
	private boolean isCommonPrefix(String[] strs,int mid) {
		
		String prefix_temp=strs[0].substring(0,mid);
		
		for(String str:strs) {
			
			if(!str.startsWith(prefix_temp))
				
				return false;
		}
		
		return true;
	}
	

	public static void main(String[] args) {


		String[] strs= {"Leets","Leetcode","Leet","Leeds"};
		
		//String[] strs= {"abcd","abc","ab"};
		
		LCP_cal lc= new LCP_cal();
		
		String prefix=lc.LongestCommonPrefix(strs);
		
		String prefix2=lc.LongCommonPrefix2(strs);
		
		String prefix3=lc.LongestCommonPrefix3(strs);
		
		String prefix4=lc.LongestCommonPrefix4(strs);
		
		String prefix5=lc.LongestCommonPrefix5(strs);
		
		System.out.println(prefix+" "+prefix2+" "+prefix3+" "+prefix4+" "+prefix5);

	}

}
