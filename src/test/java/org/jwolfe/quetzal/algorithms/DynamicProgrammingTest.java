package org.jwolfe.quetzal.algorithms;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.general.*;
import org.jwolfe.quetzal.library.tree.BinaryTreeNode;
import org.jwolfe.quetzal.library.utilities.Utilities;
import org.jwolfe.quetzal.test.QuetzalAssertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DynamicProgrammingTest {
    @Test
    void getFibonacciNumbers() {
        List<Integer> fibonacciSeries;
        List<Integer> expectedFibonacciSeries;

        expectedFibonacciSeries = Utilities.constructList(0, 1, 1, 2, 3, 5, 8, 13, 21, 34);
        fibonacciSeries = DynamicProgramming.getFibonacciNumbers(10);
        QuetzalAssertions.assertListStrictEquals(expectedFibonacciSeries, fibonacciSeries);
    }

    @Test
    void getFibonacciNumbersA2() {
        List<Integer> fibonacciSeries;
        List<Integer> expectedFibonacciSeries;

        expectedFibonacciSeries = Utilities.constructList(0, 1, 1, 2, 3, 5, 8, 13, 21, 34);
        fibonacciSeries = DynamicProgramming.getFibonacciNumbersA2(10);
        QuetzalAssertions.assertListStrictEquals(expectedFibonacciSeries, fibonacciSeries);
    }

    @Test
    void getReverseFibonacciNumbers() {
        List<Integer> reverseFibonacciSeries;
        List<Integer> expectedreverseFibonacciSeries;

        expectedreverseFibonacciSeries = Utilities.constructList(34, 21, 13, 8, 5, 3, 2, 1, 1, 0);
        reverseFibonacciSeries = DynamicProgramming.getReverseFibonacciNumbers(10);
        QuetzalAssertions.assertListStrictEquals(expectedreverseFibonacciSeries, reverseFibonacciSeries);
    }

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
    void longestIncreasingSubsequenceInCircularManner() {
        int[] arr;

        arr = new int[]{5, 4, 3, 2, 1};
        assertEquals(2, DynamicProgramming.longestIncreasingSubsequenceInCircularManner(arr));

        arr = new int[]{5, 6, 7, 1, 2, 3};
        assertEquals(6, DynamicProgramming.longestIncreasingSubsequenceInCircularManner(arr));

        arr = new int[]{1, 4, 6, 2, 3};
        assertEquals(4, DynamicProgramming.longestIncreasingSubsequenceInCircularManner(arr));
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
    void getLongestIncreasingSubsequenceList() {
        List<Integer> list;
        List<Integer> lisList;
        List<Integer> expectedLisList;

        list = Utilities.constructList(10, 22, 9, 33, 21, 50, 41, 60, 80);
        expectedLisList = Utilities.constructList(10, 22, 33, 50, 60, 80);
        lisList = DynamicProgramming.getLongestIncreasingSubsequence(list);
        QuetzalAssertions.assertListEquals(expectedLisList, lisList);

        list = Utilities.constructList(3, 2, 6, 4, 5, 1);
        expectedLisList = Utilities.constructList(2, 4, 5);
        lisList = DynamicProgramming.getLongestIncreasingSubsequence(list);
        QuetzalAssertions.assertListEquals(expectedLisList, lisList);
    }

    @Test
    void getLongestIncreasingConsecutiveSubsequence() {
        int[] arr;
        List<Integer> licsList;
        List<Integer> expectedlicsList;

        arr = new int[]{3, 10, 3, 11, 4, 5, 6, 7, 8, 12};
        expectedlicsList = Utilities.constructList(3, 4, 5, 6, 7, 8);
        licsList = DynamicProgramming.getLongestIncreasingConsecutiveSubsequence(arr);
        QuetzalAssertions.assertListEquals(expectedlicsList, licsList);

        arr = new int[]{6, 7, 8, 3, 4, 5, 9, 10};
        expectedlicsList = Utilities.constructList(6, 7, 8, 9, 10);
        licsList = DynamicProgramming.getLongestIncreasingConsecutiveSubsequence(arr);
        QuetzalAssertions.assertListEquals(expectedlicsList, licsList);
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
    void countIncreasingSubsequences() {
        int[] arr;

        arr = new int[]{1, 2, 3, 4};
        assertEquals(15, DynamicProgramming.countIncreasingSubsequences(arr));

        arr = new int[]{4, 3, 6, 5};
        assertEquals(8, DynamicProgramming.countIncreasingSubsequences(arr));

        arr = new int[]{3, 2, 4, 5, 4};
        assertEquals(14, DynamicProgramming.countIncreasingSubsequences(arr));
    }

    @Test
    void countIncreasingSubsequencesWhenArrayElementsAreDigits() {
        int[] arr;

        arr = new int[]{1, 2, 3, 4};
        assertEquals(15, DynamicProgramming.countIncreasingSubsequencesWhenArrayElementsAreDigits(arr));

        arr = new int[]{4, 3, 6, 5};
        assertEquals(8, DynamicProgramming.countIncreasingSubsequencesWhenArrayElementsAreDigits(arr));

        arr = new int[]{3, 2, 4, 5, 4};
        assertEquals(14, DynamicProgramming.countIncreasingSubsequencesWhenArrayElementsAreDigits(arr));
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
        String str = "DOOPSTHEDOOPS";
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
        String str = "DOOPSTHEDOOPS";
        int lps = DynamicProgramming.longestPalindromicSubsequenceRecursive(str);
        System.out.println("Longest palindromic subsequence of " + str + "  is of length " + lps);

        System.out.println();
        str = "BBABCBCAB";
        lps = DynamicProgramming.longestPalindromicSubsequenceRecursive(str);
        System.out.println("Longest palindromic subsequence of " + str + "  is of length " + lps);
    }

    @Test
    void getLongestPalindromicSubsequence() {
        assertEquals("BACBCAB", DynamicProgramming.getLongestPalindromicSubsequence("BBABCBCAB"));
        assertEquals("OODOO", DynamicProgramming.getLongestPalindromicSubsequence("DOOPSTHEDOOPS"));
    }

    @Test
    void countPalindromicSubsequences() {
        assertEquals(4, DynamicProgramming.countPalindromicSubsequences("abcd"));
        assertEquals(6, DynamicProgramming.countPalindromicSubsequences("abcb"));
        assertEquals(4, DynamicProgramming.countPalindromicSubsequences("aab"));
        assertEquals(15, DynamicProgramming.countPalindromicSubsequences("aaaa"));
    }

    @Test
    void countPalindromicSubsequencesRecursive() {
        assertEquals(4, DynamicProgramming.countPalindromicSubsequencesRecursive("abcd"));
        assertEquals(6, DynamicProgramming.countPalindromicSubsequencesRecursive("abcb"));
        assertEquals(4, DynamicProgramming.countPalindromicSubsequencesRecursive("aab"));
        assertEquals(15, DynamicProgramming.countPalindromicSubsequencesRecursive("aaaa"));
    }

    @Test
    void longestPalindromicSubstring() {
        System.out.println();
        String str = "forgeeksskeegfor";
        int lps = DynamicProgramming.longestPalindromicSubstring(str);
        System.out.println("Longest palindromic substring of " + str + "  is of length " + lps);
    }

    @Test
    void countPalindromicSubstrings() {
        assertEquals(3, DynamicProgramming.countPalindromicSubstrings("abaab"));
        assertEquals(4, DynamicProgramming.countPalindromicSubstrings("abbaeae"));
    }

    @Test
    void countPalindromicSubstringsRecursive() {
        assertEquals(3, DynamicProgramming.countPalindromicSubstringsRecursive("abaab"));
        assertEquals(4, DynamicProgramming.countPalindromicSubstringsRecursive("abbaeae"));
    }

    @Test
    void countPalindromicSubstringsForIndexRanges() {
        String str;
        List<Pair<Integer, Integer>> indexRanges;
        List<Integer> counts;
        List<Integer> expectedCounts;

        str = "xyaabax";
        indexRanges = Utilities.constructList(new Pair(3, 5));
        expectedCounts = Utilities.constructList(4);
        counts = DynamicProgramming.countPalindromicSubstringsForIndexRanges(str, indexRanges);
        QuetzalAssertions.assertListEquals(expectedCounts, counts);

        str = "xyaabax";
        indexRanges = Utilities.constructList(new Pair(3, 5), new Pair(2, 3));
        expectedCounts = Utilities.constructList(4, 3);
        counts = DynamicProgramming.countPalindromicSubstringsForIndexRanges(str, indexRanges);
        QuetzalAssertions.assertListEquals(expectedCounts, counts);
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
    void getAllLongestCommonSubsequences() {
        String str1;
        String str2;
        Set<String> lcsList;
        Set<String> expectedLcsList;

        str1 = "AGTGATG";
        str2 = "GTTAG";
        expectedLcsList = Utilities.constructSet("GTAG", "GTTG");
        lcsList = DynamicProgramming.getAllLongestCommonSubsequences(str1, str2);
        QuetzalAssertions.assertSetEquals(expectedLcsList, lcsList);

        str1 = "AATCC";
        str2 = "ACACG";
        expectedLcsList = Utilities.constructSet("ACC", "AAC");
        lcsList = DynamicProgramming.getAllLongestCommonSubsequences(str1, str2);
        QuetzalAssertions.assertSetEquals(expectedLcsList, lcsList);

        str1 = "ABCBDAB";
        str2 = "BDCABA";
        expectedLcsList = Utilities.constructSet("BCAB", "BCBA", "BDAB");
        lcsList = DynamicProgramming.getAllLongestCommonSubsequences(str1, str2);
        QuetzalAssertions.assertSetEquals(expectedLcsList, lcsList);
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
    void longestCommonSubsequenceWithUtmostKChangesAllowed() {
        int[] arr1;
        int[] arr2;
        int k;
        int lcs;

        arr1 = new int[]{8, 3};
        arr2 = new int[]{1, 3};
        k = 1;
        lcs = DynamicProgramming.longestCommonSubsequenceWithUtmostKChangesAllowed(arr1, arr2, k);
        assertEquals(2, lcs);

        arr1 = new int[]{1, 2, 3, 4, 5};
        arr2 = new int[]{5, 3, 1, 4, 2};
        k = 1;
        lcs = DynamicProgramming.longestCommonSubsequenceWithUtmostKChangesAllowed(arr1, arr2, k);
        assertEquals(3, lcs);
    }

    @Test
    void longestCommonSubsequence3Strings() {
        String str1;
        String str2;
        String str3;
        int lcs;

        str1 = "beeps";
        str2 = "beepsand";
        str3 = "beepsandbeeps";
        lcs = DynamicProgramming.longestCommonSubsequence(str1, str2, str3);
        assertEquals(5, lcs);

        str1 = "akcd8s2";
        str2 = "kc18sa";
        str3 = "kd8sa";
        lcs = DynamicProgramming.longestCommonSubsequence(str1, str2, str3);
        assertEquals(3, lcs);

        str1 = "ZPPK98";
        str2 = "98KXZYB";
        str3 = "98XBZ";
        lcs = DynamicProgramming.longestCommonSubsequence(str1, str2, str3);
        assertEquals(2, lcs);
    }

    @Test
    void longestCommonIncreasingSubsequenceOptimized() {
        int[] arr1;
        int[] arr2;

        arr1 = new int[]{3, 4, 9, 1};
        arr2 = new int[]{5, 3, 8, 9, 10, 2, 1};
        assertEquals(2, DynamicProgramming.longestCommonIncreasingSubsequenceOptimized(arr1, arr2));

        arr1 = new int[]{2, 3, 1, 6, 5, 4, 6};
        arr2 = new int[]{1, 3, 5, 6};
        assertEquals(3, DynamicProgramming.longestCommonIncreasingSubsequenceOptimized(arr1, arr2));

        arr1 = new int[]{4, 3, 5, 6, 7, 1, 2};
        arr2 = new int[]{1, 2, 3, 50, 6, 4, 7};
        assertEquals(3, DynamicProgramming.longestCommonIncreasingSubsequenceOptimized(arr1, arr2));
    }

    @Test
    void longestCommonIncreasingSubsequence() {
        int[] arr1;
        int[] arr2;

        arr1 = new int[]{3, 4, 9, 1};
        arr2 = new int[]{5, 3, 8, 9, 10, 2, 1};
        assertEquals(2, DynamicProgramming.longestCommonIncreasingSubsequence(arr1, arr2));

        arr1 = new int[]{2, 3, 1, 6, 5, 4, 6};
        arr2 = new int[]{1, 3, 5, 6};
        assertEquals(3, DynamicProgramming.longestCommonIncreasingSubsequence(arr1, arr2));

        arr1 = new int[]{4, 3, 5, 6, 7, 1, 2};
        arr2 = new int[]{1, 2, 3, 50, 6, 4, 7};
        assertEquals(3, DynamicProgramming.longestCommonIncreasingSubsequence(arr1, arr2));
    }

    @Test
    void longestCommonIncreasingSubsequenceRecursive() {
        int[] arr1;
        int[] arr2;

        arr1 = new int[]{3, 4, 9, 1};
        arr2 = new int[]{5, 3, 8, 9, 10, 2, 1};
        assertEquals(2, DynamicProgramming.longestCommonIncreasingSubsequenceRecursive(arr1, arr2));

        arr1 = new int[]{2, 3, 1, 6, 5, 4, 6};
        arr2 = new int[]{1, 3, 5, 6};
        assertEquals(3, DynamicProgramming.longestCommonIncreasingSubsequenceRecursive(arr1, arr2));

        arr1 = new int[]{4, 3, 5, 6, 7, 1, 2};
        arr2 = new int[]{1, 2, 3, 50, 6, 4, 7};
        assertEquals(3, DynamicProgramming.longestCommonIncreasingSubsequenceRecursive(arr1, arr2));
    }

    @Test
    void longestCommonSubstring() {
        String str1;
        String str2;
        int lcs;

        str1 = "beepsandbeeps";
        str2 = "beepssound";
        lcs = DynamicProgramming.longestCommonSubstring(str1, str2);
        assertEquals(5, lcs);

        str1 = "efghuvw";
        str2 = "uvwefgh";
        lcs = DynamicProgramming.longestCommonSubstring(str1, str2);
        assertEquals(4, lcs);

        str1 = "zxabcdezy";
        str2 = "yzabcdezx";
        lcs = DynamicProgramming.longestCommonSubstring(str1, str2);
        assertEquals(6, lcs);
    }

    @Test
    void longestCommonSubstringRecursive() {
        String str1;
        String str2;
        int lcs;

        str1 = "beepsandbeeps";
        str2 = "beepssound";
        lcs = DynamicProgramming.longestCommonSubstringRecursive(str1, str2);
        assertEquals(5, lcs);

        str1 = "efghuvw";
        str2 = "uvwefgh";
        lcs = DynamicProgramming.longestCommonSubstringRecursive(str1, str2);
        assertEquals(4, lcs);

        str1 = "zxabcdezy";
        str2 = "yzabcdezx";
        lcs = DynamicProgramming.longestCommonSubstringRecursive(str1, str2);
        assertEquals(6, lcs);
    }

    @Test
    void longestCommonSubstringSpaceOptimized() {
        String str1;
        String str2;
        int lcs;

        str1 = "beepsandbeeps";
        str2 = "beepssound";
        lcs = DynamicProgramming.longestCommonSubstringSpaceOptimized(str1, str2);
        assertEquals(5, lcs);

        str1 = "efghuvw";
        str2 = "uvwefgh";
        lcs = DynamicProgramming.longestCommonSubstringSpaceOptimized(str1, str2);
        assertEquals(4, lcs);

        str1 = "zxabcdezy";
        str2 = "yzabcdezx";
        lcs = DynamicProgramming.longestCommonSubstringSpaceOptimized(str1, str2);
        assertEquals(6, lcs);
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
    void minCoinsGreedy() {
        int coins[];
        int V;
        int minCoins;

        coins = Utilities.constructArray(1, 2, 5, 10, 20, 50, 100, 500, 1000);
        V = 70;
        minCoins = DynamicProgramming.minCoinsGreedy(coins, V);
        assertEquals(2, minCoins);

        coins = Utilities.constructArray(1, 2, 5, 10, 20, 50, 100, 500, 1000);
        V = 121;
        minCoins = DynamicProgramming.minCoinsGreedy(coins, V);
        assertEquals(3, minCoins);

        coins = Utilities.constructArray(9, 6, 5, 1);
        V = 11;
        minCoins = DynamicProgramming.minCoinsGreedy(coins, V);
        assertEquals(3, minCoins);
        // Note: Optimal solution is 2, not 3. Greedy is not optimal
    }

    @Test
    void maxChainLength() {
        List<IntPair> pairs;
        int maxChain;

        pairs = new ArrayList<>();
        pairs.add(new IntPair(5, 24));
        pairs.add(new IntPair(15, 25));
        pairs.add(new IntPair(27, 40));
        pairs.add(new IntPair(50, 60));
        maxChain = DynamicProgramming.maxChainLength(pairs);
        assertEquals(3, maxChain);
        System.out.println("Length of maximum size chain is " + maxChain);

        pairs = new ArrayList<>();
        pairs.add(new IntPair(5, 24));
        pairs.add(new IntPair(39, 60));
        pairs.add(new IntPair(15, 28));
        pairs.add(new IntPair(27, 40));
        pairs.add(new IntPair(50, 90));
        maxChain = DynamicProgramming.maxChainLength(pairs);
        assertEquals(3, maxChain);
        System.out.println("Length of maximum size chain is " + maxChain);
    }

    @Test
    void getMaxChain() {
        List<IntPair> pairs;
        List<IntPair> chain;
        List<IntPair> expectedChain;

        var p11 = new IntPair(5, 24);
        var p12 = new IntPair(15, 25);
        var p13 = new IntPair(27, 40);
        var p14 = new IntPair(50, 60);

        pairs = Utilities.constructList(p11, p12, p13, p14);
        expectedChain = Utilities.constructList(p11, p13, p14);
        chain = DynamicProgramming.getMaxChain(pairs);
        QuetzalAssertions.assertListStrictEquals(expectedChain, chain);

        var p21 = new IntPair(5, 24);
        var p22 = new IntPair(39, 60);
        var p23 = new IntPair(15, 28);
        var p24 = new IntPair(27, 40);
        var p25 = new IntPair(50, 90);
        pairs = Utilities.constructList(p21, p22, p23, p24, p25);
        expectedChain = Utilities.constructList(p11, p24, p25);
        chain = DynamicProgramming.getMaxChain(pairs);
        QuetzalAssertions.assertListStrictEquals(expectedChain, chain);

        var p31 = new IntPair(11, 20);
        var p32 = new IntPair(10, 40);
        var p33 = new IntPair(45, 60);
        var p34 = new IntPair(39, 40);
        pairs = Utilities.constructList(p31, p32, p33, p34);
        expectedChain = Utilities.constructList(p31, p34, p33);
        chain = DynamicProgramming.getMaxChain(pairs);
        QuetzalAssertions.assertListStrictEquals(expectedChain, chain);
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
    void getLongestBitonicSubsequence() {
        int[] arr;
        List<Integer> lbs;
        List<List<Integer>> expectedLbsList;

        arr = new int[]{1, 11, 2, 10, 4, 5, 2, 1};
        expectedLbsList = Utilities.constructList(Utilities.constructList(1, 2, 10, 4, 2, 1),
                Utilities.constructList(1, 11, 10, 5, 2, 1),
                Utilities.constructList(1, 2, 4, 5, 2, 1));
        lbs = DynamicProgramming.getLongestBitonicSubsequence(arr);
        QuetzalAssertions.assertListOfListStrictlyContainsList(expectedLbsList, lbs);

        arr = new int[]{12, 11, 40, 5, 3, 1};
        expectedLbsList = Utilities.constructList(Utilities.constructList(12, 11, 5, 3, 1),
                Utilities.constructList(12, 40, 5, 3, 1));
        lbs = DynamicProgramming.getLongestBitonicSubsequence(arr);
        QuetzalAssertions.assertListOfListStrictlyContainsList(expectedLbsList, lbs);

        arr = new int[]{80, 60, 30, 40, 20, 10};
        expectedLbsList = Utilities.constructList(Utilities.constructList(80, 60, 30, 20, 10),
                Utilities.constructList(80, 60, 40, 20, 10));
        lbs = DynamicProgramming.getLongestBitonicSubsequence(arr);
        QuetzalAssertions.assertListOfListStrictlyContainsList(expectedLbsList, lbs);
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
    void canSetBePartitionedIntoTwoForEqualSum() {
        int[] set;

        set = new int[]{1, 5, 11, 5};
        assertTrue(DynamicProgramming.canSetBePartitionedIntoTwoForEqualSum(set));

        set = new int[]{1, 5, 3};
        assertFalse(DynamicProgramming.canSetBePartitionedIntoTwoForEqualSum(set));
    }

    @Test
    void getEqualSumPartitionedSubsets() {
        int[] set;
        Pair<List<Integer>, List<Integer>> partitions;

        set = new int[]{1, 5, 11, 5};
        var expectedPartition1 = Utilities.constructList(5, 5, 1);
        var expectedPartition2 = Utilities.constructList(11);
        partitions = DynamicProgramming.getEqualSumPartitionedSubsets(set);
        QuetzalAssertions.assertListEquals(expectedPartition1, partitions.getFirst());
        QuetzalAssertions.assertListEquals(expectedPartition2, partitions.getSecond());

        set = new int[]{1, 5, 3};
        partitions = DynamicProgramming.getEqualSumPartitionedSubsets(set);
        assertNull(partitions);
    }

    @Test
    void getEqualSumPartitionedSubsetsRecursive() {
        int[] set;
        Pair<List<Integer>, List<Integer>> partitions;

        set = new int[]{1, 5, 11, 5};
        var expectedPartition1 = Utilities.constructList(5, 5, 1);
        var expectedPartition2 = Utilities.constructList(11);
        partitions = DynamicProgramming.getEqualSumPartitionedSubsetsRecursive(set);
        QuetzalAssertions.assertListEquals(expectedPartition1, partitions.getFirst());
        QuetzalAssertions.assertListEquals(expectedPartition2, partitions.getSecond());

        set = new int[]{1, 5, 3};
        partitions = DynamicProgramming.getEqualSumPartitionedSubsetsRecursive(set);
        assertNull(partitions);
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
    void longestAlternatingSubsequence() {
        int[] arr;

        arr = new int[]{1, 5, 4};
        assertEquals(3, DynamicProgramming.longestZigZagSubsequence(arr));

        arr = new int[]{1, 4, 5};
        assertEquals(2, DynamicProgramming.longestZigZagSubsequence(arr));

        arr = new int[]{10, 22, 9, 33, 49, 50, 31, 60};
        assertEquals(6, DynamicProgramming.longestZigZagSubsequence(arr));
    }

    @Test
    void maximumSumBitonicSubsequence() {
        int[] arr;

        arr = new int[]{1, 15, 51, 45, 33, 100, 12, 18, 9};
        assertEquals(194, DynamicProgramming.maximumSumBitonicSubsequence(arr));

        arr = new int[]{80, 60, 30, 40, 20, 10};
        assertEquals(210, DynamicProgramming.maximumSumBitonicSubsequence(arr));
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

        arr = new int[]{3, 1, 7, 5};
        assertTrue(DynamicProgramming.subsetWithSumDivisibleByMExists(arr, 6));

        arr = new int[]{1, 6};
        assertFalse(DynamicProgramming.subsetWithSumDivisibleByMExists(arr, 5));

        arr = new int[]{1, 7};
        assertFalse(DynamicProgramming.subsetWithSumDivisibleByMExists(arr, 5));
    }

    @Test
    void maximumSumOfIncreasingSubsequence() {
        int[] arr;

        arr = new int[]{1, 101, 2, 3, 100, 4, 5};
        assertEquals(106, DynamicProgramming.maximumSumOfIncreasingSubsequence(arr));

        arr = new int[]{3, 4, 5, 10};
        assertEquals(22, DynamicProgramming.maximumSumOfIncreasingSubsequence(arr));
    }

    @Test
    void getMaximumSumOfIncreasingSubsequence() {
        int[] arr;
        List<Integer> msisList;
        List<Integer> expectedMsisList;

        arr = new int[]{1, 101, 2, 3, 100, 4, 5};
        expectedMsisList = Utilities.constructList(1, 2, 3, 100);
        msisList = DynamicProgramming.getMaximumSumOfIncreasingSubsequence(arr);
        QuetzalAssertions.assertListEquals(expectedMsisList, msisList);

        arr = new int[]{3, 4, 5, 10};
        expectedMsisList = Utilities.constructList(3, 4, 5, 10);
        msisList = DynamicProgramming.getMaximumSumOfIncreasingSubsequence(arr);
        QuetzalAssertions.assertListEquals(expectedMsisList, msisList);

        arr = new int[]{10, 5, 4, 3};
        expectedMsisList = Utilities.constructList(10);
        msisList = DynamicProgramming.getMaximumSumOfIncreasingSubsequence(arr);
        QuetzalAssertions.assertListEquals(expectedMsisList, msisList);

        arr = new int[]{3, 2, 6, 4, 5, 1};
        expectedMsisList = Utilities.constructList(3, 4, 5);
        msisList = DynamicProgramming.getMaximumSumOfIncreasingSubsequence(arr);
        QuetzalAssertions.assertListEquals(expectedMsisList, msisList);
    }

    @Test
    void maximumProductOfIncreasingSubsequence() {
        int[] arr;

        arr = new int[]{3, 100, 4, 5, 150, 6};
        assertEquals(45000, DynamicProgramming.maximumProductOfIncreasingSubsequence(arr));

        arr = new int[]{10, 22, 9, 33, 21, 50, 41, 60};
        assertEquals(21780000, DynamicProgramming.maximumProductOfIncreasingSubsequence(arr));
    }

    @Test
    void minimumSumNonDecreasingSubsequenceOfLengthK() {
        int[] arr;

        arr = new int[]{58, 12, 11, 12, 82, 30, 20, 77, 16, 86};
        assertEquals(39, DynamicProgramming.minimumSumNonDecreasingSubsequenceOfLengthK(arr, 3));

        arr = new int[]{58, 12, 11, 12, 82, 30, 20, 77, 16, 86};
        assertEquals(120, DynamicProgramming.minimumSumNonDecreasingSubsequenceOfLengthK(arr, 4));

        arr = new int[]{58, 12, 11, 12, 82, 30, 20, 77, 16, 86};
        assertEquals(206, DynamicProgramming.minimumSumNonDecreasingSubsequenceOfLengthK(arr, 5));
    }

    @Test
    void countUniquePathsFromTopLeftToBottomRightInGridWithObstacles() {
        int[][] grid;

        grid = new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        assertEquals(0, DynamicProgramming.countUniquePathsFromTopLeftToBottomRightInGridWithObstacles(grid));

        grid = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 1}};
        assertEquals(0, DynamicProgramming.countUniquePathsFromTopLeftToBottomRightInGridWithObstacles(grid));

        grid = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        assertEquals(6, DynamicProgramming.countUniquePathsFromTopLeftToBottomRightInGridWithObstacles(grid));

        grid = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        assertEquals(2, DynamicProgramming.countUniquePathsFromTopLeftToBottomRightInGridWithObstacles(grid));
    }

    @Test
    void lengthOfShortestCommonSupersequence() {
        String str1;
        String str2;

        str1 = "doop";
        str2 = "opo";
        assertEquals(5, DynamicProgramming.lengthOfShortestCommonSupersequence(str1, str2));

        str1 = "AGGTAB";
        str2 = "GXTXAYB";
        assertEquals(9, DynamicProgramming.lengthOfShortestCommonSupersequence(str1, str2));
    }

    @Test
    void maximumSumAlternatingSubsequence() {
        int[] arr;

        arr = new int[]{4, 3, 8, 5, 3, 8};
        assertEquals(28, DynamicProgramming.maximumSumAlternatingSubsequence(arr));

        arr = new int[]{4, 8, 2, 5, 6, 8};
        assertEquals(14, DynamicProgramming.maximumSumAlternatingSubsequence(arr));

        arr = new int[]{8, 2, 3, 5, 7, 9, 10};
        assertEquals(25, DynamicProgramming.maximumSumAlternatingSubsequence(arr));
    }

    @Test
    void lengthOfShortestUncommonSubsequence() {
        String str1;
        String str2;

        str1 = "ab";
        str2 = "cx";
        assertEquals(1, DynamicProgramming.lengthOfShortestUncommonSubsequence(str1, str2));

        str1 = "abc";
        str2 = "cxa";
        assertEquals(1, DynamicProgramming.lengthOfShortestUncommonSubsequence(str1, str2));

        str1 = "abd";
        str2 = "cxa";
        assertEquals(1, DynamicProgramming.lengthOfShortestUncommonSubsequence(str1, str2));

        str1 = "babab";
        str2 = "babba";
        assertEquals(3, DynamicProgramming.lengthOfShortestUncommonSubsequence(str1, str2));

        str1 = "abb";
        str2 = "abab";
        assertEquals(-1, DynamicProgramming.lengthOfShortestUncommonSubsequence(str1, str2));
    }

    @Test
    void lengthOfShortestUncommonSubsequenceRecursive() {
        String str1;
        String str2;

        str1 = "ab";
        str2 = "cx";
        assertEquals(1, DynamicProgramming.lengthOfShortestUncommonSubsequenceRecursive(str1, str2));

        str1 = "abc";
        str2 = "cxa";
        assertEquals(1, DynamicProgramming.lengthOfShortestUncommonSubsequenceRecursive(str1, str2));

        str1 = "abd";
        str2 = "cxa";
        assertEquals(1, DynamicProgramming.lengthOfShortestUncommonSubsequenceRecursive(str1, str2));

        str1 = "babab";
        str2 = "babba";
        assertEquals(3, DynamicProgramming.lengthOfShortestUncommonSubsequenceRecursive(str1, str2));

        str1 = "abb";
        str2 = "abab";
        assertEquals(-1, DynamicProgramming.lengthOfShortestUncommonSubsequenceRecursive(str1, str2));
    }

    @Test
    void countDistinctSubsequences() {
        assertEquals(1, DynamicProgramming.countDistinctSubsequences(""));
        assertEquals(2, DynamicProgramming.countDistinctSubsequences("a"));
        assertEquals(7, DynamicProgramming.countDistinctSubsequences("aka"));
        assertEquals(4, DynamicProgramming.countDistinctSubsequences("zzz"));
    }

    @Test
    void lengthOfLongestIncreasingOddEvenSubsequence() {
        int[] arr;

        arr = new int[]{5, 6, 9, 4, 7, 8};
        assertEquals(4, DynamicProgramming.lengthOfLongestIncreasingOddEvenSubsequence(arr));

        arr = new int[]{1, 12, 2, 22, 5, 30, 31, 14, 17, 11};
        assertEquals(5, DynamicProgramming.lengthOfLongestIncreasingOddEvenSubsequence(arr));
    }

    @Test
    void lengthOfLongestParanthesisBalancedSubsequence() {
        assertEquals(0, DynamicProgramming.lengthOfLongestParanthesisBalancedSubsequence(")("));
        assertEquals(0, DynamicProgramming.lengthOfLongestParanthesisBalancedSubsequence("))(("));
        assertEquals(2, DynamicProgramming.lengthOfLongestParanthesisBalancedSubsequence("()"));
        assertEquals(4, DynamicProgramming.lengthOfLongestParanthesisBalancedSubsequence("()()"));
        assertEquals(4, DynamicProgramming.lengthOfLongestParanthesisBalancedSubsequence("()())"));
        assertEquals(6, DynamicProgramming.lengthOfLongestParanthesisBalancedSubsequence("(()())"));
        assertEquals(4, DynamicProgramming.lengthOfLongestParanthesisBalancedSubsequence("()(((((()"));
    }

    @Test
    void lengthOfVertexCover() {
        BinaryTreeNode root;

        root = Utilities.constructBinaryTree(10, 20, 30, 40, 50, null, 60, null, null, 70, 80);
        assertEquals(3, DynamicProgramming.lengthOfVertexCover(root));
        ;
        root = Utilities.constructBinaryTree(20, 8, 22, 4, 12, null, 25, null, null, 10, 14);
        assertEquals(3, DynamicProgramming.lengthOfVertexCover(root));
    }

    @Test
    void lengthOfVertexCoverRecursive() {
        BinaryTreeNode root;

        root = Utilities.constructBinaryTree(10, 20, 30, 40, 50, null, 60, null, null, 70, 80);
        assertEquals(3, DynamicProgramming.lengthOfVertexCoverRecursive(root));
        ;
        root = Utilities.constructBinaryTree(20, 8, 22, 4, 12, null, 25, null, null, 10, 14);
        assertEquals(3, DynamicProgramming.lengthOfVertexCoverRecursive(root));
    }

    @Test
    void getVertexCover() {
        BinaryTreeNode root;
        List<BinaryTreeNode> vertexCover;
        List<BinaryTreeNode> expectedVertexCover;

        expectedVertexCover = Utilities.constructList(new BinaryTreeNode(20), new BinaryTreeNode(30), new BinaryTreeNode(50));
        root = Utilities.constructBinaryTree(10, 20, 30, 40, 50, null, 60, null, null, 70, 80);
        vertexCover = DynamicProgramming.getVertexCover(root);
        QuetzalAssertions.assertListOfTreeNodesAreEqualOnData(expectedVertexCover, vertexCover);

        expectedVertexCover = Utilities.constructList(new BinaryTreeNode(8), new BinaryTreeNode(12), new BinaryTreeNode(22));
        root = Utilities.constructBinaryTree(20, 8, 22, 4, 12, null, 25, null, null, 10, 14);
        vertexCover = DynamicProgramming.getVertexCover(root);
        QuetzalAssertions.assertListOfTreeNodesAreEqualOnData(expectedVertexCover, vertexCover);
    }

    @Test
    void getMaximumProfitFromWeightedActivityScheduling() {
        List<Activity> activities;

        activities = new ArrayList<>();
        activities.add(new Activity(3, 10, 20));
        activities.add(new Activity(1, 2, 50));
        activities.add(new Activity(6, 19, 100));
        activities.add(new Activity(2, 100, 200));

        assertEquals(250, DynamicProgramming.getMaximumProfitFromWeightedActivityScheduling(activities));
    }

    @Test
    void getMaximumProfitFromWeightedActivitySchedulingRecursive() {
        List<Activity> activities;

        activities = new ArrayList<>();
        activities.add(new Activity(3, 10, 20));
        activities.add(new Activity(1, 2, 50));
        activities.add(new Activity(6, 19, 100));
        activities.add(new Activity(2, 100, 200));

        assertEquals(250, DynamicProgramming.getMaximumProfitFromWeightedActivitySchedulingRecursive(activities));
    }

    @Test
    void getActivitiesForMaximumProfitFromWeightedActivityScheduling() {
        List<Activity> activities;
        List<Activity> scheduledActivities;
        List<Activity> expectedScheduledActivities;

        var a1 = new Activity(3, 10, 20);
        var a2 = new Activity(1, 2, 50);
        var a3 = new Activity(6, 19, 100);
        var a4 = new Activity(2, 100, 200);

        activities = Utilities.constructList(a1, a2, a3, a4);
        expectedScheduledActivities = Utilities.constructList(a2, a4);
        scheduledActivities = DynamicProgramming.getActivitiesForMaximumProfitFromWeightedActivityScheduling(activities);
        QuetzalAssertions.assertListEquals(expectedScheduledActivities, scheduledActivities);
    }

    @Test
    void numberOfWaysToReachScoreInAGame() {
        int totalScore;
        int[] turnScores;

        totalScore = 20;
        turnScores = new int[]{3, 5, 10};
        assertEquals(4, DynamicProgramming.numberOfWaysToReachScoreInAGame(totalScore, turnScores));

        totalScore = 13;
        turnScores = new int[]{3, 5, 10};
        assertEquals(2, DynamicProgramming.numberOfWaysToReachScoreInAGame(totalScore, turnScores));
    }

    @Test
    void maximumBridgesThatCanBeBuiltWithoutOverlaps() {
        List<IntPair> cityPairs;

        cityPairs = Utilities.constructList(new IntPair(6, 2), new IntPair(4, 3), new IntPair(2, 6), new IntPair(1, 5));
        assertEquals(2, DynamicProgramming.maximumBridgesThatCanBeBuiltWithoutOverlaps(cityPairs));

        cityPairs = Utilities.constructList(new IntPair(8, 1), new IntPair(1, 2), new IntPair(4, 3), new IntPair(3, 4),
                new IntPair(5, 5), new IntPair(2, 6), new IntPair(6, 7), new IntPair(7, 8));
        assertEquals(5, DynamicProgramming.maximumBridgesThatCanBeBuiltWithoutOverlaps(cityPairs));
    }

    @Test
    void checkForPathInMatrix() {
        int[][] matrix;

        matrix = new int[][]{{0, 0, 0, -1, 0},
                {-1, 0, 0, -1, -1},
                {0, 0, 0, -1, 0},
                {-1, 0, 0, 0, 0},
                {0, 0, -1, 0, 0}};
        assertTrue(DynamicProgramming.checkForPathInMatrix(matrix));

        matrix = new int[][]{{0, 0, 0, -1, 0},
                {-1, 0, 0, -1, -1},
                {0, 0, 0, -1, 0},
                {-1, 0, -1, 0, 0},
                {0, 0, -1, 0, 0}};
        assertFalse(DynamicProgramming.checkForPathInMatrix(matrix));
    }

    @Test
    void maximumPathSumInTriangle() {
        int[][] triangle;

        triangle = new int[][]{{1, 0, 0},
                {4, 8, 0},
                {1, 5, 3}};
        assertEquals(14, DynamicProgramming.maximumPathSumInTriangle(triangle));

        triangle = new int[][]{{3, 0, 0, 0},
                {7, 4, 0, 0},
                {2, 4, 6, 0},
                {8, 5, 9, 3}};
        assertEquals(23, DynamicProgramming.maximumPathSumInTriangle(triangle));

        triangle = new int[][]{{8, 0, 0, 0},
                {-4, 4, 0, 0},
                {2, 2, 6, 0},
                {1, 1, 1, 1}};
        assertEquals(19, DynamicProgramming.maximumPathSumInTriangle(triangle));
    }

    @Test
    void minimumPathSumInTriangle() {
        int[][] triangle;

        triangle = new int[][]{{2, 0, 0},
                {3, 9, 0},
                {1, 6, 7}};
        assertEquals(6, DynamicProgramming.minimumPathSumInTriangle(triangle));

        triangle = new int[][]{{2, 0, 0, 0},
                {3, 7, 0, 0},
                {8, 5, 6, 0},
                {6, 1, 9, 3}};
        assertEquals(11, DynamicProgramming.minimumPathSumInTriangle(triangle));

        triangle = new int[][]{{3, 0, 0, 0},
                {6, 4, 0, 0},
                {5, 2, 7, 0},
                {9, 1, 8, 6}};
        assertEquals(10, DynamicProgramming.minimumPathSumInTriangle(triangle));
    }

    @Test
    void minimumPathSumIn3DArray() {
        int[][][] arr;

        arr = new int[][][]{{{1, 2}, {3, 4}},
                {{4, 8}, {5, 2}}};
        assertEquals(9, DynamicProgramming.minimumPathSumIn3DArray(arr));

        arr = new int[][][]{{{1, 2}, {4, 3}},
                {{3, 4}, {2, 1}}};
        assertEquals(7, DynamicProgramming.minimumPathSumIn3DArray(arr));
    }

    @Test
    void numberOfChangesToConvertToStrictlyIncreasingArray() {
        int[] arr;

        arr = new int[]{1, 2, 6, 5, 4};
        assertEquals(2, DynamicProgramming.numberOfChangesToConvertToStrictlyIncreasingArray(arr));

        arr = new int[]{1, 2, 3, 5, 7, 11};
        assertEquals(0, DynamicProgramming.numberOfChangesToConvertToStrictlyIncreasingArray(arr));
    }

    @Test
    void minimumJumpsRequiredToReachEndOfArray() {
        int[] arr;

        arr = new int[]{1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        assertEquals(3, DynamicProgramming.minimumJumpsRequiredToReachEndOfArray(arr));

        arr = new int[]{1, 3, 6, 3, 2, 3, 6, 8, 9, 5};
        assertEquals(4, DynamicProgramming.minimumJumpsRequiredToReachEndOfArray(arr));

        arr = new int[]{1, 3, 6, 1, 0, 9};
        assertEquals(3, DynamicProgramming.minimumJumpsRequiredToReachEndOfArray(arr));
    }

    @Test
    void minimumJumpsRequiredToReachEndOfArrayOptimized() {
        int[] arr;

        arr = new int[]{1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        assertEquals(3, DynamicProgramming.minimumJumpsRequiredToReachEndOfArrayOptimized(arr));

        arr = new int[]{1, 3, 6, 3, 2, 3, 6, 8, 9, 5};
        assertEquals(4, DynamicProgramming.minimumJumpsRequiredToReachEndOfArrayOptimized(arr));

        arr = new int[]{1, 3, 6, 1, 0, 9};
        assertEquals(3, DynamicProgramming.minimumJumpsRequiredToReachEndOfArrayOptimized(arr));
    }

    @Test
    void minimumJumpsRequiredToReachEndOfArrayA2() {
        int[] arr;

        arr = new int[]{1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        assertEquals(3, DynamicProgramming.minimumJumpsRequiredToReachEndOfArrayA2(arr));

        arr = new int[]{1, 3, 6, 3, 2, 3, 6, 8, 9, 5};
        assertEquals(4, DynamicProgramming.minimumJumpsRequiredToReachEndOfArrayA2(arr));

        arr = new int[]{1, 3, 6, 1, 0, 9};
        assertEquals(3, DynamicProgramming.minimumJumpsRequiredToReachEndOfArrayA2(arr));
    }

    @Test
    void minimumJumpsRequiredToReachEndOfArrayRecursive() {
        int[] arr;

        arr = new int[]{1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        assertEquals(3, DynamicProgramming.minimumJumpsRequiredToReachEndOfArrayRecursive(arr));

        arr = new int[]{1, 3, 6, 3, 2, 3, 6, 8, 9, 5};
        assertEquals(4, DynamicProgramming.minimumJumpsRequiredToReachEndOfArrayRecursive(arr));

        arr = new int[]{1, 3, 6, 1, 0, 9};
        assertEquals(3, DynamicProgramming.minimumJumpsRequiredToReachEndOfArrayRecursive(arr));
    }

    @Test
    void minTimeForCarAssemblyWithTwoAssemblyLines() {
        int[][] stationTimes;
        int[][] transferTimes;
        IntPair entryTimes;
        IntPair exitTimes;

        stationTimes = new int[][]{{4, 5, 3, 2},
                {2, 10, 1, 4}};
        transferTimes = new int[][]{{0, 7, 4, 5},
                {0, 9, 2, 8}};
        entryTimes = new IntPair(10, 12);
        exitTimes = new IntPair(18, 7);
        assertEquals(35, DynamicProgramming.minTimeForCarAssemblyWithTwoAssemblyLines(stationTimes, transferTimes, entryTimes, exitTimes));
    }

    @Test
    void getCostOfOptimalBinarySearchTreeForSearchFrequencies() {
        int[] keys;
        int[] frequencies;

        keys = new int[]{10, 12};
        frequencies = new int[]{34, 50};
        assertEquals(118, DynamicProgramming.getCostOfOptimalBinarySearchTreeForSearchFrequencies(keys, frequencies));

        keys = new int[]{10, 12, 20};
        frequencies = new int[]{34, 8, 50};
        assertEquals(142, DynamicProgramming.getCostOfOptimalBinarySearchTreeForSearchFrequencies(keys, frequencies));
    }

    @Test
    void getCostOfOptimalBinarySearchTreeForSearchFrequenciesRecursive() {
        int[] keys;
        int[] frequencies;

        keys = new int[]{10, 12};
        frequencies = new int[]{34, 50};
        assertEquals(118, DynamicProgramming.getCostOfOptimalBinarySearchTreeForSearchFrequenciesRecursive(keys, frequencies));

        keys = new int[]{10, 12, 20};
        frequencies = new int[]{34, 8, 50};
        assertEquals(142, DynamicProgramming.getCostOfOptimalBinarySearchTreeForSearchFrequenciesRecursive(keys, frequencies));
    }

    @Test
    void maxValueOfCoinsInOptimalStrategyForGameWhereFirstOrLastCoinCanBeTakenInTurn() {
        int[] coinValues;

        coinValues = new int[]{5, 3, 7, 10};
        assertEquals(15, DynamicProgramming.maxValueOfCoinsInOptimalStrategyForGameWhereFirstOrLastCoinCanBeTakenInTurn(coinValues));

        coinValues = new int[]{8, 15, 3, 7};
        assertEquals(22, DynamicProgramming.maxValueOfCoinsInOptimalStrategyForGameWhereFirstOrLastCoinCanBeTakenInTurn(coinValues));

        coinValues = new int[]{2, 2, 2, 2};
        assertEquals(4, DynamicProgramming.maxValueOfCoinsInOptimalStrategyForGameWhereFirstOrLastCoinCanBeTakenInTurn(coinValues));

        coinValues = new int[]{20, 30, 2, 2, 2, 10};
        assertEquals(42, DynamicProgramming.maxValueOfCoinsInOptimalStrategyForGameWhereFirstOrLastCoinCanBeTakenInTurn(coinValues));
    }

    @Test
    void maxValueOfCoinsInOptimalStrategyForGameWhereFirstOrLastCoinCanBeTakenInTurnRecursive() {
        int[] coinValues;

        coinValues = new int[]{5, 3, 7, 10};
        assertEquals(15, DynamicProgramming.maxValueOfCoinsInOptimalStrategyForGameWhereFirstOrLastCoinCanBeTakenInTurnRecursive(coinValues));

        coinValues = new int[]{8, 15, 3, 7};
        assertEquals(22, DynamicProgramming.maxValueOfCoinsInOptimalStrategyForGameWhereFirstOrLastCoinCanBeTakenInTurnRecursive(coinValues));

        coinValues = new int[]{2, 2, 2, 2};
        assertEquals(4, DynamicProgramming.maxValueOfCoinsInOptimalStrategyForGameWhereFirstOrLastCoinCanBeTakenInTurnRecursive(coinValues));

        coinValues = new int[]{20, 30, 2, 2, 2, 10};
        assertEquals(42, DynamicProgramming.maxValueOfCoinsInOptimalStrategyForGameWhereFirstOrLastCoinCanBeTakenInTurnRecursive(coinValues));
    }

    @Test
    void minimumCostToMakeTwoStringsIdentical() {
        assertEquals(30, DynamicProgramming.minimumCostToMakeTwoStringsIdentical("abcd", "acdb", 10, 20));
        assertEquals(60, DynamicProgramming.minimumCostToMakeTwoStringsIdentical("ef", "gh", 10, 20));
    }

    @Test
    void minimumInsertionsRequiredToSortAnArray() {
        int[] arr;

        arr = new int[]{2, 3, 5, 1, 4, 7, 6};
        assertEquals(3, DynamicProgramming.minimumInsertionsRequiredToSortAnArray(arr));

        arr = new int[]{4, 6, 5, 1};
        assertEquals(2, DynamicProgramming.minimumInsertionsRequiredToSortAnArray(arr));
    }

    @Test
    void numberOfWaysToPartitionASetIntoKSubsets() {
        assertEquals(3, DynamicProgramming.numberOfWaysToPartitionASetIntoKSubsets(3, 2));
        assertEquals(1, DynamicProgramming.numberOfWaysToPartitionASetIntoKSubsets(3, 1));
        assertEquals(15, DynamicProgramming.numberOfWaysToPartitionASetIntoKSubsets(5, 2));
    }

    @Test
    void numberOfWaysToPartitionASetIntoKSubsetsRecursive() {
        assertEquals(3, DynamicProgramming.numberOfWaysToPartitionASetIntoKSubsetsRecursive(3, 2));
        assertEquals(1, DynamicProgramming.numberOfWaysToPartitionASetIntoKSubsetsRecursive(3, 1));
        assertEquals(15, DynamicProgramming.numberOfWaysToPartitionASetIntoKSubsetsRecursive(5, 2));
    }

    @Test
    void isKPalindrome() {
        assertTrue(DynamicProgramming.isKPalindrome("abcdecba", 1));
        assertTrue(DynamicProgramming.isKPalindrome("abcdeca", 2));
        assertFalse(DynamicProgramming.isKPalindrome("acdcb", 1));
    }

    @Test
    void isKPalindromeUsingEditDistance() {
        assertTrue(DynamicProgramming.isKPalindromeUsingEditDistance("abcdecba", 1));
        assertTrue(DynamicProgramming.isKPalindromeUsingEditDistance("abcdeca", 2));
        assertFalse(DynamicProgramming.isKPalindromeUsingEditDistance("acdcb", 1));
    }

    @Test
    void countPalindromicSubsequencesOfLengthK() {
        assertEquals(5, DynamicProgramming.countPalindromicSubsequencesOfLengthK("aabab", 1));
        assertEquals(4, DynamicProgramming.countPalindromicSubsequencesOfLengthK("aabab", 2));
        assertEquals(1, DynamicProgramming.countPalindromicSubsequencesOfLengthK("aaa", 3));
    }

    @Test
    void minimumDeletionsToMakeASortedSequence() {
        int[] arr;

        arr = new int[]{5, 6, 1, 7, 4};
        assertEquals(2, DynamicProgramming.minimumDeletionsToMakeASortedSequence(arr));

        arr = new int[]{30, 40, 2, 5, 1, 7, 45, 50, 8};
        assertEquals(4, DynamicProgramming.minimumDeletionsToMakeASortedSequence(arr));
    }
}