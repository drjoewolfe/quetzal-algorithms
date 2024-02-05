package org.jwolfe.quetzal.algorithms.lc;

public class TypeOfTriangleII {
    class Solution {
        public String triangleType(int[] nums) {
            if(nums == null || nums.length != 3) {
                return "none";
            }

            int s1 = nums[0];
            int s2 = nums[1];
            int s3 = nums[2];

            if(s1 + s2 <= s3 || s2 + s3 <= s1 || s1 + s3 <= s2) {
                return "none";
            }

            if(s1 == s2 && s2 == s3) {
                return "equilateral";
            }

            if(s1 == s2 || s2 == s3 || s1 == s3) {
                return "isosceles";
            }

            return "scalene";
        }
    }

// [3,3,3]
// [9,4,9]
// [8,4,2]
}

//    3024. Type of Triangle II
//    Easy
//    You are given a 0-indexed integer array nums of size 3 which can form the sides of a triangle.
//
//    A triangle is called equilateral if it has all sides of equal length.
//    A triangle is called isosceles if it has exactly two sides of equal length.
//    A triangle is called scalene if all its sides are of different lengths.
//    Return a string representing the type of triangle that can be formed or "none" if it cannot form a triangle.
//
//
//
//    Example 1:
//
//    Input: nums = [3,3,3]
//    Output: "equilateral"
//    Explanation: Since all the sides are of equal length, therefore, it will form an equilateral triangle.
//    Example 2:
//
//    Input: nums = [3,4,5]
//    Output: "scalene"
//    Explanation:
//    nums[0] + nums[1] = 3 + 4 = 7, which is greater than nums[2] = 5.
//    nums[0] + nums[2] = 3 + 5 = 8, which is greater than nums[1] = 4.
//    nums[1] + nums[2] = 4 + 5 = 9, which is greater than nums[0] = 3.
//    Since the sum of the two sides is greater than the third side for all three cases, therefore, it can form a triangle.
//    As all the sides are of different lengths, it will form a scalene triangle.
//
//
//    Constraints:
//
//    nums.length == 3
//    1 <= nums[i] <= 100