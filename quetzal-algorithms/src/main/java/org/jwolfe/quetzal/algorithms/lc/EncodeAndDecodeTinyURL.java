package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class EncodeAndDecodeTinyURL {
    public class Codec {

        Map<String, String> codeDb = new HashMap<>();
        Map<String, String> valueDb = new HashMap<>();
        int counter = 0;

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            if(valueDb.containsKey(longUrl)) {
                return valueDb.get(longUrl);
            }

            String code = String.valueOf(++counter);
            codeDb.put(code, longUrl);
            valueDb.put(longUrl, code);

            return code;
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            if(!codeDb.containsKey(shortUrl)) {
                return "";
            }

            return codeDb.get(shortUrl);
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
}

//    535. Encode and Decode TinyURL
//    Medium
//    Note: This is a companion problem to the System Design problem: Design TinyURL.
//    TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.
//
//    Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
