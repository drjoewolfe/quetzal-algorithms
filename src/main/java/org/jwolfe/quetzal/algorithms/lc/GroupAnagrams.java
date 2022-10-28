package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class GroupAnagrams {
    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            List<List<String>> results = new ArrayList<>();
            if(strs == null || strs.length == 0) {
                return results;
            }

            Map<String, List<String>> map = new HashMap<>();
            for(String str : strs) {
                String key = sort(str);
                if(!map.containsKey(key)) {
                    map.put(key, new ArrayList<>());
                }

                map.get(key).add(str);
            }

            for(var list : map.values()) {
                results.add(list);
            }

            return results;
        }

        private String sort(String str) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);

            return new String(arr);
        }
    }

    class Solution_Correct_1 {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> anagramMap = new HashMap<>();

            for(String s : strs) {
                String key = sort(s);

                if(!anagramMap.containsKey(key)) {
                    anagramMap.put(key, new ArrayList<>());
                }

                List<String> anagrams = anagramMap.get(key);
                anagrams.add(s);
            }

            List<List<String>> result = new ArrayList();
            for(Map.Entry<String, List<String>> entry : anagramMap.entrySet()) {
                result.add(entry.getValue());
            }

            return result;
        }

        private String sort(String s) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);

            return new String(arr);
        }
    }

    class Solution_Correct_2 {
        public List<List<String>> groupAnagrams(String[] strs) {
            List<List<String>> anagrams = new ArrayList<>();
            if(strs == null || strs.length == 0) {
                return anagrams;
            }

            Map<String, List<String>> groups = new HashMap<>();
            for(String s : strs) {
                String repr =  getRepresentative(s);

                if(!groups.containsKey(repr)) {
                    groups.put(repr, new ArrayList<>());
                }

                groups.get(repr).add(s);
            }

            for(var entry : groups.entrySet()) {
                anagrams.add(entry.getValue());
            }

            return anagrams;
        }

        private String getRepresentative(String s) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);

            return new String(arr);
        }
    }
}

//    49. Group Anagrams
//    Medium
//    Given an array of strings strs, group the anagrams together. You can return the answer in any order.
//
//    An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
//
//
//
//    Example 1:
//
//    Input: strs = ["eat","tea","tan","ate","nat","bat"]
//    Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
//    Example 2:
//
//    Input: strs = [""]
//    Output: [[""]]
//    Example 3:
//
//    Input: strs = ["a"]
//    Output: [["a"]]
//
//
//    Constraints:
//
//    1 <= strs.length <= 104
//    0 <= strs[i].length <= 100
//    strs[i] consists of lower-case English letters.
