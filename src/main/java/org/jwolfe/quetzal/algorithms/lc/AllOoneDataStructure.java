package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class AllOoneDataStructure {
    class AllOne {

        Map<String, Integer> frequencies;
        TreeMap<Integer, Set<String>> partitions;

        public AllOne() {
            frequencies = new HashMap<>();
            partitions = new TreeMap<>();
        }

        public void inc(String key) {
            if (frequencies.containsKey(key)) {
                var beforeCount = frequencies.get(key);
                var partition = partitions.get(beforeCount);
                partition.remove(key);
                if (partition.size() == 0) {
                    partitions.remove(beforeCount);
                }
            }

            frequencies.put(key, frequencies.getOrDefault(key, 0) + 1);
            var afterCount = frequencies.get(key);

            if (!partitions.containsKey(afterCount)) {
                partitions.put(afterCount, new HashSet<>());
            }

            partitions.get(afterCount).add(key);
            // System.out.println("Incr:" + frequencies);
            // System.out.println("    Incr:" + partitions);
        }

        public void dec(String key) {
            var beforeCount = frequencies.get(key);
            var partition = partitions.get(beforeCount);
            partition.remove(key);
            if (partition.size() == 0) {
                partitions.remove(beforeCount);
            }

            if (beforeCount == 1) {
                frequencies.remove(key);
                return;
            }

            frequencies.put(key, beforeCount - 1);
            var afterCount = frequencies.get(key);

            if (!partitions.containsKey(afterCount)) {
                partitions.put(afterCount, new HashSet<>());
            }

            partitions.get(afterCount).add(key);
            // System.out.println("Decr:" + frequencies);
            // System.out.println("    Decr:" + partitions);
        }

        public String getMaxKey() {
            if (partitions.size() == 0) {
                return "";
            }

            var key = partitions.lastKey();
            if (key == null) {
                return "";
            }

            var set = partitions.get(key);
            if (set.size() == 0) {
                return "";
            }

            return set.iterator().next();
        }

        public String getMinKey() {
            if (partitions.size() == 0) {
                return "";
            }

            var key = partitions.firstKey();
            if (key == null) {
                return "";
            }

            var set = partitions.get(key);
            if (set.size() == 0) {
                return "";
            }

            return set.iterator().next();
        }
    }

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
}

//    432. All O`one Data Structure
//    Hard
//    Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.
//
//    Implement the AllOne class:
//
//    AllOne() Initializes the object of the data structure.
//    inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
//    dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
//    getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
//    getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
//    Note that each function must run in O(1) average time complexity.
//
//
//
//    Example 1:
//
//    Input
//    ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
//    [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
//    Output
//    [null, null, null, "hello", "hello", null, "hello", "leet"]
//
//    Explanation
//    AllOne allOne = new AllOne();
//    allOne.inc("hello");
//    allOne.inc("hello");
//    allOne.getMaxKey(); // return "hello"
//    allOne.getMinKey(); // return "hello"
//    allOne.inc("leet");
//    allOne.getMaxKey(); // return "hello"
//    allOne.getMinKey(); // return "leet"
//
//
//    Constraints:
//
//    1 <= key.length <= 10
//    key consists of lowercase English letters.
//    It is guaranteed that for each call to dec, key is existing in the data structure.
//    At most 5 * 104 calls will be made to inc, dec, getMaxKey, and getMinKey.