package org.jwolfe.quetzal.algorithms.lc;

import java.util.Collections;
import java.util.List;

public class LongestWordInDictionaryThroughDeleting {
    public String findLongestWord(String s, List<String> d) {
        if(s == null || s.length() == 0 || d == null || d.size() == 0) {
            return "";
        }

        Collections.sort(d, (a, b) -> {
            if(a.length() == b.length()) {
                return a.compareTo(b);
            }

            return b.length() - a.length();
        });

        for(String str : d) {
            if(isSubsequence(s, str)) {
                return str;
            }
        }

        return "";
    }

    private boolean isSubsequence(String s, String t) {
        if(t.length() > s.length()) {
            return false;
        }

        int j = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == t.charAt(j)) {
                j++;
            }

            if(j == t.length()) {
                return true;
            }
        }

        return false;
    }
}

//    524. Longest Word in Dictionary through Deleting
//    Medium
//    Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.
//
//    Example 1:
//    Input:
//    s = "abpcplea", d = ["ale","apple","monkey","plea"]
//
//    Output:
//    "apple"
//    Example 2:
//    Input:
//    s = "abpcplea", d = ["a","b","c"]
//
//    Output:
//    "a"
//    Note:
//    All the strings in the input will only contain lower-case letters.
//    The size of the dictionary won't exceed 1,000.
//    The length of all the strings in the input won't exceed 1,000.