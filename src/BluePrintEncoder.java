import java.util.ArrayList;
import java.util.Base64;
import java.util.zip.*;
import java.io.*;

public class BluePrintEncoder {
    String blueprintName = "unnamed";
    String[] icons = {"signal-N","signal-A","",""};

    public BluePrintEncoder() {}

    public BluePrintEncoder(String blueprintName) {
        this.blueprintName = blueprintName;
    }

    public BluePrintEncoder(String blueprintName, String[] icons) {
        this.blueprintName = blueprintName;
        for (int i=0; i<4; i++) {
            try {
                this.icons[i] = icons[i];
            } catch (ArrayIndexOutOfBoundsException ignore) {
                this.icons[i] = "";
            }
        }
    }


    public String header() {
        String out = Tools.readFromFile("files\\header.txt");
        String icon = Tools.readFromFile("files\\icon.txt");
        String temp = "";

        for(int i=0; i<4; i++) {
            if (!icons[i].equals("")) {
                temp += icon.replaceFirst("@name",icons[i]).replaceFirst("@index",String.valueOf(i+1));
            }
            if (!icons[i].equals("") && !icons[i + 1].equals("")) temp += ",";
        }
        return out.replaceFirst("@icons", temp);
    }

    public String footer() {
        String footer = Tools.readFromFile("files\\footer.txt");
        return footer.replaceFirst("@label",blueprintName);
    }

    public String EncodeBlueprintMeta(String str) {
        return BluePrintEncoder.EncodeBlueprint(header() + str + footer());
    }

    /**
     * Static method encodes json string to Factorio blueprint string using zlib compression and converting to base64.
     * @param str Json string to convert. Requires str to contain blueprint "metadata" (blueprint name, icons, etc).
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
        return "0" + new String(Base64.getEncoder().encode(baos.toByteArray()));
    }
}
