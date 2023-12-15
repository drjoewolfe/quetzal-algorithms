package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class DestinationCity {
    class Solution {
        public String destCity(List<List<String>> paths) {
            if(paths == null || paths.size() == 0) {
                return "";
            }

            HashSet<String> originators = new HashSet<>();
            Set<String> cities = new HashSet<>();
            for(var entry : paths) {
                String from = entry.get(0);
                String to = entry.get(1);

                originators.add(from);
                cities.add(from);
                cities.add(to);
            }

            for(var city : cities) {
                if(!originators.contains(city)) {
                    return city;
                }
            }

            return "";
        }
    }

    class Solution_Correct_1 {
        public String destCity(List<List<String>> paths) {
            if(paths == null || paths.size() == 0) {
                return "";
            }

            Map<String, Integer> map = new HashMap<>();
            for(List<String> p : paths) {
                if(p.size() != 2) {
                    return "";
                }

                String from = p.get(0);
                String to = p.get(1);

                map.put(from, map.getOrDefault(from, 0) - 1);
                map.put(to, map.getOrDefault(to, 0) + 1);
            }

            for(Map.Entry<String, Integer> entry : map.entrySet()) {
                if(entry.getValue() == 1) {
                    return entry.getKey();
                }
            }

            return "";
        }
    }
}

//    1436. Destination City
//    Easy
//    You are given the array paths, where paths[i] = [cityAi, cityBi] means there exists a direct path going from cityAi to cityBi. Return the destination city, that is, the city without any path outgoing to another city.
//
//    It is guaranteed that the graph of paths forms a line without any loop, therefore, there will be exactly one destination city.
//
//
//
//    Example 1:
//
//    Input: paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
//    Output: "Sao Paulo"
//    Explanation: Starting at "London" city you will reach "Sao Paulo" city which is the destination city. Your trip consist of: "London" -> "New York" -> "Lima" -> "Sao Paulo".
//    Example 2:
//
//    Input: paths = [["B","C"],["D","B"],["C","A"]]
//    Output: "A"
//    Explanation: All possible trips are:
//    "D" -> "B" -> "C" -> "A".
//    "B" -> "C" -> "A".
//    "C" -> "A".
//    "A".
//    Clearly the destination city is "A".
//    Example 3:
//
//    Input: paths = [["A","Z"]]
//    Output: "Z"
//
//
//    Constraints:
//
//    1 <= paths.length <= 100
//    paths[i].length == 2
//    1 <= cityAi.length, cityBi.length <= 10
//    cityAi != cityBi
//    All strings consist of lowercase and uppercase English letters and the space character.