package com.borangalleri.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class DateUtils {
    public static String getCurrentDate(LocalDateTime date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyy");
        
        return simpleDateFormat.format(date);
    }

}
