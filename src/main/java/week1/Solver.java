package week1;

import util.ByteArray;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: jesse
 * Date: 1/20/13
 * Time: 5:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class Solver {

    public final List<String> cipherTexts;

    public final Map<byte[], char[]> solutions = new LinkedHashMap<byte[], char[]>();

    public  byte [] theKey;
    public Solver(List<String> cipherTexts) {
        this.cipherTexts = cipherTexts;

        for (String s : cipherTexts) {
            byte[] bytes = ByteArray.hex2byte(s);
            char[] chars = new char[bytes.length];

            for (int i = 0; i < chars.length; i++) {
                chars[i] = '.';
            }
            solutions.put(bytes, chars);
        }
    }

    public void solve1() {
        for (byte[] bytes : solutions.keySet()) {
            boolean[] spaces = new boolean[bytes.length];
            Arrays.fill(spaces, true);

            for (byte[] aBytes : solutions.keySet()) {
                if (bytes == aBytes) {
                    continue;
                }
                byte[] xorBytes = ByteArray.xor(bytes, aBytes);
                for (int i = 0;i<xorBytes.length;i++) {
                    byte compareByte = xorBytes[i];
                    spaces[i] &= (ByteArray.isLetter(compareByte) || compareByte == 0);
                }
            }

            for (int i = 0;i<93;i++) {
                if (spaces[i]) {
                    solutions.get(bytes)[i] = ' ';
                }
            }
        }
    }

    public void solve2() {
        byte space = ' ';
        for (byte[] bytes: solutions.keySet()) {
            for (byte[] aBytes : solutions.keySet()) {
                if (bytes == aBytes) {
                    continue;
                }
                byte[] xorbytes = ByteArray.xor(bytes, aBytes);
                for (int i = 0; i<xorbytes.length;i++) {
                    if (i >= aBytes.length) {
                        break;
                    }
                    if (solutions.get(aBytes)[i] == ' ') {
                        byte thisByte = xorbytes[i];

                        solutions.get(bytes)[i] = (char) (thisByte ^ space);
                    }
                }
            }
        }
    }

    public void solve3() {
        List<KnownPart> list = new ArrayList<KnownPart>();
//        list.add(new KnownPart("There ", 0, 6));
//        list.add(new KnownPart("We can factor the number 15 with qua", 0 ,0));
//
//        list.add(new KnownPart("quantum computers", 33, 0 ));
//        list.add(new KnownPart("specializes", 55, 4));
//
//        list.add(new KnownPart("becomes", 48, 1));
//        list.add(new KnownPart("sister", 89, 5));
//        list.add(new KnownPart("little",82, 5 ));
//        list.add(new KnownPart("force", 80, 6));

        list.add(new KnownPart("The nice thing about Keeyloq is now we cryptographers can drive a lot of fancy cars - Dan Boneh", 0, 2));
        for (KnownPart kp : list) {
            byte[] bytes = kp.part.getBytes();
            int startPos = kp.index * 2;
            byte[] cipher = ByteArray.hex2byte(cipherTexts.get(kp.textNumber).substring(startPos, startPos + kp.part.length()*2));
            assert bytes.length == cipher.length;

            byte[] key = ByteArray.xor(bytes,cipher);
            theKey = key;
            for (Map.Entry<byte[], char[]> entry : solutions.entrySet()) {
                byte[] toBeDecrypted = new byte[key.length];
                System.arraycopy(entry.getKey(), kp.index, toBeDecrypted, 0, key.length);

                byte[] plain = ByteArray.xor(toBeDecrypted, key);
                char[] plaintext = byteToCharArray(plain);
                for (int i = 0; i < plain.length;i++) {
                    entry.getValue()[kp.index + i] =  plaintext[i];
                }
            }

        }
    }

    private char[] byteToCharArray(byte[] plain) {
        char[] plaintext = new char[plain.length];
        for (int i = 0; i< plain.length;i++) {
            plaintext[i] = (char) plain[i];
        }
        return plaintext;
    }

    public String solve4() {
        String target = "32510ba9babebbbefd001547a810e67149caee11d945cd7fc81a05e9f85aac650e9052ba6a8cd8257bf14d13e6f0a803b54fde9e77472dbff89d71b57bddef121336cb85ccb8f3315f4b52e301d16e9f52f904";
        byte[] targetBytes = ByteArray.hex2byte(target);
        byte[] plainBytes = ByteArray.xor(targetBytes, theKey);
        char[] plainText = byteToCharArray(plainBytes);
        return new String(plainText);
    }
}
