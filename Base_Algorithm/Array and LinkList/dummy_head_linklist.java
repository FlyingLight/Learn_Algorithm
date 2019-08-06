/**
 * 
 */
package LinkList_Learn;



/**
 * @author qiguangqin
 *
 */
public class dummy_head_linklist<K extends Comparable<K>> {

	/**
	 * @param args
	 */
	
	 class Node {
		
		K key;
		
		Node next;
		
		
		public Node(K key,Node next) {
			
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
	
	
	private Node dummy_head;
	
	private int count;
	
	
	public dummy_head_linklist() {
		
		dummy_head =new Node(null,null);
		
		this.count=0;
		
		
	}
	
	
	public int size() {
		
		return count;
	}
	
	
	public int size(Node node_first) {
		
		Node node_cur=node_first;
		
		if(node_cur==null)
			
			return 0;
					
		else 
				
			return size(node_cur.next)+1;
		
	}
	public boolean isEmpty() {
		
		return this.count==0;
	}
	

	public void add(int index,K key) {
		
		Node prev=this.dummy_head;
		
		if(index<0 ||index>count)
			
			throw new IllegalArgumentException("add failded");
		
		for(int i=0;i<index;i++)  // count the dummy node,get the index-1 pos,to insert
			
			// last position should be the rear (the count)
			
			prev=prev.next;
		
		prev.next=new Node(key,prev.next);
		
		/*
		 
		 Node node=new Node(key);
		 
		 node.next=prev.next;
		 
		 prev.next=node;
		 
		 */
		 
		
		this.count++;
				
	}
	
	public void addFirst(K key) {
		
		add(0,key);
	}
	
	public void addLast(K key) {
		
		add(count,key);  // add the element at the count(index)
	}
	
	public K get(int index) {
		
		if(index<0 || index>=count)
			
			throw new IllegalArgumentException("add failded");
		
		Node node_cur=dummy_head.next;
		
		for(int i=0;i<index;i++)
			
			node_cur=node_cur.next;
		
		return node_cur.key;
	}
	
	public Node getNode(int index) {
		
	if(index<0 || index>=count)
			
			throw new IllegalArgumentException("add failded");
		
		Node node_cur=dummy_head.next;
		
		for(int i=0;i<index;i++)
			
			node_cur=node_cur.next;
		
		return node_cur;
		
	}
	
	public Node getFirstNode() {
		
		return getNode(0);
	}
	
	public Node getLastNode() {
		
		return getNode(count-1);
	}
	
	
	public void set(int index,K key) {  
		
		if(index<0 || index>=count)
			
			throw new IllegalArgumentException("set failded");
		
		Node node_cur=dummy_head.next;
		
		for(int i=0;i<index;i++)
			
			node_cur=node_cur.next;
		
		node_cur.key=key;
		
	}
	
	
	public boolean contain(K key) {
		
	//	boolean isContain=false;
		
		Node node_cur=dummy_head.next;
		
		while(node_cur!=null) {
			
			if(node_cur.key.compareTo(key)==0) // {
				
		//		isContain=true;
				
		//		break;
		//	}
				
				return true;
			
			node_cur=node_cur.next;
				
		}
		
	//	return isContain;
		
		return false;
		
	}
	
	
	public void remove(int index) {
		
		if(index<0 || index>=this.count) // = otherwise "delNode=pre.next" will be error!
			
			throw new IllegalArgumentException("remove failded");
		
		Node prev=dummy_head;
		
		// find the previous node of the deleted noded
		
		for(int i=0;i<index;i++)
			
			prev=prev.next;
		
		Node delNode=prev.next;
		
		prev.next=delNode.next;
		
		delNode.next=null;
		
		this.count--;
			
	}
	
	public void removeFirst() {
		
		remove(0);
	}
	
	public void removeLast() {
		
		remove(this.count-1);
	}
	
	public void print() {
		
		int num=0;
		
		if(this.isEmpty()) {
			
			System.out.println("no element");
		}
		
		else {
			
			for(Node p=this.dummy_head.next;num<this.size();p=p.next,num++) {
				
				
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
	
		dummy_head_linklist<String> dhl=new dummy_head_linklist<String>();
		
		dummy_head_linklist<String> dhl2=new dummy_head_linklist<String>();
		
		dhl.addLast("dd");
		
		dhl.addLast("pp");
		
		dhl.print();
		
		//------------------------------------------------------------------------
		
		dhl2.addFirst("qi");
		
		dhl2.addFirst("guangqin");
		
		dhl2.addFirst("shi");
		
		dhl2.removeFirst();
		
		dhl2.print();
		
		System.out.println(dhl.contain("dd"));
		
		System.out.println("size of linklist1=" +dhl.size()+" size of linklist2="+dhl2.size());
		
		System.out.println("size of linklist1=" +dhl.size(dhl.getFirstNode()));
	
		
		
				
	}

}
