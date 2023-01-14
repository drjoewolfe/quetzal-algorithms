package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class LexicographicallySmallestEquivalentString {
    class Solution {
        public String smallestEquivalentString(String s1, String s2, String baseStr) {
            if(s1 == null || s1.length() == 0 || s2 == null || s1.length() != s2.length() || baseStr == null || baseStr.length() == 0) {
                return "";
            }

            Map<Character, Set<Character>> graph = new HashMap<>();
            for(int i = 0; i < s1.length(); i++) {
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(i);

                if(!graph.containsKey(c1)) {
                    graph.put(c1, new HashSet<>());
                }

                if(!graph.containsKey(c2)) {
                    graph.put(c2, new HashSet<>());
                }

                graph.get(c1).add(c2);
                graph.get(c2).add(c1);
            }

            Map<Character, TreeSet<Character>> partitionMap = new HashMap<>();
            Set<Character> visited = new HashSet<>();
            for(var u : graph.keySet()) {
                if(visited.contains(u)) {
                    continue;
                }

                TreeSet<Character> partition = new TreeSet<>();
                dfs(graph, u, visited, partition);

                for(var c : partition) {
                    partitionMap.put(c, partition);
                }
            }

            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < baseStr.length(); i++) {
                char c = baseStr.charAt(i);
                if(partitionMap.containsKey(c)) {
                    var rc = partitionMap.get(c).first();
                    builder.append(rc);
                } else {
                    builder.append(c);
                }
            }

            return builder.toString();
        }

        private void dfs(Map<Character, Set<Character>> graph, Character u, Set<Character> visited, Set<Character> partition) {
            visited.add(u);
            partition.add(u);

            for(var v : graph.get(u)) {
                if(visited.contains(v)) {
                    continue;
                }

                dfs(graph, v, visited, partition);
            }
        }
    }

// "parker"
// "morris"
// "parser"

// "leetcode"
// "programs"
// "sourcecode"
}

//    1061. Lexicographically Smallest Equivalent String
//    Medium
//    You are given two strings of the same length s1 and s2 and a string baseStr.
//
//    We say s1[i] and s2[i] are equivalent characters.
//
//    For example, if s1 = "abc" and s2 = "cde", then we have 'a' == 'c', 'b' == 'd', and 'c' == 'e'.
//    Equivalent characters follow the usual rules of any equivalence relation:
//
//    Reflexivity: 'a' == 'a'.
//    Symmetry: 'a' == 'b' implies 'b' == 'a'.
//    Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'.
//    For example, given the equivalency information from s1 = "abc" and s2 = "cde", "acd" and "aab" are equivalent strings of baseStr = "eed", and "aab" is the lexicographically smallest equivalent string of baseStr.
//
//    Return the lexicographically smallest equivalent string of baseStr by using the equivalency information from s1 and s2.
//
//
//
//    Example 1:
//
//    Input: s1 = "parker", s2 = "morris", baseStr = "parser"
//    Output: "makkek"
//    Explanation: Based on the equivalency information in s1 and s2, we can group their characters as [m,p], [a,o], [k,r,s], [e,i].
//    The characters in each group are equivalent and sorted in lexicographical order.
//    So the answer is "makkek".
//    Example 2:
//
//    Input: s1 = "hello", s2 = "world", baseStr = "hold"
//    Output: "hdld"
//    Explanation: Based on the equivalency information in s1 and s2, we can group their characters as [h,w], [d,e,o], [l,r].
//    So only the second letter 'o' in baseStr is changed to 'd', the answer is "hdld".
//    Example 3:
//
//    Input: s1 = "leetcode", s2 = "programs", baseStr = "sourcecode"
//    Output: "aauaaaaada"
//    Explanation: We group the equivalent characters in s1 and s2 as [a,o,e,r,s,c], [l,p], [g,t] and [d,m], thus all letters in baseStr except 'u' and 'd' are transformed to 'a', the answer is "aauaaaaada".
//
//
//    Constraints:
//
//    1 <= s1.length, s2.length, baseStr <= 1000
//    s1.length == s2.length
//    s1, s2, and baseStr consist of lowercase English letters.