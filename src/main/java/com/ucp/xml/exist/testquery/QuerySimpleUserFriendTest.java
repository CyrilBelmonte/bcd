package com.ucp.xml.exist.testquery;
/**
 * @Test class for friend
 * @Author Cyril Belmonte
 */
import com.ucp.xml.exist.query.QuerySimpleUser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class QuerySimpleUserFriendTest {
    QuerySimpleUser querySimpleUser = new QuerySimpleUser();

    @Test
    public void addFriend() {
        //We recover the list of friend post add and delete
        ArrayList<Integer> postAdd = querySimpleUser.friendsList(1);
        //Add a friend
        querySimpleUser.addFriend(1, 2);
        //Delete a friend
        querySimpleUser.deleteFriend(1, 2);
        //We recover the list of friend pre add and delete
        ArrayList<Integer> preAdd = querySimpleUser.friendsList(1);
        //Size verification
        assertTrue(postAdd.size() == preAdd.size());
    }

    @Test
    public void friendsList() {
        //We recover the list of friend post add
        ArrayList<Integer> preAdd = querySimpleUser.friendsList(1);
        //Adding some friend
        for (int index = 1; index < 5; index++) {
            querySimpleUser.addFriend(1, index);
        }
        //We recover the list of friend pre add
        ArrayList<Integer> postAdd = querySimpleUser.friendsList(1);
        //Size verification
        assertTrue(preAdd.size() + 4 == postAdd.size());
        //Delete friend add
        for (int index = 1; index < 5; index++) {
            querySimpleUser.deleteFriend(1, index);
        }
    }

    @Test
    public void deleteFriend() {
        //We recover the list of friend post add
        ArrayList<Integer> preAdd = querySimpleUser.friendsList(1);
        //Adding some friend
        for (int index = 1; index < 5; index++) {
            querySimpleUser.addFriend(1, index);
        }
        //Deleting the friend
        for (int index = 1; index < 5; index++) {
            querySimpleUser.deleteFriend(1, index);
        }
        //We recover the list of friend pre add
        ArrayList<Integer> postAdd = querySimpleUser.friendsList(1);
        //Size verification
        assertTrue(preAdd.size() == postAdd.size());
    }
}
