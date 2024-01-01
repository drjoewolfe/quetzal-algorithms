package org.jwolfe.quetzal.algorithms.lc;

import jdk.jshell.spi.ExecutionControl;

import java.util.List;
import java.util.Stack;

public class MiniParser {
    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     * public interface NestedInteger {
     *     // Constructor initializes an empty nested list.
     *     public NestedInteger();
     *
     *     // Constructor initializes a single integer.
     *     public NestedInteger(int value);
     *
     *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
     *     public boolean isInteger();
     *
     *     // @return the single integer that this NestedInteger holds, if it holds a single integer
     *     // Return null if this NestedInteger holds a nested list
     *     public Integer getInteger();
     *
     *     // Set this NestedInteger to hold a single integer.
     *     public void setInteger(int value);
     *
     *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
     *     public void add(NestedInteger ni);
     *
     *     // @return the nested list that this NestedInteger holds, if it holds a nested list
     *     // Return empty list if this NestedInteger holds a single integer
     *     public List<NestedInteger> getList();
     * }
     */
    class Solution {
        public NestedInteger deserialize(String s) throws ExecutionControl.NotImplementedException {
            if(s == null || s.length() == 0) {
                return null;
            }

            Stack<NestedInteger> stack = new Stack<>();
            String number = "";

            NestedInteger last = null;
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if(c == '[') {
                    var element = new NestedInteger();
                    if(!stack.isEmpty()) {
                        stack.peek().add(element);
                    }

                    stack.add(element);
                } else if(c == ']') {
                    if(number != "") {
                        stack.peek().add(new NestedInteger(Integer.valueOf(number)));
                        number = "";
                    }

                    last = stack.pop();
                } else if(c == ',') {
                    if(number != "") {
                        stack.peek().add(new NestedInteger(Integer.valueOf(number)));
                        number = "";
                    }
                } else {
                    number += c;
                }
            }

            if(last != null) {
                return last;
            }

            return new NestedInteger(Integer.valueOf(number));
        }
    }

    class Solution_Incorrect {
        public NestedInteger deserialize(String s) throws ExecutionControl.NotImplementedException {
            if(s == null || s.length() == 0) {
                return null;
            }

            return deserialize(s, 0, s.length() - 1);
        }

        private NestedInteger deserialize(String s, int start, int end) throws ExecutionControl.NotImplementedException {
            // System.out.println(s + ", " + start + ", " + end + ", " + s.substring(start, end + 1));

            NestedInteger ni = new NestedInteger();
            if(s.charAt(start) == '[') {
                int index = s.indexOf(",", start);
                // System.out.println(index);
                if(index == -1) {
                    var child = deserialize(s, start + 1, end);
                    System.out.println("\t" + child);
                    ni.add(
                            child
                    );
                    // ni.setInteger(getNumber(s, start + 1, end));
                } else {
                    // ni.setInteger(getNumber(s, start + 1, index - 1));
                    ni.add(
                            new NestedInteger(getNumber(s, start + 1, index - 1))
                    );
                    var child = deserialize(s, index + 1, end - 1);
                    System.out.println("\t" + child);
                    ni.add(
                            child
                    );

                    System.out.println(ni.getInteger() + " : " + ni.getList());
                }
            } else {
                ni.setInteger(getNumber(s, start, end));
            }

            System.out.println(ni.getInteger() + " : " + ni.getList());
            return ni;
        }

        private int getNumber(String s, int left, int right) {
            int n = 0;
            for(int i = left; i <= right; i++) {
                char c = s.charAt(i);
                if(c == ']') {
                    break;
                }

                n *= 10;
                n += (c - '0');
            }

            // System.out.println(n);
            return n;
        }

        private int getNumber(String s) {
            int n = 0;
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                n *= 10;
                n += (c - '0');
            }

            return n;
        }
    }


    // Dummy Implementation
    class NestedInteger {
        public NestedInteger() {

        }

        public NestedInteger(int val) {

        }

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() throws ExecutionControl.NotImplementedException {
            throw new ExecutionControl.NotImplementedException("Not Implemented");
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public  Integer getInteger() throws ExecutionControl.NotImplementedException {
            throw new ExecutionControl.NotImplementedException("Not Implemented");
        }

        // Set this NestedInteger to hold a single integer.
        public  void setInteger(int value) throws ExecutionControl.NotImplementedException {
            throw new ExecutionControl.NotImplementedException("Not Implemented");
        }

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) throws ExecutionControl.NotImplementedException {
            throw new ExecutionControl.NotImplementedException("Not Implemented");
        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList() throws ExecutionControl.NotImplementedException {
            throw new ExecutionControl.NotImplementedException("Not Implemented");
        }
    }

// "324"
// "[123,[456,[789]]]"
}

//    385. Mini Parser
//    Medium
//    Given a string s represents the serialization of a nested list, implement a parser to deserialize it and return the deserialized NestedInteger.
//
//    Each element is either an integer or a list whose elements may also be integers or other lists.
//
//
//
//    Example 1:
//
//    Input: s = "324"
//    Output: 324
//    Explanation: You should return a NestedInteger object which contains a single integer 324.
//    Example 2:
//
//    Input: s = "[123,[456,[789]]]"
//    Output: [123,[456,[789]]]
//    Explanation: Return a NestedInteger object containing a nested list with 2 elements:
//    1. An integer containing value 123.
//    2. A nested list containing two elements:
//    i.  An integer containing value 456.
//    ii. A nested list with one element:
//    a. An integer containing value 789
//
//
//    Constraints:
//
//    1 <= s.length <= 5 * 104
//    s consists of digits, square brackets "[]", negative sign '-', and commas ','.
//    s is the serialization of valid NestedInteger.
//    All the values in the input are in the range [-106, 106].