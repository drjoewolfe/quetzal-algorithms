package org.jwolfe.quetzal.algorithms.lc;

import java.util.Stack;

public class BasicCalculator {
    class Solution {
        public int calculate(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int n = s.length();

            int result = 0;
            int value = 0;
            int sign = 1;
            Stack<Integer> stack = new Stack<>();

            for(int i = 0; i < n; i++) {
                char c = s.charAt(i);

                if(Character.isDigit(c)) {
                    value = (value * 10) + (c - '0');
                } else if(c == '+') {
                    result += (value * sign);
                    value = 0;
                    sign = 1;
                } else if(c == '-') {
                    result += (value * sign);
                    value = 0;
                    sign = -1;
                } else if(c == '(') {
                    stack.push(result);
                    stack.push(sign);
                    result = 0;
                    sign = 1;
                } else if(c == ')') {
                    result += (value * sign);
                    result *= stack.pop();
                    result += stack.pop();

                    value = 0;
                    sign = 1;
                }
            }

            result += (value * sign);
            return result;
        }
    }

    class Solution_Incorrect {
        public int calculate(String s) {
            if(s == null || s.length() == 0) {
                return 0;
            }

            int n = s.length();
            int i = 0;

            int result = 0;
            while(i < n) {
                int num1 = 0;
                while(i < n) {
                    char c = s.charAt(i);
                    if(c == ' ') {
                        i++;
                        continue;
                    }

                    if(!Character.isDigit(c)) {
                        break;
                    }

                    int val = c - '0';
                    num1 *= 10;
                    num1 += val;
                    i++;
                }

                char op = s.charAt(i++);

                int num2 = 0;
                while(i < n) {
                    char c = s.charAt(i);
                    if(c == ' ') {
                        i++;
                        continue;
                    }

                    if(!Character.isDigit(c)) {
                        break;
                    }

                    int val = c - '0';
                    num2 *= 10;
                    num2 += val;
                    i++;
                }

                if(op == '+') {
                    result += (num1 + num2);
                } else if(op == '-') {
                    result += (num1 - num2);
                }

                System.out.println(num1 + ", " + op + ", " + num2 + ", " + result);
            }

            return result;
        }
    }

// "1 + 1"
// " 2-1 + 2 "
// "(1+(4+5+2)-3)+(6+8)"
}

//    224. Basic Calculator
//    Hard
//    Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
//
//    Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
//
//
//
//    Example 1:
//
//    Input: s = "1 + 1"
//    Output: 2
//    Example 2:
//
//    Input: s = " 2-1 + 2 "
//    Output: 3
//    Example 3:
//
//    Input: s = "(1+(4+5+2)-3)+(6+8)"
//    Output: 23
//
//
//    Constraints:
//
//    1 <= s.length <= 3 * 105
//    s consists of digits, '+', '-', '(', ')', and ' '.
//    s represents a valid expression.
//    '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
//    '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
//    There will be no two consecutive operators in the input.
//    Every number and running calculation will fit in a signed 32-bit integer.