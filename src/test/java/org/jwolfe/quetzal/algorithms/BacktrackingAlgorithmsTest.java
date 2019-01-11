package org.jwolfe.quetzal.algorithms;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.general.Coordinate;
import org.jwolfe.quetzal.library.utilities.Utilities;
import org.jwolfe.quetzal.test.QuetzalAssertions;

class BacktrackingAlgorithmsTest {

	@Test
	void generateSubsetsWithGivenSum() {
		int[] arr;
		int sum;
		List<Set<Integer>> subsets;

		arr = Utilities.constructArray(1, 2, 3);
		sum = 6;
		subsets = BacktrackingAlgorithms.generateSubsetsWithGivenSum(arr, sum);
		assertNotNull(subsets);
		assertEquals(1, subsets.size());
		QuetzalAssertions.assertSetEquals(Utilities.constructSet(1, 2, 3), subsets.get(0));

		arr = Utilities.constructArray(1, 2, 3);
		sum = 2;
		subsets = BacktrackingAlgorithms.generateSubsetsWithGivenSum(arr, sum);
		assertNotNull(subsets);
		assertEquals(1, subsets.size());
		QuetzalAssertions.assertSetEquals(Utilities.constructSet(2), subsets.get(0));

		arr = Utilities.constructArray(15, 22, 14, 26, 32, 9, 16, 8);
		sum = 53;
		subsets = BacktrackingAlgorithms.generateSubsetsWithGivenSum(arr, sum);
		assertNotNull(subsets);
		assertEquals(3, subsets.size());
		QuetzalAssertions.assertSetEquals(Utilities.constructSet(15, 16, 22), subsets.get(0));
		QuetzalAssertions.assertSetEquals(Utilities.constructSet(8, 14, 15, 16), subsets.get(1));
		QuetzalAssertions.assertSetEquals(Utilities.constructSet(8, 9, 14, 22), subsets.get(2));
	}

	@Test
	void generateSubsetsWithGivenSumForSortedArray() {
		int[] arr;
		int sum;
		List<Set<Integer>> subsets;

		arr = Utilities.constructArray(1, 2, 3);
		sum = 6;
		subsets = BacktrackingAlgorithms.generateSubsetsWithGivenSumForSortedArray(arr, sum);
		assertNotNull(subsets);
		assertEquals(1, subsets.size());
		QuetzalAssertions.assertSetEquals(Utilities.constructSet(1, 2, 3), subsets.get(0));

		arr = Utilities.constructArray(1, 2, 3);
		sum = 2;
		subsets = BacktrackingAlgorithms.generateSubsetsWithGivenSumForSortedArray(arr, sum);
		assertNotNull(subsets);
		assertEquals(1, subsets.size());
		QuetzalAssertions.assertSetEquals(Utilities.constructSet(2), subsets.get(0));

		arr = Utilities.constructArray(15, 22, 14, 26, 32, 9, 16, 8);
		Arrays.sort(arr);
		sum = 53;
		subsets = BacktrackingAlgorithms.generateSubsetsWithGivenSumForSortedArray(arr, sum);
		assertNotNull(subsets);
		assertEquals(3, subsets.size());
		QuetzalAssertions.assertSetEquals(Utilities.constructSet(8, 9, 14, 22), subsets.get(0));
		QuetzalAssertions.assertSetEquals(Utilities.constructSet(8, 14, 15, 16), subsets.get(1));
		QuetzalAssertions.assertSetEquals(Utilities.constructSet(15, 16, 22), subsets.get(2));
	}

	@Test
	void getNPrimeNumbersAfterPWithSumS() {
		Set<Set<Integer>> primes;
		Set<Set<Integer>> expectedPrimes;

		expectedPrimes = Utilities.constructSet(Utilities.constructSet(11, 17));
		primes = BacktrackingAlgorithms.getNPrimeNumbersAfterPWithSumS(7, 2, 28);
		QuetzalAssertions.assertSetEquals(expectedPrimes, primes);

		expectedPrimes = Utilities.constructSet(Utilities.constructSet(3, 7, 13),
				Utilities.constructSet(5, 7, 11));
		primes = BacktrackingAlgorithms.getNPrimeNumbersAfterPWithSumS(2, 3, 23);
		QuetzalAssertions.assertSetEquals(expectedPrimes, primes);

		expectedPrimes = Utilities.constructSet(Utilities.constructSet(7, 47),
				Utilities.constructSet(11, 43),
				Utilities.constructSet(13, 41),
				Utilities.constructSet(17, 37),
				Utilities.constructSet(23, 31));
		primes = BacktrackingAlgorithms.getNPrimeNumbersAfterPWithSumS(3, 2, 54);
		QuetzalAssertions.assertSetEquals(expectedPrimes, primes);
	}

