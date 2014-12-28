package part2.lecture.week3.maxflow;

public class FlowEdge {
  
  private final int v, w;
  private final double capacity;
  private double flow;
  
  public FlowEdge(int v, int w, double capacity) {
    this.v = v; 
    this.w = w;
    this.capacity = capacity;
  }
  
  public int from() { return v; }
  public int to() { return w; }
  public double capacity() { return capacity; }
  public double flow() { return flow; }
  
  // other endpoint
  int other(int vertex) {
    if (vertex == v) return w;
    else if (vertex == w) return v;
    else throw new RuntimeException("Illegal endpoint");
  }
  
  // residual capacity toward v
  double residualCapacityTo(int vertex) {
    if (vertex == v)      return flow;            // backward edge
    else if (vertex == w) return capacity - flow; // forward edge
    else throw new IllegalArgumentException();
  }
  
  // add delta flow toward v
  void addResidualFlowTo(int vertex, double delta) {
    if (vertex == v)      flow -= delta; // backward edge
    else if (vertex == w) flow += delta; // forward edge
    else throw new IllegalArgumentException();
  }
  
}
