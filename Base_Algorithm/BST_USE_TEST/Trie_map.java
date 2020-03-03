package BST_USE_TEST;


import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap; // based on the red-black tree, is-balanced tree

public class Trie_map {
	
	
	private class Node{
		
		public boolean isWord;
		
		public TreeMap<Character,Node>next; //  汉语字符 不是character类
		
		public Node(boolean isWord) {
			
			this.isWord=isWord;
			
			next=new TreeMap<>();  // 可以基于红黑树、或者AVL 树实现一个map
		}
		
		public Node() {
			
			this(false);
		}
	
	}
	
	
	private Node node_root;
	
	private int size;
	
	public Trie_map() {
		
		this.node_root=new Node();
		
		this.size=0;
	}
	
	public int getSize() {
		
		return this.size;
	}
	
	public void add(String word) {
		
		Node node_cur=node_root;
		
		for(int i=0;i<word.length();i++) {
			
			char c=word.charAt(i);
			
			if(node_cur.next.get(c)==null)
				
				node_cur.next.put(c, new Node());
			
			// trie tree each node represent a character (and map  next char2---> node)
			
			//  node_cur.next.get(c) a final value
			
			// char save at the edge
			
			node_cur=node_cur.next.get(c);
		}
		
		// reach the end of a word
		
		if(!node_cur.isWord) {
		
		node_cur.isWord=true;
		
		this.size++;
		
		}
	}
	
	
	public boolean contain(String word) {
		
		Node node_cur=node_root;
		
		for(int i=0;i<word.length();i++) {
			
			// i one more 
			
			char queryLetter=word.charAt(i);
			
			if(node_cur.next.get(queryLetter)==null)
				
				return false;
			
			node_cur=node_cur.next.get(queryLetter);
		}
		
		return node_cur.isWord; // last node to decide isWord(whether is just a prefix )
	}
	
	public void add_recursive(String word) {
		
		_add_recursive(node_root,word,0);
		
		
	}
	
	private void _add_recursive(Node node_cur,String word,int index) {
		
		if(!node_cur.isWord && index==word.length()) {  // last node to mark as isWord
			
			node_cur.isWord=true;
			
			this.size++;
		}
		
		else if(index<word.length()) {
			
			char addLetter=word.charAt(index);
			
			if(node_cur.next.get(addLetter)==null)
				
				node_cur.next.put(addLetter, new Node());  //  IF add need recurse too!!
			
			_add_recursive(node_cur.next.get(addLetter),word,index+1);
		}
	}
	
	public boolean contain_recursive(String word) {
		
		return _contain_recursive(node_root,word,0);
		
	}
	
	
	private boolean _contain_recursive(Node node_cur,String word,int index) {
		
		if(index==word.length()) 
			
			return node_cur.isWord;
		
		else {
			
			char queryLetter= word.charAt(index);
			
			if(node_cur.next.get(queryLetter)==null)
				
				return false;
			
			return _contain_recursive(node_cur.next.get(queryLetter),word,index+1);
		}
		
	}
	
	
	
	public boolean isPrefix(String prefix) {
		
		Node node_cur=node_root;
		
		for(int i=0;i<prefix.length();i++) {
			
			// i one more 
			
			char queryLetter=prefix.charAt(i);
			
			if(node_cur.next.get(queryLetter)==null)
				
				return false;
			
			node_cur=node_cur.next.get(queryLetter);
		}
		
		return true; //prefix no need to descide the word 
	}
	
	
		

	public static void main(String[] args) {
		
		Trie_map tm= new Trie_map();
		
		List<String> list =new ArrayList<>();
		
		list.add("qiguangqin");
		
		list.add("what");
		
		list.add("name");
		
		for(String e:list) 
			
			tm.add(e);
		
		System.out.println(tm.contain("name"));

	}

}
