package com.ucp.cookwithease.tools;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Tools {
    private Tools() {}

    public static boolean isEmail(String email) {
        String pattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

        return email != null && email.matches(pattern);
    }

    public static String sha256(String message) {
        String hashedMessage = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(message.getBytes(StandardCharsets.UTF_8));
            byte[] hashedMessageBytes = md.digest();

            hashedMessage = DatatypeConverter.printHexBinary(hashedMessageBytes).toLowerCase();

        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());

        }

        return hashedMessage;
    }

    public static String repeat(String pattern, int repeat) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < repeat; i++) {
            result.append(pattern);
        }

        return result.toString();
    }
}
