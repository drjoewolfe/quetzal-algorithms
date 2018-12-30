package org.jwolfe.quetzal.algorithms;

import org.jwolfe.quetzal.library.general.Activity;
import org.jwolfe.quetzal.library.utilities.ActivityFinishComparator;
import org.jwolfe.quetzal.library.general.Pair;
import org.jwolfe.quetzal.library.general.Triplet;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.*;

public class GreedyAlgorithms {

    public static List<Activity> selectActivities(int[] startTimes, int[] finishTimes) {
        int length = startTimes.length;
        if (length != finishTimes.length) {
            return null;
        }

        List<Activity> activities = new LinkedList<>();
        for (int i = 0; i < startTimes.length; i++) {
            Activity activity = new Activity(startTimes[i], finishTimes[i]);
            activities.add(activity);
        }

        return selectActivities(activities);
    }

    public static List<Activity> selectActivities(List<Activity> activities) {
        if (activities == null
                || activities.size() == 0) ;

        List<Activity> selectedActivities = new LinkedList<>();
        activities.sort(new ActivityFinishComparator());

        var previousActivity = activities.get(0);
        selectedActivities.add(previousActivity);
        for (int i = 1; i < activities.size(); i++) {
            Activity activity = activities.get(i);
            if (activity.getStart() > previousActivity.getFinish()) {
                previousActivity = activity;
                selectedActivities.add(activity);
            }
        }

        return selectedActivities;
    }

