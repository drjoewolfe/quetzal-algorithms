package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class JumpGameIV {
    class Solution {
        public int minJumps(int[] arr) {
            if(arr == null || arr.length < 2) {
                return 0;
            }

            int n = arr.length;

            Map<Integer, List<Integer>> map = new HashMap<>();
            for(int i = 0; i < n; i++) {
                int val = arr[i];
                map.computeIfAbsent(val, key -> new ArrayList<>()).add(i);
            }

            Queue<Integer> currentLevel = new LinkedList<>();
            currentLevel.offer(0);

            Set<Integer> visited = new HashSet<>();
            visited.add(0);

            int distance = 0;
            while(!currentLevel.isEmpty()) {
                Queue<Integer> nextLevel = new LinkedList<>();

                while(!currentLevel.isEmpty()) {
                    int u = currentLevel.poll();
                    if(u == n - 1) {
                        return distance;
                    }

                    int val = arr[u];
                    for(int v : map.get(val)) {
                        if(visited.contains(v)) {
                            continue;
                        }

                        nextLevel.offer(v);
                        visited.add(v);
                    }

                    map.get(val).clear();

                    if(u > 0 && !visited.contains(u - 1)) {
                        nextLevel.offer(u - 1);
                        visited.add(u - 1);
                    }

                    if(u < n - 1 && !visited.contains(u + 1)) {
                        nextLevel.offer(u + 1);
                        visited.add(u + 1);
                    }
                }

                currentLevel = nextLevel;
                distance++;
            }

            return -1;
        }
    }

    class Solution_MLE_1 {
        public int minJumps(int[] arr) {
            if(arr == null || arr.length < 2) {
                return 0;
            }

            int n = arr.length;

            Map<Integer, List<Integer>> map = new HashMap<>();
            for(int i = 0; i < n; i++) {
                int val = arr[i];
                if(!map.containsKey(val)) {
                    map.put(val, new ArrayList<>());
                }

                map.get(val).add(i);
            }

            Map<Integer, List<Integer>> graph = new HashMap<>();
            for(int i = 0; i < n; i++) {
                List<Integer> list = new ArrayList<>();
                if(i < n - 1) {
                    list.add(i + 1);
                }

                if(i > 0) {
                    list.add(i - 1);
                }

                int val = arr[i];
                list.addAll(map.get(val));

                graph.put(i, list);
            }

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(0);

            Set<Integer> visited = new HashSet<>();
            visited.add(0);

            int distance = 1;
            while(!queue.isEmpty()) {
                int size = queue.size();
                for(int i = 0; i < size; i++) {
                    int u = queue.poll();

                    for(int v : graph.get(u)) {
                        if(visited.contains(v)) {
                            continue;
                        }

                        if(v == n - 1) {
                            return distance;
                        }

                        queue.offer(v);
                        visited.add(v);
                    }
                }

                distance++;
            }

            return -1;
        }
    }


    class Solution_TLE_1 {
        public int minJumps(int[] arr) {
            if(arr == null || arr.length < 2) {
                return 0;
            }

            int n = arr.length;
            if(n == 2) {
                return 1;
            }

            Map<Integer, List<Integer>> map = new HashMap<>();
            for(int i = 0; i < n; i++) {
                int a = arr[i];
                if(!map.containsKey(a)) {
                    map.put(a, new ArrayList<>());
                }

                List<Integer> list = map.get(a);
                list.add(i);
            }

            Set<Integer> visited = new HashSet<>();
            Map<Integer, Integer> memo = new HashMap<>();
            return computeMinJumps(arr, arr.length - 1, 0, map, visited, memo);
        }

        private Integer computeMinJumps(int[] arr, int index, int currentJumps, Map<Integer, List<Integer>> map, Set<Integer> visited, Map<Integer, Integer> memo) {
            if(index < 0 || index >= arr.length || visited.contains(index)) {
                return null;
            }

            if(memo.containsKey(index)) {
                return memo.get(index);
            }

            if(index == 0) {
                return 0;
            }

            visited.add(index);

            Integer minJumps = Integer.MAX_VALUE;
            Integer leftOne = computeMinJumps(arr, index - 1, currentJumps + 1, map, visited, memo);
            if(leftOne != null) {
                minJumps = Math.min(minJumps, leftOne);
            }

            Integer rightOne = computeMinJumps(arr, index + 1, currentJumps + 1, map, visited, memo);
            if(rightOne != null) {
                minJumps = Math.min(minJumps, rightOne);
            }

            for(int nextIndex : map.get(arr[index])) {
                Integer pJump = computeMinJumps(arr, nextIndex, currentJumps + 1, map, visited, memo);
                if(pJump != null) {
                    minJumps = Math.min(minJumps, pJump);
                }
            }

            visited.remove(index);
            if(minJumps != null) {
                minJumps++;
            }

            memo.put(index, minJumps);
            System.out.println(memo);
            return minJumps;
        }

        private int min(int ...values) {
            int minVal = Integer.MAX_VALUE;
            for(int val : values) {
                minVal = Math.min(minVal, val);
            }

            return minVal;
        }

        class Pair {
            int x;
            int y;

            public Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public boolean equals(Object o) {
                if(o instanceof Pair) {
                    Pair other = (Pair) o;
                    return this.x == other.x && this.y == other.y;
                }

                return false;
            }

            public int hashcode() {
                int hash = 17;

                hash *= 31;
                hash += x;

                hash *= 31;
                hash += y;

                return hash;
            }
        }
    }

    class Solution_Recursive {
        private int minimumJumps = Integer.MAX_VALUE;

        public int minJumps(int[] arr) {
            if(arr == null || arr.length < 2) {
                return 0;
            }

            int n = arr.length;
            if(n == 2) {
                return 1;
            }

            Map<Integer, List<Integer>> map = new HashMap<>();
            for(int i = 0; i < n; i++) {
                int a = arr[i];
                if(!map.containsKey(a)) {
                    map.put(a, new ArrayList<>());
                }

                List<Integer> list = map.get(a);
                list.add(i);
            }

            Set<Integer> visited = new HashSet<>();

            minimumJumps = Integer.MAX_VALUE;
            computeMinJumps(arr, 0, 0, map, visited);

            return minimumJumps;
        }

        private void computeMinJumps(int[] arr, int index, int currentJumps, Map<Integer, List<Integer>> map, Set<Integer> visited) {
            if(index < 0 || index >= arr.length || visited.contains(index)) {
                return;
            }

            // System.out.println(index + " - " + currentJumps);
            if(index == arr.length - 1) {
                minimumJumps = Math.min(minimumJumps, currentJumps);
                // System.out.println("Set min jumps: " + minimumJumps);
                return;
            }

            visited.add(index);
            computeMinJumps(arr, index - 1, currentJumps + 1, map, visited);
            computeMinJumps(arr, index + 1, currentJumps + 1, map, visited);
            for(int nextIndex : map.get(arr[index])) {
                computeMinJumps(arr, nextIndex, currentJumps + 1, map, visited);
            }

            visited.remove(index);
        }
    }

// [100,-23,-23,404,100,23,23,23,3,404]
// [68,-94,-44,-18,-1,18,-87,29,-6,-87,-27,37,-57,7,18,68,-59,29,7,53,-27,-59,18,-1,18,-18,-59,-1,-18,-84,-20,7,7,-87,-18,-84,-20,-27]
}

//    1345. Jump Game IV
//    Hard
//    Given an array of integers arr, you are initially positioned at the first index of the array.
//
//    In one step you can jump from index i to index:
//
//    i + 1 where: i + 1 < arr.length.
//    i - 1 where: i - 1 >= 0.
//    j where: arr[i] == arr[j] and i != j.
//    Return the minimum number of steps to reach the last index of the array.
//
//    Notice that you can not jump outside of the array at any time.
//
//
//
//    Example 1:
//
//    Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
//    Output: 3
//    Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
//    Example 2:
//
//    Input: arr = [7]
//    Output: 0
//    Explanation: Start index is the last index. You do not need to jump.
//    Example 3:
//
//    Input: arr = [7,6,9,6,9,6,9,7]
//    Output: 1
//    Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
//
//
//    Constraints:
//
//    1 <= arr.length <= 5 * 104
//    -108 <= arr[i] <= 108