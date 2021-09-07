package B16;

import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Assembler16Test {
    private Assembler16 assembler16;

    public Assembler16Test() {
        assembler16 = new Assembler16();
    }

    @Test
    public void parserLevel1Test() throws ParserException {

        //Print tests
        assertArrayEquals(new int[] {13060, 20818, 21332, 4181, 22104},assembler16.parserLevel1("print abcd efh"));
        assertArrayEquals(new int[] {13061, 20818, 21332, 4181, 22104, 8464},assembler16.parserLevel1("print abcd efh1"));

        //Parser level0 tests
        assertArrayEquals(new int[] {256}, assembler16.parserLevel1("LOAD_A 0"));
        assertArrayEquals(new int[] {511}, assembler16.parserLevel1("LOAD_A 0xff"));
        assertArrayEquals(new int[] {123}, assembler16.parserLevel1("123"));
        assertArrayEquals(new int[] {20818},assembler16.parserLevel1("a b"));

        //Negative tests
        assertThrows(ParserException.class, () -> assembler16.parserLevel1("NOT_A_INSTRUCTION 0"));
        assertThrows(ParserException.class, () -> assembler16.parserLevel1("LOAD_A"));
        assertThrows(ParserException.class, () -> assembler16.parserLevel1(""));
        assertThrows(ParserException.class, () -> assembler16.parserLevel1("-0xff"));
        assertThrows(ParserException.class, () -> assembler16.parserLevel1("0xgf"));
        assertThrows(ParserException.class, () -> assembler16.parserLevel1("abc"));
        assertThrows(ParserException.class, () -> assembler16.parserLevel1("æ æ"));
        assertThrows(ParserException.class, () -> assembler16.parserLevel1("a æ"));

    }

    @Test
    public void parserLevel0Test() throws ParserException, CharacterInvalidException {
        //TODO maybe, write better tests (generate expected values from instruction csv and character csv).

        //Operation tests
        assertEquals(256, assembler16.parserLevel0("LOAD_A 0"));
        assertEquals(258, assembler16.parserLevel0("LOAD_A 2"));
        assertEquals(12132, assembler16.parserLevel0("JUMP_Z 100"));

        //Number tests
        assertEquals(123, assembler16.parserLevel0("123"));
        assertEquals(255, assembler16.parserLevel0("0xff"));
        assertEquals(-1, assembler16.parserLevel0("-1"));

        //Character tests
        assertEquals(20818,assembler16.parserLevel0("a b"));
        assertEquals(4112,assembler16.parserLevel0("   "));
        assertEquals(12143,assembler16.parserLevel0("? ■"));
        assertEquals(21028,assembler16.parserLevel0("b 4"));
        assertEquals(8224,assembler16.parserLevel0("0 0"));
        assertEquals(20768,assembler16.parserLevel0("a 0"));
        assertEquals(12576,assembler16.parserLevel0("A 0"));
        assertEquals(19487,assembler16.parserLevel0("\\ /"));
        assertEquals(0,assembler16.parserLevel0("\0 \0"));

        //Negative tests
        assertThrows(ParserException.class, () -> assembler16.parserLevel0("NOT_A_INSTRUCTION 0"));
        assertThrows(ParserException.class, () -> assembler16.parserLevel0("LOAD_A"));
        assertThrows(ParserException.class, () -> assembler16.parserLevel0(""));
        assertThrows(ParserException.class, () -> assembler16.parserLevel0("-0xff"));
        assertThrows(ParserException.class, () -> assembler16.parserLevel0("0xgf"));
        assertThrows(ParserException.class, () -> assembler16.parserLevel0("abc"));
        assertThrows(ParserException.class, () -> assembler16.parserLevel0("æ æ"));
        assertThrows(ParserException.class, () -> assembler16.parserLevel0("a æ"));
    }
}
