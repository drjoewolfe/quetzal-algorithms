package org.jwolfe.quetzal.algorithms;

import org.jwolfe.quetzal.library.general.Activity;
import org.jwolfe.quetzal.library.general.IntPair;
import org.jwolfe.quetzal.library.heap.IntMinHeap;
import org.jwolfe.quetzal.library.matrix.Matrix;
import org.jwolfe.quetzal.library.set.IntDisjointSet;
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
        // Universe (U) & a collection of subsets of U provided. Every subset has an associated cost.
        // Find the minumum cost subset that covers all elements of U
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

    public static int getMaxProductSubArray(int[] arr) {
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

        if (arr == null || arr.length == 0) {
            return 0;
        }

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

        if (numZeros == length) {
            // All zeros
            maxProduct = 0;
        } else if (numNegatives % 2 > 0) {
            // Odd number of negatives
            if (numNegatives == 1 && numZeros == length - 1) {
                maxProduct = 0;
            } else {
                maxProduct /= maxNegative;
            }
        }

        return maxProduct;
    }

    public static int getMinProductSubArray(int[] arr) {
        // Note the below for the minimum product
        //  1) Only positive numbers in array - min product is smallest number
        //  2) Positive & 0 numbers in array - zero
        //  3) Even number of negatives, no 0 & rest positive - product of all numbers except the largest negative
        //  4) Odd number of negatives, no 0 & rest positive - product of all numbers
        //  5) Even negatives, 0, positive - product of all non-zero numbers except the largest negative
        //  6) Odd negatives, 0, positive - product of all numbers except zero
        //  7) Even negative, 0 - product of all non-zero numbers except the largest negative
        //  8) One negative, 0 - non zero number
        //  9) Odd negative, 0 - product of all non-zero numbers
        //  10) All zeros, 0

        int length = arr.length;
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int numNegatives = 0;
        int numZeros = 0;
        int maxNegative = Integer.MIN_VALUE;
        int smallestPostive = Integer.MAX_VALUE;
        int minProduct = 1;

        for (int i = 0; i < length; i++) {
            int n = arr[i];

            if (n == 0) {
                numZeros++;
                continue;
            }

            if (n < 0) {
                numNegatives++;
                maxNegative = Math.max(maxNegative, n);
            } else {
                smallestPostive = Math.min(smallestPostive, n);
            }

            minProduct *= n;
        }

        if (numNegatives == 0 && numZeros == 0) {
            // Only positives in the array
            minProduct = smallestPostive;
        } else if (numNegatives == 0) {
            // Zero & positives in the array
            minProduct = 0;
        } else if (numNegatives % 2 == 0) {
            minProduct /= maxNegative;
        }

        return minProduct;
    }

    public static List<Activity> scheduleJobs(List<Activity> jobsToSchedule) {
        // Notes: Each job is represented by the pair (deadline, profit) in the activity
        if (jobsToSchedule == null || jobsToSchedule.size() == 1) {
            return null;
        }

        jobsToSchedule.sort((j1, j2) -> (j2.getProfit() - j1.getProfit()));

        int n = jobsToSchedule.stream().mapToInt(j -> j.getDeadline()).max().getAsInt();
        boolean[] timeSlots = new boolean[n];
        Activity[] schedule = new Activity[n];

        for (var job : jobsToSchedule) {
            for (int i = job.getDeadline() - 1; i >= 0; i--) {
                if (!timeSlots[i]) {
                    // Can slot here.
                    timeSlots[i] = true;
                    schedule[i] = job;

                    break;
                }
            }
        }

        List<Activity> scheduledJobs = new ArrayList<>();
        for (var job : schedule) {
            if (job != null) {
                scheduledJobs.add(job);
            }
        }

        return scheduledJobs;
    }

    public static List<Activity> scheduleJobsWithUnionFind(List<Activity> jobsToSchedule) {
        // Notes: Each job is represented by the pair (deadline, profit) in the activity
        if (jobsToSchedule == null || jobsToSchedule.size() == 0) {
            return null;
        }

        int n = jobsToSchedule.size();
        jobsToSchedule.sort((j1, j2) -> (j2.getProfit() - j1.getProfit()));
        int maxDeadline = jobsToSchedule.stream().mapToInt(j -> j.getDeadline()).max().getAsInt();

        List<Activity> scheduledJobs = new ArrayList<>();
        IntDisjointSet set = new IntDisjointSet(maxDeadline);
        for (int i = 0; i < n; i++) {
            var job = jobsToSchedule.get(i);
            int slot = set.find(job.getDeadline());

            if (slot > 0) {
                set.union(set.find(slot - 1), slot);
                scheduledJobs.add(job);
            }
        }

        return scheduledJobs;
    }

    public static List<Activity> scheduleJobsToMinimizeLoss(List<Activity> jobsToSchedule) {
        // Number of days for each job & Loss per day for delaying the start of a job are considered here

        if (jobsToSchedule == null || jobsToSchedule.size() == 0) {
            return null;
        }

        jobsToSchedule.sort((j1, j2) -> {
            int l1 = j1.getDailyLossDueToDelay();
            int l2 = j2.getDailyLossDueToDelay();

            int t1 = j1.getNumDaysForCompletion();
            int t2 = j2.getNumDaysForCompletion();

            return (l2 * t1) - (l1 * t2);
        });

        return jobsToSchedule;
    }

    public static double getVolumeLeftAfterOptimalSequencing(int[] volumeOfGoods, double percentDecayPerDay) {
        // One type of good can be scheduled per day (any volume of that type can be made in one day)

        if (volumeOfGoods == null || volumeOfGoods.length == 0 || percentDecayPerDay < 0 || percentDecayPerDay > 1) {
            return 0;
        }

        int n = volumeOfGoods.length;
        IntMinHeap heap = new IntMinHeap(n);
        for (int i = 0; i < n; i++) {
            heap.insert(volumeOfGoods[i]);
        }

        // Schedule based on lowest volume first
        int index = 0;
        while (!heap.isEmpty()) {
            int volume = heap.extractMin();
            volumeOfGoods[index] = volume;

            index++;
        }

        // For each item, the volume left at the end is [(1-P) ^ (N - i)] * V, where i is the day in which it was produced
        double result = 0;
        for (int i = 0; i < n; i++) {
            result += Math.pow(1 - percentDecayPerDay, n - i - 1) * volumeOfGoods[i];
        }

        return result;
    }

    public static int minimumDifferenceOfMaximumHeightDifferenceBetweenTowersByAlteringHeightByK(int[] towerHeights, int k) {
        // For every tower, we have to either increase or decrease its height by k

        if (towerHeights == null || towerHeights.length < 2 || k < 0) {
            return 0;
        }

        int n = towerHeights.length;
        Arrays.sort(towerHeights);

        int smallestTower = towerHeights[0] + k;
        int largestTower = towerHeights[n - 1] - k;

        if (smallestTower > largestTower) {
            int temp = smallestTower;
            smallestTower = largestTower;
            largestTower = temp;
        }

        for (int i = 1; i < n - 1; i++) {
            int increasedHeightTower = towerHeights[i] + k;
            int decreasedHeightTower = towerHeights[i] - k;

            if (smallestTower <= decreasedHeightTower || largestTower >= increasedHeightTower) {
                // Maximum difference is not changed
                continue;
            }

            if (largestTower - decreasedHeightTower <= increasedHeightTower - smallestTower) {
                smallestTower = decreasedHeightTower;
            } else {
                largestTower = increasedHeightTower;
            }
        }

        return Math.min(largestTower - smallestTower,
                towerHeights[n - 1] - towerHeights[0]);
    }

    public static int minimumCostOfCuttingABoardIntoSquares(int[] horizontalEdgeCuttingCosts, int[] verticalEdgeCuttingCosts) {
        // Net cost of cutting can be summed up as
        //  Sn = a1c1 + a2c2 + ... + aNcN
        //      c1, c2 etc. are the costs for the edge (horizontal or vertical)
        //      a1, a2 are the coefficients, denoting the number of units to be cut. This is dependent on previous cuts

        if (horizontalEdgeCuttingCosts == null || horizontalEdgeCuttingCosts.length == 0
                || verticalEdgeCuttingCosts == null || verticalEdgeCuttingCosts.length == 0) {
            return 0;
        }

        int m = horizontalEdgeCuttingCosts.length + 1;
        int n = verticalEdgeCuttingCosts.length + 1;

        // Greedy implementation - start with highest cost cuts
        Arrays.sort(horizontalEdgeCuttingCosts);
        Arrays.sort(verticalEdgeCuttingCosts);

        int totalCost = 0;

        int i = m - 2;
        int j = n - 2;

        int horizontalUnits = 1;
        int verticalUnits = 1;


        while (i >= 0 && j >= 0) {
            if (horizontalEdgeCuttingCosts[i] > verticalEdgeCuttingCosts[j]) {
                // Cut horizontally
                totalCost += (verticalUnits * horizontalEdgeCuttingCosts[i]);

                horizontalUnits++;
                i--;
            } else {
                // Cut vertically
                totalCost += (horizontalUnits * verticalEdgeCuttingCosts[j]);

                verticalUnits++;
                j--;
            }
        }

        while (i >= 0) {
            // Cut horizontally
            totalCost += (verticalUnits * horizontalEdgeCuttingCosts[i]);

            horizontalUnits++;
            i--;
        }

        while (j >= 0) {
            // Cut vertically
            totalCost += (horizontalUnits * verticalEdgeCuttingCosts[j]);

            verticalUnits++;
            j--;
        }

        return totalCost;
    }

    public static int minimumRotationsToUnlockACircularLock(int inputCode, int outputCode) {
        // Each of the dials in the lock can be rotated up or down. We need to take the minumum of these for each code digit
        if (inputCode < 0 || outputCode < 0) {
            return Integer.MAX_VALUE;
        }

        int rotations = 0;
        while (inputCode > 0 && outputCode > 0) {
            int inDigit = inputCode % 10;
            int outDigit = outputCode % 10;

            int difference = Math.abs(inDigit - outDigit);
            rotations += Math.min(difference, 10 - difference);

            inputCode /= 10;
            outputCode /= 10;
        }

        if (inputCode > 0 || outputCode > 0) {
            // codes are not of the same size
            return Integer.MAX_VALUE;
        }

        return rotations;
    }

    public static int maximumEqualSumOf3Stacks(Stack<Integer> stack1, Stack<Integer> stack2, Stack<Integer> stack3) {
        // We are allowed to pop from stacks to make the sum equal
        // Assumption: Stack contains only positive integers
        if (stack1 == null || stack2 == null || stack3 == null
                || stack1.size() == 0 || stack2.size() == 0 || stack3.size() == 0) {
            return 0;
        }

        int sum1 = stack1.stream().mapToInt(i -> i).sum();
        int sum2 = stack2.stream().mapToInt(i -> i).sum();
        int sum3 = stack3.stream().mapToInt(i -> i).sum();

        while (!stack1.isEmpty() && !stack2.isEmpty() && !stack3.isEmpty()) {
            if (Utilities.areEqual(sum1, sum2, sum3)) {
                return sum1;
            }

            if (sum1 > sum2 && sum1 > sum3) {
                // sum1 is largest; remove from stack 1
                sum1 -= stack1.pop();
            } else if (sum2 > sum1 && sum2 > sum3) {
                // sum2 is largest; remove from stack 1
                sum2 -= stack2.pop();
            } else {
                // sum3 is largest; remove from stack 1
                sum3 -= stack3.pop();
            }
        }

        return 0;
    }

    public static int maximumStocksThatCanBeBoughtIfXStocksCanBeBoughtOnXthDay(int[] pricesByDay, int maxAmount) {
        if (pricesByDay == null || pricesByDay.length == 0 || maxAmount <= 0) {
            return 0;
        }

        int n = pricesByDay.length;

        List<IntPair> dayPrices = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dayPrices.add(new IntPair(i + 1, pricesByDay[i]));
        }

        dayPrices.sort(Comparator.comparingInt(IntPair::getB));

        int stockCount = 0;
        for (int i = 0; i < n; i++) {
            var dayInfo = dayPrices.get(i);
            int day = dayInfo.getA();
            int cost = dayInfo.getB();

            if (cost > maxAmount) {
                // No more purchases can be made
                break;
            }

            // Get maximum number of stock on this day, upto day count
            int units = Math.min(maxAmount / cost, day);
            stockCount += units;
            maxAmount -= (units * cost);
        }

        return stockCount;
    }

    public static int getBinCountForPackingUsingOnlineNextFit(int[] itemWeights, int binCapacity) {
        // Assumption: No single item weighs more than binCapacity
        // Note: Next-Fit is 2-Approximate. The final result may be utmost 2 times the optimal

        if (itemWeights == null || itemWeights.length == 0 || binCapacity < 1) {
            return 0;
        }

        int binCount = 1;
        int remainingBinCapacity = binCapacity;

        for (int i = 0; i < itemWeights.length; i++) {
            int weight = itemWeights[i];

            if (weight > binCapacity) {
                // Invalid input
                return 0;
            }

            if (weight <= remainingBinCapacity) {
                remainingBinCapacity -= weight;
            } else {
                binCount++;
                remainingBinCapacity = binCapacity - weight;
            }
        }

        return binCount;
    }

    public static List<List<Integer>> getBinsForPackingUsingOnlineNextFit(int[] itemWeights, int binCapacity) {
        // Assumption: No single item weighs more than binCapacity
        // Note: Next-Fit is 2-Approximate. The final result may be utmost 2 times the optimal

        if (itemWeights == null || itemWeights.length == 0 || binCapacity < 1) {
            return null;
        }

        List<List<Integer>> bins = new ArrayList<>();
        List<Integer> currentBin = new ArrayList<>();
        bins.add(currentBin);

        int remainingBinCapacity = binCapacity;

        for (int i = 0; i < itemWeights.length; i++) {
            int weight = itemWeights[i];

            if (weight > binCapacity) {
                // Invalid input
                return null;
            }

            if (weight <= remainingBinCapacity) {
                currentBin.add(weight);
                remainingBinCapacity -= weight;
            } else {
                currentBin = new ArrayList<>();
                bins.add(currentBin);

                currentBin.add(weight);
                remainingBinCapacity = binCapacity - weight;
            }
        }

        return bins;
    }

    public static int getBinCountForPackingUsingOnlineFirstFit(int[] itemWeights, int binCapacity) {
        // Assumption: No single item weighs more than binCapacity
        // Note: First-Fit never uses more than 1.7M, where M is the optimal value

        if (itemWeights == null || itemWeights.length == 0 || binCapacity < 1) {
            return 0;
        }

        int n = itemWeights.length;
        int[] bins = new int[n];
        int binCount = 0;

        for (int i = 0; i < n; i++) {
            int weight = itemWeights[i];

            if (weight > binCapacity) {
                // Invalid input
                return 0;
            }

            int j;
            for (j = 0; j < binCount; j++) {
                if (weight <= bins[j]) {
                    // This bin can take the weight
                    bins[j] -= weight;
                    break;
                }
            }

            if (j == binCount) {
                // Could not fit in an existing bin
                binCount++;
                bins[j] = binCapacity - weight;
            }
        }

        return binCount;
    }

    public static List<List<Integer>> getBinsForPackingUsingOnlineFirstFit(int[] itemWeights, int binCapacity) {
        // Assumption: No single item weighs more than binCapacity
        // Note: First-Fit never uses more than 1.7M, where M is the optimal value

        if (itemWeights == null || itemWeights.length == 0 || binCapacity < 1) {
            return null;
        }

        List<List<Integer>> bins = new ArrayList<>();
        List<Integer> binSizes = new ArrayList<>();

        for (int i = 0; i < itemWeights.length; i++) {
            int weight = itemWeights[i];

            if (weight > binCapacity) {
                // Invalid input
                return null;
            }

            int j;
            for (j = 0; j < binSizes.size(); j++) {
                int binSize = binSizes.get(j);
                if (weight <= binSize) {
                    // This bin can take the weight
                    binSizes.set(j, binSize - weight);
                    bins.get(j).add(weight);
                    break;
                }
            }

            if (j == binSizes.size()) {
                // Could not fit in an existing bin
                binSizes.add(binCapacity - weight);

                bins.add(new ArrayList<>());
                bins.get(j).add(weight);
            }
        }

        return bins;
    }

    public static int getBinCountForPackingUsingOnlineBestFit(int[] itemWeights, int binCapacity) {
        // Assumption: No single item weighs more than binCapacity
        // Note: Best-Fit never uses more than 1.7M, where M is the optimal value

        if (itemWeights == null || itemWeights.length == 0 || binCapacity < 1) {
            return 0;
        }

        int n = itemWeights.length;

        int[] bins = new int[n];
        int binCount = 0;

        for (int i = 0; i < n; i++) {
            int weight = itemWeights[i];

            if (weight > binCapacity) {
                // Invalid input
                return 0;
            }

            int minSizeBinIndex = -1;
            int minSizeAvailable = Integer.MAX_VALUE;
            for (int j = 0; j < binCount; j++) {
                int remainingBinCapacity = bins[j];

                if (weight <= remainingBinCapacity) {
                    if (minSizeAvailable > remainingBinCapacity) {
                        minSizeAvailable = remainingBinCapacity;
                        minSizeBinIndex = j;
                    }
                }
            }

            if (minSizeBinIndex == -1) {
                // Could not find an appropriate bin
                binCount++;
                bins[binCount - 1] = binCapacity - weight;
            } else {
                bins[minSizeBinIndex] -= weight;
            }
        }

        return binCount;
    }

    public static List<List<Integer>> getBinsForPackingUsingOnlineBestFit(int[] itemWeights, int binCapacity) {
        // Assumption: No single item weighs more than binCapacity
        // Note: First-Fit never uses more than 1.7M, where M is the optimal value

        if (itemWeights == null || itemWeights.length == 0 || binCapacity < 1) {
            return null;
        }

        int n = itemWeights.length;

        List<List<Integer>> bins = new ArrayList<>();
        List<Integer> binSizes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int weight = itemWeights[i];

            if (weight > binCapacity) {
                // Invalid input
                return null;
            }

            int minSizeBinIndex = -1;
            int minSizeBinSpace = Integer.MAX_VALUE;

            for (int j = 0; j < binSizes.size(); j++) {
                int remainingBinCapacity = binSizes.get(j);

                if (weight <= remainingBinCapacity) {
                    if (minSizeBinSpace > remainingBinCapacity) {
                        minSizeBinSpace = remainingBinCapacity;
                        minSizeBinIndex = j;
                    }
                }
            }

            if (minSizeBinIndex == -1) {
                bins.add(new ArrayList<>());
                bins.get(bins.size() - 1).add(weight);
                binSizes.add(binCapacity - weight);
            } else {
                bins.get(minSizeBinIndex).add(weight);
                binSizes.set(minSizeBinIndex, minSizeBinSpace - weight);
            }
        }

        return bins;
    }
}