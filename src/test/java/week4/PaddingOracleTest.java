package week4;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ByteArray;

import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: jesse
 * Date: 2/10/13
 * Time: 2:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class PaddingOracleTest {
    Logger logger = LoggerFactory.getLogger(PaddingOracleTest.class);

    @Test
    public void testQuery() throws UnsupportedEncodingException {
        PaddingOracle oracle = new PaddingOracle();

        byte padding = 0;

       logger.info("0:{}",  oracle.query("f20bdba6ff29eed7b046d1df9fb7000058b1ffb4210a580f748b4ac714c001bd4a61044426fb515dad3f21f18aa5750bbdf302936266926ff37dbf7035d5eeb4"));
       logger.info("1:{}",  oracle.query("f20bdba6ff29eed7b046d1df9fb7000058b1ffb4210a580f748b4ac714c001bd4a61044426fb515dad3f21f18aa5740bbdf302936266926ff37dbf7035d5eeb4"));
       logger.info("2:{}",  oracle.query("f20bdba6ff29eed7b046d1df9fb7000058b1ffb4210a580f748b4ac714c001bd4a61044426fb515dad3f21f18aa5770bbdf302936266926ff37dbf7035d5eeb4"));
       logger.info("3:{}",  oracle.query("f20bdba6ff29eed7b046d1df9fb7000058b1ffb4210a580f748b4ac714c001bd4a61044426fb515dad3f21f18aa5760bbdf302936266926ff37dbf7035d5eeb4"));
       logger.info("4:{}",  oracle.query("f20bdba6ff29eed7b046d1df9fb7000058b1ffb4210a580f748b4ac714c001bd4a61044426fb515dad3f21f18aa5710bbdf302936266926ff37dbf7035d5eeb4"));
       logger.info("5:{}",  oracle.query("f20bdba6ff29eed7b046d1df9fb7000058b1ffb4210a580f748b4ac714c001bd4a61044426fb515dad3f21f18aa5700bbdf302936266926ff37dbf7035d5eeb4"));
       logger.info("6:{}",  oracle.query("f20bdba6ff29eed7b046d1df9fb7000058b1ffb4210a580f748b4ac714c001bd4a61044426fb515dad3f21f18aa5730bbdf302936266926ff37dbf7035d5eeb4"));
       logger.info("7:{}",  oracle.query("f20bdba6ff29eed7b046d1df9fb7000058b1ffb4210a580f748b4ac714c001bd4a61044426fb515dad3f21f18aa5720bbdf302936266926ff37dbf7035d5eeb4"));
       logger.info("8:{}",  oracle.query("f20bdba6ff29eed7b046d1df9fb7000058b1ffb4210a580f748b4ac714c001bd4a61044426fb515dad3f21f18aa57d0bbdf302936266926ff37dbf7035d5eeb4"));
       logger.info("9:{}",  oracle.query("f20bdba6ff29eed7b046d1df9fb7000058b1ffb4210a580f748b4ac714c001bd4a61044426fb515dad3f21f18aa57c0bbdf302936266926ff37dbf7035d5eeb4"));
    }
    @Test
    public void testPadding() {
        byte p = 0;
        for(int i= 0;i<256;i++) {

            logger.info("byte: {}:{}", i, String.format("%02x", p));
            p++;
        }

    }

    @Test
    public void chop() {
        byte[] param = ByteArray.hex2byte("f20bdba6ff29eed7b046d1df9fb7000058b1ffb4210a580f748b4ac714c001bd4a61044426fb515dad3f21f18aa577c0bdf302936266926ff37dbf7035d5eeb4");
        byte[][] block = ByteArray.chopBlock(param);
        logger.info("Block: {}", block);
    }
    @Test
    public void testStrings() {
        PaddingOracle oracle = new PaddingOracle();
        oracle.query("f20bdba6ff29eed7b046d1df9fb7000058b1ffb4210a580f748b4ac714c001bd4a61044426fb515dad3f21f18aa5700bbdf302936266926ff37dbf7035d5eeb4");
    }

    @Test
    public void translate1() {
        String s = "84, 104, 101, 32, 77, 97, 103, 105, 99, 32, 87, 111, 114, 100, 115, 32, 97, 114, 101, 32, 83, 113, 117, 101, 97, 109, 105, 115, 104, 32, 79, 115, 115, 105, 102, 114, 97, 103, 101";
        String s2 = "97, 114, 101, 32, 83, 113, 117, 101, 97, 109, 105, 115, 104, 32, 79, 115";
        String[] bla=s.split(",");
        byte[] result = new byte[bla.length];
        for (int i = 0;i<bla.length;i++) {
            result[i] = Byte.valueOf(bla[i].trim());
        }
        logger.info("En: {}", new String(result));
    }
}
