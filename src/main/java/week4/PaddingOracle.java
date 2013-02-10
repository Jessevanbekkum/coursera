package week4;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ByteArray;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: jesse
 * Date: 2/10/13
 * Time: 2:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class PaddingOracle {
    Logger logger = LoggerFactory.getLogger(PaddingOracle.class);
    final String complete = "f20bdba6ff29eed7b046d1df9fb7000058b1ffb4210a580f748b4ac714c001bd4a61044426fb515dad3f21f18aa577c0bdf302936266926ff37dbf7035d5eeb4";

    public int query(String parameterValue) {
//        logger.info("Query for {} - {} - {} - {}", parameterValue.substring(0,32),
//                parameterValue.substring(32,64),
//                parameterValue.substring(64,96),
//                parameterValue.substring(96,128));
        URL url;
        HttpURLConnection connection = null;
        String urlParameters =
                null;
        try {
            urlParameters = "er=" + URLEncoder.encode(parameterValue, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }

        try {
            //Create connection
            url = new URL("http://crypto-class.appspot.com/po?");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length", "" +
                    Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);


//            Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();


            return connection.getResponseCode();
        } catch (IOException io) {

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }

        return 0;
    }
@Test
    public void doSpam() {
        String c0a = "f20bdba6ff29eed7b046d1df9fb7000058b1ffb4210a580f748b4ac714c001bd4a61044426fb515dad3f21f18aa577";//c0
        String c1 = "bdf302936266926ff37dbf7035d5eeb4";
        byte offset = 1;
        byte g = 0;
        for (int i = 0; i < 256; i++) {
            byte p = (byte) i;

            int result = query(c0a + String.format("%02x", p) + c1);
            //logger.info("byte: {}:{}", i, );

            if (result == 404)
            {    logger.info("Mac error at {}", g);
            break;
            }
            if (result == 403)
                logger.info("Padding error at {}", g);
            g++;
        }
    }

    @Test
    public void startChopping() {
        boolean found = false;
        byte[] endResult = new byte[16];


        final byte[] iv = ByteArray.hex2byte(complete.substring(0, 32));
        final byte[] c0 = ByteArray.hex2byte(complete.substring(32, 64));
        final byte[] c1 = ByteArray.hex2byte(complete.substring(64, 96));
        final byte[] c2 = ByteArray.hex2byte(complete.substring(96, 128));


        for (int j = 15; j >= 0; j--) {
            byte[] myPadding = ByteArray.getPadding(16 - j);
            byte[] g = endResult;
            for (int i = 0; i < 256; i++) {
                byte[] padding = ByteArray.xor(g, myPadding);
                //byte[] c1$ = ByteArray.xor(c1, g);
                byte[] c1$ = ByteArray.xor(c1, padding);
                byte[] c0$ = ByteArray.xor(c0, padding);
                //byte[] iv$ = ByteArray.xor(iv, padding);
                int result = query(ByteArray.byte2hex(ByteArray.concat(iv, c0, c1$, c2)));

                logger.info("Result from {}:{}", g, result);
                if (result == 404) {
                    found = true;
                    logger.info("Mac error at {}", g);
                    break;
                }

//            if (result == 403)
//                logger.info("Padding error at {}", g);

                g[j]++;
            }
            if (!found) {
                logger.info("Could not found a valid g");
                break;
            }
            else {
                found = false;
                endResult[j] = g[j];
            }

        }
        logger.info("Endresult: {}", endResult);
    }
}

