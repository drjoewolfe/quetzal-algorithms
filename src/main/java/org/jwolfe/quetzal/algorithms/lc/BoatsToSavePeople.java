package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BoatsToSavePeople {
    class Solution {
        public int numRescueBoats(int[] people, int limit) {
            if(people == null || people.length == 0 || limit < 1) {
                return 0;
            }

            int n = people.length;
            Arrays.sort(people);

            int left = 0;
            int right = n - 1;

            int boats = 0;
            while(left <= right) {
                int a = people[left];
                int b = people[right];

                if(a + b <= limit) {
                    left++;
                    right--;
                } else {
                    right--;
                }

                boats++;
            }

            return boats;
        }
    }

    class Solution_Correct_1 {
        public int numRescueBoats(int[] people, int limit) {
            if(people == null || people.length == 0 || limit < 1) {
                return 0;
            }

            int boats = 0;
            Arrays.sort(people);

            int i = 0;
            int j = people.length - 1;
            while(i <= j) {
                int a = people[j];
                int b = people[i];

                if(b + a <= limit) {
                    i++;
                    j--;
                } else {
                    j--;
                }

                boats++;
            }

            return boats;
        }
    }

    class Solution_IncorrectAgain {
        public int numRescueBoats(int[] people, int limit) {
            if(people == null || people.length == 0 || limit < 1) {
                return 0;
            }

            int boats = 0;
            Arrays.sort(people);
            Set<Integer> indicesToIgnore = new HashSet<>();
            for(int i = people.length - 1; i >= 0; i--) {
                if(indicesToIgnore.contains(i)) {
                    continue;
                }

                int a = people[i];
                if(i > 0) {
                    int j = binarySearchForLessThanOrEqual(people, limit - a, i - 1, indicesToIgnore);
                    if(j != -1 && !indicesToIgnore.contains(j)) {
                        indicesToIgnore.add(j);
                    }


                    System.out.println(a + ", " + (limit - a) + ", " + j);
                }

                boats++;
            }

            return boats;
        }

        private int binarySearchForLessThanOrEqual(int[] nums, int val, int right, Set<Integer> indicesToIgnore) {
            int left = 0;
            int index = -1;

            System.out.println(indicesToIgnore);
            while(left <= right) {
                int mid = left + (right - left) / 2;

                if(nums[mid] == val) {
                    if(!indicesToIgnore.contains(mid)) {
                        return mid;
                    }

                    right = mid - 1;
                } else if(nums[mid] < val) {
                    if(!indicesToIgnore.contains(mid)) {
                        index = mid;
                    }

                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return index;
        }
    }

    class Solution_Wrong {
        public int numRescueBoats(int[] people, int limit) {
            if(people == null || people.length == 0 || limit < 1) {
                return 0;
            }

            Arrays.sort(people);
            int boats = 0;
            for(int i = people.length - 1; i >= 0; i--) {
                int a = people[i];
                if(i > 0 && limit - a >= people[i - 1]) {
                    i--;
                }

                boats++;
            }

            return boats;
        }
    }

// [1,2]
// 3

// [5,1,4,2]
// 6

// [5,1,7,4,2,4]
// 7
}

//    881. Boats to Save People
//    Medium
//    You are given an array people where people[i] is the weight of the ith person, and an infinite number of boats where each boat can carry a maximum weight of limit. Each boat carries at most two people at the same time, provided the sum of the weight of those people is at most limit.
//
//    Return the minimum number of boats to carry every given person.
//
//
//
//    Example 1:
//
//    Input: people = [1,2], limit = 3
//    Output: 1
//    Explanation: 1 boat (1, 2)
//    Example 2:
//
//    Input: people = [3,2,2,1], limit = 3
//    Output: 3
//    Explanation: 3 boats (1, 2), (2) and (3)
//    Example 3:
//
//    Input: people = [3,5,3,4], limit = 5
//    Output: 4
//    Explanation: 4 boats (3), (3), (4), (5)
//
//
//    Constraints:
//
//    1 <= people.length <= 5 * 104
//    1 <= people[i] <= limit <= 3 * 104