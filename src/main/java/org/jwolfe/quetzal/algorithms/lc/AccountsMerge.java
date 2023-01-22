package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class AccountsMerge {
    class Solution {
        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            List<List<String>> mergedAccounts = new ArrayList<>();
            if(accounts == null || accounts.size() == 0) {
                return mergedAccounts;
            }

            int n = accounts.size();

            Map<String, Set<Integer>> map = new HashMap<>();
            for(int i = 0; i < accounts.size(); i++) {
                var accountInfo = accounts.get(i);
                for(int j = 1; j < accountInfo.size(); j++) {
                    String email = accountInfo.get(j);

                    if(!map.containsKey(email)) {
                        map.put(email, new HashSet<>());
                    }

                    map.get(email).add(i);
                }
            }

            DisjointSets disjointSets = new DisjointSets(n);
            for(var entry : map.entrySet()) {
                var indexes = entry.getValue();
                int u = indexes.iterator().next();

                for(var v : indexes) {
                    disjointSets.union(u, v);
                }
            }

            List<Set<Integer>> sets = disjointSets.getDisjointSets();
            for(var set : sets) {
                List<String> mergedAccount = new ArrayList<>();
                mergedAccounts.add(mergedAccount);

                Set<String> mergedEmails = new TreeSet<>();
                for(var index : set) {
                    List<String> accountInfo = accounts.get(index);
                    for(int i = 1; i < accountInfo.size(); i++) {
                        mergedEmails.add(accountInfo.get(i));
                    }
                }

                mergedAccount.add(accounts.get(set.iterator().next()).get(0));
                mergedAccount.addAll(mergedEmails);
            }

            return mergedAccounts;
        }

        private class DisjointSets {
            int[] parent;
            int[] rank;

            public DisjointSets(int n) {
                parent = new int[n];
                rank = new int[n];

                for(int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            public void union(int u, int v) {
                int up = find(u);
                int vp = find(v);

                if(rank[up] > rank[vp]) {
                    parent[vp] = up;
                } else if(rank[up] < rank[vp]) {
                    parent[up] = vp;
                } else {
                    parent[up] = vp;
                    rank[vp]++;
                }
            }

            public int find(int u) {
                if(u != parent[u]) {
                    parent[u] = find(parent[u]);
                }

                return parent[u];
            }

            public List<Set<Integer>> getDisjointSets() {
                Map<Integer, Set<Integer>> map = new HashMap<>();
                for(int i = 0; i < parent.length; i++) {
                    int ip = find(i);
                    if(!map.containsKey(ip)) {
                        map.put(ip, new HashSet<>());
                    }

                    map.get(ip).add(i);
                }

                List<Set<Integer>> sets = new ArrayList<>();
                for(var entry : map.entrySet()) {
                    sets.add(entry.getValue());
                }

                return sets;
            }
        }
    }

    class Solution_InCorrect_1 {
        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            List<List<String>> mergedAccounts = new ArrayList<>();
            if(accounts == null || accounts.size() == 0) {
                return mergedAccounts;
            }

            Map<String, Account> map = new HashMap<>();
            Set<Account> set = new HashSet<>();

            for(int id = 0; id < accounts.size(); id++) {
                var accountDetails = accounts.get(id);

                Account account = null;
                for(int i = 1; i < accountDetails.size(); i++) {
                    String email = accountDetails.get(i);

                    if(map.containsKey(email)) {
                        if(account == null) {
                            account = map.get(email);
                        } else if(!account.equals(map.get(email))) {
                            Account duplicateAccount = map.get(email);
                            account.merge(duplicateAccount);
                            map.put(email, account);
                            set.remove(duplicateAccount);
                        }
                    }
                }

                if(account == null) {
                    account = new Account(id, accountDetails);
                    for(int i = 1; i < accountDetails.size(); i++) {
                        String email = accountDetails.get(i);
                        map.put(email, account);
                    }

                    set.add(account);
                } else {
                    account.merge(accountDetails);
                }
            }

            for(var account : set) {
                List<String> accountDetails = new ArrayList<>();
                accountDetails.add(account.name);
                for(String email : account.emails) {
                    accountDetails.add(email);
                }

                mergedAccounts.add(accountDetails);
            }

            return mergedAccounts;
        }

        private class Account {
            int id;
            String name;
            Set<String> emails;

            public Account() {
                emails = new TreeSet<>();
            }

            public Account(int id, List<String> accountDetails) {
                this();

                this.id = id;
                this.name = accountDetails.get(0);

                for(int i = 1; i < accountDetails.size(); i++) {
                    this.emails.add(accountDetails.get(i));
                }
            }

            public void merge(List<String> accountDetails) {
                for(int i = 1; i < accountDetails.size(); i++) {
                    this.emails.add(accountDetails.get(i));
                }
            }

            public void merge(Account other) {
                this.emails.addAll(other.emails);
            }

            public boolean equals(Object o) {
                if(o instanceof Account) {
                    return ((Account) o).id == this.id;
                }

                return false;
            }

            public int hashCode() {
                return id;
            }
        }
    }

// [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]

// [["David","David0@m.co","David1@m.co"],["David","David3@m.co","David4@m.co"],["David","David4@m.co","David5@m.co"],["David","David2@m.co","David3@m.co"],["David","David1@m.co","David2@m.co"]]
}

//    721. Accounts Merge
//    Medium
//    Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
//
//    Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
//
//    After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
//
//
//
//    Example 1:
//
//    Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
//    Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
//    Explanation:
//    The first and second John's are the same person as they have the common email "johnsmith@mail.com".
//    The third John and Mary are different people as none of their email addresses are used by other accounts.
//    We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
//    ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
//    Example 2:
//
//    Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
//    Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
//
//
//    Constraints:
//
//    1 <= accounts.length <= 1000
//    2 <= accounts[i].length <= 10
//    1 <= accounts[i][j].length <= 30
//    accounts[i][0] consists of English letters.
//    accounts[i][j] (for j > 0) is a valid email.