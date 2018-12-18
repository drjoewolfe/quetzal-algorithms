package org.jwolfe.quetzal.algorithms;

import org.jwolfe.quetzal.library.matrix.Matrix;
import org.jwolfe.quetzal.library.trie.BinaryTrieNode;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.HashMap;
import java.util.HashSet;

public class MatrixAlgorithms {
    public static void printSpiralMatrix(int[][] matrix) {
        int left = 0;
        int right = matrix[0].length;
        int top = 0;
        int bottom = matrix.length;

        while(left < right
                && top < bottom) {
            for (int i = left; i < right; i++) {
                System.out.print(matrix[top][i] + "\t");
            }
            System.out.println();
            top++;

            for (int i = top; i < bottom; i++) {
                System.out.print(matrix[i][right-1] + "\t");
            }
            System.out.println();
            right--;

            if(top < bottom) {
                for (int i = right - 1; i >= left; i--) {
                    System.out.print(matrix[bottom - 1][i] + "\t");
                }
                System.out.println();
                bottom--;
            }

            if(left < right) {
                for (int i = bottom - 1; i >= top; i--) {
                    System.out.print(matrix[i][left] + "\t");
                }
                System.out.println();
                left++;
            }
        }
    }

    public static void printUniqueRowsInBooleanMatrixTrie(int[][] matrix) {
        printMatrix(matrix);
        if (matrix == null)
            return;

        int rows = matrix.length;
        if (rows == 0)
            return;

        int columns = matrix[0].length;
        if (columns == 0)
            return;

        System.out.println();
        BinaryTrieNode root = new BinaryTrieNode();
        for(int i = 0; i<rows; i++) {
            if(insertIntoTrie(root, matrix, i, 0)) {
                printMatrix(matrix, i);
            }
        }
    }

    private static boolean insertIntoTrie(BinaryTrieNode root, int[][] matrix, int row, int startColumn) {
        int columns = matrix[row].length;

        if(startColumn < columns) {
            int index = matrix[row][startColumn];
            var child = root.getChildren()[index];
            if(child == null) {
                child = new BinaryTrieNode();
                root.getChildren()[index] = child;
            }

            return insertIntoTrie(child, matrix, row, startColumn + 1);
        }
        else {
            if(!root.isEndMarker()) {
                root.setEndMarker(true);
                return true;
            }
        }

        return false;
    }

    public static void printUniqueRowsInBooleanMatrixHashSet(int[][] matrix) {
        printMatrix(matrix);

        if (matrix == null)
            return;

        int rows = matrix.length;
        if (rows == 0)
            return;

        int columns = matrix[0].length;
        if (columns == 0)
            return;


        System.out.println();
        var set = new HashSet<String>();
        for (int i = 0; i < rows; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < columns; j++) {
                sb.append(matrix[i][j]);
            }

            String str = sb.toString();
            if(!set.contains(str)){
                printMatrix(matrix, i);
                set.add(str);
            }
        }
    }

    public static int maximumSizeSquareSubmatrixWithOnes(int[][] matrix) {
        printMatrix(matrix);

        if (matrix == null)
            return 0;

        int rows = matrix.length;
        if (rows == 0)
            return 0;

        int columns = matrix[0].length;
        if (columns == 0)
            return 0;

        int[][] S = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            S[i][0] = matrix[i][0];
        }

        for (int j = 0; j < columns; j++) {
            S[0][j] = matrix[0][j];
        }

        int max = 0;
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                if(matrix[i][j] == 0) {
                    S[i][j] = 0;
                }
                else {
                    S[i][j] = 1 + Utilities.min(
                            S[i - 1][j],
                            S[i][j - 1],
                            S[i - 1][j - 1]
                    );

                    if (max < S[i][j])
                        max = S[i][j];
                }
            }
        }

        System.out.println();
        printMatrix(S);
        return max;
    }

    public static int[][] placeSquareNNumbersForEqualSum(int n) {
        int[][] matrix = new int[n][n];
        int[][] answer = new int[n][n];

        int number = 1;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                matrix[i][j] = number;
                number++;
            }
        }

        printMatrix(matrix);

        for(int i=0; i<n; i++) {
            for (int j = 0; j < n; j++) {
                answer[i][j] = matrix[j][(i+j)%n];
            }
        }

        System.out.println();
        printMatrix(answer);
        return answer;
    }

    public static boolean checkRectangeWithCorners1(int[][] matrix) {
        printMatrix(matrix);

        if (matrix == null)
            return false;

        int rows = matrix.length;
        if (rows == 0)
            return false;

        int columns = matrix[0].length;
        if (columns == 0)
            return false;

        var map = new HashMap<Integer, HashSet<Integer>>();
        for(int i=0; i<rows; i++) {
            for (int j = 0; j < columns; j++) {
                for(int k = j+1; k < columns; k++) {
                    if(matrix[i][j] == 1
                            && matrix[i][k] == 1) {

                        if(map.containsKey(j)
                                && map.get(j).contains(k))
                            return true;

                        if(map.containsKey(k)
                                && map.get(k).contains(j))
                            return true;

                        if (!map.containsKey(j)) {
                            map.put(j, new HashSet<>());
                        }

                        if (!map.containsKey(k)) {
                            map.put(k, new HashSet<>());
                        }

                        map.get(j).add(k);
                        map.get(k).add(j);
                    }
                }
            }
        }

        System.out.println();
        for(int i : map.keySet()) {
            System.out.print(i + ": \t");
            for(int j : map.get(i)) {
                System.out.print(j + "\t");
            }

            System.out.println();
        }

        return false;
    }

    public static boolean checkRectangeWithCorners1Brute(int[][] matrix) {
        printMatrix(matrix);

        if (matrix == null)
            return false;

        int rows = matrix.length;
        if (rows == 0)
            return false;

        int columns = matrix[0].length;
        if (columns == 0)
            return false;

        for (int x1 = 0; x1 < rows; x1++) {
            for (int y1 = 0; y1 < columns; y1++) {

                if (matrix[x1][y1] == 1) {
                    for (int x2 = x1 + 1; x2 < rows; x2++) {
                        for (int y2 = y1 + 1; y2 < columns; y2++) {
                            if(matrix[x1][y2] == 1
                                    && matrix[x2][y1] == 1
                                    && matrix[x2][y2] ==1)
                                return true;

                        }
                    }
                }
            }
        }

        return false;
    }

    public static boolean isOrthogonal(int[][] matrix) {
        // Orthogonal => A * At = I
        // Only applicable for square matrixes

        if(matrix == null
                || matrix.length == 0) {
            return false;
        }

        int length = matrix.length;
        for (int i = 0; i < length; i++) {
            if(length != matrix[i].length) {
                return false;
            }
        }

        int[][] transpose = Matrix.getTranspose(matrix);
        int[][] product = Matrix.multiply(matrix, transpose);
        return Matrix.isIdentity(product);
    }

    private static void printMatrix(int[][] matrix) {
        if (matrix == null)
            return;

        int rows = matrix.length;
        if (rows == 0)
            return;

        int columns = matrix[0].length;
        if (columns == 0)
            return;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(matrix[i][j] + "\t");
            }

            System.out.println();
        }
    }

    private static void printMatrix(int[][] matrix, int row) {
        if (matrix == null)
            return;

        int rows = matrix.length;
        if (rows < row)
            return;

        int columns = matrix[0].length;
        if (columns == 0)
            return;

        for (int j = 0; j < columns; j++) {
            System.out.print(matrix[row][j] + "\t");
        }

        System.out.println();
    }
}

