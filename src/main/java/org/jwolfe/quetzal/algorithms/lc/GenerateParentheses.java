package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> combinations = new ArrayList<>();
            if(n < 1) {
                return combinations;
            }

            generateParanthesisCombinations(n, 0, 0, new StringBuilder(), combinations);
            return combinations;
        }

        private void generateParanthesisCombinations(int n, int openCount, int closeCount, StringBuilder builder, List<String> combinations) {
            if(builder.length() == n * 2) {
                combinations.add(builder.toString());
                return;
            }

            if(openCount < n) {
                builder.append("(");
                generateParanthesisCombinations(n, openCount + 1, closeCount, builder,combinations);
                builder.deleteCharAt(builder.length() - 1);
            }

            if(closeCount < openCount) {
                builder.append(")");
                generateParanthesisCombinations(n, openCount, closeCount + 1, builder,combinations);
                builder.deleteCharAt(builder.length() - 1);
            }
        }
    }

    class Solution_Correct_2 {
        public List<String> generateParenthesis(int n) {
            List<String> combinations = new ArrayList<>();
            if(n <= 0) {
                return combinations;
            }

            generateParenthesis(new StringBuilder(), n, 0, 0, combinations);
            return combinations;
        }

        private void generateParenthesis(StringBuilder builder, int n, int open, int close, List<String> combinations) {
            if(builder.length() == 2 * n) {
                combinations.add(builder.toString());
                return;
            }

            // Open bracket
            if(open < n) {
                builder.append("(");
                generateParenthesis(builder, n, open + 1, close, combinations);

                builder.deleteCharAt(builder.length() - 1);
            }

            // Close bracket
            if(close < open) {
                builder.append(")");
                generateParenthesis(builder, n, open, close + 1, combinations);

                builder.deleteCharAt(builder.length() - 1);
            }
        }
    }
}

//    22. Generate Parentheses
//    Medium
//    Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
//
//
//
//    Example 1:
//
//    Input: n = 3
//    Output: ["((()))","(()())","(())()","()(())","()()()"]
//    Example 2:
//
//    Input: n = 1
//    Output: ["()"]
//
//
//    Constraints:
//
//    1 <= n <= 8