package org.jwolfe.quetzal.algorithms;

import java.util.Stack;

public class StackAlgorithms {
    public static String convertInfixToPostfix(String infixExpression) {
        StringBuilder postfixExpression = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < infixExpression.length(); i++) {
            char c = infixExpression.charAt(i);
            if(Character.isLetterOrDigit(c)) {
                postfixExpression.append(c);
            }
            else if(isOperator(c)) {
                if (c == '(') {
                    stack.push(c);
                } else if (c == ')') {
                    while(!stack.isEmpty()
                            && stack.peek() != '(') {
                        postfixExpression.append(stack.pop());
                    }

                    if(!stack.isEmpty()
                            && stack.peek() != '(') {
                        // Invalid expression
                        return null;
                    }

                    stack.pop();
                } else {
                    int operatorPrecedence = getOperatorPrecedence(c);
                    while (!stack.isEmpty()
                            && operatorPrecedence <= getOperatorPrecedence(stack.peek())) {
                        postfixExpression.append(stack.pop());
                    }

                    stack.push(c);
                }
            }
        }

        while (!stack.isEmpty()) {
            postfixExpression.append(stack.pop());
        }

        return postfixExpression.toString();
    }

    private static boolean isOperator(char c) {
        return (c == '+'
                || c == '-'
                || c == '*'
                || c == '/'
                || c == '^'
                || c == '('
                || c == ')');
    }

    private static int getOperatorPrecedence(char operator) {
        switch (operator) {
            case '+': return 1;
            case '-': return 1;
            case '*': return 2;
            case '/': return 2;
            case '^': return 3;
            case '(': return 0;
            case ')': return 0;
        }

        return -1;
    }

    public static int evaluatePostfixExpression(String postfixExpression) {
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < postfixExpression.length(); i++) {
            char c = postfixExpression.charAt(i);
            if(Character.isDigit(c)) {
                stack.push(c - '0');
            }
            else {
                int v1 = stack.pop();
                int v2 = stack.pop();

                switch (c) {
                    case '+': stack.push(v2 + v1);
                        break;
                    case '-': stack.push(v2 - v1);
                        break;
                    case '*': stack.push(v2 * v1);
                        break;
                    case '/': stack.push(v2 / v1);
                        break;
                }
            }
        }

        return stack.pop();
    }

    public static void reverse(Stack<Integer> stack) {
        if(stack.isEmpty()) {
            return;
        }

        int value = stack.pop();
        reverse(stack);
        insertAtBottom(stack, value);
    }

    private static void insertAtBottom(Stack<Integer> stack, int value) {
        if(stack.isEmpty()) {
            stack.push(value);
        }
        else {
            int previous = stack.pop();
            insertAtBottom(stack, value);
            stack.push(previous);
        }
    }

    public static void sort(Stack<Integer> stack) {
        if(stack.isEmpty()) {
            return;
        }

        int value = stack.pop();
        sort(stack);
        sortedInsert(stack, value);
    }

    private static void sortedInsert(Stack<Integer> stack, int value) {
        if(stack.isEmpty()
                || stack.peek() > value) {
            stack.push(value);
            return;
        }

        int previous = stack.pop();
        sortedInsert(stack, value);
        stack.push(previous);
    }
}
