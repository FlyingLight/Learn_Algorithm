/**
 * 
 */
package BST_USE_TEST;

/**
 * @author qiguangqin
 *
 */
public class SST<K extends Comparable<K>,V> {  //  顺序链表
	
	private class Node{   // 只能在外部类中调用，构造函数创建 ,private方法 不可以被继承，所以不可以重写
		
		private K key;
		
		private V value;
		
		private Node next;
		
		private Node previous;
		
		public Node(K key, V value) {
			
			this.key=key;
			
			this.value=value;
			
			this.next=null;
			
			this.previous=null;
			
		}

		@Override
		public String toString() {
			return "[key=" + key + ", value=" + value + "]";
		}
		
		
	}

	private Node head;
	
	private Node rear;
	
	private int count;
	
	public SST() {
		
		this.head=null;
		
		this.rear=null;
		
		this.count=0;
	}
	
	public int size() {
		
		return this.count;
		
	}
	
	public boolean isEmpty() {
		
		
		return count==0;
	}
	
	public void insert(K key,V value) {
		
		
		head=_insert(key,value);
	}
	
	
	
	public void delete(K key) {
		
		if(!contain(key)||isEmpty()) {
			
			
			System.out.println("empty sequence or not contain key");
		}
		
		else {
		
		_delete(key);
		
		}
		
	}
	
	
	private void _delete(K key) {
		
		Node res=search(key);
		
		res.previous.next=res.next;
		
		res.next.previous=res.previous;
		
	//	res.next=null;
		
	//	res.previous=null;
		
	//	res=null;
		
		this.count--;
			
			
	}
	
	private Node _insert(K key,V value) {
		
		
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
	
	public boolean contain(K key) {
		
		
		return _contain(key);
		
	
	}
	
	private boolean _contain(K key) {
		
		
		Node res= this.head;
		
		boolean isExist=false;
		
		if(res==null) {
			
			System.out.println("empty sst,please insert");
			
		}
		
		else {
			
			int num=0;
		
			for (Node p=this.head;num<this.size();p=p.next,num++) {
				
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
		
		
		Node res= this.head;
		
		 int num=0;
		
		 
		 if(!contain(key)||isEmpty()) {
				
				
				System.out.println("empty sequence or not contain key");
			}
		 
		 
		else {
	
		for (Node p=this.head;num<this.size();p=p.next,num++) {  //  返回第一个 为key 的 node
			
			if(p.key==key) {
				
				res=p;
			}
		
		}
		
		}
	
		return res;
		
	}
	
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
	
	
	public void print() {
		
		int num=0;
		
		if(this.isEmpty()) {
			
			System.out.println("no element");
		}
		
		else {
			
			for(Node p=this.head;num<this.size();p=p.next,num++) {
				
				
				if(num<this.size()-1) {
				
				System.out.print(p.value);
				
				System.out.print("<->");
				
				
			}
				
				else{
					
				System.out.println(p.value);
			
				
				}
			}
			
			
		}
	}
	
	public static void main(String[]args) {
		
		SST<String,Integer>sst=new SST<String,Integer>();
		
		sst.insert("ming", 11);
		
		sst.insert("di", 19);
		
		sst.insert("qiq", 26);
		
		sst.insert("dqw", 29);
		
		sst.insert("poi", 23);
		
		
		sst.update("ming", 56);
		
		System.out.println();
		
		sst.delete("dasdfi");
		
	//	sst.delete("asdfas");
		
		sst.print();
		
		//System.out.println();
		
		
		System.out.print("length of link_sequence ="+sst.size()+'\t'+sst.contain("di"));
		
		
	}
	
}

