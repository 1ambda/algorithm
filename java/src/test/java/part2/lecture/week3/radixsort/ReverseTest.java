package part2.lecture.week3.radixsort;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.junit.Test;

public class ReverseTest {

	@Test
	public void test() throws IOException {
		String cd= new File(".").getCanonicalPath();
		String pkg = "/src/test/java/part2/lecture/week3/radixsort/";
		String file = "M42643801.txt";
		Path path = Paths.get(cd + pkg + file);
		
		StringBuilder prime = new StringBuilder();
		
		Stream<String> lines = Files.lines(path);
		lines.forEach(s -> prime.append(s));

		// too slow
		// String rev = Strings.reverse1(prime.toString());
	
		// Stringbuiler's concat is more fast.
		// since it doens't make a new string every iteration

		String rev = Strings.reverse2(prime.toString());
		System.out.println(rev.length());
	}
}
