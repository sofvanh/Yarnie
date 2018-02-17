package com.sofivanhanen.yarnie.API;


import com.sofivanhanen.yarnie.Pattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sofvanh on 13/02/18.
 */

public class FullPatternsResult {

    // The getPatternsById query returns this object

    private HashMap<Integer, Pattern> patterns;

    public HashMap<Integer, Pattern> getPatterns() { return patterns; }

    public List<Pattern> getPatternsAsList(boolean include0Yardage) {
        ArrayList<Pattern> result = new ArrayList<>();
        for (Pattern pattern : patterns.values()) {
            if (include0Yardage || pattern.getYardage() > 0) {
                result.add(pattern);
            }
        }
        return result;
    }

}
