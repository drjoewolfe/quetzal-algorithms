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
}