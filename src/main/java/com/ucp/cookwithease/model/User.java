package com.ucp.cookwithease.model;

import com.ucp.cookwithease.tools.Tools;
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
            comment.setUserID(id);
        }
    }

    public void addBookmark(Recipe recipe) {
        bookmarks.addFirst(recipe);
    }

    public void addFriend(User friend) {
        friends.addFirst(friend);
    }

    public void addComment(Comment comment) {
        comment.setUserID(id);
        comment.setPseudo(pseudo);
        comments.addFirst(comment);
    }

    public int getBookmarksCount() {
        return bookmarks.size();
    }

    public int getFriendsCount() {
        return friends.size();
    }

    public int getCommentsCount() {
        return comments.size();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + "\n" +
            Tools.repeat("-", firstName.length() + lastName.length() + 1) + "\n" +
            "  Pseudo: " + pseudo + " | Email: " + email + "\n" +
            "  Inscription: " + inscriptionDate;
    }
}
