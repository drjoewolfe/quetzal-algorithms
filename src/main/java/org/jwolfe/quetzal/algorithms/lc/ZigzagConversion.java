package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class ZigzagConversion {
    class Solution {
        public String convert(String s, int numRows) {
            if(s == null || s.length() == 0 || numRows < 2) {
                return s;
            }

            List<StringBuilder> rows = new ArrayList<>();
            for(int i = 0; i < numRows; i++) {
                rows.add(new StringBuilder());
            }

            boolean zig = true;
            int rowIndex = 0;
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                StringBuilder row = rows.get(rowIndex);
                row.append(c);

                if(zig) {
                    rowIndex++;
                    if(rowIndex == numRows) {
                        zig = false;
                        rowIndex -= 2;
                    }
                } else {
                    rowIndex--;
                    if(rowIndex < 0) {
                        zig = true;
                        rowIndex = 1;
                    }
                }
            }

            StringBuilder result = new StringBuilder();
            for(int i = 0; i < numRows; i++) {
                result.append(rows.get(i).toString());
            }

            return result.toString();
        }
    }

    class Solution_Correct_1 {
        public String convert(String s, int numRows) {
            if(s == null || s.length() == 0 || numRows < 2) {
                return s;
            }

            List<StringBuilder> rows = new ArrayList<>();
            for(int i = 0; i < numRows; i++) {
                rows.add(new StringBuilder());
            }

            boolean zig = true;
            int rowIndex = 0;
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                rows.get(rowIndex).append(c);
                if(zig) {
                    if(rowIndex == numRows - 1) {
                        zig = !zig;
                        rowIndex--;
                    } else {
                        rowIndex++;
                    }
                } else {
                    if(rowIndex == 0) {
                        zig = !zig;
                        rowIndex++;
                    } else {
                        rowIndex--;
                    }
                }
            }

            StringBuilder builder = new StringBuilder();
            for(StringBuilder row : rows) {
                builder.append(row.toString());
            }

            return builder.toString();
        }
    }

// "PAYPALISHIRING"
// 3
}

//    6. Zigzag Conversion
//    Medium
//    The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
//
//    P   A   H   N
//    A P L S I I G
//    Y   I   R
//    And then read line by line: "PAHNAPLSIIGYIR"
//
//    Write the code that will take a string and make this conversion given a number of rows:
//
//    string convert(string s, int numRows);
//
//
//    Example 1:
//
//    Input: s = "PAYPALISHIRING", numRows = 3
//    Output: "PAHNAPLSIIGYIR"
//    Example 2:
//
//    Input: s = "PAYPALISHIRING", numRows = 4
//    Output: "PINALSIGYAHRPI"
//    Explanation:
//    P     I    N
//    A   L S  I G
//    Y A   H R
//    P     I
//    Example 3:
//
//    Input: s = "A", numRows = 1
//    Output: "A"
//
//
//    Constraints:
//
//    1 <= s.length <= 1000
//    s consists of English letters (lower-case and upper-case), ',' and '.'.
//    1 <= numRows <= 1000