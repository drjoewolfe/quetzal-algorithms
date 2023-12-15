package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveInvalidParentheses {
    class Solution {
        private Set<String> candidateExpressions;
        private int minRemovedCount;

        public List<String> removeInvalidParentheses(String s) {
            List<String> results = new ArrayList<>();
            if(s == null || s.length() == 0) {
                return results;
            }

            candidateExpressions = new HashSet<>();
            minRemovedCount = Integer.MAX_VALUE;

            generateValidExpressionsWithMinRemovals(s, 0, 0, 0, 0, new StringBuilder());

            results.addAll(candidateExpressions);
            return results;
        }

        private void generateValidExpressionsWithMinRemovals(String s, int index, int leftCount, int rightCount, int currentRemovedCount, StringBuilder currentExpression) {
            if(index == s.length()) {
                if(leftCount == rightCount) {
                    // Valid expression
                    if(currentRemovedCount < minRemovedCount) {
                        reset();
                        minRemovedCount = currentRemovedCount;
                    }

                    if(currentRemovedCount == minRemovedCount) {
                        candidateExpressions.add(currentExpression.toString());
                    }
                }

                return;
            }

            char c = s.charAt(index);
            if(c != '(' && c != ')') {
                // Non bracket character. Include & continue
                currentExpression.append(c);
                generateValidExpressionsWithMinRemovals(s, index + 1, leftCount, rightCount, currentRemovedCount, currentExpression);
                currentExpression.deleteCharAt(currentExpression.length() - 1);
            } else {
                // Delete current bracket
                generateValidExpressionsWithMinRemovals(s, index + 1, leftCount, rightCount, currentRemovedCount + 1, currentExpression);

                // Include current bracket
                if(c == '(') {
                    currentExpression.append(c);
                    generateValidExpressionsWithMinRemovals(s, index + 1, leftCount + 1, rightCount, currentRemovedCount, currentExpression);
                    currentExpression.deleteCharAt(currentExpression.length() - 1);
                } else {
                    if(leftCount > rightCount) {
                        currentExpression.append(c);
                        generateValidExpressionsWithMinRemovals(s, index + 1, leftCount, rightCount + 1, currentRemovedCount, currentExpression);
                        currentExpression.deleteCharAt(currentExpression.length() - 1);
                    }
                }
            }
        }

        private void reset() {
            candidateExpressions.clear();
            minRemovedCount = Integer.MAX_VALUE;
        }
    }

// "()())()"
// ")("
}

//    301. Remove Invalid Parentheses
//    Hard
//    Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
//
//    Return a list of unique strings that are valid with the minimum number of removals. You may return the answer in any order.
//
//
//
//    Example 1:
//
//    Input: s = "()())()"
//    Output: ["(())()","()()()"]
//    Example 2:
//
//    Input: s = "(a)())()"
//    Output: ["(a())()","(a)()()"]
//    Example 3:
//
//    Input: s = ")("
//    Output: [""]
//
//
//    Constraints:
//
//    1 <= s.length <= 25
//    s consists of lowercase English letters and parentheses '(' and ')'.
//    There will be at most 20 parentheses in s.