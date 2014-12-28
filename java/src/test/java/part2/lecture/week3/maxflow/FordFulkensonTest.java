package part2.lecture.week3.maxflow;

import org.junit.Test;

public class FordFulkensonTest {

  @Test
  public void test() {
    int E = 5;
    
    int s = 0;
    int t = 3;
   
    // http://en.wikipedia.org/wiki/Max-flow_min-cut_theorem#Example
    FlowEdge[] edges = new FlowEdge[E];
    edges[0] = new FlowEdge(s, 1, 4.0);
    edges[1] = new FlowEdge(s, 2, 3.0);
    edges[2] = new FlowEdge(1, 2, 3.0);
    edges[3] = new FlowEdge(1, t, 4.0);
    edges[4] = new FlowEdge(2, t, 5.0);
   
    int V = 4;
    FlowNetwork G = new FlowNetwork(V);
    
    for (FlowEdge e : edges)
      G.addEdge(e);
     
    FordFulkerson FF = new FordFulkerson(G, s, t);
    
    assert(FF.value() == 7);
  }

}
