package org.jwolfe.quetzal.algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberAlgorithmsTest {

    @Test
    void powerWithoutMutiplicationAndDivision() {
        assertEquals(NumberAlgorithms.powerWithoutMutiplicationAndDivision(5, 3), 125)  ;
    }

    @Test
    void countSetBits() {
        assertEquals(NumberAlgorithms.countSetBits(6), 2);
        assertEquals(NumberAlgorithms.countSetBits(13), 3);
    }

    @Test
    void getBinaryRepresentation() {
        NumberAlgorithms.getBinaryRepresentation(7);
        System.out.println();
        NumberAlgorithms.getBinaryRepresentation(4);
        System.out.println();
        NumberAlgorithms.getBinaryRepresentation(15);
        System.out.println();
    }

    @Test
    void maxNumberFromDigitsOfNumber() {
        int number = 38293367;
        int maxNumber = NumberAlgorithms.maxNumberFromDigitsOfNumber(number);
        System.out.println(number + " -> " + maxNumber);
    }

    @Test
    void turnOffBit() {
        assertEquals(NumberAlgorithms.turnOffBit(15, 1), 14);
        assertEquals(NumberAlgorithms.turnOffBit(15, 2), 13);
        assertEquals(NumberAlgorithms.turnOffBit(15, 3), 11);
        assertEquals(NumberAlgorithms.turnOffBit(15, 4), 7);
        assertEquals(NumberAlgorithms.turnOffBit(15, 5), 15);
    }

    @Test
    void convert0To5() {
        int number = 10010010;

        System.out.println(number);
        number = NumberAlgorithms.convert0To5(number);
        System.out.println(number);

        assertEquals(15515515, number);
    }

    @Test
    void swapOddEvenBits() {
        int number;
        int result;

        number = 23;
        result = NumberAlgorithms.swapOddEvenBits(number);
        System.out.println(number + " -> " + result);
        assertEquals(43, result);
    }

    @Test
    void addOneWithoutMathOperators() {
        assertEquals(15, NumberAlgorithms.addOneWithoutMathOperators(14));
        assertEquals(204, NumberAlgorithms.addOneWithoutMathOperators(203));
        assertEquals(2, NumberAlgorithms.addOneWithoutMathOperators(1));
        assertEquals(100, NumberAlgorithms.addOneWithoutMathOperators(99));
        assertEquals(1001, NumberAlgorithms.addOneWithoutMathOperators(1000));
    }

    @Test
    void nextSparse() {
        assertEquals(8, NumberAlgorithms.nextSparse(6));
        assertEquals(4, NumberAlgorithms.nextSparse(4));
        assertEquals(40, NumberAlgorithms.nextSparse(38));
        assertEquals(64, NumberAlgorithms.nextSparse(44));
    }
}