import java.io.*;
import java.util.ArrayList;

/*
OP codes:
0000 LOAD_CARD
0001 LOAD_A
0010 LOAD_B
0011 LOAD_C
0100 LOAD_D
0101 STORE_A
0110 STORE_B
0111 STORE_C
1000 STORE_D
1001 ADD
1010 SUB
1011 INC
1100 JUMP
1101 JUMP_NEG
1110 JUMP_ZERO
1111 DEC
*/

public class Program {

    ArrayList<String> PrintList = new ArrayList<String>();
    int entityNumber = 1;

    public void intPrintList() {
        addString(convertToOneString(readFromFile("fileheader.txt")));
    }

    public void finPrintList() {
        addString(convertToOneString(readFromFile("filefooter.txt")));
    }

    public void start() {
        intPrintList();
        code(0,"LOAD_A",15);
        code(1,"INC",0);
        code(2,"STORE_A",15);
        code(3,"JUMP",0);

        finPrintList();

        writeToFile(convertToOneString(PrintList));
    }

    public void code(int line, String opCode, int address) {
        boolean[] res = combineArrays(functionToOpCode(opCode),intToBinary(address));
        addCodeLine(line,res);
    }

    public boolean[] intToBinary(int number) {
        final boolean[] ret = new boolean[4];
        for (int i = 0; i < 4; i++) {
            ret[4 - 1 - i] = (1 << i & number) != 0;
        }
        return ret;
    }

    public boolean[] combineArrays(boolean[] opCode, boolean[] address) {
        boolean[] out = {false,false,false,false,false,false,false,false};
        for(int i=0; i<4; i++) {
            out[i] = opCode[i];
        }
        for(int i=0; i<4; i++) {
            out[i+4] = address[i];
        }
        return out;
    }

    public boolean[] functionToOpCode(String functionName) {
        boolean[] out = {false,false,false,false};
        boolean[] LOAD_CARD = {false,false,false,false};
        boolean[] LOAD_A = {false,false,false,true};
        boolean[] LOAD_B = {false,false,true,false};
        boolean[] LOAD_C = {false,false,true,true};
        boolean[] LOAD_D = {false,true,false,false};
        boolean[] STORE_A = {false,true,false,true};
        boolean[] STORE_B = {false,true,true,false};
        boolean[] STORE_C = {false,true,true,true};
        boolean[] STORE_D = {true,false,false,false};
        boolean[] ADD = {true,false,false,true};
        boolean[] SUB = {true,false,true,false};
        boolean[] INC = {true,false,true,true};
        boolean[] JUMP = {true,true,false,false};
        boolean[] JUMP_NEG = {true,true,false,true};
        boolean[] JUMP_ZERO = {true,true,true,false};
        boolean[] DEC = {true,true,true,true};

        switch (functionName) {
            case "LOAD_CARD":
                out = LOAD_CARD;
                break;
            case "LOAD_A":
                out = LOAD_A;
                break;
            case "LOAD_B":
                out = LOAD_B;
                break;
            case "LOAD_C":
                out = LOAD_C;
                break;
            case "LOAD_D":
                out = LOAD_D;
                break;
            case "STORE_A":
                out = STORE_A;
                break;
            case "STORE_B":
                out = STORE_B;
                break;
            case "STORE_C":
                out = STORE_C;
                break;
            case "STORE_D":
                out = STORE_D;
                break;
            case "ADD":
                out = ADD;
                break;
            case "SUB":
                out = SUB;
                break;
            case "INC":
                out = INC;
                break;
            case "JUMP":
                out = JUMP;
                break;
            case "JUMP_NEG":
                out = JUMP_NEG;
                break;
            case "JUMP_ZERO":
                out = JUMP_ZERO;
                break;
            case "DEC":
                out = DEC;
                break;
        }
        return out;
    }

    public void addCodeLine(int line, boolean[] code) {
        for(int i=0; i < 8; i++) {
            addCombinator(i,(line*2),code[i]);
        }
    }

    public void addCombinator(int x, int y, boolean bool) {
        int signalNumber = (7 - x);

        if (entityNumber != 1) {
            addStringln(",");
        }
        addStringln("{");
        addStringln("\"entity_number\": " + entityNumber + ",");
        addStringln("\"name\": \"constant-combinator\",");
        addStringln("\"position\": {");
        addStringln("\"x\": " + x + ",");
        addStringln("\"y\": " + y);
        addStringln("},");
        addStringln("\"control_behavior\": {");
        addStringln("\"filters\": [");
        addStringln("{");
        addStringln("\"signal\": {");
        addStringln("\"type\": \"virtual\",");
        addStringln("\"name\": \"signal-" + signalNumber + "\"");
        addStringln("},");
        addStringln("\"count\": 1,");
        addStringln("\"index\": 1");
        addStringln("}");
        addStringln("]");

        if(!bool) {
            addStringln(",");
            addStringln("\"is_on\": false");
        }

        addStringln("}");
        addStringln("}");

        this.entityNumber++;
    }

    public void addString(String string) {
        this.PrintList.add(string);
    }

    public void addStringln(String string) {
        this.PrintList.add((string + "\n"));
    }

    public String convertToOneString(ArrayList<String> array) {
        String out = "";

        for (String string : array) {
            out += string;
        }
        return out;
    }

    public void writeToFile(String string) {
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

    public ArrayList<String> readFromFile(String file) {
        ArrayList<String> out = new ArrayList<String>();

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            for(String line; (line = br.readLine()) != null; ) {
                out.add((line + "\n"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return out;
    }
}