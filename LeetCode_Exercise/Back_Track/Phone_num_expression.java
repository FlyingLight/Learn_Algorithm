package backtrack_Method;

import java.util.ArrayList;
import java.util.List;

public class Phone_num_expression {
	
	
	private static final String[] Dict= {
	"",	// 0	
	"" , //1
	"abc", //2
	"def",//3
	"ghi" ,//4
	"jkl",//5
	"mno",//6
	"pqrs",//7
	"tuv", //8
	"wxyz"//9
	};
	
	public List<String>LetterCombinations(String digits){
		
		List<String>res= new ArrayList<>();
		
		if(digits.equals("")|| digits.length()==0)  {res.add(new String("")); return res;}
		
		_back_track(0,digits,new StringBuilder(),res);
		
		return res;
		
	}
	
	private void _back_track(int i,String digits,StringBuilder sb,List<String>res) {
		
		
		if(i==digits.length()) {
			
			res.add(sb.toString());
			
			sb= new StringBuilder();
			
			return ;
		}
		
		
		char cur_digit=digits.charAt(i); // Get the cur_digit 
		
		char[] letters= Dict[cur_digit-'0'].toCharArray();
		
		for(char letter:letters) {
			
			sb.append(letter);
			
			_back_track(i+1,digits,sb,res);
			
			sb.deleteCharAt(sb.length()-1);
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String number="23";
		
		List<String> res;
		
		Phone_num_expression pne= new Phone_num_expression();
		
		res=pne.LetterCombinations(number);
		
		System.out.println(res);

	}

}
