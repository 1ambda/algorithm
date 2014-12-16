package part2.lectrue.week5.tries;

import org.junit.Test;

import part2.lecture.week5.tries.TrieST;

public class TrieTest {

	@Test
	public void test() {
		TrieST st = new TrieST();
		
		st.put("three", (Object)3);
		st.put("four", (Object)4);
		st.put("five", (Object)4);
		st.put("six", (Object)4);
		st.put("nine", (Object)9);
		
		System.out.println(st.contains("four"));
		System.out.println(st.contains("five"));
		System.out.println();
		
		st.delete("three");
		System.out.println(st.contains("four"));
		System.out.println(st.contains("three"));
		System.out.println();
	
		for (Object s : st.keys())
			System.out.println(s);
		System.out.println();

		for (Object s : st.keysWithPrefix("f"))
			System.out.println(s);
		System.out.println();
	}
}
