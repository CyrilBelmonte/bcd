package com.ucp.xml.profiles;

import com.ucp.xml.exist.query.QuerySimpleUser;

public class TestProfile {
    public static void main(String[] args) {

        /*On lance l'initiation des profiles avec un % ici est de 100%
        QuerySimpleUser querySimpleUser = new QuerySimpleUser();

        querySimpleUser.majCat(1,128,3);

        querySimpleUser.majCat(1,358,1);*/

        Profiles profiles = new Profiles(100.0);
        for (int index =0  ; index<profiles.getProfilesList().size();index++){
            System.out.println("["+index+"] "+profiles.getProfilesList().get(index));
        }



    }
}
