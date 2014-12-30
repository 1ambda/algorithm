package part2.lecture.week6.compression;

public class RunLength {

  private final static int R = 256; // max run-length count
  private final static int lgR = 8; // # of bits per count
  
  public static void compress() {
    int run = 0;
    boolean old = false;
    
    while (!BinaryStdIn.isEmpty()) {
      boolean current = BinaryStdIn.readBoolean();
      
      // alternate bit
      if (current != old) {
        BinaryStdOut.write(run, lgR);
        run = 1;
        old = !old;
      }
      // same bit
      else {
        // max count
        if (run == R - 1) {
          BinaryStdOut.write(run, lgR);
          // print dummy alternate bit whose length is 0
          run = 0;
          BinaryStdOut.write(run, lgR); 
        }
        
        run++;
      }
    }
    
    BinaryStdOut.write(run, lgR);
    BinaryStdOut.close();
  }
  
  public static void expand() {
    boolean bit = false;
    
    while (!BinaryStdIn.isEmpty()) {
      int run = BinaryStdIn.readInt(lgR); // read lgR bit
      
      for (int i = 0; i < run; i ++)
        BinaryStdOut.write(bit);
      
      bit = !bit;
    }
    
    BinaryStdOut.close();
  }
}
