package part2.lecture.week3.maxflow;

import java.util.LinkedList;
import java.util.Queue;

public class FordFulkerson {

  private boolean[] marked;  // ture if s->v path in residual network
  private FlowEdge[] edgeTo; // last edge on s->v path
  private double value;      // value of flow
  
  public FordFulkerson(FlowNetwork G, int s, int t) {
    value = 0.0;
    
    while (hasAugmentingPath(G, s, t)) {
      double bottle = Double.POSITIVE_INFINITY;
      
      // compute bottleneck capacity
      for (int v = t; v != s; v = edgeTo[v].other(v))
        bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
      
      // augment flow
      for (int v = t; v != s; v = edgeTo[v].other(v))
        edgeTo[v].addResidualFlowTo(v, bottle);
      
      value += bottle;
    }
  }

  // find a shortest augmenting path using BFS
  private boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
    edgeTo = new FlowEdge[G.V()];
    marked = new boolean[G.V()];
    
    Queue<Integer> q = new LinkedList<Integer>();
    q.add(s);
    marked[s] = true;
    
    while (!q.isEmpty()) {
      int v = q.remove();
     
      
      for (FlowEdge e : G.adj(v)) {
        // backward edge of v -> w is not empty
        int w = e.other(v);
        
        // path v->w is in the residual network?
        // => forward edge of v->w is not full
        if (e.residualCapacityTo(w) > 0 && !marked[w]) {
          edgeTo[w] = e;
          marked[w] = true;
          q.add(w);
        }
      }
    }
    
    return marked[t];
  }
  
  public double value() { return value; }
  // is `v` reachable from s in residual network?
  public boolean inCut(int v) { return marked[v]; }
  
}
