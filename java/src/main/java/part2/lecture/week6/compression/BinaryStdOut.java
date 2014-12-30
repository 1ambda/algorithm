package part2.lecture.week6.compression;

import java.io.BufferedOutputStream;
import java.io.IOException;

/**
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public final class BinaryStdOut {
    private static BufferedOutputStream out = new BufferedOutputStream(System.out);

    private static int buffer;     // 8-bit buffer of bits to write out
    private static int N;          // number of bits remaining in buffer

    // don't instantiate
    private BinaryStdOut() { }

    private static void writeBit(boolean bit) {
        // add bit to buffer
        buffer <<= 1;
        if (bit) buffer |= 1;

        // if buffer is full (8 bits), write out as a single byte
        N++;
        if (N == 8) clearBuffer();
    } 

    private static void writeByte(int x) {
        assert x >= 0 && x < 256;

        // optimized if byte-aligned
        if (N == 0) {
            try { out.write(x); }
            catch (IOException e) { e.printStackTrace(); }
            return;
        }

        // otherwise write one bit at a time
        for (int i = 0; i < 8; i++) {
            boolean bit = ((x >>> (8 - i - 1)) & 1) == 1;
            writeBit(bit);
        }
    }

    // write out any remaining bits in buffer to standard output, padding with 0s
    private static void clearBuffer() {
        if (N == 0) return;
        if (N > 0) buffer <<= (8 - N);
        try { out.write(buffer); }
        catch (IOException e) { e.printStackTrace(); }
        N = 0;
        buffer = 0;
    }

    public static void flush() {
        clearBuffer();
        try { out.flush(); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public static void close() {
        flush();
        try { out.close(); }
        catch (IOException e) { e.printStackTrace(); }
    }


    public static void write(boolean x) {
        writeBit(x);
    } 

    public static void write(byte x) {
        writeByte(x & 0xff);
    }

    public static void write(int x) {
        writeByte((x >>> 24) & 0xff);
        writeByte((x >>> 16) & 0xff);
        writeByte((x >>>  8) & 0xff);
        writeByte((x >>>  0) & 0xff);
    }

    public static void write(int x, int r) {
        if (r == 32) { write(x); return; }
        if (r < 1 || r > 32)        throw new IllegalArgumentException("Illegal value for r = " + r);
        if (x < 0 || x >= (1 << r)) throw new IllegalArgumentException("Illegal " + r + "-bit char = " + x);
        for (int i = 0; i < r; i++) {
            boolean bit = ((x >>> (r - i - 1)) & 1) == 1;
            writeBit(bit);
        }
    }

    public static void write(double x) {
        write(Double.doubleToRawLongBits(x));
    }

    public static void write(long x) {
        writeByte((int) ((x >>> 56) & 0xff));
        writeByte((int) ((x >>> 48) & 0xff));
        writeByte((int) ((x >>> 40) & 0xff));
        writeByte((int) ((x >>> 32) & 0xff));
        writeByte((int) ((x >>> 24) & 0xff));
        writeByte((int) ((x >>> 16) & 0xff));
        writeByte((int) ((x >>>  8) & 0xff));
        writeByte((int) ((x >>>  0) & 0xff));
    }

    public static void write(float x) {
        write(Float.floatToRawIntBits(x));
    }

    public static void write(short x) {
        writeByte((x >>>  8) & 0xff);
        writeByte((x >>>  0) & 0xff);
    }

    public static void write(char x) {
        if (x < 0 || x >= 256) throw new IllegalArgumentException("Illegal 8-bit char = " + x);
        writeByte(x);
    }

    public static void write(char x, int r) {
        if (r == 8) { write(x); return; }
        if (r < 1 || r > 16)        throw new IllegalArgumentException("Illegal value for r = " + r);
        if (x < 0 || x >= (1 << r)) throw new IllegalArgumentException("Illegal " + r + "-bit char = " + x);
        for (int i = 0; i < r; i++) {
            boolean bit = ((x >>> (r - i - 1)) & 1) == 1;
            writeBit(bit);
        }
    }

    public static void write(String s) {
        for (int i = 0; i < s.length(); i++)
            write(s.charAt(i));
    }

    public static void write(String s, int r) {
        for (int i = 0; i < s.length(); i++)
            write(s.charAt(i), r);
    }
}