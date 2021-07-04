package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountVowelsPermutation {
    class Solution {
        private int MOD = 1_000_000_007;
        public int countVowelPermutation(int n) {
            if(n < 1) {
                return 0;
            }

            long aPrevCount = 1, aCount = 1;
            long ePrevCount = 1, eCount = 1;
            long iPrevCount = 1, iCount = 1;
            long oPrevCount = 1, oCount = 1;
            long uPrevCount = 1, uCount = 1;

            for(int i = 1; i < n; i++) {
                aCount = ePrevCount;
                eCount = (aPrevCount + iPrevCount) % MOD;
                iCount = (aPrevCount + ePrevCount + oPrevCount + uPrevCount) % MOD;
                oCount = (iPrevCount + uPrevCount) % MOD;
                uCount = aPrevCount;

                aPrevCount = aCount;
                ePrevCount = eCount;
                iPrevCount = iCount;
                oPrevCount = oCount;
                uPrevCount = uCount;
            }

            return (int) ((aCount + eCount + iCount + oCount + uCount) % MOD);
        }
    }

    class Solution_DP {
        private int MOD = 1_000_000_007;
        public int countVowelPermutation(int n) {
            if(n < 1) {
                return 0;
            }

            long[] aCount = new long[n];
            long[] eCount = new long[n];
            long[] iCount = new long[n];
            long[] oCount = new long[n];
            long[] uCount = new long[n];

            aCount[0] = eCount[0] = iCount[0] = oCount[0] = uCount[0] = 1;
            for(int i = 1; i < n; i++) {
                aCount[i] = eCount[i - 1];
                eCount[i] = (aCount[i - 1] + iCount[i - 1]) % MOD;
                iCount[i] = (aCount[i - 1] + eCount[i - 1] + oCount[i - 1] + uCount[i - 1]) % MOD;
                oCount[i] = (iCount[i - 1] + uCount[i - 1]) % MOD;
                uCount[i] = aCount[i - 1];
            }

            return (int) ((aCount[n - 1] + eCount[n - 1] + iCount[n - 1] + oCount[n - 1] + uCount[n - 1]) % MOD);
        }
    }

    class Solution_Memoized {
        private int MOD = 1_000_000_007;
        public int countVowelPermutation(int n) {
            if(n < 1) {
                return 0;
            }

            Map<Character, List<Character>> allowedSuccessors = new HashMap<>();
            allowedSuccessors.put('a', List.of('e'));
            allowedSuccessors.put('e', List.of('a', 'i'));
            allowedSuccessors.put('i', List.of('a', 'e', 'o', 'u'));
            allowedSuccessors.put('o', List.of('i', 'u'));
            allowedSuccessors.put('u', List.of('a'));

            Map<Integer, Map<Character, Integer>> memo = new HashMap<>();

            long count = 0;
            count += countVowelPermutation(n - 1, 'a', allowedSuccessors, memo);
            count %= MOD;

            count += countVowelPermutation(n - 1, 'e', allowedSuccessors, memo);
            count %= MOD;

            count += countVowelPermutation(n - 1, 'i', allowedSuccessors, memo);
            count %= MOD;

            count += countVowelPermutation(n - 1, 'o', allowedSuccessors, memo);
            count %= MOD;

            count += countVowelPermutation(n - 1, 'u', allowedSuccessors, memo);
            count %= MOD;

            return (int) count;
        }

        private int countVowelPermutation(int n, Character lastChar, Map<Character, List<Character>> allowedSuccessors, Map<Integer, Map<Character, Integer>> memo) {
            if(n == 0) {
                return 1;
            }

            if(memo.containsKey(n) && memo.get(n).containsKey(lastChar)) {
                return memo.get(n).get(lastChar);
            }

            long count = 0;
            for(Character nextChar : allowedSuccessors.get(lastChar)) {
                count += countVowelPermutation(n - 1, nextChar, allowedSuccessors, memo);
                count %= MOD;
            }

            if(!memo.containsKey(n)) {
                memo.put(n, new HashMap<>());
            }

            int intCount = (int) count;
            memo.get(n).put(lastChar, intCount);
            return intCount;
        }
    }

    class Solution_Recursive {
        private int MOD = 1_000_000_007;
        public int countVowelPermutation(int n) {
            if(n < 1) {
                return 0;
            }

            Map<Character, List<Character>> allowedSuccessors = new HashMap<>();
            allowedSuccessors.put('a', List.of('e'));
            allowedSuccessors.put('e', List.of('a', 'i'));
            allowedSuccessors.put('i', List.of('a', 'e', 'o', 'u'));
            allowedSuccessors.put('o', List.of('i', 'u'));
            allowedSuccessors.put('u', List.of('a'));

            int count = 0;
            count += countVowelPermutation(n - 1, 'a', allowedSuccessors) % MOD;
            count += countVowelPermutation(n - 1, 'e', allowedSuccessors) % MOD;
            count += countVowelPermutation(n - 1, 'i', allowedSuccessors) % MOD;
            count += countVowelPermutation(n - 1, 'o', allowedSuccessors) % MOD;
            count += countVowelPermutation(n - 1, 'u', allowedSuccessors) % MOD;

            return count;
        }

        private int countVowelPermutation(int n, Character lastChar, Map<Character, List<Character>> allowedSuccessors) {
            if(n == 0) {
                return 1;
            }

            int count = 0;
            for(Character nextChar : allowedSuccessors.get(lastChar)) {
                count += countVowelPermutation(n - 1, nextChar, allowedSuccessors) % MOD;
            }

            return count;
        }
    }

// 1
// 2
// 5
// 20
// 144
}

//    1220. Count Vowels Permutation
//    Hard
//    Given an integer n, your task is to count how many strings of length n can be formed under the following rules:
//
//    Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
//    Each vowel 'a' may only be followed by an 'e'.
//    Each vowel 'e' may only be followed by an 'a' or an 'i'.
//    Each vowel 'i' may not be followed by another 'i'.
//    Each vowel 'o' may only be followed by an 'i' or a 'u'.
//    Each vowel 'u' may only be followed by an 'a'.
//    Since the answer may be too large, return it modulo 10^9 + 7.
//
//
//
//    Example 1:
//
//    Input: n = 1
//    Output: 5
//    Explanation: All possible strings are: "a", "e", "i" , "o" and "u".
//    Example 2:
//
//    Input: n = 2
//    Output: 10
//    Explanation: All possible strings are: "ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" and "ua".
//    Example 3:
//
//    Input: n = 5
//    Output: 68
//
//
//    Constraints:
//
//    1 <= n <= 2 * 10^4