package org.jwolfe.quetzal.algorithms;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class StackAlgorithmsTest {
    @Test
    void convertInfixToPostfix() {
        String infixExpression;
        String postFixExpression;
        String expectedPostFixExpression;

        infixExpression = "a";
        expectedPostFixExpression = "a";
        postFixExpression = StackAlgorithms.convertInfixToPostfix(infixExpression);
        System.out.println("infix: " + infixExpression);
        System.out.println("postfix: " + postFixExpression);
        assertEquals(expectedPostFixExpression, postFixExpression);

        infixExpression = "a+b";
        expectedPostFixExpression = "ab+";
        postFixExpression = StackAlgorithms.convertInfixToPostfix(infixExpression);
        System.out.println("infix: " + infixExpression);
        System.out.println("postfix: " + postFixExpression);
        assertEquals(expectedPostFixExpression, postFixExpression);

        infixExpression = "a+b+c";
        expectedPostFixExpression = "ab+c+";
        postFixExpression = StackAlgorithms.convertInfixToPostfix(infixExpression);
        System.out.println("infix: " + infixExpression);
        System.out.println("postfix: " + postFixExpression);
        assertEquals(expectedPostFixExpression, postFixExpression);

        infixExpression = "a+b*c+d";
        expectedPostFixExpression = "abc*+d+";
        postFixExpression = StackAlgorithms.convertInfixToPostfix(infixExpression);
        System.out.println("infix: " + infixExpression);
        System.out.println("postfix: " + postFixExpression);
        assertEquals(expectedPostFixExpression, postFixExpression);

        infixExpression = "(a+b)";
        expectedPostFixExpression = "ab+";
        postFixExpression = StackAlgorithms.convertInfixToPostfix(infixExpression);
        System.out.println("infix: " + infixExpression);
        System.out.println("postfix: " + postFixExpression);
        assertEquals(expectedPostFixExpression, postFixExpression);

        infixExpression = "a+b*(c^d-e)^(f+g*h)-i";
        expectedPostFixExpression = "abcd^e-fgh*+^*+i-";
        postFixExpression = StackAlgorithms.convertInfixToPostfix(infixExpression);
        System.out.println("infix: " + infixExpression);
        System.out.println("postfix: " + postFixExpression);
        assertEquals(expectedPostFixExpression, postFixExpression);
    }

    @Test
    void evaluatePostfixExpression() {
        String postfixExpression;
        int result;

        postfixExpression = "231*+9-";
        result = StackAlgorithms.evaluatePostfixExpression(postfixExpression);
        System.out.println(postfixExpression + " -> " + result);
        assertEquals(-4, result);
    }

    @Test
    void reverse() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(Arrays.toString(stack.toArray()));
        StackAlgorithms.reverse(stack);
        System.out.println(Arrays.toString(stack.toArray()));
    }

    @Test
    void sort() {
        Stack<Integer> stack = new Stack<>();
        stack.push(30);
        stack.push(-5);
        stack.push(18);
        stack.push(14);
        stack.push(3);
        System.out.println(Arrays.toString(stack.toArray()));
        StackAlgorithms.sort(stack);
        System.out.println(Arrays.toString(stack.toArray()));
    }
}