package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class ThreeSumWithMultiplicity {
    class Solution {
        public int threeSumMulti(int[] arr, int target) {
            if(arr == null || arr.length < 3 || target < 0) {
                return 0;
            }

            int count = 0;
            int n = arr.length;
            int mod = 1000000007;

            Arrays.sort(arr);
            for(int i = 0; i < n - 2; i++) {
                int j = i + 1;
                int k = n - 1;

                int a = arr[i];
                while(j < k) {
                    int b = arr[j];
                    int c = arr[k];

                    int sum = a + b + c;
                    if(sum == target) {
                        if(b == c) {
                            count += (k - j + 1) * (k - j) / 2;
                            count %= mod;

                            break;
                        } else {
                            int bc = 1;
                            while(j + 1 < k && arr[j] == arr[j + 1]) {
                                j++;
                                bc++;
                            }

                            int cc = 1;
                            while(j < k && arr[k] == arr[k - 1]) {
                                k--;
                                cc++;
                            }

                            count += (bc * cc);
                            count %= mod;
                        }

                        j++;
                        k--;
                    } else if(sum > target) {
                        k--;
                    } else {
                        j++;
                    }
                }
            }

            return count;
        }
    }

    class Solution_Brute {
        public int threeSumMulti(int[] arr, int target) {
            if(arr == null || arr.length < 3 || target < 0) {
                return 0;
            }

            int count = 0;
            int n = arr.length;
            for(int i = 0; i < n - 2; i++) {
                for(int j = i + 1; j < n - 1; j++) {
                    for(int k = j + 1; k < n; k++) {
                        if(arr[i] + arr[j] + arr[k] == target) {
                            count++;
                        }
                    }
                }
            }

            return count;
        }
    }

// [1,1,2,2,3,3,4,4,5,5]
// 8
}

//    923. 3Sum With Multiplicity
//    Given an integer array arr, and an integer target, return the number of tuples i, j, k such that i < j < k and arr[i] + arr[j] + arr[k] == target.
//
//    As the answer can be very large, return it modulo 109 + 7.
//
//
//
//    Example 1:
//
//    Input: arr = [1,1,2,2,3,3,4,4,5,5], target = 8
//    Output: 20
//    Explanation:
//    Enumerating by the values (arr[i], arr[j], arr[k]):
//    (1, 2, 5) occurs 8 times;
//    (1, 3, 4) occurs 8 times;
//    (2, 2, 4) occurs 2 times;
//    (2, 3, 3) occurs 2 times.
//    Example 2:
//
//    Input: arr = [1,1,2,2,2,2], target = 5
//    Output: 12
//    Explanation:
//    arr[i] = 1, arr[j] = arr[k] = 2 occurs 12 times:
//    We choose one 1 from [1,1] in 2 ways,
//    and two 2s from [2,2,2,2] in 6 ways.
//
//
//    Constraints:
//
//    3 <= arr.length <= 3000
//    0 <= arr[i] <= 100
//    0 <= target <= 300
