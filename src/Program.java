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

CODE:

Fibonacci with overflow protection:

        code(0,"LOAD_D",13);
        code(1,"LOAD_A",15);
        code(2,"LOAD_B",14);
        code(3,"ADD",1);
        code(4,"LOAD_B",15);
        code(5,"STORE_A",15);
        code(6,"STORE_A",14);
        code(7,"ADD",1);
        code(8,"STORE_A",15);
        code(9,"SUB",12);
        code(10,"JUMP_NEG",1);
        number(11,0);
        number(13,232);
        number(14,0);
        number(15,1);


        code16(0,"LOAD_A",0xff);
        code16(1,"LOAD_B",0xfe);
        code16(2,"ADD",0x10);
        code16(3,"LOAD_B",0xff);
        code16(4,"STORE_A",0xff);
        code16(5,"STORE_A",0xfe);
        code16(6,"ADD",0x10);
        code16(7,"STORE_A",0xff);
        code16(8,"JUMP_O",10);
        code16(9,"JUMP",0);
        code16(10,"LOAD_ROM",0);

        number16(0xfe,0);
        number16(0xff,1);


Divide cache AB:

        code(0,"STORE_A",14);
        code(1,"LOAD_C",13);
        code(2,"LOAD_A",14);
        code(3,"SUB",4);
        code(4,"STORE_A",14);
        code(5,"JUMP_NEG",9);
        code(6,"INC",2);
        code(7,"STORE_A",13);
        code(8,"JUMP",1);
        code(9,"STORE_C",15);
        code(10,"JUMP",10);
        number(13,0);

Power of two to overflow:

        code(0,"LOAD_A",15);
        code(1,"ADD",0);
        code(2,"STORE_A",15);
        code(3,"JUMP_ZERO",5);
        code(4,"JUMP",1);
        code(5,"LOAD_CARD",0);
        number(15,1);

*/

public class Program {
    GameObjects gameObjects = new GameObjects();

    //TODO remove

    ArrayList<String> PrintList = new ArrayList<String>();
    int entityNumber = 1;

    public void intPrintList() {
        addString(Tools.readFromFile("fileheader.txt"));
    }

    public void finPrintList() {
        addString(Tools.readFromFile("filefooter.txt"));
    }

    public void start() {

        BluePrintEncoder encoder = new BluePrintEncoder("TEMP", new String[]{"signal-C", "signal-1", "signal-6"});



        //intPrintList();

        code16(0, "LOAD_A", 255);
        code16(1,"LOAD_B", 254);
        code16(2, "ADD", 1);
        code16(3, "LOAD_B", 255);
        code16(4, "STORE_A", 255);
        code16(5, "STORE_A", 254);
        code16(6, "ADD",1);
        code16(7, "STORE_A", 255);
        code16(8, "SUB", 0x30);
        code16(9,"JUMP_O", 11);
        code16(10, "JUMP", 0);
        number16(11,0);

        number16(254,0);
        number16(255, 1);


        //finPrintList();






        System.out.println(encoder.EncodeBlueprintMeta(convertToOneString(PrintList)));
        Tools.setClipBoard(encoder.EncodeBlueprintMeta(convertToOneString(PrintList)));

        Tools.writeToFile(convertToOneString(PrintList));
    }

    public void number8(int line, int number) {
        addCodeLine(line,intToBinary(number,8));
    }

    public void code8(int line, String opCode, int address) {
        boolean[] res = combineArrays(functionToOpCode(opCode),intToBinary(address,4));
        addCodeLine(line,res);
    }

    public void code16(int line, String instruction, int address) {
        int n = functions16(instruction)*0x100 + address;
        int x = line % 16;
        int y = (int) Math.floor(line/16)*3;
        PrintList.add(gameObjects.constantCombinator(x,y, new GameObjects.Filter("virtual","signal-R",n)));
    }

    public void character16(int line, char c1, char c2) {
        int n1 = (charToInt16(c1)+0x10)*0x100;
        int n2 = charToInt16(c2)+0x10;

        number16(line,n1+n2);
    }

