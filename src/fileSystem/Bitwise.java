package fileSystem;

/**
 * Manage bitwise operations in a byte or array of bytes.
 *
 * Unit tests are in {@see TestBitwise}. See TestBitwise.java.
 */
public class Bitwise {
	
    private static final int bitmasks[] = {1, 2, 4, 8, 16, 32, 64, 128};

    /**
     * Check to see if bit i is set in byte. Returns true if it is
     * set, false otherwise.
     */
    public static boolean isset(int i, byte b) {//FIXME!!!
    	return i >= 0 && i < bitmasks.length && (b & bitmasks[i]) != 0;
    }

    /**
     * Check to see if bit i is set in array of bytes. Returns true if
     * it is set, false otherwise.
     */
    public static boolean isset(int i, byte bytes[]) {//FIXME!!!
    	int offset1 = bytes.length - 1 - i / 8;
    	int offset2 = i % 8;
    	return isset(offset2, bytes[offset1]);
    }

    /**
     * Set bit i in byte and return the new byte.
     */
    public static byte set(int i, byte b) {//FIXME!!!
    	return (i >= 0 && i < bitmasks.length) ? (byte)(b | bitmasks[i]) : 0;
    }

    /**
     * Set bit i in array of bytes.
     */
    public static void set(int i, byte bytes[]) {//FIXME!!!
    	int offset1 = bytes.length - 1 - i / 8;
    	int offset2 = i % 8;
    	bytes[offset1] = set(offset2, bytes[offset1]);
    }

    /**
     * Clear bit i in byte and return the new byte.
     */
    public static byte clear(int i, byte b) {//FIXME!!!
    	return (i >= 0 && i < bitmasks.length) ? (byte)(b & ~bitmasks[i]) : 0;
    }

    /**
     * Clear bit i in array of bytes and return true if the bit was 1
     * before clearing, false otherwise.
     */
    public static boolean clear(int i, byte bytes[]) {//FIXME!!!
    	int offset1 = bytes.length - 1 - i / 8;
    	int offset2 = i % 8;
    	boolean origin = isset(offset2, bytes[offset1]);
    	bytes[offset1] = clear(offset2, bytes[offset1]);
    	return origin;
    }

    /**
     * Clear every bit in array of bytes.
     *
     * There is no clearAll for a single byte, you can just get a new
     * byte for that.
     */
    public static void clearAll(byte bytes[]) {
    	for (int k = 0; k < bytes.length; k++) {
    		bytes[k] = 0;
    	}
    }

    /**
     * Convert byte to a string of bits. Each bit is represented as
     * "0" if it is clear, "1" if it is set.
     */
    public static String toString(byte b) {//FIXME!!!
    	String value = "";
    	for (int i = bitmasks.length - 1; i >= 0; i--) {
    		value += isset(i, b) ? "1" : "0";
    	}
    	return value;
    }

    /**
     * Convert array of bytes to string of bits (each byte converted
     * to a string by calling {@link #byteToString(byte b)}, every
     * byte separated by sep, every "every" bytes separated by lsep.
     */
    public static String toString(byte bytes[], String sep,
                                  String lsep, int every) {
        String s = "";
        for(int i = bytes.length * 8 - 1; i >= 0; --i) {
        	s += isset(i, bytes) ? "1" : "0";
        	if(i > 0)
                if(every > 0 && i % (8 * every) == 0)
                    s += lsep;
                else if(i % 8 == 0)
                    s += sep;
        }
        return s;
    }

    /**
     * Convert array of bytes to string of bits, each byte separated
     * by sep. See {@link #byteToString(byte bytes[], String sep)}.
     */
    public static String toString(byte bytes[], String sep) {
        return toString(bytes, sep, null, 0);
    }

    /**
     * Convert array of bytes to string of bits, each byte separated
     * by a comma, and every 8 bytes separated by a newline. See
     * {@link #byteToString(byte bytes[], String sep)}.
     */
    public static String toString(byte bytes[]) {
        return toString(bytes, ",", "\n", 8);
    }
}