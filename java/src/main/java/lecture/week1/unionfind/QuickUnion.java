package lecture.week1.unionfind;

public class QuickUnion implements UnionFind {
	
	protected int[] id;
	
	public QuickUnion(int N) {
		this.id = new int[N];
		
		for(int i = 0; i < N; i++) {
			this.id[i] = i;
		}
	}
	
	public int getRoot(int n) {
		int root = id[n];
		
		while(root != id[root]) {
			root = id[root];
		}
		
		return root;
	}

	public void union(int p, int q) {
		if (!connected(p, q)) {
			int pRoot = this.getRoot(p);
			int qRoot = this.getRoot(q);
			id[pRoot] = qRoot;
		}
	}

	public boolean connected(int p, int q) {
		return getRoot(p) == getRoot(q);
	}
}
