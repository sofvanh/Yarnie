package com.sofivanhanen.yarnie.Utils;

import com.sofivanhanen.yarnie.Pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sofvanh on 16/02/18.
 */

public class MiscUtils {

    // Some queries return lists, others return arrays,
    // some algorithms work better on one or the other.
    // Therefore, we have these transformation methods.

    public static List<Pattern> arrayToList(Pattern[] patterns) {
        ArrayList<Pattern> list = new ArrayList<>();
        for (Pattern pattern : patterns) {
            list.add(pattern);
        }
        return list;
    }

    public static Pattern[] listToArray(List<Pattern> patterns) {
        Pattern[] array = new Pattern[patterns.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = patterns.get(i);
        }
        return array;
    }

}
