package com.animal.logic.markovChain.service;

import com.animal.logic.markovChain.dto.MarkovChainRequest;
import com.animal.logic.markovChain.exception.InvalidInputException;
import com.animal.logic.markovChain.exception.InvalidSizeException;
import com.animal.logic.markovChain.util.FileUploadUtil;

import java.util.*;

public class MarkovService {
    private final static String WHITESPACE = "\\s+";


    public static String textGenerator(MarkovChainRequest request) {
        if(request.getPrefixSize() <= request.getSuffixSize()) throw new InvalidSizeException("Prefix is greater or equal to the suffix");
        if(request.getPrefixSize() > request.getOutputSize()) throw new InvalidSizeException("Prefix is greater than the output size");

        String fileContents = FileUploadUtil.convertFileToString(request.getFile());
        if(fileContents.isEmpty()) throw new InvalidInputException("File is empty");

        String[] words = fileContents.split(WHITESPACE);
        Map<String, List<String>> table = getFrequencyTable(words, request.getPrefixSize(), request.getSuffixSize());
        String startValue = getRandomStartPosition(table);
        return getNewString(table, startValue, request.getSuffixSize(), request.getOutputSize());
    }

    public static Map<String, List<String>> getFrequencyTable(String[] words, int prefixSize, int suffixSize) {
        Map<String, List<String>> freqTable = new HashMap<>();

        exit:
        for(int i=0; i<words.length-prefixSize; i++) {
            StringBuilder tableKey = new StringBuilder(words[i]);
            StringBuilder tableValue = new StringBuilder();
//            Add all prefix  and suffix words
            for(int j=i+1; j<i+prefixSize; j++) tableKey.append(" ").append(words[j]);

            int k = 0;
            while(k<suffixSize) {
                int index = k + i + prefixSize;
//                if the index is greater than the words array, exit loop
                if(index >= words.length)
                    break exit;
                tableValue.append(words[index]);
                if(k != suffixSize-1) tableValue.append(" ");
                k++;
            }
//            Update the hashmap
            freqTable.computeIfAbsent(tableKey.toString(), val -> new ArrayList<>()).add(tableValue.toString());
        }
        return freqTable;
    }

//    Convert hashmap keys into an array and randomly select a key
    public static String getRandomStartPosition(Map<String, List<String>> table) {
        if(table.size() < 1) throw new InvalidInputException("File size is too small");
        Object[] tableKeys = table.keySet().toArray();
        return (String) tableKeys[(int)(Math.random() * tableKeys.length)];
    }

//    randomly select a string suffix from the arrayList
    public static String getRandomSuffix(List<String> suffixes) {
        int index = (int)(Math.random() * suffixes.size());
        return suffixes.get(index);
    }

    public static String getNewString(Map<String, List<String>> table, String prefix, int suffixSize, int newStringSize) {
        StringBuilder output = new StringBuilder(prefix);
        int outputSize = prefix.split(WHITESPACE).length;
        while(outputSize < newStringSize) {
//            if not present in the table exit
            if(table.get(prefix) == null) break;
//          get a random suffix from the table that corresponds to the prefix
            String suffix = getRandomSuffix(table.get(prefix));
            output.append(" ").append(suffix);
//            Calculate the new prefix by shifting the point suffix size places and appending the suffix
            prefix = prefix.split(WHITESPACE, suffixSize+1)[suffixSize] + " " + suffix;
//            track the length of characters in the string
            outputSize += suffix.split(WHITESPACE).length;
        }
        return output.toString();
    }
}
