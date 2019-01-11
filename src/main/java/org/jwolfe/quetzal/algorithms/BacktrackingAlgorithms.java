package org.jwolfe.quetzal.algorithms;

import java.util.*;

public class BacktrackingAlgorithms {
	public static List<Set<Integer>> generateSubsetsWithGivenSum(int[] arr, int targetSum) {
		if (arr == null || arr.length == 0 || targetSum == 0) {
			return null;
		}

		List<Set<Integer>> resultSubSets = new ArrayList<>();
		Set<Integer> currentSet = new TreeSet<>();
		generateSubsetsWithGivenSum(arr, currentSet, 0, targetSum, 0, resultSubSets);

		return resultSubSets;
	}

	private static void generateSubsetsWithGivenSum(int[] arr, Set<Integer> currentSet, int currentIndex, int targetSum,
													int currentSum, List<Set<Integer>> resultSubSets) {
		if (currentSum == targetSum) {
			Set<Integer> set = new TreeSet<>(currentSet);
			resultSubSets.add(set);

			currentSet.remove(arr[currentIndex - 1]);
			generateSubsetsWithGivenSum(arr, currentSet, currentIndex + 1, targetSum,
					currentSum - arr[currentIndex - 1], resultSubSets);
			return;
		} else {
			for (int i = currentIndex; i < arr.length; i++) {
				currentSet.add(arr[i]);
				generateSubsetsWithGivenSum(arr, currentSet, i + 1, targetSum, currentSum + arr[i], resultSubSets);
				currentSet.remove(arr[i]);
			}
		}
	}

	public static List<Set<Integer>> generateSubsetsWithGivenSumForSortedArray(int[] arr, int targetSum) {
		if (arr == null || arr.length == 0 || targetSum == 0) {
			return null;
		}

		List<Set<Integer>> resultSubSets = new ArrayList<>();

		// See if total is less than the requested sum.
		int total = 0;
		for (int i = 0; i < arr.length; i++) {
			total += arr[i];
		}
		if (total < targetSum) {
			return resultSubSets;
		}

		// Find subsets if any.
		Set<Integer> currentSet = new TreeSet<>();
		generateSubsetsWithGivenSumForSortedArray(arr, currentSet, 0, targetSum, 0, resultSubSets);

		return resultSubSets;
	}

	private static void generateSubsetsWithGivenSumForSortedArray(int[] arr, Set<Integer> currentSet, int currentIndex,
																  int targetSum, int currentSum, List<Set<Integer>> resultSubSets) {
		if (currentSum == targetSum) {
			Set<Integer> set = new TreeSet<>(currentSet);
			resultSubSets.add(set);

			currentSet.remove(arr[currentIndex - 1]);
			if (currentIndex + 1 >= arr.length
					|| currentSum - arr[currentIndex - 1] + arr[currentIndex + 1] > targetSum) {
				return;
			}

			generateSubsetsWithGivenSum(arr, currentSet, currentIndex + 1, targetSum,
					currentSum - arr[currentIndex - 1], resultSubSets);
			return;
		} else {
			if (currentIndex >= arr.length || currentSum + arr[currentIndex] > targetSum) {
				return;
			}

			for (int i = currentIndex; i < arr.length; i++) {
				if (currentSum + arr[i] > targetSum) {
					continue;
				}

				currentSet.add(arr[i]);
				generateSubsetsWithGivenSumForSortedArray(arr, currentSet, i + 1, targetSum, currentSum + arr[i],
						resultSubSets);
				currentSet.remove(arr[i]);
			}
		}
	}

	public static Set<Set<Integer>> getNPrimeNumbersAfterPWithSumS(int p, int n, int s) {
		// Get all primes between p & s
		List<Integer> primes = new ArrayList<>();
		for (int i = p + 1; i <= s; i++) {
			if (NumberAlgorithms.isPrime(i)) {
				primes.add(i);
			}
		}

		return getSubsetsOfLengthNAddingToSum(primes, n, s);
	}

	public static Set<Set<Integer>> getSubsetsOfLengthNAddingToSum(List<Integer> set, int length, int sum) {
		Set<Set<Integer>> subSets = new HashSet<>();
		Set<Integer> currentSet = new HashSet<>();

		generateSubsetsOfLengthNAddingToSum(set, length, sum, 0, currentSet, subSets);
		return subSets;
	}

