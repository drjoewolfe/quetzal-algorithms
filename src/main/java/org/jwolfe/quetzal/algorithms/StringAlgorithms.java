package org.jwolfe.quetzal.algorithms;

import java.util.*;

public class StringAlgorithms {
    public static Set<String> getAllSubsequences(String str) {
        // Note: If all elements of the string are unique, there are 2^n -1 subsequences

        Set<String> subsequences = new HashSet<>();
        constructAllSubsequences(str, subsequences);
        return subsequences;
    }

    private static void constructAllSubsequences(String str, Set<String> subsequences) {
        if (str == null
                || str.length() == 0) {
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            for (int j = str.length(); j > i; j--) {
                String subStr = str.substring(i, j);
                if (!subsequences.contains(subStr)) {
                    subsequences.add(subStr);
                }

                for (int k = 1; k < subStr.length() - 1; k++) {
                    StringBuilder sb = new StringBuilder(subStr);
                    sb.deleteCharAt(k);

                    String tempStr = sb.toString();
                    if (!subsequences.contains(tempStr)) {
                        constructAllSubsequences(tempStr, subsequences);
                    }
                }
            }
        }
    }

    public static Set<String> getAllSubsequencesIterative(String str) {
        // Note: If all elements of the string are unique, there are 2^n -1 subsequences
        if (str == null
                || str.length() == 0) {
            return null;
        }

        Set<String> allSubsequences = new HashSet<>();
        int maxSubsequences = (int) Math.pow(2, str.length());
        for (int i = 1; i < maxSubsequences; i++) {
            String subsequence = getSubsequenceForMask(str, i);
            if (!allSubsequences.contains(subsequence)) {
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
            if ((mask & (1 << i)) > 0) {
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
                if (!allSubStrings.contains(subString)) {
                    allSubStrings.add(subString);
                }
            }
        }

        return allSubStrings;
    }

    public static List<String> getAllSubStringsInLexicographicOrder(String str) {
        // Power set in lexicographic order
        char[] characters = str.toCharArray();
        Arrays.sort(characters);

        String sortedStr = new String(characters);
        int n = sortedStr.length();

        StringBuilder buffer = new StringBuilder();
        List<String> allSubstrings = new ArrayList<>();
        generateAllSubStringsInLexicographicOrder(sortedStr, -1, n - 1, buffer, allSubstrings);

        return allSubstrings;
    }

    private static void generateAllSubStringsInLexicographicOrder(String str, int startIndex, int endIndex, StringBuilder buffer, List<String> allSubstrings) {
        if (startIndex > endIndex) {
            return;
        }

        allSubstrings.add(buffer.toString());
        for (int i = startIndex + 1; i <= endIndex; i++) {
            buffer.append(str.charAt(i));
            generateAllSubStringsInLexicographicOrder(str, i, endIndex, buffer, allSubstrings);

            // Backtrack
            buffer.deleteCharAt(buffer.length() - 1);
        }
    }

    public static String getSumString(String str1, String str2) {
        Integer i1 = Integer.parseInt(str1);
        Integer i2 = Integer.parseInt(str2);

        Integer i3 = i1 + i2;
        return i3.toString();
    }

    public static int getUniqueCharacterCount(StringBuilder sb) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < sb.length(); i++) {
            set.add(sb.charAt(i));
        }

        return set.size();
    }

    public static int getUniqueCharacterCount(String str) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            set.add(str.charAt(i));
        }

        return set.size();
    }

    public static boolean isBinaryString(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '0' && str.charAt(i) != '1') {
                // Not a proper binary string
                return false;
            }
        }

        return true;
    }

    public static boolean isPalidrome(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        if (str.length() == 1) {
            return true;
        }

        int n = str.length();
        int i = 0;
        int j = n - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    public static boolean isPalidrome(String str, int startIndex, int endIndex) {
        if (str == null || str.length() < 2) {
            return false;
        }

        int n = str.length();
        if (startIndex < 0 || endIndex >= n) {
            return false;
        }

        int i = startIndex;
        int j = endIndex;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    public static List<List<String>> getAllPalindromePartitions(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }

        List<List<String>> allPartitions = new ArrayList<>();
        List<String> currentPartition = new ArrayList<>();

        generateAllPalindromePartitions(str, str.length(), 0, currentPartition, allPartitions);

        return allPartitions;
    }

    private static void generateAllPalindromePartitions(String str, int length, int index, List<String> currentPartition, List<List<String>> allPartitions) {
        if (index >= length) {
            allPartitions.add(new ArrayList<>(currentPartition));
            return;
        }

        int paritionIndex = 0;
        for (int i = index; i < length; i++) {
            String subStr = str.substring(index, i + 1);
            if (isPalidrome(subStr)) {
                currentPartition.add(subStr);
                paritionIndex = currentPartition.size() - 1;
                generateAllPalindromePartitions(str, length, i + 1, currentPartition, allPartitions);

                currentPartition.remove(paritionIndex);
            }
        }
    }

    public static String reverse(String str) {
        if (str == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }

    public static int getOverlapLength(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return 0;
        }

        int m = str1.length();
        int n = str2.length();

        // Case: one string completely contains the other
        if (m > n && str1.indexOf(str2) != -1) {
            return n;
        } else if (str2.indexOf(str1) != -1) {
            return m;
        }

        int overlapLength = 0;

        // Case: Prefix from str1 to Suffix from str2
        for (int i = 1; i <= Math.min(m, n); i++) {
            String subStr1 = str1.substring(0, i);
            String subStr2 = str2.substring(n - i, n);

            if (subStr1.equals(subStr2)) {
                overlapLength = Math.max(overlapLength, i);
            }
        }

        // Case: Suffix from str1 to Prefix from str2
        for (int i = 1; i <= Math.min(m, n); i++) {
            String subStr1 = str1.substring(m - i, m);
            String subStr2 = str2.substring(0, i);

            if (subStr1.equals(subStr2)) {
                overlapLength = Math.max(overlapLength, i);
            }
        }

        return overlapLength;
    }

    public static String mergeStrings(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return null;
        }

        int m = str1.length();
        int n = str2.length();

        // Case: one string completely contains the other
        if (m > n && str1.indexOf(str2) != -1) {
            return str1;
        } else if (str2.indexOf(str1) != -1) {
            return str2;
        }

        int overlapLength = 0;
        String mergeLeft = null;
        String mergeRight = null;


        // Case: Prefix from str1 to Suffix from str2
        for (int i = 1; i <= Math.min(m, n); i++) {
            String subStr1 = str1.substring(0, i);
            String subStr2 = str2.substring(n - i, n);

            if (subStr1.equals(subStr2)) {
                if (overlapLength < i) {
                    overlapLength = i;
                    mergeLeft = str2;
                    mergeRight = str1.substring(i, m);
                }
            }
        }

        // Case: Suffix from str1 to Prefix from str2
        for (int i = 1; i <= Math.min(m, n); i++) {
            String subStr1 = str1.substring(m - i, m);
            String subStr2 = str2.substring(0, i);

            if (subStr1.equals(subStr2)) {
                if (overlapLength < i) {
                    overlapLength = i;
                    mergeLeft = str1;
                    mergeRight = str2.substring(i, n);
                }
            }
        }

        if (overlapLength == 0) {
            return str1 + str2;
        }

        return mergeLeft + mergeRight;
    }
}
