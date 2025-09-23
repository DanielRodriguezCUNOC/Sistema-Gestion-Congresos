<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Lista de Congresos</title>
    <jsp:include page="/includes/resources.jsp" />
    <link href="resources/css/congresos.css" rel="stylesheet" />
  </head>
  <body>
    <div class="container mt-4">
      <h2 class="text-center mb-4">Congresos Disponibles</h2>

      <div class="row g-4">
        <c:forEach var="congreso" items="${listaCongresos}">
          <div class="col-md-4">
            <div class="card shadow-sm h-100">
              <div class="card-body d-flex flex-column">
                <h5 class="card-title">${congreso[1]}</h5>
                <p class="card-text mb-1">
                  <strong>Fecha Inicio:</strong> ${congreso[2]}
                </p>
                <p class="card-text mb-1">
                  <strong>Fecha Finalización:</strong> ${congreso[3]}
                </p>
                <p class="card-text mb-1">
                  <strong>Precio:</strong> Q${congreso[4]}
                </p>
                <p class="card-text mb-1">
                  <strong>Cupo:</strong> ${congreso[5]}
                </p>
                <p class="card-text mb-1">
                  <strong>Ubicación:</strong> ${congreso[6]}
                </p>
                <p class="card-text mb-3">
                  <strong>Institución:</strong> ${congreso[7]}
                </p>

                <div class="mt-auto">
                  <button
                    class="btn btn-primary w-100"
                    onclick="inscribirse('${congreso[0]}')"
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
