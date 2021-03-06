package org.jwolfe.quetzal.algorithms;

import org.jwolfe.quetzal.library.general.*;
import org.jwolfe.quetzal.library.tree.BinaryTreeNode;
import org.jwolfe.quetzal.library.utilities.ActivityFinishComparator;
import org.jwolfe.quetzal.library.utilities.ActivityStartComparator;
import org.jwolfe.quetzal.library.utilities.PairFirstSorter;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DynamicProgramming {

    /**
     * Returns the first n fibonacci numbers
     *
     * @param n count of fibonacci numbers required
     * @return the first n fibonacci numbers
     */
    public static List<Integer> getFibonacciNumbers(int n) {
        if (n <= 0) {
            return null;
        }

        List<Integer> fibonacciSeries = new ArrayList<>();

        int a = 0;
        fibonacciSeries.add(a);
        if (n == 1) {
            return fibonacciSeries;
        }

        int b = 1;
        fibonacciSeries.add(b);
        if (n == 2) {
            return fibonacciSeries;
        }

        int c;

        for (int i = 3; i <= n; i++) {
            c = a + b;
            fibonacciSeries.add(c);

            a = b;
            b = c;
        }

        return fibonacciSeries;
    }

    /**
     * Returns the first n fibonacci numbers
     *
     * @param n count of fibonacci numbers required
     * @return the first n fibonacci numbers
     */
    public static List<Integer> getFibonacciNumbersA2(int n) {
        // Implementation using 2 variables (a, b)

        if (n <= 0) {
            return null;
        }

        List<Integer> fibonacciSeries = new ArrayList<>();

        int a = 0;
        fibonacciSeries.add(a);
        if (n == 1) {
            return fibonacciSeries;
        }

        int b = 1;
        fibonacciSeries.add(b);
        if (n == 2) {
            return fibonacciSeries;
        }

        for (int i = 3; i <= n; i++) {
            fibonacciSeries.add(a + b);

            b = a + b;
            a = b - a;
        }

        return fibonacciSeries;
    }

    /**
     * Returns the first n fibonacci numbers
     *
     * @param n count of fibonacci numbers required
     * @return the first n fibonacci numbers
     */
    public static List<Integer> getReverseFibonacciNumbers(int n) {
        if (n <= 0) {
            return null;
        }

        List<Integer> fibonacciSeries = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            fibonacciSeries.add(null);
        }

        int a = 0;
        fibonacciSeries.set(n - 1, a);
        if (n == 1) {
            return fibonacciSeries;
        }

        int b = 1;
        fibonacciSeries.set(n - 2, b);
        if (n == 2) {
            return fibonacciSeries;
        }

        int c;

        for (int i = 3; i <= n; i++) {
            c = a + b;
            fibonacciSeries.set(n - i, c);

            a = b;
            b = c;
        }

        return fibonacciSeries;
    }

    public static int longestIncreasingSubsequence(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;
        int[] lis = new int[n];
        Arrays.fill(lis, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                }
            }
        }

        System.out.println("Array:\t\t" + Arrays.toString(arr));
        System.out.println("LIS:\t\t" + Arrays.toString(lis));

        int max = 0;
        for (int i : lis) {
            if (max < i)
                max = i;
        }

        return max;
    }

    public static int longestIncreasingSubsequence(int[] arr, int startIndex, int endIndex) {
        if (arr == null || arr.length == 0 || startIndex > endIndex) {
            return 0;
        }

        int n = endIndex - startIndex + 1;
        int[] lis = new int[n];
        Arrays.fill(lis, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[startIndex + i] > arr[startIndex + j]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }

        return Arrays.stream(lis).max().getAsInt();
    }

    public static int longestIncreasingSubsequenceInCircularManner(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        // Append the array with itself
        int n = arr.length;
        int[] circularArray = new int[2 * n];

        for (int i = 0; i < n; i++) {
            circularArray[i] = arr[i];
            circularArray[n + i] = arr[i];
        }

        // For each sliding window of length n in the circular array, find lis.
        int maxLis = 0;
        for (int i = 0; i < n; i++) {
            int lis = longestIncreasingSubsequence(circularArray, i, i + n - 1);
            maxLis = Math.max(maxLis, lis);
        }

        return maxLis;
    }

    public static List<Integer> getLongestIncreasingSubsequence(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        List<List<Integer>> dp = new ArrayList<>();

        List<Integer> runningList = new ArrayList<>();
        runningList.add(arr[0]);
        dp.add(new ArrayList<>(runningList));

        for (int i = 1; i < arr.length; i++) {
            runningList.clear();
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]
                        && runningList.size() < dp.get(j).size()) {
                    runningList = dp.get(j);
                }
            }

            dp.add(new ArrayList<>(runningList));
            dp.get(i).add(arr[i]);
        }

        List<Integer> lisList = dp.get(0);
        for (int i = 1; i < dp.size(); i++) {
            if (lisList.size() < dp.get(i).size()) {
                lisList = dp.get(i);
            }
        }

        return lisList;
    }

    public static List<Integer> getLongestIncreasingSubsequence(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return null;
        }

        List<List<Integer>> dp = new ArrayList<>();

        List<Integer> runningList = new ArrayList<>();
        runningList.add(list.get(0));
        dp.add(new ArrayList<>(runningList));

        for (int i = 1; i < list.size(); i++) {
            runningList.clear();
            for (int j = 0; j < i; j++) {
                if (list.get(j) < list.get(i)
                        && runningList.size() < dp.get(j).size()) {
                    runningList = dp.get(j);
                }
            }

            dp.add(new ArrayList<>(runningList));
            dp.get(i).add(list.get(i));
        }

        List<Integer> lisList = dp.get(0);
        for (int i = 1; i < dp.size(); i++) {
            if (lisList.size() < dp.get(i).size()) {
                lisList = dp.get(i);
            }
        }

        return lisList;
    }

    public static List<Integer> getLongestIncreasingConsecutiveSubsequence(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        int n = arr.length;

        int[] dp = new int[n];
        Map<Integer, Integer> map = new HashMap<>();

        Arrays.fill(dp, 1);

        int licsLength = 1;
        int licsEndIndex = 0;

        for (int currentIndex = 0; currentIndex < n; currentIndex++) {
            int current = arr[currentIndex];
            int previous = current - 1;

            if (map.containsKey(previous)) {
                int previousIndex = map.get(previous);
                dp[currentIndex] = dp[previousIndex] + 1;
            }

            map.put(current, currentIndex);

            if (dp[currentIndex] > licsLength) {
                licsLength = dp[currentIndex];
                licsEndIndex = currentIndex;
            }
        }

        int licsEnd = arr[licsEndIndex];
        int licsStart = licsEnd - licsLength + 1;
        List<Integer> licsList = new ArrayList<>();
        for (int i = licsStart; i <= licsEnd; i++) {
            licsList.add(i);
        }

        return licsList;
    }

    public static int longestIncreasingSubsequenceRecursive(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        AtomicInteger max = new AtomicInteger(1);
        longestIncreasingSubsequenceRecursive(arr, arr.length - 1, max);

        return max.intValue();
    }

    private static int longestIncreasingSubsequenceRecursive(int[] arr, int index, AtomicInteger max) {
        if (index == 0) {
            return 1;
        }

        int lis = 1;
        for (int i = 0; i < index; i++) {
            int iLis = longestIncreasingSubsequenceRecursive(arr, i, max);
            if (arr[i] < arr[index] && lis < iLis + 1) {
                lis = iLis + 1;
            }
        }

        if (max.intValue() < lis) {
            max.set(lis);
        }

        return lis;
    }

    public static int countIncreasingSubsequences(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] += dp[j];
                }
            }
        }

        int count = Arrays.stream(dp).sum();
        return count;
    }

    public static int countIncreasingSubsequencesOfSizeK(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;

        // dp[i][j] => Number of subsequences of size 'i', for the array ending at index 'j'
        int[][] dp = new int[k + 1][n];

        // Count of IS of size 1 at each index is 1
        for (int i = 0; i < n; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= k; i++) {
            for (int j = 0; j < n; j++) {

                for (int l = 0; l < j; l++) {
                    if (arr[l] < arr[j]) {
                        dp[i][j] += dp[i - 1][l];
                    }
                }
            }
        }

        int count = 0;
        for (int j = 0; j < n; j++) {
            count += dp[k][j];
        }

        return count;
    }

    public static int countIncreasingSubsequencesWhenArrayElementsAreDigits(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;

        int[] countOfSubsequences = new int[10];
        for (int i = 0; i < n; i++) {
            int digit = arr[i];
            countOfSubsequences[digit] += 1;

            for (int j = digit - 1; j >= 0; j--) {
                countOfSubsequences[digit] += countOfSubsequences[j];
            }
        }

        return Arrays.stream(countOfSubsequences).sum();
    }

    public static int editDistance(String str1, String str2) {
        if (str1 == null || str2 == null)
            return 0;

        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Utilities.min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]);
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    public static int editDistanceRecursive(String str1, String str2) {
        if (str1 == null || str2 == null)
            return 0;

        return editDistanceRecursive(str1, str2, str1.length(), str2.length());
    }

    private static int editDistanceRecursive(String str1, String str2, int m, int n) {
        if (n == 0)
            return m;

        if (m == 0)
            return n;

        if (str1.charAt(m - 1) == str2.charAt(n - 1)) {
            return editDistanceRecursive(str1, str2, m - 1, n - 1);
        }

        return 1 + Utilities.min(editDistanceRecursive(str1, str2, m - 1, n),
                editDistanceRecursive(str1, str2, m, n - 1), editDistanceRecursive(str1, str2, m - 1, n - 1));
    }

    public static int longestPalindromicSubsequence(String str) {
        if (str == null)
            return 0;

        int n = str.length();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;

                if (str.charAt(i) == str.charAt(j) && j - 1 == i) {
                    dp[i][j] = 2;
                } else if (str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }

        return dp[0][n - 1];
    }

    public static int longestPalindromicSubsequenceRecursive(String str) {
        if (str == null)
            return 0;

        return longestPalindromicSubsequenceRecursive(str, 0, str.length() - 1);
    }

    private static int longestPalindromicSubsequenceRecursive(String str, int m, int n) {
        if (m == n) {
            return 1;
        }

        if (str.charAt(m) == str.charAt(n) && n - m == 1) {
            return 2;
        } else if (str.charAt(m) == str.charAt(n)) {
            return 2 + longestPalindromicSubsequenceRecursive(str, m + 1, n - 1);
        } else {
            return Math.max(longestPalindromicSubsequenceRecursive(str, m, n - 1),
                    longestPalindromicSubsequenceRecursive(str, m + 1, n));
        }
    }

    public static String getLongestPalindromicSubsequence(String str) {
        // Longest Palindromic Subsequence is the same as Longest Common Subsequence of a string & its reverse

        if (str == null || str.length() == 0) {
            return null;
        }

        int n = str.length();
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (str.charAt(i - 1) == str.charAt(n - j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        StringBuilder subsequence = new StringBuilder();
        int i = n;
        int j = n;
        while (i > 0 && j > 0) {
            if (str.charAt(i - 1) == str.charAt(n - j)) {
                subsequence.append(str.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return subsequence.reverse().toString();
    }

    public static int countPalindromicSubsequences(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int n = str.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;

                if (str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
                }
            }
        }

        return dp[0][n - 1];
    }

    public static int countPalindromicSubsequencesRecursive(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int n = str.length();
        return countPalindromicSubsequencesRecursive(str, 0, n - 1);
    }

    private static int countPalindromicSubsequencesRecursive(String str, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            return 0;
        }

        if (startIndex == endIndex) {
            return 1;
        }

        if (str.charAt(startIndex) == str.charAt(endIndex)) {
            return 1 + countPalindromicSubsequencesRecursive(str, startIndex + 1, endIndex) + countPalindromicSubsequencesRecursive(str, startIndex, endIndex - 1);
        } else {
            return countPalindromicSubsequencesRecursive(str, startIndex + 1, endIndex) + countPalindromicSubsequencesRecursive(str, startIndex, endIndex - 1)
                    - countPalindromicSubsequencesRecursive(str, startIndex + 1, endIndex - 1);
        }
    }

    public static int longestPalindromicSubstring(String str) {
        int n = str.length();
        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        int start = 0;
        int maxLength = 1;

        for (int i = 0; i < n - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }

        for (int k = 3; k <= n; k++) {
            for (int i = 0; i < n - k + 1; i++) {
                int j = i + k - 1;

                if (str.charAt(i) == str.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;

                    if (k > maxLength) {
                        start = i;
                        maxLength = k;
                    }
                }
            }
        }

        System.out.println("Longest palindromic substring of " + str + " found at index: " + start
                + ", with a length of " + maxLength);
        return maxLength;
    }

    public static int countPalindromicSubstrings(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int n = str.length();
        int[][] dp = new int[n][n];

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;

                if (i == j - 1) {
                    dp[i][j] = (str.charAt(i) == str.charAt(j)) ? 1 : 0;
                } else if (StringAlgorithms.isPalidrome(str, i, j)) {
                    dp[i][j] = 1 + dp[i + 1][j]
                            + dp[i][j - 1]
                            - dp[i + 1][j - 1];
                } else {
                    dp[i][j] = dp[i + 1][j]
                            + dp[i][j - 1]
                            - dp[i + 1][j - 1];
                }
            }
        }

        return dp[0][n - 1];
    }

    public static int countPalindromicSubstringsRecursive(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int n = str.length();
        return countPalindromicSubstringsRecursive(str, 0, n - 1);
    }

    private static int countPalindromicSubstringsRecursive(String str, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return 0;
        }

        if (startIndex == endIndex - 1) {
            if (str.charAt(startIndex) == str.charAt(endIndex)) {
                return 1;
            }

            return 0;
        }

        if (StringAlgorithms.isPalidrome(str, startIndex, endIndex)) {
            return 1 + countPalindromicSubstringsRecursive(str, startIndex + 1, endIndex)
                    + countPalindromicSubstringsRecursive(str, startIndex, endIndex - 1)
                    - countPalindromicSubstringsRecursive(str, startIndex + 1, endIndex - 1);
        } else {
            return countPalindromicSubstringsRecursive(str, startIndex + 1, endIndex)
                    + countPalindromicSubstringsRecursive(str, startIndex, endIndex - 1)
                    - countPalindromicSubstringsRecursive(str, startIndex + 1, endIndex - 1);
        }
    }

    public static List<Integer> countPalindromicSubstringsForIndexRanges(String str, List<Pair<Integer, Integer>> indexRanges) {
        if (str == null || str.length() == 0 || indexRanges == null || indexRanges.size() == 0) {
            return null;
        }

        int n = str.length();

        // isPalindrome[i][j] = true => str(i, j) is a palindrome
        boolean[][] isPalindrome = new boolean[n][n];

        // palindromeCount[i][j] denotes the count of palindromes in the substring str(i, j)
        int[][] palindromeCount = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            // Single characters are palindromes
            isPalindrome[i][i] = true;

            for (int j = i + 1; j < n; j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    isPalindrome[i][j] = (i + 1) > (j - 1) || isPalindrome[i + 1][j - 1];
                }

                // Count of palindromes of (i + 1, j) & (i, j - 1). Subtract count of (i + 1, j - 1) since they are counted twice
                palindromeCount[i][j] = 1 + palindromeCount[i][j - 1]
                        + palindromeCount[i + 1][j]
                        - palindromeCount[i + 1][j - 1]
                        + (isPalindrome[i][j] ? 1 : 0);
            }
        }

        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < indexRanges.size(); i++) {
            var indexes = indexRanges.get(i);
            int x = indexes.getFirst();
            int y = indexes.getSecond();

            if (x < 0 || y >= n) {
                results.add(-1);
            } else {
                results.add(palindromeCount[x][y]);
            }
        }

        return results;
    }

    public static int knapsack01(int[] weights, int[] values, int W) {
        int n = weights.length;

        int[][] dp = new int[n + 1][W + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i - 1] > w) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                }
            }
        }

        return dp[n][W];
    }

    public static Knapsack knapsack01(List<CarryItem> items, int maxWeight) {
        if (items == null || maxWeight < 0) {
            return null;
        }

        int n = items.size();

        // The value matrix that stores the max values for various combinations, built in a bottom-up fashion
        int[][] dp = new int[n + 1][maxWeight + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= maxWeight; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                var item = items.get(i - 1);
                if (item.getWeight() > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], item.getValue() + dp[i - 1][j - item.getWeight()]);
                }
            }
        }

        // dp[n][maxWeight] has the final max value for the Knapsack. Building the knapsack.
        Knapsack knapsack = new Knapsack();
        var sackItems = knapsack.getItems();
        int weight = maxWeight;
        for (int i = n; i > 0; i--) {
            if (dp[i][weight] == dp[i - 1][weight]) {
                // This item was not included.
                continue;
            } else {
                var item = items.get(i - 1);
                sackItems.add(item);
                weight -= item.getWeight();
            }
        }

        return knapsack;
    }

    public static int knapsack01Unbounded(int[] weights, int[] values, int W) {
        if (weights == null || values == null || weights.length != values.length || W < 0) {
            return -1;
        }

        int n = weights.length;
        int[] dp = new int[W + 1];
        dp[0] = 0;

        for (int i = 1; i <= W; i++) {
            dp[i] = 0;
            for (int j = 0; j < n; j++) {
                if (weights[j] <= i) {
                    dp[i] = Math.max(dp[i], values[j] + dp[i - weights[j]]);
                }
            }
        }

        return dp[W];
    }

    public static int knapsack01Recursive(int[] weights, int[] values, int W) {
        int n = weights.length;
        if (values.length != n)
            return 0;

        if (W <= 0)
            return 0;

        return knapsack01Recursive(weights, values, W, n);
    }

    private static int knapsack01Recursive(int[] weights, int[] values, int W, int n) {
        if (n <= 0) {
            return 0;
        }

        if (weights[n - 1] > W) {
            return knapsack01Recursive(weights, values, W, n - 1);
        }

        return Math.max(values[n - 1] + knapsack01Recursive(weights, values, W - weights[n - 1], n - 1),
                knapsack01Recursive(weights, values, W, n - 1));
    }

    public static int binomialCoefficient(int n, int k) {
        // C(n, k) = C(n-1, k-1) + C(n-1, k)
        // C(n, 0) = C(n, n) = 1

        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0 || i == j) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
            }
        }

        return dp[n][k];
    }

    public static int binomialCoefficientRecursive(int n, int k) {
        // C(n, k) = C(n-1, k-1) + C(n-1, k)
        // C(n, 0) = C(n, n) = 1

        if (k == 0 || n == k) {
            return 1;
        }

        return binomialCoefficientRecursive(n - 1, k - 1) + binomialCoefficientRecursive(n - 1, k);
    }

    public static int binomialCoefficientEfficient(int n, int k) {
        // C(n, k) = C(n, n-k)
        if (k > n - k) {
            k = n - k;
        }

        // C(n, k) = [n * (n-1) * (n-2) * .... ( n-k+1)[ / [k * (k - 1) * (k-2) * .... *
        // 1)
        int result = 1;
        for (int i = 0; i < k; i++) {
            result *= (n - i);
            result /= (i + 1);
        }

        return result;
    }

    public static int longestCommonSubsequence(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return 0;
        }

        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    public static String getLongestCommonSubsequence(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return null;
        }

        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int lcsSize = dp[m][n];
        StringBuilder sb = new StringBuilder(lcsSize);
        int i = m;
        int j = n;
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                sb.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] == dp[i][j]) {
                i--;
            } else {
                j--;
            }
        }

        return sb.reverse().toString();
    }

    public static Set<String> getAllLongestCommonSubsequences(String str1, String str2) {
        if (str1 == null || str1.length() == 0 || str2 == null || str2.length() == 0) {
            return null;
        }

        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return getAllLongestCommonSubsequences(str1, str2, m, n, dp);
    }

    private static Set<String> getAllLongestCommonSubsequences(String str1, String str2, int m, int n, int[][] dp) {
        Set<String> lcsList = new HashSet<>();
        if (m <= 0 || n <= 0) {
            // Reached the end of one of the strings; return an empty string
            lcsList.add("");
            return lcsList;
        }

        if (str1.charAt(m - 1) == str2.charAt(n - 1)) {
            // All lcs between str1(0...m-1) &str2(0...n-1) has the current character at its end.
            var subList = getAllLongestCommonSubsequences(str1, str2, m - 1, n - 1, dp);
            for (var lcs : subList) {
                lcsList.add(lcs + str1.charAt(m - 1));
            }
        } else {
            if (dp[m - 1][n] >= dp[m][n - 1]) {
                var subList = getAllLongestCommonSubsequences(str1, str2, m - 1, n, dp);
                lcsList.addAll(subList);
            }

            if (dp[m - 1][n] <= dp[m][n - 1]) {
                var subList = getAllLongestCommonSubsequences(str1, str2, m, n - 1, dp);
                lcsList.addAll(subList);
            }
        }

        return lcsList;
    }

    public static int longestCommonSubsequenceRecursive(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return 0;
        }

        int m = str1.length();
        int n = str2.length();

        return longestCommonSubsequenceRecursive(str1, str2, m, n);
    }

    private static int longestCommonSubsequenceRecursive(String str1, String str2, int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }

        if (str1.charAt(m - 1) == str2.charAt(n - 1)) {
            return 1 + longestCommonSubsequenceRecursive(str1, str2, m - 1, n - 1);
        } else {
            return Math.max(longestCommonSubsequenceRecursive(str1, str2, m, n - 1),
                    longestCommonSubsequenceRecursive(str1, str2, m - 1, n));
        }
    }

    public static int longestCommonSubsequenceSpaceOptimized(String str1, String str2) {
        // We need only the previous row for any iteration (if we do not need to get the lcs list.
        // So we can work with 2 rows, instead of m.

        if (str1 == null || str2 == null) {
            return 0;
        }

        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[2][n + 1];

        int binaryIndex = 0;

        for (int i = 0; i <= m; i++) {
            binaryIndex = i & 1;

            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[binaryIndex][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[binaryIndex][j] = dp[1 - binaryIndex][j - 1] + 1;
                } else {
                    dp[binaryIndex][j] = Math.max(dp[1 - binaryIndex][j], dp[binaryIndex][j - 1]);
                }
            }
        }

        return dp[binaryIndex][n];
    }

    public static int longestCommonSubsequenceWithUtmostKChangesAllowed(int[] arr1, int[] arr2, int maxChanges) {
        if (arr1 == null || arr1.length == 0 || arr2 == null || arr2.length == 0 || maxChanges <= 0) {
            return 0;
        }

        int m = arr1.length;
        int n = arr2.length;

        // dp[i][j][k] represents the lcs at arr1[0..i-1], arr[0...j-1] with utmost k changes made to arr1
        int[][][] dp = new int[m + 1][n + 1][maxChanges + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= maxChanges; k++) {
                    if (i == 0 || j == 0 || k == 0) {
                        dp[i][j][k] = 0;
                    } else {
                        int lcs = 0;

                        // Try with no changes
                        lcs = Math.max(dp[i - 1][j][k], dp[i][j - 1][k]);

                        // If the characters are the same
                        if (arr1[i - 1] == arr2[j - 1]) {
                            // ith & jth items are same. No changes to be made
                            lcs = Math.max(lcs, 1 + dp[i - 1][j - 1][k]);
                        }

                        // Try with one change
                        lcs = Math.max(lcs, 1 + dp[i - 1][j - 1][k - 1]);

                        dp[i][j][k] = lcs;
                    }
                }
            }
        }

        return dp[m][n][maxChanges];
    }

    public static int longestCommonSubsequence(String str1, String str2, String str3) {
        if (str1 == null || str2 == null || str3 == null) {
            return 0;
        }

        int m = str1.length();
        int n = str2.length();
        int o = str3.length();

        int[][][] dp = new int[m + 1][n + 1][o + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= o; k++) {
                    if (i == 0 || j == 0 || k == 0) {
                        dp[i][j][k] = 0;
                    } else if (str1.charAt(i - 1) == str2.charAt(j - 1)
                            && str1.charAt(i - 1) == str3.charAt(k - 1)) {
                        dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
                    } else {
                        dp[i][j][k] = Utilities.max(dp[i - 1][j][k], dp[i][j - 1][k], dp[i][j][k - 1]);
                    }
                }
            }
        }

        return dp[m][n][o];
    }

    public static int longestCommonIncreasingSubsequenceOptimized(int[] arr1, int[] arr2) {
        if (arr1 == null || arr1.length == 0 || arr2 == null || arr2.length == 0) {
            return 0;
        }

        int m = arr1.length;
        int n = arr2.length;

        // DP implementation -> O(n^2)
        int[] lcis = new int[n];

        for (int i = 0; i < m; i++) {
            int lcisLength = 0;

            for (int j = 0; j < n; j++) {
                int a = arr1[i];
                int b = arr2[j];

                if (a > b) {
                    lcisLength = Math.max(lcisLength, lcis[j]);
                } else if (a == b) {
                    lcis[j] = Math.max(lcis[j], lcisLength + 1);
                }
            }
        }

        return Arrays.stream(lcis).max().getAsInt();
    }

    public static int longestCommonIncreasingSubsequence(int[] arr1, int[] arr2) {
        if (arr1 == null || arr1.length == 0 || arr2 == null || arr2.length == 0) {
            return 0;
        }

        int m = arr1.length;
        int n = arr2.length;

        // DP implementation -> O(n^4)
        int[][] lcis = new int[m][n];
        int maxLcis = 0;

        for (int j = 0; j < n; j++) {
            if (arr1[0] == arr2[j]) {
                lcis[0][j] = 1;
                maxLcis = 1;
            } else {
                lcis[0][j] = 0;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (arr1[i] == arr2[j]) {
                    int candidateMax = 0;

                    // Get max from candidate matrix
                    for (int k = 0; k < i; k++) {
                        for (int l = 0; l < j; l++) {
                            if (lcis[k][l] != 0
                                    && arr1[i] > arr1[k]) {
                                candidateMax = Math.max(candidateMax, lcis[k][l]);
                            }
                        }
                    }

                    lcis[i][j] = 1 + candidateMax;
                    maxLcis = Math.max(maxLcis, lcis[i][j]);
                } else {
                    lcis[i][j] = 0;
                }
            }
        }

        return maxLcis;
    }

    public static int longestCommonIncreasingSubsequenceRecursive(int[] arr1, int[] arr2) {
        if (arr1 == null || arr1.length == 0 || arr2 == null || arr2.length == 0) {
            return 0;
        }

        int m = arr1.length;
        int n = arr2.length;

        return longestCommonIncreasingSubsequenceRecursive(arr1, arr2, m - 1, n - 1, Integer.MAX_VALUE);
    }

    private static int longestCommonIncreasingSubsequenceRecursive(int[] arr1, int[] arr2, int i, int j, int previous) {
        if (i < 0 || j < 0) {
            return 0;
        }

        if (arr1[i] == arr2[j]
                && arr1[i] < previous) {
            return Utilities.max(
                    longestCommonIncreasingSubsequenceRecursive(arr1, arr2, i - 1, j - 1, arr1[i]) + 1,
                    longestCommonIncreasingSubsequenceRecursive(arr1, arr2, i - 1, j, previous),
                    longestCommonIncreasingSubsequenceRecursive(arr1, arr2, i, j - 1, previous));
        } else {
            return Math.max(
                    longestCommonIncreasingSubsequenceRecursive(arr1, arr2, i - 1, j, previous),
                    longestCommonIncreasingSubsequenceRecursive(arr1, arr2, i, j - 1, previous));
        }
    }

    public static int longestCommonSubstring(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return 0;
        }

        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        int lcs = 0;

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    lcs = Math.max(lcs, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return lcs;
    }

    public static int longestCommonSubstringRecursive(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return 0;
        }

        int m = str1.length();
        int n = str2.length();

        return longestCommonSubstringRecursive(str1, str2, m, n, 0);
    }

    private static int longestCommonSubstringRecursive(String str1, String str2, int m, int n, int runningLength) {
        if (m == 0 || n == 0) {
            return runningLength;
        }

        if (str1.charAt(m - 1) == str2.charAt(n - 1)) {
            runningLength = longestCommonSubstringRecursive(str1, str2, m - 1, n - 1, runningLength + 1);
        }

        int leftDown = longestCommonSubstringRecursive(str1, str2, m - 1, n, 0);
        int rightDown = longestCommonSubstringRecursive(str1, str2, m, n - 1, 0);

        return Utilities.max(runningLength, leftDown, rightDown);
    }

    public static int longestCommonSubstringSpaceOptimized(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return 0;
        }

        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[2][n + 1];
        int binaryIndex = 0;
        int lcs = 0;

        for (int i = 0; i <= m; i++) {
            binaryIndex = 1 & i;

            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[binaryIndex][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[binaryIndex][j] = dp[1 - binaryIndex][j - 1] + 1;
                    lcs = Math.max(lcs, dp[binaryIndex][j]);
                } else {
                    dp[binaryIndex][j] = 0;
                }
            }
        }

        return lcs;
    }

    public static int maxWaysForCoinChange(int[] coins, int value) {
        // Note: we have an infinite supply for each coin.

        if (coins == null || coins.length == 0 || value < 0) {
            return 0;
        }

        int n = coins.length;
        int[][] dp = new int[n][value + 1];
        for (int i = 0; i < n; i++) {
            // Zero value -> 1 solution (=> no coins)
            dp[i][0] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= value; j++) {
                int coinValue = coins[i];

                int inclusiveWays = coinValue <= j ? dp[i][j - coinValue] : 0;
                int exclusiveWays = i > 0 ? dp[i - 1][j] : 0;

                dp[i][j] = inclusiveWays + exclusiveWays;
            }
        }

        return dp[n - 1][value];
    }

    public static int maxWaysForCoinChangeRecursive(int[] coins, int value) {
        // Note: we have an infinite supply for each coin.

        if (coins == null || coins.length == 0 || value < 0) {
            return 0;
        }

        return maxWaysForCoinChangeRecursive(coins, value, coins.length - 1);
    }

    private static int maxWaysForCoinChangeRecursive(int[] coins, int value, int index) {
        if (index < 0 || value < 0) {
            return 0;
        }

        if (value == 0) {
            // One way -> No coins
            return 1;
        }

        int inclusiveWays = maxWaysForCoinChangeRecursive(coins, value - coins[index], index);
        int exclusiveWays = maxWaysForCoinChangeRecursive(coins, value, index - 1);

        return inclusiveWays + exclusiveWays;
    }

    public static int minCoins(int[] coins, int V) {
        int n = coins.length;

        int[] table = new int[V + 1];
        table[0] = 0;

        for (int i = 1; i <= V; i++) {
            table[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= V; i++) {
            for (int j = 0; j < n; j++) {
                if (coins[j] <= i) {
                    int subResult = table[i - coins[j]];
                    if (subResult != Integer.MAX_VALUE && subResult + 1 < table[i]) {
                        table[i] = subResult + 1;
                    }
                }
            }
        }

        return table[V];
    }

    public static int minCoinsRecursive(int[] coins, int V) {
        if (V == 0) {
            return 0;
        }

        int minCoins = Integer.MAX_VALUE;

        for (int i = 0; i < coins.length; i++) {
            if (coins[i] <= V) {
                int subResult = minCoins(coins, V - coins[i]);
                if (subResult != Integer.MAX_VALUE && subResult + 1 < minCoins) {
                    minCoins = 1 + subResult;
                }
            }
        }

        return minCoins;
    }

    public static int minCoinsGreedy(int[] coins, int V) {
        // Note: Greedy approach may not always give the correct solution.

        Arrays.sort(coins);
        int n = coins.length;

        int numCoins = 0;
        int i = n - 1;
        while (i >= 0) {
            if (coins[i] <= V) {
                numCoins++;
                V -= coins[i];
            } else {
                i--;
            }
        }

        if (V != 0) {
            return 0;
        }

        return numCoins;
    }

    public static int maxChainLength(List<IntPair> pairs) {
        var sorter = new PairFirstSorter();
        Collections.sort(pairs, sorter);

        int n = pairs.size();
        int[] maxChainLengths = new int[n];
        Arrays.fill(maxChainLengths, 1);

        for (int i = 1; i < n; i++) {
            var pair1 = pairs.get(i);
            for (int j = 0; j < i; j++) {
                var pair2 = pairs.get(j);

                if (pair1.getFirst() > pair2.getSecond() && maxChainLengths[i] < maxChainLengths[j] + 1) {
                    maxChainLengths[i] = maxChainLengths[j] + 1;
                }
            }
        }

        int maxLength = 0;
        for (int i = 0; i < maxChainLengths.length; i++) {
            if (maxLength < maxChainLengths[i]) {
                maxLength = maxChainLengths[i];
            }
        }

        return maxLength;
    }

    public static List<IntPair> getMaxChain(List<IntPair> pairs) {
        if (pairs == null || pairs.size() == 0) {
            return null;
        }

        pairs.sort(new PairFirstSorter());

        int n = pairs.size();
        List<List<IntPair>> allMaxChains = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            allMaxChains.add(new ArrayList<>());
        }

        allMaxChains.get(0).add(pairs.get(0));

        for (int i = 1; i < n; i++) {
            var iPair = pairs.get(i);
            var maxChain = allMaxChains.get(i);

            for (int j = 0; j < i; j++) {
                var jPair = pairs.get(j);

                if (iPair.getFirst() > jPair.getSecond()) {
                    if (maxChain.size() < allMaxChains.get(j).size()) {
                        maxChain.clear();
                        maxChain.addAll(allMaxChains.get(j));
                    }
                }
            }

            maxChain.add(pairs.get(i));
        }

        var maxChain = allMaxChains.get(0);
        for (int i = 1; i < n; i++) {
            if (maxChain.size() < allMaxChains.get(i).size()) {
                maxChain = allMaxChains.get(i);
            }
        }

        return maxChain;
    }

    public static int countBinaryStringsWithoutConsecutiveOnesForLengthN(int n) {
        // a[i] -> # binary strings of length i, that end in 0
        // b[i] -> # binary strings of length i, that end in 1
        //
        // a[i] = a[i-1] + [i-1]
        // b[i] = a[i-1]
        // a[1] = b[1] = 1
        // Total # of strings at i is a[i] + b[i]
        //

        int[] a = new int[n];
        int[] b = new int[n];

        a[0] = 1;
        b[0] = 1;

        for (int i = 1; i < n; i++) {
            a[i] = a[i - 1] + b[i - 1];
            b[i] = a[i - 1];
        }

        return a[n - 1] + b[n - 1];
    }

    public static int longestBitonicSubsequence(int[] arr) {
        if (arr == null) {
            return 0;
        }

        int n = arr.length;

        // Longest increasing subsequence
        int[] lis = new int[n];
        Arrays.fill(lis, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && lis[j] + 1 > lis[i]) {
                    lis[i] = lis[j] + 1;
                }
            }
        }

        // Longest decreasing subsequence
        int[] lds = new int[n];
        Arrays.fill(lds, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j] && lds[j] + 1 > lds[i]) {
                    lds[i] = lds[j] + 1;
                }
            }
        }

        // Longest bitonic subsequence
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            int length = lis[i] + lds[i] - 1;
            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }

    public static List<Integer> getLongestBitonicSubsequence(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        int n = arr.length;

        // Longest Increasing Subsequence
        List<List<Integer>> lis = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            lis.add(new ArrayList<>());
        }

        lis.get(0).add(arr[0]);
        for (int i = 1; i < n; i++) {
            var ilist = lis.get(i);

            for (int j = 0; j < i; j++) {
                var jList = lis.get(j);
                if (arr[i] > arr[j]
                        && ilist.size() < jList.size()) {
                    ilist.clear();
                    ilist.addAll(jList);
                }
            }

            ilist.add(arr[i]);
        }

        // Longest Decreasing Subsequence
        List<List<Integer>> lds = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            lds.add(new ArrayList<>());
        }

        lds.get(n - 1).add(arr[n - 1]);
        for (int i = n - 2; i >= 0; i--) {
            var iList = lds.get(i);
            for (int j = n - 1; j > i; j--) {
                var jList = lds.get(j);

                if (arr[i] > arr[j]
                        && iList.size() < jList.size()) {
                    iList.clear();
                    iList.addAll(jList);
                }
            }

            iList.add(0, arr[i]);
        }

        List<Integer> maxLisList = lis.get(0);
        List<Integer> maxLdsList = lds.get(0);
        int maxSize = maxLdsList.size() + maxLdsList.size() - 1;
        for (int i = 1; i < n; i++) {
            var lisList = lis.get(i);
            var ldsList = lds.get(i);

            int size = lisList.size() + ldsList.size() - 1;
            if (size > maxSize) {
                maxSize = size;
                maxLisList = lisList;
                maxLdsList = ldsList;
            }
        }

        List<Integer> bitonicList = new ArrayList<>();
        for (int i = 0; i < maxLisList.size(); i++) {
            bitonicList.add(maxLisList.get(i));
        }

        for (int i = 1; i < maxLdsList.size(); i++) {
            bitonicList.add(maxLdsList.get(i));
        }

        return bitonicList;
    }

    public static int paintFence(int numPosts, int numColors) {
        int[] dp = new int[numPosts];
        dp[0] = numColors;

        int sameColorWays = 0;
        int diffColorWays = numColors;

        for (int i = 1; i < numPosts; i++) {
            sameColorWays = diffColorWays;
            diffColorWays = dp[i - 1] * (numColors - 1);

            dp[i] = sameColorWays + diffColorWays;
        }

        return dp[numPosts - 1];
    }

    public static int maxStackHeight(List<Triplet<Integer, Integer, Integer>> boxes) {
        if (boxes == null) {
            return 0;
        }

        List<Triplet<Integer, Integer, Integer>> allRotations = new ArrayList<>();
        for (Triplet<Integer, Integer, Integer> box : boxes) {
            allRotations.add(new Triplet(box.getFirst(), box.getSecond(), box.getThird()));
            allRotations.add(new Triplet(box.getFirst(), box.getThird(), box.getSecond()));
            allRotations.add(new Triplet(box.getSecond(), box.getThird(), box.getFirst()));
            allRotations.add(new Triplet(box.getSecond(), box.getFirst(), box.getThird()));
            allRotations.add(new Triplet(box.getThird(), box.getFirst(), box.getSecond()));
            allRotations.add(new Triplet(box.getThird(), box.getSecond(), box.getFirst()));
        }

        allRotations.sort((o1, o2) -> ((o2.getFirst() * o2.getSecond()) - (o1.getFirst() * o1.getSecond())));
        System.out.println(Arrays.toString(allRotations.toArray()));

        int[] msh = new int[allRotations.size()];
        for (int i = 0; i < msh.length; i++) {
            msh[i] = allRotations.get(i).getThird();
        }

        for (int i = 1; i < msh.length; i++) {
            var iBox = allRotations.get(i);
            for (int j = 0; j < i; j++) {
                var jBox = allRotations.get(j);
                if (jBox.getFirst() > iBox.getFirst() && jBox.getSecond() > iBox.getSecond()) {
                    if (msh[j] + iBox.getThird() > msh[i]) {
                        msh[i] = msh[j] + iBox.getThird();
                    }
                }
            }
        }

        int maxHeight = 0;
        for (int i = 0; i < msh.length; i++) {
            maxHeight = Math.max(maxHeight, msh[i]);
        }

        return maxHeight;
    }

    public static int countFriendPairings(int n) {
        // Each person can remain single, or be paired up with another
        // For nth person, # pairings = f(n-1) + (n-1) * f(n-2)
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;

        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + i * dp[i - 2];
        }

        return dp[n - 1];
    }

    public static int eggDrop(int eggs, int floors) {
        if (eggs == 0 || floors == 0) {
            return 0;
        }

        int[][] dp = new int[eggs + 1][floors + 1];
        for (int i = 0; i < eggs; i++) {
            dp[i][1] = 1;
            dp[i][0] = 0;
        }

        for (int j = 0; j <= floors; j++) {
            dp[1][j] = j;
        }

        for (int i = 2; i <= eggs; i++) {
            for (int j = 2; j <= floors; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 1; k <= j; k++) {
                    int netTrials = 1 + Math.max(dp[i - 1][k - 1], dp[i][j - k]);
                    dp[i][j] = Math.min(dp[i][j], netTrials);
                }
            }
        }

        return dp[eggs][floors];
    }

    public static int eggDropRecursive(int eggs, int floors) {
        // Find minimum number of trials to find the lowest floor from which an egg
        // dropped down will break

        if (eggs == 0 || floors == 0) {
            return 0;
        }

        // If there is only one floor, only one trial is required
        if (floors == 1) {
            return 1;
        }

        // If there is only one egg, minimum number of trials is equal to the number of
        // floors.
        if (eggs == 1) {
            return floors;
        }

        int minTrials = Integer.MAX_VALUE;
        for (int i = 1; i <= floors; i++) {
            // Two possibilities -> egg broke & egg did not break
            // Egg broke : try in lower floors
            // Egg didnt break : try in upper floors
            // Find the max of either of these options

            int addlTrialsIfBroke = eggDropRecursive(eggs - 1, i - 1);
            int addlTrialsIfNotBroke = eggDropRecursive(eggs, floors - i);

            int netTrials = 1 + Math.max(addlTrialsIfBroke, addlTrialsIfNotBroke);
            minTrials = Math.min(minTrials, netTrials);
        }

        return minTrials;
    }

    public static int longestRepeatingSubsequence(String str) {
        // Variation of longest common subsequence, with (str, str). Match when m != n

        if (str == null || str.length() == 0) {
            return 0;
        }

        int n = str.length();
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j && str.charAt(i - 1) == str.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][n];
    }

    public static int longestRepeatingSubsequenceMemoized(String str) {
        // Variation of longest common subsequence, with (str, str). Match when m != n

        if (str == null || str.length() == 0) {
            return 0;
        }

        Map<IntPair, Integer> memo = new HashMap<>();
        return longestRepeatingSubsequenceMemoized(str, str.length(), str.length(), memo);
    }

    private static int longestRepeatingSubsequenceMemoized(String str, int m, int n, Map<IntPair, Integer> memo) {
        if (m == 0 || n == 0) {
            return 0;
        }

        IntPair pair = new IntPair(m, n);
        if (memo.containsKey(pair)) {
            return memo.get(pair);
        }

        int lrs = 0;
        if (m != n && str.charAt(m - 1) == str.charAt(n - 1)) {
            lrs = 1 + longestRepeatingSubsequenceMemoized(str, m - 1, n - 1, memo);
        } else {
            lrs = Math.max(longestRepeatingSubsequenceMemoized(str, m - 1, n, memo),
                    longestRepeatingSubsequenceMemoized(str, m, n - 1, memo));
        }

        memo.put(pair, lrs);
        return lrs;
    }

    public static int longestRepeatingSubsequenceRecursive(String str) {
        // Variation of longest common subsequence, with (str, str). Match when m != n

        if (str == null || str.length() == 0) {
            return 0;
        }

        return longestRepeatingSubsequenceRecursive(str, str.length(), str.length());
    }

    private static int longestRepeatingSubsequenceRecursive(String str, int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }

        if (m != n && str.charAt(m - 1) == str.charAt(n - 1)) {
            return 1 + longestRepeatingSubsequenceRecursive(str, m - 1, n - 1);
        }

        return Math.max(longestRepeatingSubsequenceRecursive(str, m - 1, n),
                longestRepeatingSubsequenceRecursive(str, m, n - 1));
    }

    public static int getNthUglyNumber(int n) {
        // Ugly numbers have their only prime factors as 2, 3, or 5. By convention 1 is
        // the fist ugly number
        // Approach 1: Iterate through all numbers checking if it is ugly. Return the
        // number when the ugly counter hits n
        // Approach 2: Merge-style implementation from sequences of multiples of 2, 3, &
        // 5.

        if (n == 1) {
            return 1;
        }

        int[] uglyNumbers = new int[n];

        int twoIndex = 0;
        int threeIndex = 0;
        int fiveIndex = 0;

        int twoMultiple = 2;
        int threeMultiple = 3;
        int fiveMultiple = 5;

        uglyNumbers[0] = 1;
        int nthUglyNumber = 1;
        for (int i = 1; i < n; i++) {
            nthUglyNumber = Utilities.min(twoMultiple, threeMultiple, fiveMultiple);
            uglyNumbers[i] = nthUglyNumber;

            if (nthUglyNumber == twoMultiple) {
                twoIndex++;
                twoMultiple = uglyNumbers[twoIndex] * 2;
            }

            if (nthUglyNumber == threeMultiple) {
                threeIndex++;
                threeMultiple = uglyNumbers[threeIndex] * 3;
            }

            if (nthUglyNumber == fiveMultiple) {
                fiveIndex++;
                fiveMultiple = uglyNumbers[fiveIndex] * 5;
            }
        }

        return nthUglyNumber;
    }

    public static int[] getUglyNumbers(int n) {
        if (n <= 0) {
            return null;
        }

        int[] uglyNumbers = new int[n];
        uglyNumbers[0] = 1;

        int twoIndex = 0;
        int threeIndex = 0;
        int fiveIndex = 0;

        int twoMultiple = 2;
        int threeMultiple = 3;
        int fiveMultiple = 5;

        int nextUglyNumber = 1;
        for (int i = 1; i < n; i++) {
            nextUglyNumber = Utilities.min(twoMultiple, threeMultiple, fiveMultiple);
            uglyNumbers[i] = nextUglyNumber;

            if (twoMultiple == nextUglyNumber) {
                twoIndex++;
                twoMultiple = uglyNumbers[twoIndex] * 2;
            }

            if (threeMultiple == nextUglyNumber) {
                threeIndex++;
                threeMultiple = uglyNumbers[threeIndex] * 3;
            }

            if (fiveMultiple == nextUglyNumber) {
                fiveIndex++;
                fiveMultiple = uglyNumbers[fiveIndex] * 5;
            }
        }

        return uglyNumbers;
    }

    public static boolean subSetSum(int[] set, int sum) {
        // Pseudo-polynomial, DP implementation

        if (set == null || set.length == 0) {
            return false;
        }

        int n = set.length;

        // dp[i][j] is true, if subset S(0...i) contains the sum j
        boolean[][] dp = new boolean[n + 1][sum + 1];

        // Sum = 0 => empty subset matches, and thus all subsets
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // Empty subset does not match sums > 0;
        for (int j = 1; j <= sum; j++) {
            dp[0][j] = false;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (set[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - set[i - 1]];
                }
            }
        }

        return dp[n][sum];
    }

    public static boolean subSetSumRecursive(int[] set, int sum) {
        if (set == null || set.length == 0) {
            return false;
        }

        return subSetSumRecursive(set, sum, set.length - 1);
    }

    private static boolean subSetSumRecursive(int[] set, int sum, int index) {
        if (sum == 0) {
            return true;
        }

        if (index < 0) {
            return false;
        }

        // If current element is greater than sum, ignore
        if (set[index] > sum) {
            return subSetSumRecursive(set, sum, index - 1);
        }

        // Current index not included
        boolean inclusive = subSetSumRecursive(set, sum, index - 1);

        // Current index included
        boolean exclusive = subSetSumRecursive(set, sum - set[index], index - 1);

        return inclusive || exclusive;
    }

    public static boolean canSetBePartitionedIntoTwoForEqualSum(int[] set) {
        if (set == null || set.length <= 1) {
            return false;
        }

        int sum = Arrays.stream(set).sum();
        if (sum % 2 == 1) {
            // Odd sum. Cannot be partitioned
            return false;
        }

        return subSetSum(set, sum / 2);
    }

    public static Pair<List<Integer>, List<Integer>> getEqualSumPartitionedSubsets(int[] set) {
        if (set == null || set.length <= 1) {
            return null;
        }

        int sum = Arrays.stream(set).sum();
        if (sum % 2 == 1) {
            return null;
        }

        int n = set.length;
        int halfSum = sum / 2;

        // dp[i][j] => true, if S(0...i) contains the sum j
        boolean[][] dp = new boolean[n + 1][halfSum + 1];

        // Non-zero is not possible for empty subset
        for (int j = 1; j <= halfSum; j++) {
            dp[0][j] = false;
        }

        // Zero matches for all sets
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= halfSum; j++) {
                int element = set[i - 1];
                if (element > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // Exclude or include
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - element];
                }
            }
        }

        if (!dp[n][halfSum]) {
            return null;
        }

        List<Integer> leftPartition = new ArrayList<>();
        List<Integer> rightPartition = new ArrayList<>();

        int i = n;
        int j = halfSum;
        while (i > 0 && j >= 0) {
            int element = set[i - 1];
            if (dp[i - 1][j]) {
                // Current element was excluded
                leftPartition.add(element);
                i--;
            } else {
                rightPartition.add(element);
                i--;
                j -= element;
            }
        }

        var result = new Pair(leftPartition, rightPartition);
        return result;
    }

    public static Pair<List<Integer>, List<Integer>> getEqualSumPartitionedSubsetsRecursive(int[] set) {
        if (set == null || set.length <= 1) {
            return null;
        }

        int sum = Arrays.stream(set).sum();
        if (sum % 2 == 1) {
            return null;
        }

        int n = set.length;

        List<Integer> leftPartition = new ArrayList<>();
        List<Integer> rightPartition = new ArrayList<>();
        generateEqualSumPartitionedSubsetsRecursive(set, n, 0, leftPartition, rightPartition, 0, 0);

        if (leftPartition.size() == 0) {
            return null;
        }

        var result = new Pair(leftPartition, rightPartition);
        return result;
    }

    private static boolean generateEqualSumPartitionedSubsetsRecursive(int[] set, int length, int currentIndex,
                                                                       List<Integer> leftPartition, List<Integer> rightPartition,
                                                                       int leftSum, int rightSum) {
        if (currentIndex == length) {
            // Reached the end of the array. Verify sums
            if (leftSum == rightSum) {
                // Partitions are equal in sum
                return true;
            }

            return false;
        }

        int element = set[currentIndex];
        boolean partitionSuccessful;

        // Try include element in left partition
        leftPartition.add(element);
        partitionSuccessful = generateEqualSumPartitionedSubsetsRecursive(set, length, currentIndex + 1,
                leftPartition, rightPartition,
                leftSum + element, rightSum);

        if (partitionSuccessful) {
            return true;
        }

        leftPartition.remove(leftPartition.size() - 1);

        // Try include element in right partition
        rightPartition.add(element);
        partitionSuccessful = generateEqualSumPartitionedSubsetsRecursive(set, length, currentIndex + 1,
                leftPartition, rightPartition,
                leftSum, rightSum + element);

        if (partitionSuccessful) {
            return true;
        }

        rightPartition.remove(rightPartition.size() - 1);
        return false;
    }

    public static int maxLengthSnakeSequence(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int columns = grid[0].length;

        for (int i = 1; i < rows; i++) {
            if (grid[i].length != columns) {
                return 0;
            }
        }

        int[][] dp = new int[rows][columns];
        int maxLength = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int downLength = 0;
                if (i > 0 && (Math.abs(grid[i][j] - grid[i - 1][j]) == 1)) {
                    downLength = dp[i - 1][j];
                }

                int leftLength = 0;
                if (j > 0 && Math.abs(grid[i][j] - grid[i][j - 1]) == 1) {
                    leftLength = dp[i][j - 1];
                }

                int length = Math.max(downLength, leftLength) + 1;
                dp[i][j] = length;
                maxLength = Math.max(maxLength, length);
            }
        }

        return maxLength;
    }

    public static int maxRevenueFromRodCutting(int[] prices, int rodLength) {
        // Note: prices[] contains the price for all lengths of the rod upto the given
        // rod length.
        // Since arrays are zero-indexed, index of 0 stands for length of 1 unit

        if (prices == null || prices.length == 0 || prices.length != rodLength) {
            return 0;
        }

        int[] revenues = new int[rodLength + 1];
        for (int i = 1; i <= rodLength; i++) {
            for (int j = 0; j < i; j++) {
                revenues[i] = Math.max(revenues[i], prices[j] + revenues[i - j - 1]);
            }
        }

        return revenues[rodLength];
    }

    public static int maxRevenueFromRodCuttingRecursive(int[] prices, int rodLength) {
        // Note: prices[] contains the price for all lengths of the rod upto the given
        // rod length.
        // Since arrays are zero-indexed, index of 0 stands for length of 1 unit

        if (prices == null || prices.length == 0 || prices.length != rodLength) {
            return 0;
        }

        return maxRevenueFromRodCuttingRecursive(prices, rodLength, rodLength);
    }

    private static int maxRevenueFromRodCuttingRecursive(int[] prices, int rodLength, int currentLength) {
        if (currentLength <= 0) {
            return 0;
        }

        int maxRevenue = Integer.MIN_VALUE;
        for (int i = 0; i < currentLength; i++) {
            int revenue = prices[i] + maxRevenueFromRodCuttingRecursive(prices, rodLength, currentLength - i - 1);
            maxRevenue = Math.max(maxRevenue, revenue);
        }

        return maxRevenue;
    }

    public static boolean wordBreak(String str, List<String> dictionary) {
        if (str == null || str.length() == 0 || dictionary == null || dictionary.size() == 0) {
            return false;
        }

        int n = str.length();
        boolean[] dp = new boolean[n + 1];
        List<Integer> matchedIndexes = new ArrayList<>();

        dp[0] = true;
        matchedIndexes.add(-1);

        for (int i = 1; i <= n; i++) {

            for (int j = matchedIndexes.size() - 1; j >= 0; j--) {
                String prefix = str.substring(matchedIndexes.get(j) + 1, i);
                if (dictionary.contains(prefix)) {
                    dp[i] = true;
                    matchedIndexes.add(i - 1);
                    break;
                }
            }
        }

        return dp[n];
    }

    public static boolean wordBreakDP(String str, List<String> dictionary) {
        if (str == null || str.length() == 0 || dictionary == null || dictionary.size() == 0) {
            return false;
        }

        int n = str.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            String prefix = str.substring(0, i);
            if (dp[i] == false && dictionary.contains(prefix)) {
                dp[i] = true;
            }

            if (dp[i] == true) {
                if (i == n) {
                    return true;
                }

                for (int j = i + 1; j <= n; j++) {
                    String suffix = str.substring(i, j);
                    if (dp[j] == false && dictionary.contains(suffix)) {
                        dp[j] = true;
                    }

                    if (j == n && dp[j] == true) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static boolean wordBreakRecursive(String str, List<String> dictionary) {
        // Note: Using a trie for the dictionary will yeild better performance

        if (str == null || str.length() == 0 || dictionary == null || dictionary.size() == 0) {
            return false;
        }

        return wordBreakRecursiveHelper(str, dictionary);
    }

    public static boolean wordBreakRecursiveHelper(String str, List<String> dictionary) {
        int size = str.length();
        if (size == 0) {
            return true;
        }

        for (int len = 1; len <= size; len++) {
            String prefix = str.substring(0, len);
            if (dictionary.contains(prefix)) {
                String suffix = str.substring(len);
                if (wordBreakRecursiveHelper(suffix, dictionary)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static List<String> getWordBreaks(String str, List<String> dictionary) {
        if (str == null || str.length() == 0 || dictionary == null || dictionary.size() == 0) {
            return null;
        }

        List<String> wordBreaks = new ArrayList<>();
        generatewordBreaks(str, dictionary, "", wordBreaks);
        return wordBreaks;
    }

    public static void generatewordBreaks(String str, List<String> dictionary, String runningString,
                                          List<String> wordBreaks) {
        int size = str.length();
        if (size == 0) {
            wordBreaks.add(runningString.substring(0, runningString.length() - 1));
            return;
        }

        for (int len = 1; len <= size; len++) {
            String prefix = str.substring(0, len);
            if (dictionary.contains(prefix)) {
                String suffix = str.substring(len);
                generatewordBreaks(suffix, dictionary, runningString + prefix + " ", wordBreaks);
            }
        }

        return;
    }


    /**
     * Returns the length of the largest subset where, for every pair in the subset, the larger element is divisible by the smaller one.
     *
     * @param arr integer array whose largest divisible pairs subset is required
     * @return length of the largest divisible pairs subset
     */
    public static int lengthOfLargestDivisiblePairsSubset(int[] arr) {
        //

        if (arr == null || arr.length == 0) {
            return 0;
        }

        Arrays.sort(arr);
        int n = arr.length;
        int[] dp = new int[n];
        dp[n - 1] = 1;

        for (int i = n - 2; i >= 0; i--) {
            int max = 0;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] % arr[i] == 0) {
                    max = Math.max(max, dp[j]);
                }
            }

            dp[i] = max + 1;
        }

        return Arrays.stream(dp).max().getAsInt();
    }

    /**
     * Returns the largest subset where, for every pair in the subset, the larger element is divisible by the smaller one.
     *
     * @param arr integer array whose largest divisible pairs subset is required
     * @return the largest divisible pairs subset
     */
    public static Set<Integer> getLargestDivisiblePairsSubset(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        Set<Integer> largestdivisiblePairsSubset = new HashSet<>();
        Set<Integer> runningSubset = new HashSet<>();

        generateLargestDivisiblePairsSubset(arr, arr.length, 0, runningSubset, largestdivisiblePairsSubset);
        return largestdivisiblePairsSubset;
    }

    private static void generateLargestDivisiblePairsSubset(int[] arr, int length, int index, Set<Integer> runningSubset, Set<Integer> largestdivisiblePairsSubset) {
        if (index >= length) {
            if (runningSubset.size() > largestdivisiblePairsSubset.size()) {
                largestdivisiblePairsSubset.clear();
                largestdivisiblePairsSubset.addAll(runningSubset);
            }

            return;
        }

        int item = arr[index];

        // Try including item
        boolean canInclude = true;
        for (var element : runningSubset) {
            if ((element >= item && element % item != 0)
                    || (element < item && item % element != 0)) {
                canInclude = false;
                break;
            }
        }

        if (canInclude) {
            runningSubset.add(item);
            generateLargestDivisiblePairsSubset(arr, length, index + 1, runningSubset, largestdivisiblePairsSubset);

            // Backtrack
            runningSubset.remove(item);
        }

        // Try excluding item
        generateLargestDivisiblePairsSubset(arr, length, index + 1, runningSubset, largestdivisiblePairsSubset);
    }

    public static int minimumPalindromePartitioning(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int n = str.length();

        // palindomes[i][j] == 0 if substr(str, i, j) is a palindrome
        boolean[][] palindromes = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            // Strings of length 1 are palindromes
            palindromes[i][i] = true;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                if (len == 2) {
                    palindromes[i][j] = str.charAt(i) == str.charAt(j);
                } else {
                    palindromes[i][j] = (str.charAt(i) == str.charAt(j))
                            && palindromes[i + 1][j - 1];
                }
            }
        }

        // cuts[i] denotes the minimum number of cuts for palindromic partioning of substr(str, 0, j)
        int[] cuts = new int[n];
        for (int i = 0; i < n; i++) {
            if (palindromes[0][i]) {
                cuts[i] = 0;
            } else {
                cuts[i] = Integer.MAX_VALUE;
                for (int k = 0; k < i; k++) {
                    if (palindromes[k + 1][i] == true
                            && 1 + cuts[k] < cuts[i]) {
                        cuts[i] = 1 + cuts[k];
                    }
                }
            }
        }

        return cuts[n - 1];
    }

    public static int minimumPalindromePartitioningA1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int n = str.length();

        // palindomes[i][j] == 0 if substr(str, i, j) is a palindrome
        boolean[][] palindromes = new boolean[n][n];

        // cuts[i][j] denotes the minimum number of cuts for palindromic partioning of substr(str, i, j)
        int[][] cuts = new int[n][n];

        for (int i = 0; i < n; i++) {
            // Strings of length 1 are palindromes
            palindromes[i][i] = true;
            cuts[i][i] = 0;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;

                if (len == 2) {
                    palindromes[i][j] = (str.charAt(i) == str.charAt(j));
                } else {
                    palindromes[i][j] = (str.charAt(i) == str.charAt(j))
                            && palindromes[i + 1][j - 1];
                }

                if (palindromes[i][j]) {
                    cuts[i][j] = 0;
                } else {
                    cuts[i][j] = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        cuts[i][j] = Math.min(cuts[i][j],
                                cuts[i][k] + 1 + cuts[k + 1][j]);
                    }
                }
            }
        }

        return cuts[0][n - 1];
    }

    public static int minimumPalindromePartitioningRecursive(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        if (str.length() == 0 || StringAlgorithms.isPalidrome(str)) {
            return 0;
        }

        return minimumPalindromePartitioningRecursive(str, 0, str.length() - 1);
    }

    public static int minimumPalindromePartitioningRecursive(String str, int start, int end) {
        if (start == end) {
            return 0;
        }

        if (StringAlgorithms.isPalidrome(str.substring(start, end + 1))) {
            return 0;
        }

        int minCuts = Integer.MAX_VALUE;
        for (int k = start; k < end; k++) {
            int cuts = minimumPalindromePartitioningRecursive(str, start, k) + 1 + minimumPalindromePartitioningRecursive(str, k + 1, end);
            minCuts = Math.min(minCuts, cuts);
        }

        return minCuts;
    }

    public static int minimumCostForMatrixChainMultiplication(int[] matrixDimensions) {
        // In matrixDimensions, array i has the dimensions matrixlengths[i-1] x matrixDimensions[i]

        if (matrixDimensions == null || matrixDimensions.length == 0) {
            return 0;
        }

        // Note: There are (n-1) matrices represented by matrixDimensions
        int n = matrixDimensions.length;

        // minCosts[i][j] => minimum number of scalar operations required for M[i]*M[i+1]*...*M[j]. (M[i] dimensions are matrixDimensions[i-1] & matrixDimensions[i]
        // Note: 0th row & column for the array are not used
        int[][] minCosts = new int[n][n];

        for (int i = 1; i < n; i++) {
            // Multiplying one matrix
            minCosts[i][i] = 0;
        }

        for (int len = 2; len < n; len++) {
            for (int i = 1; i < n - len + 1; i++) {
                int j = i + len - 1;

                minCosts[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = minCosts[i][k]
                            + minCosts[k + 1][j]
                            + matrixDimensions[i - 1] * matrixDimensions[k] * matrixDimensions[j];
                    minCosts[i][j] = Math.min(minCosts[i][j], cost);
                }
            }
        }

        // Note: returning from 1st row
        return minCosts[1][n - 1];
    }

    public static int minimumCostForMatrixChainMultiplicationRecursive(int[] matrixDimensions) {
        // In matrixDimensions, array i has the dimensions matrixlengths[i-1] x matrixDimensions[i]

        if (matrixDimensions == null || matrixDimensions.length == 0) {
            return 0;
        }

        return minimumCostForMatrixChainMultiplicationRecursive(matrixDimensions, 0, matrixDimensions.length - 1);
    }

    private static int minimumCostForMatrixChainMultiplicationRecursive(int[] matrixDimensions, int start, int end) {
        if (end == start + 1) {
            // Single matrix
            return 0;
        }

        int minCost = Integer.MAX_VALUE;
        for (int k = start + 1; k < end; k++) {
            int cost = minimumCostForMatrixChainMultiplicationRecursive(matrixDimensions, start, k);
            cost += minimumCostForMatrixChainMultiplicationRecursive(matrixDimensions, k, end);
            cost += (matrixDimensions[start] * matrixDimensions[k] * matrixDimensions[end]);

            minCost = Math.min(minCost, cost);
        }

        return minCost;
    }

    public static int lengthOfLongestArithmeticProgression(int[] arr) {
        // Note: Assuming input array is sorted

        if (arr == null) {
            return 0;
        }

        int n = arr.length;
        if (n <= 2) {
            return n;
        }

        // llap[i][j] denotes the length of the longest arithmetic progression that has arr[i] & arr[j] as its first & second elements in the array
        // Note: only cells that has i < j are valid in the llap array
        int[][] llap = new int[n][n];
        int maxLlap = 2;

        for (int i = 0; i < n; i++) {
            // Last column. Fill with 2.
            llap[i][n - 1] = 2;
        }

        for (int j = n - 2; j > 0; j--) {
            // Try to find indices i, j, k such that their numbers are in AP
            int i = j - 1;
            int k = j + 1;

            while (i >= 0 && k < n) {
                int a = arr[i];
                int b = arr[j];
                int c = arr[k];

                if (a + c < 2 * b) {
                    k++;
                } else if (a + c > 2 * b) {
                    llap[i][j] = 2;
                    i--;
                } else {
                    // a, b, c are in AP
                    llap[i][j] = llap[j][k] + 1;
                    maxLlap = Math.max(maxLlap, llap[i][j]);

                    i--;
                    k++;
                }
            }

            while (i >= 0) {
                // Fill up with 2 for rest of the 'i''s for the current j
                llap[i][j] = 2;
                i--;
            }
        }

        return maxLlap;
    }

    public static int lengthOfLongestGeometricProgression(int[] arr) {
        // Note: Assuming input array is sorted

        if (arr == null) {
            return 0;
        }

        int n = arr.length;
        if (n < 2) {
            return n;
        }

        if (n == 2) {
            return (arr[1] % arr[0] == 0) ? 2 : 1;
        }

        // llgp[i][j] denotes the length of the longest geometric progression that has arr[i] & arr[j] as its first & second elements in the array
        // Note: only cells that has i < j are valid in the llgp array
        int[][] llgp = new int[n][n];
        int maxLlgp = 1;

        for (int i = 0; i < n; i++) {
            // Last column. Fill with 2 or 1.
            llgp[i][n - 1] = (i != n - 1) && (arr[n - 1] % arr[i] == 0) ? 2 : 1;
            maxLlgp = Math.max(maxLlgp, llgp[i][n - 1]);
        }

        for (int j = n - 2; j > 0; j--) {
            // Try to find indices i, j, k such that their numbers are in GP
            int i = j - 1;
            int k = j + 1;

            while (i >= 0 && k < n) {
                int a = arr[i];
                int b = arr[j];
                int c = arr[k];

                if (a * c < Math.pow(b, 2)) {
                    k++;
                } else if (a * c > Math.pow(b, 2)) {
                    llgp[i][j] = (arr[j] % arr[i] == 0) ? 2 : 1;
                    maxLlgp = Math.max(maxLlgp, llgp[i][j]);

                    i--;
                } else {
                    // a, b, c are in GP
                    llgp[i][j] = llgp[j][k] + 1;
                    maxLlgp = Math.max(maxLlgp, llgp[i][j]);

                    i--;
                    k++;
                }
            }

            while (i >= 0) {
                llgp[i][j] = (arr[j] % arr[i] == 0) ? 2 : 1;
                i--;
            }
        }

        return maxLlgp;
    }

    public static int longestZigZagSubsequence(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;

        // dp[i][0] => length of the longest zig-zag subsequence that ends at i & where the last element is greater than the previous.
        // dp[i][1] => length of the longest zig-zag subsequence that ends at i & where the last element is smaller than the previous.
        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
            dp[i][1] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i][0] = Math.max(dp[i][0], dp[j][1] + 1);
                } else if (arr[j] > arr[i]) {
                    dp[i][1] = Math.max(dp[i][1], dp[j][0] + 1);
                }
            }
        }

        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            maxLength = Utilities.max(maxLength, dp[i][0], dp[i][1]);
        }

        return maxLength;
    }

    public static int longestAlternatingSubsequence(int[] arr) {
        // For alternating subsequences,
        //      x1 < x2 > x3 < x4 > x5 < …. xn or
        //      x1 > x2 < x3 > x4 < x5 > …. xn

        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;

        // las[i][0] => las where arr[i] is lesser than the previous element
        // las[i][1] => las where arr[i] is greater than the previous element
        int[][] las = new int[n][2];
        las[0][0] = arr[0];
        las[0][1] = arr[0];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    las[i][1] = Math.max(las[i][1], las[j][0] + arr[i]);
                } else if (arr[i] < arr[j]) {
                    las[i][0] = Math.max(las[i][1], las[j][1] + arr[i]);
                }
            }
        }

        int maxLas = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxLas = Utilities.max(maxLas, las[i][0], las[i][1]);
        }

        return maxLas;
    }

    public static int maximumSumBitonicSubsequence(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;

        // Maximum sum increasing subsequence
        int[] msis = new int[n];
        msis[0] = arr[0];
        for (int i = 1; i < n; i++) {
            msis[i] = arr[i];
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    msis[i] = Math.max(msis[i], msis[j] + arr[i]);
                }
            }
        }

        // Maximum sum decreasing subsequence
        int[] msds = new int[n];
        msds[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            msds[i] = arr[i];
            for (int j = n - 1; j > i; j--) {
                if (arr[i] > arr[j]) {
                    msds[i] = Math.max(msds[i], msds[j] + arr[i]);
                }
            }
        }

        int maxSumBitonicSubsequence = 0;
        for (int i = 0; i < n; i++) {
            maxSumBitonicSubsequence = Math.max(maxSumBitonicSubsequence,
                    msis[i] + msds[i] - arr[i]);
        }

        return maxSumBitonicSubsequence;
    }

    public static int maximumSumBitonicSubarray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;

        // Maximum sum increasing subarray
        int[] msis = new int[n];
        msis[0] = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1]) {
                msis[i] = arr[i] + msis[i - 1];
            } else {
                msis[i] = arr[i];
            }
        }

        // Maximum sum decreasing subarray
        int[] msds = new int[n];
        msds[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                msds[i] = arr[i] + msds[i + 1];
            } else {
                msds[i] = arr[i];
            }
        }

        int maxSumBitonicSubarray = 0;
        for (int i = 0; i < n; i++) {
            maxSumBitonicSubarray = Math.max(maxSumBitonicSubarray,
                    msis[i] + msds[i] - arr[i]);
        }

        return maxSumBitonicSubarray;
    }

    public static boolean subsetWithSumDivisibleByMExists(int[] arr, int m) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        int n = arr.length;

        if (n > m) {
            // A subset with sum divisible by m always exists (Pigeonhole principle)
            return true;
        }

        // Represents sum (modulo) m values that can be made from the subsets of the array
        boolean[] remainders = new boolean[m];

        for (int i = 0; i < n; i++) {
            if (remainders[0]) {
                // Found a subset whose sum is divisible by m.
                break;
            }

            boolean[] tempRemainders = new boolean[m];

            // For all possible remainders of <sum> modulo m
            for (int j = 0; j < m; j++) {
                if (remainders[j]) {
                    int rem = (j + arr[i]) % m;
                    if (!remainders[rem]) {
                        tempRemainders[rem] = true;
                    }
                }
            }

            for (int j = 0; j < m; j++) {
                if (tempRemainders[j]) {
                    remainders[j] = true;
                }
            }

            // arr[i] also represents a single element subset
            int rem = arr[i] % m;
            remainders[rem] = true;
        }

        // remainders[0] represents the existance of the subset whose sum modulo m is 0.
        return remainders[0];
    }

    public static int maximumSumOfIncreasingSubsequence(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;
        int[] msis = new int[n];

        msis[0] = arr[0];
        for (int i = 1; i < n; i++) {
            int maxSum = arr[i];

            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    maxSum = Math.max(maxSum, msis[j] + arr[i]);
                }
            }

            msis[i] = maxSum;
        }

        int maxSum = msis[0];
        for (int i = 1; i < n; i++) {
            maxSum = Math.max(maxSum, msis[i]);
        }

        return maxSum;
    }

    public static List<Integer> getMaximumSumOfIncreasingSubsequence(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        int n = arr.length;
        int[] msis = new int[n];
        List<List<Integer>> msisLists = new ArrayList<>();

        msis[0] = arr[0];

        List<Integer> runningList = new ArrayList<>();
        runningList.add(arr[0]);
        msisLists.add(runningList);

        for (int i = 1; i < n; i++) {
            msis[i] = arr[i];
            runningList = null;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]
                        && msis[i] < msis[j] + arr[i]) {
                    msis[i] = msis[j] + arr[i];
                    runningList = msisLists.get(j);
                }
            }

            List<Integer> list = new ArrayList<>();
            if (runningList != null) {
                list.addAll(runningList);
            }

            list.add(arr[i]);
            msisLists.add(list);
        }

        int maxIndex = 0;
        int maxSum = arr[0];
        for (int i = 1; i < n; i++) {
            if (maxSum < msis[i]) {
                maxSum = msis[i];
                maxIndex = i;
            }
        }

        return msisLists.get(maxIndex);
    }

    public static int maximumProductOfIncreasingSubsequence(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;
        int[] mpis = new int[n];
        mpis[0] = arr[0];
        int maxProduct = mpis[0];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    // Increasing subsequence
                    int product = arr[i] * mpis[j];
                    mpis[i] = Math.max(mpis[i], product);
                    maxProduct = Math.max(maxProduct, mpis[i]);
                }
            }
        }

        return maxProduct;
    }

    public static int minimumSumNonDecreasingSubsequenceOfLengthK(int[] arr, int length) {
        if (arr == null || arr.length == 0 || length <= 0) {
            return 0;
        }

        int n = arr.length;
        int[][] dp = new int[n][length + 1];
        Utilities.fillArray(dp, Integer.MAX_VALUE);

        int min = arr[0];
        for (int i = 0; i < n; i++) {
            min = Math.min(min, arr[i]);
            dp[i][0] = 0;
            dp[i][1] = min;
        }

        for (int i = 1; i < n; i++) {
            for (int k = 2; k <= i + 1 && k <= length; k++) {
                int minValue = Integer.MAX_VALUE;

                for (int j = 0; j < i; j++) {
                    if (arr[i] >= arr[j]) {
                        if (dp[j][k - 1] == Integer.MAX_VALUE) {
                            continue;
                        }

                        int value = Math.min(dp[j][k - 1] + arr[i],
                                dp[j][k]);
                        minValue = Math.min(minValue, value);
                    } else {
                        minValue = Math.min(minValue, dp[j][k]);
                    }
                }

                dp[i][k] = minValue;
            }
        }

        return dp[n - 1][length];
    }

    public static int countUniquePathsFromTopLeftToBottomRightInGridWithObstacles(int[][] grid) {
        // In the grid, 0 implies free and non-zero denotes an obstacle.
        // Navigation is allowed to right or down
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        for (var row : grid) {
            if (row.length != n) {
                // Invalid grid
                return 0;
            }
        }

        if (grid[0][0] == 1 || grid[n - 1][m - 1] == 1) {
            // Obstacle at start or end
            return 0;
        }

        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    // Left column / Top row
                    dp[i][j] = 1;
                } else {
                    if (grid[i][j] == 0) {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    }
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    public static int lengthOfShortestCommonSupersequence(String str1, String str2) {
        // This implementation re-uses the longest common subsequence for the 2 strings.
        // This can also be solved as a DP by itself using the following recursion.
        //      if(str1.charAt(i - 1) == str2.charAt(j - 1)) {
        //          dp[i][j] = 1 + dp[i - 1][j - 1];
        //      }
        //      else {
        //          dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]
        //      }

        if (str1 == null) {
            return str2.length();
        }

        if (str2 == null) {
            return str1.length();
        }

        int lcsLength = longestCommonSubsequence(str1, str2);
        return (str1.length() + str2.length()) - lcsLength;
    }

    public static int maximumSumAlternatingSubsequence(int[] arr) {
        // Note: Alternating subsequence follows the order decreasing - increasing - decreasing.... between each pair

        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;
        if (n == 1) {
            return arr[0];
        }

        // msas[i][0] -> length of maximum alternate subsequence such that arr[i] is smaller than the previous element
        // msas[i][1] -> length of maximum alternate subsequence such that arr[i] is larger than the previous element
        int[][] msas = new int[n][2];
        msas[0][0] = arr[0];
        msas[0][1] = arr[0];

        // Flag - We should consider increasing only after a decreasing has occured.
        boolean decreasingEncountered = false;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && decreasingEncountered) {
                    // Increasing
                    msas[i][1] = Math.max(msas[i][1],
                            msas[j][0] + arr[i]);

                } else if (arr[i] < arr[j]) {
                    // Decreasing
                    msas[i][0] = Math.max(msas[i][0],
                            msas[j][1] + arr[i]);

                    decreasingEncountered = true;
                }
            }
        }

        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxValue = Utilities.max(maxValue,
                    msas[i][0],
                    msas[i][1]);
        }

        return maxValue;
    }

    public static int lengthOfShortestUncommonSubsequence(String str1, String str2) {
        // Expected: length of the shortest subsequence in str1, which is not present in str2
        if (str1 == null || str1.length() == 0) {
            return -1;
        }

        if (str2 == null) {
            // Any character in str1 is an uncommon subsequence
            return 1;
        }

        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m + 1][n + 1];
        // For empty str2, sus is 1.
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }

        // For empty str1, sus is max
        for (int j = 0; j <= n; j++) {
            dp[0][j] = m + 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char c = str1.charAt(i - 1);
                int index;
                for (index = j - 1; index >= 0; index--) {
                    if (c == str2.charAt(index)) {
                        break;
                    }
                }

                if (index == -1) {
                    // c not found in str2
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j],
                            1 + dp[i - 1][index]);
                }
            }
        }

        int length = dp[m][n];
        return length == m + 1 ? -1 : length;
    }

    public static int lengthOfShortestUncommonSubsequenceRecursive(String str1, String str2) {
        // Expected: length of the shortest subsequence in str1, which is not present in str2
        if (str1 == null || str1.length() == 0) {
            return -1;
        }

        if (str2 == null) {
            // Any character in str1 is an uncommon subsequence
            return 1;
        }

        int m = str1.length();
        int n = str2.length();

        int length = lengthOfShortestUncommonSubsequenceRecursive(str1, str2, m, n, 0, 0);
        return length == m + 1 ? -1 : length;
    }

    private static int lengthOfShortestUncommonSubsequenceRecursive(String str1, String str2, int m, int n, int i, int j) {
        if (i == m) {
            // Reached end of str1
            return m + 1;
        }

        if (j == n) {
            // Reached end of str2, with str1 still having characters
            return 1;
        }

        // Find first occurance of mth char from str1 in str2
        char c = str1.charAt(i);
        int index;
        for (index = j; index < n; index++) {
            if (c == str2.charAt(index)) {
                break;
            }
        }

        if (index == n) {
            // c not found in str2
            return 1;
        }

        int exclusive = lengthOfShortestUncommonSubsequenceRecursive(str1, str2, m, n, i + 1, j);
        int inclusive = 1 + lengthOfShortestUncommonSubsequenceRecursive(str1, str2, m, n, i + 1, index + 1);

        return Math.min(exclusive, inclusive);
    }

    public static int countDistinctSubsequences(String str) {
        // If all characters in the string are distinct, the count is
        //      C(n, 0) + C(n, 1) + C(n, 2) + ... + C(n, n) = 2^n

        // Since the string may have non-distinct characters, the recursion is
        //      ds(n) = 2 * ds(n - 1) - repetitions
        //          repetitions = 0, if the character has not been encountered before
        //                      = ds(index) of the previous occurance of the character, if encountered

        if (str == null) {
            return 0;
        }

        if (str.length() == 0) {
            return 1;
        }

        int n = str.length();

        Map<Character, Integer> map = new HashMap<>();
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            dp[i] = 2 * dp[i - 1];

            char c = str.charAt(i - 1);
            if (map.containsKey(c)) {
                dp[i] = dp[i] - dp[map.get(c)];
            }

            map.put(c, i - 1);
        }

        return dp[n];
    }

    public static int lengthOfLongestIncreasingOddEvenSubsequence(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;

        // lieos[i] denotes the length of the lieos ending at i
        int[] lieos = new int[n];
        Arrays.fill(lieos, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]
                        && (arr[i] + arr[j]) % 2 != 0) {
                    lieos[i] = Math.max(lieos[i], lieos[j] + 1);
                }
            }
        }

        int maxLength = Arrays.stream(lieos).max().getAsInt();
        return maxLength;
    }

    public static int lengthOfLongestParanthesisBalancedSubsequence(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int n = str.length();

        // dp[i][j] denotes the length of the longest balanced subsequence of the string str(i...j)
        int[][] dp = new int[n][n];

        // For substrings of length 2
        for (int i = 0; i < n - 1; i++) {
            if (str.charAt(i) == '(' && str.charAt(i + 1) == ')') {
                dp[i][i + 1] = 2;
            }
        }

        for (int len = 3; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;

                if (str.charAt(i) == '(' && str.charAt(j) == ')') {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                }

                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j],
                            dp[i][k] + dp[k + 1][j]);
                }
            }

        }

        return dp[0][n - 1];
    }

    public static int lengthOfVertexCover(BinaryTreeNode root) {
        if (root == null) {
            // Empty tree - zero cover
            return 0;
        }

        if (root.getLeft() == null && root.getRight() == null) {
            // Only one node - zero cover
            return 0;
        }

        Map<BinaryTreeNode, Integer> memo = new HashMap<>();
        return lengthOfVertexCoverMemoized(root, memo);
    }

    private static int lengthOfVertexCoverMemoized(BinaryTreeNode root, Map<BinaryTreeNode, Integer> memo) {
        if (root == null) {
            // Empty tree - zero cover
            return 0;
        }

        if (root.getLeft() == null && root.getRight() == null) {
            // Only one node - zero cover
            return 0;
        }

        if (memo.containsKey(root)) {
            return memo.get(root);
        }

        // Try with root included in the vertex cover
        int inclusive = 1 + lengthOfVertexCoverMemoized(root.getLeft(), memo) + lengthOfVertexCoverMemoized(root.getRight(), memo);

        //  Try with root excluded from the vertex cover (In this case, both the children should be included
        int exclusive = 0;
        if (root.getLeft() != null) {
            exclusive += 1 + lengthOfVertexCoverMemoized(root.getLeft().getLeft(), memo) + lengthOfVertexCoverMemoized(root.getLeft().getRight(), memo);
        }

        if (root.getRight() != null) {
            exclusive += 1 + lengthOfVertexCoverMemoized(root.getRight().getLeft(), memo) + lengthOfVertexCoverMemoized(root.getRight().getRight(), memo);
        }

        int lengthOfCover = Math.min(inclusive, exclusive);
        memo.put(root, lengthOfCover);

        return lengthOfCover;
    }

    public static int lengthOfVertexCoverRecursive(BinaryTreeNode root) {
        if (root == null) {
            // Empty tree - zero cover
            return 0;
        }

        if (root.getLeft() == null && root.getRight() == null) {
            // Only one node - zero cover
            return 0;
        }

        // Try with root included in the vertex cover
        int inclusive = 1 + lengthOfVertexCoverRecursive(root.getLeft()) + lengthOfVertexCoverRecursive(root.getRight());

        //  Try with root excluded from the vertex cover (In this case, both the children should be included
        int exclusive = 0;
        if (root.getLeft() != null) {
            exclusive += 1 + lengthOfVertexCoverRecursive(root.getLeft().getLeft()) + lengthOfVertexCoverRecursive(root.getLeft().getRight());
        }

        if (root.getRight() != null) {
            exclusive += 1 + lengthOfVertexCoverRecursive(root.getRight().getLeft()) + lengthOfVertexCoverRecursive(root.getRight().getRight());
        }

        return Math.min(inclusive, exclusive);
    }

    public static List<BinaryTreeNode> getVertexCover(BinaryTreeNode root) {
        if (root == null) {
            // Empty tree - zero cover
            return null;
        }

        if (root.getLeft() == null && root.getRight() == null) {
            // Only one node - zero cover
            return null;
        }

        Map<BinaryTreeNode, List<BinaryTreeNode>> memo = new HashMap<>();
        generateVertexCover(root, memo);

        return memo.get(root);
    }

    private static int generateVertexCover(BinaryTreeNode root, Map<BinaryTreeNode, List<BinaryTreeNode>> memo) {
        if (root == null) {
            // Empty tree - zero cover
            return 0;
        }

        if (root.getLeft() == null && root.getRight() == null) {
            memo.put(root, new ArrayList<>());
            return 0;
        }

        if (memo.containsKey(root)) {
            return memo.get(root).size();
        }

        // Try with root included in the vertex cover
        int inclusive = 1 + generateVertexCover(root.getLeft(), memo) + generateVertexCover(root.getRight(), memo);

        //  Try with root excluded from the vertex cover (In this case, both the children should be included
        int exclusive = 0;
        if (root.getLeft() != null) {
            exclusive += 1 + generateVertexCover(root.getLeft().getLeft(), memo) + generateVertexCover(root.getLeft().getRight(), memo);
        }

        if (root.getRight() != null) {
            exclusive += 1 + generateVertexCover(root.getRight().getLeft(), memo) + generateVertexCover(root.getRight().getRight(), memo);
        }

        List<BinaryTreeNode> vertexCover = new ArrayList<>();
        if (inclusive < exclusive) {
            vertexCover.add(root);

            if (root.getLeft() != null) {
                vertexCover.addAll(memo.get(root.getLeft()));
            }

            if (root.getRight() != null) {
                vertexCover.addAll(memo.get(root.getRight()));
            }
        } else {
            if (root.getLeft() != null) {
                vertexCover.add(root.getLeft());

                if (root.getLeft().getLeft() != null) {
                    vertexCover.addAll(memo.get(root.getLeft().getLeft()));
                }

                if (root.getLeft().getRight() != null) {
                    vertexCover.addAll(memo.get(root.getLeft().getRight()));
                }
            }

            if (root.getRight() != null) {
                vertexCover.add(root.getRight());

                if (root.getRight().getLeft() != null) {
                    vertexCover.addAll(memo.get(root.getRight().getLeft()));
                }

                if (root.getRight().getRight() != null) {
                    vertexCover.addAll(memo.get(root.getRight().getRight()));
                }
            }
        }

        memo.put(root, vertexCover);
        int lengthOfCover = vertexCover.size();
        return lengthOfCover;
    }

    public static int getMaximumProfitFromWeightedActivityScheduling(List<Activity> activities) {
        if (activities == null || activities.size() == 0) {
            return 0;
        }

        // Sort activities by finish time
        activities.sort(new ActivityFinishComparator());

        int n = activities.size();

        // dp[i] denotes the maximum profit when there are i activities
        int[] dp = new int[n];
        dp[0] = activities.get(0).getProfit();

        for (int i = 1; i < n; i++) {
            // Profit, including the current activity
            int inclusiveProfit = activities.get(i).getProfit();

            int previousActivityIndex = getPreviousActivityIndexUsingBinarySearch(activities, i);
            if (previousActivityIndex != -1) {
                inclusiveProfit += dp[previousActivityIndex];
            }

            // Profit, excluding the current activity
            int exclusiveProfit = dp[n - 1];

            dp[i] = Math.max(inclusiveProfit, exclusiveProfit);
        }

        return dp[n - 1];
    }

    public static int getMaximumProfitFromWeightedActivitySchedulingRecursive(List<Activity> activities) {
        if (activities == null || activities.size() == 0) {
            return 0;
        }

        // Sort activities by finish time
        activities.sort(new ActivityFinishComparator());
        return getMaximumProfitFromWeightedActivitySchedulingRecursive(activities, activities.size() - 1);
    }

    private static int getMaximumProfitFromWeightedActivitySchedulingRecursive(List<Activity> activities, int index) {
        if (index == 0) {
            return activities.get(0).getProfit();
        }

        // Profit, including the activity
        int inclusiveProfit = activities.get(index).getProfit();

        int previousActivityIndex = getPreviousActivityIndex(activities, index);
        if (previousActivityIndex != -1) {
            inclusiveProfit += getMaximumProfitFromWeightedActivitySchedulingRecursive(activities, previousActivityIndex);
        }

        // Profit, excluding the activity
        int exclusiveProfit = getMaximumProfitFromWeightedActivitySchedulingRecursive(activities, index - 1);

        return Math.max(inclusiveProfit, exclusiveProfit);
    }

    private static int getPreviousActivityIndex(List<Activity> activities, int activityIndex) {
        var currentActivity = activities.get(activityIndex);
        int previousActivityIndex = -1;
        for (int i = activityIndex - 1; i >= 0; i--) {
            var activity = activities.get(i);
            if (activity.getFinish() <= currentActivity.getStart()) {
                previousActivityIndex = i;
            }
        }

        return previousActivityIndex;
    }

    private static int getPreviousActivityIndexUsingBinarySearch(List<Activity> activities, int activityIndex) {
        // A log-n optimized implementation of get-previous-activity-index
        var currentActivity = activities.get(activityIndex);

        int left = 0;
        int right = activityIndex - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            var activity = activities.get(mid);

            if (activity.getFinish() <= currentActivity.getStart()) {
                if (mid < activityIndex - 1) {
                    var nextActivity = activities.get(mid + 1);

                    if (nextActivity.getFinish() <= currentActivity.getStart()) {
                        left = mid;
                        continue;
                    }
                }

                return mid;
            } else {
                right = mid;
            }

        }

        return -1;
    }

    public static List<Activity> getActivitiesForMaximumProfitFromWeightedActivityScheduling(List<Activity> activities) {
        // Approach similar to LIS
        if (activities == null || activities.size() == 0) {
            return null;
        }

        // Sort activities by finish time
        activities.sort(new ActivityStartComparator());

        int n = activities.size();

        List<List<Activity>> maxProfitList = new ArrayList<>(n);

        List<Activity> runningList = new ArrayList<>();
        runningList.add(activities.get(0));
        maxProfitList.add(runningList);

        for (int i = 1; i < n; i++) {
            var ai = activities.get(i);
            runningList = new ArrayList<>();
            maxProfitList.add(runningList);

            int maxProfit = Integer.MIN_VALUE;
            int maxProfitIndex = -1;
            for (int j = 0; j < i; j++) {
                var aj = activities.get(j);
                if (aj.getFinish() <= ai.getStart()) {
                    int profit = maxProfitList.get(j).stream().mapToInt(a -> a.getProfit()).sum();
                    if (profit > maxProfit) {
                        maxProfit = profit;
                        maxProfitIndex = j;
                    }
                }
            }

            if (maxProfitIndex != -1) {
                runningList.addAll(maxProfitList.get(maxProfitIndex));
            }

            runningList.add(ai);
        }

        var activitiesForMaxProfit = maxProfitList.get(0);
        int maxProfit = activitiesForMaxProfit.stream().mapToInt(a -> a.getProfit()).sum();
        for (int i = 1; i < n; i++) {
            int profit = maxProfitList.get(i).stream().mapToInt(a -> a.getProfit()).sum();
            if (profit > maxProfit) {
                activitiesForMaxProfit = maxProfitList.get(i);
                maxProfit = profit;
            }
        }

        return activitiesForMaxProfit;
    }

    public static int numberOfWaysToReachScoreInAGame(int totalScore, int[] turnScores) {
        // In one turn, one of turn scores can be scored (ex: 3 or 5 or 10)
        if (totalScore < 0 || turnScores == null || turnScores.length == 0) {
            return 0;
        }

        // dp[i] denotes the number of ways by which a score "i" can be obtained
        int[] dp = new int[totalScore + 1];
        Arrays.fill(dp, 0);

        // One way to reach zero score
        dp[0] = 1;

        for (int score : turnScores) {
            for (int i = score; i <= totalScore; i++) {
                dp[i] = dp[i] + dp[i - score];
            }
        }

        return dp[totalScore];
    }

    public static int maximumBridgesThatCanBeBuiltWithoutOverlaps(List<IntPair> cityPairs) {
        // For each city pair, one is on the north side of a river, and the other on the south side
        // The values in each pair denotes the co-ordinates on the x-axis of the city
        if (cityPairs == null || cityPairs.size() == 0) {
            return 0;
        }

        cityPairs.sort((x, y) ->
        {
            if (x.getB() != y.getB()) {
                return x.getB() - y.getB();
            } else {
                return x.getA() - y.getA();
            }
        });

        int n = cityPairs.size();
        int[] lis = new int[n];
        Arrays.fill(lis, 1);

        // Fill lis based on north-city
        for (int i = 1; i < n; i++) {
            var iCity = cityPairs.get(i);
            for (int j = 0; j < i; j++) {
                var jCity = cityPairs.get(j);
                if (iCity.getA() >= jCity.getA()) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }

        int maxBridges = lis[0];
        for (int i = 1; i < n; i++) {
            maxBridges = Math.max(maxBridges, lis[i]);
        }

        return maxBridges;
    }

    public static boolean checkForPathInMatrix(int[][] matrix) {
        // In the matrix, -1 indicates an obstacle, 0 indicates passage
        // Check for path from top-left to bottom-right. Path can be traversed right or down
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0 || matrix[0][0] == -1) {
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        for (var row : matrix) {
            if (row.length != n) {
                return false;
            }

            for (int cell : row) {
                if (cell != -1 && cell != 0) {
                    // Value other than 0 or -1 found in cell
                    return false;
                }
            }
        }

        if (matrix[m - 1][n - 1] == -1) {
            // Last cell has an obstacle
            return false;
        }

        // Algorithm: Mark traversable cells as 1, starting from top-left. Final value of 1 at bottom right indicates existance of path
        matrix[0][0] = 1;

        // Set first column
        for (int i = 1; i < m; i++) {
            if (matrix[i][0] != -1) {
                matrix[i][0] = matrix[i - 1][0];
            }
        }

        // Set first row
        for (int j = 1; j < n; j++) {
            if (matrix[0][j] != -1) {
                matrix[0][j] = matrix[0][j - 1];
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] != -1) {
                    matrix[i][j] = Math.max(matrix[i - 1][j],
                            matrix[i][j - 1]);
                }
            }
        }

        return matrix[m - 1][n - 1] == 1 ? true : false;
    }

    public static int maximumPathSumInTriangle(int[][] triangle) {
        if (triangle == null || triangle.length == 0 || triangle[0].length == 0) {
            return 0;
        }

        int m = triangle.length;
        int n = triangle[0].length;

        for (var row : triangle) {
            if (row.length != n) {
                return 0;
            }
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                triangle[i][j] = triangle[i][j] +
                        Math.max(triangle[i + 1][j], triangle[i + 1][j + 1]);
            }
        }

        return triangle[0][0];
    }

    public static int minimumPathSumInTriangle(int[][] triangle) {
        if (triangle == null || triangle.length == 0 || triangle[0].length == 0) {
            return 0;
        }

        int m = triangle.length;
        int n = triangle[0].length;

        for (var row : triangle) {
            if (row.length != n) {
                return 0;
            }
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                triangle[i][j] = triangle[i][j] +
                        Math.min(triangle[i + 1][j], triangle[i + 1][j + 1]);
            }
        }

        return triangle[0][0];
    }

    public static int minimumPathSumIn3DArray(int[][][] arr) {
        if (arr == null || arr.length == 0 || arr[0].length == 0 || arr[0][0].length == 0) {
            return 0;
        }

        int l = arr.length;
        int m = arr[0].length;
        int n = arr[0][0].length;

        int[][][] minPathSum = new int[l][m][n];
        minPathSum[0][0][0] = arr[0][0][0];

        // Fill for x-axis
        for (int i = 1; i < l; i++) {
            minPathSum[i][0][0] = minPathSum[i - 1][0][0] + arr[i][0][0];
        }

        // Fill for y-axis
        for (int j = 1; j < m; j++) {
            minPathSum[0][j][0] = minPathSum[0][j - 1][0] + arr[0][j][0];
        }

        // Fill for z-axis
        for (int k = 1; k < n; k++) {
            minPathSum[0][0][k] = minPathSum[0][0][k - 1] + arr[0][0][k];
        }

        // Fill for x-y plane
        for (int i = 1; i < l; i++) {
            for (int j = 1; j < m; j++) {
                minPathSum[i][j][0] = Math.min(minPathSum[i - 1][j][0],
                        minPathSum[i][j - 1][0]) + arr[i][j][0];
            }
        }

        // Fill for x-z plane
        for (int i = 1; i < l; i++) {
            for (int k = 1; k < n; k++) {
                minPathSum[i][0][k] = Math.min(minPathSum[i - 1][0][k],
                        minPathSum[i][0][k - 1]) + arr[i][0][k];
            }
        }

        // Fill for y-z plane
        for (int j = 1; j < m; j++) {
            for (int k = 1; k < n; k++) {
                minPathSum[0][j][k] = Math.min(minPathSum[0][j - 1][k],
                        minPathSum[0][j][k - 1]) + arr[0][j][k];
            }
        }

        // Fill remaining cells
        for (int i = 1; i < l; i++) {
            for (int j = 1; j < m; j++) {
                for (int k = 1; k < n; k++) {
                    minPathSum[i][j][k] = Utilities.min(minPathSum[i - 1][j][k],
                            minPathSum[i][j - 1][k],
                            minPathSum[i][j][k - 1]) + arr[i][j][k];
                }
            }
        }

        return minPathSum[l - 1][m - 1][n - 1];
    }

    public static int numberOfChangesToConvertToStrictlyIncreasingArray(int[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == 0) {
            return Integer.MAX_VALUE;
        }

        int n = arr.length;
        int[] lis = new int[n];
        Arrays.fill(lis, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]
                        && (arr[i] - arr[j]) >= (i - j)) {
                    // Increasing, and has space for inserting any missing values
                    lis[i] = Math.max(lis[i], 1 + lis[j]);
                }
            }
        }

        int lisLength = Arrays.stream(lis).max().getAsInt();

        // Number of changes = Number of elements - Length of LIS
        return n - lisLength;
    }

    public static int minimumJumpsRequiredToReachEndOfArray(int[] arr) {
        // Each element in the array denotes the maximum number of elements that can be jumped from that point.
        // Start with the first element (index - 0) & reach the last (index -  n-1)

        if (arr == null || arr.length == 0 || arr[0] == 0) {
            return Integer.MAX_VALUE;
        }

        int n = arr.length;

        // minJumps[i] denotes the minimum number of jumps required to reach arr[i] from arr[0]
        int[] minJumps = new int[n];
        Arrays.fill(minJumps, Integer.MAX_VALUE);

        minJumps[0] = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (minJumps[j] == Integer.MAX_VALUE
                        || arr[j] == 0) {
                    continue;
                }

                int reach = j + arr[j];
                if (reach < i) {
                    continue;
                }

                // i is reachable from j
                minJumps[i] = Math.min(minJumps[i], 1 + minJumps[j]);
            }
        }

        return minJumps[n - 1];
    }

    public static int minimumJumpsRequiredToReachEndOfArrayOptimized(int[] arr) {
        // Each element in the array denotes the maximum number of elements that can be jumped from that point.
        // Start with the first element (index - 0) & reach the last (index -  n-1)
        // O(n) implementation - Other approaches limit at O(n^2)

        if (arr == null || arr.length == 0 || arr[0] == 0) {
            return Integer.MAX_VALUE;
        }

        int n = arr.length;

        int maxReach = arr[0];
        int jumpsUsed = 1;
        int stepsRemaining = arr[0];

        for (int i = 1; i < n; i++) {
            if (i == n - 1) {
                // Reached the end
                return jumpsUsed;
            }

            maxReach = Math.max(maxReach, i + arr[i]);
            stepsRemaining--;

            if (stepsRemaining == 0) {
                if (maxReach < i) {
                    break;
                }

                jumpsUsed++;
                stepsRemaining = maxReach - i;
            }
        }

        return Integer.MAX_VALUE;
    }

    public static int minimumJumpsRequiredToReachEndOfArrayA2(int[] arr) {
        // Each element in the array denotes the maximum number of elements that can be jumped from that point.
        // Start with the first element (index - 0) & reach the last (index -  n-1)

        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;

        // minJumps[i] denotes the minimum number of jumps required to reach arr[n - 1] from arr[i]
        int[] minJumps = new int[n];
        Arrays.fill(minJumps, Integer.MAX_VALUE);

        minJumps[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (j > i + arr[i]) {
                    // j's from now not accessible from i
                    break;
                }

                if (minJumps[j] != Integer.MAX_VALUE) {
                    minJumps[i] = Math.min(minJumps[i], 1 + minJumps[j]);
                }
            }
        }

        return minJumps[0];
    }

    public static int minimumJumpsRequiredToReachEndOfArrayRecursive(int[] arr) {
        // Each element in the array denotes the maximum number of elements that can be jumped from that point.
        // Start with the first element (index - 0) & reach the last (index -  n-1)

        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;
        return minimumJumpsRequiredToReachEndOfArrayRecursive(arr, 0, n - 1);
    }

    private static int minimumJumpsRequiredToReachEndOfArrayRecursive(int[] arr, int startIndex, int endIndex) {
        // Minimum Jumps = 1 + min (Minimum Jumps from all elements reachable from startIndex)

        if (startIndex == endIndex) {
            return 0;
        }

        if (startIndex > endIndex || arr[startIndex] == 0) {
            // Not possible to reach end from this position
            return Integer.MAX_VALUE;
        }

        int reach = Math.min(startIndex + arr[startIndex], endIndex);
        int minJumps = Integer.MAX_VALUE;
        for (int i = startIndex + 1; i <= reach; i++) {
            int jumps = minimumJumpsRequiredToReachEndOfArrayRecursive(arr, i, endIndex);
            if (jumps != Integer.MAX_VALUE) {
                minJumps = Math.min(minJumps, 1 + jumps);
            }
        }

        return minJumps;
    }

    public static int minTimeForCarAssemblyWithTwoAssemblyLines(int[][] stationTimes, int[][] transferTimes, IntPair entryTimes, IntPair exitTimes) {
        if (stationTimes == null || stationTimes.length != 2
                || transferTimes == null || transferTimes.length != 2
                || entryTimes == null || exitTimes == null) {
            return Integer.MAX_VALUE;
        }

        int stations = stationTimes[0].length;
        if (transferTimes[0].length != stations
                || transferTimes[0][0] != 0
                || transferTimes[1][0] != 0) {
            return Integer.MAX_VALUE;
        }

        int[][] minTimes = new int[2][stations + 1];

        // Entry
        minTimes[0][0] = entryTimes.getFirst() + stationTimes[0][0];
        minTimes[1][0] = entryTimes.getSecond() + stationTimes[1][0];

        // Intermediate Stations
        for (int i = 1; i < stations; i++) {
            minTimes[0][i] = Math.min(minTimes[0][i - 1] + stationTimes[0][i],
                    minTimes[1][i - 1] + transferTimes[1][i] + stationTimes[0][i]);
            minTimes[1][i] = Math.min(minTimes[1][i - 1] + stationTimes[1][i],
                    minTimes[0][i - 1] + transferTimes[0][i] + stationTimes[1][i]);
        }

        // Exit
        minTimes[0][stations] = minTimes[0][stations - 1] + exitTimes.getFirst();
        minTimes[1][stations] = minTimes[1][stations - 1] + exitTimes.getSecond();

        return Math.min(minTimes[0][stations], minTimes[1][stations]);
    }

    public static int getCostOfOptimalBinarySearchTreeForSearchFrequencies(int[] keys, int[] frequencies) {
        // A BST to be constructed out of the keys such that the overall cost given the search frequencies is minimum.
        // The highest frequency to be the root -> so cost of accessing it is one.
        // Cost of a node is the frequency multiplied by its level. Root level is 1.

        //  Recursion Formula:
        //      optimalCost(i, j) = Sigma (k = i to j) frequencies[k] + min (r = i to j) (optimalCost(i, r-1) + optimalCost(r + 1, j))
        if (keys == null || keys.length == 0 || frequencies == null || frequencies.length != keys.length) {
            return 0;
        }

        int n = keys.length;

        // Dynamic Programming implementation
        int[][] costs = new int[n][n];

        // Fill the diagonal (length = 1)
        for (int i = 0; i < n; i++) {
            costs[i][i] = frequencies[i];
        }

        // Fill for remaining lengths
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;

                int frequencySum = 0;
                for (int k = i; k <= j; k++) {
                    frequencySum += frequencies[k];
                }

                int minCost = Integer.MAX_VALUE;
                for (int r = i; r <= j; r++) {
                    // Considering keys[r] as the root of the subtree
                    int leftCost = r > i ? costs[i][r - 1] : 0;
                    int rightCost = r < j ? costs[r + 1][j] : 0;
                    int totalCost = leftCost + rightCost + frequencySum;

                    minCost = Math.min(minCost, totalCost);
                }

                costs[i][j] = minCost;
            }
        }

        return costs[0][n - 1];
    }

    public static int getCostOfOptimalBinarySearchTreeForSearchFrequenciesRecursive(int[] keys, int[] frequencies) {
        // A BST to be constructed out of the keys such that the overall cost given the search frequencies is minimum.
        // The highest frequency to be the root -> so cost of accessing it is one.
        // Cost of a node is the frequency multiplied by its level. Root level is 1.

        //  Recursion Formula:
        //      optimalCost(i, j) = Sigma (k = i to j) frequencies[k] + min (r = i to j) (optimalCost(i, r-1) + optimalCost(r + 1, j))
        if (keys == null || keys.length == 0 || frequencies == null || frequencies.length != keys.length) {
            return 0;
        }

        int n = keys.length;
        return getCostOfOptimalBinarySearchTreeForSearchFrequenciesRecursive(keys, frequencies, 0, n - 1);
    }

    private static int getCostOfOptimalBinarySearchTreeForSearchFrequenciesRecursive(int[] keys, int[] frequencies, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            return 0;
        }

        if (startIndex == endIndex) {
            return frequencies[startIndex];
        }

        // Try with every element between (& inclusive of) startIndex & endIndex as root for optimal cost
        // All queries to every element within the range will go through the root.
        int frequencySum = 0;
        for (int k = startIndex; k <= endIndex; k++) {
            frequencySum += frequencies[k];
        }

        int minChildCost = Integer.MAX_VALUE;
        for (int r = startIndex; r <= endIndex; r++) {
            int childCost = 0;
            childCost += getCostOfOptimalBinarySearchTreeForSearchFrequenciesRecursive(keys, frequencies, startIndex, r - 1);
            childCost += getCostOfOptimalBinarySearchTreeForSearchFrequenciesRecursive(keys, frequencies, r + 1, endIndex);

            minChildCost = Math.min(minChildCost, childCost);
        }

        return frequencySum + minChildCost;
    }

    public static int maxValueOfCoinsInOptimalStrategyForGameWhereFirstOrLastCoinCanBeTakenInTurn(int[] coinValues) {
        if (coinValues == null || coinValues.length == 0) {
            return 0;
        }

        // We'll take the first turn, and the opponent will take the next
        // Note: The opponent will play so as to minimize your value in his turn

        // Recursion Formula
        //      MaxValue(i, j) = Max ( Vi + min( MaxValue(i + 2, j), MaxValue(i + 1, j - 1)),
        //                              Vj + min( MaxValue(i + 1, j - 1), MaxValue(i, j - 2))

        int n = coinValues.length;
        int[][] maxValues = new int[n][n];

        // Diagonal - Length 1
        for (int i = 0; i < n; i++) {
            maxValues[i][i] = coinValues[i];
        }

        // Other Lengths
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;

                int factor1 = (i + 2) <= j ? maxValues[i + 2][j] : 0;
                int factor2 = (i + 1) <= (j - 1) ? maxValues[i + 1][j - 1] : 0;
                int factor3 = i <= (j - 2) ? maxValues[i][j - 2] : 0;

                int iVal = coinValues[i] + Math.min(factor1, factor2);
                int jVal = coinValues[j] + Math.min(factor2, factor3);

                maxValues[i][j] = Math.max(iVal, jVal);
            }
        }

        return maxValues[0][n - 1];
    }

    public static int maxValueOfCoinsInOptimalStrategyForGameWhereFirstOrLastCoinCanBeTakenInTurnRecursive(int[] coinValues) {
        if (coinValues == null || coinValues.length == 0) {
            return 0;
        }

        // We'll take the first turn, and the opponent will take the next
        // Note: The opponent will play so as to minimize your value in his turn

        // Recursion Formula
        //      MaxValue(i, j) = Max ( Vi + min( MaxValue(i + 2, j), MaxValue(i + 1, j - 1)),
        //                              Vj + min( MaxValue(i + 1, j - 1), MaxValue(i, j - 2))

        int n = coinValues.length;
        return maxValueOfCoinsInOptimalStrategyForGameWhereFirstOrLastCoinCanBeTakenInTurnRecursive(coinValues, 0, n - 1);
    }

    private static int maxValueOfCoinsInOptimalStrategyForGameWhereFirstOrLastCoinCanBeTakenInTurnRecursive(int[] coinValues, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            return 0;
        }

        if (startIndex == endIndex) {
            return coinValues[startIndex];
        }

        // Try taking the coin at start
        int startValue = coinValues[startIndex];
        startValue += Math.min(
                maxValueOfCoinsInOptimalStrategyForGameWhereFirstOrLastCoinCanBeTakenInTurnRecursive(coinValues, startIndex + 2, endIndex),
                maxValueOfCoinsInOptimalStrategyForGameWhereFirstOrLastCoinCanBeTakenInTurnRecursive(coinValues, startIndex + 1, endIndex - 1)
        );

        // Try taking the coin at end
        int endValue = coinValues[endIndex];
        endValue += Math.min(
                maxValueOfCoinsInOptimalStrategyForGameWhereFirstOrLastCoinCanBeTakenInTurnRecursive(coinValues, startIndex + 1, endIndex - 1),
                maxValueOfCoinsInOptimalStrategyForGameWhereFirstOrLastCoinCanBeTakenInTurnRecursive(coinValues, startIndex, endIndex - 2)
        );

        return Math.max(startValue, endValue);
    }

    public static int minimumCostToMakeTwoStringsIdentical(String str1, String str2, int str1DeletionCost, int str2DeletionCost) {
        if (str1 == null || str2 == null) {
            return 0;
        }

        if (str1.length() == 0 && str2.length() == 0) {
            return 0;
        }

        int m = str1.length();
        int n = str2.length();

        int lcsLength = longestCommonSubsequence(str1, str2);

        int str1Cost = str1DeletionCost * (m - lcsLength);
        int str2Cost = str2DeletionCost * (n - lcsLength);

        return str1Cost + str2Cost;
    }

    public static int minimumInsertionsRequiredToSortAnArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;
        int lisLength = longestIncreasingSubsequence(arr);

        return n - lisLength;
    }

    public static int numberOfWaysToPartitionASetIntoKSubsets(int setSize, int partitionCount) {
        // Sterling numbers of the second kind.
        // Recurrence:
        //      S(n, k) = k * S(n - 1, k) + S(n - 1, k - 1)
        //      S(0, 0) = 1; S(n, 0) = 0; S(0, n) = 0;

        if (setSize < 0 || partitionCount < 0) {
            return 0;
        }

        if (setSize == 0 && partitionCount == 0) {
            return 1;
        }

        if (setSize == 0 || partitionCount == 0) {
            return 0;
        }

        int[][] dp = new int[setSize + 1][partitionCount + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= setSize; i++) {
            dp[i][0] = 0;
        }

        for (int j = 1; j <= partitionCount; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= setSize; i++) {
            for (int j = 1; j <= partitionCount; j++) {
                dp[i][j] = j * dp[i - 1][j] + dp[i - 1][j - 1];
            }
        }

        return dp[setSize][partitionCount];
    }

    public static int numberOfWaysToPartitionASetIntoKSubsetsRecursive(int setSize, int partitionCount) {
        // Sterling numbers of the second kind.
        // Recurrence:
        //      S(n, k) = k * S(n - 1, k) + S(n - 1, k - 1)
        //      S(0, 0) = 1; S(n, 0) = 0; S(0, n) = 0;

        if (setSize < 0 || partitionCount < 0) {
            return 0;
        }

        if (setSize == 0 && partitionCount == 0) {
            return 1;
        }

        if (setSize == 0 || partitionCount == 0) {
            return 0;
        }

        return partitionCount * numberOfWaysToPartitionASetIntoKSubsetsRecursive(setSize - 1, partitionCount)
                + numberOfWaysToPartitionASetIntoKSubsetsRecursive(setSize - 1, partitionCount - 1);
    }

    public static boolean isKPalindrome(String str, int k) {
        // Does string becomes a palindrome by utmost k character deletions ?
        //
        // Approach: Find the length of the longest palindromic subsequence (lps) in the string
        //          for a k-palindrome, the difference between the string length & lps should be utmost k

        int n = str.length();
        int[][] lps = new int[n][n];

        for (int i = 0; i < n; i++) {
            lps[i][i] = 1;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;

                char a = str.charAt(i);
                char b = str.charAt(j);

                if (a == b && len == 2) {
                    lps[i][j] = 2;
                } else if (a == b) {
                    lps[i][j] = 2 + lps[i + 1][j - 1];
                } else {
                    lps[i][j] = Math.max(lps[i + 1][j], lps[i][j - 1]);
                }
            }
        }

        int lpsLength = lps[0][n - 1];
        int deletionsRequired = n - lpsLength;

        return (k >= deletionsRequired);
    }

    public static boolean isKPalindromeUsingEditDistance(String str, int k) {
        // Does string becomes a palindrome by utmost k character deletions ?

        // Approach: Variant of Edit Distance
        //      Edit Distance of string to its reverse, with only deletions allowed
        //      Note: n, the result from edit distance, involves deletions from str1 & str2. Hence its half is to be considered

        int n = str.length();
        String reverse = StringAlgorithms.reverse(str);

        int[][] deleteDistance = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    deleteDistance[i][j] = j;
                } else if (j == 0) {
                    deleteDistance[i][j] = i;
                } else if (str.charAt(i - 1) == reverse.charAt(j - 1)) {
                    deleteDistance[i][j] = deleteDistance[i - 1][j - 1];
                } else {
                    deleteDistance[i][j] = 1 + Math.min(deleteDistance[i - 1][j], deleteDistance[i][j - 1]);
                }
            }
        }

        return (2 * k >= deleteDistance[n][n]);
    }

    public static int countPalindromicSubsequencesOfLengthK(String str, int k) {
        // Note: k <= 3; and only lower-case, alphabetic characters [a-z] present
        // Approach:
        //      k = 1 => count of characters
        //      k = 2 => count(char) * ((count(char) - 1) / 2
        //      k = 3 => count of all patterns of the form aba. Use left & right prefix arrays

        if (str == null || str.length() == 0 || k <= 0 || k > 3) {
            return 0;
        }

        int n = str.length();

        // leftPrefixes[c][i] => number of occurances of c in the substring str(0...i)
        int[][] leftPrefixes = new int[26][n];

        // rightPrefixes[c][i] => number of occurances of c in the substring str(i...n-1)
        int[][] rightPrefixes = new int[26][n];

        // First character in string
        leftPrefixes[str.charAt(0) - 'a'][0] = 1;

        // Last character in string
        rightPrefixes[str.charAt(n - 1) - 'a'][n - 1] = 1;

        // Populate left-prefixes
        for (int i = 1; i < n; i++) {
            for (int c = 0; c < 26; c++) {
                leftPrefixes[c][i] += leftPrefixes[c][i - 1];
            }

            leftPrefixes[str.charAt(i) - 'a'][i]++;
        }

        // Populate right-prefixes
        for (int i = n - 2; i >= 0; i--) {
            for (int c = 0; c < 26; c++) {
                rightPrefixes[c][i] += rightPrefixes[c][i + 1];
            }

            rightPrefixes[str.charAt(i) - 'a'][i]++;
        }

        int count = 0;

        if (k == 1) {
            for (int c = 0; c < 26; c++) {
                count += leftPrefixes[c][n - 1];
            }
        } else if (k == 2) {
            for (int c = 0; c < 26; c++) {
                int v = leftPrefixes[c][n - 1];
                count += (v * (v - 1) / 2);
            }
        } else {
            for (int i = 1; i < n - 1; i++) {
                for (int c = 0; c < 26; c++) {
                    count += (leftPrefixes[c][i - 1] * rightPrefixes[c][i + 1]);
                }
            }
        }

        return count;
    }

    public static int minimumDeletionsToMakeASortedSequence(int[] arr) {
        // Minimum deletions = Length of array - Length of LIS

        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;

        int[] lis = new int[n];
        Arrays.fill(lis, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }

        int lisLength = Arrays.stream(lis).max().getAsInt();
        return n - lisLength;
    }

    public static int minimumNumberOfSquaresWhoseSumIsEqualToN(int n) {
        if (n < 0) {
            return 0;
        }

        if (n <= 3) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        for (int i = 4; i <= n; i++) {
            // Max number of sum of squares will be 1^2 + 1^2 + ... n times = n
            dp[i] = i;

            for (int j = 1; j <= i; j++) {
                int squareValue = j * j;
                if (squareValue > i) {
                    break;
                }

                dp[i] = Math.min(dp[i], 1 + dp[i - squareValue]);
            }
        }

        return dp[n];
    }

    public static int minimumNumberOfSquaresWhoseSumIsEqualToNRecursive(int n) {
        if (n < 0) {
            return 0;
        }

        if (n <= 3) {
            return n;
        }

        // Max number of sum of squares will be 1^2 + 1^2 + ... n times = n
        int minSquares = n;
        for (int i = 1; i <= n; i++) {
            int squareValue = i * i;

            if (squareValue > n) {
                break;
            }

            // Number of squares including current iteration value
            int squares = 1 + minimumNumberOfSquaresWhoseSumIsEqualToNRecursive(n - squareValue);

            minSquares = Math.min(minSquares, squares);
        }

        return minSquares;
    }

    public static int minimumAdjustmentCostForEnsuringElementDifferenceInArrayIsUtmostTarget(int[] arr, int target) {
        if (arr == null || arr.length == 0 || target < 0) {
            return 0;
        }

        int n = arr.length;

        int[][] dp = new int[n][target + 1];
        for (int j = 0; j <= target; j++) {
            dp[0][j] = Math.abs(j - arr[0]);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = Math.max(j - target, 0); k <= Math.min(target, j + target); k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + Math.abs(arr[i] - j));
                }
            }
        }

        int cost = Integer.MAX_VALUE;
        for (int j = 0; j <= target; j++) {
            cost = Math.min(cost, dp[n - 1][j]);
        }

        return cost;
    }
}