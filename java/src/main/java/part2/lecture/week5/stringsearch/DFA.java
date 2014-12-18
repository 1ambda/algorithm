package part2.lecture.week5.stringsearch;

public class DFA {
	
	int[][] dfa;
	String pattern;
	int R;
	
	public DFA(String pattern, int R) {
		this.pattern = pattern;
		this.R = R;
		int M = pattern.length();
	
		// initialize
		dfa = new int[R][];
		for (int r = 0; r < R; r++)
			dfa[r] = new int[M];
		
		dfa[pattern.charAt(0)][0] = 1;
		
		// build DFA
		for (int X = 0, j = 1; j < M; j++) {
			// mismatch
			for (int c = 0; c < R; c++)
				dfa[c][j] = dfa[c][X];
			
			// match
			dfa[pattern.charAt(j)][j] = j + 1;
			
			// update X
			X = dfa[pattern.charAt(j)][X];
		}
	}

	public int search(String docs) {
		
		int i, j, M = pattern.length(), N = docs.length();
		
		for (i = 0, j = 0; i < N && j < M; i++) {
			j = dfa[docs.charAt(i)][j];
		}
		
		if (j == M) return i - M;
		else return N;
	}
}
