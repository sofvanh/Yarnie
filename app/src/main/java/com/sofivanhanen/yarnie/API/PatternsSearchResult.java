package com.sofivanhanen.yarnie.API;

import com.sofivanhanen.yarnie.Pattern;

/**
 * Created by sofvanh on 02/02/18.
 */

public class PatternsSearchResult {

    // The getPatterns query (/patterns/search.json) returns this object

    private Pattern[] patterns;
    private Object paginator;
    // TODO: Create the paginator class

    public Pattern[] getPatterns() { return patterns; }

    // The query for full version patterns requires ids in space delimited form
    public String getIdsAsString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < patterns.length; i++) {
            string.append(patterns[i].getId());
            if (i+1<patterns.length) string.append(" ");
        }
        return string.toString();
    }

    public Object getPaginator() { return paginator; }

}
