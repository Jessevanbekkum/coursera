package week3;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ByteArray;

import java.util.Arrays;

/**
 *
 */
public class Exam3 {
    Logger logger = LoggerFactory.getLogger(VideoHashTest.class);


    public byte[] f1(byte[] x, byte[] y) {

        BlockCipher aesEngine = new AESEngine();

        aesEngine.init(true, new KeyParameter(y));

        byte[] encrypt = new byte[16];

        aesEngine.processBlock(x, 0, encrypt, 0);

        return ByteArray.xor(encrypt, y);
    }

    public byte[] f2(byte[] x, byte[] y) {

        BlockCipher aesEngine = new AESEngine();

        aesEngine.init(true, new KeyParameter(x));

        byte[] encrypt = new byte[16];

        aesEngine.processBlock(x, 0, encrypt, 0);

        return ByteArray.xor(encrypt, y);
    }


    @Test
    public void test1() {
        String hex1 = "00000000000000000000000000000000";
        String hex2 = "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";
        byte[] x = ByteArray.hex2byte(hex1);
        byte[] y = ByteArray.hex2byte(hex2);
        logger.info("1: {}", f1(x, y));
        logger.info("2: {}", f1(y, x));
        logger.info("3: {}", f1(x, x));
        logger.info("4: {}", f1(y, y));

    }

    @Test
    public void test2() {
        String hex1 = "00000000000000000000000000000000";
        String hex2 = "88888888888888888888888888888888";
        String hex3 = "1234567890abcdef1234567890abcdef";
        byte[] m1 = ByteArray.hex2byte(hex1);
        byte[] m2 = ByteArray.hex2byte(hex2);
        byte[] h1 = ByteArray.hex2byte(hex3);

        BlockCipher encrypt = new AESEngine();

        encrypt.init(true, new KeyParameter(m1));
        BlockCipher decrypt = new AESEngine();

        decrypt.init(false, new KeyParameter(m2));

        byte[] t1 = new byte[16];
        encrypt.processBlock(h1, 0, t1, 0);
        byte[] t2 = ByteArray.xor(t1, m1);
        byte[] t3 = ByteArray.xor(t2, m2);

        byte[] h2 = new byte[16];

        decrypt.processBlock(t3, 0, h2, 0);
        logger.info("h2: {}", h2);
        logger.info("h2 hex: {}", ByteArray.byte2hex(h2));
        logger.info("f1(m1, h1): {}", f1(h1, m1));
        logger.info("f1(m2, h2): {}", f1(h2, m2));
//        assert Arrays.equals(f1(m1, h1),(f1(m2,h2)));

    }

    @Test
    public void opgave9() {
        String hex1 = "00000000000000000000000000000000";
        String hex2 = "88888888888888888888888888888888";
        String hex3 = "1234567890abcdef1234567890abcdef";
        byte[] x1 = ByteArray.hex2byte(hex1);
        byte[] x2 = ByteArray.hex2byte(hex2);
        byte[] y1 = ByteArray.hex2byte(hex3);

        BlockCipher encrypt = new AESEngine();

        encrypt.init(true, new KeyParameter(x1));
        BlockCipher decrypt = new AESEngine();

        decrypt.init(true, new KeyParameter(x2));

        byte[] t1 = new byte[16];
        encrypt.processBlock(x1, 0, t1, 0);
        byte[] t2 = new byte[16];
        decrypt.processBlock(x2, 0, t2, 0);
        byte[] t3 = ByteArray.xor(t1, t2);

        byte[] y2 = ByteArray.xor(t3, y1);

        logger.info("h2 hex: {}", ByteArray.byte2hex(y2));
        logger.info("f1(m1, h1): {}", f2(x1, y1));
        logger.info("f1(m2, h2): {}", f2(x2, y2));
//        assert Arrays.equals(f1(m1, h1),(f1(m2,h2)));

    }
}
