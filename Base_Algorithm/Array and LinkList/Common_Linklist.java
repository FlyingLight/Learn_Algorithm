package LinkList_Learn;



public class Common_Linklist<K extends Comparable<K>> {
	
	
	private class Node{
		
		K key;
		
		Node next;
		
		
		public Node( K key,Node next) {
			
			this.key=key;
			
			this.next=next;
			
		}
		
		public Node() {
			
			this(null,null);
		}
		
		public Node(K key) {
			
			this(key,null);
		}
		
	}
	
	
	private Node head;
	
	private int count;
	
	
	public Common_Linklist() {
		
		this.count=0;
		
		this.head=null;
	}
	
	public boolean isEmpty() { return count==0;}
	
	public int size() {return this.count;}
	
	public void addFirst(K key) {
		
		// Node node=new Node(key);
		
		// node.next=head;
		
		//head=node;
		
		head=new Node(key,head);
		
		this.count++;
		
	//	return head;
		
	}
	
	public void add(int index,K key) {
		
		if(index<0||index>count)
			
			throw new IllegalArgumentException("ADD failed,illegal index");
		
		
		if (index==0)
			
			addFirst(key);
		
		else {
			
		Node prev=this.head;
		
			for(int i=0;i<index-1;i++) 
				
				prev=prev.next;
			
			prev.next=new Node(key,prev.next);
			
			this.count++;	
		
		}
		
	}
	
	public void addLast(K key) {
		
		
		add(count,key);
	}
	

	
	
	public void print() {
		
		int num=0;
		
		if(this.isEmpty()) {
			
			System.out.println("no element");
		}
		
		else {
			
			for(Node p=this.head;num<this.size();p=p.next,num++) {
				
				
				if(num<this.size()-1) {
				
				System.out.print(p.key);
				
				System.out.print("->");
				
				
			}
				
				else{
					
				System.out.println(p.key);
			
				
				}
			}
			
			
		}
	}

	public static void main(String[] args) {
		
		Common_Linklist<String> cll= new Common_Linklist<String>();
		
		cll.addFirst("qq");
		
		cll.addFirst("dd");
		
		cll.addLast("asd");
		
		cll.print();
		

	}

}
