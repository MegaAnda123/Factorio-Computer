import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;

public class Tools {

    public static void writeToFile(String string) {
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("blueprint.txt"), "utf-8"));
            writer.write(string);
            ((BufferedWriter) writer).newLine();
        } catch (IOException ex) {
        } finally {
            try {writer.close();} catch (Exception ex) {/*ignore*/}
        }
    }

    public static String readFromFile(String file) {
        StringBuilder out = new StringBuilder();

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            for(String line; (line = br.readLine()) != null; ) {
                out.append(line).append("\n");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return out.toString();
    }

    public static void setClipBoard(String str) {
        StringSelection selection = new StringSelection(str);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }

}
