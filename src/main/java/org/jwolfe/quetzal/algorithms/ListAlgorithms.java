package org.jwolfe.quetzal.algorithms;

import java.util.*;

import org.jwolfe.quetzal.library.utilities.Utilities;

public class ListAlgorithms {
	public static <T> List<List<T>> getAllPermutations(List<T> list) {
		if (list == null) {
			return null;
		}

		List<List<T>> allPermutations = new ArrayList<>();
		constructAllPermutations(list, 0, list.size() - 1, allPermutations);

		return allPermutations;
	}

	private static <T> void constructAllPermutations(List<T> list, int start, int end, List<List<T>> allPermutations) {
		if (start == end) {
			var permutation = List.copyOf(list);
			allPermutations.add(permutation);
		} else {
			for (int i = start; i <= end; i++) {
				Utilities.swap(list, start, i);
				constructAllPermutations(list, start + 1, end, allPermutations);
				Utilities.swap(list, start, i);
			}
		}
	}

	public static <T> List<List<T>> getAllSubsets(List<T> list) {
		if (list == null) {
			return null;
		}

		int length = list.size();
		int subSetCount = (int) Math.pow(2, length);
		List<List<T>> allSubsets = new ArrayList<>();
		for (int i = 0; i < subSetCount; i++) {
			List<T> subSet = new ArrayList<>();
			for (int j = 0; j < length; j++) {
				if ((i & (1 << j)) > 0) {
					subSet.add(list.get(j));
				}
			}

			allSubsets.add(subSet);
		}

		return allSubsets;
	}

	public static <T> List<List<T>> getAllSubsetsSorted(List<T> list) {
		var allSubsets = getAllSubsets(list);
		Collections.sort(allSubsets, (x, y) -> x.size() - y.size());

		return allSubsets;
	}

	public static <T> Set<Set<T>> getAllSubsets(Set<T> set) {
		// Backtracking based implementation for get all subsets
		if (set == null) {
			return null;
		}

		Set<Set<T>> subsets = new HashSet<>();
		// Add the empty subset
		subsets.add(new HashSet<>());

		List<T> list = new ArrayList<>();
		list.addAll(set);

		Set<T> currentSet = new HashSet<>();
		getAllSubsets(list, list.size(), 0, currentSet, subsets);

		return subsets;
	}

	private static <T> void getAllSubsets(List<T> list, int length, int index, Set<T> currentSet, Set<Set<T>> subsets) {
		for (int i = index; i < length; i++) {
			T currentElement = list.get(i);
			currentSet.add(currentElement);
			subsets.add(new TreeSet<>(currentSet));
			getAllSubsets(list, length, i + 1, currentSet, subsets);

			currentSet.remove(currentElement);
		}
	}
}
