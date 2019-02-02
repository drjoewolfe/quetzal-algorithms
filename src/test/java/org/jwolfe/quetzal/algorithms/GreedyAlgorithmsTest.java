package org.jwolfe.quetzal.algorithms;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.general.Activity;
import org.jwolfe.quetzal.library.general.Pair;
import org.jwolfe.quetzal.library.general.Triplet;
import org.jwolfe.quetzal.library.utilities.Utilities;
import org.jwolfe.quetzal.test.QuetzalAssertions;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GreedyAlgorithmsTest {

    @Test
    void selectActivities() {
        int[] startTimes = {1, 3, 0, 5, 8, 5};
        int[] finishTimes = {2, 4, 6, 7, 9, 9};

        var selectedActivities = GreedyAlgorithms.selectActivities(startTimes, finishTimes);
        for (int i = 0; i < selectedActivities.size(); i++) {
            Activity activity = selectedActivities.get(i);
            System.out.println(i + ": " + activity.getStart() + ", " + activity.getFinish());
        }
    }

    @Test
    void fractionalKnapSack() {
        List<Pair<Integer, Integer>> items = new LinkedList<>();
        items.add(new Pair<>(60, 10));
        items.add(new Pair<>(100, 20));
        items.add(new Pair<>(120, 30));

        double maxValue = GreedyAlgorithms.fractionalKnapSack(50, items);
        System.out.println("Max value: " + maxValue);
        assertEquals(240, maxValue);
    }

    @Test
    void assignMiceToHoles() {
        int[] micePositions;
        int[] holePositions;
        int totalTime;

        micePositions = Utilities.constructArray(4, -4, 2);
        holePositions = Utilities.constructArray(4, 0, 5);
        totalTime = GreedyAlgorithms.assignMiceToHoles(micePositions, holePositions);
        assertEquals(4, totalTime);

        micePositions = Utilities.constructArray(-10, -79, -79, 67, 93, -85, -28, -94);
        holePositions = Utilities.constructArray(-2, 9, 69, 25, -31, 23, 50, 78);
        totalTime = GreedyAlgorithms.assignMiceToHoles(micePositions, holePositions);
        assertEquals(102, totalTime);
    }

    @Test
    void getThievesCaughtByPolice() {
        char[] arr;
        int theivesCaught;

        arr = Utilities.constructArray('P', 'T', 'T', 'P', 'T');
        theivesCaught = GreedyAlgorithms.getThievesCaughtByPolice(arr, 1);
        assertEquals(2, theivesCaught);

        arr = Utilities.constructArray('T', 'T', 'P', 'P', 'T', 'P');
        theivesCaught = GreedyAlgorithms.getThievesCaughtByPolice(arr, 2);
        assertEquals(3, theivesCaught);

        arr = Utilities.constructArray('P', 'T', 'P', 'T', 'T', 'P');
        theivesCaught = GreedyAlgorithms.getThievesCaughtByPolice(arr, 3);
        assertEquals(3, theivesCaught);
    }

    @Test
    void fitWallWithShelvesForMinimumRemainingWidth() {
        Triplet<Integer, Integer, Integer> result;

        result = GreedyAlgorithms.fitWallWithShelvesForMinimumRemainingWidth(24, 5, 3);
        assertNotNull(result);
        assertEquals(3, (int) result.getFirst());
        assertEquals(3, (int) result.getSecond());
        assertEquals(0, (int) result.getThird());

        result = GreedyAlgorithms.fitWallWithShelvesForMinimumRemainingWidth(29, 9, 3);
        assertNotNull(result);
        assertEquals(3, (int) result.getFirst());
        assertEquals(0, (int) result.getSecond());
        assertEquals(2, (int) result.getThird());
    }

    @Test
    void getSubsetCover() {
        Set<Integer> universe;
        Map<Set<Integer>, Integer> setCosts;
        Set<Set<Integer>> cover;
        Set<Set<Integer>> expectedCover;

        // Optimal solution
        universe = Utilities.constructSet(1, 2, 3, 4, 5);
        var setA1 = Utilities.constructSet(4, 1, 3);
        var setA2 = Utilities.constructSet(2, 5);
        var setA3 = Utilities.constructSet(1, 4, 3, 2);
        setCosts = new HashMap<>();
        setCosts.put(setA1, 5);
        setCosts.put(setA2, 10);
        setCosts.put(setA3, 3);
        expectedCover = Utilities.constructSet(setA2, setA3);
        cover = GreedyAlgorithms.getSubsetCover(universe, setCosts);
        assertNotNull(cover);
        QuetzalAssertions.assertSetEquals(expectedCover, cover);

        // Approximate solution
        universe = Utilities.constructSet(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        var setB1 = Utilities.constructSet(1, 2);
        var setB2 = Utilities.constructSet(2, 3, 4, 5);
        var setB3 = Utilities.constructSet(6, 7, 8, 9, 10, 11, 12, 13);
        var setB4 = Utilities.constructSet(1, 3, 5, 7, 9, 11, 13);
        var setB5 = Utilities.constructSet(2, 4, 6, 8, 10, 12, 13);
        setCosts = new HashMap<>();
        setCosts.put(setB1, 5);
        setCosts.put(setB2, 5);
        setCosts.put(setB3, 5);
        setCosts.put(setB4, 5);
        setCosts.put(setB5, 5);
        expectedCover = Utilities.constructSet(setB3, setB2, setB4);
        cover = GreedyAlgorithms.getSubsetCover(universe, setCosts);
        assertNotNull(cover);
        QuetzalAssertions.assertSetEquals(expectedCover, cover);
    }

    @Test
    void getMinimumSwapsForBracketBalancing() {
        assertEquals(2, GreedyAlgorithms.getMinimumSwapsForBracketBalancing("[]][]["));
        assertEquals(0, GreedyAlgorithms.getMinimumSwapsForBracketBalancing("[[][]]"));
    }

    @Test
    void getMinimumSwapsForBracketBalancingA2() {
        assertEquals(2, GreedyAlgorithms.getMinimumSwapsForBracketBalancingA2("[]][]["));
        assertEquals(0, GreedyAlgorithms.getMinimumSwapsForBracketBalancingA2("[[][]]"));
    }

    @Test
    void getMaxProductSubArray() {
        int[] arr;
        int product;

        arr = Utilities.constructArray(4, -2, 5);
        product = GreedyAlgorithms.getMaxProductSubArray(arr);
        assertEquals(20, product);

        arr = Utilities.constructArray(-4, -2, 3, 7, 5, 0, 1);
        product = GreedyAlgorithms.getMaxProductSubArray(arr);
        assertEquals(840, product);

        arr = Utilities.constructArray(2, 3, 4, 5);
        product = GreedyAlgorithms.getMaxProductSubArray(arr);
        assertEquals(120, product);

        arr = Utilities.constructArray(-1, -1, -2, 4, 3);
        product = GreedyAlgorithms.getMaxProductSubArray(arr);
        assertEquals(24, product);

        arr = Utilities.constructArray(-1, 0);
        product = GreedyAlgorithms.getMaxProductSubArray(arr);
        assertEquals(0, product);

        arr = Utilities.constructArray(0, 0, 0);
        product = GreedyAlgorithms.getMaxProductSubArray(arr);
        assertEquals(0, product);

        arr = Utilities.constructArray(4);
        product = GreedyAlgorithms.getMaxProductSubArray(arr);
        assertEquals(4, product);

        arr = Utilities.constructArray(4, 0);
        product = GreedyAlgorithms.getMaxProductSubArray(arr);
        assertEquals(4, product);
    }

    @Test
    void getMinProductSubArray() {
        int[] arr;
        int product;

        arr = Utilities.constructArray(4, -2, 5);
        product = GreedyAlgorithms.getMinProductSubArray(arr);
        assertEquals(-40, product);

        arr = Utilities.constructArray(-4, -2, 3, 7, 5, 0, 1);
        product = GreedyAlgorithms.getMinProductSubArray(arr);
        assertEquals(-420, product);

        arr = Utilities.constructArray(2, 3, 4, 5);
        product = GreedyAlgorithms.getMinProductSubArray(arr);
        assertEquals(2, product);

        arr = Utilities.constructArray(-1, -1, -2, 4, 3);
        product = GreedyAlgorithms.getMinProductSubArray(arr);
        assertEquals(-24, product);

        arr = Utilities.constructArray(-1, 0);
        product = GreedyAlgorithms.getMinProductSubArray(arr);
        assertEquals(-1, product);

        arr = Utilities.constructArray(0, 0, 0);
        product = GreedyAlgorithms.getMinProductSubArray(arr);
        assertEquals(0, product);

        arr = Utilities.constructArray(4);
        product = GreedyAlgorithms.getMinProductSubArray(arr);
        assertEquals(4, product);

        arr = Utilities.constructArray(4, 0);
        product = GreedyAlgorithms.getMinProductSubArray(arr);
        assertEquals(0, product);
    }

    @Test
    void scheduleJobs() {
        List<Activity> jobsToSchedule;
        List<Activity> scheduledJobs;
        List<Activity> expectedScheduledJobs;

        var j11 = new Activity("A", 4, 20);
        var j12 = new Activity("B", 1, 10);
        var j13 = new Activity("C", 1, 40);
        var j14 = new Activity("D", 1, 30);

        jobsToSchedule = Utilities.constructList(j11, j12, j13, j14);
        expectedScheduledJobs = Utilities.constructList(j13, j11);
        scheduledJobs = GreedyAlgorithms.scheduleJobs(jobsToSchedule);
        QuetzalAssertions.assertListStrictEquals(expectedScheduledJobs, scheduledJobs);

        var j21 = new Activity("A", 2, 100);
        var j22 = new Activity("B", 1, 19);
        var j23 = new Activity("C", 2, 27);
        var j24 = new Activity("D", 1, 25);
        var j25 = new Activity("E", 3, 15);

        jobsToSchedule = Utilities.constructList(j21, j22, j23, j24, j25);
        expectedScheduledJobs = Utilities.constructList(j23, j21, j25);
        scheduledJobs = GreedyAlgorithms.scheduleJobs(jobsToSchedule);
        QuetzalAssertions.assertListStrictEquals(expectedScheduledJobs, scheduledJobs);
    }

    @Test
    void scheduleJobsWithUnionFind() {
        List<Activity> jobsToSchedule;
        List<Activity> scheduledJobs;
        List<Activity> expectedScheduledJobs;

        var j11 = new Activity("A", 4, 20);
        var j12 = new Activity("B", 1, 10);
        var j13 = new Activity("C", 1, 40);
        var j14 = new Activity("D", 1, 30);

        jobsToSchedule = Utilities.constructList(j11, j12, j13, j14);
        expectedScheduledJobs = Utilities.constructList(j13, j11);
        scheduledJobs = GreedyAlgorithms.scheduleJobsWithUnionFind(jobsToSchedule);
        QuetzalAssertions.assertListStrictEquals(expectedScheduledJobs, scheduledJobs);

        var j21 = new Activity("A", 2, 100);
        var j22 = new Activity("B", 1, 19);
        var j23 = new Activity("C", 2, 27);
        var j24 = new Activity("D", 1, 25);
        var j25 = new Activity("E", 3, 15);

        jobsToSchedule = Utilities.constructList(j21, j22, j23, j24, j25);
        expectedScheduledJobs = Utilities.constructList(j21, j23, j25);
        scheduledJobs = GreedyAlgorithms.scheduleJobsWithUnionFind(jobsToSchedule);
        QuetzalAssertions.assertListStrictEquals(expectedScheduledJobs, scheduledJobs);
    }

    @Test
    void scheduleJobsToMinimizeLoss() {
        List<Activity> jobsToSchedule;
        List<Activity> scheduledJobs;
        List<Activity> expectedScheduledJobs;

        var j11 = Activity.createActivityForNumDaysAndLoss("A", 4, 3);
        var j12 = Activity.createActivityForNumDaysAndLoss("B", 1000, 1);
        var j13 = Activity.createActivityForNumDaysAndLoss("C", 2, 2);
        var j14 = Activity.createActivityForNumDaysAndLoss("D", 5, 4);

        jobsToSchedule = Utilities.constructList(j11, j12, j13, j14);
        expectedScheduledJobs = Utilities.constructList(j13, j14, j11, j12);
        scheduledJobs = GreedyAlgorithms.scheduleJobsToMinimizeLoss(jobsToSchedule);
        QuetzalAssertions.assertListStrictEquals(expectedScheduledJobs, scheduledJobs);

        var j21 = Activity.createActivityForNumDaysAndLoss("A", 2, 1);
        var j22 = Activity.createActivityForNumDaysAndLoss("B", 4, 2);
        var j23 = Activity.createActivityForNumDaysAndLoss("C", 1, 3);
        var j24 = Activity.createActivityForNumDaysAndLoss("D", 3, 5);
        var j25 = Activity.createActivityForNumDaysAndLoss("E", 2, 6);

        jobsToSchedule = Utilities.constructList(j21, j22, j23, j24, j25);
        expectedScheduledJobs = Utilities.constructList(j23, j25, j24, j21, j22);
        scheduledJobs = GreedyAlgorithms.scheduleJobsToMinimizeLoss(jobsToSchedule);
        QuetzalAssertions.assertListStrictEquals(expectedScheduledJobs, scheduledJobs);
    }

    @Test
    void getVolumeLeftAfterOptimalSequencing() {
        int[] volumeOfGoods;
        double percentDecayPerDay;

        volumeOfGoods = new int[]{3, 5, 4, 1, 2, 7, 6, 8, 9, 10};
        percentDecayPerDay = 0.10;
        assertEquals(42, Math.ceil(GreedyAlgorithms.getVolumeLeftAfterOptimalSequencing(volumeOfGoods, percentDecayPerDay)));

        volumeOfGoods = new int[]{4, 2, 151, 15, 1, 52, 12};
        percentDecayPerDay = 0.10;
        assertEquals(224, Math.ceil(GreedyAlgorithms.getVolumeLeftAfterOptimalSequencing(volumeOfGoods, percentDecayPerDay)));

        volumeOfGoods = new int[]{3, 1, 41, 52, 15, 4, 1, 63, 12};
        percentDecayPerDay = 0.20;
        assertEquals(146, Math.ceil(GreedyAlgorithms.getVolumeLeftAfterOptimalSequencing(volumeOfGoods, percentDecayPerDay)));
    }

    @Test
    void minimumDifferenceOfMaximumHeightDifferenceBetweenTowersByAlteringHeightByK() {
        int[] towerHeights;

        towerHeights = new int[]{1, 15, 10};
        assertEquals(5, GreedyAlgorithms.minimumDifferenceOfMaximumHeightDifferenceBetweenTowersByAlteringHeightByK(towerHeights, 6));

        towerHeights = new int[]{1, 5, 15, 10};
        assertEquals(8, GreedyAlgorithms.minimumDifferenceOfMaximumHeightDifferenceBetweenTowersByAlteringHeightByK(towerHeights, 3));

        towerHeights = new int[]{4, 6};
        assertEquals(2, GreedyAlgorithms.minimumDifferenceOfMaximumHeightDifferenceBetweenTowersByAlteringHeightByK(towerHeights, 10));

        towerHeights = new int[]{6, 10};
        assertEquals(2, GreedyAlgorithms.minimumDifferenceOfMaximumHeightDifferenceBetweenTowersByAlteringHeightByK(towerHeights, 3));

        towerHeights = new int[]{1, 10, 14, 14, 14, 15};
        assertEquals(5, GreedyAlgorithms.minimumDifferenceOfMaximumHeightDifferenceBetweenTowersByAlteringHeightByK(towerHeights, 6));

        towerHeights = new int[]{1, 2, 3};
        assertEquals(2, GreedyAlgorithms.minimumDifferenceOfMaximumHeightDifferenceBetweenTowersByAlteringHeightByK(towerHeights, 2));
    }

    @Test
    void minimumCostOfCuttingABoardIntoSquares() {
        int[] horizontalEdgeCuttingCosts;
        int[] verticalEdgeCuttingCosts;

        // 6 x 4 board
        horizontalEdgeCuttingCosts = new int[]{2, 1, 3, 1, 4};
        verticalEdgeCuttingCosts = new int[]{4, 1, 2};
        assertEquals(42, GreedyAlgorithms.minimumCostOfCuttingABoardIntoSquares(horizontalEdgeCuttingCosts, verticalEdgeCuttingCosts));
    }

    @Test
    void minimumRotationsToUnlockACircularLock() {
        assertEquals(8, GreedyAlgorithms.minimumRotationsToUnlockACircularLock(2345, 5432));
        assertEquals(12, GreedyAlgorithms.minimumRotationsToUnlockACircularLock(28756, 98234));
    }

    @Test
    void maximumEqualSumOf3Stacks() {
        Stack<Integer> stack1;
        Stack<Integer> stack2;
        Stack<Integer> stack3;

        stack1 = Utilities.createStack(3, 10);
        stack2 = Utilities.createStack(4, 5);
        stack3 = Utilities.createStack(2, 1);
        assertEquals(0, GreedyAlgorithms.maximumEqualSumOf3Stacks(stack1, stack2, stack3));

        stack1 = Utilities.createStack(1, 1, 1, 2, 3);
        stack2 = Utilities.createStack(2, 3, 4);
        stack3 = Utilities.createStack(1, 4, 5, 2);
        assertEquals(5, GreedyAlgorithms.maximumEqualSumOf3Stacks(stack1, stack2, stack3));
    }
}