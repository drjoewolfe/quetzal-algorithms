package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class NumberOfFlowersInFullBloom {
    class Solution {
        public int[] fullBloomFlowers(int[][] flowers, int[] people) {
            if(people == null || people.length == 0) {
                return new int[0];
            }

            if(flowers == null || flowers.length == 0) {
                return new int[people.length];
            }

            int[] peopleSorted = Arrays.copyOf(people, people.length);
            Arrays.sort(peopleSorted);

            Arrays.sort(flowers, (a, b) -> a[0] - b[0]);

            Map<Integer, Integer> personFlowersMap = new HashMap<>();
            PriorityQueue<Integer> heap = new PriorityQueue<>();
            int index = 0;
            for(int i = 0; i < people.length; i++) {
                int person = peopleSorted[i];

                while(index < flowers.length && flowers[index][0] <= person) {
                    heap.offer(flowers[index][1]);
                    index++;
                }

                while(!heap.isEmpty() && heap.peek() < person) {
                    heap.poll();
                }

                personFlowersMap.put(person, heap.size());
            }

            int[] results = new int[people.length];
            for(int i = 0; i < people.length; i++) {
                int person = people[i];
                results[i] = personFlowersMap.get(person);
            }

            return results;
        }
    }

    class Solution_MLE {
        public int[] fullBloomFlowers(int[][] flowers, int[] people) {
            if(people == null || people.length == 0) {
                return new int[0];
            }

            if(flowers == null || flowers.length == 0) {
                return new int[people.length];
            }

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for(int[] flower : flowers) {
                int start = flower[0];
                int end = flower[1];

                min = Math.min(min, start);
                max = Math.max(max, end);
            }

            int[] blooms = new int[max + 1];
            for(int[] flower : flowers) {
                int start = flower[0];
                int end = flower[1];

                for(int i = start; i <= end; i++) {
                    blooms[i]++;
                }
            }

            int n = people.length;
            int[] results = new int[n];
            for(int i = 0; i < n; i++) {
                int person = people[i];
                if(person >= min && person <= max) {
                    results[i] = blooms[person];
                }
            }

            return results;
        }
    }


// [[1,6],[3,7],[9,12],[4,13]]
// [2,3,7,11]

// [[19,37],[19,38],[19,35]]
// [6,7,21,1,13,37,5,37,46,43]
}

//    2251. Number of Flowers in Full Bloom
//    Hard
//    You are given a 0-indexed 2D integer array flowers, where flowers[i] = [starti, endi] means the ith flower will be in full bloom from starti to endi (inclusive). You are also given a 0-indexed integer array people of size n, where people[i] is the time that the ith person will arrive to see the flowers.
//
//    Return an integer array answer of size n, where answer[i] is the number of flowers that are in full bloom when the ith person arrives.
//
//
//
//    Example 1:
//
//
//    Input: flowers = [[1,6],[3,7],[9,12],[4,13]], poeple = [2,3,7,11]
//    Output: [1,2,2,2]
//    Explanation: The figure above shows the times when the flowers are in full bloom and when the people arrive.
//    For each person, we return the number of flowers in full bloom during their arrival.
//    Example 2:
//
//
//    Input: flowers = [[1,10],[3,3]], poeple = [3,3,2]
//    Output: [2,2,1]
//    Explanation: The figure above shows the times when the flowers are in full bloom and when the people arrive.
//    For each person, we return the number of flowers in full bloom during their arrival.
//
//
//    Constraints:
//
//    1 <= flowers.length <= 5 * 104
//    flowers[i].length == 2
//    1 <= starti <= endi <= 109
//    1 <= people.length <= 5 * 104
//    1 <= people[i] <= 109