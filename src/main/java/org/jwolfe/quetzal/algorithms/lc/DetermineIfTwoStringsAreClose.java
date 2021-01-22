package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class DetermineIfTwoStringsAreClose {
    public boolean closeStrings(String word1, String word2) {
        if(word1 == null || word2 == null || word1.length() != word2.length()) {
            return false;
        }

        int n = word1.length();
        if(n < 1) {
            return true;
        }

        Map<Character, Integer> aCounters = new HashMap<>();
        Map<Character, Integer> bCounters = new HashMap<>();

        for(int i = 0; i < n; i++) {
            char a = word1.charAt(i);
            aCounters.put(a, aCounters.getOrDefault(a, 0) + 1);

            char b = word2.charAt(i);
            bCounters.put(b, bCounters.getOrDefault(b, 0) + 1);
        }

        Map<Integer, Integer> checks = new HashMap<>();
        for(var aEntry : aCounters.entrySet()) {
            Character key = aEntry.getKey();
            if(!bCounters.containsKey(key)) {
                return false;
            }

            Integer count = aEntry.getValue();
            checks.put(count, checks.getOrDefault(count, 0) + 1);
        }

        for(var bEntry : bCounters.entrySet()) {
            Integer count = bEntry.getValue();
            checks.put(count, checks.getOrDefault(count, 0) - 1);
        }

        for(var entry : checks.entrySet()) {
            if(entry.getValue() != 0) {
                return false;
            }
        }

        return true;
    }
}
