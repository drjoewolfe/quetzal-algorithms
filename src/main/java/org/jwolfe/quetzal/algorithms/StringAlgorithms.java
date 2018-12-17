package org.jwolfe.quetzal.algorithms;

import java.util.HashSet;
import java.util.Set;

public class StringAlgorithms {
    public static Set<String> getAllSubsequences(String str) {
        // Note: If all elements of the string are unique, there are 2^n -1 subsequences

        Set<String> subsequences = new HashSet<>();
        constructAllSubsequences(str, subsequences);
        return subsequences;
    }

    private static void constructAllSubsequences(String str, Set<String> subsequences) {
        if(str == null
            || str.length() == 0) {
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            for (int j = str.length(); j > i ; j--) {
                String subStr =str.substring(i, j);
                if(!subsequences.contains(subStr)) {
                    subsequences.add(subStr);
                }

                for (int k = 1; k < subStr.length() - 1; k++) {
                    StringBuilder sb = new StringBuilder(subStr);
                    sb.deleteCharAt(k);

                    String tempStr = sb.toString();
                    if(!subsequences.contains(tempStr)) {
                        constructAllSubsequences(tempStr, subsequences);
                    }
                }
            }
        }
    }
}
