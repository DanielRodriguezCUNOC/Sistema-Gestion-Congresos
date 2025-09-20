package com.gestion.congresos.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.gestion.congresos.Backend.exceptions.ImageFormatException;

import jakarta.servlet.http.Part;

public class ImageConverter {

    private static final long MAX_SIZE = 5 * 1024 * 1024; // 5MB

    public static byte[] convertImage(Part imagePart) throws ImageFormatException, IOException {
        if (imagePart != null && imagePart.getSize() > 0) {

            if (imagePart.getSize() > MAX_SIZE) {
                throw new ImageFormatException("El tamaño de la imagen supera los 5MB.");
            }

            String contentType = imagePart.getContentType();

            if (contentType == null ||
                    !(contentType.equals("image/jpeg") || contentType.equals("image/png")
                            || contentType.equals("image/gif"))) {
                throw new ImageFormatException("Formato de imagen no válido. Solo se permiten JPEG, PNG o GIF.");
            }

            byte[] imageBytes;
            try (InputStream inputStream = imagePart.getInputStream()) {
                imageBytes = inputStream.readAllBytes();
            }

            String detectedType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(imageBytes));
            if (detectedType == null || !detectedType.startsWith("image/")) {
                throw new ImageFormatException("El archivo no es una imagen válida.");
            }

            BufferedImage img;
            try (ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes)) {
                img = ImageIO.read(bais);
                if (img == null) {
                    throw new ImageFormatException("El archivo no es una imagen válida.");
                }
            }

            if (contentType.equals("image/jpeg")) {
                return imageBytes;
            } else {
                return convertToJpeg(img);
            }
        }
        return null;
    }

    /*
     * Convierte una imagen (PNG, GIF, etc.) a JPEG y devuelve los bytes.
     */
    private static byte[] convertToJpeg(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // * Convertir la imagen a RGB (porque PNG y GIF pueden tener transparencia)
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        // * Pintamos sobre un fondo blanco
        Graphics2D g = newImage.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, newImage.getWidth(), newImage.getHeight());
        g.drawImage(image, 0, 0, null);
        g.dispose();

        // * Escribimos como JPEG
        ImageIO.write(newImage, "jpg", baos);

        return baos.toByteArray();
    }
}