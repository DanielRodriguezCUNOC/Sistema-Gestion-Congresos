package com.gestion.congresos.utils;

import java.io.IOException;
import java.io.InputStream;

import jakarta.servlet.http.Part;

public class ImageConverter {

    public static byte[] convertImage(Part imagePart) throws IOException {

        if (imagePart != null && imagePart.getSize() > 0) {
            try (InputStream inputStream = imagePart.getInputStream()) {
                return inputStream.readAllBytes();
            }

        }
        return null;

    }
}
