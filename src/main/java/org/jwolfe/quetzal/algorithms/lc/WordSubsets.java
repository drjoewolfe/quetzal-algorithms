package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordSubsets {
    class Solution {
        public List<String> wordSubsets(String[] A, String[] B) {
            List<String> universals = new ArrayList<>();
            if (A == null || A.length == 0) {
                return universals;
            }

            if (B == null || B.length == 0) {
                for (String word : A) {
                    universals.add(word);
                }

                return universals;
            }

            int[] bFrequencies = new int[26];
            for (String word : B) {
                int[] frequencies = countFrequencies(word);
                for (int i = 0; i < 26; i++) {
                    bFrequencies[i] = Math.max(bFrequencies[i], frequencies[i]);
                }
            }

            for (String word : A) {
                int[] frequencies = countFrequencies(word);
                boolean isUniversal = true;
                for (int i = 0; i < 26; i++) {
                    if (frequencies[i] < bFrequencies[i]) {
                        isUniversal = false;
                        break;
                    }
                }

                if (isUniversal) {
                    universals.add(word);
                }
            }

            return universals;
        }

        private int[] countFrequencies(String word) {
            int[] frequencies = new int[26];
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                frequencies[c - 'a']++;
            }

            return frequencies;
        }
    }

    class Solution_Correct_1 {
        public List<String> wordSubsets(String[] A, String[] B) {
            List<String> universals = new ArrayList<>();
            if (A == null || A.length == 0) {
                return universals;
            }

            if (B == null || B.length == 0) {
                for (String a : A) {
                    universals.add(a);
                }

                return universals;
            }

            int[] bFrequencies = new int[26];
            for (String b : B) {
                int[] frequencies = new int[26];
                for (int j = 0; j < b.length(); j++) {
                    frequencies[b.charAt(j) - 'a']++;
                }

                for (int j = 0; j < 26; j++) {
                    bFrequencies[j] = Math.max(bFrequencies[j], frequencies[j]);
                }
            }

            int[] aFrequencies = new int[26];
            for (String a : A) {
                Arrays.fill(aFrequencies, 0);
                for (int i = 0; i < a.length(); i++) {
                    aFrequencies[a.charAt(i) - 'a']++;
                }

                boolean isUniversal = true;
                for (int j = 0; j < 26; j++) {
                    if (bFrequencies[j] > aFrequencies[j]) {
                        isUniversal = false;
                        break;
                    }
                }

                if (isUniversal) {
                    universals.add(a);
                }
            }

            return universals;
        }
    }

    class Solution_Classic {
        public List<String> wordSubsets(String[] A, String[] B) {
            List<String> universals = new ArrayList<>();
            if (A == null || A.length == 0) {
                return universals;
            }

            if (B == null || B.length == 0) {
                for (String a : A) {
                    universals.add(a);
                }

                return universals;
            }

            List<int[]> bFrequencyList = new ArrayList<>();
            for (String b : B) {
                int[] bFrequencies = new int[26];
                for (int j = 0; j < b.length(); j++) {
                    bFrequencies[b.charAt(j) - 'a']++;
                }

                bFrequencyList.add(bFrequencies);
            }

            int[] aFrequencies = new int[26];
            for (String a : A) {
                Arrays.fill(aFrequencies, 0);
                for (int i = 0; i < a.length(); i++) {
                    aFrequencies[a.charAt(i) - 'a']++;
                }

                boolean isUniversal = true;
                for (int[] bFrequencies : bFrequencyList) {
                    for (int j = 0; j < 26; j++) {
                        if (bFrequencies[j] > aFrequencies[j]) {
                            isUniversal = false;
                            break;
                        }
                    }

                    if (!isUniversal) {
                        break;
                    }
                }

                if (isUniversal) {
                    universals.add(a);
                }
            }

            return universals;
        }
    }

// ["amazon","apple","facebook","google","leetcode"]
// ["e","o"]
}

//    916. Word Subsets
//    Medium
//    We are given two arrays A and B of words.  Each word is a string of lowercase letters.
//
//    Now, say that word b is a subset of word a if every letter in b occurs in a, including multiplicity.  For example, "wrr" is a subset of "warrior", but is not a subset of "world".
//
//    Now say a word a from A is universal if for every b in B, b is a subset of a.
//
//    Return a list of all universal words in A.  You can return the words in any order.
//
//
//
//    Example 1:
//
//    Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","o"]
//    Output: ["facebook","google","leetcode"]
//    Example 2:
//
//    Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["l","e"]
//    Output: ["apple","google","leetcode"]
//    Example 3:
//
//    Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","oo"]
//    Output: ["facebook","google"]
//    Example 4:
//
//    Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["lo","eo"]
//    Output: ["google","leetcode"]
//    Example 5:
//
//    Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["ec","oc","ceo"]
//    Output: ["facebook","leetcode"]
//
//
//    Note:
//
//    1 <= A.length, B.length <= 10000
//    1 <= A[i].length, B[i].length <= 10
//    A[i] and B[i] consist only of lowercase letters.
//    All words in A[i] are unique: there isn't i != j with A[i] == A[j].
