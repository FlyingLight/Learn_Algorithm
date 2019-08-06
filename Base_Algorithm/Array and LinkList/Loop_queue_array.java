package LinkList_Learn;

public class Loop_queue_array<K> implements queue<K>{
	
	private K[] data;
	
	private int front,tail;
	
	//  head and tail index,tail,head point to the first element
	//  point to the positon after the last element
	
	private int count; // actually use 
	
	
	public Loop_queue_array(int capacity) {
		
		data=(K[])new Object[capacity+1];  // loop_queue must waste one space
		
		// Capacity = all the element sum
		
		this.front=this.tail=0;
		
		this.count=0;
	}
	
	public Loop_queue_array() {
		
		this(10);
	}
	
	public int getCapacity() {
		
		return data.length-1;   // the max capacity of queue
		
		// will waste one space, to construct the loop queue
	}
	
	@Override
	public boolean isEmpty() {
		
		return front==tail;
	}
	
	@Override
	public int getSize() {
		
		return this.count;
	}
	
	@Override
	public void enqueue(K key){
		
		if((tail+1)%data.length==front) 
			
			resize(getCapacity()*2); // 队列的最大容量*2
		
		data[tail]=key; // tail == next position to insert
		
		tail=(tail+1)%data.length;
		
		this.count++;
		
		
	}
	
	private void resize(int newCapacity) {
		
		K[] newData= (K[])new Object[newCapacity+1];
		
		for(int i=0;i<count;i++)
			
			newData[i]= data[(i+front)%data.length];
		
			//  new[0] ---> front(such as data[2])
			// 	new[1] ---> front+1(such as data[3])
			//  new[2] ---> front+2(such as data[4])  
			//  new[3] ---> front+3(such as data[5])   
			//  new[4] ---> front+4(such as data[6])   
			//  new[5] ---> front+5(such as data[7])   
			//  new[6] ---> tail-1(such as data[0])
		
			//  data[1] ---> leave out, waste one space
		
		data=newData;  // Capacity will change 
		
		front=0;
		
		tail=count;
		
	}

	@Override
	public K dequeue() {
		
		if(front==tail)
			
			throw new IllegalArgumentException("dequeue failed,empty queue");
		
		K ret = data[front];
		
		data[front]=null; // keep from the loitering pointer
		
		front=(front+1)%data.length;// update the front
		
		this.count--;
		
		if(count==this.getCapacity()/4)
			
			resize(getCapacity()/2);
		
		return ret;
		
	}
	
	@Override
	public K getFront() {
		
		if(isEmpty()) {
			
			throw new IllegalArgumentException("empty queue");
		}
		
		return data[front];
		
	}
	
	
	public void print() {
		
		if(front==tail)
			
			throw new IllegalArgumentException("dequeue failed,empty queue");
		
		/*8
		for(int i=front;i!=tail;i=(i+1)%data.length) {
			
			System.out.println(data[i]);
			
		}
		*/
		
		int temp_pi=front;
		
		for(int i=0;i<count;i++) {
			
			System.out.print(data[temp_pi]);
			
			temp_pi=(temp_pi+1)%data.length;
			
		}
			
	}
	
	public static void main(String[] args) {
		 
		Loop_queue_array<Character> lqa= new Loop_queue_array();
		
		for(char i='A';i<='Z';i++)
		
			lqa.enqueue(i);
		
		lqa.enqueue('b');
	
		lqa.print();
		
		System.out.print(lqa.getCapacity()+" ");

		for(char i='D';i<='Z';i++)
			lqa.dequeue();
		
		lqa.print();
		
		System.out.println(lqa.getCapacity());
		
		}
		

		
	}
	
	


