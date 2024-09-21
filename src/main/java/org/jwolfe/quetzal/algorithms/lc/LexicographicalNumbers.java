package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LexicographicalNumbers {
    class Solution {
        public List<Integer> lexicalOrder(int n) {
            if (n < 1) {
                return null;
            }

            List<Integer> results = new ArrayList<>();
            computeLexicalOrder(n, results);

            return results;
        }

        private void computeLexicalOrder(int n, List<Integer> results) {
            for (int i = 1; i <= 9; i++) {
                if (i > n) {
                    return;
                }

                results.add(i);
                computeLexicalOrderHelper(i, n, results);
            }
        }

        private void computeLexicalOrderHelper(int a, int n, List<Integer> results) {
            for (int i = 0; i <= 9; i++) {
                int val = a * 10 + i;
                if (val > n) {
                    return;
                }

                results.add(val);
                computeLexicalOrderHelper(val, n, results);
            }
        }
    }

    class Solution_Correct_1 {
        public List<Integer> lexicalOrder(int n) {
            if (n < 1) {
                return null;
            }

            List<Integer> results = new ArrayList<>();
            computeLexicalOrder(n, results);

            return results;
        }

        private void computeLexicalOrder(int n, List<Integer> results) {
            for (int i = 1; i <= 9; i++) {
                if (i > n) {
                    return;
                }

                results.add(i);
                computeLexicalOrderHelper(i, n, results);
            }
        }

        private void computeLexicalOrderHelper(int a, int n, List<Integer> results) {
            for (int i = 0; i <= 9; i++) {
                int v = a * 10 + i;
                if (v > n) {
                    return;
                }

                results.add(v);
                computeLexicalOrderHelper(v, n, results);
            }
        }
    }

    class Solution_NLogN {
        public List<Integer> lexicalOrder(int n) {
            if (n < 1) {
                return null;
            }

            List<String> list = new ArrayList<>();
            for (int a = 1; a <= n; a++) {
                list.add(Integer.toString(a));
            }

            Collections.sort(list);
            List<Integer> results = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                results.add(Integer.valueOf(list.get(i)));
            }

            return results;
        }
    }
}

//    386. Lexicographical Numbers
//    Medium
//    Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.
//
//    You must write an algorithm that runs in O(n) time and uses O(1) extra space.
//
//
//
//    Example 1:
//
//    Input: n = 13
//    Output: [1,10,11,12,13,2,3,4,5,6,7,8,9]
//    Example 2:
//
//    Input: n = 2
//    Output: [1,2]
//
//
//    Constraints:
//
//    1 <= n <= 5 * 104