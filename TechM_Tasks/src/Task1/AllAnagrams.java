package Task1;

import java.util.*;

public class AllAnagrams {
    
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        
        // Base case: If the string s is smaller than p, no anagrams are possible
        if (s.length() < p.length()) {
            return result;
        }
        
        // Frequency map for string p
        Map<Character, Integer> pMap = new HashMap<>();
        for (char c : p.toCharArray()) {
            pMap.put(c, pMap.getOrDefault(c, 0) + 1);
        }
        
        // Sliding window map for string s
        Map<Character, Integer> sMap = new HashMap<>();
        
        // Set up initial window
        for (int i = 0; i < p.length(); i++) {
            sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);
        }
        
        // Check if the initial window is an anagram
        if (sMap.equals(pMap)) {
            result.add(0);
        }
        
        for (int i = p.length(); i < s.length(); i++) {
            // Add the new character to the window
            sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);
            
            // Remove the old character from the window
            char leftChar = s.charAt(i - p.length());
            sMap.put(leftChar, sMap.get(leftChar) - 1);
            if (sMap.get(leftChar) == 0) {
                sMap.remove(leftChar);
            }
            
            // If the frequency maps are equal, we've found an anagram
            if (sMap.equals(pMap)) {
                result.add(i - p.length() + 1);
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in); 
        String s = sc.next();
        String p = sc.next();
        
        List<Integer> result = findAnagrams(s, p);
        
        System.out.println("Anagram indices: " + result); 
    }
}

