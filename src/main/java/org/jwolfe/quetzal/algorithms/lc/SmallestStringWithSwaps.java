package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class SmallestStringWithSwaps {
    class Solution {
        public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
            if(s == null || s.length() == 0 || pairs == null || pairs.size() == 0) {
                return s;
            }

            int n = s.length();
            DisjointSets sets = new DisjointSets(n);

            for(var pair : pairs) {
                int u = pair.get(0);
                int v = pair.get(1);

                sets.union(u, v);
            }

            Map<Integer, List<Integer>> partitions = new HashMap<>();
            for(int i = 0; i < n; i++) {
                int ip = sets.find(i);

                if(!partitions.containsKey(ip)) {
                    partitions.put(ip, new ArrayList<>());
                }

                partitions.get(ip).add(i);
            }

            char[] result = new char[n];
            for(var entry : partitions.entrySet()) {
                var list = entry.getValue();

                PriorityQueue<Character> heap = new PriorityQueue<>();
                for(var index : list) {
                    heap.offer(s.charAt(index));
                }

                for(var index : list) {
                    result[index] = heap.poll();
                }
            }

            return new String(result);
        }

        private class DisjointSets {
            int[] parent;
            int[] rank;

            int n;

            public DisjointSets(int n) {
                this.n = n;
                parent = new int[n];
                rank = new int[n];

                for(int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            public void union(int u, int v) {
                int up = find(u);
                int vp = find(v);

                if(rank[up] < rank[vp]) {
                    parent[up] = vp;
                } else if(rank[up] > rank[vp]) {
                    parent[vp] = up;
                } else {
                    parent[up] = vp;
                    rank[vp]++;
                }
            }

            public int find(int u) {
                if(u != parent[u]) {
                    parent[u] = find(parent[u]);
                }

                return parent[u];
            }
        }
    }

    class Solution_TLE {
        public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
            if(s == null || s.length() == 0 || pairs == null || pairs.size() == 0) {
                return s;
            }

            Queue<String> queue = new LinkedList<>();
            queue.offer(s);

            Set<String> visited = new HashSet<>();
            visited.add(s);

            String minString = s;
            while(!queue.isEmpty()) {
                String str = queue.poll();

                for(var pair : pairs) {
                    String next = swap(str, pair);
                    if(visited.contains(next)) {
                        continue;
                    }

                    if(minString.compareTo(next) > 0) {
                        minString = next;
                    }

                    queue.offer(next);
                    visited.add(next);
                }
            }

            return minString;
        }

        private String swap(String s, List<Integer> pair) {
            StringBuilder sb = new StringBuilder(s);

            int left = pair.get(0);
            int right = pair.get(1);

            sb.setCharAt(left, s.charAt(right));
            sb.setCharAt(right, s.charAt(left));

            return sb.toString();
        }
    }


    class Solution_Incorrect {
        public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
            if(s == null || s.length() == 0 || pairs == null || pairs.size() == 0) {
                return "";
            }

            int n = s.length();

            List<List<Integer>> graph = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }

            for(var pair : pairs) {
                graph.get(pair.get(0)).add(pair.get(1));
                graph.get(pair.get(1)).add(pair.get(0));
            }

            boolean[] visited = new boolean[n];
            for(int u = 0; u < n; u++) {
                if(!visited[u]) {
                    dfs(graph, u, visited);
                }
            }

            return s;
        }

        private void dfs(List<List<Integer>> graph, int u, boolean[] visited) {
            visited[u] = true;

            for(int v : graph.get(u)) {
                if(!visited[v]) {
                    continue;
                }

                dfs(graph, v, visited);
            }
        }
    }


// "dcab"
// [[0,3],[1,2]]
}

//    1202. Smallest String With Swaps
//    Medium
//    You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
//
//    You can swap the characters at any pair of indices in the given pairs any number of times.
//
//    Return the lexicographically smallest string that s can be changed to after using the swaps.
//
//
//
//    Example 1:
//
//    Input: s = "dcab", pairs = [[0,3],[1,2]]
//    Output: "bacd"
//    Explaination:
//    Swap s[0] and s[3], s = "bcad"
//    Swap s[1] and s[2], s = "bacd"
//    Example 2:
//
//    Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
//    Output: "abcd"
//    Explaination:
//    Swap s[0] and s[3], s = "bcad"
//    Swap s[0] and s[2], s = "acbd"
//    Swap s[1] and s[2], s = "abcd"
//    Example 3:
//
//    Input: s = "cba", pairs = [[0,1],[1,2]]
//    Output: "abc"
//    Explaination:
//    Swap s[0] and s[1], s = "bca"
//    Swap s[1] and s[2], s = "bac"
//    Swap s[0] and s[1], s = "abc"
//
//
//    Constraints:
//
//    1 <= s.length <= 10^5
//    0 <= pairs.length <= 10^5
//    0 <= pairs[i][0], pairs[i][1] < s.length
//    s only contains lower case English letters.