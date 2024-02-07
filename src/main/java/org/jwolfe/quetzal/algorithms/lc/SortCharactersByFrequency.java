package org.jwolfe.quetzal.algorithms.lc;

import org.jwolfe.quetzal.library.general.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class SortCharactersByFrequency {
    class Solution {
        public String frequencySort(String s) {
            if(s == null || s.length() < 2) {
                return s;
            }

            int[] counts = new int[76];
            for(int i = 0; i < s.length(); i++) {
                counts[s.charAt(i) - '0']++;
            }

            List<Pair<Character, Integer>> frequencyPairs = new ArrayList<>();
            for(int i = 0; i < 76; i++) {
                if(counts[i] == 0) {
                    continue;
                }

                char c = (char) (i + '0');
                frequencyPairs.add(new Pair<>(c, counts[i]));
            }

            Collections.sort(frequencyPairs, (a, b) -> b.getValue() - a.getValue());

            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < frequencyPairs.size(); i++) {
                var pair = frequencyPairs.get(i);
                char c = pair.getKey();
                int count = pair.getValue();

                for(int j = 0; j < count; j++) {
                    builder.append(c);
                }
            }

            return builder.toString();
        }
    }

    class Solution_Correct_1 {
        public String frequencySort(String s) {
            if(s == null || s.length() < 2) {
                return s;
            }

            Map<Character, Integer> frequency = new HashMap<>();
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                frequency.put(c, frequency.getOrDefault(c, 0) + 1);
            }

            Map<Character, Integer> sortedFrequency = frequency.entrySet().stream()
                    .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            StringBuilder builder = new StringBuilder();
            for(Map.Entry<Character, Integer> entry : sortedFrequency.entrySet()) {
                char c = entry.getKey();
                int count = entry.getValue();
                for(int i = 0; i < count; i++) {
                    builder.append(c);
                }
            }

            return builder.toString();
        }
    }
}

//    451. Sort Characters By Frequency
//    Medium
//    Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.
//
//    Return the sorted string. If there are multiple answers, return any of them.
//
//
//
//    Example 1:
//
//    Input: s = "tree"
//    Output: "eert"
//    Explanation: 'e' appears twice while 'r' and 't' both appear once.
//    So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
//    Example 2:
//
//    Input: s = "cccaaa"
//    Output: "aaaccc"
//    Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
//    Note that "cacaca" is incorrect, as the same characters must be together.
//    Example 3:
//
//    Input: s = "Aabb"
//    Output: "bbAa"
//    Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
//    Note that 'A' and 'a' are treated as two different characters.
//
//
//    Constraints:
//
//    1 <= s.length <= 5 * 105
//    s consists of uppercase and lowercase English letters and digits.