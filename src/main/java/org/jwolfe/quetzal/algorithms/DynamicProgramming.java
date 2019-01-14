package org.jwolfe.quetzal.algorithms;

import org.jwolfe.quetzal.library.general.*;
import org.jwolfe.quetzal.library.matrix.Matrix;
import org.jwolfe.quetzal.library.utilities.PairFirstSorter;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DynamicProgramming {
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

    public static int maxChainLength(List<Pair<Integer, Integer>> pairs) {
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

    public static int countLargestDivisiblePairsSubset(int[] arr) {
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

    public static int minimumPalindromePartitioningRecursive(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        if(str.length() == 0 || StringAlgorithms.isPalidrome(str)) {
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
}
