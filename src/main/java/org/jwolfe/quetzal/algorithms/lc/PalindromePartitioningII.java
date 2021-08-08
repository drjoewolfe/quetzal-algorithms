package org.jwolfe.quetzal.algorithms.lc;

public class PalindromePartitioningII {
    class Solution {
        public int minCut(String s) {
            if(s == null || s.length() < 2) {
                return 0;
            }

            int n = s.length();
            boolean[][] palindromeConfigurations = getPalindromeConfigurations(s, n);

            int[] dp = new int[n];
            for(int j = 1; j < n; j++) {
                if(palindromeConfigurations[0][j]) {
                    continue;
                }

                int min = Integer.MAX_VALUE;

                for(int i = j; i >= 1; i--) {
                    if(palindromeConfigurations[i][j]) {
                        // Suffix is a palindrome
                        if(dp[i - 1] < min) {
                            min = dp[i - 1];
                        }
                    }
                }

                dp[j] = 1 + min;
            }

            return dp[n - 1];
        }

        private boolean[][] getPalindromeConfigurations(String s, int n) {
            boolean[][] dp = new boolean[n][n];
            for(int i = 0; i < n; i++) {
                dp[i][i] = true;
            }

            for(int i = 0; i < n - 1; i++) {
                int j = i + 1;
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                }
            }

            for(int l = 3; l <= n; l++) {
                for(int i = 0; i + l <= n; i++) {
                    int j = i + l - 1;

                    if(s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
            }

            return dp;
        }

        private void print(int[][] arr) {
            for(int[] row : arr) {
                for(int cell : row) {
                    System.out.print(cell + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    class Solution_TLE {
        public int minCut(String s) {
            if(s == null || s.length() < 2) {
                return 0;
            }

            int n = s.length();
            boolean[][] palindromeConfigurations = getPalindromeConfigurations(s, n);

            int[][] dp = new int[n][n];

            for(int i = 0; i < n - 1; i++) {
                int j = i + 1;
                if(s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = 1;
                }
            }

            for(int l = 3; l <= n; l++) {
                for(int i = 0; i + l <= n; i++) {
                    int j = i + l - 1;

                    if(palindromeConfigurations[i][j]) {
                        continue;
                    }

                    // Try cuts
                    int min = j - i;
                    for(int k = i; k < j; k++) {
                        min = Math.min(min,
                                dp[i][k] + dp[k + 1][j]);
                    }

                    dp[i][j] = 1 + min;
                }
            }

            return dp[0][n - 1];
        }

        private boolean[][] getPalindromeConfigurations(String s, int n) {
            boolean[][] dp = new boolean[n][n];
            for(int i = 0; i < n; i++) {
                dp[i][i] = true;
            }

            for(int i = 0; i < n - 1; i++) {
                int j = i + 1;
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                }
            }

            for(int l = 3; l <= n; l++) {
                for(int i = 0; i + l <= n; i++) {
                    int j = i + l - 1;

                    if(s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
            }

            return dp;
        }

        private void print(int[][] arr) {
            for(int[] row : arr) {
                for(int cell : row) {
                    System.out.print(cell + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

// "aab"
// "fiefhgdcdcgfeibggchibffahiededbbegegdfibdbfdadfbdbceaadeceeefiheibahgececggaehbdcgebaigfacifhdbecbebfhiefchaaheiichgdbheacfbhfiaffaecicbegdgeiaiccghggdfggbebdaefcagihbdhhigdgbghbahhhdagbdaefeccfiaifffcfehfcdiiieibadcedibbedgfegibefagfccahfcbegdfdhhdgfhgbchiaieehdgdabhidhfeecgfiibediiafacagigbhchcdhbaigdcedggehhgdhedaebchcafcdehcffdiagcafcgiidhdhedgaaegdchibhdaegdfdaiiidcihifbfidechicighbcbgibadbabieaafgeagfhebfaheaeeibagdfhadifafghbfihehgcgggffgbfccgafigieadfehieafaehaggeeaaaehggffccddchibegfhdfafhadgeieggiigacbfgcagigbhbhefcadafhafdiegahbhccidbeeagcgebehheebfaechceefdiafgeddhdfcadfdafbhiifigcbddahbabbeedidhaieagheihhgffbfbiacgdaifbedaegbhigghfeiahcdieghhdabdggfcgbafgibiifdeefcbegcfcdihaeacihgdchihdadifeifdgecbchgdgdcifedacfddhhbcagaicbebbiadgbddcbagbafeadhddaeebdgdebafabghcabdhdgieiahggddigefddccfccibifgbfcdccghgceigdfdbghdihechfabhbacifgbiiiihcgifhdbhfcaiefhccibebcahidachfabicbdabibiachahggffiibbgchbidfbbhfcicfafgcagaaadbacddfiigdiiffhbbehaaacidggfbhgeaghigihggfcdcidbfccahhgaffiibbhidhdacacdfebedbiacaidaachegffaiiegeabfdgdcgdacfcfhdcbfiaaifgfaciacfghagceaaebhhibbieehhcbiggabefbeigcbhbcidbfhfcgdddgdffghidbbbfbdhcgabaagddcebaechbbiegeiggbabdhgghciheabdibefdfghbfbfebidhicdhbeghebeddgfdfhefebiiebdchifbcbahaddhbfafbbcebiigadhgcfbebgbebhfddgdeehhgdegaeedfadegfeihcgeefbbagbbacbgggciehdhiggcgaaicceeaefgcehfhfdciaghcbbgdihbhecfbgffefhgiefgeiggcebgaacefidghdfdhiabgibchdicdehahbibeddegfciaeaffgbefbbeihbafbagagedgbdadfdggfeaebaidchgdbcifhahgfdcehbahhdggcdggceiabhhafghegfdiegbcadgaecdcdddfhicabdfhbdiiceiegiedecdifhbhhfhgdbhibbdgafhgdcheefdhifgddchadbdggiidhbhegbdfdidhhfbehibiaacdfbiagcbheabaaebfeaeafbgigiefeaeheabifgcfibiddadicheahgbfhbhddaheghddceedigddhchecaghdegigbegcbfgbggdgbbigegffhcfcbbebdchffhddbfhhfgegggibhafiebcfgeaeehgdgbccbfghagfdbdfcbcigbigaccecfehcffahiafgabfcaefbghccieehhhiighcfeabffggfchfdgcfhadgidabdceediefdccceidcfbfiiaidechhbhdccccaigeegcaicabbifigcghcefaafaefd"
}

//    132. Palindrome Partitioning II
//    Hard
//    Given a string s, partition s such that every substring of the partition is a palindrome.
//
//    Return the minimum cuts needed for a palindrome partitioning of s.
//
//
//
//    Example 1:
//
//    Input: s = "aab"
//    Output: 1
//    Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
//    Example 2:
//
//    Input: s = "a"
//    Output: 0
//    Example 3:
//
//    Input: s = "ab"
//    Output: 1
//
//
//    Constraints:
//
//    1 <= s.length <= 2000
//    s consists of lower-case English letters only.