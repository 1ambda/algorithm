package part1.lecture.week1.unionfind;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import part1.lecture.week1.unionfind.QuickFind;
import part1.lecture.week1.unionfind.QuickUnion;
import part1.lecture.week1.unionfind.UnionFind;

public class QuickUnionTest {
	
	UnionFind uf;

	@Before
	public void setup() {
		uf = new QuickFind(10);
	}
	
	@Test
	public void testGetRoot() {
		QuickUnion spy = Mockito.spy(new QuickUnion(5));
		
		assertThat(spy.getRoot(0), is(0));
		assertThat(spy.getRoot(1), is(1));
		assertThat(spy.getRoot(2), is(2));
		assertThat(spy.getRoot(3), is(3));
		assertThat(spy.getRoot(4), is(4));
		
		spy.union(0, 1);
		assertThat(spy.getRoot(0), is(1));
		assertThat(spy.getRoot(1), is(1));

		spy.union(2, 3);
		spy.union(4, 2);
		assertThat(spy.getRoot(2), is(3));
		assertThat(spy.getRoot(3), is(3));
		assertThat(spy.getRoot(4), is(3));
	}
	
	@Test
	public void testConnected() {
		// initial     : 0 1 2 3 4
		// union(0, 1) : 1 1 2 3 4
		// union(2, 3) : 1 1 3 3 4
		// union(3, 1) : 1 1 3 1 4
		
		//   1   4
		//  0 3
		//    2 
		
		QuickUnion spy = Mockito.spy(new QuickUnion(5)); 
		when(spy.getRoot(1)).thenReturn(1);
		when(spy.getRoot(0)).thenReturn(1);
		when(spy.getRoot(3)).thenReturn(1);
		when(spy.getRoot(2)).thenReturn(1);
		when(spy.getRoot(4)).thenReturn(4);
		
		assertThat(spy.connected(0, 4), is(not(true)));
		assertThat(spy.connected(1, 4), is(not(true)));
		assertThat(spy.connected(2, 4), is(not(true)));
		assertThat(spy.connected(3, 4), is(not(true)));
		
		assertThat(spy.connected(0, 1), is(true));
		assertThat(spy.connected(0, 2), is(true));
		assertThat(spy.connected(0, 3), is(true));
		assertThat(spy.connected(1, 2), is(true));
		assertThat(spy.connected(1, 3), is(true));
		assertThat(spy.connected(2, 3), is(true));
	}
	
	@Test
	public void testUnion() {
		QuickUnion spy = Mockito.spy(new QuickUnion(5));
		when(spy.getRoot(0)).thenReturn(0);
		when(spy.getRoot(1)).thenReturn(1);
		when(spy.getRoot(2)).thenReturn(2);
		when(spy.getRoot(3)).thenReturn(3);
		when(spy.getRoot(4)).thenReturn(4);
		
		spy.union(0, 1);
		when(spy.getRoot(0)).thenReturn(1);
		spy.union(2, 3);
		when(spy.getRoot(2)).thenReturn(3);
		spy.union(2, 0);
		when(spy.getRoot(2)).thenReturn(1);
		when(spy.getRoot(3)).thenReturn(1);
		
		verify(spy, times(1)).connected(0, 1);
		verify(spy, times(1)).connected(2, 3);
		Mockito.reset(spy);
		
		assertThat(spy.connected(0, 1), is(true));
		assertThat(spy.connected(2, 3), is(true));
		assertThat(spy.connected(2, 0), is(true));
		assertThat(spy.connected(1, 3), is(true));
		assertThat(spy.connected(0, 3), is(true));
	}
	
}
