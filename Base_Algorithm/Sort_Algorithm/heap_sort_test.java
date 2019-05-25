/**
 * 
 */
package sort_learn_test;

import java.util.Arrays;

import java.util.Random;

/**
 * @author qiguangqin
 *
 */
public class heap_sort_test {
	
	private int []data;
	
	private int count;
	
	private int cap;  // 堆的最大容量
	
	private void swap(int[] arr,int i,int j) {
		
		int temp;
		
		temp=arr[i];
		
		arr[i]=arr[j];
		
		arr[j]=temp;
	}
	
	public heap_sort_test(int cap) {
		
		// b 数组index 从1 开始
		
		data=new int[cap+1];
		
		count =0;
		
		this.cap=cap;
		
	}
	
	public heap_sort_test(int[] arr,int n) {
		
		this.data=new int[n+1];
		
		this.cap=n;
		
		for(int i=0;i<n;i++)
			
			data[i+1]=arr[i];
		
		this.count=n;
		
		for(int i=count/2;i>0;i--) {
			
			this.shiftdown(i);
			
		}
	}
	
	public int size() {
		
		return count;
	}
	
	public boolean isEmpty() {
		
		return count==0;
	}
	
	
	public void shiftUp(int k) {
		
		// 将索引K 位置的元素，试着向上移动，与父节点进行比较
		
		while(k>1&& data[k]>data[k/2]) { // if(data[k/2]<data[k]) swap k/=2 不满足 依然要k/2 
			
			// 判断条件放在while 循环上面
					
			swap(data,k,k/2);
			
			k/=2;  // while 循环最后要有递增和递减量
		}
	}
	
	private void resize(int new_cap) {
		
		this.cap=new_cap;
		
		int [] data =new int[new_cap+1];
		
		data=Arrays.copyOf(this.data,data.length);
		
		this.data=data;
		
		//System.out.print("new_Cap="+this.cap);
	}

	public void insert(int item) {
		
		if (count+1>this.cap) {
		
			resize(2*cap);
		
		}
		
		data[count+1]=item;
		
		count++;
		
		shiftUp(count);
		
	}
	
	public void shiftdown(int k) {
		
		
		int element=data[k];  // 将需要shiftdown的元素赋值给element
		
		while(2*k<=count) {   // 判断当前循环中的节点 k 是否有左孩子
			
			int j=2*k;  // 将element与data[k](当前)的孩子节点data[j]进行比较，
			
			//data[j]大于element,就把 data[k],赋值成data[j],因为原来是最大堆（完全二叉树）
			
			if(j+1<=count &&data[j]<data[j+1]) 
					
					j+=1;
					
			if(data[j]<=element)
				
				break;
					
			data[k]=data[j];
			
			k=j;
													
		}
		
		data[k]=element;
		
	}
	
	public void shiftdown2(int k) {
		
		while(2*k<=count) { // 判断该节点是否有左孩子
			
			int j=2*k;  // arr[j] 为可能需要与arr[k]交换，在此轮循环中
			
			if(j+1<=count && data[j+1]>data[j])
				
				j+=1;
			
			if(data[k]>=data[j]) break;
			
			swap(data,k,j);
			
			k=j;
		}
	}
	
	public int extractMax() {
		
		assert(count>0);
		
		int ret=data[1];
		
		swap(data,1,count);
		
		count--;
		
		//shiftdown(1);
		
		shiftdown2(1);  // 通过shiftdown 操作后，序列从大到小输出，因为总是会保持最大堆的性质
		
		return ret;
	}
	
