package com.ucp.xml.profiles;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;

public class TimerProfiles {

    public TimerProfiles(double d, long delayMillisecond) {
        Timer t = new Timer();
        GregorianCalendar gc = new GregorianCalendar();
        //Start task after 5 second of the launch of program
        gc.add(Calendar.SECOND, 5);
        //delay in millisecond
        t.scheduleAtFixedRate(new AutoProfiles(d), gc.getTime(), delayMillisecond);
    }
}
