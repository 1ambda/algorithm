package part2.lecture.week3.radixsort;

import static org.junit.Assert.*;

import org.junit.Test;

public class KeyIndexedCountingTest {

	@Test
	public void test() {
		
		int[] arr = {3, 7, 8, 6, 5, 5, 4, 3, 1};
		// Sort an array a[] of N integers between 0 and R - 1
		for(int i : arr)
			System.out.print(i);
		
		System.out.println();
		Strings.keyIndexedCounting(arr, 9);
		for (int i : arr)
			System.out.print(i);
	}
}
