package B16;

import java.io.*;
import java.util.*;

import Tools.*;

public class Assembler16 {

    File instruction;

    public Assembler16() {
        instruction = new File("src\\B16\\16BIT\\Instruction set.csv");
    }

    /**
     * Parser for higher level instructions. Falls back to parser level 0 if parsing fails.
     * Operation format example: "LOAD_A 16","ADD 0","print Hello world!".
     * @param instruction instruction in string form.
     * @return integer array containing numerical value of instructions.
     */
    public int[] parserLevel1(String instruction) throws ParserException {
        List<Integer> out = new ArrayList<>();

        try {
            switch (instruction.split(" ")[0]) {
                case "print":
                    char[] chars = instruction.substring(6).toCharArray();
                    out.add(parsOperationLevel0("DISP " + (int) Math.ceil((double) chars.length/2)));
                    for (int i = 0; i < chars.length; i+=2) {
                        try {
                            out.add(parsCharactersLevel0(chars[i] + " " + chars[i+1]));
                        } catch (IndexOutOfBoundsException e) {
                            out.add(parsCharactersLevel0(chars[i] + "  "));
                        }
                    }
                    break;
                default:
                    throw new Exception("No high level instruction detected, fallback to parser level0");
            }
        } catch (Exception ignored) {
            out.add(parserLevel0(instruction));
        }

        //Convert Integer list to int array before returning.
        int[] temp = new int[out.size()];
        for (int i = 0; i < out.size(); i++) {
            temp[i] = out.get(i);
        }

        return temp;
    }

    /**
     * Basic assembly level syntax detection.
     * Level 0 parser tries to pars instruction as a operation, number or character if all fails parser exception is thrown.
     * Operation format example: "LOAD_A 16","ADD 0".
     * Number format example: "256","0x100","0xFF".
     * Character format example: "A B","a c","   ","1 %","\0 \0".
     * @param instruction instruction in string form.
     * @return numerical value of one memory address instruction
     */
    public int parserLevel0(String instruction) throws ParserException {
        //Try to pars operation.
        try {
            return parsOperationLevel0(instruction);
        } catch (Exception ignored) {}

        //Try to pars number.
        try {
            return parsNumberLevel0(instruction);
        } catch (Exception ignored) {}

        //Try to pars characters.
        try {
            return parsCharactersLevel0(instruction);
        } catch (Exception ignored) {}

        throw new ParserException("Instruction: \"" +  instruction + "\" could not be parsed.");
    }

    /**
     * Operation parser for parserLevel0.
     * Operation parser generates numerical value of given operation and shifts it 8 bits left and adds address values.
     * @param instruction operation method will pars.
     * @return numerical value of operation.
     * @throws OperationInvalidException
     * @throws FileNotFoundException
     */
    private int parsOperationLevel0(String instruction) throws OperationInvalidException, FileNotFoundException {
        if(instruction.split(" ")[0].equals("a")) {throw new OperationInvalidException("Stops weird bug");}

        int numericOperation;
        numericOperation = parsOperation(instruction.split(" ")[0]); //Pars operation form string to numeric value.
        numericOperation *= 0x100; //Shift operation 8 bits left.
        numericOperation += Integer.decode(instruction.split(" ")[1]); // Add extra.
        return numericOperation;
    }

    /**
     * Number parser for parserLevel0.
     * @param instruction number to be parsed.
     * @return returns base 10 numerical value of number.
     * @throws NumberFormatException
     */
    private int parsNumberLevel0(String instruction) throws NumberFormatException {
        try {
            return Integer.valueOf(instruction, 10);
        } catch (Exception ignore) {
            //Try to pars hex.
            try {
                if(instruction.startsWith("0x")) {
                    return Integer.valueOf(instruction.substring(2), 16);
                }
            } catch (Exception ignored) {}
        }
        throw new NumberFormatException("Number format not recognised");
    }


    private int parsCharactersLevel0(String instruction) throws CharacterInvalidException {
        if(instruction.length()!=3) {throw new CharacterInvalidException("Unexpected format");}
        if(instruction.charAt(1)!=' ') {throw new CharacterInvalidException("Unexpected format");}
        int numericCharacters;
        numericCharacters = (parsCharacter(instruction.charAt(0)) + 0x10) * 0x100;
        numericCharacters += (parsCharacter(instruction.charAt(2)) + 0x10);
        return numericCharacters;
    }

    /**
     * Operation parser generates numerical value of given operation.
     * @param operation operation method will pars.
     * @return numerical value of operation.
     */
    public int parsOperation(String operation) throws FileNotFoundException, OperationInvalidException {
        Scanner scanner = new Scanner(instruction);
        scanner.findAll(operation).findFirst();
        scanner.useDelimiter(",");
        scanner.next();
        String temp = scanner.next();
        scanner.close();
        try {
            return Integer.parseInt(temp, 16); //Pars hex to decimal.
        } catch (Exception e) {
            throw new OperationInvalidException("\"" + operation + "\" is not recognised as a operation.");
        }
    }

    /**
     * Character parser generates numerical value of given character.
     * @param character character method will pars.
     * @return numerical value of character.
     */
    public int parsCharacter(char character) throws CharacterInvalidException {
        if(character==' ') return 0;
        if(character=='\0') return -16;
        String characters = Tools.readFromFile("src\\B16\\16BIT\\Characters.csv");
        String[] temp = characters.split("\n");

        try {
            for(String line : temp) {
                if(line.charAt(0)==character) {
                    return Integer.parseInt(line.substring(2),10);
                }
            }
        } catch (Exception e) {
            throw new CharacterInvalidException("\"" + character + "\" is not recognised as a character or is not supported.");
        }
        throw new CharacterInvalidException("\"" + character + "\" is not recognised as a character or is not supported.");
    }

}
