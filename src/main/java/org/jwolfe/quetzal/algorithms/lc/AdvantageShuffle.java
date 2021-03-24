package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class AdvantageShuffle {
    class Solution {
        public int[] advantageCount(int[] A, int[] B) {
            if(A == null || A.length == 0 || B == null || B.length != A.length) {
                return new int[0];
            }

            int n = A.length;
            List<ElementIndex> elementsA = new ArrayList<>();
            List<ElementIndex> elementsB = new ArrayList<>();

            for(int i = 0; i < n; i++) {
                elementsA.add(new ElementIndex(A[i], i));
                elementsB.add(new ElementIndex(B[i], i));
            }

            Collections.sort(elementsA, (a, b) -> a.element - b.element);
            Collections.sort(elementsB, (a, b) -> a.element - b.element);

            List<ElementIndex> unassigned = new ArrayList<>();

            int i = 0;
            int j = 0;

            Set<Integer> assignedIndexes = new HashSet<>();
            int[] result = new int[n];

            while(i < n && j < n) {
                var ae = elementsA.get(i);
                var be = elementsB.get(j);

                if(ae.element > be.element) {
                    result[be.index] = ae.element;
                    assignedIndexes.add(be.index);

                    i++;
                    j++;
                } else {
                    unassigned.add(ae);
                    i++;
                }
            }

            j = 0;
            for(i = 0; i < n; i++) {
                if(assignedIndexes.contains(i)) {
                    continue;
                }

                result[i] = unassigned.get(j++).element;
            }

            return result;
        }

        private class ElementIndex {
            int element;
            int index;

            public ElementIndex(int e, int i) {
                element = e;
                index = i;
            }
        }
    }

    class Solution_Brute {
        int maxAdvantage;
        int[] maxAdvantagedArray;

        public int[] advantageCount(int[] A, int[] B) {
            if(A == null || A.length == 0 || B == null || B.length != A.length) {
                return new int[0];
            }

            int n = A.length;
            maxAdvantage = -1;
            advantageCount(A, B, n, 0);

            return maxAdvantagedArray;
        }

        private void advantageCount(int[] A, int[] B, int n, int index) {
            if(index == n) {
                int advantage = getAdvantage(A, B);
                if(advantage > maxAdvantage) {
                    maxAdvantage = advantage;
                    maxAdvantagedArray = A.clone();
                }

                return;
            }

            for(int i = index; i < n; i++) {
                swap(A, index, i);
                advantageCount(A, B, n, index + 1);
                swap(A, index, i);
            }
        }

        private int getAdvantage(int[] A, int[] B) {
            int advantage = 0;
            for(int i = 0; i < A.length; i++) {
                if(A[i] > B[i]) {
                    advantage++;
                }
            }

            return advantage;
        }

        private void swap(int[] A, int i, int j) {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
        }
    }

    class Solution_Wrong {
        public int[] advantageCount(int[] A, int[] B) {
            if(A == null || A.length == 0 || B == null || B.length != A.length) {
                return new int[0];
            }

            int n = A.length;
            Arrays.sort(A);
            Arrays.sort(B);

            int[] result = new int[n];
            int i = 0;
            int j = 0;

            while(i < n && j < n) {
                result[i] = A[i];

                if(A[i] > B[j]) {
                    i++;
                    j++;
                } else {
                    i++;
                }
            }

            while(i < n) {
                result[i] = A[i++];
            }

            return result;
        }
    }

// [2,7,11,15]
// [1,10,4,11]
}

//    870. Advantage Shuffle
//    Medium
//    Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for which A[i] > B[i].
//
//    Return any permutation of A that maximizes its advantage with respect to B.
//
//
//
//    Example 1:
//
//    Input: A = [2,7,11,15], B = [1,10,4,11]
//    Output: [2,11,7,15]
//    Example 2:
//
//    Input: A = [12,24,8,32], B = [13,25,32,11]
//    Output: [24,32,8,12]
//
//
//    Note:
//
//    1 <= A.length = B.length <= 10000
//    0 <= A[i] <= 10^9
//    0 <= B[i] <= 10^9