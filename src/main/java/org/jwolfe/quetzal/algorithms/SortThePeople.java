package org.jwolfe.quetzal.algorithms;

import java.util.*;

public class SortThePeople {
    class Solution {
        public String[] sortPeople(String[] names, int[] heights) {
            if(names == null || heights == null || names.length != heights.length) {
                return names;
            }

            int n = names.length;

            Map<Integer, String> map = new HashMap<>();
            for(int i = 0; i < n; i++) {
                map.put(heights[i], names[i]);
            }

            Arrays.sort(heights);
            for(int i = n - 1; i >= 0; i--) {
                names[n - i - 1] = map.get(heights[i]);
            }

            return names;
        }
    }

    class Solution_Correct_1 {
        public String[] sortPeople(String[] names, int[] heights) {
            if(names == null || heights == null || names.length != heights.length) {
                return names;
            }

            int n = names.length;

            List<Person> list = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                list.add(new Person(names[i], heights[i]));
            }

            Collections.sort(list, (a, b) -> b.height - a.height);
            String[] sorted = new String[n];
            for(int i = 0; i < n; i++) {
                sorted[i] = list.get(i).name;
            }

            return sorted;
        }

        class Person {
            String name;
            int height;

            public Person(String name, int height) {
                this.name = name;
                this.height = height;
            }
        }
    }
}

//    2418. Sort the People
//    Easy
//    You are given an array of strings names, and an array heights that consists of distinct positive integers. Both arrays are of length n.
//
//    For each index i, names[i] and heights[i] denote the name and height of the ith person.
//
//    Return names sorted in descending order by the people's heights.
//
//
//
//    Example 1:
//
//    Input: names = ["Mary","John","Emma"], heights = [180,165,170]
//    Output: ["Mary","Emma","John"]
//    Explanation: Mary is the tallest, followed by Emma and John.
//    Example 2:
//
//    Input: names = ["Alice","Bob","Bob"], heights = [155,185,150]
//    Output: ["Bob","Alice","Bob"]
//    Explanation: The first Bob is the tallest, followed by Alice and the second Bob.
//
//
//    Constraints:
//
//    n == names.length == heights.length
//    1 <= n <= 103
//    1 <= names[i].length <= 20
//    1 <= heights[i] <= 105
//    names[i] consists of lower and upper case English letters.
//    All the values of heights are distinct.