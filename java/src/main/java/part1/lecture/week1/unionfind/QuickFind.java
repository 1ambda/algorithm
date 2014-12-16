package part1.lecture.week1.unionfind;

public class QuickFind implements UnionFind {
	
	private int[] id;
	
	public QuickFind(int N) {
		this.id = new int[N];
		
		for(int i = 0; i < this.id.length; i++) {
			this.id[i] = i;
		}
	}

	public void union(int p, int q) {
		if (!connected(p, q)) {
			int pValue = id[p];
			
			// replace id values same as p's into the value of q
			for(int i = 0; i < this.id.length; i++) {
				if (id[i] == pValue) {
					id[i] = id[q];
				}
			}
		}
	}

	public boolean connected(int p, int q) {
		return id[p] == id[q];
	}

}
