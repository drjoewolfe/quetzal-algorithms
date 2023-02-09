package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NamingACompany {
    class Solution {
        public long distinctNames(String[] ideas) {
            if(ideas == null || ideas.length < 2) {
                return 0;
            }

            long count = 0;

            List<Set<String>> suffixSets = new ArrayList<>();
            for(int i = 0; i < 26; i++) {
                suffixSets.add(new HashSet<>());
            }

            for(String idea : ideas) {
                String suffix = idea.substring(1);
                suffixSets.get(idea.charAt(0) - 'a').add(suffix);
            }

            for(int i = 0; i < 25; i++) {
                var set1 = suffixSets.get(i);
                for(int j = i + 1; j < 26; j++) {
                    var set2 = suffixSets.get(j);

                    int interSectionCount = 0;
                    for(String idea : set1) {
                        if(set2.contains(idea)) {
                            interSectionCount++;
                        }
                    }

                    count += (2 * (set1.size() - interSectionCount) * (set2.size() - interSectionCount));
                }
            }

            return count;
        }
    }

    class Solution_TLE {
        public long distinctNames(String[] ideas) {
            if(ideas == null || ideas.length < 2) {
                return 0;
            }

            long count = 0;

            Set<String> ideaSet = new HashSet<>();
            for(String idea : ideas) {
                ideaSet.add(idea);
            }

            int n = ideas.length;
            for(int i = 0; i < n - 1; i++) {
                String idea1 = ideas[i];
                for(int j = i + 1; j < n; j++) {
                    String idea2 = ideas[j];

                    String newIdea1 = idea2.charAt(0) + idea1.substring(1);
                    String newIdea2 = idea1.charAt(0) + idea2.substring(1);

                    if(!ideaSet.contains(newIdea1)
                            && !ideaSet.contains(newIdea2)) {
                        count += 2;
                    }
                }
            }

            return count;
        }
    }

// ["coffee","donuts","time","toffee"]
}

//    2306. Naming a Company
//    Hard
//    You are given an array of strings ideas that represents a list of names to be used in the process of naming a company. The process of naming a company is as follows:
//
//    Choose 2 distinct names from ideas, call them ideaA and ideaB.
//    Swap the first letters of ideaA and ideaB with each other.
//    If both of the new names are not found in the original ideas, then the name ideaA ideaB (the concatenation of ideaA and ideaB, separated by a space) is a valid company name.
//    Otherwise, it is not a valid name.
//    Return the number of distinct valid names for the company.
//
//
//
//    Example 1:
//
//    Input: ideas = ["coffee","donuts","time","toffee"]
//    Output: 6
//    Explanation: The following selections are valid:
//    - ("coffee", "donuts"): The company name created is "doffee conuts".
//    - ("donuts", "coffee"): The company name created is "conuts doffee".
//    - ("donuts", "time"): The company name created is "tonuts dime".
//    - ("donuts", "toffee"): The company name created is "tonuts doffee".
//    - ("time", "donuts"): The company name created is "dime tonuts".
//    - ("toffee", "donuts"): The company name created is "doffee tonuts".
//    Therefore, there are a total of 6 distinct company names.
//
//    The following are some examples of invalid selections:
//    - ("coffee", "time"): The name "toffee" formed after swapping already exists in the original array.
//    - ("time", "toffee"): Both names are still the same after swapping and exist in the original array.
//    - ("coffee", "toffee"): Both names formed after swapping already exist in the original array.
//    Example 2:
//
//    Input: ideas = ["lack","back"]
//    Output: 0
//    Explanation: There are no valid selections. Therefore, 0 is returned.
//
//
//    Constraints:
//
//    2 <= ideas.length <= 5 * 104
//    1 <= ideas[i].length <= 10
//    ideas[i] consists of lowercase English letters.
//    All the strings in ideas are unique.