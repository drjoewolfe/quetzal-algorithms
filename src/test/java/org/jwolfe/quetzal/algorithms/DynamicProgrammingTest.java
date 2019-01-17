package org.jwolfe.quetzal.algorithms;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.general.CarryItem;
import org.jwolfe.quetzal.library.general.Knapsack;
import org.jwolfe.quetzal.library.general.Pair;
import org.jwolfe.quetzal.library.general.Triplet;
import org.jwolfe.quetzal.library.utilities.Utilities;
import org.jwolfe.quetzal.test.QuetzalAssertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DynamicProgrammingTest {
    @Test
    void longestIncreasingSubsequence() {
        int[] arr;
        int lis;

        arr = new int[]{10, 22, 9, 33, 21, 50, 41, 60, 80};
        lis = DynamicProgramming.longestIncreasingSubsequence(arr);
        assertEquals(6, lis);
        System.out.println("Length of longest increasing subsequence is " + lis);

        System.out.println();
        arr = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        lis = DynamicProgramming.longestIncreasingSubsequence(arr);
        assertEquals(4, lis);
        System.out.println("Length of longest increasing subsequence is " + lis);

        System.out.println();
        arr = new int[]{3, 10, 2, 1, 20};
        lis = DynamicProgramming.longestIncreasingSubsequence(arr);
        assertEquals(3, lis);
        System.out.println("Length of longest increasing subsequence is " + lis);

        System.out.println();
        arr = new int[]{3, 2};
        lis = DynamicProgramming.longestIncreasingSubsequence(arr);
        assertEquals(1, lis);
        System.out.println("Length of longest increasing subsequence is " + lis);

        System.out.println();
        arr = new int[]{50, 3, 10, 7, 40, 80};
        lis = DynamicProgramming.longestIncreasingSubsequence(arr);
        assertEquals(4, lis);
        System.out.println("Length of longest increasing subsequence is " + lis);
    }

    @Test
    void getLongestIncreasingSubsequence() {
        int[] arr;
        List<Integer> lisList;
        List<Integer> expectedLisList;

        arr = new int[]{10, 22, 9, 33, 21, 50, 41, 60, 80};
        expectedLisList = Utilities.constructList(10, 22, 33, 50, 60, 80);
        lisList = DynamicProgramming.getLongestIncreasingSubsequence(arr);
        QuetzalAssertions.assertListEquals(expectedLisList, lisList);

        arr = new int[]{3, 2, 6, 4, 5, 1};
        expectedLisList = Utilities.constructList(2, 4, 5);
        lisList = DynamicProgramming.getLongestIncreasingSubsequence(arr);
        QuetzalAssertions.assertListEquals(expectedLisList, lisList);
    }

    @Test
    void longestIncreasingSubsequenceRecursive() {
        int[] arr;
        int lis;

        arr = new int[]{10, 22, 9, 33, 21, 50, 41, 60, 80};
        lis = DynamicProgramming.longestIncreasingSubsequenceRecursive(arr);
        assertEquals(6, lis);

        System.out.println();
        arr = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        lis = DynamicProgramming.longestIncreasingSubsequenceRecursive(arr);
        assertEquals(4, lis);

        arr = new int[]{3, 10, 2, 1, 20};
        lis = DynamicProgramming.longestIncreasingSubsequenceRecursive(arr);
        assertEquals(3, lis);

        arr = new int[]{3, 2};
        lis = DynamicProgramming.longestIncreasingSubsequenceRecursive(arr);
        assertEquals(1, lis);

        arr = new int[]{50, 3, 10, 7, 40, 80};
        lis = DynamicProgramming.longestIncreasingSubsequenceRecursive(arr);
        assertEquals(4, lis);
    }

    @Test
    void editDistance() {
        String str1, str2;
        int distance;

        str1 = "geek";
        str2 = "gesek";
        distance = DynamicProgramming.editDistance(str1, str2);
        System.out.println("Edit distance between " + str1 + " & " + str2 + " is " + distance);

        str1 = "cat";
        str2 = "cut";
        distance = DynamicProgramming.editDistance(str1, str2);
        System.out.println("Edit distance between " + str1 + " & " + str2 + " is " + distance);

        str1 = "sunday";
        str2 = "saturday";
        distance = DynamicProgramming.editDistance(str1, str2);
        System.out.println("Edit distance between " + str1 + " & " + str2 + " is " + distance);
    }

    @Test
    void editDistanceRecursive() {
        String str1, str2;
        int distance;

        str1 = "geek";
        str2 = "gesek";
        distance = DynamicProgramming.editDistanceRecursive(str1, str2);
        System.out.println("Edit distance between " + str1 + " & " + str2 + " is " + distance);

        str1 = "cat";
        str2 = "cut";
        distance = DynamicProgramming.editDistanceRecursive(str1, str2);
        System.out.println("Edit distance between " + str1 + " & " + str2 + " is " + distance);

        str1 = "sunday";
        str2 = "saturday";
        distance = DynamicProgramming.editDistanceRecursive(str1, str2);
        System.out.println("Edit distance between " + str1 + " & " + str2 + " is " + distance);
    }

    @Test
    void longestPalindromicSubsequence() {
        System.out.println();
        String str = "GEEKSFORGEEKS";
        int lps = DynamicProgramming.longestPalindromicSubsequence(str);
        System.out.println("Longest palindromic subsequence of " + str + "  is of length " + lps);

        System.out.println();
        str = "BBABCBCAB";
        lps = DynamicProgramming.longestPalindromicSubsequence(str);
        System.out.println("Longest palindromic subsequence of " + str + "  is of length " + lps);
    }

    @Test
    void longestPalindromicSubsequenceRecursive() {
        System.out.println();
        String str = "GEEKSFORGEEKS";
        int lps = DynamicProgramming.longestPalindromicSubsequenceRecursive(str);
        System.out.println("Longest palindromic subsequence of " + str + "  is of length " + lps);

        System.out.println();
        str = "BBABCBCAB";
        lps = DynamicProgramming.longestPalindromicSubsequenceRecursive(str);
        System.out.println("Longest palindromic subsequence of " + str + "  is of length " + lps);
    }

    @Test
    void longestPalindromicSubstring() {
        System.out.println();
        String str = "forgeeksskeegfor";
        int lps = DynamicProgramming.longestPalindromicSubstring(str);
        System.out.println("Longest palindromic substring of " + str + "  is of length " + lps);
    }

    @Test
    void knapsack01() {
        int val[] = {60, 100, 120};
        int wt[] = {10, 20, 30};
        int W = 50;
        int res = DynamicProgramming.knapsack01(wt, val, W);
        System.out.println(res);
    }

    @Test
    void knapsack01WithItems() {
        List<CarryItem> items;
        int maxWeight;
        Knapsack knapsack;
        List<CarryItem> expectedKnapsackItems;

        items = Utilities.constructList(new CarryItem(10, 60), new CarryItem(20, 100), new CarryItem(30, 120));
        maxWeight = 50;
        expectedKnapsackItems = Utilities.constructList(new CarryItem(20, 100), new CarryItem(30, 120));
        knapsack = DynamicProgramming.knapsack01(items, maxWeight);
        assertNotNull(knapsack);
        assertEquals(220, knapsack.getValue());
        QuetzalAssertions.assertListEquals(expectedKnapsackItems, knapsack.getItems());

        items = Utilities.constructList(new CarryItem(20, 40), new CarryItem(10, 100), new CarryItem(40, 50), new CarryItem(30, 60));
        maxWeight = 60;
        expectedKnapsackItems = Utilities.constructList(new CarryItem(20, 40), new CarryItem(10, 100), new CarryItem(30, 60));
        knapsack = DynamicProgramming.knapsack01(items, maxWeight);
        assertNotNull(knapsack);
        assertEquals(200, knapsack.getValue());
        QuetzalAssertions.assertListEquals(expectedKnapsackItems, knapsack.getItems());

        items = Utilities.constructList(new CarryItem(1, 1), new CarryItem(3, 4), new CarryItem(4, 5), new CarryItem(5, 7));
        maxWeight = 7;
        expectedKnapsackItems = Utilities.constructList(new CarryItem(3, 4), new CarryItem(4, 5));
        knapsack = DynamicProgramming.knapsack01(items, maxWeight);
        assertNotNull(knapsack);
        assertEquals(9, knapsack.getValue());
        QuetzalAssertions.assertListEquals(expectedKnapsackItems, knapsack.getItems());
    }

    @Test
    void knapsack01Unbounded() {
        int[] weights;
        int[] values;
        int W;
        int knapsackValue;

        weights = new int[]{1, 50};
        values = new int[]{1, 30};
        W = 100;
        knapsackValue = DynamicProgramming.knapsack01Unbounded(weights, values, W);
        assertEquals(100, knapsackValue);

        weights = new int[]{1, 3, 4, 5};
        values = new int[]{10, 40, 50, 70};
        W = 8;
        knapsackValue = DynamicProgramming.knapsack01Unbounded(weights, values, W);
        assertEquals(110, knapsackValue);

        weights = new int[]{5, 10, 15};
        values = new int[]{10, 30, 20};
        W = 100;
        knapsackValue = DynamicProgramming.knapsack01Unbounded(weights, values, W);
        assertEquals(300, knapsackValue);
    }

    @Test
    void knapsack01Recursive() {
        int val[] = {60, 100, 120};
        int wt[] = {10, 20, 30};
        int W = 50;
        int res = DynamicProgramming.knapsack01Recursive(wt, val, W);
        System.out.println(res);
    }

    @Test
    void binomialCoefficient() {
        int n, k;
        int c;

        n = 5;
        k = 2;
        c = DynamicProgramming.binomialCoefficient(n, k);
        assertEquals(10, c);
        System.out.println("Binomail coefficient is " + c);
    }

    @Test
    void binomialCoefficientRecursive() {
        int n, k;
        int c;

        n = 5;
        k = 2;
        c = DynamicProgramming.binomialCoefficientRecursive(n, k);
        assertEquals(10, c);
        System.out.println("Binomail coefficient is " + c);
    }

    @Test
    void binomialCoefficientEfficient() {
        int n, k;
        int c;

        n = 5;
        k = 2;
        c = DynamicProgramming.binomialCoefficientEfficient(n, k);
        assertEquals(10, c);
        System.out.println("Binomail coefficient is " + c);
    }

    @Test
    void longestCommonSubsequence() {
        String str1;
        String str2;
        int lcs;

        str1 = "ABCDGH";
        str2 = "AEDFHR";
        lcs = DynamicProgramming.longestCommonSubsequence(str1, str2);
        assertEquals(3, lcs);
        System.out.println("Length of LCS is " + lcs);

        str1 = "AGGTAB";
        str2 = "GXTXAYB";
        lcs = DynamicProgramming.longestCommonSubsequence(str1, str2);
        assertEquals(4, lcs);
        System.out.println("Length of LCS is " + lcs);
    }

    @Test
    void getLongestCommonSubsequence() {
        String str1;
        String str2;
        String lcsString;

        str1 = "ABCDGH";
        str2 = "AEDFHR";
        lcsString = DynamicProgramming.getLongestCommonSubsequence(str1, str2);
        assertEquals("ADH", lcsString);

        str1 = "AGGTAB";
        str2 = "GXTXAYB";
        lcsString = DynamicProgramming.getLongestCommonSubsequence(str1, str2);
        assertEquals("GTAB", lcsString);

        str1 = "MZJAWXU";
        str2 = "XMJYAUZ";
        lcsString = DynamicProgramming.getLongestCommonSubsequence(str1, str2);
        assertEquals("MJAU", lcsString);
    }

    @Test
    void longestCommonSubsequenceRecursive() {
        String str1;
        String str2;
        int lcs;

        str1 = "ABCDGH";
        str2 = "AEDFHR";
        lcs = DynamicProgramming.longestCommonSubsequenceRecursive(str1, str2);
        assertEquals(3, lcs);
        System.out.println("Length of LCS is " + lcs);

        str1 = "AGGTAB";
        str2 = "GXTXAYB";
        lcs = DynamicProgramming.longestCommonSubsequenceRecursive(str1, str2);
        assertEquals(4, lcs);
        System.out.println("Length of LCS is " + lcs);
    }

    @Test
    void longestCommonSubsequenceSpaceOptimized() {
        String str1;
        String str2;
        int lcs;

        str1 = "ABCDGH";
        str2 = "AEDFHR";
        lcs = DynamicProgramming.longestCommonSubsequenceSpaceOptimized(str1, str2);
        assertEquals(3, lcs);
        System.out.println("Length of LCS is " + lcs);

        str1 = "AGGTAB";
        str2 = "GXTXAYB";
        lcs = DynamicProgramming.longestCommonSubsequenceSpaceOptimized(str1, str2);
        assertEquals(4, lcs);
        System.out.println("Length of LCS is " + lcs);
    }

    @Test
    void maxWaysForCoinChange() {
        int[] coins;
        int value;
        int maxWays;

        coins = new int[]{1, 2, 3};
        value = 4;
        maxWays = DynamicProgramming.maxWaysForCoinChange(coins, value);
        assertEquals(4, maxWays);

        coins = new int[]{2, 5, 3, 6};
        value = 10;
        maxWays = DynamicProgramming.maxWaysForCoinChange(coins, value);
        assertEquals(5, maxWays);
    }

    @Test
    void maxWaysForCoinChangeRecursive() {
        int[] coins;
        int value;
        int maxWays;

        coins = new int[]{1, 2, 3};
        value = 4;
        maxWays = DynamicProgramming.maxWaysForCoinChangeRecursive(coins, value);
        assertEquals(4, maxWays);

        coins = new int[]{2, 5, 3, 6};
        value = 10;
        maxWays = DynamicProgramming.maxWaysForCoinChangeRecursive(coins, value);
        assertEquals(5, maxWays);
    }

    @Test
    void minCoins() {
        int coins[];
        int V;
        int minCoins;

        coins = Utilities.constructArray(9, 6, 5, 1);
        V = 11;
        minCoins = DynamicProgramming.minCoins(coins, V);
        assertEquals(2, minCoins);
        System.out.println("Minimum coins required is " + minCoins);

        coins = Utilities.constructArray(25, 10, 5);
        V = 30;
        minCoins = DynamicProgramming.minCoins(coins, V);
        assertEquals(2, minCoins);
        System.out.println("Minimum coins required is " + minCoins);
    }

    @Test
    void minCoinsRecursive() {
        int coins[];
        int V;
        int minCoins;

        coins = Utilities.constructArray(9, 6, 5, 1);
        V = 11;
        minCoins = DynamicProgramming.minCoinsRecursive(coins, V);
        assertEquals(2, minCoins);
        System.out.println("Minimum coins required is " + minCoins);

        coins = Utilities.constructArray(25, 10, 5);
        V = 30;
        minCoins = DynamicProgramming.minCoinsRecursive(coins, V);
        assertEquals(2, minCoins);
        System.out.println("Minimum coins required is " + minCoins);
    }

    @Test
    void maxChainLength() {
        List<Pair<Integer, Integer>> pairs;
        int maxChain;

        pairs = new ArrayList<>();
        pairs.add(new Pair<>(5, 24));
        pairs.add(new Pair<>(15, 25));
        pairs.add(new Pair<>(27, 40));
        pairs.add(new Pair<>(50, 60));
        maxChain = DynamicProgramming.maxChainLength(pairs);
        assertEquals(3, maxChain);
        System.out.println("Length of maximum size chain is " + maxChain);

        pairs = new ArrayList<>();
        pairs.add(new Pair<>(5, 24));
        pairs.add(new Pair<>(39, 60));
        pairs.add(new Pair<>(15, 28));
        pairs.add(new Pair<>(27, 40));
        pairs.add(new Pair<>(50, 90));
        maxChain = DynamicProgramming.maxChainLength(pairs);
        assertEquals(3, maxChain);
        System.out.println("Length of maximum size chain is " + maxChain);
    }

    @Test
    void countBinaryStringsWithoutConsecutiveOnesForLengthN() {
        int n;
        int count;

        n = 2;
        count = DynamicProgramming.countBinaryStringsWithoutConsecutiveOnesForLengthN(n);
        System.out.println(count);
        assertEquals(3, count);

        n = 3;
        count = DynamicProgramming.countBinaryStringsWithoutConsecutiveOnesForLengthN(n);
        System.out.println(count);
        assertEquals(5, count);
    }

    @Test
    void longestBitonicSubsequence() {
        int[] arr;
        int lbsLength;

        arr = Utilities.constructArray(0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15);
        lbsLength = DynamicProgramming.longestBitonicSubsequence(arr);
        System.out.println("LBS: " + lbsLength);
        assertEquals(7, lbsLength);
    }

    @Test
    void paintFence() {
        int numPosts;
        int numColors;
        int ways;

        numPosts = 2;
        numColors = 4;
        ways = DynamicProgramming.paintFence(numPosts, numColors);
        System.out.println("Posts: " + numPosts + ", Colors: " + numColors + "  ->  Ways:" + ways);
        assertEquals(16, ways);

        numPosts = 3;
        numColors = 2;
        ways = DynamicProgramming.paintFence(numPosts, numColors);
        System.out.println("Posts: " + numPosts + ", Colors: " + numColors + "  ->  Ways:" + ways);
        assertEquals(6, ways);
    }

    @Test
    void maxStackHeight() {
        List<Triplet<Integer, Integer, Integer>> boxes;
        int height;

        boxes = new ArrayList<>();
        boxes.add(new Triplet(4, 6, 7));
        boxes.add(new Triplet(1, 2, 3));
        boxes.add(new Triplet(4, 5, 6));
        boxes.add(new Triplet(10, 12, 32));

        height = DynamicProgramming.maxStackHeight(boxes);
        System.out.println("Height: " + height);
        assertEquals(60, height);
    }

    @Test
    void countFriendPairings() {
        int n;

        n = 3;
        assertEquals(4, DynamicProgramming.countFriendPairings(n));

        n = 4;
        assertEquals(10, DynamicProgramming.countFriendPairings(n));
    }

    @Test
    void eggDrop() {
        int minTrials;

        minTrials = DynamicProgramming.eggDrop(0, 78);
        assertEquals(0, minTrials);

        minTrials = DynamicProgramming.eggDrop(20, 0);
        assertEquals(0, minTrials);

        minTrials = DynamicProgramming.eggDrop(1, 78);
        assertEquals(78, minTrials);

        minTrials = DynamicProgramming.eggDrop(2, 36);
        assertEquals(8, minTrials);
    }

    @Test
    void eggDropRecursive() {
        int minTrials;

        minTrials = DynamicProgramming.eggDropRecursive(0, 78);
        assertEquals(0, minTrials);

        minTrials = DynamicProgramming.eggDropRecursive(20, 0);
        assertEquals(0, minTrials);

        minTrials = DynamicProgramming.eggDropRecursive(1, 78);
        assertEquals(78, minTrials);

        minTrials = DynamicProgramming.eggDropRecursive(2, 5);
        assertEquals(3, minTrials);
    }

    @Test
    void longestRepeatingSubsequence() {
        int lrs;

        lrs = DynamicProgramming.longestRepeatingSubsequence("ATACTCGGA");
        assertEquals(4, lrs);

        lrs = DynamicProgramming.longestRepeatingSubsequence("AABEBCDD");
        assertEquals(3, lrs);
    }

    @Test
    void longestRepeatingSubsequenceMemoized() {
        int lrs;

        lrs = DynamicProgramming.longestRepeatingSubsequenceMemoized("ATACTCGGA");
        assertEquals(4, lrs);

        lrs = DynamicProgramming.longestRepeatingSubsequenceMemoized("AABEBCDD");
        assertEquals(3, lrs);
    }

    @Test
    void longestRepeatingSubsequenceRecursive() {
        int lrs;

        lrs = DynamicProgramming.longestRepeatingSubsequenceRecursive("ATACTCGGA");
        assertEquals(4, lrs);

        lrs = DynamicProgramming.longestRepeatingSubsequenceRecursive("AABEBCDD");
        assertEquals(3, lrs);
    }

    @Test
    void getNthUglyNumber() {
        assertEquals(1, DynamicProgramming.getNthUglyNumber(1));
        assertEquals(2, DynamicProgramming.getNthUglyNumber(2));
        assertEquals(3, DynamicProgramming.getNthUglyNumber(3));
        assertEquals(4, DynamicProgramming.getNthUglyNumber(4));
        assertEquals(5, DynamicProgramming.getNthUglyNumber(5));
        assertEquals(6, DynamicProgramming.getNthUglyNumber(6));
        assertEquals(8, DynamicProgramming.getNthUglyNumber(7));
        assertEquals(9, DynamicProgramming.getNthUglyNumber(8));
        assertEquals(10, DynamicProgramming.getNthUglyNumber(9));
        assertEquals(12, DynamicProgramming.getNthUglyNumber(10));
        assertEquals(15, DynamicProgramming.getNthUglyNumber(11));
        assertEquals(24, DynamicProgramming.getNthUglyNumber(15));
        assertEquals(5832, DynamicProgramming.getNthUglyNumber(150));
    }

    @Test
    void getUglyNumbers() {
        int[] uglyNumbers;
        int[] expectedUglyNumbers;

        expectedUglyNumbers = Utilities.constructArray(1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15);
        uglyNumbers = DynamicProgramming.getUglyNumbers(11);
        assertArrayEquals(expectedUglyNumbers, uglyNumbers);
    }

    @Test
    void subSetSum() {
        int[] set;

        set = Utilities.constructArray(1, 2, 3);
        assertTrue(DynamicProgramming.subSetSum(set, 5));

        set = Utilities.constructArray(7, 3, 2, 5, 8);
        assertTrue(DynamicProgramming.subSetSum(set, 14));

        set = Utilities.constructArray(3, 34, 4, 12, 5, 2);
        assertTrue(DynamicProgramming.subSetSum(set, 9));

        set = Utilities.constructArray(3, 34, 4, 12, 5, 2);
        assertFalse(DynamicProgramming.subSetSum(set, 35));
    }

    @Test
    void subSetSumRecursive() {
        int[] set;

        set = Utilities.constructArray(1, 2, 3);
        assertTrue(DynamicProgramming.subSetSumRecursive(set, 5));

        set = Utilities.constructArray(7, 3, 2, 5, 8);
        assertTrue(DynamicProgramming.subSetSumRecursive(set, 14));

        set = Utilities.constructArray(3, 34, 4, 12, 5, 2);
        assertTrue(DynamicProgramming.subSetSumRecursive(set, 9));

        set = Utilities.constructArray(3, 34, 4, 12, 5, 2);
        assertFalse(DynamicProgramming.subSetSumRecursive(set, 35));
    }

    @Test
    void maxLengthSnakeSequence() {
        int[][] grid1 = {
                {9, 6, 5, 2},
                {8, 7, 6, 5},
                {7, 3, 1, 6},
                {1, 1, 1, 7}};
        assertEquals(7, DynamicProgramming.maxLengthSnakeSequence(grid1));

        int[][] grid2 = {
                {7, 5, 2, 3, 1},
                {3, 4, 1, 4, 4},
                {1, 5, 6, 7, 8},
                {3, 4, 5, 8, 9},
                {3, 2, 2, 7, 6}};
        assertEquals(8, DynamicProgramming.maxLengthSnakeSequence(grid2));
    }

    @Test
    void maxRevenueFromRodCutting() {
        int[] prices;

        prices = Utilities.constructArray(1, 5, 8, 9, 10, 17, 17, 20);
        assertEquals(22, DynamicProgramming.maxRevenueFromRodCutting(prices, 8));

        prices = Utilities.constructArray(3, 5, 8, 9, 10, 17, 17, 20);
        assertEquals(24, DynamicProgramming.maxRevenueFromRodCutting(prices, 8));
    }

    @Test
    void maxRevenueFromRodCuttingRecursive() {
        int[] prices;

        prices = Utilities.constructArray(1, 5, 8, 9, 10, 17, 17, 20);
        assertEquals(22, DynamicProgramming.maxRevenueFromRodCuttingRecursive(prices, 8));

        prices = Utilities.constructArray(3, 5, 8, 9, 10, 17, 17, 20);
        assertEquals(24, DynamicProgramming.maxRevenueFromRodCuttingRecursive(prices, 8));
    }

    @Test
    void wordBreak() {
        String str;
        List<String> dictionary;

        dictionary = Utilities.constructList("i", "and", "want", "need", "wish", "straw", "berry", "strawberry", "uni", "corn", "unicorn", "models", "fruit", "salad", "fruitsalad");

        str = "i";
        assertTrue(DynamicProgramming.wordBreak(str, dictionary));

        str = "want";
        assertTrue(DynamicProgramming.wordBreak(str, dictionary));

        str = "we";
        assertFalse(DynamicProgramming.wordBreak(str, dictionary));

        str = "straw";
        assertTrue(DynamicProgramming.wordBreak(str, dictionary));

        str = "strawberry";
        assertTrue(DynamicProgramming.wordBreak(str, dictionary));

        str = "strawmerry";
        assertFalse(DynamicProgramming.wordBreak(str, dictionary));

        str = "fruitstraw";
        assertTrue(DynamicProgramming.wordBreak(str, dictionary));

        str = "strawfruit";
        assertTrue(DynamicProgramming.wordBreak(str, dictionary));

        str = "saladmodel";
        assertFalse(DynamicProgramming.wordBreak(str, dictionary));

        str = "iwant";
        assertTrue(DynamicProgramming.wordBreak(str, dictionary));

        str = "iwish";
        assertTrue(DynamicProgramming.wordBreak(str, dictionary));

        str = "iwantandneed";
        assertTrue(DynamicProgramming.wordBreak(str, dictionary));

        str = "iwantunicorn";
        assertTrue(DynamicProgramming.wordBreak(str, dictionary));

        str = "iwantunicornmodels";
        assertTrue(DynamicProgramming.wordBreak(str, dictionary));

        str = "iwantfruitsaladandstrawberry";
        assertTrue(DynamicProgramming.wordBreak(str, dictionary));

        str = "iwantandneedandwishunicornmodelsandfruitsaladandstrawberry";
        assertTrue(DynamicProgramming.wordBreak(str, dictionary));

        str = "iwantandneedandwishunicornmodelsandfruitsaladandstrawberryandpista";
        assertFalse(DynamicProgramming.wordBreak(str, dictionary));

        str = "iwantandneedandwishmerryunicornmodelsandfruitsaladandstrawberry";
        assertFalse(DynamicProgramming.wordBreak(str, dictionary));
    }

    @Test
    void wordBreakDP() {
        String str;
        List<String> dictionary;

        dictionary = Utilities.constructList("i", "and", "want", "need", "wish", "straw", "berry", "strawberry", "uni", "corn", "unicorn", "models", "fruit", "salad", "fruitsalad");

        str = "i";
        assertTrue(DynamicProgramming.wordBreakDP(str, dictionary));

        str = "want";
        assertTrue(DynamicProgramming.wordBreakDP(str, dictionary));

        str = "we";
        assertFalse(DynamicProgramming.wordBreakDP(str, dictionary));

        str = "straw";
        assertTrue(DynamicProgramming.wordBreakDP(str, dictionary));

        str = "strawberry";
        assertTrue(DynamicProgramming.wordBreakDP(str, dictionary));

        str = "strawmerry";
        assertFalse(DynamicProgramming.wordBreakDP(str, dictionary));

        str = "fruitstraw";
        assertTrue(DynamicProgramming.wordBreakDP(str, dictionary));

        str = "strawfruit";
        assertTrue(DynamicProgramming.wordBreakDP(str, dictionary));

        str = "saladmodel";
        assertFalse(DynamicProgramming.wordBreakDP(str, dictionary));

        str = "iwant";
        assertTrue(DynamicProgramming.wordBreakDP(str, dictionary));

        str = "iwish";
        assertTrue(DynamicProgramming.wordBreakDP(str, dictionary));

        str = "iwantandneed";
        assertTrue(DynamicProgramming.wordBreakDP(str, dictionary));

        str = "iwantunicorn";
        assertTrue(DynamicProgramming.wordBreakDP(str, dictionary));

        str = "iwantunicornmodels";
        assertTrue(DynamicProgramming.wordBreakDP(str, dictionary));

        str = "iwantfruitsaladandstrawberry";
        assertTrue(DynamicProgramming.wordBreakDP(str, dictionary));

        str = "iwantandneedandwishunicornmodelsandfruitsaladandstrawberry";
        assertTrue(DynamicProgramming.wordBreakDP(str, dictionary));

        str = "iwantandneedandwishunicornmodelsandfruitsaladandstrawberryandpista";
        assertFalse(DynamicProgramming.wordBreakDP(str, dictionary));

        str = "iwantandneedandwishmerryunicornmodelsandfruitsaladandstrawberry";
        assertFalse(DynamicProgramming.wordBreakDP(str, dictionary));
    }

    @Test
    void wordBreakRecursive() {
        String str;
        List<String> dictionary;

        dictionary = Utilities.constructList("i", "and", "want", "need", "wish", "straw", "berry", "strawberry", "uni", "corn", "unicorn", "models", "fruit", "salad", "fruitsalad");

        str = "i";
        assertTrue(DynamicProgramming.wordBreakRecursive(str, dictionary));

        str = "want";
        assertTrue(DynamicProgramming.wordBreakRecursive(str, dictionary));

        str = "we";
        assertFalse(DynamicProgramming.wordBreakRecursive(str, dictionary));

        str = "straw";
        assertTrue(DynamicProgramming.wordBreakRecursive(str, dictionary));

        str = "strawberry";
        assertTrue(DynamicProgramming.wordBreakRecursive(str, dictionary));

        str = "strawmerry";
        assertFalse(DynamicProgramming.wordBreakRecursive(str, dictionary));

        str = "fruitstraw";
        assertTrue(DynamicProgramming.wordBreakRecursive(str, dictionary));

        str = "strawfruit";
        assertTrue(DynamicProgramming.wordBreakRecursive(str, dictionary));

        str = "saladmodel";
        assertFalse(DynamicProgramming.wordBreakRecursive(str, dictionary));

        str = "iwant";
        assertTrue(DynamicProgramming.wordBreakRecursive(str, dictionary));

        str = "iwish";
        assertTrue(DynamicProgramming.wordBreakRecursive(str, dictionary));

        str = "iwantandneed";
        assertTrue(DynamicProgramming.wordBreakRecursive(str, dictionary));

        str = "iwantunicorn";
        assertTrue(DynamicProgramming.wordBreakRecursive(str, dictionary));

        str = "iwantunicornmodels";
        assertTrue(DynamicProgramming.wordBreakRecursive(str, dictionary));

        str = "iwantfruitsaladandstrawberry";
        assertTrue(DynamicProgramming.wordBreakRecursive(str, dictionary));

        str = "iwantandneedandwishunicornmodelsandfruitsaladandstrawberry";
        assertTrue(DynamicProgramming.wordBreakRecursive(str, dictionary));

        str = "iwantandneedandwishunicornmodelsandfruitsaladandstrawberryandpista";
        assertFalse(DynamicProgramming.wordBreakRecursive(str, dictionary));

        str = "iwantandneedandwishmerryunicornmodelsandfruitsaladandstrawberry";
        assertFalse(DynamicProgramming.wordBreakRecursive(str, dictionary));
    }

    @Test
    void getWordBreaks() {
        String str;
        List<String> dictionary;
        List<String> wordBreaks;
        List<String> expectedWordBreaks;

        dictionary = Utilities.constructList("i", "and", "want", "need", "wish", "straw", "berry", "strawberry", "uni", "corn", "unicorn", "models", "fruit", "salad", "fruitsalad");

        str = "i";
        expectedWordBreaks = Utilities.constructList("i");
        wordBreaks = DynamicProgramming.getWordBreaks(str, dictionary);
        QuetzalAssertions.assertListEquals(expectedWordBreaks, wordBreaks);

        str = "strawberry";
        expectedWordBreaks = Utilities.constructList("straw berry", "strawberry");
        wordBreaks = DynamicProgramming.getWordBreaks(str, dictionary);
        QuetzalAssertions.assertListEquals(expectedWordBreaks, wordBreaks);

        str = "iwantunicornmodels";
        expectedWordBreaks = Utilities.constructList("i want uni corn models", "i want unicorn models");
        wordBreaks = DynamicProgramming.getWordBreaks(str, dictionary);
        QuetzalAssertions.assertListEquals(expectedWordBreaks, wordBreaks);

        str = "iwantandneedandwishmerryunicornmodelsandfruitsaladandstrawberry";
        wordBreaks = DynamicProgramming.getWordBreaks(str, dictionary);
        assertEquals(0, wordBreaks.size());
    }

    @Test
    void lengthOfLargestDivisiblePairsSubset() {
        int[] arr;

        arr = Utilities.constructArray(10, 5, 3, 15, 20);
        assertEquals(3, DynamicProgramming.lengthOfLargestDivisiblePairsSubset(arr));

        arr = Utilities.constructArray(18, 1, 3, 6, 13, 17);
        assertEquals(4, DynamicProgramming.lengthOfLargestDivisiblePairsSubset(arr));

        arr = Utilities.constructArray(5, 7, 9);
        assertEquals(1, DynamicProgramming.lengthOfLargestDivisiblePairsSubset(arr));
    }

    @Test
    void getLargestDivisiblePairsSubset() {
        int[] arr;
        Set<Integer> largestDivisiblePairsSubset;
        Set<Integer> expectedSubset;

        arr = Utilities.constructArray(10, 5, 3, 15, 20);
        expectedSubset = Utilities.constructSet(10, 5, 20);
        largestDivisiblePairsSubset = DynamicProgramming.getLargestDivisiblePairsSubset(arr);
        QuetzalAssertions.assertSetEquals(expectedSubset, largestDivisiblePairsSubset);

        arr = Utilities.constructArray(18, 1, 3, 6, 13, 17);
        expectedSubset = Utilities.constructSet(18, 1, 3, 6);
        largestDivisiblePairsSubset = DynamicProgramming.getLargestDivisiblePairsSubset(arr);
        QuetzalAssertions.assertSetEquals(expectedSubset, largestDivisiblePairsSubset);

        arr = Utilities.constructArray(5, 7, 9);
        largestDivisiblePairsSubset = DynamicProgramming.getLargestDivisiblePairsSubset(arr);
        assertEquals(1, largestDivisiblePairsSubset.size());
    }

    @Test
    void minimumPalindromePartitioning() {
        assertEquals(9, DynamicProgramming.minimumPalindromePartitioning("abcdefghij"));
        assertEquals(3, DynamicProgramming.minimumPalindromePartitioning("ababbbabbababa"));
        assertEquals(1, DynamicProgramming.minimumPalindromePartitioning("add"));
        assertEquals(3, DynamicProgramming.minimumPalindromePartitioning("doopy"));
    }

    @Test
    void minimumPalindromePartitioningA1() {
        assertEquals(9, DynamicProgramming.minimumPalindromePartitioningA1("abcdefghij"));
        assertEquals(3, DynamicProgramming.minimumPalindromePartitioningA1("ababbbabbababa"));
        assertEquals(1, DynamicProgramming.minimumPalindromePartitioningA1("add"));
        assertEquals(3, DynamicProgramming.minimumPalindromePartitioningA1("doopy"));
    }

    @Test
    void minimumPalindromePartitioningRecursive() {
        assertEquals(9, DynamicProgramming.minimumPalindromePartitioningRecursive("abcdefghij"));
        assertEquals(3, DynamicProgramming.minimumPalindromePartitioningRecursive("ababbbabbababa"));
        assertEquals(1, DynamicProgramming.minimumPalindromePartitioningRecursive("add"));
        assertEquals(3, DynamicProgramming.minimumPalindromePartitioningRecursive("doopy"));
    }

    @Test
    void minimumCostForMatrixChainMultiplication() {
        int[] matrixDimensions;

        matrixDimensions = new int[]{10, 30, 5, 60};
        assertEquals(4500, DynamicProgramming.minimumCostForMatrixChainMultiplication(matrixDimensions));

        matrixDimensions = new int[]{10, 100, 5, 50};
        assertEquals(7500, DynamicProgramming.minimumCostForMatrixChainMultiplication(matrixDimensions));

        matrixDimensions = new int[]{1, 2, 3, 4};
        assertEquals(18, DynamicProgramming.minimumCostForMatrixChainMultiplication(matrixDimensions));

        matrixDimensions = new int[]{40, 20, 30, 10, 30};
        assertEquals(26000, DynamicProgramming.minimumCostForMatrixChainMultiplication(matrixDimensions));

        matrixDimensions = new int[]{10, 20, 30, 40, 30};
        assertEquals(30000, DynamicProgramming.minimumCostForMatrixChainMultiplication(matrixDimensions));

        matrixDimensions = new int[]{10, 20, 30};
        assertEquals(6000, DynamicProgramming.minimumCostForMatrixChainMultiplication(matrixDimensions));
    }

    @Test
    void minimumCostForMatrixChainMultiplicationRecursive() {
        int[] matrixDimensions;

        matrixDimensions = new int[]{10, 30, 5, 60};
        assertEquals(4500, DynamicProgramming.minimumCostForMatrixChainMultiplicationRecursive(matrixDimensions));

        matrixDimensions = new int[]{10, 100, 5, 50};
        assertEquals(7500, DynamicProgramming.minimumCostForMatrixChainMultiplicationRecursive(matrixDimensions));

        matrixDimensions = new int[]{1, 2, 3, 4};
        assertEquals(18, DynamicProgramming.minimumCostForMatrixChainMultiplicationRecursive(matrixDimensions));

        matrixDimensions = new int[]{40, 20, 30, 10, 30};
        assertEquals(26000, DynamicProgramming.minimumCostForMatrixChainMultiplicationRecursive(matrixDimensions));

        matrixDimensions = new int[]{10, 20, 30, 40, 30};
        assertEquals(30000, DynamicProgramming.minimumCostForMatrixChainMultiplicationRecursive(matrixDimensions));

        matrixDimensions = new int[]{10, 20, 30};
        assertEquals(6000, DynamicProgramming.minimumCostForMatrixChainMultiplicationRecursive(matrixDimensions));
    }

    @Test
    void lengthOfLongestArithmeticProgression() {
        int[] arr;

        arr = new int[]{1, 7, 10, 15, 27, 29};
        assertEquals(3, DynamicProgramming.lengthOfLongestArithmeticProgression(arr));

        arr = new int[]{5, 10, 15, 20, 25, 30};
        assertEquals(6, DynamicProgramming.lengthOfLongestArithmeticProgression(arr));
    }

    @Test
    void lengthOfLongestGeometricProgression() {
        int[] arr;

        arr = new int[]{5, 7, 10, 15, 20, 29};
        assertEquals(3, DynamicProgramming.lengthOfLongestGeometricProgression(arr));

        arr = new int[]{3, 7, 11, 17};
        assertEquals(1, DynamicProgramming.lengthOfLongestGeometricProgression(arr));

        arr = new int[]{3, 7, 11, 15};
        assertEquals(2, DynamicProgramming.lengthOfLongestGeometricProgression(arr));

        arr = new int[]{3, 9, 27, 81};
        assertEquals(4, DynamicProgramming.lengthOfLongestGeometricProgression(arr));

        arr = new int[]{1, 3, 9, 27, 81, 243};
        assertEquals(6, DynamicProgramming.lengthOfLongestGeometricProgression(arr));

        arr = new int[]{1, 3, 4, 9, 7, 27};
        assertEquals(4, DynamicProgramming.lengthOfLongestGeometricProgression(arr));

        arr = new int[]{2, 3, 5, 7, 11, 13};
        assertEquals(1, DynamicProgramming.lengthOfLongestGeometricProgression(arr));
    }

    @Test
    void longestZigZagSubsequence() {
        int[] arr;

        arr = new int[]{1, 5, 4};
        assertEquals(3, DynamicProgramming.longestZigZagSubsequence(arr));

        arr = new int[]{1, 4, 5};
        assertEquals(2, DynamicProgramming.longestZigZagSubsequence(arr));

        arr = new int[]{10, 22, 9, 33, 49, 50, 31, 60};
        assertEquals(6, DynamicProgramming.longestZigZagSubsequence(arr));
    }

    @Test
    void maximumSumBitonicSubarray() {
        int[] arr;

        arr = new int[]{5, 3, 9, 2, 7, 6, 4};
        assertEquals(19, DynamicProgramming.maximumSumBitonicSubarray(arr));

        arr = new int[]{9, 12, 14, 8, 6, 5, 10, 20};
        assertEquals(54, DynamicProgramming.maximumSumBitonicSubarray(arr));
    }

    @Test
    void subsetWithSumDivisibleByMExists() {
        int[] arr;

        arr = new int[] {3, 1, 7, 5};
        assertTrue(DynamicProgramming.subsetWithSumDivisibleByMExists(arr, 6));

        arr = new int[] {1, 6};
        assertFalse(DynamicProgramming.subsetWithSumDivisibleByMExists(arr, 5));

        arr = new int[] {1, 7};
        assertFalse(DynamicProgramming.subsetWithSumDivisibleByMExists(arr, 5));
    }
}