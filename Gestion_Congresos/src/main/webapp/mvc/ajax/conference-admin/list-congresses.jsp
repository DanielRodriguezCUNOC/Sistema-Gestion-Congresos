<%@ page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="card shadow-lg">
  <div class="card-body">
    <h3 class="card-title text-center mb-4">Lista de Congresos</h3>

    <c:if test="${empty congresses}">
      <div class="alert alert-warning text-center">
        No hay congresos registrados.
      </div>
    </c:if>

    <c:if test="${not empty congresses}">
      <div class="table-responsive">
        <table class="table table-striped table-hover align-middle">
          <thead class="table-dark">
            <tr>
              <th>ID</th>
              <th>Institución Encargada</th>
              <th>Ubicación Institucion</th>
              <th>Nombre Congreso</th>
              <th>Fecha Inicio</th>
              <th>Fecha Fin</th>
              <th>Precio</th>
              <th>Convocatoria</th>
              <th>Estado</th>
              <th>Cupo</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="congreso" items="${congresses}">
              <tr>
                <td>${congreso[0]}</td>
                <td>${congreso[1]}</td>
                <td>${congreso[2]}</td>
                <td>${congreso[3]}</td>
                <td>${congreso[4]}</td>
                <td>${congreso[5]}</td>
                <td>${congreso[6]}</td>
                <td>
                  <c:choose>
                    <c:when test="${congreso[7] == 'true'}">
                      <span class="badge bg-success">Sí</span>
                    </c:when>
                    <c:otherwise>
                      <span class="badge bg-danger">No</span>
                    </c:otherwise>
                  </c:choose>
                </td>
                <td>
                  <c:choose>
                    <c:when test="${congreso[8] == 'true'}">
                      <span class="badge bg-primary">Activo</span>
                    </c:when>
                    <c:otherwise>
                      <span class="badge bg-secondary">Inactivo</span>
                    </c:otherwise>
                  </c:choose>
                </td>

                <td>${congreso[9]}</td>
                <td>
                  <button
                    class="btn btn-sm btn-warning"
                    id="btn-edit-congress"
                    data-congress-id="${congreso[0]}"
                  >
                    Editar
                  </button>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </c:if>
  </div>
</div>
