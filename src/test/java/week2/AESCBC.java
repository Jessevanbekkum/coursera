package week2;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ByteArray;
import week1.PrintSolutions;

import static util.ByteArray.hex2byte;

/**
 * Created with IntelliJ IDEA.
 * User: jesse
 * Date: 1/25/13
 * Time: 10:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class AESCBC {
    Logger logger = LoggerFactory.getLogger(PrintSolutions.class);

    @Test
    public void cbc1() {
        AESSolver solver = new AESSolver();

       String s = solver.decryptCBC(hex2byte("140b41b22a29beb4061bda66b6747e14"),
                hex2byte("4ca00ff4c898d61e1edbf1800618fb2828a226d160dad07883d04e008a7897ee2e4b7465d5290d0c0e6c6822236e1daafb94ffe0c5da05d9476be028ad7c1d81"));

        logger.info("A: {}", s);
    }
@Test
    public void cbc2() {
        AESSolver solver = new AESSolver();
        String s  = solver.decryptCBC(hex2byte("140b41b22a29beb4061bda66b6747e14"),
        hex2byte("5b68629feb8606f9a6667670b75b38a5b4832d0f26e1ab7da33249de7d4afc48e713ac646ace36e872ad5fb8a512428a6e21364b0c374df45503473c5242a253"));

        logger.info("B: {}", s);
    }

    @Test
    public void ctr1() {
        AESSolver solver = new AESSolver();
        String s = solver.decryptCTR(hex2byte("36f18357be4dbd77f050515c73fcf9f2"),
                hex2byte("69dda8455c7dd4254bf353b773304eec0ec7702330098ce7f7520d1cbbb20fc388d1b0adb5054dbd7370849dbf0b88d393f252e764f1f5f7ad97ef79d59ce29f5f51eeca32eabedd9afa9329"));
        logger.info("C: {}", s);
    }
@Test
    public void ctr2() {
        AESSolver solver = new AESSolver();
        String s = solver.decryptCTR(hex2byte("36f18357be4dbd77f050515c73fcf9f2"),
                hex2byte("770b80259ec33beb2561358a9f2dc617e46218c0a53cbeca695ae45faa8952aa0e311bde9d4e01726d3184c34451"));
        logger.info("D: {}", s);
    }


}
