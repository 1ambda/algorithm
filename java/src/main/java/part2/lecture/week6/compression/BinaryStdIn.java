package part2.lecture.week6.compression;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public final class BinaryStdIn {
    private static BufferedInputStream in = new BufferedInputStream(System.in);
    private static final int EOF = -1;    // end of file

    private static int buffer;            // one character buffer
    private static int N;                 // number of bits left in buffer

    // static initializer
    static { fillBuffer(); }

    // don't instantiate
    private BinaryStdIn() { }

    private static void fillBuffer() {
        try { buffer = in.read(); N = 8; }
        catch (IOException e) { System.out.println("EOF"); buffer = EOF; N = -1; }
    }

    public static void close() {
        try {
            in.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not close BinaryStdIn");
        }
    }

    public static boolean isEmpty() {
        return buffer == EOF;
    }

    public static boolean readBoolean() {
        if (isEmpty()) throw new RuntimeException("Reading from empty input stream");
        N--;
        boolean bit = ((buffer >> N) & 1) == 1;
        if (N == 0) fillBuffer();
        return bit;
    }

    public static char readChar() {
        if (isEmpty()) throw new RuntimeException("Reading from empty input stream");

        // special case when aligned byte
        if (N == 8) {
            int x = buffer;
            fillBuffer();
            return (char) (x & 0xff);
        }

        // combine last N bits of current buffer with first 8-N bits of new buffer
        int x = buffer;
        x <<= (8-N);
        int oldN = N;
        fillBuffer();
        if (isEmpty()) throw new RuntimeException("Reading from empty input stream");
        N = oldN;
        x |= (buffer >>> N);
        return (char) (x & 0xff);
        // the above code doesn't quite work for the last character if N = 8
        // because buffer will be -1
    }

    public static char readChar(int r) {
        if (r < 1 || r > 16) throw new IllegalArgumentException("Illegal value of r = " + r);

        // optimize r = 8 case
        if (r == 8) return readChar();

        char x = 0;
        for (int i = 0; i < r; i++) {
            x <<= 1;
            boolean bit = readBoolean();
            if (bit) x |= 1;
        }
        return x;
    }

    public static String readString() {
        if (isEmpty()) throw new RuntimeException("Reading from empty input stream");

        StringBuilder sb = new StringBuilder();
        while (!isEmpty()) {
            char c = readChar();
            sb.append(c);
        }
        return sb.toString();
    }

    public static short readShort() {
        short x = 0;
        for (int i = 0; i < 2; i++) {
            char c = readChar();
            x <<= 8;
            x |= c;
        }
        return x;
    }

    public static int readInt() {
        int x = 0;
        for (int i = 0; i < 4; i++) {
            char c = readChar();
            x <<= 8;
            x |= c;
        }
        return x;
    }

    public static int readInt(int r) {
        if (r < 1 || r > 32) throw new IllegalArgumentException("Illegal value of r = " + r);

        // optimize r = 32 case
        if (r == 32) return readInt();

        int x = 0;
        for (int i = 0; i < r; i++) {
            x <<= 1;
            boolean bit = readBoolean();
            if (bit) x |= 1;
        }
        return x;
    }

    public static long readLong() {
        long x = 0;
        for (int i = 0; i < 8; i++) {
            char c = readChar();
            x <<= 8;
            x |= c;
        }
        return x;
    }

    public static double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    public static float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    public static byte readByte() {
        char c = readChar();
        byte x = (byte) (c & 0xff);
        return x;
    }
    
}
