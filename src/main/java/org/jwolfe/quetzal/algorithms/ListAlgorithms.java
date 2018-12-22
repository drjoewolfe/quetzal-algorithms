package org.jwolfe.quetzal.algorithms;

import java.util.ArrayList;
import java.util.List;

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
}
