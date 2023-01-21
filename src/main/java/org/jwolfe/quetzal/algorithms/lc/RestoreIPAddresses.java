package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses {
    class Solution {
        public List<String> restoreIpAddresses(String s) {
            List<String> ipAddresses = new ArrayList<>();
            if(s == null || s.length() < 3) {
                return ipAddresses;
            }

            generateIpAddresses(s, 0, new ArrayList<>(), ipAddresses);
            return ipAddresses;
        }

        private void generateIpAddresses(String s, int index, List<Integer> parts, List<String> ipAddresses) {
            if(index == s.length()) {
                if(parts.size() == 4) {
                    ipAddresses.add(getIpAddress(parts));
                }

                return;
            }

            if(parts.size() == 4) {
                return;
            }

            if(s.charAt(index) == '0') {
                parts.add(0);
                generateIpAddresses(s, index + 1, parts, ipAddresses);

                parts.remove(parts.size() - 1);
            } else {
                int n = s.length();
                int part = 0;
                for(int i = index; i < Math.min(i + 3, n); i++) {
                    char c = s.charAt(i);
                    int val = c - '0';

                    part *= 10;
                    part += val;

                    if(part > 255) {
                        return;
                    }

                    parts.add(part);
                    generateIpAddresses(s, i + 1, parts, ipAddresses);

                    parts.remove(parts.size() - 1);
                }
            }
        }

        private String getIpAddress(List<Integer> parts) {
            StringBuilder builder = new StringBuilder();
            builder.append(parts.get(0)
                    + "." + parts.get(1)
                    + "." + parts.get(2)
                    + "." + parts.get(3));

            return builder.toString();
        }
    }

    class Solution_Correct_1 {
        public List<String> restoreIpAddresses(String s) {
            List<String> ipAddresses = new ArrayList<>();
            if(s == null || s.length() < 4) {
                return ipAddresses;
            }

            generateIpAddresses(s, 0, new ArrayList<>(), ipAddresses);
            return ipAddresses;
        }

        private void generateIpAddresses(String s, int index, List<Integer> parts, List<String> ipAddresses) {
            if(index == s.length()) {
                if(parts.size() != 4) {
                    return;
                }

                // valid IP address
                StringBuilder builder = new StringBuilder();
                builder.append(
                        Integer.toString(parts.get(0))
                                + "." + Integer.toString(parts.get(1))
                                + "." + Integer.toString(parts.get(2))
                                + "." + Integer.toString(parts.get(3)));

                ipAddresses.add(builder.toString());
                return;
            }

            int n = s.length();
            if(parts.size() == 4 || (parts.size() == 3 && n - index > 3)) {
                return;
            }

            if(s.charAt(index) == '0') {
                parts.add(0);
                generateIpAddresses(s, index + 1, parts, ipAddresses);

                parts.remove(parts.size() - 1);
            } else {
                int part = 0;
                for(int i = index; i < Math.min(index + 3, n); i++) {
                    part *= 10;
                    part += (s.charAt(i) - '0');

                    if(part > 255) {
                        return;
                    }

                    parts.add(part);
                    generateIpAddresses(s, i + 1, parts, ipAddresses);

                    parts.remove(parts.size() - 1);
                }
            }
        }

        private void print(List<Integer> list) {
            for(var entry : list) {
                System.out.print(entry + ".");
            }

            System.out.println();
        }
    }

// "25525511135"
// "101023"
}

//    93. Restore IP Addresses
//    Medium
//    A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
//
//    For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
//    Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s. You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.
//
//
//
//    Example 1:
//
//    Input: s = "25525511135"
//    Output: ["255.255.11.135","255.255.111.35"]
//    Example 2:
//
//    Input: s = "0000"
//    Output: ["0.0.0.0"]
//    Example 3:
//
//    Input: s = "101023"
//    Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
//
//
//    Constraints:
//
//    1 <= s.length <= 20
//    s consists of digits only.