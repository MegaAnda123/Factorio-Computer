import B16.Assembler16;
import B16.ParserException;
import B16.ROM16;
import Tools.Tools;

public class Main {

    public static boolean setClipboard = true;
    //public static String code = "DISP 4\nT e\ns t\n.  \na 0\nJUMP 0";
    public static String code = "print Long test string with symbols ?! /\\ ■\nJUMP 0";


    public static void main(String[] args) throws ParserException {
        Assembler16 as = new Assembler16();
        ROM16 rom16 = new ROM16();

        String temp = Tools.readFromFile("src/B16/codeExamples/Prime Numbers bruteforce.txt");
        if(!temp.equals("")) {
            code = temp;
        }

        System.out.println(code);


        String[] codeLn = code.split("\n");




        for (String c : codeLn) {
            rom16.addLines(as.parserLevel1(c));
        }

        System.out.println(rom16.getBluePrint());

        if(setClipboard) {
            Tools.setClipBoard(rom16.getBluePrint());
        }


    }
}
