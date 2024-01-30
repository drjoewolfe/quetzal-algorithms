package org.jwolfe.quetzal.algorithms.lc;

import java.util.Stack;

public class EvaluateReversePolishNotation {
    class Solution {
        public int evalRPN(String[] tokens) {
            if (tokens == null || tokens.length == 0) {
                return 0;
            }

            Stack<Integer> stack = new Stack<>();

            for (String token : tokens) {
                if (token.equals("+")) {
                    var b = stack.pop();
                    var a = stack.pop();

                    var c = a + b;
                    stack.push(c);
                } else if (token.equals("-")) {
                    var b = stack.pop();
                    var a = stack.pop();

                    var c = a - b;
                    stack.push(c);
                } else if (token.equals("*")) {
                    var b = stack.pop();
                    var a = stack.pop();

                    var c = a * b;
                    stack.push(c);
                } else if (token.equals("/")) {
                    var b = stack.pop();
                    var a = stack.pop();

                    var c = a / b;
                    stack.push(c);
                } else {
                    stack.push(Integer.valueOf(token));
                }
            }

            return stack.pop();
        }
    }

    class Solution_Correct_1 {
        public int evalRPN(String[] tokens) {
            if (tokens == null || tokens.length == 0) {
                return 0;
            }

            Stack<Integer> stack = new Stack<>();

            for (String t : tokens) {
                if (t.equals("+")) {
                    if (stack.size() < 2) {
                        return 0;
                    }

                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a + b);
                } else if (t.equals("-")) {
                    if (stack.size() < 2) {
                        return 0;
                    }

                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a - b);
                } else if (t.equals("*")) {
                    if (stack.size() < 2) {
                        return 0;
                    }

                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a * b);
                } else if (t.equals("/")) {
                    if (stack.size() < 2) {
                        return 0;
                    }

                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a / b);
                } else {
                    int a = Integer.valueOf(t);
                    stack.push(a);
                }
            }

            if (stack.size() > 1) {
                return 0;
            }

            return stack.pop();
        }
    }

    class Solution_A2 {
        public int evalRPN(String[] tokens) {
            if (tokens == null || tokens.length == 0) {
                return 0;
            }

            Stack<Integer> stack = new Stack<>();
            for (String token : tokens) {
                int a;
                int b;
                switch (token) {
                    case "+":
                        b = stack.pop();
                        a = stack.pop();
                        stack.push(a + b);
                        break;
                    case "-":
                        b = stack.pop();
                        a = stack.pop();
                        stack.push(a - b);
                        break;
                    case "*":
                        b = stack.pop();
                        a = stack.pop();
                        stack.push(a * b);
                        break;
                    case "/":
                        b = stack.pop();
                        a = stack.pop();
                        stack.push(a / b);
                        break;
                    default:
                        stack.push(Integer.valueOf(token));
                        break;
                }
            }

            if (stack.size() != 1) {
                throw new RuntimeException("Invalid input");
            }

            return stack.pop();
        }
    }
}

//    150. Evaluate Reverse Polish Notation
//    Medium
//    Evaluate the value of an arithmetic expression in Reverse Polish Notation.
//
//    Valid operators are +, -, *, and /. Each operand may be an integer or another expression.
//
//    Note that division between two integers should truncate toward zero.
//
//    It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate to a result, and there will not be any division by zero operation.
//
//
//
//    Example 1:
//
//    Input: tokens = ["2","1","+","3","*"]
//    Output: 9
//    Explanation: ((2 + 1) * 3) = 9
//    Example 2:
//
//    Input: tokens = ["4","13","5","/","+"]
//    Output: 6
//    Explanation: (4 + (13 / 5)) = 6
//    Example 3:
//
//    Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
//    Output: 22
//    Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
//    = ((10 * (6 / (12 * -11))) + 17) + 5
//    = ((10 * (6 / -132)) + 17) + 5
//    = ((10 * 0) + 17) + 5
//    = (0 + 17) + 5
//    = 17 + 5
//    = 22
//
//
//    Constraints:
//
//    1 <= tokens.length <= 104
//    tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
