package week3;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;

/**
 *
 */
public class VideoHashTest {
Logger logger = LoggerFactory.getLogger(VideoHashTest.class);
    VideoHash videoHash = new VideoHash();

    @Test
    public void doVideo1() {
        URL uri = this.getClass().getResource("/video1.mp4");
        uri.getFile();
        File f = new File(uri.getFile());

        String hash = videoHash.hash(f);
     logger.info("Hash: {}", hash);
    }

    @Test
    public void doVideo2() {
        URL uri = this.getClass().getResource("/video2.mp4");
        uri.getFile();
        File f = new File(uri.getFile());

        String hash = videoHash.hash(f);
        logger.info("Hash: {}", hash);
    }
}
