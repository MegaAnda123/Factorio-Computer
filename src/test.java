import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class test {

    public static void main(String[] args) throws FileNotFoundException, InstructionInvalidException {
        new test();
    }
    public test() {
        ROM16 rom = new ROM16();

        rom.addLine(0,69);
        rom.addLine(0xff,700);


        System.out.println(rom.getJson());

        BluePrintEncoder encoder = new BluePrintEncoder();
        Tools.setClipBoard(encoder.EncodeBlueprintMeta(rom.getJson()));

        System.out.println(encoder.EncodeBlueprintMeta(rom.getJson()));


    }

}
