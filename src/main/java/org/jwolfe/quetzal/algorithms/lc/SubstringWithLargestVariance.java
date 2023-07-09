package org.jwolfe.quetzal.algorithms.lc;

public class SubstringWithLargestVariance {
    class Solution {
        public int largestVariance(String s) {
            if(s == null || s.length() < 2) {
                return 0;
            }

            int n = s.length();
            int[] frequencies = new int[26];
            for(int i = 0; i < n; i++) {
                char c = s.charAt(i);
                frequencies[c - 'a']++;
            }

            char[] sa = s.toCharArray();

            int maxVariance = 0;
            for(int i = 0; i < 26; i++) {
                for(int j = 0; j < 26; j++) {
                    if(i == j || frequencies[i] == 0 || frequencies[j] == 0) {
                        continue;
                    }

                    char c1 = (char) ('a' + i);
                    char c2 = (char) ('a' + j);

                    int c1Count = 0;
                    int c2Count = 0;

                    int remainingC2Count = frequencies[j];

                    for(char c : sa) {
                        if(c == c1) {
                            c1Count++;
                        } else if(c == c2) {
                            c2Count++;
                            remainingC2Count--;
                        } else {
                            continue;
                        }

                        if(c2Count > 0) {
                            maxVariance = Math.max(maxVariance, c1Count - c2Count);
                        }

                        if(c1Count < c2Count && remainingC2Count > 0) {
                            c1Count = 0;
                            c2Count = 0;
                        }
                    }
                }
            }

            return maxVariance;
        }
    }

    class Solution_TLE {
        public int largestVariance(String s) {
            if(s == null || s.length() < 2) {
                return 0;
            }

            int n = s.length();
            int largestVariance = 0;
            for(int l = 1; l <= n; l++) {
                for(int i = 0; i + l <= n; i++) {
                    int j = i + l - 1;
                    int variance = getVariance(s, i, j);
                    largestVariance = Math.max(largestVariance, variance);
                }
            }

            return largestVariance;
        }

