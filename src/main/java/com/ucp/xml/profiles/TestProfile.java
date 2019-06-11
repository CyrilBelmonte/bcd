package com.ucp.xml.profiles;

public class TestProfile {
    public static void main(String[] args) {
        Profiles profiles = new Profiles(85.0);
        for (int index =0  ; index<profiles.getProfilesList().size();index++){
            System.out.println("["+index+"] "+profiles.getProfilesList().get(index));
        }
    }
}
