package com.example.testApp.common;

import java.sql.Timestamp;
import java.util.Date;

public class Utils {

    // Returns the current time
    public static Timestamp getTimeStamp() {
        Date date= new Date();

        long time = date.getTime();
        System.out.println("Time in Milliseconds: " + time);

        return new Timestamp(time);
    }

}
