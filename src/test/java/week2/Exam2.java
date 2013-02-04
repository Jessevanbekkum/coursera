package week2;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ByteArray;
import week1.PrintSolutions;

/**
 * Created with IntelliJ IDEA.
 * User: jesse
 * Date: 1/27/13
 * Time: 10:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class Exam2 {
    Logger logger = LoggerFactory.getLogger(PrintSolutions.class);

    @Test
    public void vraag4() {
        String a1 = "9d1a4f78cb28d863";
        String b1 = "75e5e3ea773ec3e6";
        String a2 = "9f970f4e932330e4";
        String b2 = "6068f0b1b645c008";
        String a3 = "7b50baab07640c3d";
        String b3 = "ac343a22cea46d60";
        String a4 = "7c2822ebfdc48bfb";
        String b4 = "325032a9c5e2364b";

        doSomething(a1, b1);
        doSomething(a2, b2);
        doSomething(a3, b3);
        doSomething(a4, b4);
    }

    public void doSomething(String a, String b) {
        byte[] as = ByteArray.xor(ByteArray.hex2byte(a), ByteArray.hex2byte(b));
        StringBuilder sb = new StringBuilder();
        for (byte byt : as) {
            sb.append(Integer.toBinaryString(0x100 + byt).substring(1));
        }

        logger.info("{}", sb.toString());

        Byte assdf = 3;


    }

    @Test
    public void keySearch() {
        //logger.info("blabal: {}", f(new int[]{1, 0, 0, 1, 0}, new int[]{0, 1, 1, 0}));

        int[][] allKeys = getAllKeys();

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                for (int k = 0; k < 16; k++) {
                    for (int l = 0; l < 16; l++) {
                        for (int m = 0; m < 16; m++) {
                            int[][] key = {allKeys[i], allKeys[j],allKeys[k],allKeys[l],allKeys[m]};

                            if (f(key, new int[]{0,1,1,0}).equals("0011") &&
                                    f(key, new int[]{1,0,1,0}).equals("1010") &&
                                    f(key, new int[]{1,1,1,0}).equals("0110")) {
                                logger.info("key: {}", key);
                            }
                        }
                    }
                }
            }
        }
    }


    public int[][] getAllKeys() {
        int[][] a = new int[16][4];
        int i = 0;
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 2; k++) {
                for (int l = 0; l < 2; l++) {
                    for (int m = 0; m < 2; m++) {
                        int[] key = {j,k,l,m};
                        a[i] = key;
                        i++;
                    }
                }
            }
        }
        return a;
    }


    @Test
    public void testGetAllKeys() {
        logger.info("Keys: {}", getAllKeys());
    }
    public String f(int[][] k, int[] x) {
        StringBuilder sb = new StringBuilder(4);
        int[] t = k[0];

        for (int i = 1; i <= 4; i++) {
            if (x[i - 1] == 1) t = xor(t, k[i]);
            //sb.append(t);
        }

        return "" + t[0] + t[1] + t[2] + t[3];
    }


    public int[] xor(int[] a, int[] b) {
        int[] c = new int[4];
        for (int i = 0;i<a.length;i++){
            c[i] = (a[i] + b[i]) %2;
        }
        return c;
    }
}
