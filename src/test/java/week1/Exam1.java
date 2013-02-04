package week1;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ByteArray;

/**
 * Created with IntelliJ IDEA.
 * User: jesse
 * Date: 1/21/13
 * Time: 8:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class Exam1 {
    Logger logger = LoggerFactory.getLogger(PrintSolutions.class);

    @Test
    public void doMyHomework() {
        byte[] cipher = ByteArray.hex2byte("09e1c5f70a65ac519458e7e53f36");
        byte[] plain = "attack at dawn".getBytes();
        byte[] key = ByteArray.xor(cipher, plain);
        byte[] cipher2 = ByteArray.xor(key, "attack at dusk".getBytes());

        logger.info("Solution: {}", ByteArray.byte2hex(cipher2).toLowerCase());
    }
}
