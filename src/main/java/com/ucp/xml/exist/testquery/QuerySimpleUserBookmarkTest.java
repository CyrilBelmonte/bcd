package com.ucp.xml.exist.testquery;
/**
 * @Test class for bookmark
 * @Author Cyril Belmonte
 */

import com.ucp.xml.exist.query.QuerySimpleUser;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class QuerySimpleUserBookmarkTest {
    QuerySimpleUser querySimpleUser = new QuerySimpleUser();

    @Test
    public void addBookmark() {
        //We recover the list of bookmark post add and delete
        List<Integer> postAdd = querySimpleUser.bookmarksList(1);
        //Add a bookmark
        querySimpleUser.addBookmark(1, 2);
        //Delete a bookmark
        querySimpleUser.deleteBookmark(1, 2);
        //We recover the list of bookmarks after add and delete
        List<Integer> preAdd = querySimpleUser.bookmarksList(1);
        //Size verification
        assertTrue(postAdd.size() == preAdd.size());
    }

    @Test
    public void bookmarksList() {
        //We recover the list of bookmark post add
        List<Integer> preAdd = querySimpleUser.bookmarksList(1);
        //Add a some bookmarks (5 here)
        for (int index = 1; index < 5; index++) {
            querySimpleUser.addBookmark(1, index);
        }
        //We recover the list of bookmarks after
        List<Integer> postAdd = querySimpleUser.bookmarksList(1);
        //Size Verification
        assertTrue(preAdd.size() + 4 == postAdd.size());
        //We delete bookmarks after verifications
        for (int index = 1; index < 5; index++) {
            querySimpleUser.deleteBookmark(1, index);
        }
    }

    @Test
    public void deleteBookmark() {
        //We recover the list of bookmark post add
        List<Integer> preAdd = querySimpleUser.bookmarksList(1);
        //Add a some bookmarks (5 here)
        for (int index = 1; index < 5; index++) {
            querySimpleUser.addBookmark(1, index);
        }
        //Delete all bookmark adding
        for (int index = 1; index < 5; index++) {
            querySimpleUser.deleteBookmark(1, index);
        }
        //We recover the list of bookmark post add
        List<Integer> postAdd = querySimpleUser.bookmarksList(1);
        /* Size verification */
        assertTrue(preAdd.size() == postAdd.size());
    }
}
