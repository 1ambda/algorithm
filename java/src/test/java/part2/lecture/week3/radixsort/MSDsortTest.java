package part2.lecture.week3.radixsort;

import static org.junit.Assert.*;

import org.junit.Test;

public class MSDsortTest {

	@Test
	public void test() {
		String[] arr = {"BPACD", "APPC", "APPAC", "APPLE", "A", "BDBD", "Z"};
		// Sort an array a[] of N integers between 0 and R - 1
		for(String s : arr)
			System.out.print(s + " ");
		
		System.out.println();
		Strings.MSDsort(arr);
		for(String s : arr)
			System.out.print(s + " ");
	}

}
