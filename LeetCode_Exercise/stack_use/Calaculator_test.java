/**
 * 
 */
package stack_use;

import java.util.LinkedList;

import java.util.List;

import java.util.Stack;

/**
 * @author qiguangqin
 *
 */
public class Calaculator_test {

	/**
	 * @param args
	 */
	
	
	public int calculate(String exp) {
		
		return call_res(backExp(exp));
	}
	
	private List<String>backExp(String exp_middle){
		
		Stack<String> sign = new Stack<>();
		
		List<String> temp_back_exp = new LinkedList<>();
		
		
		for(int i=0;i<exp_middle.length();i++) {
			
			// Get the **,***,****
			
			if(Character.isDigit(exp_middle.charAt(i))) {
			
				int k=i+1; //  substring method [a,b)
			
				for(;k<exp_middle.length() && Character.isDigit(exp_middle.charAt(k));k++) {}
			
				temp_back_exp.add(exp_middle.substring(i,k));
			
				i=k-1;
			
				continue;
			
			}
			
			if(exp_middle.charAt(i)=='/'||exp_middle.charAt(i)=='*') {
				
				// deal with the '/' and '*'
				
				while(!sign.isEmpty() && (sign.lastElement().equals("/") || sign.lastElement().equals("*"))) {
					
					temp_back_exp.add(sign.pop());
				}
				
				sign.add(String.valueOf(exp_middle.charAt(i)));
				
				continue;
			}
			
			if(exp_middle.charAt(i)=='+'|| exp_middle.charAt(i)=='-') {
				
				while(!sign.isEmpty() && !isNumeric(sign.lastElement())) {
					
					temp_back_exp.add(sign.pop());
				}
				
				sign.add(String.valueOf(exp_middle.charAt(i)));
				
				continue;
				
			}
			
		}
			
			while(sign.size()>0) {
				
				temp_back_exp.add(sign.pop());
			}
		
		return temp_back_exp;
	}
	
	private boolean isNumeric(String str) {
		
		for(int i=0;i<str.length();i++) {
			
			if(!Character.isDigit(str.charAt(i))) {
				
				return false;
			}
		}
		
		return true;
	}
	
	private int call_res(List<String>temp_back_exp) {
		
		Stack<Integer> cal_stack= new Stack<>();
		
		for(String c:temp_back_exp) {
			
			if(isNumeric(c)) {
				
				cal_stack.push(Integer.valueOf(c));
				
				continue;
			}
			
			else {
				
				int a=cal_stack.pop();
				
				int b=cal_stack.pop();
				
				switch(c.toCharArray()[0]) { // string ---> character array
				
				case '+':
					
					cal_stack.push(b+a);
					
					continue;
					
				case '-':
					
					cal_stack.push(b-a);
					
					continue;
					
				case '*':
					
					cal_stack.push(b*a);
					
					continue;
					
				case '/':
					
					cal_stack.push(b/a);
					
					continue;
					
					
				}
			}
		}
		
		return cal_stack.pop();
	}
	
	
	
	public static void main(String[] args) {
		
		Calaculator_test ct= new Calaculator_test();
		
		String exp_middle="3+2*2";
		
		List<String> cc =ct.backExp(exp_middle);
		
		System.out.println(cc);
		
		//int res=ct.call_res(cc);
		
		System.out.println(ct.calculate(exp_middle));

	}

}
