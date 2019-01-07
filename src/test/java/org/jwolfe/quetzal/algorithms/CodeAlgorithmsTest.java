package org.jwolfe.quetzal.algorithms;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.utilities.Utilities;
import org.jwolfe.quetzal.test.QuetzalAssertions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CodeAlgorithmsTest {

    @Test
    void getGrayCodes() {
        List<String> grayCodes;
        List<String> expectedGrayCodes;

        expectedGrayCodes = Utilities.constructList("0", "1");
        grayCodes = CodeAlgorithms.getGrayCodes(1);
        QuetzalAssertions.assertListEquals(expectedGrayCodes, grayCodes);

        expectedGrayCodes = Utilities.constructList("00", "01", "11", "10");
        grayCodes = CodeAlgorithms.getGrayCodes(2);
        QuetzalAssertions.assertListEquals(expectedGrayCodes, grayCodes);

        expectedGrayCodes = Utilities.constructList("000", "001", "011", "010", "110", "111", "101", "100");
        grayCodes = CodeAlgorithms.getGrayCodes(3);
        QuetzalAssertions.assertListEquals(expectedGrayCodes, grayCodes);

        expectedGrayCodes = Utilities.constructList("0000", "0001", "0011", "0010", "0110", "0111", "0101", "0100", "1100", "1101", "1111", "1110", "1010", "1011", "1001", "1000");
        grayCodes = CodeAlgorithms.getGrayCodes(4);
        QuetzalAssertions.assertListEquals(expectedGrayCodes, grayCodes);
    }

    @Test
    void grayToBinary() {
        assertNull(CodeAlgorithms.grayToBinary(null));
        assertNull(CodeAlgorithms.grayToBinary("abc"));
        assertEquals("0011", CodeAlgorithms.grayToBinary("0010"));
        assertEquals("01001", CodeAlgorithms.grayToBinary("01101"));
    }

    @Test
    void binaryToGray() {
        assertNull(CodeAlgorithms.binaryToGray(null));
        assertNull(CodeAlgorithms.binaryToGray("abc"));
        assertEquals("0010", CodeAlgorithms.binaryToGray("0011"));
        assertEquals("01101", CodeAlgorithms.binaryToGray("01001"));
    }

    @Test
    void grayToNumber() {
        assertEquals(-1, CodeAlgorithms.grayToNumber(null));
        assertEquals(-1, CodeAlgorithms.grayToNumber(""));
        assertEquals(-1, CodeAlgorithms.grayToNumber("abc"));
        assertEquals(7, CodeAlgorithms.grayToNumber("100"));
        assertEquals(10, CodeAlgorithms.grayToNumber("1111"));
    }

    @Test
    void numberToGray() {
        assertNull(CodeAlgorithms.numberToGray(-1));
        assertEquals("0", CodeAlgorithms.numberToGray(0));
        assertEquals("100", CodeAlgorithms.numberToGray(7));
        assertEquals("1111", CodeAlgorithms.numberToGray(10));
    }

    @Test
    void grayValueToNumber() {
        assertEquals(0, CodeAlgorithms.grayValueToNumber(0));
        assertEquals(1, CodeAlgorithms.grayValueToNumber(1));
        assertEquals(2, CodeAlgorithms.grayValueToNumber(3));
        assertEquals(4, CodeAlgorithms.grayValueToNumber(6));
        assertEquals(5, CodeAlgorithms.grayValueToNumber(7));
        assertEquals(6, CodeAlgorithms.grayValueToNumber(5));
        assertEquals(7, CodeAlgorithms.grayValueToNumber(4));
    }

    @Test
    void numberToGrayValue() {
        assertEquals(0, CodeAlgorithms.numberToGrayValue(0));
        assertEquals(1, CodeAlgorithms.numberToGrayValue(1));
        assertEquals(3, CodeAlgorithms.numberToGrayValue(2));
        assertEquals(6, CodeAlgorithms.numberToGrayValue(4));
        assertEquals(7, CodeAlgorithms.numberToGrayValue(5));
        assertEquals(5, CodeAlgorithms.numberToGrayValue(6));
        assertEquals(4, CodeAlgorithms.numberToGrayValue(7));
    }
}