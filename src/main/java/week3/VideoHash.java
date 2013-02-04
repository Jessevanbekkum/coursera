package week3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ByteArray;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 */
public class VideoHash {
    Logger logger = LoggerFactory.getLogger(VideoHash.class);

    public String hash(File video) {
        MessageDigest md = null;
        String hash = "";

        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
        logger.info("Attempting to hash a file of length {}", video.length());
        RandomAccessFile raf;
        try {
            raf = new RandomAccessFile(video, "r");
            int lastBlockLength = (int) raf.length() % 1024;
            long pos = raf.length() - lastBlockLength;

            byte[] lastBlock = new byte[lastBlockLength];
            raf.seek(pos);
            raf.readFully(lastBlock);
            byte[] digest = md.digest(lastBlock);
            pos -= 1024;
            while (pos >= 0) {
                raf.seek(pos);
                byte[] block = new byte[1024 + 32];
                raf.readFully(block, 0, 1024);
                System.arraycopy(digest, 0, block, 1024, 32);

                digest = md.digest(block);

                hash = ByteArray.byte2hex(digest);
                //logger.info("The hash of pos {} is {}",pos, hash);
                pos = pos - 1024;
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return hash.toLowerCase();
    }
}
