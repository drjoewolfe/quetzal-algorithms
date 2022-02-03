package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class FourSumII {
    class Solution {
        public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
            if(A == null || B == null || C == null || D == null) {
                return 0;
            }

            int n = A.length;
            if(B.length != n || C.length != n || D.length != n) {
                return 0;
            }

            Map<Integer, Integer> map = new HashMap<>();
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    int a = A[i];
                    int b = B[j];

                    int sum = a + b;
                    map.put(sum, map.getOrDefault(sum, 0) + 1);
                }
            }

            int count = 0;
            for(int k = 0; k < n; k++) {
                for(int l = 0; l < n; l++) {
                    int c = C[k];
                    int d = D[l];

                    int sum = c + d;
                    int target = (-1) * sum;

                    if(map.containsKey(target)) {
                        count += map.get(target);
                    }
                }
            }

            return count;
        }
    }

    class Solution_Correct_1 {
        public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
            if(A == null || B == null || C == null || D == null || A.length == 0) {
                return 0;
            }

            if(A.length != B.length || B.length != C.length || C.length != D.length) {
                return 0;
            }

            int n = A.length;
            Map<Integer, Integer> map = new HashMap<>();
            for(int a : A) {
                for(int b : B) {
                    int delta = a + b;
                    map.put(delta, map.getOrDefault(delta, 0) + 1);
                }
            }

            int count = 0;
            for(int c : C) {
                for(int d : D) {
                    int delta = (c + d) * -1;
                    if(map.containsKey(delta)) {
                        count += map.get(delta);
                    }
                }
            }

            return count;
        }
    }

    class Solution_O3 {
        public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
            if(A == null || B == null || C == null || D == null || A.length == 0) {
                return 0;
            }

            if(A.length != B.length || B.length != C.length || C.length != D.length) {
                return 0;
            }

            int n = A.length;

            Map<Integer, Integer> mapA = new HashMap<>();
            Map<Integer, Integer> mapB = new HashMap<>();
            Map<Integer, Integer> mapC = new HashMap<>();
            Map<Integer, Integer> mapD = new HashMap<>();

            for(int i = 0; i < n; i++) {
                int a = A[i];
                int b = B[i];
                int c = C[i];
                int d = D[i];

                mapA.put(a, mapA.getOrDefault(a, 0) + 1);
                mapB.put(b, mapB.getOrDefault(b, 0) + 1);
                mapC.put(c, mapC.getOrDefault(c, 0) + 1);
                mapD.put(d, mapD.getOrDefault(d, 0) + 1);
            }

            int count = 0;
            for(var a : mapA.keySet()) {
                int af = mapA.get(a);
                for(var b : mapB.keySet()) {
                    int bf = mapB.get(b);
                    for(var c : mapC.keySet()) {
                        int cf = mapC.get(c);
                        int d = (a + b + c) * -1;
                        if(mapD.containsKey(d)) {
                            int df = mapD.get(d);

                            // System.out.println(a + ", " + b + ", " + c + ", " + d + " : " + af + ", " + bf + ", " + cf + ", " + df);
                            count += (af * bf * cf * df);
                        }
                    }
                }
            }


            return count;
        }
    }

    class Solution_Brute {
        public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
            if(A == null || B == null || C == null || D == null || A.length == 0) {
                return 0;
            }

            if(A.length != B.length || B.length != C.length || C.length != D.length) {
                return 0;
            }

            int n = A.length;
            int count = 0;

            for(int i = 0; i < n; i++) {
                int a = A[i];
                for(int j = 0; j < n; j++) {
                    int b = B[j];
                    for(int k = 0; k < n; k++) {
                        int c = C[k];
                        for(int l = 0; l < n; l++) {
                            int d = D[l];

                            if(a + b + c + d == 0) {
                                count++;
                            }
                        }
                    }
                }
            }

            return count;
        }
    }

// [1,2]
// [-2,-1]
// [-1,2]
// [0,2]
}

//    454. 4Sum II
//    Medium
//    Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of tuples (i, j, k, l) such that:
//
//    0 <= i, j, k, l < n
//    nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
//
//
//    Example 1:
//
//    Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
//    Output: 2
//    Explanation:
//    The two tuples are:
//    1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
//    2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
//    Example 2:
//
//    Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
//    Output: 1
//
//
//    Constraints:
//
//    n == nums1.length
//    n == nums2.length
//    n == nums3.length
//    n == nums4.length
//    1 <= n <= 200
//    -228 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 228