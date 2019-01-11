package org.jwolfe.quetzal.algorithms;

import java.util.*;

public class SetAlgorithms {
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
