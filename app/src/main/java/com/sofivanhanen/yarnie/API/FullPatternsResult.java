package com.sofivanhanen.yarnie.API;


import com.sofivanhanen.yarnie.Data.Pattern;
import com.sofivanhanen.yarnie.Data.PatternList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sofvanh on 13/02/18.
 */

public class FullPatternsResult {

    // The getPatternsById query returns this object

    private HashMap<Integer, Pattern> patterns;

    public HashMap<Integer, Pattern> getPatterns() { return patterns; }

    public PatternList getPatternsAsList(boolean include0Yardage) {
        PatternList result = new PatternList();
        for (Pattern pattern : patterns.values()) {
            if (include0Yardage || pattern.getYardage() > 0) {
                result.add(pattern);
            }
        }
        return result;
    }

}