	@Test
	void isSubStringSumString() {
		assertTrue(BacktrackingAlgorithms.isSubStringSumString("12358"));
		assertTrue(BacktrackingAlgorithms.isSubStringSumString("199100199"));
		assertFalse(BacktrackingAlgorithms.isSubStringSumString("2368"));
		assertTrue(BacktrackingAlgorithms.isSubStringSumString("12243660"));
		assertTrue(BacktrackingAlgorithms.isSubStringSumString("1111112223"));
		assertTrue(BacktrackingAlgorithms.isSubStringSumString("1212243660"));
		assertFalse(BacktrackingAlgorithms.isSubStringSumString("123456787"));
	}

	@Test
	void getAllPathsFromTopLeftToBottomRight() {
		int[][] matrix;
		List<List<Integer>> allPaths;
		List<List<Integer>> expectedPaths;

		matrix = new int[][]{{1, 2, 3},
				{4, 5, 6}};
		expectedPaths = Utilities.constructList(Utilities.constructList(1, 4, 5, 6),
				Utilities.constructList(1, 2, 5, 6),
				Utilities.constructList(1, 2, 3, 6));
		allPaths = BacktrackingAlgorithms.getAllPathsFromTopLeftToBottomRight(matrix);
		QuetzalAssertions.assertListOfListEquals(expectedPaths, allPaths);

		matrix = new int[][]{{1, 2},
				{3, 4}};
		expectedPaths = Utilities.constructList(Utilities.constructList(1, 3, 4),
				Utilities.constructList(1, 2, 4));
		allPaths = BacktrackingAlgorithms.getAllPathsFromTopLeftToBottomRight(matrix);
		QuetzalAssertions.assertListOfListEquals(expectedPaths, allPaths);

		matrix = new int[][]{{1, 2, 3},
				{4, 5, 6},
				{7, 8, 9}};
		expectedPaths = Utilities.constructList(Utilities.constructList(1, 4, 7, 8, 9),
				Utilities.constructList(1, 4, 5, 8, 9),
				Utilities.constructList(1, 4, 5, 6, 9),
				Utilities.constructList(1, 2, 5, 8, 9),
				Utilities.constructList(1, 2, 5, 6, 9),
				Utilities.constructList(1, 2, 3, 6, 9));
		allPaths = BacktrackingAlgorithms.getAllPathsFromTopLeftToBottomRight(matrix);
		QuetzalAssertions.assertListOfListEquals(expectedPaths, allPaths);
	}

	@Test
	void getLongestRouteBetweenSourceAndDestinationInAMatrixWithHurdles() {
		int[][] matrix;
		Coordinate source;
		Coordinate destination;
		List<Coordinate> longestPath;
		List<Coordinate> expectedPath;

		matrix = new int[][]{
				{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
				{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
		};
		source = new Coordinate(0, 0);
		destination = new Coordinate(1, 7);
		expectedPath = Utilities.constructList(new Coordinate(0, 0), new Coordinate(0, 1), new Coordinate(1, 1), new Coordinate(1, 0),
				new Coordinate(2, 0), new Coordinate(2, 1), new Coordinate(2, 2), new Coordinate(2, 3),
				new Coordinate(2, 4), new Coordinate(1, 4), new Coordinate(1, 3), new Coordinate(0, 3),
				new Coordinate(0, 4), new Coordinate(0, 5), new Coordinate(0, 6), new Coordinate(0, 7),
				new Coordinate(0, 8), new Coordinate(0, 9), new Coordinate(1, 9), new Coordinate(2, 9),
				new Coordinate(2, 8), new Coordinate(2, 7), new Coordinate(2, 6), new Coordinate(1, 6), new Coordinate(1, 7));
		longestPath = BacktrackingAlgorithms.getLongestRouteBetweenSourceAndDestinationInAMatrixWithHurdles(matrix, source, destination);
		QuetzalAssertions.assertListEquals(expectedPath, longestPath);
	}

	@Test
	void fillMarkedGridFromListSuchThatNoAdjacentListPairsAreAdjacentInGrid() {
		int[][] grid;
		int[] list;
		boolean solved;
		int[][] expectedGrid;

		grid = new int[][]{{0, -1, -1, 0},
				{-1, -1, -1, -1},
				{0, -1, -1, 0}};
		list = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
		expectedGrid = new int[][]{{0, 3, 5, 0},
				{7, 1, 8, 2},
				{0, 4, 6, 0}};
		solved = BacktrackingAlgorithms.fillMarkedGridFromListSuchThatNoAdjacentListPairsAreAdjacentInGrid(grid, list);
		assertTrue(solved);
		QuetzalAssertions.assertTwoDimensionalArrayEquals(expectedGrid, grid);
	}
}
