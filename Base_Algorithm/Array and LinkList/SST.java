/**
 * 
 */
package LinkList_Learn;

/**
 * @author qiguangqin
 *
 */
public class SST<K extends Comparable<K>>{  //  顺序链表
	
	
	private class Node{   // 只能在外部类中调用，构造函数创建 ,private方法 不可以被继承，所以不可以重写
		
		private K key;
		
		private Node next;
		
		private Node previous;
		
		public Node(K key) {
			
			this.key=key;
			
			this.next=null;
			
			this.previous=null;
			
		}
		
		public Node(K key,Node next,Node previous) {
			
			this(key);
			
			this.next=next;
			
			this.previous=previous;
			
		}

		@Override
		public String toString() {
			return "[key=" + key + "]";
		}
		
		
	}

	private Node header=new Node(null,null,null);
	
	private int count;
	
	public SST() {
		
		header.next=header.previous=header;
		
		this.count=0;
	}
	
	public int size() {
		
		return this.count;
		
	}
	
	public boolean isEmpty() {
		
		
		return count==0;
	}
	
	public void addFirst(K key) {
		
		
		_addFirst(key,header);
	}
	
	
	
	private Node _addFirst(K key,Node node) {
		
		Node newNode= new Node(key,node,node.previous);
		
		newNode.previous.next=newNode;
		
		newNode.next.previous=newNode;
		
		this.count++;
		
		return newNode;
	
	}
	
	public void remove(K key) {
		
		if(!contain(key)||isEmpty()) {
			
			
			System.out.println("empty sequence or not contain key");
		}
		
		else {
		
		_remove(key);
		
		}
		
	}
	
	
	private void _remove(K key) {
		
		Node res=search(key);
		
		res.previous.next=res.next;
		
		res.next.previous=res.previous;
		
		res.next=res.previous=null;
		
		res.key=null;
		
		this.count--;
			
			
	}
	/*
	private Node _insert(K key,Node header) {
		
		
		Node res=null;
		
		if(this.isEmpty()) {
			
	
			res=new Node(key,value);
			
			head=res;
			
			rear=head;
			
			rear.previous=head;
			
			head.next=rear;    // 形成循环链表
			
			this.count++;
					
		}
		
		else {
			
				res=new Node(key,value);
				
				rear.next=res;
				
				res.previous=rear;
				
				rear=res;
				
				rear.next=head;
				
				head.previous=rear;
				
				this.count++;
			
		}
	
		return head;
		
	}
	*/
	
	
	
	public boolean contain(K key) {
		
		
		return _contain(key);
		
	
	}
	
	private boolean _contain(K key) {
		
		
		Node res= this.header;
		
		boolean isExist=false;
		
		if(res.next==header || res.previous==header) {
			
			System.out.println("empty sst,please insert");
			
		}
		
		else {
			
			int num=0;
		
			for (Node p=this.header;num<=this.size();p=p.next,num++) {
				
				if(p.key==key) {
					
					isExist=true;
				
					break;
				}
				
			}
			
		
		}
		
		return isExist;
		
	}
	
	
	public Node search(K key) {
		
		Node res;
		
		assert contain(key)==true && !this.isEmpty();
		
		res=_search(key);
		
		return res;
		
		
	}
	
	private Node _search(K key) {
		
		
		Node res= this.header;
		
		 int num=0;
		
		 
		 if(!contain(key)||isEmpty()) {
				
				
				System.out.println("empty sequence or not contain key");
			}
		 
		 
		else {
	
		for (Node p=this.header;num<=this.size();p=p.next,num++) {  //  返回第一个 为key 的 node
			
			if(p.key==key) {
				
				res=p;
			}
		
		}
		
		}
	
		return res;
		
		
	}
	
	/*
	public void update(K key,V value) {
		
		
		_update(key,value);
	}
	
	
	private void _update (K key,V value) {
		

		Node res;
		
		if(!contain(key)||isEmpty()) {
			
			System.out.print(key +" not exist,plese recheck");
		
			return;
		
		}
			
		else {
			
			res=this.search(key);
			
			res.value=value;
			
		}
		
		
	}
	
	*/
	
	
	public void print() {
		
		int num=0;
		
		if(this.isEmpty()) {
			
			System.out.println("no element");
		}
		
		else {
			
			for(Node p=this.header.next;num<this.size();p=p.next,num++) {
				
				
				if(num<this.size()-1) {
				
				System.out.print(p.key);
				
				System.out.print("<->");
				
				
			}
				
				else{
					
				System.out.println(p.key);
			
				
				}
			}
			
			
		}
	}
	
	public static void main(String[]args) {
		
		SST<String>sst=new SST<String>();
		
		sst.addFirst("ming");
		
		sst.addFirst("di");
		
		sst.addFirst("qiq");
		
		sst.addFirst("dqw");
		
		sst.addFirst("poi");
		
		sst.addFirst("dee");
		
		
		//sst.update("ming");
		
		System.out.println();
		
		sst.remove("dee");
		
	//	sst.delete("asdfas");
		
		sst.print();
		
		//System.out.println();
		
		
		System.out.print("length of link_sequence ="+sst.size()+'\t'+sst.contain("dee"));
		
		
	}
	
}

