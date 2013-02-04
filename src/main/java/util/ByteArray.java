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
            sb.append(String.format("%02X", b));
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


}