        private int getVariance(String str, int start, int end) {
            int[] frequencies = new int[26];
            for(int i = start; i <= end; i++) {
                char c = str.charAt(i);
                frequencies[c - 'a']++;
            }

            int minFrequency = Integer.MAX_VALUE;
            int maxFrequency = Integer.MIN_VALUE;
            for(int i = 0; i < 26; i++) {
                if(frequencies[i] == 0) {
                    continue;
                }

                minFrequency = Math.min(minFrequency, frequencies[i]);
                maxFrequency = Math.max(maxFrequency, frequencies[i]);
            }

            return maxFrequency - minFrequency;
        }
    }

// "aababbb"
// "abbabaaba"
// "lucmdweawziyvixyfesksmkxkbzzzqdmrmvdxeghlrlyteuhvumwppwltssrlboozoiudqegobjvnuinwoaaxbiqivtwabunqkzvjnczasvghsvrckpzelcqeloppxwmnbeocoiximllpvhahesjxznfphohoycaqsaghpoligtghoejodmhwuzjmpwkrpehheuubiespninbzfbqtiimtzbymdrxxbjzhqanmoocicqfhdrtfwjbxkgehjdqhmmjnrrgilsvyhonfmvywaejhxxgabogdqgttfiufrgpgpduwhzgmgoecwagwdvmnobiukuigphrkupqkeaphjsqhmetkgmcramydkosqqmayrdgfiokpanxznuknqpcqsbumyrxfsmmcxvherbjykpbwdzeqjgdhysauxflcdhkmlflmygylnxubaimtmsbbapfsrqdwwihubmemmhumzhmvalwkneehsxjofrcubyscgmlwfuzepmlyvpthqlvxrzcekmbemneozbtfajwkaizheoexbtdicgzmgnbytwyruexhigheujnolqafjmvtfgeduwtkisjovklsazfoslylmqjkgafbcfsawdjlyyobskeywidozxbmmapjrhqjjtoknpujwibccdotmnfxqcmhbelrireqfxmqoitciszlhecacxrpdbxeqravhrgwylhzpamvjjmghrzywpfpxjogidkkuolqscxuqgxzfmkuiagndjfhcmuwysojjmwtdrmicpnjpxonymsuwvrodwmfbtpwyxmesmkpuctrlabbknyoyueumfitkpdzsnkurzzyexeutmbqcdbmirqndghaksbpukszbkgvgswjrixuwvzsoymjuiungsnpytstwjbekzudtjxqkwkhgyophfllqvmdwvdlywtnsvlfwkesxdhdfwytgtwkgprlocjlcjqezcwpiwldnrqwyqxrgyyrkdotjhtsppwjkpecnpyarjftdbvzhdnqkqpbkwtkcfsomzwgxnwtsoslvxbwdkfvaeyxzkadctnngewqbwftphtfcdhjbwzytmrlolbgouoluyfyngtkijgwvxmjzqcapymvdssiirusnrnmuextfeosrdsudwixozufmwatfmjjumqmnprsqdrrerjkivjlnohkgckhuzbajvfjezbsivnhnexfryxghcxvetlwnjlutskdguwlsqhcuravxvfmzeycxifyjjqvbdmlmzfsuekrszqvdtmlfcytznjkplqpveqybkdmggrnyuoabxkepgbenzaufxwrmqufmnlgndjakvhbkkkzhgdoutdphnrqhtogbrpgdifgcqzheognwlgoqszqjsshaiciiwjqoxlznfgjtytrmkmypspmmsyencfxdjtzzlzgjzzoqwkzriqhvfqigezstcwcflbhyalipesdxddoyzcdskthyiasfdkgxgigirbixeaneynxedrbvfybpxxonssjylrahpkklrjgvbzllsfinxtcdkejynhxekkehqlizormtlmglsakfrketakpgsziogdgtfpwzeufejryluxjjuwfcgvbipmkrgtnfupqshysughfgnxtfgazdybvdtiqiimxibxlxhzsorqgshaauhgjszlfhaoxzfhnnfsdnsxqjmhaliuhavzqielpcqjzbzelrnruhqzxrynexubqpkhsoqrearfdxmliaiamfaorysjpuldzvuqnddmskegfmrxdgeonfhyuzhpgmghsvkvolhvrdyqvgqxshjjzrozkhkrsoktmvpkllizosqdsmybnwmybkyfqxyaeumgcubtdwtlbxuhcowgqvvrraazmeoamazjbljfzfvjmjhiifpskinydncsbcoefknvjzqinbfvgyyfjzqewxwdzivzeemqvxmjrsuxavjeqtbklezsqeas"
}

//    2272. Substring With Largest Variance
//    Hard
//    The variance of a string is defined as the largest difference between the number of occurrences of any 2 characters present in the string. Note the two characters may or may not be the same.
//
//    Given a string s consisting of lowercase English letters only, return the largest variance possible among all substrings of s.
//
//    A substring is a contiguous sequence of characters within a string.
//
//
//
//    Example 1:
//
//    Input: s = "aababbb"
//    Output: 3
//    Explanation:
//    All possible variances along with their respective substrings are listed below:
//    - Variance 0 for substrings "a", "aa", "ab", "abab", "aababb", "ba", "b", "bb", and "bbb".
//    - Variance 1 for substrings "aab", "aba", "abb", "aabab", "ababb", "aababbb", and "bab".
//    - Variance 2 for substrings "aaba", "ababbb", "abbb", and "babb".
//    - Variance 3 for substring "babbb".
//    Since the largest possible variance is 3, we return it.
//    Example 2:
//
//    Input: s = "abcde"
//    Output: 0
//    Explanation:
//    No letter occurs more than once in s, so the variance of every substring is 0.
//
//
//    Constraints:
//
//    1 <= s.length <= 104
//    s consists of lowercase English letters.