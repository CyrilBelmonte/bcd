package com.ucp.ihm;

import com.ucp.xml.exist.query.QueryProfile;
import com.ucp.xml.exist.query.QuerySimpleUser;
import sun.font.GlyphLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelUser extends JPanel {

    public PanelUser(int idUser){

        QuerySimpleUser querySimpleUser = new QuerySimpleUser();
        ArrayList<Integer> friends = querySimpleUser.friendsList(idUser);

        QueryProfile queryProfile = new QueryProfile();
        List<Integer> profiles = queryProfile.getIdUsersByIdUser(idUser);

        Label friendsL = new Label("User's friends");

        String friendsList = "";

        String profilesList = "";

        for (Integer friend : friends){
            friendsList = friendsList +";"+friend;
        }

        for (Integer profile : profiles){
            profilesList = profilesList +";"+profile;
        }

        Label profilesL = new Label("User with same profiles");


        this.add(friendsL);
        this.add(new Label(friendsList));
        this.add(profilesL);
        this.add(new Label(profilesList));

    }

}
