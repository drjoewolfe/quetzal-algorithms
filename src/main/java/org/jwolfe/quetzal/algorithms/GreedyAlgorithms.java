package org.jwolfe.quetzal.algorithms;

import org.jwolfe.quetzal.library.general.Activity;
import org.jwolfe.quetzal.library.utilities.ActivityFinishComparator;
import org.jwolfe.quetzal.library.general.Pair;

import java.util.*;

public class GreedyAlgorithms {

    public static List<Activity> selectActivities(int[] startTimes, int[] finishTimes) {
        int length = startTimes.length;
        if(length != finishTimes.length) {
            return null;
        }

        List<Activity> activities = new LinkedList<>();
        for(int i = 0; i<startTimes.length; i++) {
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
            if(activity.getStart() > previousActivity.getFinish()) {
                previousActivity = activity;
                selectedActivities.add(activity);
            }
        }

        return selectedActivities;
    }

    public static double fractionalKnapSack(int knapsackWeight, List<Pair<Integer, Integer>> items) {
        // Each pair is of the form (value, weight)

        if(items == null
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
            if(weight + item.getSecond() <= knapsackWeight ) {
                weight += item.getSecond();
                value += item.getFirst();
            }
            else {
                int remainingWeight = knapsackWeight - weight;
                value += item.getFirst() * remainingWeight / item.getSecond();
            }
        }

        return value;
    }

    public static int assignMiceToHoles(int[] micePositions, int[] holePositions) {
        // Returns the last time (in minutes) that a mouse enters a hole.
        // At any moment, a mouse can enter a hole, move left one unit, or move right one unit in a minute.
        if(micePositions == null || holePositions == null
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
			if(arr[i]== 'P') {
				policePositions.add(i);
			} else if(arr[i]== 'T') {
				thiefPosititions.add(i);
			} 
		}
    	
    	int policeIndex = 0;
    	int thiefIndex = 0;
    	int thievesCaught = 0;
    	
    	while(policeIndex < policePositions.size()
    			&& thiefIndex < thiefPosititions.size()) {
    		int policePosition = policePositions.get(policeIndex);
    		int theifPosition = thiefPosititions.get(thiefIndex);
    		
    		if(Math.abs(policePosition - theifPosition) <=k) {
    			thievesCaught++;
    			policeIndex++;
    			thiefIndex++;
    		}
    		else if(policePosition < theifPosition) {
    			policePosition++;
    		}
    		else {
    			theifPosition++;
    		}
    	}
    	
    	return thievesCaught;
	}
}
