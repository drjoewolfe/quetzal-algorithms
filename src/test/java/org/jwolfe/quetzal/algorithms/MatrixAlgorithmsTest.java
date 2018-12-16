package org.jwolfe.quetzal.algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixAlgorithmsTest {

    @Test
    void printSpiralMatrix() {
        int matrix[][] = {
                {1,  2,  3,  4,  5,  6},
                {7,  8,  9,  10, 11, 12},
                {13, 14, 15, 16, 17, 18}
        };

        MatrixAlgorithms.printSpiralMatrix(matrix);
    }

    @Test
    void printUniqueRowsInBooleanMatrixTrie() {
        int M[][] = {
                {0, 1, 0, 0, 1},
                {1, 0, 1, 1, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 1, 0, 0}
        };

        MatrixAlgorithms.printUniqueRowsInBooleanMatrixTrie( M );
    }

    @Test
    void printUniqueRowsInBooleanMatrixHashSet() {
        int M[][] = {
                {0, 1, 0, 0, 1},
                {1, 0, 1, 1, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 1, 0, 0}
        };

        MatrixAlgorithms.printUniqueRowsInBooleanMatrixHashSet( M );
    }

    @Test
    void maximumSizeSquareSubmatrixWithOnes() {
        int M[][] = {
                {0, 1, 1, 0, 1},
                {1, 1, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0}
        };

        int size = MatrixAlgorithms.maximumSizeSquareSubmatrixWithOnes(M);
        System.out.println("Maximum size square submatrix of 1s is " + size);
    }

    @Test
    void placeSquareNNumbersForEqualSum() {
        int n = 5;
        int[][] arr = MatrixAlgorithms.placeSquareNNumbersForEqualSum(n);
    }

    @Test
    void checkRectangeWithCorners1() {
        int[][] matrix = {
                { 1, 0, 0, 1, 0 },
                { 0, 0, 1, 0, 1 },
                { 0, 0, 0, 1, 0 },
                { 1, 0, 1, 0, 1 }
        };

        if (MatrixAlgorithms.checkRectangeWithCorners1(matrix))
            System.out.println("Yes");
        else
            System.out.println("No");
    }

    @Test
    void checkRectangeWithCorners1Brute() {
        int[][] matrix = {
                { 1, 0, 0, 1, 0 },
                { 0, 0, 1, 0, 1 },
                { 0, 0, 0, 1, 0 },
                { 1, 0, 1, 0, 1 }
        };

        if (MatrixAlgorithms.checkRectangeWithCorners1Brute(matrix))
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}