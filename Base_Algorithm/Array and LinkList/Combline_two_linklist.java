package LinkList_Learn;

public class Combline_two_linklist {
	
	
	public dummy_head_linklist<Integer> combine(dummy_head_linklist dhl1,dummy_head_linklist dhl2) {
		
		
		dummy_head_linklist.Node node_1=dhl1.getFirstNode();
		
		dummy_head_linklist.Node node_2=dhl2.getFirstNode();
		
		dummy_head_linklist dhl3=new dummy_head_linklist<Integer>();
		
		while(node_1!=null || node_2!=null) {
			
			
			if(node_1==null &&node_2!=null) {
				
				dhl3.addLast(node_2.key);
				
				node_2=node_2.next;
			}
			
			else if(node_2==null && node_1!=null) {
				
				dhl3.addLast(node_1.key);
				
				node_1=node_1.next;
				
			}
			
			else if(node_1.key.compareTo(node_2.key)<0) {
				
				dhl3.addLast(node_1.key);
				
				node_1=node_1.next;
			}
			
			else  {
				
				dhl3.addLast(node_2.key);
				
				node_2=node_2.next;
			}
			
		}
		
		return dhl3;

	}

	public static void main(String[] args) {
		
		dummy_head_linklist dhl1=new dummy_head_linklist<Integer>();
		
		dummy_head_linklist dhl2=new dummy_head_linklist<Integer>();
		
		dummy_head_linklist dhl3=new dummy_head_linklist<Integer>();
		
		dhl1.addLast(1);
		
		dhl1.addLast(4);
		
		dhl1.addLast(5);
		
	
		dhl2.addLast(2);
		
		dhl2.addLast(3);
		
		dhl2.addLast(7);
		
		Combline_two_linklist ctl=new Combline_two_linklist();
		
		dhl3=ctl.combine(dhl1, dhl2);
		
		dhl3.print();
		
	}

}
