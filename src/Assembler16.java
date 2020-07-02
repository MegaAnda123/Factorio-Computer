import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assembler16 {

    public Assembler16() {

    }

    /**
     * Pars instruction to numerical value and shift it 8 bits left and add extra.
     * @param instruction instruction in string from
     * @param extra extra data TODO make extra parser
     * @return numerical value of one memory address instruction
     */
    public int parsLine(String instruction, int extra) throws FileNotFoundException, InstructionInvalidException {
        return parsInstruction(instruction)*0x100 + extra; //Shift instruction 8 bits left and add extra.
    }

    /**
     * Instruction parser generates numerical value of given instruction.
     * @param instruction instruction method will pars.
     * @return numerical value of instruction.
     */
    public int parsInstruction(String instruction) throws FileNotFoundException, InstructionInvalidException {
        Scanner scanner = new Scanner(new File("16BIT\\Instruction set.csv"));
        scanner.findAll(instruction).findFirst();
        scanner.useDelimiter(",");
        scanner.next();
        String temp = scanner.next();
        scanner.close();
        try {
            return Integer.parseInt(temp, 16); //Pars hex to decimal.
        } catch (Exception e) {
            throw new InstructionInvalidException("\"" + instruction + "\" is not recognised as a instruction.");
        }
    }

}
