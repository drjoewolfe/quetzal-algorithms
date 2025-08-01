package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
    class Solution {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> triangle = new ArrayList<>();
            if (numRows < 1) {
                return triangle;
            }

            List<Integer> currRow = new ArrayList<>();
            currRow.add(1);
            triangle.add(currRow);

            for (int i = 2; i <= numRows; i++) {
                List<Integer> prevRow = currRow;

                currRow = new ArrayList<>();
                currRow.add(1);

                for (int j = 0; j < prevRow.size() - 1; j++) {
                    int first = prevRow.get(j);
                    ;
                    int second = prevRow.get(j + 1);

                    currRow.add(first + second);
                }

                currRow.add(1);
                triangle.add(currRow);
            }

            return triangle;
        }
    }

    class Solution_Correct_1 {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> triangle = new ArrayList<>();

            if(numRows < 1) {
                return triangle;
            }

            List<Integer> row = new ArrayList<>();
            row.add(1);

            triangle.add(row);

            for(int i = 2; i <= numRows; i++) {
                List<Integer> previousRow = row;
                row = new ArrayList<>();

                row.add(1);
                for(int j = 1; j < previousRow.size(); j++) {
                    row.add(previousRow.get(j - 1) + previousRow.get(j));
                }

                row.add(1);

                triangle.add(row);
            }

            return triangle;
        }
    }
}

//    118. Pascal's Triangle
//    Easy
//    Given an integer numRows, return the first numRows of Pascal's triangle.
//
//    In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
//
//
//
//
//    Example 1:
//
//    Input: numRows = 5
//    Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
//    Example 2:
//
//    Input: numRows = 1
//    Output: [[1]]
//
//
//    Constraints:
//
//    1 <= numRows <= 30
