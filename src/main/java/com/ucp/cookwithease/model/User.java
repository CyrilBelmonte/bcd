package com.ucp.cookwithease.model;

import lombok.*;

import java.util.Date;
import java.util.LinkedList;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    // Used by the database
    @Setter(AccessLevel.NONE)
    private int id;

    // Not loaded (only for update)
    private String password = null;

    private String firstName;
    private String lastName;
    private String pseudo;
    private String email;
    private Date inscriptionDate;

    private LinkedList<Recipe> bookmarks = new LinkedList<>();
    private LinkedList<User> friends = new LinkedList<>();
    private LinkedList<Comment> comments = new LinkedList<>();

    public void setId(int id) {
        this.id = id;

        for (Comment comment : comments) {
            comment.setRecipeID(id);
        }
    }

    public void addBookmark(Recipe recipe) {
        bookmarks.addLast(recipe);
    }

    public void addFriend(User friend) {
        friends.addLast(friend);
    }

    public void addComment(Comment comment) {
        comment.setUserID(id);
        comments.addLast(comment);
    }
}
