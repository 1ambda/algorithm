package part2.lecture.week6.compression;

import java.util.PriorityQueue;


// ref: http://algs4.cs.princeton.edu/55compression/Huffman.java.html
public class Huffman {

	// support extended ASCII
	private static final int R = 256;
	
	private static class Node implements Comparable<Node> {

		private char ch;
		private int  freq;
		private final Node left, right;
	
		public Node(char ch, int freq, Node left, Node right) {
			this.ch = ch;
			this.freq = freq;
			this.left = left;
			this.right = right;
		}
	
		public boolean isLeaf() {
			return left == null && right == null;
		}

		@Override
		public int compareTo(Node that) {
			return this.freq - that.freq;
		}
	}
	
	public static void compress() {
		String s = BinaryStdIn.readString(); // input
		char[] input = s.toCharArray();
		
		// tabulate freq counts
		int[] freq = new int[R];
		for (int i = 0; i < R; i++)
			freq[input[i]]++;
		
		// build huffman trie
		Node root = buildTrie(freq);
		
		// build syombol table
		String[] st = new String[R];
		buildCode(st, root, "");
		
		// print trie for decoder
		writeTrie(root);
		
		// print N (# of input)
		BinaryStdOut.write(input.length);
		
		// encode
		for (int i = 0; i < input.length; i++) {
			String code = st[input[i]];
			
			// traverse huffman trie
			for (int j = 0; j < code.length(); i++) {
				if (code.charAt(j) == '0')
					BinaryStdOut.write(false);
				else if (code.charAt(j) == '1')
					BinaryStdOut.write(true);
				else throw new IllegalStateException("Illegal State");
			}
		}
		
		BinaryStdOut.close();
	}
	
	public static void expand() {
		  Node root = readTrie(); // read in encoding trie
		  int N = BinaryStdIn.readInt(); // read in # of chars
		  
		  for (int i = 0; i < N; i++) {
		    Node x = root;
		    while (!x.isLeaf()) {
		      if (!BinaryStdIn.readBoolean())
		        x = x.left;  // 0
		      else
		        x = x.right; // 1
		    }
		    
		    BinaryStdOut.write(x.ch, 8); // print char
		  }
		  
		  BinaryStdOut.close();
		
	}
	private static Node readTrie() {
		// leaf
		if (BinaryStdIn.readBoolean()) {
		  char c = BinaryStdIn.readChar(8);
		  return new Node(c, 0, null, null);
		}
		  
		Node l = readTrie(); 
		Node r = readTrie();
		return new Node('\0', 0, l, r);
	}
	
	private static void writeTrie(Node x) {
		if (x.isLeaf()) {
			BinaryStdOut.write(true);    // leaf
			BinaryStdOut.write(x.ch, 8); // print char
			return;
		}
		
		// pre-order traversal
		BinaryStdOut.write(false); // internal node
		writeTrie(x.left);
		writeTrie(x.right);
	}
	
	private static Node buildTrie(int[] freq) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		
		for (char c = 0; c < R; c++) 
			if (freq[c] > 0)
				pq.add(new Node(c, freq[c], null, null));
		
		// if only one char
		if (pq.size() == 1) {
			if (freq['\0'] == 0) pq.add(new Node('\0', 0, null, null));
			else                 pq.add(new Node('\1', 0, null, null));
		}
		
		// merge two tries
		while (pq.size() > 1) {
			Node l = pq.remove();
			Node r = pq.remove();
			Node p = new Node('\0', l.freq + r.freq, l, r); // parent
			pq.add(p);
		}
		
		return pq.remove();
	}

	// make a symbol table
	private static void buildCode(String[] st, Node x, String s) {
		if (!x.isLeaf()) {
			buildCode(st, x.left, s + '0');
			buildCode(st, x.right, s + '1');
		} else {
			st[x.ch] = s; 
		}
	}
}

