package com.sofivanhanen.yarnie;

/**
 * Created by sofvanh on 02/02/18.
 */

public class PatternsSearchResult {

    // The getPatterns query (/patterns/search.json) returns this object

    private Pattern[] patterns;
    private Object paginator;
    // TODO: Create the paginator class

    public Pattern[] getPatterns() { return patterns; }

    public Object getPaginator() { return paginator; }

}
