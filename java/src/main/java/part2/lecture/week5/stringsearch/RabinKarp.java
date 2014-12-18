package part2.lecture.week5.stringsearch;

import java.math.BigInteger;
import java.util.Random;

public class RabinKarp {
	
	String pattern;
	long patternHash;
	int M;
	long Q;
	int R;
	long RM; // R^(M-1) % Q

	public RabinKarp(String pattern) {
		this.pattern = pattern;
		
		R = 25;
		M = pattern.length();
		Q = longRandomPrime();
		
		// pre-compute R^(M-1) % Q for use in removing leading digit
		RM = 1;
		for (int i = 1; i <= M-1; i++)
			RM = (RM * R) % Q;
		
		patternHash = hash(pattern, M);
	}
	
	private long hash(String key, int M) {
		long h = 0;
		
		for (int j = 0; j < M; j++)
			h = (R * h + key.charAt(j)) % Q; 
		return h;
	}
	
	private static long longRandomPrime() {
		BigInteger prime = BigInteger.probablePrime(31, new Random());
		return prime.longValue();
	}
	
	public int search(String docs) {
		int N = docs.length();
		long docsHash = hash(docs, M);
	
		if (docsHash == patternHash) return 0;
		
		for (int i = M; i < N; i++) {
			// remove leading digit
			docsHash = (docsHash + Q - RM * docs.charAt(i-M) % Q) % Q;
			// add trailing digit
			docsHash = (docsHash * R + docs.charAt(i)) % Q;
			
			// match
			if (patternHash == docsHash) return i - M + 1;
		}
		
		return N;
	}
}
