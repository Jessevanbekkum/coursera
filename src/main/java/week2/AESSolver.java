package week2;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import util.ByteArray;

/**
 * Created with IntelliJ IDEA.
 * User: jesse
 * Date: 1/25/13
 * Time: 10:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class AESSolver {

    public String decryptCBC(byte[] key, byte[] inputCipher) {
        byte[] iv = new byte[16];
        byte[] cmsg = new byte[inputCipher.length - 16];
        assert cmsg.length % 16 == 0;

        byte[] wholePlainText = new byte[cmsg.length];
        System.arraycopy(inputCipher, 0, iv, 0, 16);
        System.arraycopy(inputCipher, 16, cmsg, 0, cmsg.length);


        BlockCipher aesEngine = new AESEngine();

        aesEngine.init(false, new KeyParameter(key));


        for (int i = 0; i < cmsg.length; i += 16) {
            byte[] block = new byte[16];
            System.arraycopy(cmsg, i, block, 0, 16);
            byte[] decryptBlock = new byte[16];
            aesEngine.processBlock(block, 0, decryptBlock, 0);
            byte[] plainBlock = ByteArray.xor(decryptBlock, iv);
            iv = block;
            System.arraycopy(plainBlock, 0, wholePlainText, i, 16);
        }


        return new String(removePadding(wholePlainText));
    }

    private byte[] removePadding(byte[] wholePlainText) {
        byte padding = wholePlainText[wholePlainText.length-1];
        byte[] wholePlainWithoutPad = new byte[wholePlainText.length - (int) padding];
        System.arraycopy(wholePlainText, 0, wholePlainWithoutPad, 0, wholePlainWithoutPad.length);
        return wholePlainWithoutPad;
    }

    public String decryptCTR(byte[] key, byte[] inputCipher) {


        byte[] wholePlainText = new byte[inputCipher.length -16];

        byte[][] wholeBlock = chopBlock(inputCipher);
        byte[] iv = wholeBlock[0];


        BlockCipher aesEngine = new AESEngine();

        aesEngine.init(true, new KeyParameter(key));

        for (int i = 1;i<wholeBlock.length ; i++) {

            byte[] encIv = new byte[iv.length];
            aesEngine.processBlock(iv, 0, encIv, 0);
            byte[] result = ByteArray.xor(encIv, wholeBlock[i]);

            System.arraycopy(result, 0, wholePlainText, (i-1)*16, Math.min(result.length, wholePlainText.length - (i-1)*16));
            iv = ByteArray.inc(iv);
        }

        return new String(wholePlainText);

    }


    public byte[][] chopBlock(byte[] bytes) {

        int nrOfBlocks = bytes.length %16==0 ? bytes.length/16: bytes.length/16 + 1;
        byte[][] result = new byte[nrOfBlocks][16];
        for (int i = 0 ; i< result.length;i++) {
            System.arraycopy(bytes, i*16, result[i], 0, Math.min(16, bytes.length - i*16));
        }
        return result;
    }
}
