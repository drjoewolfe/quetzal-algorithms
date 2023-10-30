package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class SortIntegersByTheNumberOf1Bits {
    class Solution {
        public int[] sortByBits(int[] arr) {
            if(arr == null || arr.length < 2) {
                return arr;
            }

            var intArr = Arrays.stream(arr).boxed().toArray(Integer[]::new);
            Arrays.sort(intArr, new BitComparator());
            return Arrays.stream(intArr).mapToInt(Integer::intValue).toArray();
        }

        private class BitComparator implements Comparator<Integer> {
            @Override
            public int compare(Integer a, Integer b) {
                int wa = getWeight(a);
                int wb = getWeight(b);

                if(wa == wb) {
                    return a - b;
                }

                return wa - wb;
            }

            private int getWeight(int a) {
                int weight = 0;

                while (a > 0) {
                    weight++;
                    a &= (a - 1);
                }

                return weight;
            }
        }
    }

    class Solution_Correct_1 {
        public int[] sortByBits(int[] arr) {
            if(arr == null || arr.length == 0) {
                return arr;
            }

            Map<Integer, List<Integer>> map = new TreeMap<>();
            for(int num : arr) {
                int setBits = countSetBits(num);

                List<Integer> chain = map.getOrDefault(setBits, new ArrayList<Integer>());
                chain.add(num);

                map.put(setBits, chain);
            }

            int index = 0;
            for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
                List<Integer> values = entry.getValue();
                Collections.sort(values);
                for(int num : values) {
                    arr[index++] = num;
                }
            }

            return arr;
        }

        private int countSetBits(int num) {
            int setBits = 0;

            while(num > 0) {
                setBits += num & 1;
                num >>= 1;
            }

            return setBits;
        }
    }
}

//    1356. Sort Integers by The Number of 1 Bits
//    Easy
//    You are given an integer array arr. Sort the integers in the array in ascending order by the number of 1's in their binary representation and in case of two or more integers have the same number of 1's you have to sort them in ascending order.
//
//    Return the array after sorting it.
//
//
//
//    Example 1:
//
//    Input: arr = [0,1,2,3,4,5,6,7,8]
//    Output: [0,1,2,4,8,3,5,6,7]
//    Explantion: [0] is the only integer with 0 bits.
//    [1,2,4,8] all have 1 bit.
//    [3,5,6] have 2 bits.
//    [7] has 3 bits.
//    The sorted array by bits is [0,1,2,4,8,3,5,6,7]
//    Example 2:
//
//    Input: arr = [1024,512,256,128,64,32,16,8,4,2,1]
//    Output: [1,2,4,8,16,32,64,128,256,512,1024]
//    Explantion: All integers have 1 bit in the binary representation, you should just sort them in ascending order.
//
//
//    Constraints:
//
//    1 <= arr.length <= 500
//    0 <= arr[i] <= 104