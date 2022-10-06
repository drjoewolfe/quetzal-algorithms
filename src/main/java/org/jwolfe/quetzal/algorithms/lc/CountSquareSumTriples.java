package org.jwolfe.quetzal.algorithms.lc;

public class CountSquareSumTriples {
    class Solution {
        public int countTriples(int n) {
            if(n < 1) {
                return 0;
            }

            int count = 0;
            for(int a = 1; a <= n; a++) {
                for(int b = 1; b <= n; b++) {
                    int cs = a*a + b*b;
                    double c = Math.sqrt(cs);

                    if(c % 1 == 0 && c <= n) {
                        count++;
                    }
                }
            }

            return count;
        }
    }
}

//    1925. Count Square Sum Triples
//    Easy
//    A square triple (a,b,c) is a triple where a, b, and c are integers and a2 + b2 = c2.
//
//    Given an integer n, return the number of square triples such that 1 <= a, b, c <= n.
//
//
//
//    Example 1:
//
//    Input: n = 5
//    Output: 2
//    Explanation: The square triples are (3,4,5) and (4,3,5).
//    Example 2:
//
//    Input: n = 10
//    Output: 4
//    Explanation: The square triples are (3,4,5), (4,3,5), (6,8,10), and (8,6,10).
//
//
//    Constraints:
//
//    1 <= n <= 250