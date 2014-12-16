package part2.lecture.week3.radixsort;

import java.util.Arrays;

public class Strings {

	public static String reverse1(String s) {
		
		String rev = "";
		int n = s.length();
		
		for (int i = 0; i < n; i++)
			rev += s.charAt(n - i - 1);
		
		return rev;
	}

	public static String reverse2(String s) {
		
		StringBuilder rev = new StringBuilder();
		int n = s.length();

		for (int i = 0; i < n; i++)
			rev.append(s.charAt(n - i - 1));
		
		return rev.toString();
	}

	// time: ~ 11N + 4R, space: N + R 
	public static void keyIndexedCounting(int[] a, int R) {

		int N = a.length;
		int[] count = new int[R + 1];
		int[] aux = new int[N];
	
		// count frequencies
		for (int i = 0; i < N; i++)
			count[a[i] + 1]++;
		
		// accumulate freq
		for (int r = 0; r < R; r++)
			count[r + 1] += count[r];
		
		// sort
		for (int i = 0; i < N; i++)
			aux[count[a[i]]++] = a[i];
		
		// copy
		for (int i = 0; i < N; i++)
			a[i] = aux[i];
	}
	
	// W: fixed-length of strings
	public static void LSDsort(String[] a, int W) {
		int N = a.length;
		int R = 256;
		String[] aux = new String[N];
		
		// key indexed counting for each digit from right to left
		for (int d = W - 1; d >= 0; d--) {
			int[] count = new int[R + 1];
			
			// count frequencies
			for (int i = 0; i < N; i++) 
				count[a[i].charAt(d) + 1]++;
				
			for (int r = 0; r < R; r++) 	
				count[r + 1] += count[r];
			
			for (int i = 0; i < N; i++)
				aux[count[a[i].charAt(d)]++] = a[i];
		
			for (int i = 0; i < N; i++)
				a[i] = aux[i];
		}
	}
	
	// helper methods for MSD sort
	
	private static int charAt(String s, int d) {
		  if (d < s.length()) return s.charAt(d);
		  else return -1;
	}
	
	// substring comparison is much faster than charAt comparison
	private static boolean less(String v, String w, int d) {
		return v.substring(d).compareTo(w.substring(d)) < 0;
	}
	
	private static void isort(String[] a, String[] aux, int l, int h, int d) {
		// insertion sort
		for (int i = l; i <= h; i++)
			for (int j = i; j > l && less(a[j], a[j - 1], d); j--) {
				// swap a[j - 1], a[j]
				String temp = a[j - 1];
				a[j - 1] = a[j];
				a[j] = temp;
			}
			
	}
	
	private static void msdSort(String[] a, String[] aux, int l, int h, int d) {
		
		int CUTOFF = 15;
		if (h <= l + CUTOFF) {
			isort(a, aux, l, h, d);
			return;
		}
		
		int R = 256;
		int[] count = new int[R + 2];
		
		// count frequencies
		for (int i = l; i <= h; i++) {
			int c = charAt(a[i], d);
			count[c + 2]++;
		}
		
		// accumulate
		for (int r = 0; r < R + 1; r++)
			count[r + 1] += count[r];
		
		// sort
		for (int i = l; i <= h; i++) {
			int c = charAt(a[i], d);
			aux[count[c + 1]++] = a[i];
		}
		
		// copy
		for (int i = l; i <= h; i++)
			a[i] = aux[i - l];
		
		// solve sub-arrays
		for (int r = 0; r < R; r++)
			msdSort(a, aux, l + count[r], l + count[r + 1] - 1, d + 1);
	}
	
	public static void MSDsort(String[] a) {
		String[] aux = new String[a.length];
		msdSort(a, aux, 0, a.length - 1, 0);
	}

	// 3-Way Quicksort
	public static void Quicksort3way(String[] a) {
		qsort3way(a, 0, a.length - 1, 0);
	}
	
	// helper method for 3 way quicksort
	private static void swap(String[] a, int i, int j) {
		String temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	private static void qsort3way(String[] a, int l, int h, int d) {
		if (h <= l) return;
	
		int lt = l, gt = h;
		int v = charAt(a[l], d);
		int i = l + 1;
		
		// partition
		while (i <= gt) {
			int t = charAt(a[i], d);
			
			if      (t < v) swap(a, lt++, i++);
			else if (t > v) swap(a, i, gt--);
			else            i++;
		}
		
		// a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]
		qsort3way(a, l, lt - 1, d);
		if (v >= 0) qsort3way(a, lt, gt, d + 1);
		qsort3way(a, gt + 1, h, d);
	}

	// longrest common prefix
	public static String lcp(String v, String w) {
		
		int n = Math.min(v.length(), w.length());
		
		for (int i = 0; i < n; i++) {
			if (v.charAt(i) != w.charAt(i)) return v.substring(0, i);
		}
		
		return v.substring(0, n);
	}
	
	public static String lrs(String s) {
	
		int N = s.length();
		String[] suffixes = new String[N];
	
		// form suffixes
		for(int i = 0; i < N; i++)
			suffixes[i] = s.substring(i, N);

		// sort
		Arrays.sort(suffixes);
		
	
		// find longest repeated substring using lcp
		String lrs = "";
		
		for (int i = 0; i < N - 1; i++) {
			String x = lcp(suffixes[i], suffixes[i + 1]);
			
			if (x.length() > lrs.length()) lrs = x;
		}
		
		return lrs;
	}
}







