/**
 * 
 */
package LinkList_Learn;

/**
 * @author qiguangqin
 *
 */
public class Array<E > {
	
	private E[] elements;
	
	private int count;
	
	public Array(int Capacity) {
		
		elements=(E[])new Object[Capacity];
		
		this.count=0;
	}
	
	public Array() {
		
	
		
		this(10);
	}
	
	public int getCapacity() {
		
		return elements.length;
	}
	
	public boolean isEmpty() {
		
		return this.count==0;
	}
	
	public E get(int index) {
		
		if(index<0 || index>=count) {
			
			throw new IllegalArgumentException("invalid index");
		}
		
		return elements[index];
	}
	
	private void resize(int newCapacity) {
		
		if(newCapacity<0)
			
			throw new IllegalArgumentException("invalid index");
		
		E[] newElements= (E[])new Object[newCapacity];
		
		for(int i=0;i<count;i++) {
			
			newElements[i]=elements[i];
			
		}
		
		elements=newElements;
		
	}

	public void add(int index, E e) {
		
		if(index<0||index>count)
			
			throw new IllegalArgumentException("invalid index");
		
		if(count==getCapacity())  // prevent out of bound  judge before insert , whether array is full
			
			resize(2*elements.length);
		
		for(int i=count;i>index;i--) 
			
			// i=count i> index 不能移动到index
			
			// i=count-1 i>=index   elements[i+1]=elements[i]
			
			elements[i]=elements[i-1];
		
		elements[index]=e;
		
		this.count++;
		
	}
	
	public void addLast(E e) {
		
		add(count,e);
		/*
		if(count==getCapacity())  // prevent out of bound
		
			resize(2*elements.length);	
		
		 elements[count++]=e;
		 */
	}
	

	public void addFirst(E e) {
		
		add(0,e);
	}
	
	public void set(int index ,E e) {
		
		if(index<0||index>=count)
			
			throw new IllegalArgumentException("invalid index");
		
		elements[index]=e;
	}
	
	
	// whether the elements array contain e
	public boolean contains(E e) {
		
		for(int i=0;i<count;i++) {
			
			if(elements[i]==e)
				
				return true;
		}
		
			return false;
	}
	
	// return the index of the elements
	public int find(E e) {
		
		for(int i=0;i<count;i++) {
			
			if(elements[i]==e)
				
				return i;
		}
		
		return -1;
		
	}
	
	public void remove(int index) {
		
		if(index<0||index>=count)
			
			throw new IllegalArgumentException("invalid index");
		

		for(int i=count-1;i<index;i--) {
			
			// for(i=index+1;i<count;i++)
			// elements[i-1]=elements[i];
			
			elements[i-1]=elements[i];
		}
		
		elements[count-1]=null;// let gc work
		
		this.count--; 
		
		if(count<getCapacity()/4 && elements.length/2!=0) {
			
			resize(elements.length/2);
			
		}
		
	}
	
	public void print() {
		
		for(int i=0;i<this.count;i++)
			
			System.out.println(elements[i]);
	}
	
	
	
	public static void main(String[] args) {
		
		Array<Character> aa1= new Array<Character>();
		
		for(char i='A';i<='Z';i++)
			
			aa1.addLast(i);
		
		aa1.addFirst('b');
		
		System.out.print(aa1.getCapacity());
		
		for(char i='C';i<='Z';i++)

			aa1.remove(aa1.count-1);
		
		aa1.contains('b');
		
		aa1.print();
		
		System.out.print(aa1.getCapacity());
	}
}
