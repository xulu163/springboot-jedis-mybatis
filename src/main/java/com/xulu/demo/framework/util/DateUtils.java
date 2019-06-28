package com.xulu.demo.framework.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public static String formatDateTime(Date date, String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }


}
