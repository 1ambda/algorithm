package part2.lecture.week5.stringsearch;

import static org.junit.Assert.*;

import org.junit.Test;

public class BruteForceStringSearchTest {

	@Test
	public void testBruteForce() {
		String docs = "substring search application";
		String pattern = "app";
		String pattern2 = "apa";

		int idx1 = StringSearch.bruteForceBackup(pattern, docs);
		assert(idx1 == docs.indexOf(pattern));

		
		int idx2 = StringSearch.bruteForceBackup(pattern2, docs);
		assert(idx2 == docs.length());
	}
}
