package part2.lectrue.week5.tries;

import org.junit.Test;

import part2.lecture.week5.tries.TernaryST;

public class TernaryStTest {

	@Test
	public void test() {
		TernaryST st = new TernaryST();
		
		st.put("three", (Object)3);
		st.put("four", (Object)4);
		
		System.out.println(st.contains("four"));
		System.out.println(st.contains("five"));
	}

}
