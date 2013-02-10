package util;


import java.util.Arrays;

public class ByteArray {

    public static byte[] hex2byte(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static String byte2hex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static byte[] xor(byte[] as , byte[] bs) {
        int minLength = Math.min(as.length, bs.length);
        byte[] result = new byte[minLength];
        for (int i = 0; i<minLength; i++) {
            result[i] = (byte) (as[i] ^ bs[i]);
        }

        return result;
    }


    public static boolean isLetter(int b) {
        if ((b >= 65 && b < 91) || (b >= 97 && b < 123)) {
            return true;
        }

        int[] legalChars = new int[]{40,41,33,46,44,48,49,50,51,52,53,54,55,56,57,58, 59, 34, 45};

        for (int i : legalChars) {
            if (i==b)
                return true;
        }
        return false;
    }

    public static byte[] inc(byte[] input ) {
        for (int i = input.length -1;i>=0;i--){
            if (input[i]!= Byte.MAX_VALUE) {
                input[i]++;
                return input;
            }

            input[i] =Byte.MIN_VALUE;
        }
        byte[]  result = new byte[input.length+1];
        Arrays.fill(result, Byte.MIN_VALUE);
        result[0] = Byte.MIN_VALUE +1;
        return result;
    }

    public static byte[][] chopBlock(byte[] bytes) {

        int nrOfBlocks = bytes.length %16==0 ? bytes.length/16: bytes.length/16 + 1;
        byte[][] result = new byte[nrOfBlocks][16];
        for (int i = 0 ; i< result.length;i++) {
            System.arraycopy(bytes, i*16, result[i], 0, Math.min(16, bytes.length - i*16));
        }
        return result;
    }

    public static byte[] concat(byte[]... bytes) {
        int i = 0;
        for (byte[] b : bytes) {
            i+= b.length;
        }
        int j = 0;
        byte[] result = new byte[i];
        for (byte[] b: bytes) {
            System.arraycopy(b, 0, result, j, b.length);
            j += b.length;
        }

        return result;
    }



    public static byte[] getPadding(int i) {
        if (i>16||i<0) throw new IllegalArgumentException();

        byte[] result = new byte[16];

        for (int j = 1; j<=i;j++) {
            result[16-j] = (byte)i;
        }
        return result;
    }
}
