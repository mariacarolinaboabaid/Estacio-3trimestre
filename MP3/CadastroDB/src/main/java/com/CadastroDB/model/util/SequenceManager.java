package com.CadastroDB.model.util;

import java.util.HashMap;
import java.util.Map;

public class SequenceManager {
    
    private Map<String, Integer> sequences = new HashMap<>();

    public int getValue(String sequenceName) {
        sequences.putIfAbsent(sequenceName, 0);
        int value = sequences.get(sequenceName);
        sequences.put(sequenceName, value + 1);
        return value;
    }
}
