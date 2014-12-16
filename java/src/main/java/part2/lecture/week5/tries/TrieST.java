package part2.lecture.week5.tries;

import java.util.LinkedList;
import java.util.Queue;

public class TrieST<Value> {

	private static final int R = 256; // extended ASCII
	private Node root = new Node();
	private int N;

	private static class Node {
		private Object val;
		private Node[] next = new Node[R];
	}
	
	public TrieST() { N = 0; }
	
	public int size() { return N; }
	
	public boolean isEmpty() { return size() == 0; }

	public Value get(String key) {
		Node x = get(root, key, 0);
		
		if (x == null) return null;
		return (Value) x.val;
	}
	
	public void delete(String key) {
		root = delete(root, key, 0);
	}

	private Node delete(Node x, String key, int d) {
		if (x == null) return null;
		
		if (d == key.length()) {
			if (x.val != null) N--;
			x.val = null;
		} else {
			char c = key.charAt(d);
			x.next[c] = delete(x.next[c], key, d + 1);
		}
		
		// remove subtrie rooted at x if it is completely empty
		if (x.val != null) return x;
		for (int c = 0; c < R; c++)
			if (x.next[c] != null) return x;
		
		return null;
	}

	private Node get(Node x, String key, int d) {
		if (x == null) return null;
		if (d == key.length()) return x;
		char c = key.charAt(d);
		return get(x.next[c], key, d + 1);
	}
	
	public boolean contains(String key) {
		return get(key) != null;
	}

	public void put(String key, Value val) {
		if (val == null) delete(key);
		else root = put(root, key, val, 0);
	}

	private Node put(Node x, String key, Value val, int d) {
		if (x == null) x = new Node();
		if (d == key.length()) {
			if (x.val == null) N++;
			x.val = val;
			return x;
		}
		
		char c = key.charAt(d);
		x.next[c] = put(x.next[c], key, val, d + 1);
		return x;
	}

	// char-based operations
	private void collect(Node x, StringBuilder prefix, Queue<String> q) {
		// TODO Auto-generated method stub
	
		if (x == null) return;
		if (x.val != null) q.add(prefix.toString());
		
		for (char c = 0; c < R; c++) {
			prefix.append(c);
			collect(x.next[c], prefix, q);
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}
	
	public Iterable<String> keys() { return keysWithPrefix(""); }

	public Iterable<String> keysWithPrefix(String prefix) {
		Queue<String> q = new LinkedList<String>();
		Node x = get(root, prefix, 0);
		collect(x, new StringBuilder(prefix), q);
		return q;
	}
	
	public String longestPrefixOf(String query) {
		int length = search(root, query, 0, 0);
		return query.substring(0, length);
	}

	private int search(Node x, String query, int d, int length) {
		if (x == null) return length;
		if (x.val != null) length = d;
		if (d == query.length()) return length;
		
		char c = query.charAt(d);
		return search(x.next[c], query, d + 1, length);
	}
	
	
}
