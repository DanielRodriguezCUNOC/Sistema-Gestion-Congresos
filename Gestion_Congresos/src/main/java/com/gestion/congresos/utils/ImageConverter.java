package com.gestion.congresos.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import com.gestion.congresos.Backend.exceptions.ImageFormatException;

import jakarta.servlet.http.Part;

public class ImageConverter {

    private static final long MAX_SIZE = 5 * 1024 * 1024; // 5MB

    public static byte[] convertImage(Part imagePart) throws ImageFormatException, IOException {

        // * Validamos que la imagen no sea nula y que su tamaño sea adecuado */
        if (imagePart != null && imagePart.getSize() > 0) {

            if (imagePart.getSize() > MAX_SIZE) {
                throw new ImageFormatException("El tamaño de la imagen supera los 5MB.");
            }

            String contentType = imagePart.getContentType();

            if (contentType == null ||
                    !(contentType.equals("image/jpeg")) || contentType.equals("image/png")
                    || contentType.equals("image/gif")) {
                throw new ImageFormatException("Formato de imagen no válido. Solo se permiten JPEG.");
            }

            // * Leemos la imagen en un arreglo de bytes */
            byte[] imageBytes;
            try (InputStream inputStream = imagePart.getInputStream()) {
                imageBytes = inputStream.readAllBytes();
            }

            // * URLConnection nos permite obtener los primeros bytes del archivo con los
            // cuales podemos determinar el tipo de archivo */
            String detectedImageValid = URLConnection.guessContentTypeFromStream

            (new ByteArrayInputStream(imageBytes));
            if (detectedImageValid == null || !detectedImageValid.startsWith("image/")) {
                throw new ImageFormatException("El archivo no es una imagen válida.");
            }

            /*
             * Validamos que la imagen pueda ser leída por ImageIO (esto descarta archivos
             * con extensiones manipuladas)
             */
            try (ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes)) {
                BufferedImage img = ImageIO.read(bais);
                if (img == null) {
                    throw new ImageFormatException("El archivo no es una imagen válida.");
                }

            }
            return imageBytes;
        }
        return null;
    }
}
