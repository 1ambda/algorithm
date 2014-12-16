package part1.lecture.week1.unionfind;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

public class WeightedQuickUnionTest {
	
	WeightedQuickUnionTest spy;

	@Test
	public void testUnion() {
		WeightedQuickUnion spy = Mockito.spy(new WeightedQuickUnion(5));
	
		assertThat(spy.connected(0, 1), is(not(true)));
		// 0 1 -> 1 1
		spy.union(0, 1);
		assertThat(spy.connected(1, 0), is(true));
		assertThat(spy.getTreeSize(0), is(1));
		assertThat(spy.getTreeSize(1), is(2));
		
		// 1 1 2 -> 1 1 1 
		spy.union(0, 2);
		assertThat(spy.connected(0, 1), is(true));
		assertThat(spy.connected(1, 2), is(true));
		assertThat(spy.connected(0, 2), is(true));
		
		assertThat(spy.getRoot(0), is(1));
		assertThat(spy.getRoot(1), is(1));
		assertThat(spy.getRoot(2), is(1));
		
		// 1 1 1 4 4
		spy.union(3, 4);
		assertThat(spy.connected(1, 3), is(false));
		assertThat(spy.connected(4, 3), is(true));
		
		// 1 1 1 4 1
		spy.union(2,  4);
		assertThat(spy.connected(4, 3), is(true));
		assertThat(spy.getRoot(4), is(1));
		assertThat(spy.getRoot(3), is(1));
	}
}