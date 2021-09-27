package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class UniqueEmailAddresses {
    class Solution {
        public int numUniqueEmails(String[] emails) {
            if(emails == null || emails.length == 0) {
                return 0;
            }

            if(emails.length == 1) {
                return 1;
            }

            Set<String> uniqueEmails = new HashSet<>();
            for(String mailId : emails) {
                String[] parts = mailId.split("@");
                if(parts.length != 2) {
                    continue;
                }

                String localFragment = parts[0];
                String domainFragment = parts[1];

                int pi = localFragment.indexOf("+");
                if(pi >= 0) {
                    localFragment = localFragment.substring(0, pi);
                }

                localFragment = localFragment.replace(".", "");

                String formattedMailId = localFragment + "@" + domainFragment;
                if(!uniqueEmails.contains(formattedMailId)) {
                    uniqueEmails.add(formattedMailId);
                }
            }

            return uniqueEmails.size();
        }
    }

    class Solution_Correct_2 {
        public int numUniqueEmails(String[] emails) {
            if(emails == null || emails.length == 0) {
                return 0;
            }

            Set<String> addresses = new HashSet<>();
            for(String email : emails) {
                String address = getAddress(email);
                if(address != null) {
                    addresses.add(address);
                }
            }

            return addresses.size();
        }

        private String getAddress(String email) {
            int atIndex = email.indexOf("@");
            if(atIndex < 0) {
                return null;
            }

            StringBuilder name = new StringBuilder();
            for(int i = 0; i < atIndex; i++) {
                char c = email.charAt(i);

                if(c == '+') {
                    break;
                } else if(c != '.') {
                    name.append(c);
                }
            }

            return name.toString() + email.substring(atIndex, email.length() - 1);
        }
    }
}

//    929. Unique Email Addresses
//    Easy
//    Every valid email consists of a local name and a domain name, separated by the '@' sign. Besides lowercase letters, the email may contain one or more '.' or '+'.
//
//    For example, in "alice@leetcode.com", "alice" is the local name, and "leetcode.com" is the domain name.
//    If you add periods '.' between some characters in the local name part of an email address, mail sent there will be forwarded to the same address without dots in the local name. Note that this rule does not apply to domain names.
//
//    For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.
//    If you add a plus '+' in the local name, everything after the first plus sign will be ignored. This allows certain emails to be filtered. Note that this rule does not apply to domain names.
//
//    For example, "m.y+name@email.com" will be forwarded to "my@email.com".
//    It is possible to use both of these rules at the same time.
//
//    Given an array of strings emails where we send one email to each email[i], return the number of different addresses that actually receive mails.
//
//
//
//    Example 1:
//
//    Input: emails = ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
//    Output: 2
//    Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails.
//    Example 2:
//
//    Input: emails = ["a@leetcode.com","b@leetcode.com","c@leetcode.com"]
//    Output: 3
//
//
//    Constraints:
//
//    1 <= emails.length <= 100
//    1 <= emails[i].length <= 100
//    email[i] consist of lowercase English letters, '+', '.' and '@'.
//    Each emails[i] contains exactly one '@' character.
//    All local and domain names are non-empty.
//    Local names do not start with a '+' character.