    public static double fractionalKnapSack(int knapsackWeight, List<Pair<Integer, Integer>> items) {
        // Each pair is of the form (value, weight)

        if (items == null
                || items.size() == 0) {
            return 0;
        }

        Collections.sort(items, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                return (p2.getFirst() / p2.getSecond()) - (p1.getFirst() / p1.getSecond());
            }
        });

        double value = 0;
        int weight = 0;
        for (int i = 0; i < items.size(); i++) {
            var item = items.get(i);
            if (weight + item.getSecond() <= knapsackWeight) {
                weight += item.getSecond();
                value += item.getFirst();
            } else {
                int remainingWeight = knapsackWeight - weight;
                value += item.getFirst() * remainingWeight / item.getSecond();
            }
        }

        return value;
    }

    public static int assignMiceToHoles(int[] micePositions, int[] holePositions) {
        // Returns the last time (in minutes) that a mouse enters a hole.
        // At any moment, a mouse can enter a hole, move left one unit, or move right one unit in a minute.
        if (micePositions == null || holePositions == null
                || micePositions.length == 0
                || micePositions.length != holePositions.length) {
            return -1;
        }


        Arrays.sort(micePositions);
        Arrays.sort(holePositions);

        int maxTime = 0;
        for (int i = 0; i < micePositions.length; i++) {
            maxTime = Math.max(maxTime, Math.abs(micePositions[i] - holePositions[i]));
        }

        return maxTime;
    }

    public static int getThievesCaughtByPolice(char[] arr, int k) {
        // Array contains 'P' or 'T'
        // A policeman ('P') can catch one thief ('T') and only if T is at a distance of utmost k
        // Returns the maximum number of theives that can be caught

        List<Integer> policePositions = new ArrayList<>();
        List<Integer> thiefPosititions = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'P') {
                policePositions.add(i);
            } else if (arr[i] == 'T') {
                thiefPosititions.add(i);
            }
        }

        int policeIndex = 0;
        int thiefIndex = 0;
        int thievesCaught = 0;

        while (policeIndex < policePositions.size()
                && thiefIndex < thiefPosititions.size()) {
            int policePosition = policePositions.get(policeIndex);
            int theifPosition = thiefPosititions.get(thiefIndex);

            if (Math.abs(policePosition - theifPosition) <= k) {
                thievesCaught++;
                policeIndex++;
                thiefIndex++;
            } else if (policePosition < theifPosition) {
                policePosition++;
            } else {
                theifPosition++;
            }
        }

        return thievesCaught;
    }

    public static Triplet<Integer, Integer, Integer> fitWallWithShelvesForMinimumRemainingWidth(int wallWidth, int longerShelfWidth, int shorterShelfWidth) {
        // Result is of the format (# longer shelves, # shorter shelves, remaining width in wall)
        // Objective is minimum remaining width. However, longer shelves are preferred over shorter ones

        int numLongerShelves = 0;
        int numShorterShelves = 0;
        int minRemainingWidth = wallWidth;

        int longShelfCounter = 0;
        int shortShelfCounter = 0;
        int remainingWidth = 0;

        while (wallWidth >= longerShelfWidth) {
            longShelfCounter++;
            wallWidth -= longerShelfWidth;

            shortShelfCounter = wallWidth / shorterShelfWidth;
            remainingWidth = wallWidth % shorterShelfWidth;

            if (remainingWidth <= minRemainingWidth) {
                numLongerShelves = longShelfCounter;
                numShorterShelves = shortShelfCounter;
                minRemainingWidth = remainingWidth;
            }

        }

        return new Triplet<>(numLongerShelves, numShorterShelves, minRemainingWidth);
    }

    public static Set<Set<Integer>> getSubsetCover(Set<Integer> universe, Map<Set<Integer>, Integer> setCosts) {
        // Greedy implementation. Approximation, not optimal.
        if (universe == null || universe.size() == 0
                || setCosts == null || setCosts.size() == 0) {
            return null;
        }

        Set<Set<Integer>> cover = new HashSet<>();
        Set<Integer> incudedNumbers = new HashSet<>();

        while (incudedNumbers.size() < universe.size()) {
            double minCostRatio = Double.MAX_VALUE;
            Set<Integer> selectedSubset = null;
            for (var subSet : setCosts.keySet()) {
                if (cover.contains(subSet)) {
                    // Already in cover
                    continue;
                }

                Set<Integer> uniqueNumbers = Utilities.getSetDifference(subSet, incudedNumbers);

                int cost = setCosts.get(subSet);
                double costRatio = 1.0 * cost / uniqueNumbers.size();

                if (costRatio < minCostRatio) {
                    minCostRatio = costRatio;
                    selectedSubset = subSet;
                }
            }

            cover.add(selectedSubset);
            incudedNumbers.addAll(selectedSubset);
        }

        return cover;
    }

    public static int getMinimumSwapsForBracketBalancing(String str) {
        // O(n) time, O(1) space complexity.
        if (str == null || str.length() == 0) {
            return 0;
        }

        int swapsRequired = 0;
        int openCount = 0;
        int closeCount = 0;
        int balance = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == '[') {
                openCount++;

                if (balance > 0) {
                    // Current open helps to reduce balance difference by 1.
                    swapsRequired += balance;
                    balance--;
                }

            } else if (c == ']') {
                closeCount++;

                balance = (closeCount - openCount);
            }
        }

        return swapsRequired;
    }

    public static int getMinimumSwapsForBracketBalancingA2(String str) {
        // O(n) time, O(n) space complexity. Better than brute, which is O(n^2) time

        if (str == null || str.length() == 0) {
            return 0;
        }

        List<Integer> openPositions = new ArrayList<Integer>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '[') {
                openPositions.add(i);
            }
        }

        int counter = 0;
        int openCounterIndex = 0;
        int swapsRequired = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '[') {
                counter++;
                openCounterIndex++;
            } else if (c == ']') {
                counter--;
            }

            if (counter < 0) {
                // Encountered imbalance
                int nextOpen = openPositions.get(openCounterIndex);
                swapsRequired += (nextOpen - i);
                str = Utilities.swap(str, i, nextOpen);
                openCounterIndex++;

                counter = 1;
            }
        }

        return swapsRequired;
    }

    public static int getMaxProductFromSubset(int[] arr) {
        // Note the below for the maximum product
        //  1) Only positive numbers in array - max product is product of all numbers
        //  2) Positive & 0 numbers in array - max product is product of all positive numbers
        //  3) Even number of negatives, no 0 & rest positive - product of all numbers
        //  4) Odd number of negatives, no 0 & rest positive - product of all numbers except the largest negative
        //  5) Even negatives, 0, positive - product of all non-zero numbers
        //  6) Odd negatives, 0, positive - product of all numbers except zero & the largest negative
        //  7) Even negative, 0 - product of all non-zero numbers
        //  8) One negative, 0 - zero
        //  9) Odd negative, 0 - product of all non-zero numbers except the largest negative
        //  10) All zeros, 0

        int length = arr.length;
        int numNegatives = 0;
        int numZeros = 0;
        int maxNegative = Integer.MIN_VALUE;
        int maxProduct = 1;

        for (int i = 0; i < arr.length; i++) {
            int n = arr[i];

            if (n == 0) {
                numZeros++;
                continue;
            }

            maxProduct *= n;

            if (n < 0) {
                numNegatives++;
                maxNegative = Math.max(maxNegative, n);
            }
        }

        if(numZeros == length) {
            // All zeros
            maxProduct = 0;
        }
        else if(numNegatives % 2 > 0) {
            // Odd number of negatives
            if(numNegatives == 1 && numZeros == length - 1) {
                maxProduct = 0;
            }
            else {
                maxProduct /= maxNegative;
            }
        }

        return maxProduct;
    }
}
