package part2.lecture.week5.stringsearch;

import static org.junit.Assert.*;

import org.junit.Test;

public class RabinKarpTest {

	@Test
	public void test() {
		String docs = "substring search application";
		String pattern1 = "app";
		String pattern2 = "apa";

		RabinKarp rk1 = new RabinKarp(pattern1);
		RabinKarp rk2 = new RabinKarp(pattern2);
		
		assert(rk1.search(docs) == docs.indexOf(pattern1)); // success
		assert(rk2.search(docs) == docs.length()); // fail
	}

}
