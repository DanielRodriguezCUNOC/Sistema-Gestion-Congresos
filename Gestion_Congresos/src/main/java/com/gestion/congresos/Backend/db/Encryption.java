package com.gestion.congresos.Backend.db;

import java.util.Base64;

// The `public class Encryption` is a Java class that provides methods for encrypting and decrypting
// passwords using Base64 encoding and decoding. It contains two static methods: `encryptPassword` for
// encoding a password to Base64, and `decryptPassword` for decoding a Base64-encoded password back to
// its original form.
public class Encryption {

    /**
     * The `encryptPassword` function encodes a given password using Base64
     * encoding.
     * 
     * @param password The `encryptPassword` method you provided encodes the given
     *                 password using Base64
     *                 encoding. This method takes a `String` password as input and
     *                 returns the Base64 encoded version
     *                 of the password.
     * @return The method `encryptPassword` is returning the Base64 encoded string
     *         of the input
     *         password.
     */
    public static String encryptPassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    /**
     * The function decrypts a Base64-encoded password and returns the decoded
     * string.
     * 
     * @param encryptedPassword The `decryptPassword` method takes a Base64 encoded
     *                          password as input.
     * @return The `decryptPassword` method returns a decrypted password as a String
     *         after decoding the
     *         input encrypted password using Base64 decoding.
     */
    public static String decryptPassword(String encryptedPassword) {
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);
        return new String(decodedBytes);
    }

}