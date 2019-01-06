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

    public static Set<String> getAllSubsequencesIterative(String str) {
        // Note: If all elements of the string are unique, there are 2^n -1 subsequences
        if(str == null
            || str.length() == 0) {
            return null;
        }

        Set<String> allSubsequences = new HashSet<>();
        int maxSubsequences = (int) Math.pow(2, str.length());
        for (int i = 1; i < maxSubsequences; i++) {
            String subsequence = getSubsequenceForMask(str, i);
            if(!allSubsequences.contains(subsequence)) {
                allSubsequences.add(subsequence);
            }
        }

        return allSubsequences;
    }

    private static String getSubsequenceForMask(String str, int mask) {
        // Mask is in binary
        StringBuilder subsequence = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if((mask & (1 << i)) > 0) {
                subsequence.append(str.charAt(i));
            }
        }

        return subsequence.toString();
    }

    public static Set<String> getAllSubstrings(String str) {
        // Note: If all elements of the string are unique, there are n * (n+1) / 2 substrings
        Set<String> allSubStrings = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                var subString = str.substring(i, j + 1);
                if(!allSubStrings.contains(subString)) {
                    allSubStrings.add(subString);
                }
            }
        }

        return allSubStrings;
    }

	public static String getSumString(String str1, String str2) {
		Integer i1 = Integer.parseInt(str1);
		Integer i2 = Integer.parseInt(str2);
		
		Integer i3 = i1 + i2;		
		return i3.toString();
	}
}
