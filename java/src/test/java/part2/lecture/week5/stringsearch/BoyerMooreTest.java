package part2.lecture.week5.stringsearch;

import static org.junit.Assert.*;
import org.junit.Test;

public class BoyerMooreTest {

	@Test
	public void testBoyerMoore() {
		String docs = "substring search application";
		String pattern1 = "app";
		String pattern2 = "apa";
		
		BoyerMoore bm1 = new BoyerMoore(pattern1, 256);
		BoyerMoore bm2 = new BoyerMoore(pattern2, 256);
				
		assert(bm1.search(docs) == docs.indexOf(pattern1)); // success
		assert(bm2.search(docs) == docs.length()); // fail
	}
}
