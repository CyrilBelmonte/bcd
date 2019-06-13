package com.ucp.xml.profiles;

import com.ucp.xml.exist.query.QueryProfile;

import java.util.TimerTask;

public class AutoProfiles extends TimerTask {
    private double d;

    public AutoProfiles(double d) {
        this.d = d;
    }

    @Override
    public void run() {
        System.out.println("START TASK PROFILE");
        Profiles profiles = new Profiles(d);
        QueryProfile queryProfile = new QueryProfile();
        queryProfile.addProfiles(profiles.getProfilesList());
        System.out.println("END TASK PROFILE");
    }
}
