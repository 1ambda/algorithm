package part2.lecture.week5.stringsearch;

public class StringSearch {

	public static int bruteForce(String pattern, String docs) {
		
		int M = pattern.length();
		int N = docs.length();
	
		for (int i = 0; i < N - M; i ++) {
			int j;
			
			for (j = 0; j < M; j++) 
				if (pattern.charAt(j) != docs.charAt(i + j)) break;
			
			if (j == M) return i;
		}
	
		return N;
	}

	public static int bruteForceBackup(String pattern, String docs) {
		int i, N = docs.length();
		int j, M = pattern.length();
		
		for (i = 0, j = 0; i < N && j < M; i++) 
			if (docs.charAt(i + j) == pattern.charAt(j)) j++;
			else { i -= j; j = 0; }
		
		if (j == M) return i - M;
		else return N;
	}
}
