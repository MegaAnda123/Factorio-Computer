import java.util.Base64;
import java.util.zip.*;
import java.io.*;

public class BluePrintEncoder {

    /**
     * Encodes json string to Factorio blueprint string using zlib compression and converting to base64.
     * @param str Json string to convert.
     * @return Base64 blueprint string.
     */
    public static String EncodeBlueprint(String str) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DeflaterOutputStream dos = new DeflaterOutputStream(baos);
        try {
            dos.write(str.getBytes());
            dos.flush();
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Compression failed");
        }
        String out = "0" + new String(Base64.getEncoder().encode(baos.toByteArray()));
        return out;
    }
}
