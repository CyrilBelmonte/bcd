package com.ucp.ia;

public class KohonenThread extends  Thread {

    Kohonen kohonen;
    String type;
    public KohonenThread(Kohonen kohonen, String type) {
        this.kohonen=kohonen;
        this.type=type;
    }

    public void run(){
       System.out.println("Clustering kohonen "+type);
       kohonen.Clustering();
        System.out.println("Clustering end "+type);
    }
}
