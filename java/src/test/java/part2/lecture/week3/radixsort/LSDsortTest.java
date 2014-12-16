package part2.lecture.week3.radixsort;

import static org.junit.Assert.*;

import org.junit.Test;

public class LSDsortTest {

	@Test
	public void test() {
		String[] arr = {"BPAC", "APPZC", "APPAC", "APPLE"};
		// Sort an array a[] of N integers between 0 and R - 1
		for(String s : arr)
			System.out.print(s + " ");
		
		System.out.println();
		Strings.LSDsort(arr, 5);
		for(String s : arr)
			System.out.print(s + " ");
	}
}
