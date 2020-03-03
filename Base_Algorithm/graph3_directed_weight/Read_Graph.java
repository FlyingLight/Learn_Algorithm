/**
 * 
 */
package graph3_directed_weight;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;



/**
 * @author qiguangqin
 *
 */
public class Read_Graph {
	
	private Scanner scanner;
	
	private void readFile(String fileName) {
		
		assert fileName!=null;
		
		try {
			
			File file =new File(fileName);
			
			if(file.exists()) {
				
				FileInputStream fis=new FileInputStream(file);
				
				scanner=new Scanner(new BufferedInputStream(fis),"UTF-8");
				
				scanner.useLocale(Locale.ENGLISH);
			}
			
			else
				
				throw new IllegalArgumentException(fileName+ "doesn't exists");
					
		}
		catch(IOException ioe) {
			
			throw new IllegalArgumentException( "Could not open"+fileName,ioe);
		}
	}
	
	
	public Read_Graph(Graph graph,String fileName) {
		
		
		readFile(fileName);
		
		try {
			
			int V=scanner.nextInt();  //  先处理首行，vertex  and edges 
			
			if(V < 0)
				
				throw new IllegalArgumentException("number of vertices in a Graph must be nonegative");
			
			assert V==graph.V();  //  确认 vetrex 和实际一致 
			
			int E=scanner.nextInt();  // E 表示edges的数量
			
			if(E < 0)
				
				throw new IllegalArgumentException("number of Edges in a Graph must be nonegative");
			
			for (int i=0;i<E;i++) {   //  遍历每一条边 Edge
				
				int v=scanner.nextInt();
				
				int w=scanner.nextInt();
				
				int weight=scanner.nextInt();
				
				assert v>=0 && v<V;
				
				assert w>=0 &&w<V;
					
				graph.addEdge(v, w,weight);
				
			}	
		}
		
		catch(InputMismatchException e) {
			
			String token=scanner.next();
			
			throw new InputMismatchException("attempts to read an 'int' value from input stream, but the next token is \"" + token + "\"");
		}
		
		catch (NoSuchElementException e) {
			
            throw new NoSuchElementException("attemps to read an 'int' value from input stream, but there are no more tokens available");
        }
	}

}
