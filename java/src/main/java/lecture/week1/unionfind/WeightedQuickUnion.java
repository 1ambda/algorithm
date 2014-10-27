package lecture.week1.unionfind;

public class WeightedQuickUnion extends QuickUnion {
	
	int treeSize[];

	public WeightedQuickUnion(int N) {
		super(N);
		
		this.treeSize = new int[N];
		
		for(int i = 0; i < this.treeSize.length; i++)  {
			this.treeSize[i] = 1;
		}
	}
	
	// for debug
	public int getTreeSize(int index) {
		return treeSize[index];
	}
	
	// path compression version
	@Override
	public int getRoot(int n) {
		
		int root = id[n];
		
		while(root != id[root]) {
			id[root] = id[id[root]];
			root = id[root];
		}
		
		return root;
	}
	
	@Override
	public void union(int p, int q) {
		if(!connected(p, q)) {
			int qRoot = getRoot(q);
			int pRoot = getRoot(p);
			
			if (treeSize[qRoot] >= treeSize[pRoot]) {
				id[pRoot] = qRoot;
				treeSize[qRoot] += treeSize[pRoot];
			} else {
				id[qRoot] = pRoot;
				treeSize[pRoot] += treeSize[qRoot];
			}
		}
	}
}
