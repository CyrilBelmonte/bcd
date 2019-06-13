package com.ucp.xml.exist.query.thread;

import com.ucp.xml.exist.query.thread.profiles.ThreadList;

public class Test {

    public static void main(String[] args) {
        ThreadList t = new ThreadList(1,"starter");

        System.out.println(t.getCategory());
    }
}
