package org.jwolfe.quetzal.algorithms.lc;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class FlattenNestedListIterator {
    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     * public interface NestedInteger {
     *
     *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
     *     public boolean isInteger();
     *
     *     // @return the single integer that this NestedInteger holds, if it holds a single integer
     *     // Return null if this NestedInteger holds a nested list
     *     public Integer getInteger();
     *
     *     // @return the nested list that this NestedInteger holds, if it holds a nested list
     *     // Return null if this NestedInteger holds a single integer
     *     public List<NestedInteger> getList();
     * }
     */
    public class NestedIterator implements Iterator<Integer> {

        private Stack<NestedInteger> stack;

        public NestedIterator(List<NestedInteger> nestedList) {
            stack = new Stack<>();

            for(int i = nestedList.size() - 1; i >= 0; i--) {
                stack.push(nestedList.get(i));
            }
        }

        @Override
        public Integer next() {
            processTopElement();

            if(!stack.isEmpty()) {
                return stack.pop().getInteger();
            }

            return null;
        }

        @Override
        public boolean hasNext() {
            processTopElement();

            return !stack.isEmpty();
        }

        private void processTopElement() {
            if(stack.isEmpty()) {
                return;
            }

            NestedInteger item = stack.peek();

            while(!item.isInteger()) {
                stack.pop();

                List<NestedInteger> nestedList = item.getList();
                for(int i = nestedList.size() - 1; i >= 0; i--) {
                    stack.push(nestedList.get(i));
                }

                if(stack.isEmpty()) {
                    break;
                }

                item = stack.peek();
            }
        }
    }

    public interface NestedInteger {

         // @return true if this NestedInteger holds a single integer, rather than a nested list.
         public boolean isInteger();

         // @return the single integer that this NestedInteger holds, if it holds a single integer
         // Return null if this NestedInteger holds a nested list
         public Integer getInteger();

         // @return the nested list that this NestedInteger holds, if it holds a nested list
         // Return null if this NestedInteger holds a single integer
         public List<NestedInteger> getList();
    }

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
}

//    341. Flatten Nested List Iterator
//    Medium
//    You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists. Implement an iterator to flatten it.
//
//    Implement the NestedIterator class:
//
//    NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with the nested list nestedList.
//    int next() Returns the next integer in the nested list.
//    boolean hasNext() Returns true if there are still some integers in the nested list and false otherwise.
//
//
//    Example 1:
//
//    Input: nestedList = [[1,1],2,[1,1]]
//    Output: [1,1,2,1,1]
//    Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
//    Example 2:
//
//    Input: nestedList = [1,[4,[6]]]
//    Output: [1,4,6]
//    Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
//
//
//    Constraints:
//
//    1 <= nestedList.length <= 500
//    The values of the integers in the nested list is in the range [-106, 106].