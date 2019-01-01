package org.jwolfe.quetzal.algorithms;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.general.Pair;
import org.jwolfe.quetzal.library.general.Triplet;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DynamicProgrammingTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void lis() {
        int arr1[] = {10, 22, 9, 33, 21, 50, 41, 60};
        int lis1 = DynamicProgramming.lis(arr1);
        System.out.println("Length of lis is " + lis1);

        System.out.println();
        int arr2[] = {10, 9, 2, 5, 3, 7, 101, 18};
        int lis2 = DynamicProgramming.lis(arr2);
        System.out.println("Length of lis is " + lis2);
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
}