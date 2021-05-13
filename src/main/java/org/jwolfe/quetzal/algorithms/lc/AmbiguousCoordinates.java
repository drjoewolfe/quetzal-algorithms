package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class AmbiguousCoordinates {
    class Solution {
        public List<String> ambiguousCoordinates(String s) {
            List<String> results = new ArrayList<>();
            if(s == null || s.length() < 2) {
                return results;
            }

            String str = s.substring(1, s.length() - 1);
            for(int i = 1; i <= str.length() - 1; i++) {
                processCoordinates(str, i, results);
            }

            return results;
        }

        private void processCoordinates(String str, int index, List<String> results) {
            // a = s[0...index - 1], b = s[index...n]
            String a = str.substring(0, index);
            String b = str.substring(index);

            for(int i = 0; i <= a.length() - 1; i++) {
                for(int j = 0; j <= b.length() - 1; j++) {
                    processDecimals(a, b, i, j, results);
                }
            }
        }

        private void processDecimals(String a, String b, int i, int j, List<String> results) {
            String ad = a;
            if(i > 0) {
                ad = a.substring(0, i) + "." + a.substring(i);
            }

            String bd = b;
            if(j > 0) {
                bd = b.substring(0, j) + "." + b.substring(j);
            }

            if(isValid(ad) && isValid(bd)) {
                results.add("(" + ad + ", " + bd + ")");
            }

        }

        private boolean isValid(String a) {
            // No Decimal: Length > 1 => should not start with zero
            // Decimal: Left part & right part
            //          Left part: Length > 1  => should not start with zero
            //          Right part: Should not end with zero

            String[] parts = a.split("\\.");
            String left = parts.length > 0 ? parts[0] : a;
            String right = parts.length > 1 ? parts[1] : null;

            if(left.length() > 1 && left.charAt(0) == '0') {
                return false;
            }

            if(right != null && right.charAt(right.length() - 1) == '0') {
                return false;
            }

            return true;
        }
    }


// "(123)"
// "(00011)"
}

//    816. Ambiguous Coordinates
//    Medium
//    We had some 2-dimensional coordinates, like "(1, 3)" or "(2, 0.5)".  Then, we removed all commas, decimal points, and spaces, and ended up with the string s.  Return a list of strings representing all possibilities for what our original coordinates could have been.
//
//    Our original representation never had extraneous zeroes, so we never started with numbers like "00", "0.0", "0.00", "1.0", "001", "00.01", or any other number that can be represented with less digits.  Also, a decimal point within a number never occurs without at least one digit occuring before it, so we never started with numbers like ".1".
//
//    The final answer list can be returned in any order.  Also note that all coordinates in the final answer have exactly one space between them (occurring after the comma.)
//
//    Example 1:
//    Input: s = "(123)"
//    Output: ["(1, 23)", "(12, 3)", "(1.2, 3)", "(1, 2.3)"]
//    Example 2:
//    Input: s = "(00011)"
//    Output:  ["(0.001, 1)", "(0, 0.011)"]
//    Explanation:
//    0.0, 00, 0001 or 00.01 are not allowed.
//    Example 3:
//    Input: s = "(0123)"
//    Output: ["(0, 123)", "(0, 12.3)", "(0, 1.23)", "(0.1, 23)", "(0.1, 2.3)", "(0.12, 3)"]
//    Example 4:
//    Input: s = "(100)"
//    Output: [(10, 0)]
//    Explanation:
//    1.0 is not allowed.
//
//
//    Note:
//
//    4 <= s.length <= 12.
//    s[0] = "(", s[s.length - 1] = ")", and the other elements in s are digits.