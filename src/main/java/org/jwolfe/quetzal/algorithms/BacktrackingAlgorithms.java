package org.jwolfe.quetzal.algorithms;

import org.jwolfe.quetzal.library.general.Coordinate;
import org.jwolfe.quetzal.library.general.Pair;

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

    public static List<Coordinate> getLongestRouteBetweenSourceAndDestinationInAMatrixWithHurdles(int[][] matrix, Coordinate source, Coordinate destination) {
        // Note: In the matrix, a zero value implies a hurdle; a non-zero value implies clear.

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0 || source == null || destination == null) {
            return null;
        }

        int rowLength = matrix.length;
        int columnLength = matrix[0].length;
        for (var row : matrix) {
            if (row.length != columnLength) {
                return null;
            }
        }

        if (!source.isWithinBounds(0, 0, rowLength - 1, columnLength - 1)
                || !destination.isWithinBounds(0, 0, rowLength - 1, columnLength - 1)) {
            return null;
        }

        List<Coordinate> deltaCoordinates = new ArrayList<>();
        // Left
        deltaCoordinates.add(new Coordinate(0, -1));
        // Right
        deltaCoordinates.add(new Coordinate(0, 1));
        // Top
        deltaCoordinates.add(new Coordinate(-1, 0));
        // Bottom
        deltaCoordinates.add(new Coordinate(1, 0));

        List<Coordinate> currentPath = new ArrayList<>();
        List<Coordinate> longestRoute = new ArrayList<>();
        generateLongestRouteBetweenSourceAndDestinationInAMatrixWithHurdles(matrix, rowLength, columnLength, deltaCoordinates, source, destination, source, currentPath, longestRoute);

        return longestRoute;
    }

    private static int generateLongestRouteBetweenSourceAndDestinationInAMatrixWithHurdles(int[][] matrix, int rowLength, int columnLength, List<Coordinate> deltaCoordinates,
                                                                                           Coordinate source, Coordinate destination,
                                                                                           Coordinate current, List<Coordinate> currentPath, List<Coordinate> longestRoute) {
        if (!current.isWithinBounds(0, 0, rowLength - 1, columnLength - 1)
                || matrix[current.getX()][current.getY()] == 0) {
            // Out of bounds, or hurdle present
            return Integer.MIN_VALUE;
        }

        if (currentPath.contains(current)) {
            // Already visited
            return Integer.MIN_VALUE;
        }

        currentPath.add(current);

        if (current.equals(destination)) {
            // Reached destination
            int pathLength = currentPath.size();
            if (longestRoute.size() < pathLength) {
                longestRoute.clear();
                longestRoute.addAll(currentPath);
            }

            currentPath.remove(current);
            return pathLength;
        }

        int maxLength = Integer.MIN_VALUE;
        for (var delta : deltaCoordinates) {
            var next = current.buildNew(delta);
            int length = generateLongestRouteBetweenSourceAndDestinationInAMatrixWithHurdles(matrix, rowLength, columnLength, deltaCoordinates, source, destination, next, currentPath, longestRoute);
            maxLength = Math.max(maxLength, length);
        }

        currentPath.remove(current);

        return maxLength;
    }

    public static boolean fillMarkedGridFromListSuchThatNoAdjacentListPairsAreAdjacentInGrid(int[][] grid, int[] list) {
        // In the grid, cells marked with 0 cannot be filled. -1 implies the marked cell is empty.
        // As such, the number of marked cells should be equal to the list size.
        // Also, the list should have unique values greater than zero.
        if (grid == null || grid.length == 0 || grid[0].length == 0 || list == null || list.length == 0) {
            return false;
        }

        int rowCount = grid.length;
        int columnCount = grid[0].length;
        int markers = 0;
        for (var row : grid) {
            if (row.length != columnCount) {
                return false;
            }

            for (var cell : row) {
                if (cell == -1) {
                    markers++;
                }
            }
        }

        if (markers != list.length) {
            return false;
        }

        return fillMarkedGridFromListSuchThatNoAdjacentListPairsAreAdjacentInGrid(grid, rowCount, columnCount, new Coordinate(-1, -1), list, list.length, 0);
    }

    public static boolean fillMarkedGridFromListSuchThatNoAdjacentListPairsAreAdjacentInGrid(int[][] grid, int rowCount, int columnCount, Coordinate coordinate,
                                                                                             int[] list, int elementCount, int index) {
        if (index == elementCount) {
            // Successfully filled all markers
            return true;
        }

        var unassignedCells = getUnassignedCellsFromMarkedGrid(grid, rowCount, columnCount);
        for (int i = 0; i < unassignedCells.size(); i++) {
            var current = unassignedCells.get(i);
            if (canFillCellInMarkedGridFromListSuchThatNoAdjacentListPairsAreAdjacentInGrid(grid, rowCount, columnCount, list, index, current)) {
                grid[current.getX()][current.getY()] = list[index];
                if (fillMarkedGridFromListSuchThatNoAdjacentListPairsAreAdjacentInGrid(grid, rowCount, columnCount, current, list, elementCount, index + 1)) {
                    return true;
                }

                // Didn't work out. Backtrack
                grid[current.getX()][current.getY()] = -1;
            }
        }

        return false;
    }

    private static boolean canFillCellInMarkedGridFromListSuchThatNoAdjacentListPairsAreAdjacentInGrid(int[][] grid, int rowCount, int columnCount,
                                                                                                       int[] list, int index, Coordinate cellCoordinates) {
        int x = cellCoordinates.getX();
        int y = cellCoordinates.getY();

        if (grid[x][y] != -1) {
            // Already assigned
            return false;
        }

        if (index == 0) {
            // First item. Always allowed since we are inserting sequentially
            return true;
        }

        int previous = list[index - 1];

        // None of the adjacent cells should contain the previous value.
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (x == 0 && y == 0) {
                    continue;
                }

                int nx = x + i;
                int ny = y + j;

                if (nx >= 0 && nx < rowCount
                        && ny >= 0 && ny < columnCount
                        && grid[nx][ny] == previous) {
                    return false;
                }

            }
        }

        return true;
    }

    private static List<Coordinate> getUnassignedCellsFromMarkedGrid(int[][] grid, int rowCount, int columnCount) {
        List<Coordinate> unassignedCells = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (grid[i][j] == -1) {
                    unassignedCells.add(new Coordinate(i, j));
                }
            }
        }

        return unassignedCells;
    }
}