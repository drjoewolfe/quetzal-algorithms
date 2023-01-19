package org.jwolfe.quetzal.algorithms.lc;

public class StringWithoutAAAOrBBB {
    class Solution {
        public String strWithout3a3b(int a, int b) {
            if(a == 0 && b == 0) {
                return "";
            }

            if(a >= b) {
                return strWithout3a3bHelper(a, b, 'a', 'b');
            } else {
                return strWithout3a3bHelper(b, a, 'b', 'a');
            }
        }

        private String strWithout3a3bHelper(int a, int b, char ac, char bc) {
            StringBuilder builder = new StringBuilder();

            while(a > b) {
                builder.append(ac);
                a--;

                if(a > 0) {
                    builder.append(ac);
                    a--;
                }

                if(b > 0) {
                    builder.append(bc);
                    b--;
                }
            }

            while(a > 0) {
                builder.append(ac);
                builder.append(bc);

                a--;
            }

            return builder.toString();
        }
    }

    class Solution_Incorrect {
        public String strWithout3a3b(int a, int b) {
            if(a == 0 && b == 0) {
                return "";
            }

            int c = a > b ? a : b;
            char cc = a > b ? 'a' : 'b';

            int d = a > b ? b : a;
            char dc = a > b ? 'b' : 'a';

            StringBuilder builder = new StringBuilder();
            int i = 0;
            int j = 0;

            while(i < c && j < d) {
                builder.append(cc);
                i++;

                if(i < c) {
                    builder.append(cc);
                    i++;
                }

                builder.append(dc);
                j++;
            }

            while(j < d) {
                builder.insert(0, dc);
                j++;
            }

            while(i < c) {
                builder.append(cc);
                i++;
            }

            return builder.toString();
        }
    }

// 1
// 2

// 4
// 1

// 6
// 6
}

//    984. String Without AAA or BBB
//    Medium
//    Given two integers a and b, return any string s such that:
//
//    s has length a + b and contains exactly a 'a' letters, and exactly b 'b' letters,
//    The substring 'aaa' does not occur in s, and
//    The substring 'bbb' does not occur in s.
//
//
//    Example 1:
//
//    Input: a = 1, b = 2
//    Output: "abb"
//    Explanation: "abb", "bab" and "bba" are all correct answers.
//    Example 2:
//
//    Input: a = 4, b = 1
//    Output: "aabaa"
//
//
//    Constraints:
//
//    0 <= a, b <= 100
//    It is guaranteed such an s exists for the given a and b.