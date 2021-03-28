package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class ReconstructOriginalDigitsFromEnglish {
    class Solution {
        public String originalDigits(String s) {
            if(s == null || s.length() == 0) {
                return s;
            }

            Map<Character, Integer> counts = new HashMap<>();
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                counts.put(c, counts.getOrDefault(c, 0) + 1);
            }

            List<Integer> numbers = new ArrayList<>();
            while(counts.size() > 0) {
                String match = null;

                if(counts.containsKey('w')) {
                    match = "two";
                    numbers.add(2);
                } else if(counts.containsKey('u')) {
                    match = "four";
                    numbers.add(4);
                } else if(counts.containsKey('x')) {
                    match = "six";
                    numbers.add(6);
                } else if(counts.containsKey('g')) {
                    match = "eight";
                    numbers.add(8);
                } else if(counts.containsKey('z')) {
                    match = "zero";
                    numbers.add(0);
                } else if(counts.containsKey('o') && counts.containsKey('n')) {
                    match = "one";
                    numbers.add(1);
                } else if(counts.containsKey('h') && counts.containsKey('r')) {
                    match = "three";
                    numbers.add(3);
                } else if(counts.containsKey('f') && counts.containsKey('i')) {
                    match = "five";
                    numbers.add(5);
                } else if(counts.containsKey('s') && counts.containsKey('v')) {
                    match = "seven";
                    numbers.add(7);
                } else if(counts.containsKey('n') && counts.containsKey('i')) {
                    match = "nine";
                    numbers.add(9);
                } else {
                    throw new RuntimeException("Invalid string");
                }

                if(!decrementCounters(counts, match)) {
                    throw new RuntimeException("Invalid string");
                }
            }

            Collections.sort(numbers);
            StringBuilder sb = new StringBuilder();
            for(int a : numbers) {
                sb.append(a);
            }

            return sb.toString();
        }

        private boolean decrementCounters(Map<Character, Integer> counts, String s) {
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(!counts.containsKey(c)) {
                    return false;
                }

                if(counts.get(c) > 1) {
                    counts.put(c, counts.get(c) - 1);
                } else {
                    counts.remove(c);
                }
            }

            return true;
        }

        // one
        // two
        // three
        // four
        // five
        // six
        // seven
        // eight
        // nine
        // ten

        // one      -   (o + n)
        // two      -   w
        // three    -   (h + r)
        // four     -   u
        // five     -   (f + i)
        // six      -   x
        // seven    -   (s + v)
        // eight    -   g
        // nine     -   (n + i)
        // zero     -   z
    }

    class Solution_Incorrect {
        public String originalDigits(String s) {
            Map<String, Integer> numberMap = getNumberMap();
            Map<String, int[]> numberFrequencies = getFrequencyMap(numberMap.keySet());

            int[] frequencies = new int[26];
            for(int i = 0; i < s.length(); i++) {
                frequencies[s.charAt(i) - 'a']++;
            }

            int letterCount = s.length();
            List<Integer> digits = new ArrayList<>();
            while(letterCount > 0) {
                boolean processed = false;
                for(var entry : numberMap.entrySet()) {
                    int count = processEntry(frequencies, entry, numberFrequencies, digits);
                    if(count > 0) {
                        processed = true;
                    }

                    letterCount -= count;
                }

                print(frequencies);
                if(!processed) {
                    return getString(digits);
                }
            }

            return getString(digits);
        }

        private int processEntry(int[] frequencies, Map.Entry<String, Integer> entry, Map<String, int[]> numberFrequencies, List<Integer> digits) {
            String key = entry.getKey();
            int[] keyFrequencies = numberFrequencies.get(key);
            for(int i = 0; i < 26; i++) {
                if(frequencies[i] < keyFrequencies[i]) {
                    return 0;
                }
            }

            for(int i = 0; i < 26; i++) {
                frequencies[i] -= keyFrequencies[i];
            }

            digits.add(entry.getValue());
            return key.length();
        }

        private String getString(List<Integer> list) {
            Collections.sort(list);
            StringBuilder builder = new StringBuilder();
            for(Integer a : list) {
                builder.append(a);
            }

            return builder.toString();
        }

        private LinkedHashMap<String, Integer> getNumberMap() {
            LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
            map.put("zero", 0);
            map.put("eight", 8);
            map.put("six", 6);
            map.put("four", 4);
            map.put("three", 3);
            map.put("two", 2);
            map.put("nine", 9);
            map.put("seven", 7);
            map.put("one", 1);
            map.put("five", 5);

            return map;
        }

        private Map<String, int[]> getFrequencyMap(Set<String> set) {
            Map<String, int[]> map = new HashMap<>();

            for(String s : set) {
                int[] frequencies = new int[26];
                for(int i = 0; i < s.length(); i++) {
                    frequencies[s.charAt(i) - 'a']++;
                }

                map.put(s, frequencies);
            }

            return map;
        }

        private void print(int[] counts) {
            for(int a : counts) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

// "owoztneoer"
}

//    423. Reconstruct Original Digits from English
//    Medium
//    Given a non-empty string containing an out-of-order English representation of digits 0-9, output the digits in ascending order.
//
//    Note:
//    Input contains only lowercase English letters.
//    Input is guaranteed to be valid and can be transformed to its original digits. That means invalid inputs such as "abc" or "zerone" are not permitted.
//    Input length is less than 50,000.
//    Example 1:
//    Input: "owoztneoer"
//
//    Output: "012"
//    Example 2:
//    Input: "fviefuro"
//
//    Output: "45"
