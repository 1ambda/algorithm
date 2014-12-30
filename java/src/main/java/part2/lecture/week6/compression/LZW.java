package part2.lecture.week6.compression;

// ref: http://algs4.cs.princeton.edu/55compression/LZW.java.html
public class LZW {

	private static final int R = 256;  // # of input chars
	private static final int L = 4096; // # of codewords = 2^W
	private static final int W = 12;   // codeword with
	
	public static void compress() {
		String input = BinaryStdIn.readString();
		
		// single char
		TST<Integer> st = new TST<Integer>();
		for (int i = 0; i < R; i++) 
			st.put("" + (char) i, i);
		int code = R + 1;
		
		while (input.length() > 0) {
			String s = st.longestPrefixOf(input);
			BinaryStdOut.write(st.get(s), W); // print s's codeword
			
			int t = s.length();
			// add (s + 1 char) to symbol table
			if (t < input.length() && code < L)
				st.put(input.substring(0, t + 1), code++);
			
			input = input.substring(t);
		}
		
		BinaryStdOut.write(R, W); // print terminate codeword R
		BinaryStdOut.close();
	}
	
	public static void expand() {
		String[] st = new String[L];
		int i; // next available codeword value
		
		for (i = 0; i < R; i++) 
			st[i] = "" + (char) i;
		
		st[i++] = ""; // termination codeword
		
		int codeword = BinaryStdIn.readInt(W);
		if (codeword == R) return; // if empty message
		String val = st[codeword];
		
		while(true) {
			BinaryStdOut.write(val);
			
			codeword = BinaryStdIn.readInt(W);
			if (codeword == R) break;
			
			String s = st[codeword];
			
			// tricky case
			if (codeword == i) s = val + val.charAt(0);
			// add string into table
			if (i < L) st[i++] = val + s.charAt(0);
			
			val = s;
		}
		
		BinaryStdOut.close();
	}
}












