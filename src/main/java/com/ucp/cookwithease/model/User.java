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

    private LinkedList<Integer> bookmarks = new LinkedList<>();
    private LinkedList<Integer> friends = new LinkedList<>();
    private LinkedList<Comment> comments = new LinkedList<>();

    public void setId(int id) {
        this.id = id;

        for (Comment comment : comments) {
            comment.setUserID(id);
        }
    }

    public boolean hasBookmark(int recipeID) {
        return bookmarks.contains(recipeID);
    }

    public boolean hasFriend(int friendID) {
        return friends.contains(friendID);
    }

    public boolean hasComment(int recipeID) {
        for (Comment comment : comments) {
            if (comment.getRecipeID() == recipeID) {
                return true;
            }
        }

        return false;
    }

    public void addBookmark(int recipeID) {
        bookmarks.addFirst(recipeID);
    }

    public void addFriend(int friendID) {
        friends.addFirst(friendID);
    }

    public void addComment(Comment comment) {
        comment.setUserID(id);
        comment.setPseudo(pseudo);
        comments.addFirst(comment);
    }

    public void deleteBookmark(int recipeID) {
        bookmarks.remove((Integer) recipeID);
    }

    public void deleteFriend(int friendID) {
        friends.remove((Integer) friendID);
    }

    public void deleteComment(int recipeID) {
        Comment comment;

        for (int i = 0; i < comments.size(); i++) {
            comment = comments.get(i);

            if (comment.getRecipeID() == recipeID) {
                comments.remove(i);
                break;
            }
        }
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
