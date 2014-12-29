package part2.lecture.week6.regex;

import java.util.Stack;

public class NFA {
	private char[] re; // match transition
	private Digraph G; // epsilon transition
	private int M;     // # of states
	
	public NFA(String regex) {
		M = regex.length();
		re = regex.toCharArray();
		G = buildEpsilonTransitionDigraph();
	}

	private Digraph buildEpsilonTransitionDigraph() {
		
		Digraph G = new Digraph(M + 1); // including the accept state
		Stack<Integer> ops = new Stack<Integer>();
		
		for (int i = 0; i < M; i++) {
			int lp = i;
			
			// keep '(', '|' to implement closure, OR
			if (re[i] == '(' || re[i] == '|') ops.push(i);
			
			// OR
			else if (re[i] == ')') {
				int or = ops.pop();
				
				if (re[or] == '|') { // if '|' exists
					lp = ops.pop();
					G.addEdge(lp, or + 1);
					G.addEdge(or, i);
				} 
				else lp = or;        // left paren
			}
			
			if (i < M - 1 && re[i + 1] == '*') {
				G.addEdge(lp, i + 1);
				G.addEdge(i + 1, lp);
			}
			
			if (re[i] == '*' || re[i] == '(' || re[i] == ')')
				G.addEdge(i, i + 1);
		}
		
		return G;
	}
	
	public boolean recognizes(String txt) {
		
		Bag<Integer> rs = new Bag<Integer>(); // reachable states
		DirectedDFS dfs = new DirectedDFS(G, 0); 
		
		// get reachable states initially
		for (int v = 0; v < G.V(); v++) 
			if (dfs.marked(v)) rs.add(v);
		
		for (int i = 0; i < txt.length(); i++) {
			
			// match transition
			Bag<Integer> match = new Bag<Integer>();
			for (int v : rs) {
				if (v == M) continue;
				if ((re[v] == txt.charAt(i)) || re[v] == '.')
					match.add(v + 1); // add next state 
			}
			
			// run epsilon transition with match
			dfs = new DirectedDFS(G, match);
			rs = new Bag<Integer>();
			for (int v = 0; v < G.V(); v++)
				if (dfs.marked(v)) rs.add(v);
		}
		
		for (int v : rs) 
			if (v == M) return true;
					
		return false;
	}
}
