package part2.lecture.week5.stringsearch;

import static org.junit.Assert.*;

import org.junit.Test;

public class KMPTest {
	@Test
	public void testKMP() {
		String docs = "substring search application";
		String pattern1 = "app";
		String pattern2 = "apa";
		
		DFA dfa1 = new DFA(pattern1, 256); 
		DFA dfa2 = new DFA(pattern2, 256);
		
		assert(dfa1.search(docs) == docs.indexOf(pattern1)); // success
		assert(dfa2.search(docs) == docs.length()); // fail
	}
}
