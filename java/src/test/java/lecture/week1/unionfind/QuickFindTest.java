package lecture.week1.unionfind;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import lecture.week1.unionfind.QuickFind;
import lecture.week1.unionfind.UnionFind;

import org.junit.Before;
import org.junit.Test;


public class QuickFindTest {
	
	private UnionFind uf;
	
	@Before
	public void setup() {
		uf = new QuickFind(10);
	}

	@Test
	public void testConnected() {
		assertThat(uf.connected(0, 1), is(false));
	}
	
	@Test
	public void testUnion() {
		assertThat(uf.connected(0, 1), is(not(true)));
		
		uf.union(0, 1);
		uf.union(0, 2);
		
		assertThat(uf.connected(0, 1), is(true));
		assertThat(uf.connected(1, 2), is(true));
	}
}
