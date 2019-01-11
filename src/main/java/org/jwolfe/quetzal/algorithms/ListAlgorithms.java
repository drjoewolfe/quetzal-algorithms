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

	public static List<List<Integer>> getDistinctSubsets(List<Integer> list) {
		if (list == null) {
			return null;
		}

		int length = list.size();
		int subSetCount = (int) Math.pow(2, length);
		List<List<Integer>> distinctSubsets = new ArrayList<>();
		Set<String> sets = new TreeSet<>();

		for (int i = 0; i < subSetCount; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < length; j++) {
				if ((i & (1 << j)) > 0) {
					sb.append(list.get(j) + "|");
				}
			}

			String setRepresentation = sb.toString();
			if (!sets.contains(setRepresentation)) {
				sets.add(setRepresentation);
			}
		}

		for (var setRepresentation : sets) {
			List<Integer> subSet = new ArrayList<>();
			var elements = setRepresentation.split("|");
			for (var item : elements) {
				if (item.equals("") || item.equals("|")) {
					continue;
				}

				int number = Integer.parseInt(item);
				subSet.add(number);
			}

			distinctSubsets.add(subSet);
		}

		return distinctSubsets;
	}

	public static <T> List<List<T>> getAllSubsetsSorted(List<T> list) {
		var allSubsets = getAllSubsets(list);
		Collections.sort(allSubsets, (x, y) -> x.size() - y.size());

		return allSubsets;
	}
}
