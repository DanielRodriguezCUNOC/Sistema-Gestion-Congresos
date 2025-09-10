package com.gestion.congresos.db;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * The Encryption class provides methods to encrypt a password using AES
 * encryption with a 128-bit key.
 */
public class Encryption {

    private static Cipher cipher;

    /**
     * The `encryptPassword` function generates an AES secret key, encrypts a given
     * password using this
     * key, and returns the encrypted password.
     * 
     * @param password The code you provided is a method that encrypts a password
     *                 using the AES encryption
     *                 algorithm. It generates a secret key, initializes a cipher
     *                 with AES algorithm, and then encrypts the
     *                 password using the generated secret key.
     * @return The `encryptPassword` method is returning the encrypted password as a
     *         String if the
     *         encryption process is successful. If there is an error during
     *         encryption, it will return `null`.
     */

    public String encryptPassword(String password) {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            // *Tamaño de clave de 128 bits
            keyGen.init(128);
            SecretKey secretKey = keyGen.generateKey();

            // *Instanciamos el cifrador con el algoritmo AES
            cipher = Cipher.getInstance("AES");

            // *Llamamos al método que cifra la contraseña
            String encryptedPassword = encryptPassword(password, secretKey);
            if (encryptedPassword != null) {
                return encryptedPassword;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * The `encryptPassword` function takes a password and a secret key, encrypts
     * the password using the
     * secret key, and returns the encrypted password as a Base64 encoded string.
     * 
     * @param password  The `encryptPassword` method you provided takes a `String`
     *                  password and a
     *                  `SecretKey` as parameters to encrypt the password using a
     *                  cipher. The encrypted password is then
     *                  encoded using Base64 and returned as a `String`.
     * @param secretKey The `secretKey` parameter in your `encryptPassword` method
     *                  is of type `SecretKey`.
     *                  This key is used to initialize the cipher for encryption. It
     *                  is essential for encrypting the
     *                  password securely. Make sure to generate a strong and secure
     *                  `SecretKey` before calling this method.
     * @return The method `encryptPassword` returns the encrypted password as a
     *         Base64 encoded string.
     */
    private String encryptPassword(String password, SecretKey secretKey) {
        try {
            // *Convertimos la contraseña a un arreglo de bytes
            byte[] passwordByte = password.getBytes();
            // *Iniciamos el cifrado en modo ENCRYPT_MODE
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            // *Ciframos la contraseña
            byte[] encryptedByte = cipher.doFinal(passwordByte);
            /*
             * Codificamos el arreglo de bytes a Base64 para obtener una
             * representación en String
             */
            Base64.Encoder encoder = Base64.getEncoder();
            String encryptedPassword = encoder.encodeToString(encryptedByte);
            return encryptedPassword;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}