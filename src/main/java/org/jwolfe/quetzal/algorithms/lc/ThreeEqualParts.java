package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class ThreeEqualParts {
    class Solution {
        public int[] threeEqualParts(int[] arr) {
            int[] empty = new int[] {-1, -1};
            if(arr == null || arr.length < 3) {
                return empty;
            }

            int oneCount = 0;
            for(int val : arr) {
                oneCount += val;
            }

            if(oneCount % 3 != 0) {
                return empty;
            }

            int n = arr.length;

            int partCount = oneCount / 3;
            if(partCount == 0) {
                return new int[] {0, n - 1};
            }

            int l1 = -1;
            int r1 = -1;
            int l2 = -1;
            int r2 = -1;
            int l3 = -1;
            int r3 = -1;

            oneCount = 0;
            for(int i = 0; i < n; i++) {
                if(arr[i] != 1) {
                    continue;
                }

                oneCount++;
                if(oneCount == 1) {
                    l1 = i;
                }

                if(oneCount == partCount) {
                    r1 = i;
                }

                if(oneCount == partCount + 1) {
                    l2 = i;
                }

                if(oneCount == partCount * 2) {
                    r2 = i;
                }

                if(oneCount == partCount * 2 + 1) {
                    l3 = i;
                }

                if(oneCount == partCount * 3) {
                    r3 = i;
                }
            }

            int[] part1 = Arrays.copyOfRange(arr, l1, r1 + 1);
            int[] part2 = Arrays.copyOfRange(arr, l2, r2 + 1);
            int[] part3 = Arrays.copyOfRange(arr, l3, r3 + 1);

            if(!Arrays.equals(part1, part2) || !Arrays.equals(part1, part3)) {
                return empty;
            }

            int x = l2 - r1 - 1;
            int y = l3 - r2 - 1;
            int z = n - r3 - 1;

            if(x < z || y < z) {
                return empty;
            }

            return new int[] {r1 + z, r2 + z + 1};
        }
    }
}

//    927. Three Equal Parts
//    Hard
//    You are given an array arr which consists of only zeros and ones, divide the array into three non-empty parts such that all of these parts represent the same binary value.
//
//    If it is possible, return any [i, j] with i + 1 < j, such that:
//
//    arr[0], arr[1], ..., arr[i] is the first part,
//    arr[i + 1], arr[i + 2], ..., arr[j - 1] is the second part, and
//    arr[j], arr[j + 1], ..., arr[arr.length - 1] is the third part.
//    All three parts have equal binary values.
//    If it is not possible, return [-1, -1].
//
//    Note that the entire part is used when considering what binary value it represents. For example, [1,1,0] represents 6 in decimal, not 3. Also, leading zeros are allowed, so [0,1,1] and [1,1] represent the same value.
//
//
//
//    Example 1:
//
//    Input: arr = [1,0,1,0,1]
//    Output: [0,3]
//    Example 2:
//
//    Input: arr = [1,1,0,1,1]
//    Output: [-1,-1]
//    Example 3:
//
//    Input: arr = [1,1,0,0,1]
//    Output: [0,2]
//
//
//    Constraints:
//
//    3 <= arr.length <= 3 * 104
//    arr[i] is 0 or 1
