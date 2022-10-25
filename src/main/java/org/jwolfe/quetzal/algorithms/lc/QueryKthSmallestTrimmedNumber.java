package org.jwolfe.quetzal.algorithms.lc;

import java.util.PriorityQueue;

public class QueryKthSmallestTrimmedNumber {
    class Solution {
        public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
            if(nums == null || nums.length == 0 || queries == null || queries.length == 0) {
                return new int[0];
            }

            int n = nums.length;
            int qn = queries.length;
            int index = 0;
            int[] results = new int[qn];
            for(int[] query : queries) {
                int k = query[0];
                int t = query[1];

                PriorityQueue<Pair> maxHeap = new PriorityQueue<Pair>((a, b) -> {
                    if(b.value.equals(a.value)) {
                        return b.index - a.index;
                    }

                    return b.value.compareTo(a.value);
                });

                for(int i = 0; i < n; i++) {
                    String val = trimRight(nums[i], t);
                    maxHeap.offer(new Pair(i, val));

                    if(maxHeap.size() > k) {
                        maxHeap.poll();
                    }
                }

                results[index++] = (int) maxHeap.poll().index;
            }

            return results;
        }

        private String trimRight(String s, int count) {
            return s.substring(s.length() - count);
        }

        private class Pair {
            int index;
            String value;

            public Pair(int index, String value) {
                this.index = index;
                this.value = value;
            }

            @Override
            public String toString() {
                return "(" + index + ", " + value + ")";
            }
        }
    }

    class Solution_Incorrect {
        public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
            if(nums == null || nums.length == 0 || queries == null || queries.length == 0) {
                return new int[0];
            }

            int n = nums.length;
            int qn = queries.length;
            int index = 0;
            int[] results = new int[qn];
            for(int[] query : queries) {
                int k = query[0];
                int t = query[1];

                PriorityQueue<Pair> maxHeap = new PriorityQueue<Pair>((a, b) -> {
                    if(b.value == a.value) {
                        return b.index - a.index;
                    }

                    return b.value > a.value ? 1
                            : (b.value == a.value) ? 0 : -1;
                });

                for(int i = 0; i < n; i++) {
                    long val = Long.valueOf(trimRight(nums[i], t));
                    maxHeap.offer(new Pair(i, val));

                    if(maxHeap.size() > k) {
                        maxHeap.poll();
                    }
                }

                results[index++] = (int) maxHeap.poll().index;
            }

            return results;
        }

        private String trimRight(String s, int count) {
            return s.substring(s.length() - count);
        }

        private class Pair {
            int index;
            long value;

            public Pair(int index, long value) {
                this.index = index;
                this.value = value;
            }

            @Override
            public String toString() {
                return "(" + index + ", " + value + ")";
            }
        }
    }

// ["102","473","251","814"]
// [[1,1],[2,3],[4,2],[1,2]]

// ["64333639502","65953866768","17845691654","87148775908","58954177897","70439926174","48059986638","47548857440","18418180516","06364956881","01866627626","36824890579","14672385151","71207752868"]
// [[9,4],[6,1],[3,8],[12,9],[11,4],[4,9],[2,7],[10,3],[13,1],[13,1],[6,1],[5,10]]

// ["8331019423839036903","2215783497319194533","3244863164120264914","2723857887888553250","1069645833408356268","3799170975306313470","3300849027471666477","8896469467436127218","9595084104356246555","4601424390471226348","2777623221871959897","2660664761264162910","4830224756337097853","2239177595019260973","5704104074606481922","5158962343348888307","4957489822885409209","5533958195540658313","6712811206814843536","9775503283462317096","1975389311819120035","1292135637676764140","9838972337538013522","7609294617007602893","0186572359592634437","9236053726818307461","7264888050655615544","4990296885039745852","1417868535147288083"]
// [[22,19],[16,17],[10,7],[27,16],[9,9],[21,4],[24,2],[12,11],[2,5],[24,12],[25,7],[7,13],[14,9],[23,15],[18,17],[22,16],[4,14],[14,17],[25,11],[12,16],[29,3],[22,11],[29,2],[24,2],[24,15],[7,14],[7,3],[7,14],[1,3]]
}

//    2343. Query Kth Smallest Trimmed Number
//    Medium
//
//    179
//
//    342
//
//    Add to List
//
//    Share
//    You are given a 0-indexed array of strings nums, where each string is of equal length and consists of only digits.
//
//    You are also given a 0-indexed 2D integer array queries where queries[i] = [ki, trimi]. For each queries[i], you need to:
//
//    Trim each number in nums to its rightmost trimi digits.
//    Determine the index of the kith smallest trimmed number in nums. If two trimmed numbers are equal, the number with the lower index is considered to be smaller.
//    Reset each number in nums to its original length.
//    Return an array answer of the same length as queries, where answer[i] is the answer to the ith query.
//
//    Note:
//
//    To trim to the rightmost x digits means to keep removing the leftmost digit, until only x digits remain.
//    Strings in nums may contain leading zeros.
//
//
//    Example 1:
//
//    Input: nums = ["102","473","251","814"], queries = [[1,1],[2,3],[4,2],[1,2]]
//    Output: [2,2,1,0]
//    Explanation:
//    1. After trimming to the last digit, nums = ["2","3","1","4"]. The smallest number is 1 at index 2.
//    2. Trimmed to the last 3 digits, nums is unchanged. The 2nd smallest number is 251 at index 2.
//    3. Trimmed to the last 2 digits, nums = ["02","73","51","14"]. The 4th smallest number is 73.
//    4. Trimmed to the last 2 digits, the smallest number is 2 at index 0.
//    Note that the trimmed number "02" is evaluated as 2.
//    Example 2:
//
//    Input: nums = ["24","37","96","04"], queries = [[2,1],[2,2]]
//    Output: [3,0]
//    Explanation:
//    1. Trimmed to the last digit, nums = ["4","7","6","4"]. The 2nd smallest number is 4 at index 3.
//    There are two occurrences of 4, but the one at index 0 is considered smaller than the one at index 3.
//    2. Trimmed to the last 2 digits, nums is unchanged. The 2nd smallest number is 24.
//
//
//    Constraints:
//
//    1 <= nums.length <= 100
//    1 <= nums[i].length <= 100
//    nums[i] consists of only digits.
//    All nums[i].length are equal.
//    1 <= queries.length <= 100
//    queries[i].length == 2
//    1 <= ki <= nums.length
//    1 <= trimi <= nums[i].length
//
//
//    Follow up: Could you use the Radix Sort Algorithm to solve this problem? What will be the complexity of that solution?