import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ByteArray;
import week1.Ciphertexts;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: jesse
 * Date: 1/20/13
 * Time: 4:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class ByteArrayTest {
    Logger logger = LoggerFactory.getLogger(ByteArrayTest.class);
    Ciphertexts ciphertexts = new Ciphertexts();
    @Test
    public void MyTest() {

        Ciphertexts ciphertexts = new Ciphertexts();
        for (String s : ciphertexts.texts) {
            logger.info("Hex: {}", new String(ByteArray.hex2byte(s)));
        }

    }

    @Test
    public void xorTest() {

        String s1 = ciphertexts.texts.get(0);

        byte[] as = ByteArray.hex2byte(s1);

        logger.info("--------{}: {}", 0, as);
        for (int i = 1;i<ciphertexts.texts.size();i++) {
            byte[] bs = ByteArray.hex2byte(ciphertexts.texts.get(i));
            logger.info("Xor 0 & {}: {}",i, ByteArray.xor(as, bs));

        }
    }

    @Test
    public void printStuff() {
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String space = " ";
        for (byte c : lower.getBytes()) {
            logger.info("{}: {}", (char) c, ByteArray.xor(new byte[]{c}, space.getBytes()));
        }
        for (byte c : upper.getBytes()) {
            logger.info("{}: {}", (char) c, ByteArray.xor(new byte[]{c}, space.getBytes()));
        }
    }

    @Test
    public void printHello() {
        logger.info("{}", ByteArray.xor("hello".getBytes(), "WORLD".getBytes()));
    }

    @Test
    public void printAll() {
        logger.info("Start");
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (byte c : lower.getBytes()) {
            for (byte d : upper.getBytes()) {
                int b = c ^ d;
                //if () {
                    logger.info("c={}, d={}", c, d);
                //}
            }
        }
        logger.info("Finish");
    }

    @Test
    public void printSpace() {
        logger.info("{}", ByteArray.xor(" ".getBytes(), " ".getBytes()));
    }

    @Test
    public void printXors() {

    }
    @Test
    public void printSpaceAndNogiets() {
        byte space = ' ';
        byte eight = (byte) 8;
        logger.info("Space: {}", space);
        logger.info("Xor of space and 8: {}", space ^ eight );
    }

    @Test
    public void toTheCode() {
        byte[] crypt1 = ByteArray.hex2byte(ciphertexts.texts.get(5).substring(0,72));
        byte[] plain1 = "There are two types of cryptography ".getBytes();

        byte[] key = ByteArray.xor(crypt1, plain1);

        for (String s : ciphertexts.texts) {
            byte[] crypt = ByteArray.hex2byte(s);
            byte[] plain = ByteArray.xor(crypt,key);
            logger.info("And the solution is: {}", new String(plain));
        }

        byte[] sol = ByteArray.hex2byte("32510ba9babebbbefd001547a810e67149caee11d945cd7fc81a05e9f85aac650e9052ba6a8cd8257bf14d13e6f0a803b54fde9e77472dbff89d71b57bddef121336cb85ccb8f3315f4b52e301d16e9f52f904".substring(0,72));
        byte[] plainsol = ByteArray.xor(sol, key);

        logger.info("blabla: {}", new String(plainsol));



    }

    public void identity(String s) {
        assert s.equalsIgnoreCase(ByteArray.byte2hex(ByteArray.hex2byte(s)));
    }

    @Test
    public void testIdentity() {
        identity("aa");
        identity("abebbbefd001547a810e67149caee11d945cd7fc81a0");
    }

    @Test
    public void testInc() {
        byte[] b = new byte[1];
        b[0] = 14;
        byte[] c =  ByteArray.inc(b);
        assertThat((int) c[0], is(15));


    }

    @Test
    public void testIncCarry() {
        byte[] b = new byte[1];
        b[0] = Byte.MAX_VALUE;
        byte[] c =  ByteArray.inc(b);
        assertThat((int) c[0], is(Byte.MIN_VALUE +1));
        assertThat((int) c[1], is(Byte.MIN_VALUE + 0));
    }

}
