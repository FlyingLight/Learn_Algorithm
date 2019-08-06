package LinkList_Learn;

import java.util.Stack;

public class bracket_isValid {
	
	
	private String remove_other(String s) {
		
		StringBuffer sb1= new StringBuffer();
		
		for(int i=0;i<s.length();i++) {
			
			char c=s.charAt(i);
			
			if(c=='('||c=='['||c=='{'||c==')'||c==']'||c=='}') 
					
					sb1.append(c);
			
		}
		
		return sb1.toString();
	}
	
	
	
	public boolean isValid(String s) {
			
		Stack<Character> stack=new Stack<>();
		
		for(int i=0;i<s.length();i++) {
			
			char c=s.charAt(i);
			
			if(c=='('||c=='['||c=='{')
				
				stack.push(c);
			
			else {
				
				if(stack.isEmpty())
					
					return false;
							
					char topChar=stack.pop();
					
					if(c=='('&& topChar!=')')
						
						return false;
					
					if(c==']'&& topChar!='[')
						
						return false;
						
					if(c=='}'&& topChar!='{')
						
						return false;
									
			}
		}
		return stack.isEmpty();
		
	}

	public static void main(String[] args) {
		
		bracket_isValid bv1= new bracket_isValid();
		
		String s="add{!vd%{#c[%[$(sd(&)ds)v]]ds}}";
		
		String s1="(]";
		
		System.out.println(bv1.isValid(bv1.remove_other(s1)));

	}

}
