package com.samagraassignment.utils;

public class Utils {

    public static Long getUnixTimeStamp()
    {
        long unixTime = System.currentTimeMillis() / 1000L;
        return unixTime;
    }
}
