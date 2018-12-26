package org.jwolfe.quetzal.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class BacktrackingAlgorithms {
	public static List<Set<Integer>> generateSubsetsWithGivenSum(int[] arr, int targetSum) {
		if(arr == null || arr.length == 0 || targetSum == 0) {
			return null;
		}
		
		List<Set<Integer>> resultSubSets = new ArrayList<>();
		Set<Integer> currentSet = new TreeSet<>();		
		generateSubsetsWithGivenSum(arr, currentSet, 0, targetSum, 0, resultSubSets);
		
		return resultSubSets;
	}

	private static void generateSubsetsWithGivenSum(int[] arr, Set<Integer> currentSet, 
													int currentIndex, 
													int targetSum, int currentSum,  
													List<Set<Integer>> resultSubSets) {
		if(currentSum == targetSum) {		
			Set<Integer> set = new TreeSet<>(currentSet);
			resultSubSets.add(set);
			
			currentSet.remove(arr[currentIndex - 1]);
			generateSubsetsWithGivenSum(arr, currentSet, currentIndex + 1, targetSum, currentSum - arr[currentIndex - 1], resultSubSets);
			return;
		}
		else {
			for (int i = currentIndex; i < arr.length; i++) {
				currentSet.add(arr[i]);
				generateSubsetsWithGivenSum(arr, currentSet, i + 1, targetSum, currentSum + arr[i], resultSubSets);
				currentSet.remove(arr[i]);
			}
		}		
	}
}