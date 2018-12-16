package org.jwolfe.quetzal.algorithms;

import org.jwolfe.quetzal.library.general.Pair;
import org.jwolfe.quetzal.library.utilities.PairFirstSorter;
import org.jwolfe.quetzal.library.general.Triplet;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DynamicProgramming {
    public static int lis(int[] arr) {
        int n = arr.length;
        int[] lis = new int[n];
        Arrays.fill(lis, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]
                        && lis[i] < lis[j] + 1) {
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

    public static int editDistance(String str1, String str2) {
        if (str1 == null
                || str2 == null)
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
                    dp[i][j] = 1 + Utilities.min(
                            dp[i - 1][j],
                            dp[i][j - 1],
                            dp[i - 1][j - 1]
                    );
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    public static int editDistanceRecursive(String str1, String str2) {
        if (str1 == null
                || str2 == null)
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

        return 1 + Utilities.min(
                editDistanceRecursive(str1, str2, m - 1, n),
                editDistanceRecursive(str1, str2, m, n - 1),
                editDistanceRecursive(str1, str2, m - 1, n - 1)
        );
    }

    public static int longestPalindromicSubsequence(String str) {
        if (str == null)
            return 0;

        int n = str.length();
        int dp[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;

                if (str.charAt(i) == str.charAt(j)
                        && j - 1 == i) {
                    dp[i][j] = 2;
                } else if (str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(
                            dp[i][j - 1],
                            dp[i + 1][j]
                    );
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

        if (str.charAt(m) == str.charAt(n)
                && n - m == 1) {
            return 2;
        } else if (str.charAt(m) == str.charAt(n)) {
            return 2 + longestPalindromicSubsequenceRecursive(str, m + 1, n - 1);
        } else {
            return Math.max(
                    longestPalindromicSubsequenceRecursive(str, m, n - 1),
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

                if (str.charAt(i) == str.charAt(j)
                        && dp[i + 1][j - 1]) {
                    dp[i][j] = true;

                    if (k > maxLength) {
                        start = i;
                        maxLength = k;
                    }
                }
            }
        }

        System.out.println("Longest palindromic substring of " + str + " found at index: " + start + ", with a length of " + maxLength);
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
                    dp[i][w] = Math.max(
                            values[i - 1] + dp[i - 1][w - weights[i - 1]],
                            dp[i - 1][w]);
                }
            }
        }

        return dp[n][W];
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

        return Math.max(
                values[n - 1] + knapsack01Recursive(weights, values, W - weights[n - 1], n - 1),
                knapsack01Recursive(weights, values, W, n - 1));
    }

    public static int binomialCoefficient(int n, int k) {
        // C(n, k) = C(n-1, k-1) + C(n-1, k)
        // C(n, 0) = C(n, n) = 1

        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0
                        || i == j) {
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

        if (k == 0
                || n == k) {
            return 1;
        }

        return binomialCoefficientRecursive(n - 1, k - 1) + binomialCoefficientRecursive(n - 1, k);
    }

    public static int binomialCoefficientEfficient(int n, int k) {
        // C(n, k) = C(n, n-k)
        if (k > n - k) {
            k = n - k;
        }

        // C(n, k) = [n * (n-1) * (n-2) * .... ( n-k+1)[  /  [k * (k - 1) * (k-2) * .... * 1)
        int result = 1;
        for (int i = 0; i < k; i++) {
            result *= (n - i);
            result /= (i + 1);
        }

        return result;
    }

    public static int longestCommonSubsequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0
                        || j == 0) {
                    dp[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(
                            dp[i - 1][j],
                            dp[i][j - 1]
                    );
                }
            }
        }

        return dp[m][n];
    }

    public static int longestCommonSubsequenceRecursive(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        return longestCommonSubsequenceRecursive(str1, str2, m, n);
    }

    private static int longestCommonSubsequenceRecursive(String str1, String str2, int m, int n) {
        if (m == 0
                || n == 0) {
            return 0;
        }

        if (str1.charAt(m - 1) == str2.charAt(n - 1)) {
            return 1 + longestCommonSubsequenceRecursive(str1, str2, m - 1, n - 1);
        } else {
            return Math.max(
                    longestCommonSubsequenceRecursive(str1, str2, m, n - 1),
                    longestCommonSubsequenceRecursive(str1, str2, m - 1, n)
            );
        }
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
                    if (subResult != Integer.MAX_VALUE
                            && subResult + 1 < table[i]) {
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
                if (subResult != Integer.MAX_VALUE
                        && subResult + 1 < minCoins) {
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

                if (pair1.getFirst() > pair2.getSecond()
                        && maxChainLengths[i] < maxChainLengths[j] + 1) {
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
                if (arr[i] > arr[j]
                        && lis[j] + 1 > lis[i]) {
                    lis[i] = lis[j] + 1;
                }
            }
        }

        // Longest decreasing subsequence
        int[] lds = new int[n];
        Arrays.fill(lds, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j]
                        && lds[j] + 1 > lds[i]) {
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
                if (jBox.getFirst() > iBox.getFirst()
                        && jBox.getSecond() > iBox.getSecond()) {
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
        if(eggs == 0 || floors == 0) {
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
                for (int k = 1; k <=j; k++) {
                    int netTrials = 1 + Math.max(dp[i-1][k-1], dp[i][j-k]);
                    dp[i][j] = Math.min(dp[i][j], netTrials);
                }
            }
        }

        return dp[eggs][floors];
    }

    public static int eggDropRecursive(int eggs, int floors) {
        // Find minimum number of trials to find the lowest floor from which an egg dropped down will break

        if(eggs == 0 || floors == 0) {
            return 0;
        }

        // If there is only one floor, only one trial is required
        if(floors == 1) {
            return 1;
        }

        // If there is only one egg, minimum number of trials is equal to the number of floors.
        if(eggs == 1) {
            return floors;
        }

        int minTrials = Integer.MAX_VALUE;
        for (int i = 1; i <= floors; i++) {
            // Two possibilities -> egg broke & egg didnt break
            //      Egg broke : try in lower floors
            //      Egg didnt break : try in upper floors
            // Find the max of either of these options

            int addlTrialsIfBroke = eggDropRecursive(eggs - 1, i - 1);
            int addlTrialsIfNotBroke = eggDropRecursive(eggs, floors - i);

            int netTrials = 1 + Math.max(addlTrialsIfBroke, addlTrialsIfNotBroke);
            minTrials = Math.min(minTrials, netTrials);
        }

        return minTrials;
    }
}
