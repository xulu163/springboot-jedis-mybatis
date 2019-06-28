package com.xulu.demo.framework.util;

import java.util.Date;

public class IdUtil {

    public static  String generateId(String type, String tail){
        return type + DateUtils.formatDateTime(new Date(), "yyyyMMddHHmmss") + tail;
    }

}
