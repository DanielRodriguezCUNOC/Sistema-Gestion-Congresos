<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Talleres Disponibles</title>
    <jsp:include page="/includes/resources.jsp" />
    <link href="resources/css/talleres.css" rel="stylesheet" />
  </head>
  <body>
    <div class="container mt-4">
      <h2 class="text-center mb-4">Talleres Disponibles</h2>

      <div class="row g-4">
        <c:forEach var="taller" items="${listaTalleres}">
          <div class="col-md-4">
            <div class="card shadow-lg h-100 border-0">
              <div class="card-body d-flex flex-column">
                <h5 class="card-title text-primary">${taller[1]}</h5>
                <p class="card-text">
                  <strong>Descripción:</strong> ${taller[2]}
                </p>
                <p class="card-text mb-1">
                  <strong>Fecha:</strong> ${taller[3]}
                </p>
                <p class="card-text mb-1">
                  <strong>Horario:</strong> ${taller[4]} - ${taller[5]}
                </p>
                <p class="card-text mb-1">
                  <strong>Cupo:</strong> ${taller[6]}
                </p>
                <p class="card-text mb-1 text-success">
                  <strong>Cupos Disponibles:</strong> ${taller[7]}
                </p>
                <p class="card-text mb-1">
                  <strong>Ubicación:</strong> ${taller[8]}
                </p>
                <p class="card-text mb-3">
                  <strong>Institución:</strong> ${taller[9]}
                </p>

                <div class="mt-auto">
                  <button
                    class="btn btn-outline-primary w-100"
                    onclick="inscribirseTaller('${taller[0]}')"
                  >
                    Inscribirse
                  </button>
                </div>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>

      <div class="text-center mt-5">
        <button class="btn btn-secondary px-4" id="btn-back">Regresar</button>
      </div>
    </div>
  </body>
</html>
