package part2.lecture.week5.stringsearch;

public class BoyerMoore {
	
	int[] right;
	String pattern;

	public BoyerMoore(String pattern, int R) {

		this.pattern = pattern;
		int M = pattern.length();
	
		// initialize skip table
		right = new int[R];
		for (int i = 0; i < R; i++) right[i] = -1;
		for (int j = 0; j < M; j++) right[pattern.charAt(j)] = j;
	}
	
	public int search(String docs) {

		int M = pattern.length();
		int N = docs.length();
		
		for (int i = 0; i <= N - M; i++) {
			int skip = 0;
			
			for (int j = M - 1; j >=0; j--) {
				if (pattern.charAt(j) != docs.charAt(i + j)) {
					// calculate skip value
					skip = Math.max(1, j - right[docs.charAt(i + j)]);
					break;
				}
			}
			
			if (skip == 0) return i;
		}
		
		return N;
	}
}
