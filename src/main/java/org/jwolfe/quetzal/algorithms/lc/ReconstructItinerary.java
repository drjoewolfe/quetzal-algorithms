package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class ReconstructItinerary {
    class Solution {
        public List<String> findItinerary(List<List<String>> tickets) {
            List<String> itinerary = new ArrayList<>();
            if(tickets == null || tickets.size() == 0) {
                return itinerary;
            }

            Map<String, PriorityQueue<String>> graph = new HashMap<>();
            for(var ticket : tickets) {
                String from = ticket.get(0);
                String to = ticket.get(1);

                if(!graph.containsKey(from)) {
                    graph.put(from, new PriorityQueue<>());
                }

                graph.get(from).offer(to);
            }

            String departure = "JFK";
            dfs(departure, graph, itinerary);

            return itinerary;
        }

        private void dfs(String departure, Map<String, PriorityQueue<String>> graph, List<String> itinerary) {
            var arrivals = graph.get(departure);
            while(arrivals != null && arrivals.size() > 0) {
                dfs(arrivals.poll(), graph, itinerary);
            }

            itinerary.add(0, departure);
        }
    }

    class Solution_Correct_1 {
        public List<String> findItinerary(List<List<String>> tickets) {
            List<String> itinerary = new ArrayList<>();
            if(tickets == null || tickets.size() == 0) {
                return itinerary;
            }

            Map<String, PriorityQueue<String>> graph = new HashMap<>();
            for(List<String> ticket : tickets) {
                if(ticket.size() != 2) {
                    return itinerary;
                }

                String from = ticket.get(0);
                String to = ticket.get(1);

                graph.putIfAbsent(from, new PriorityQueue<>());
                graph.get(from).offer(to);
            }

            String from = "JFK";
            dfs("JFK", graph, itinerary);

            return itinerary;
        }

        private void dfs(String departure, Map<String, PriorityQueue<String>> graph, List<String> itinerary) {
            PriorityQueue<String> arrivals = graph.get(departure);
            while(arrivals != null && !arrivals.isEmpty()) {
                dfs(arrivals.poll(), graph, itinerary);
            }

            itinerary.add(0, departure);
        }
    }

// [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
// [["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]
}

//    332. Reconstruct Itinerary
//    Hard
//    You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.
//
//    All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
//
//    For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
//    You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.
//
//
//
//    Example 1:
//
//
//    Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
//    Output: ["JFK","MUC","LHR","SFO","SJC"]
//    Example 2:
//
//
//    Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
//    Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
//    Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.
//
//
//    Constraints:
//
//    1 <= tickets.length <= 300
//    tickets[i].length == 2
//    fromi.length == 3
//    toi.length == 3
//    fromi and toi consist of uppercase English letters.
//    fromi != toi