	private static void generateSubsetsOfLengthNAddingToSum(List<Integer> set, int length, int sum, int currentIndex,
															Set<Integer> currentSet, Set<Set<Integer>> subsets) {
		if (currentSet.size() == length) {
			int subSetSum = currentSet.stream().mapToInt(i -> i).sum();
			if (subSetSum == sum) {
				subsets.add(new HashSet<>(currentSet));
			}

			return;
		}

		if (currentIndex >= set.size()) {
			return;
		}

		int currentNumber = set.get(currentIndex);

		// Option 1: Include current number
		currentSet.add(currentNumber);
		generateSubsetsOfLengthNAddingToSum(set, length, sum, currentIndex + 1, currentSet, subsets);

		// Option 2: Exclude current number
		currentSet.remove(currentNumber);
		generateSubsetsOfLengthNAddingToSum(set, length, sum, currentIndex + 1, currentSet, subsets);
	}

	public static boolean isSubStringSumString(String str) {
		// A string has substring sum (ex: 12358 -> (1+2=3), (2+3=5) & (3+5=0)) if.
		// i) length of the string is at least 3
		// ii) sub-str(i, x) + sub-str(x+1, j) = sub-str(j+1, l);
		// iii) (ii) repeatedly holds for sub-str(x+1, j) + sub-str(j+1, l) =
		// sub-str(l+1, m) for the entire string

		if (str == null || str.length() < 3) {
			return false;
		}

		int n = str.length();
		for (int len1 = 1; len1 < n - 1; len1++) {
			for (int len2 = 1; len2 + len1 <= n; len2++) {
				if (isSubStringSumString(str, 0, len1, len2)) {
					return true;
				}
			}
		}

		return false;
	}

	private static boolean isSubStringSumString(String str, int startIndex, int len1, int len2) {
		int length = str.length();
		int currentLength = startIndex + len1 + len2;

		String str1 = str.substring(startIndex, startIndex + len1); // (i, x)
		String str2 = str.substring(startIndex + len1, currentLength); // (x+1, j)
		String str3 = StringAlgorithms.getSumString(str1, str2); // (j+1, l)

		int len3 = str3.length();
		if (len3 > (length - currentLength)) {
			return false;
		}

		String actualStr3 = str.substring(currentLength, currentLength + len3);
		if (str3.equals(actualStr3)) {
			if (len3 == length - currentLength) {
				// Finished parsing the string for sum-string properties successfully
				return true;
			}

			return isSubStringSumString(str, startIndex + len1, len2, len3);
		}

		return false;
	}

	public static List<List<Integer>> getAllPathsFromTopLeftToBottomRight(int[][] matrix) {
		// From each cell, we can move right, or down

		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return null;
		}

		int rowLength = matrix.length;
		int columnLength = matrix[0].length;
		for (var row : matrix) {
			if (row.length != columnLength) {
				return null;
			}
		}

		List<List<Integer>> allPaths = new ArrayList<>();
		List<Integer> currentPath = new ArrayList<>();

		generateAllPathsFromTopLeftToBottomRight(matrix, rowLength, columnLength, 0, 0, currentPath, allPaths);
		return allPaths;
	}

	private static void generateAllPathsFromTopLeftToBottomRight(int[][] matrix, int rowLength, int columnLength,
																 int row, int column,
																 List<Integer> currentPath, List<List<Integer>> allPaths) {

		if (row < 0 || row >= rowLength || column < 0 || column >= columnLength) {
			// Outside the bounds of the matrix
			return;
		}

		int cell = matrix[row][column];
		currentPath.add(cell);
		int index = currentPath.size() - 1;

		if (row == rowLength - 1 && column == columnLength - 1) {
			// Reached bottom right
			allPaths.add(new ArrayList<>(currentPath));
			currentPath.remove(index);
			return;
		}

		// Try down
		generateAllPathsFromTopLeftToBottomRight(matrix, rowLength, columnLength, row + 1, column, currentPath, allPaths);

		// Try right
		generateAllPathsFromTopLeftToBottomRight(matrix, rowLength, columnLength, row, column + 1, currentPath, allPaths);

		currentPath.remove(index);
	}
}