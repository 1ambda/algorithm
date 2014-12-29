package part2.lecture.week6.regex;

import static org.junit.Assert.*;
import org.junit.Test;

public class NFATest {

	@Test
	public void test() {
		String pattern = "AB*A";
		String text1 = "Hello World ACA! NFA";
		String text2 = "Hello World AA! NFA";
		String text3 = "Hello World ABBBBA! NFA";
		
		NFA nfa = new NFA(pattern);
		
		assert(nfa.recognizes(text1) == false);
		assert(nfa.recognizes(text2) == true);
		assert(nfa.recognizes(text3) == true);
	}
}
