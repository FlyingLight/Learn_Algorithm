package grah_learn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Euler_Loop_get {
	
	private Graph_Euler graph;
	
	private ArrayList<ArrayList<Edge>>res;
	
	private boolean isLoop;
	
	private int special_w=0;
	
	public Euler_Loop_get(Graph_Euler graph) {
		

		this.graph = ( Graph_Euler)graph.clone();
		
		this.res= new ArrayList<>();
		
	}
	
	public boolean hasEulerLoop() {
		
		Components_V2 cc= new Components_V2(graph);
		
		if(cc.getCcount()>1) return false;
		
		else {
			
			for(int v=0;v<graph.V();v++) 
				
				if(graph.degree(v)%2==1)  return false;
				
			return true;
		}
	}
	
	// Using Hierholzer Method(Non-recursive) to judge Euler Loop
	
	/*
	 
	 0       3
	   \   /
	 |   2   |
	  /    \
	 1       4
	 
	 cur_vet=[0, 0]
	 cur_vet=[0, 0, 1]
     cur_vet=[0, 0, 1, 2]  
     
     degree(0)==0  pop 0   
     
     cur_v=0    cur_v'=2   cur_vet' =[0,0,1]  res=[0]
     
     degree(2)!=0  add(2) cur_vet=[0,0,1,2]
     
     cur_vet=[0, 0, 1, 2, 3]  
     
     cur_vet=[0, 0, 1, 2, 3, 4]  pop 2 cur_vet=[0,0,1,2,3] res=[0,2]
     
     ----------------------------------------------------------------------
     
     cur_v=2  cur_v'=4    cur_vet'=[0, 0, 1, 2, 3]  res=[0,2]
     
     cur_v=4  cur_v'=3    cur_vet'=[0, 0, 1, 2]  res=[0,2,4]
     
     cur_v=3  cur_v'=2    cur_vet'=[0, 0, 1]  res=[0,2,4,3]
     
     cur_v=2  cur_v'=1    cur_vet'=[0, 0]   res=[0,2,4,3,2]
     
     cur_v=1 cur_v'=0     cur_vet'=[0]     res=[0,2,4,3,2,1]
     
     cur_v=0 cur_v'=[null] cur_vet'=[]   res=[0,2,4,3,2,1,0]
     
     reverse res= [0, 1, 2, 3, 4, 2, 0]
	  
	 */
	
	public ArrayList<Integer>result(){
		
		ArrayList<Integer> res= new ArrayList<>();
		
		if(!this.hasEulerLoop()) return res;
		
		Stack<Integer> cur_vet= new Stack<>();
		
		int cur_v=0;
		
		Graph_Euler g= (Graph_Euler)graph.clone();
		
		cur_vet.push(cur_v);
		
		while(!cur_vet.isEmpty()) {
			
			if(g.degree(cur_v)!=0) {  // haven't form the cycle with current vertex
				
				cur_vet.push(cur_v);
				
				//System.out.println(cur_vet);
				
				int w=g.adj(cur_v).iterator().next(); // traverse the next edge 
				
				g.removeEdge(cur_v, w);
				
				cur_v=w;
			}
			
			else {
				
				//System.out.println("pp"+cur_v);
				
				res.add(cur_v);  //  have form the cycle with current vertex
				
				cur_v=cur_vet.pop();  // needed to back_track
				
				//System.out.println(cur_vet);
			}
			
		}
		
		return res;
	}
	
	
	
	public ArrayList<Integer> result2(){

		ArrayList<Integer> res= new ArrayList<>();

		if(!this.hasEulerLoop()) return res;
			
			Stack<Integer> cur_vet= new Stack<>();
			
			int cur_v=0;
			
			Graph_Euler g= (Graph_Euler)graph.clone();
			
			cur_vet.push(cur_v);

			while(!cur_vet.isEmpty()){

				if(g.degree(cur_v)!=0){

					int w=g.adj(cur_v).iterator().next();
					
					//System.out.println("w="+w);

					g.removeEdge(cur_v,w);

					cur_vet.push(w);

					cur_v=w;
					
					//System.out.println("cur_vet="+cur_vet);
				}

				else{

					cur_v=cur_vet.peek();
					
					//System.out.println("cur_v'="+cur_v);
					
					//System.out.println("cur_vet'="+cur_vet);
					
					if(g.degree(cur_v)==0) {
						
						res.add(cur_v);
						
						cur_vet.pop();
					
					//System.out.println("res'="+res);
						}
					
				}

			}
			
			Collections.reverse(res);
			
			return res;
	}
	
	
	// Using Hierholzer Method(recursive) to judge Euler Loop
	
	public ArrayList<Integer>get_res_recursive() {
		
		ArrayList<Integer> res= new ArrayList<>();
		
		if(!this.hasEulerLoop()) return res;
		
		result_recur(graph,0,res);
		
		return res;
	}
	
	private void result_recur(Graph_Euler graph,int cur_v,ArrayList<Integer> res) {
		
		
		if(graph.degree(cur_v)==0) {
			
			res.add(cur_v);
			
			return;
			
		}
		
		else{
			
			while(graph.degree(cur_v)!=0) {
				
				int w= graph.adj(cur_v).iterator().next();
				
				graph.removeEdge(cur_v,w);
				
				result_recur(graph,w,res);
			
			}
			
			res.add(cur_v);
		}
	}
	
	
	public ArrayList<ArrayList<Edge>>get_backTrack_Euler() {
		
		if(!this.hasEulerLoop()) return res;
		
		ArrayList<Edge>tmp_list=new ArrayList<>();
		
		this.backTrack_Euler(graph, 0, 0, graph.E(), tmp_list);
		
		return res;
	}
	
	private void backTrack_Euler(Graph_Euler graph,int v,int parent,int left,ArrayList<Edge>tmp_list) {
		
		if(v!=parent) {
			
			isLoop=false;
			
			graph.removeEdge(v, parent);
			
			System.out.println("v="+v+" "+"parent="+parent);
			
			left--; //System.out.println("pp"+left);
			
			tmp_list.add(new Edge(parent,v));//System.out.println(tmp_list);
			
		}
		
		 if(graph.degree(v)==0 && left==0) {
			
			res.add(new ArrayList<>(tmp_list));//System.out.println("dadf");
			
			return;
			
		}
		
		else {
		
			while(graph.degree(v)!=0 && left!=0) {
				
				int w=0;//System.out.print("v="+v+" ");//if(isLoop==false)
				
				w=graph.adj(v).iterator().next();
				
				if(isLoop==true) {
					
					//w=graph.adj(v).iterator().next();  
					
					graph.addEdge(v, special_w);
					
					//System.out.println("specail v="+v+" special parent="+parent);
					
				}
					
					//for(int q:graph.adj(v)) System.out.print("q="+q);
					
					//System.out.println("w="+w+" "+"v="+v);
					
					backTrack_Euler(graph,w,v,left,tmp_list);
				}
			
			}
			
		if(graph.degree(v)==0 &&left!=0) {
			
			this.isLoop=true;
		
			special_w=v;
	
			tmp_list.remove(tmp_list.size()-1);
			
			//return ;
			
			}
		
		}
		
	
	public static void main(String[] args) {
	
		String fileName9="E:\\iotest\\testG9.txt";
		
		Graph_Euler g9=new Graph_Euler(5,false);
	        
	    new Read_Graph(g9,fileName9);
	    
	    g9.show();
	    
	    Graph_Euler g= (Graph_Euler)g9.clone();
	    
	    Euler_Loop_get elg= new Euler_Loop_get(g);
	    
	   ArrayList<Integer> res= elg.result2();
	    
       Collections.reverse(res);
	    
      System.out.println(res);
	   
	   
	  List<Integer>res2= elg.get_res_recursive();
	   
	   System.out.println(res2);
	   
	   
	    
	   //System.out.println( elg.get_backTrack_Euler());
	    
	  
		
	}

}
