package week3;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ByteArray;

/**
 *
 */
public class Exam3 {
    Logger logger = LoggerFactory.getLogger(VideoHashTest.class);


    public byte[] f1(byte[] x, byte[] y) {

        BlockCipher aesEngine = new AESEngine();

        aesEngine.init(false, new KeyParameter(y));

        byte[] encrypt = new byte[16];

        aesEngine.processBlock(x, 0, encrypt, 0);
        return encrypt;
    }

    @Test
    public void test1() {
        String hex1 = "00000000000000000000000000000000";
        String hex2 = "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";
        byte[] x = ByteArray.hex2byte(hex1);
        byte[] y = ByteArray.hex2byte(hex2);
        logger.info("1: {}", f1(x,y));
        logger.info("2: {}", f1(y,x));
        logger.info("3: {}", f1(x,x));
        logger.info("4: {}", f1(y,y));

    }
}
