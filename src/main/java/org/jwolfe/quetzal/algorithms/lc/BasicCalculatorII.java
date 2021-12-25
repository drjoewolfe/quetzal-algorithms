package org.jwolfe.quetzal.algorithms.lc;

import java.util.Stack;

public class BasicCalculatorII {
    class Solution {
        public int calculate(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int n = s.length();
            int a = 0;
            int b = 0;
            char operator = '+';
            Stack<Integer> stack = new Stack<>();
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if(c >= '0' && c <= '9') {
                    // Number
                    a *= 10;
                    a += c - '0';
                }

                if((!Character.isDigit(c) && !Character.isWhitespace(c)) || i == n - 1) {
                    // Operator or end
                    switch(operator) {
                        case '+':
                            stack.push(a);
                            break;
                        case '-':
                            stack.push(a * -1);
                            break;
                        case '*':
                            b = stack.pop();
                            stack.push(b * a);
                            break;
                        case '/':
                            b = stack.pop();
                            stack.push(b / a);
                            break;
                    }

                    operator = c;
                    a = 0;
                }
            }

            int result = 0;
            while(!stack.isEmpty()) {
                result += stack.pop();
            }

            return result;
        }
    }

    class Solution_Correct_1 {
        public int calculate(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int n = s.length();

            char currOp = '+';
            int currNum = 0;
            Stack<Integer> stack = new Stack<>();

            for(int i = 0; i < n; i++) {
                char c = s.charAt(i);

                if(Character.isDigit(c)) {
                    // Integer
                    currNum *= 10;
                    currNum += (c - '0');
                }

                if((!Character.isDigit(c) && !Character.isWhitespace(c)) || i == n - 1) {
                    // Operator
                    if(currOp == '+') {
                        stack.push(currNum);
                    } else if(currOp == '-') {
                        stack.push((-1) * currNum);
                    } else if(currOp == '*') {
                        int product = currNum * stack.pop();
                        stack.push(product);
                    } else if(currOp == '/') {
                        int quotient = stack.pop() / currNum;
                        stack.push(quotient);
                    }

                    currNum = 0;
                    currOp = c;
                }
            }

            int result = 0;
            while(!stack.isEmpty()) {
                result += stack.pop();
            }

            return result;
        }
    }

// "3+2*2"
}

//    227. Basic Calculator II
//    Medium
//    Given a string s which represents an expression, evaluate this expression and return its value.
//
//    The integer division should truncate toward zero.
//
//    You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
//
//    Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
//
//
//
//    Example 1:
//
//    Input: s = "3+2*2"
//    Output: 7
//    Example 2:
//
//    Input: s = " 3/2 "
//    Output: 1
//    Example 3:
//
//    Input: s = " 3+5 / 2 "
//    Output: 5
//
//
//    Constraints:
//
//    1 <= s.length <= 3 * 105
//    s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
//    s represents a valid expression.
//    All the integers in the expression are non-negative integers in the range [0, 231 - 1].
//    The answer is guaranteed to fit in a 32-bit integer.