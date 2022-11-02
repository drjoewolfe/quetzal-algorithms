package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostPopularVideoCreator {
    class Solution {
        public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
            List<List<String>> results = new ArrayList<>();
            if(creators == null || creators.length == 0 || ids == null || views == null || ids.length != creators.length || views.length != creators.length) {
                return results;
            }

            int n = creators.length;
            Map<String, CreatorInfo> creatorSummary = new HashMap<>();
            int maxTotalViewCount = 0;

            for(int i = 0; i < n; i++) {
                String creator = creators[i];
                String id = ids[i];
                int viewCount = views[i];

                if(!creatorSummary.containsKey(creator)) {
                    CreatorInfo info = new CreatorInfo();
                    info.creator = creator;
                    info.totalViewCount = viewCount;
                    info.maxViewCount = viewCount;
                    info.maxViewId = id;

                    creatorSummary.put(creator, info);
                    maxTotalViewCount = Math.max(maxTotalViewCount, viewCount);
                } else {
                    CreatorInfo info = creatorSummary.get(creator);
                    info.totalViewCount += viewCount;
                    if(info.maxViewCount < viewCount) {
                        info.maxViewCount = viewCount;
                        info.maxViewId = id;
                    } else if(info.maxViewCount == viewCount
                            && info.maxViewId.compareTo(id) > 0) {
                        info.maxViewId = id;
                    }

                    maxTotalViewCount = Math.max(maxTotalViewCount, info.totalViewCount);
                }
            }

            for(var entry : creatorSummary.entrySet()) {
                CreatorInfo info = entry.getValue();
                if(info.totalViewCount != maxTotalViewCount) {
                    continue;
                }

                String creator = entry.getKey();
                List<String> creatorRecord = new ArrayList<>();
                creatorRecord.add(creator);
                creatorRecord.add(info.maxViewId);

                results.add(creatorRecord);
            }

            return results;
        }

        private class CreatorInfo {
            String creator;
            int totalViewCount;
            int maxViewCount;
            String maxViewId;
        }
    }
}

//    2456. Most Popular Video Creator
//    Medium
//    You are given two string arrays creators and ids, and an integer array views, all of length n. The ith video on a platform was created by creator[i], has an id of ids[i], and has views[i] views.
//
//    The popularity of a creator is the sum of the number of views on all of the creator's videos. Find the creator with the highest popularity and the id of their most viewed video.
//
//    If multiple creators have the highest popularity, find all of them.
//    If multiple videos have the highest view count for a creator, find the lexicographically smallest id.
//    Return a 2D array of strings answer where answer[i] = [creatori, idi] means that creatori has the highest popularity and idi is the id of their most popular video. The answer can be returned in any order.
//
//
//
//    Example 1:
//
//    Input: creators = ["alice","bob","alice","chris"], ids = ["one","two","three","four"], views = [5,10,5,4]
//    Output: [["alice","one"],["bob","two"]]
//    Explanation:
//    The popularity of alice is 5 + 5 = 10.
//    The popularity of bob is 10.
//    The popularity of chris is 4.
//    alice and bob are the most popular creators.
//    For bob, the video with the highest view count is "two".
//    For alice, the videos with the highest view count are "one" and "three". Since "one" is lexicographically smaller than "three", it is included in the answer.
//    Example 2:
//
//    Input: creators = ["alice","alice","alice"], ids = ["a","b","c"], views = [1,2,2]
//    Output: [["alice","b"]]
//    Explanation:
//    The videos with id "b" and "c" have the highest view count.
//    Since "b" is lexicographically smaller than "c", it is included in the answer.
//
//
//    Constraints:
//
//    n == creators.length == ids.length == views.length
//    1 <= n <= 105
//    1 <= creators[i].length, ids[i].length <= 5
//    creators[i] and ids[i] consist only of lowercase English letters.
//    0 <= views[i] <= 105