    public int charToInt16(char c) {
        int i = 0;

        if(c == ' ') return i; i++;
        if(c == '!') return i; i++;
        if(c == '"') return i; i++;
        if(c == '#') return i; i++;
        if(c == '$') return i; i++;
        if(c == '%') return i; i++;
        if(c == '&') return i; i++;
        if(c == '\'') return i; i++;
        if(c == '(') return i; i++;
        if(c == ')') return i; i++;
        if(c == '*') return i; i++;
        if(c == '+') return i; i++;
        if(c == ',') return i; i++;
        if(c == '-') return i; i++;
        if(c == '.') return i; i++;
        if(c == '/') return i; i++;
        if(c == '0') return i; i++;
        if(c == '1') return i; i++;
        if(c == '2') return i; i++;
        if(c == '3') return i; i++;
        if(c == '4') return i; i++;
        if(c == '5') return i; i++;
        if(c == '6') return i; i++;
        if(c == '7') return i; i++;
        if(c == '8') return i; i++;
        if(c == '9') return i; i++;
        if(c == ':') return i; i++;
        if(c == ';') return i; i++;
        if(c == '<') return i; i++;
        if(c == '=') return i; i++;
        if(c == '>') return i; i++;
        if(c == '?') return i; i++;
        if(c == '@') return i; i++;
        if(c == 'A') return i; i++;
        if(c == 'B') return i; i++;
        if(c == 'C') return i; i++;
        if(c == 'D') return i; i++;
        if(c == 'E') return i; i++;
        if(c == 'F') return i; i++;
        if(c == 'G') return i; i++;
        if(c == 'H') return i; i++;
        if(c == 'I') return i; i++;
        if(c == 'J') return i; i++;
        if(c == 'K') return i; i++;
        if(c == 'L') return i; i++;
        if(c == 'M') return i; i++;
        if(c == 'N') return i; i++;
        if(c == 'O') return i; i++;
        if(c == 'P') return i; i++;
        if(c == 'Q') return i; i++;
        if(c == 'R') return i; i++;
        if(c == 'S') return i; i++;
        if(c == 'T') return i; i++;
        if(c == 'U') return i; i++;
        if(c == 'V') return i; i++;
        if(c == 'W') return i; i++;
        if(c == 'X') return i; i++;
        if(c == 'Y') return i; i++;
        if(c == 'Z') return i; i++;
        if(c == '[') return i; i++;
        if(c == '\\') return i; i++;
        if(c == ']') return i; i++;
        if(c == '^') return i; i++;
        if(c == '_') return i; i++;
        if(c == '`') return i; i++;
        if(c == 'a') return i; i++;
        if(c == 'b') return i; i++;
        if(c == 'c') return i; i++;
        if(c == 'd') return i; i++;
        if(c == 'e') return i; i++;
        if(c == 'f') return i; i++;
        if(c == 'g') return i; i++;
        if(c == 'h') return i; i++;
        if(c == 'i') return i; i++;
        if(c == 'j') return i; i++;
        if(c == 'k') return i; i++;
        if(c == 'l') return i; i++;
        if(c == 'm') return i; i++;
        if(c == 'n') return i; i++;
        if(c == 'o') return i; i++;
        if(c == 'p') return i; i++;
        if(c == 'q') return i; i++;
        if(c == 'r') return i; i++;
        if(c == 's') return i; i++;
        if(c == 't') return i; i++;
        if(c == 'u') return i; i++;
        if(c == 'v') return i; i++;
        if(c == 'w') return i; i++;
        if(c == 'x') return i; i++;
        if(c == 'y') return i; i++;
        if(c == 'z') return i; i++;
        if(c == '{') return i; i++;
        if(c == '|') return i; i++;
        if(c == '}') return i; i++;
        if(c == '~') return i; i++;
        if(c == '■') return i;
        if(c == '\0') return -16;

        return -1;
    }

    public void number16(int line, int number) {
        int x = line % 16;
        int y = (int) Math.floor(line/16)*3;
        PrintList.add(gameObjects.constantCombinator(x,y, new GameObjects.Filter("virtual","signal-R",number)));
    }

    public boolean[] intToBinary(int number, int base) {
        final boolean[] ret = new boolean[base];
        for (int i = 0; i < base; i++) {
            ret[base - 1 - i] = (1 << i & number) != 0;
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
        PrintList.addAll(gameObjects.constantCombinator(x,y,String.valueOf(signalNumber),bool));
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

    public int functions16(String function) {
        int n = 0;
        if(function.equals("LOAD_ROM")) return n; n++;
        if(function.equals("LOAD_A")) return n; n++;
        if(function.equals("LOAD_B")) return n; n++;
        if(function.equals("LOAD_C")) return n; n++;
        if(function.equals("LOAD_D")) return n; n++;
        if(function.equals("LOAD_E")) return n; n++;
        if(function.equals("LOAD_F")) return n; n++;
        if(function.equals("LOAD_G")) return n; n++;
        if(function.equals("LOAD_H")) return n; n++;
        if(function.equals("LOAD_I")) return n; n++;
        if(function.equals("LOAD_J")) return n; n++;
        if(function.equals("LOAD_K")) return n; n++;
        if(function.equals("LOAD_L")) return n; n++;
        if(function.equals("LOAD_M")) return n; n++;
        if(function.equals("LOAD_N")) return n; n++;
        if(function.equals("LOAD_O")) return n; n++;
        if(function.equals("LOAD_P")) return n; n++;
        if(function.equals("STORE_A")) return n; n++;
        if(function.equals("STORE_B")) return n; n++;
        if(function.equals("STORE_C")) return n; n++;
        if(function.equals("STORE_D")) return n; n++;
        if(function.equals("STORE_E")) return n; n++;
        if(function.equals("STORE_F")) return n; n++;
        if(function.equals("STORE_G")) return n; n++;
        if(function.equals("STORE_H")) return n; n++;
        if(function.equals("STORE_I")) return n; n++;
        if(function.equals("STORE_J")) return n; n++;
        if(function.equals("STORE_K")) return n; n++;
        if(function.equals("STORE_L")) return n; n++;
        if(function.equals("STORE_M")) return n; n++;
        if(function.equals("STORE_N")) return n; n++;
        if(function.equals("STORE_O")) return n; n++;
        if(function.equals("STORE_P")) return n; n++;
        if(function.equals("AND")) return n; n++;
        if(function.equals("OR")) return n; n++;
        if(function.equals("XOR")) return n; n++;
        if(function.equals("NOT")) return n; n++;
        if(function.equals("ADD")) return n; n++;
        if(function.equals("SUB")) return n; n++;
        if(function.equals("MUL")) return n; n++;
        if(function.equals("DIV")) return n; n++;
        if(function.equals("POW")) return n; n++;
        if(function.equals("MOD")) return n; n++;
        if(function.equals("INC")) return n; n++;
        if(function.equals("DEC")) return n; n++;
        if(function.equals("JUMP")) return n; n++;
        if(function.equals("JUMP_O")) return n; n++;
        if(function.equals("JUMP_Z")) return n; n++;
        if(function.equals("JUMP_N")) return n; n++;
        if(function.equals("CALL")) return n; n++;
        if(function.equals("RET")) return n; n++;
        if(function.equals("DISP")) return n; n++;


        return -1;
    }
}