package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class ValidateStackSequences {
    class Solution {
        public boolean validateStackSequences(int[] pushed, int[] popped) {
            if(pushed == null && popped == null) {
                return true;
            }

            if(pushed == null || popped == null || pushed.length != popped.length) {
                return false;
            }

            if(pushed.length == 0) {
                return true;
            }

            Stack<Integer> stack = new Stack<>();
            int i = 0;
            for(int j = 0; j < popped.length; j++) {
                int val = popped[j];
                if(!stack.isEmpty() && stack.peek() == val) {
                    stack.pop();
                } else {
                    while(i < pushed.length && pushed[i] != val) {
                        stack.push(pushed[i]);
                        i++;
                    }

                    if(i == pushed.length) {
                        return false;
                    }

                    i++;
                }
            }

            return true;
        }
    }

    class Solution_Correct_1 {
        public boolean validateStackSequences(int[] pushed, int[] popped) {
            if(pushed == null && popped == null) {
                return true;
            }

            Stack<Integer> stack = new Stack<>();
            Set<Integer> set = new HashSet<>();

            int i = 0;
            for(int j = 0; j < popped.length; j++) {
                int a = popped[j];
                if(!stack.isEmpty() && stack.peek() == a) {
                    stack.pop();
                    continue;
                } else if(set.contains(a)) {
                    return false;
                } else {
                    while(i < pushed.length && pushed[i] != a) {
                        stack.push(pushed[i]);
                        set.add(pushed[i]);
                        i++;
                    }

                    if(i == pushed.length) {
                        return false;
                    }

                    i++;
                }
            }

            return true;
        }
    }
}

//    946. Validate Stack Sequences
//    Medium
//    Given two sequences pushed and popped with distinct values, return true if and only if this could have been the result of a sequence of push and pop operations on an initially empty stack.
//
//
//
//    Example 1:
//
//    Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
//    Output: true
//    Explanation: We might do the following sequence:
//    push(1), push(2), push(3), push(4), pop() -> 4,
//    push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
//    Example 2:
//
//    Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
//    Output: false
//    Explanation: 1 cannot be popped before 2.
//
//
//    Constraints:
//
//    0 <= pushed.length == popped.length <= 1000
//    0 <= pushed[i], popped[i] < 1000
//    pushed is a permutation of popped.
//    pushed and popped have distinct values.