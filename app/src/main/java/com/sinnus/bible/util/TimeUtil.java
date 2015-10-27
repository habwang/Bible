package com.sinnus.bible.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sinnus on 2015/8/31.
 */
public class TimeUtil {
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    public static void displayCurrentTime(String tag){
        System.out.println(tag+":"+sdf.format(new Date()));
    }
}
