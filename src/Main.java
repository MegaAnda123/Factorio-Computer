import B16.Assembler16;
import B16.CharacterInvalidException;
import B16.ParserException;
import B16.ROM16;
import Deprecated.Deprecated;

public class Main {

    //public static String code = "DISP 4\nT e\ns t\n.  \na 0\nJUMP 0";
    public static String code = "print Long test string with symbols ?! /\\ ■\nJUMP 0";


    public static void main(String[] args) throws ParserException {
        Assembler16 as = new Assembler16();
        ROM16 rom16 = new ROM16();
        String[] codeLn = code.split("\n");




        for (String c : codeLn) {
            rom16.addLines(as.parserLevel1(c));
        }

        System.out.println(rom16.getBluePrint());
    }
}
