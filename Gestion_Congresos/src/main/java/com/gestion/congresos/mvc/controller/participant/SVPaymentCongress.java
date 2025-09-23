package com.gestion.congresos.mvc.controller.participant;

import java.io.IOException;

import com.gestion.congresos.Backend.exceptions.DataBaseException;
import com.gestion.congresos.Backend.handler.participant.PaymentCongressHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SVPaymentCongress", urlPatterns = { "/SVPaymentCongress" })
public class SVPaymentCongress extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PaymentCongressHandler paymentCongressHandler = new PaymentCongressHandler(request);

        request.setAttribute("listaInscripciones", paymentCongressHandler.getAvailableCongressesForPayment());

        request.getRequestDispatcher("/mvc/ajax/participant/list-pay-congress.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PaymentCongressHandler paymentCongressHandler = new PaymentCongressHandler(request);

        String action = request.getParameter("action");

        try {
            boolean success = false;
            switch (action) {
                case "pago":
                    success = paymentCongressHandler.processPayment();
                    break;
                default:
                    response.getWriter().write("{\"success\": false, \"message\": \"Acción no válida\"}");
                    return;
            }

            if (success) {
                response.getWriter().write("{\"success\": true, \"message\": \"Pago realizado con éxito\"}");
            } else {
                response.getWriter().write("{\"success\": false, \"message\": \"Error al procesar el pago\"}");

            }

        } catch (DataBaseException e) {
            response.getWriter()
                    .write("{\"success\": false, \"message\": \"Error de base de datos: " + e.getMessage() + "\"}");
        } catch (Exception e) {
            response.getWriter()
                    .write("{\"success\": false, \"message\": \"Error inesperado: " + e.getMessage() + "\"}");
        }
    }

}
