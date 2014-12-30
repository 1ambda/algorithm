package part2.lecture.week6.compression;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;

public class RunLengthTest {
  
  @Test
  public void test() {
  
    // 00000000 00000001 11111111
    String data = "Hello World!";
    
    // SO: junit-how-to-simulate-system-in-testing
    InputStream stdin = System.in;
    
    try {
      System.setIn(new ByteArrayInputStream(data.getBytes()));
      RunLength.compress();
    } finally {
      System.setIn(stdin);
    }
  }
}
