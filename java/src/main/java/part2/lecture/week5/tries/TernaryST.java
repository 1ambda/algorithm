package part2.lecture.week5.tries;

import java.util.LinkedList;
import java.util.Queue;

public class TernaryST<Value> {

	private int N;
	private Node root;
	
	private class Node {
		private char c;
		private Value val;
		private Node left, mid, right;
	}
	
	public TernaryST() { N = 0; }
	
	public int size() { return N; }
	public boolean isEmpty() { return size() == 0; }
	
	public boolean contains(String key) {
		return get(key) != null;
	}
	
	public Value get(String key) {
		if (key == null) throw new NullPointerException();
		if (key.length() == 0) throw new IllegalArgumentException("key shouldn't be empty");
		
		Node x = get(root, key, 0);
		if (x == null) return null;
		return x.val;
	}

	private Node get(Node x, String key, int d) {
		if (key == null) throw new NullPointerException();
		if (key.length() == 0) throw new IllegalArgumentException("key shouldn't be empty");
		if (x == null) return null;
		
		char c = key.charAt(d);
		if      (c < x.c) 				return get(x.left, key, d);
		else if (c > x.c) 				return get(x.right, key, d);
		else if (d < key.length() - 1)  return get(x.mid, key, d + 1);
		else 							return x;
	}
	
	public void put(String key, Value val) {
		if (!contains(key)) N++;
		root = put(root, key, val, 0);
	}

	private Node put(Node x, String key, Value val, int d) {
		char c = key.charAt(d);
		
		if (x == null) {
			x = new Node();
			x.c = c;
		}
		
		if 		(c < x.c) 				x.left = put(x.left, key, val, d);
		else if	(c > x.c) 				x.right = put(x.right, key, val, d);
		else if (d < key.length() - 1)	x.mid = put(x.mid, key, val, d + 1);
		else 							x.val = val;
				
		return x;
	}
	
}
