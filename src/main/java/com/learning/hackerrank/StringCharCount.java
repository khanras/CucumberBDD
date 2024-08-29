package com.learning.hackerrank;

import java.util.*;

public class StringCharCount {
    public static void main(String[] args) {
        String dataString = "Swis";
        StringCharCount stringCharCount = new StringCharCount();
        Map<Character, Integer> charCountMap = stringCharCount.countEachCharOccurrence(dataString);
        for (char c : charCountMap.keySet()) {
            System.out.println("Character " + c + ": " + charCountMap.get(c));
        }

        Map.Entry<Character, Integer> mostFrequentChar = stringCharCount.findMostFrequentChar(charCountMap);
        System.out.println("Most frequent Char is: " + mostFrequentChar.getKey() + " Count: " + mostFrequentChar.getValue());

        Map.Entry<Character, Integer> secondMostFrequentChar = stringCharCount.findSecondMostFrequentChar(charCountMap);
        System.out.println("Second Most frequent Char is: " + secondMostFrequentChar.getKey() + " Count: " + secondMostFrequentChar.getValue());

        char firstNonRepeatedChar = stringCharCount.findFirstNonRepeatedChar(charCountMap);
        System.out.println("First Non Repeated Char is: " + firstNonRepeatedChar);
    }

    private Character findFirstNonRepeatedChar(Map<Character, Integer> charCountMap) {
        for (Map.Entry<Character, Integer> eachChar : charCountMap.entrySet()) {
            if(eachChar.getValue()==1){
                return eachChar.getKey();
            }
        }
        return null;
    }

    private Map.Entry<Character, Integer> findSecondMostFrequentChar(Map<Character, Integer> charCountMap) {
        List<Map.Entry<Character, Integer>> sortedEntries = new ArrayList<>(charCountMap.entrySet());
        sortedEntries.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));
        if (sortedEntries.size() < 2) {
            System.out.println("No second most frequent character found.");
            return null;
        } else {
            return sortedEntries.get(1);
        }
    }

    private Map.Entry<Character, Integer> findMostFrequentChar(Map<Character, Integer> charCountMap) {
        Map.Entry<Character, Integer> maxCountEntry = null;
        for (Map.Entry<Character, Integer> eachEntry : charCountMap.entrySet()) {
            if (maxCountEntry == null) {
                maxCountEntry = eachEntry;
            } else if (eachEntry.getValue().compareTo(maxCountEntry.getValue()) > 0) {
                maxCountEntry = eachEntry;
            }
        }
        return maxCountEntry;
    }

    private Map<Character, Integer> countEachCharOccurrence(String data) {
        Map<Character, Integer> charCountMap = new LinkedHashMap<>();
        for (char eachChar : data.toLowerCase().replaceAll("\\s+", "").toCharArray()) {
            charCountMap.put(eachChar, charCountMap.getOrDefault(eachChar, 0) + 1);
        }
        return charCountMap;
    }
}
