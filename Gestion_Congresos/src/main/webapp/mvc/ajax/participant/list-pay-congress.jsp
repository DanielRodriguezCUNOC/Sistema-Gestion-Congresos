<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Mis Inscripciones</title>
    <jsp:include page="/includes/resources.jsp" />
    <link href="resources/css/congresos.css" rel="stylesheet" />
  </head>
  <body>
    <div class="container mt-4">
      <h2 class="text-center mb-4">Mis Inscripciones a Congresos</h2>

      <c:choose>
        <c:when test="${empty listaInscripciones}">
          <div class="alert alert-info text-center">
            No tienes inscripciones registradas.
          </div>
        </c:when>
        <c:otherwise>
          <div class="row g-4">
            <c:forEach var="inscripcion" items="${listaInscripciones}">
              <div class="col-md-4">
                <div class="card shadow-sm h-100 border-primary">
                  <div class="card-body d-flex flex-column">
                    <h5 class="card-title text-primary">${inscripcion[1]}</h5>
                    <p class="card-text mb-1">
                      <strong>Fecha Inicio:</strong> ${inscripcion[2]}
                    </p>
                    <p class="card-text mb-1">
                      <strong>Fecha Finalización:</strong> ${inscripcion[3]}
                    </p>
                    <p class="card-text mb-3">
                      <strong>Precio:</strong> Q${inscripcion[4]}
                    </p>

                    <div class="mt-auto">
                      <button
                        class="btn btn-success w-100"
                        onclick="payCongress('${inscripcion[0]}')"
                      >
                        Realizar Pago
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </c:forEach>
          </div>
        </c:otherwise>
      </c:choose>

      <div class="text-center mt-5">
        <button class="btn btn-secondary px-4" id="btn-back">Regresar</button>
      </div>
    </div>
  </body>
</html>
