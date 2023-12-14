package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ExpressionAddOperators {
    class Solution {
        public List<String> addOperators(String num, int target) {
            List<String> results = new ArrayList<>();
            if(num == null || num.length() == 0) {
                return results;
            }

            generateExpressions(num, target, 0, 0, 0, 0, new ArrayList<>(), results);
            return results;
        }

        private void generateExpressions(String num, int targetValue, long currentValue, int index, long currentOperand, long previousOperand, List<String> expressionFragments, List<String> results) {
            if(index == num.length()) {
                if(currentValue == targetValue && currentOperand == 0) {
                    StringBuilder builder = new StringBuilder();
                    for(int i = 1; i < expressionFragments.size(); i++) {
                        String fragment = expressionFragments.get(i);
                        builder.append(fragment);
                    }

                    results.add(builder.toString());
                }

                return;
            }

            char c = num.charAt(index);
            int cv = c - '0';

            currentOperand = currentOperand * 10 + cv;
            if(currentOperand > 0) {
                generateExpressions(num, targetValue, currentValue, index + 1, currentOperand, previousOperand, expressionFragments, results);
            }

            String currentOperandString = String.valueOf(currentOperand);

            // Addition
            expressionFragments.add("+");
            expressionFragments.add(currentOperandString);
            generateExpressions(num, targetValue, currentValue + currentOperand, index + 1, 0, currentOperand, expressionFragments, results);
            expressionFragments.remove(expressionFragments.size() - 1);
            expressionFragments.remove(expressionFragments.size() - 1);

            if(expressionFragments.size() > 0) {
                // Substraction
                expressionFragments.add("-");
                expressionFragments.add(currentOperandString);
                generateExpressions(num, targetValue, currentValue - currentOperand, index + 1, 0, -currentOperand, expressionFragments, results);
                expressionFragments.remove(expressionFragments.size() - 1);
                expressionFragments.remove(expressionFragments.size() - 1);


                // Multiplication
                expressionFragments.add("*");
                expressionFragments.add(currentOperandString);
                generateExpressions(num, targetValue, currentValue - previousOperand + (previousOperand * currentOperand), index + 1, 0, previousOperand * currentOperand, expressionFragments, results);
                expressionFragments.remove(expressionFragments.size() - 1);
                expressionFragments.remove(expressionFragments.size() - 1);
            }
        }

        private void printFragments(List<String> expressionFragments) {
            StringBuilder builder = new StringBuilder();
            for(String fragment : expressionFragments) {
                builder.append(fragment);
            }

            System.out.println(builder.toString());
        }
    }

    class Solution_Incorrect_2 {
        public List<String> addOperators(String num, int target) {
            List<String> combinations = new ArrayList<>();
            if(num == null || num.length() == 0) {
                return combinations;
            }

            int a = num.charAt(0) - '0';
            StringBuilder builder = new StringBuilder();
            builder.append(a);
            generateExpressions(num, num.length(), 1, target, builder, combinations);

            return combinations;
        }

        private void generateExpressions(String num, int n, int index, int target, StringBuilder currentCombination, List<String> combinations) {
            if(index == n) {
                int current = evaluateExpression(currentCombination);

                System.out.println(currentCombination.toString() + " = " + current + " ?= " + target);
                if(current == target) {
                    combinations.add(currentCombination.toString());
                }

                return;
            }

            int a = num.charAt(index) - '0';

            // Try +
            currentCombination.append("+" + a);
            generateExpressions(num, n, index + 1, target, currentCombination, combinations);
            currentCombination.delete(currentCombination.length() - 2, currentCombination.length());

            // Try -
            currentCombination.append("-" + a);
            generateExpressions(num, n, index + 1, target, currentCombination, combinations);
            currentCombination.delete(currentCombination.length() - 2, currentCombination.length());

            // Try *
            currentCombination.append("*" + a);
            generateExpressions(num, n, index + 1, target, currentCombination, combinations);
            currentCombination.delete(currentCombination.length() - 2, currentCombination.length());
        }

        private int evaluateExpression(StringBuilder expression) {
            Stack<Integer> operandStack = new Stack<>();
            Stack<Character> operatorStack = new Stack<>();

            for(int i = 0; i < expression.length(); i++) {
                char c = expression.charAt(i);

                if(c >= '0' && c <= '9') {
                    operandStack.push(c - '0');
                } else {
                    // Operator
                    while(!operatorStack.isEmpty() && operatorStack.peek() == '*') {
                        int a = operandStack.pop();
                        int b = operandStack.pop();

                        operandStack.push(a * b);
                        operatorStack.pop();
                    }

                    operatorStack.push(c);
                }
            }

            while(!operatorStack.isEmpty()) {
                char o = operatorStack.pop();
                int b = operandStack.pop();
                int a = operandStack.pop();

                System.out.println("\t" + a + " " + o + " " + b);

                if(o == '+') {
                    operandStack.push(a + b);
                } else {
                    operandStack.push(a - b);
                }
            }

            return operandStack.pop();
        }
    }

    class Solution_Incorrect {
        public List<String> addOperators(String num, int target) {
            List<String> combinations = new ArrayList<>();
            if(num == null || num.length() == 0) {
                return combinations;
            }

            int a = num.charAt(0) - '0';
            StringBuilder builder = new StringBuilder();
            builder.append(a);
            generateExpressions(num, num.length(), 1, a, target, builder, combinations);

            return combinations;
        }

        private void generateExpressions(String num, int n, int index, int current, int target, StringBuilder currentCombination, List<String> combinations) {
            if(index == n) {
                System.out.println(currentCombination.toString() + " = " + current + " ?= " + target);
                if(current == target) {
                    combinations.add(currentCombination.toString());
                }

                return;
            }

            int a = num.charAt(index) - '0';

            // Try +
            currentCombination.append("+" + a);
            generateExpressions(num, n, index + 1, current + a, target, currentCombination, combinations);
            currentCombination.delete(currentCombination.length() - 2, currentCombination.length());

            // Try -
            currentCombination.append("-" + a);
            generateExpressions(num, n, index + 1, current - a, target, currentCombination, combinations);
            currentCombination.delete(currentCombination.length() - 2, currentCombination.length());

            // Try *
            currentCombination.append("*" + a);
            generateExpressions(num, n, index + 1, current * a, target, currentCombination, combinations);
            currentCombination.delete(currentCombination.length() - 2, currentCombination.length());
        }
    }

// "123"
// 6

// "232"
// 8

// "2147483648"
// -2147483648
}

//    282. Expression Add Operators
//    Hard
//    Given a string num that contains only digits and an integer target, return all possibilities to insert the binary operators '+', '-', and/or '*' between the digits of num so that the resultant expression evaluates to the target value.
//
//    Note that operands in the returned expressions should not contain leading zeros.
//
//
//
//    Example 1:
//
//    Input: num = "123", target = 6
//    Output: ["1*2*3","1+2+3"]
//    Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.
//    Example 2:
//
//    Input: num = "232", target = 8
//    Output: ["2*3+2","2+3*2"]
//    Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.
//    Example 3:
//
//    Input: num = "3456237490", target = 9191
//    Output: []
//    Explanation: There are no expressions that can be created from "3456237490" to evaluate to 9191.
//
//
//    Constraints:
//
//    1 <= num.length <= 10
//    num consists of only digits.
//    -231 <= target <= 231 - 1
