package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangleII {
    class Solution {
        public List<Integer> getRow(int rowIndex) {
            List<Integer> row = new ArrayList<>();
            if(rowIndex < 0) {
                return row;
            }

            row.add(1);
            for(int i = 1; i <= rowIndex; i++) {
                for(int j = row.size() - 1; j > 0; j--) {
                    row.set(j, row.get(j) + row.get(j - 1));
                }

                row.add(1);
            }

            return row;
        }
    }

    class Solution_Correct_1 {
        public List<Integer> getRow(int rowIndex) {
            List<Integer> row = new ArrayList<>();

            if(rowIndex < 0) {
                return row;
            }

            row.add(1);
            for(int currentRowIndex = 1; currentRowIndex <= rowIndex; currentRowIndex++) {
                for(int j = row.size() - 1; j > 0; j--) {
                    row.set(j, row.get(j) + row.get(j - 1));
                }

                row.add(1);
            }

            return row;
        }

        public List<Integer> getRowClassic(int rowIndex) {
            List<Integer> row = new ArrayList<>();

            if(rowIndex < 0) {
                return row;
            }

            row.add(1);

            List<Integer> previousRow = null;
            for(int currentRowIndex = 1; currentRowIndex <= rowIndex; currentRowIndex++) {
                previousRow = row;

                row = new ArrayList<>();
                row.add(1);
                for(int i = 1; i <previousRow.size(); i++) {
                    row.add(previousRow.get(i) + previousRow.get(i - 1));
                }

                row.add(1);
            }

            return row;
        }
    }
}

//    119. Pascal's Triangle II
//    Easy
//    Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
//
//    In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
//
//
//
//
//    Example 1:
//
//    Input: rowIndex = 3
//    Output: [1,3,3,1]
//    Example 2:
//
//    Input: rowIndex = 0
//    Output: [1]
//    Example 3:
//
//    Input: rowIndex = 1
//    Output: [1,1]
//
//
//    Constraints:
//
//    0 <= rowIndex <= 33
//
//
//    Follow up: Could you optimize your algorithm to use only O(rowIndex) extra space?