	public void print(){
		
		for(int i=1;i<=this.count;i++)
			
			System.out.print(this.data[i]+" ");
	}
	
	
	public void treePrint(){

        if( size() >= 100 ){
        	
            System.out.println("This print function can only work for less than 100 integer");
            return;
        }

        System.out.println("The max heap size is: " + size());
        
        System.out.println("Data in the max heap: ");
        
        for( int i = 1 ; i <= size() ; i ++ ){
        	
            // 我们的print函数要求堆中的所有整数在[0, 100)的范围内
        	
            assert (Integer)data[i] >= 0 && (Integer)data[i] < 100;
            
            System.out.print(data[i] + " ");
        }
        System.out.println();
        
        System.out.println();

        int n = size();
        
        int maxLevel = 0;
        
        int numberPerLevel = 1;
        
        while( n > 0 ){
        	
            maxLevel += 1;
            
            n -= numberPerLevel;
            
            numberPerLevel *= 2;
        }

        int maxLevelNumber = (int)Math.pow(2, maxLevel-1);
        
        int curTreeMaxLevelNumber = maxLevelNumber;
        
        int index = 1;
        
        for( int level = 0 ; level < maxLevel ; level ++ ){

            String line1 = new String(new char[maxLevelNumber*3-1]).replace('\0', ' ');

            int curLevelNumber = Math.min(count-(int)Math.pow(2,level)+1,(int)Math.pow(2,level));
            
            boolean isLeft = true;
            
            for( int indexCurLevel = 0 ; indexCurLevel < curLevelNumber ; index ++ , indexCurLevel ++ ){
            	
                line1 = putNumberInLine( (Integer)data[index] , line1 , indexCurLevel , curTreeMaxLevelNumber*3-1 , isLeft );
                
                isLeft = !isLeft;
            }
            
            System.out.println(line1);

            if( level == maxLevel - 1 )
            	
                break;

            String line2 = new String(new char[maxLevelNumber*3-1]).replace('\0', ' ');
            
            for( int indexCurLevel = 0 ; indexCurLevel < curLevelNumber ; indexCurLevel ++ )
            	
                line2 = putBranchInLine( line2 , indexCurLevel , curTreeMaxLevelNumber*3-1 );
            
            System.out.println(line2);

            curTreeMaxLevelNumber /= 2;
        }
    }

    private String putNumberInLine( Integer num, String line, int indexCurLevel, int curTreeWidth, boolean isLeft){

        int subTreeWidth = (curTreeWidth - 1) / 2;
        
        int offset = indexCurLevel * (curTreeWidth+1) + subTreeWidth;
        
        assert offset + 1 < line.length();
        
        if( num >= 10 )
        	
            line = line.substring(0, offset+0) + num.toString()
            
                    + line.substring(offset+2);
        else{
            if( isLeft)
            	
                line = line.substring(0, offset+0) + num.toString()
                
                        + line.substring(offset+1);
            else
            	
                line = line.substring(0, offset+1) + num.toString()
                
                        + line.substring(offset+2);
        }
        return line;
    }

    private String putBranchInLine( String line, int indexCurLevel, int curTreeWidth){

        int subTreeWidth = (curTreeWidth - 1) / 2;
        
        int subSubTreeWidth = (subTreeWidth - 1) / 2;
        
        int offsetLeft = indexCurLevel * (curTreeWidth+1) + subSubTreeWidth;
        
        assert offsetLeft + 1 < line.length();
        
        int offsetRight = indexCurLevel * (curTreeWidth+1) + subTreeWidth + 1 + subSubTreeWidth;
        
        assert offsetRight < line.length();

        line = line.substring(0, offsetLeft+1) + "/" + line.substring(offsetLeft+2);
        
        line = line.substring(0, offsetRight) + "\\" + line.substring(offsetRight+1);

        return line;
    }

	
	public static void main(String[] args) {
		
		int Cap=20;
		
		Random random =new Random(20);
		
		heap_sort_test hst =new heap_sort_test(Cap);
		
		for(int i=0;i<Cap;i++) {
		
		hst.insert(random.nextInt(100));
		
		}
		
		//hst.print();
		
		//hst.treePrint();
		
		while(!hst.isEmpty()) {
		
		int b=hst.extractMax();
		
		System.out.print(b+" ");
		
		}
	
	}
}
