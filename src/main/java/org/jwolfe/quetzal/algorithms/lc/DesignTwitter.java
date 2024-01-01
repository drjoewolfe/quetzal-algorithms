package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class DesignTwitter {
    class Twitter {

        int time;
        Map<Integer, Set<Integer>> followeeMap;
        Map<Integer, TreeSet<Tweet>> tweets;


        /** Initialize your data structure here. */
        public Twitter() {
            time = 0;
            followeeMap = new HashMap<>();
            tweets = new HashMap<>();
        }

        /** Compose a new tweet. */
        public void postTweet(int userId, int tweetId) {
            Tweet tweet = new Tweet(userId, tweetId, time++);
            if(!tweets.containsKey(userId)) {
                tweets.put(userId, new TreeSet<>());
            }

            var set = tweets.get(userId);
            set.add(tweet);
            if(set.size() > 10) {
                set.pollLast();
            }
        }

        /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
        public List<Integer> getNewsFeed(int userId) {
            TreeSet<Tweet> feed = new TreeSet<>();
            if(tweets.containsKey(userId)) {
                feed.addAll(tweets.get(userId));
            }

            if(followeeMap.containsKey(userId)) {
                for(var followeeId : followeeMap.get(userId)) {
                    if(tweets.containsKey(followeeId)) {
                        for(var followeeTweet : tweets.get(followeeId)) {
                            feed.add(followeeTweet);

                            if(feed.size() > 10) {
                                feed.pollLast();
                            }
                        }
                    }
                }
            }

            List<Integer> list = new ArrayList<>();
            for(var tweet : feed) {
                list.add(tweet.tweetId);
            }

            return list;
        }

        /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
        public void follow(int followerId, int followeeId) {
            if(!followeeMap.containsKey(followerId)) {
                followeeMap.put(followerId, new HashSet<>());
            }

            followeeMap.get(followerId).add(followeeId);
        }

        /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
        public void unfollow(int followerId, int followeeId) {
            if(followeeMap.containsKey(followerId) && followeeMap.get(followerId).contains(followeeId)) {
                followeeMap.get(followerId).remove(followeeId);
            }
        }

        private class Tweet implements Comparable<Tweet> {
            int userId;
            int tweetId;
            int time;

            public Tweet(int userId, int tweetId, int time) {
                this.userId = userId;
                this.tweetId = tweetId;
                this.time = time;
            }

            @Override
            public int compareTo(Tweet other) {
                return other.time - this.time;
            }
        }
    }

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
}

//    355. Design Twitter
//    Medium
//    Design a simplified version of Twitter where users can post tweets, follow/unfollow another user, and is able to see the 10 most recent tweets in the user's news feed.
//
//    Implement the Twitter class:
//
//    Twitter() Initializes your twitter object.
//    void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId by the user userId. Each call to this function will be made with a unique tweetId.
//    List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user themself. Tweets must be ordered from most recent to least recent.
//    void follow(int followerId, int followeeId) The user with ID followerId started following the user with ID followeeId.
//    void unfollow(int followerId, int followeeId) The user with ID followerId started unfollowing the user with ID followeeId.
//
//
//    Example 1:
//
//    Input
//    ["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"]
//    [[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]]
//    Output
//    [null, null, [5], null, null, [6, 5], null, [5]]
//
//    Explanation
//    Twitter twitter = new Twitter();
//    twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
//    twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
//    twitter.follow(1, 2);    // User 1 follows user 2.
//    twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
//    twitter.getNewsFeed(1);  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
//    twitter.unfollow(1, 2);  // User 1 unfollows user 2.
//    twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.
//
//
//    Constraints:
//
//    1 <= userId, followerId, followeeId <= 500
//    0 <= tweetId <= 104
//    All the tweets have unique IDs.
//    At most 3 * 104 calls will be made to postTweet, getNewsFeed, follow, and unfollow.