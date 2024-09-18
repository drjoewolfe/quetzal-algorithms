package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LargestNumber {
    class Solution {
        public String largestNumber(int[] nums) {
            if (nums == null || nums.length == 0) {
                return "0";
            }

            List<String> list = new ArrayList<>();
            for (int num : nums) {
                list.add(Integer.toString(num));
            }

            Collections.sort(list, (a, b) -> {
                String s1 = a + b;
                String s2 = b + a;

                return s2.compareTo(s1);
            });

            if (list.get(0).equals("0")) {
                return "0";
            }

            StringBuilder builder = new StringBuilder();
            for (String val : list) {
                builder.append(val);
            }

            return builder.toString();
        }
    }

    class Solution_Correct_1 {
        public String largestNumber(int[] nums) {
            if (nums == null || nums.length == 0) {
                return "";
            }

            int n = nums.length;
            List<String> list = new ArrayList<>();
            for (int a : nums) {
                list.add(Integer.toString(a));
            }

            Collections.sort(list, (a, b) -> {
                String s1 = a + b;
                String s2 = b + a;

                return s2.compareTo(s1);
            });

            if (list.get(0).equals("0")) {
                return "0";
            }

            StringBuilder builder = new StringBuilder();
            for (String str : list) {
                builder.append(str);
            }

            return builder.toString();
        }
    }
}

//    179. Largest Number
//    Medium
//    Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.
//
//    Since the result may be very large, so you need to return a string instead of an integer.
//
//
//
//    Example 1:
//
//    Input: nums = [10,2]
//    Output: "210"
//    Example 2:
//
//    Input: nums = [3,30,34,5,9]
//    Output: "9534330"
//
//
//    Constraints:
//
//    1 <= nums.length <= 100
//    0 <= nums[i] <= 109