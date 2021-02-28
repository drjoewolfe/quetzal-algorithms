package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MaximumFrequencyStack {
    Map<Integer, Integer> frequency;
    Map<Integer, Stack<Integer>> stackMap;
    int maxFrequency;

    public MaximumFrequencyStack() {
        frequency = new HashMap<>();
        stackMap = new HashMap<>();

        maxFrequency = 0;
    }

    public void push(int x) {
        frequency.put(x, frequency.getOrDefault(x, 0) + 1);
        int count = frequency.get(x);

        stackMap.putIfAbsent(count, new Stack<>());
        stackMap.get(count).push(x);

        if (count > maxFrequency) {
            maxFrequency = count;
        }
    }

    public int pop() {
        if (maxFrequency == 0) {
            throw new RuntimeException("Stack is empty");
        }

        int x = stackMap.get(maxFrequency).pop();
        frequency.put(x, frequency.get(x) - 1);

        if (stackMap.get(maxFrequency).size() == 0) {
            maxFrequency--;
        }

        return x;
    }
}

//    895. Maximum Frequency Stack
//    Hard
//    Implement FreqStack, a class which simulates the operation of a stack-like data structure.
//
//    FreqStack has two functions:
//
//    push(int x), which pushes an integer x onto the stack.
//    pop(), which removes and returns the most frequent element in the stack.
//    If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.
//
//
//    Example 1:
//
//    Input:
//    ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
//    [[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
//    Output: [null,null,null,null,null,null,null,5,7,5,4]
//    Explanation:
//    After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:
//
//    pop() -> returns 5, as 5 is the most frequent.
//    The stack becomes [5,7,5,7,4].
//
//    pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
//    The stack becomes [5,7,5,4].
//
//    pop() -> returns 5.
//    The stack becomes [5,7,4].
//
//    pop() -> returns 4.
//    The stack becomes [5,7].
//
//
//    Note:
//
//    Calls to FreqStack.push(int x) will be such that 0 <= x <= 10^9.
//    It is guaranteed that FreqStack.pop() won't be called if the stack has zero elements.
//    The total number of FreqStack.push calls will not exceed 10000 in a single test case.
//    The total number of FreqStack.pop calls will not exceed 10000 in a single test case.
//    The total number of FreqStack.push and FreqStack.pop calls will not exceed 150000 across all test cases.
