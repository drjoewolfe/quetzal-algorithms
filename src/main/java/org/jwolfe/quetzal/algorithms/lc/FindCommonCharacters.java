package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class FindCommonCharacters {
    class Solution {
        public List<String> commonChars(String[] A) {
            List<String> commonCharacters = new ArrayList<>();
            if(A == null || A.length == 0) {
                return commonCharacters;
            }

            int n = A.length;
            int[] commonCharacterCounts = new int[26];
            int[] currentCharacterCounts = new int[26];

            String word = A[0];
            updateCounts(word, commonCharacterCounts);

            for(int i = 1; i < n; i++) {
                word = A[i];

                Arrays.fill(currentCharacterCounts, 0);
                updateCounts(word, currentCharacterCounts);

                for(int j = 0; j < 26; j++) {
                    commonCharacterCounts[j] = Math.min(commonCharacterCounts[j], currentCharacterCounts[j]);
                }
            }

            for(int j = 0; j < 26; j++) {
                while(commonCharacterCounts[j] > 0) {
                    commonCharacters.add(String.valueOf((char) (j + 'a')));
                    commonCharacterCounts[j]--;
                }
            }

            return commonCharacters;
        }

        private void updateCounts(String word, int[] counts) {
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int x = c - 'a';

                counts[x]++;
            }
        }
    }

    class Solution_Correct_1 {
        public List<String> commonChars(String[] A) {
            List<String> commonCharacters = new ArrayList<>();
            if(A == null || A.length == 0) {
                return commonCharacters;
            }

            Map<Character, Integer> frequencies = new HashMap<>();
            Map<Character, Integer> common = new HashMap<>();

            for(int i = 0; i < A[0].length(); i++) {
                char c = A[0].charAt(i);
                frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
            }

            for(int i = 1; i < A.length; i++) {
                String string = A[i];
                common.clear();

                for(int j = 0; j < string.length(); j++) {
                    char c = string.charAt(j);

                    if(frequencies.containsKey(c) && frequencies.get(c) > 0) {
                        common.put(c, common.getOrDefault(c, 0) + 1);
                        frequencies.put(c, frequencies.get(c) - 1);
                    }
                }

                frequencies = new HashMap<>(common);
            }

            for(Character c : frequencies.keySet()) {
                for(int i = 0; i < frequencies.get(c); i++) {
                    commonCharacters.add(Character.toString(c));
                }
            }

            return commonCharacters;
        }
    }
}

//    1002. Find Common Characters
//    Easy
//    Given a string array words, return an array of all characters that show up in all strings within the words (including duplicates). You may return the answer in any order.
//
//
//
//    Example 1:
//
//    Input: words = ["bella","label","roller"]
//    Output: ["e","l","l"]
//    Example 2:
//
//    Input: words = ["cool","lock","cook"]
//    Output: ["c","o"]
//
//
//    Constraints:
//
//    1 <= words.length <= 100
//    1 <= words[i].length <= 100
//    words[i] consists of lowercase English letters.