package com.gestion.congresos.Backend.reports;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GenerateReportCongressInstitution {

    public boolean exportarTablaHTML(List<List<Object>> datos) {
        try {

            File carpeta = new File("reportes");
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
            String fechaHora = ahora.format(formatter);

            String nombreArchivo = "reporte_congresos_" + fechaHora + ".html";
            File archivo = new File(carpeta, nombreArchivo);

            try (FileWriter writer = new FileWriter(archivo)) {
                StringBuilder html = new StringBuilder();
                html.append("<!DOCTYPE html>")
                        .append("<html><head><meta charset='UTF-8'><title>Reporte de Congresos</title></head><body>")
                        .append("<table border='1' style='border-collapse: collapse;'>")
                        .append("<tr>")
                        .append("<th>Institución</th>")
                        .append("<th>Ubicación</th>")
                        .append("<th>Congreso</th>")
                        .append("<th>Fecha Inicio</th>")
                        .append("<th>Fecha Fin</th>")
                        .append("<th>Precio</th>")
                        .append("<th>Acepta Convocatoria</th>")
                        .append("<th>Estado</th>")
                        .append("<th>Cupo</th>")
                        .append("</tr>");

                for (List<Object> fila : datos) {
                    html.append("<tr>");
                    for (Object valor : fila) {
                        html.append("<td>").append(valor).append("</td>");
                    }
                    html.append("</tr>");
                }

                html.append("</table></body></html>");
                writer.write(html.toString());
            }
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
