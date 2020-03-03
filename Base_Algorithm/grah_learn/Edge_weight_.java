package grah_learn;

public class Edge_weight_<Weight extends Number & Comparable > implements Comparable<Edge_weight_> {

	private int a,b;
	
	private Weight weight;
	
	public Edge_weight_(int a,int b,Weight weight) {
		
		this.a=a;
		
		this.b=b;
		
		this.weight=weight;
		
	}
	
	
	public Edge_weight_(Edge_weight_<Weight>e) {
		
		this.a=e.a;
		
		this.b=e.b;
		
		this.weight=e.weight;
		
	}
	
	public int v()  {
		
		return a;
	}
	
	
	public int w() {
		
		return b;
	}
	
	public Weight wt()  {return weight;}
	
	public int ohter(int x) {
		
		assert x==a || x==b;
		
		return x==a ?b:a;
	}
	
	public int compareTo(Edge_weight_ that) {
		
		
		if(weight.compareTo(that.wt())<0)
			
			return -1;
		
		else if(weight.compareTo(that.w())>0)
			
			return +1;
		
		else
			
			return 0;
			
		
	}
}
