package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class MaximumSquareAreaByRemovingFencesFromAField {
    class Solution {
        private int MOD = 1_000_000_007;

        public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
            Set<Integer> hEdges = getEdges(hFences, m);
            Set<Integer> vEdges = getEdges(vFences, n);

            long res = 0;
            for (int e : hEdges) {
                if (vEdges.contains(e)) {
                    res = Math.max(res, e);
                }
            }

            if (res == 0) {
                return -1;
            } else {
                return (int) ((res * res) % MOD);
            }
        }

        private Set<Integer> getEdges(int[] fences, int border) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            for (int fence : fences) {
                list.add(fence);
            }

            list.add(border);

            Collections.sort(list);

            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    int d = list.get(j) - list.get(i);
                    set.add(d);
                }
            }

            return set;
        }
    }
}

//    2975. Maximum Square Area by Removing Fences From a Field
//    Medium
//    There is a large (m - 1) x (n - 1) rectangular field with corners at (1, 1) and (m, n) containing some horizontal and vertical fences given in arrays hFences and vFences respectively.
//
//    Horizontal fences are from the coordinates (hFences[i], 1) to (hFences[i], n) and vertical fences are from the coordinates (1, vFences[i]) to (m, vFences[i]).
//
//    Return the maximum area of a square field that can be formed by removing some fences (possibly none) or -1 if it is impossible to make a square field.
//
//    Since the answer may be large, return it modulo 109 + 7.
//
//    Note: The field is surrounded by two horizontal fences from the coordinates (1, 1) to (1, n) and (m, 1) to (m, n) and two vertical fences from the coordinates (1, 1) to (m, 1) and (1, n) to (m, n). These fences cannot be removed.
//
//
//
//    Example 1:
//
//
//
//    Input: m = 4, n = 3, hFences = [2,3], vFences = [2]
//    Output: 4
//    Explanation: Removing the horizontal fence at 2 and the vertical fence at 2 will give a square field of area 4.
//    Example 2:
//
//
//
//    Input: m = 6, n = 7, hFences = [2], vFences = [4]
//    Output: -1
//    Explanation: It can be proved that there is no way to create a square field by removing fences.
//
//
//    Constraints:
//
//    3 <= m, n <= 109
//    1 <= hFences.length, vFences.length <= 600
//    1 < hFences[i] < m
//    1 < vFences[i] < n
//    hFences and vFences are unique.