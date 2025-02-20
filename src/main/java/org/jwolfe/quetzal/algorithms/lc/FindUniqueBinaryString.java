package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class FindUniqueBinaryString {
    class Solution {
        public String findDifferentBinaryString(String[] nums) {
            if (nums == null || nums.length == 0) {
                return "";
            }

            int n = nums.length;
            String ans = "";
            for (int i = 0; i < n; i++) {
                String str = nums[i];
                char c = str.charAt(i);

                ans += (c == '0') ? '1' : '0';
            }

            return ans;
        }
    }

    class Solution_Correct_1 {
        public String findDifferentBinaryString(String[] nums) {
            if (nums == null || nums.length == 0) {
                return "";
            }

            Set<Integer> set = new HashSet<>();
            for (String str : nums) {
                int val = Integer.parseInt(str, 2);
                set.add(val);
            }

            int n = nums.length;
            for (int val = 0; val <= n; val++) {
                if (!set.contains(val)) {
                    String ans = Integer.toBinaryString(val);
                    while (ans.length() < n) {
                        ans = "0" + ans;
                    }

                    return ans;
                }
            }

            return "";
        }
    }

// ["01","10"]
}

//    1980. Find Unique Binary String
//    Medium
//    Given an array of strings nums containing n unique binary strings each of length n, return a binary string of length n that does not appear in nums. If there are multiple answers, you may return any of them.
//
//
//
//    Example 1:
//
//    Input: nums = ["01","10"]
//    Output: "11"
//    Explanation: "11" does not appear in nums. "00" would also be correct.
//    Example 2:
//
//    Input: nums = ["00","01"]
//    Output: "11"
//    Explanation: "11" does not appear in nums. "10" would also be correct.
//    Example 3:
//
//    Input: nums = ["111","011","001"]
//    Output: "101"
//    Explanation: "101" does not appear in nums. "000", "010", "100", and "110" would also be correct.
//
//
//    Constraints:
//
//    n == nums.length
//    1 <= n <= 16
//    nums[i].length == n
//    nums[i] is either '0' or '1'.
//    All the strings of nums are unique.
