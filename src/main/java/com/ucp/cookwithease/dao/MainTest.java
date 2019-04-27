package com.ucp.cookwithease.dao;

import com.ucp.cookwithease.model.Comment;
import com.ucp.cookwithease.model.User;
import com.ucp.cookwithease.tools.Tools;

import java.util.Date;


public class MainTest {
    public static void main(String[] args) {
        User user = new User();

        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPseudo("jdoe");
        user.setEmail("john.doe@u-cergy.fr");
        user.setPassword(Tools.sha256("password"));
        user.setInscriptionDate(new Date());

        System.out.println(DAOFactory.getUserDAO().insert(user));
        System.out.println(user.getId());


        Comment comment = new Comment();

        comment.setUserID(user.getId());
        comment.setRecipeID(1);
        comment.setDescription("Génial !");
        comment.setRating(5);
        comment.setPublicationDate(new Date());

        System.out.println(DAOFactory.getCommentDAO().insert(comment));
    }
}
