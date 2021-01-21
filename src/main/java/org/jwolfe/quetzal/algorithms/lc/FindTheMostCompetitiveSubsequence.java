package org.jwolfe.quetzal.algorithms.lc;

import java.util.Stack;

public class FindTheMostCompetitiveSubsequence {
    public int[] mostCompetitive(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k < 1) {
            return new int[0];
        }

        int n = nums.length;
        int maxIgnore = n - k;
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < nums.length; i++) {
            while(!stack.isEmpty() && stack.peek() > nums[i] && maxIgnore > 0) {
                stack.pop();
                maxIgnore--;
            }

            stack.push(nums[i]);
        }

        while(stack.size() > k) {
            stack.pop();
        }

        int[] result = new int[k];
        for(int i = k - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }
}
