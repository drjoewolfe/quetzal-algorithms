package org.jwolfe.quetzal.algorithms.lc;

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackUsingQueues {
    class MyStack {
        Queue<Integer> queue;

        /** Initialize your data structure here. */
        public MyStack() {
            queue = new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            queue.offer(x);
            for(int i = 0; i < queue.size() - 1; i++) {
                queue.offer(queue.poll());
            }
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return queue.poll();
        }

        /** Get the top element. */
        public int top() {
            return queue.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue.isEmpty();
        }
    }

    class MyStack_Correct_1 {
        Queue<Integer> queue1;
        Queue<Integer> queue2;

        Queue<Integer> main;
        Queue<Integer> backing;

        int top;

        /** Initialize your data structure here. */
        public MyStack_Correct_1() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();

            main = queue1;
            backing = queue2;
        }

        /** Push element x onto stack. */
        public void push(int x) {
            main.offer(x);
            top = x;
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            while(main.size() != 1) {
                top = main.poll();
                backing.offer(top);
            }

            int val = main.poll();
            swapMain();

            return val;
        }

        /** Get the top element. */
        public int top() {
            return top;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return main.isEmpty();
        }

        private void swapMain() {
            var temp = main;
            main = backing;
            backing = temp;
        }
    }

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
}

//    225. Implement Stack using Queues
//    Easy
//
//    Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).
//
//    Implement the MyStack class:
//
//    void push(int x) Pushes element x to the top of the stack.
//    int pop() Removes the element on the top of the stack and returns it.
//    int top() Returns the element on the top of the stack.
//    boolean empty() Returns true if the stack is empty, false otherwise.
//    Notes:
//
//    You must use only standard operations of a queue, which means that only push to back, peek/pop from front, size and is empty operations are valid.
//    Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque (double-ended queue) as long as you use only a queue's standard operations.
//
//
//    Example 1:
//
//    Input
//    ["MyStack", "push", "push", "top", "pop", "empty"]
//    [[], [1], [2], [], [], []]
//    Output
//    [null, null, null, 2, 2, false]
//
//    Explanation
//    MyStack myStack = new MyStack();
//    myStack.push(1);
//    myStack.push(2);
//    myStack.top(); // return 2
//    myStack.pop(); // return 2
//    myStack.empty(); // return False
//
//
//    Constraints:
//
//    1 <= x <= 9
//    At most 100 calls will be made to push, pop, top, and empty.
//    All the calls to pop and top are valid.
//
//
//    Follow-up: Can you implement the stack using only one queue?