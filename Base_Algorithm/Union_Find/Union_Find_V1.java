package Union_Find;

public class Union_Find_V1 implements UF{
	
	private int[] id;
	
	private int count;
	
	public Union_Find_V1(int size) {
		
		
		this.count=size;
		
		for(int i=0;i<count;i++) {
			
			id[i]=i;
		}
				
	}
	

	@Override
	public int getSize() {
		
		return count;
	}
	
	private int find(int p) {
		
		if(p<0||p>=count)
			
			throw new IllegalArgumentException("invalid number");
			
		return id[p];
	}
	
	
	
	@Override
	public boolean isConnected(int p,int q) {
		
		if((p<0||p>=count)&&(q<0||q>=count))
			
			throw new IllegalArgumentException("invalid number");
		
		return find(p)==find(q);
		
		
	}
	
	@Override
	public void unionElements(int p,int q) {
		

		if((p<0||p>=count)&&(q<0||q>=count))
			
			throw new IllegalArgumentException("invalid number");
		
		int qid=find(q);
		
		int pid=find(p);
			
		if(pid==qid)
			
			return;
		
		else {
			
			for(int i=0;i<count;i++) {
				
				if(id[i]==pid) // set elements with pid reset by qid(union )
				
					id[i]=qid;  //  not in one set, all set with one 
			}
		}
	}
	

	public void print() {
		
		
		for(int i=0;i<count;i++) {
			
			System.out.println(id[i]);
		}
	}
	
	public static void main(String[] args) {
	
	}

}
