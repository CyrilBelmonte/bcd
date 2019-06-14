package com.ucp.daemons;

import com.ucp.xml.profiles.TimerProfiles;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ProfilesDaemon implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        TimerProfiles timerProfiles = new TimerProfiles(98.5, 20000);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // Nothing
    }
}
