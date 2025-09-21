<%@ page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="card shadow-lg">
  <div class="card-body">
    <h3 class="card-title text-center mb-4">Lista de Actividades</h3>

    <c:if test="${empty activities}">
      <div class="alert alert-warning text-center">
        No hay actividades registradas.
      </div>
    </c:if>

    <c:if test="${not empty activities}">
      <div class="table-responsive">
        <table class="table table-striped table-hover align-middle">
          <thead class="table-dark">
            <tr>
              <th>ID</th>
              <th>Congreso</th>
              <th>Nombre Actividad</th>
              <th>Tipo Actividad</th>
              <th>Salon</th>
              <th>Ubicación Salón</th>
              <th>Descripción</th>
              <th>Fecha</th>
              <th>Hora Inicio</th>
              <th>Hora Finalización</th>
              <th>Cupo</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="actividad" items="${activities}">
              <tr>
                <td>${actividad[0]}</td>
                <td>${actividad[12]}</td>
                <td>${actividad[4]}</td>
                <td>
                  <c:choose>
                    <c:when test="${actividad[3] == '1'}">TALLER</c:when>
                    <c:when test="${actividad[3] == '2'}">PONENCIA</c:when>
                    <c:otherwise>${actividad[3]}</c:otherwise>
                  </c:choose>
                </td>
                <td>${actividad[10]}</td>
                <td>${actividad[11]}</td>
                <td>${actividad[8]}</td>
                <td>${actividad[5]}</td>
                <td>${actividad[6]}</td>
                <td>${actividad[7]}</td>
                <td>
                  <c:choose>
                    <c:when
                      test="${empty actividad[9] || actividad[9] == 'null'}"
                    ></c:when>
                    <c:otherwise>${actividad[9]}</c:otherwise>
                  </c:choose>
                </td>
                <td>
                  <button
                    class="btn btn-sm btn-primary"
                    id="btn-edit-activity"
                    data-activity-id="${actividad.idActividad}"
                  >
                    Editar
                  </button>
                  <button
                    class="btn btn-sm btn-danger"
                    id="btn-delete-activity"
                    data-activity-id="${actividad[0]}"
                  >
                    Eliminar
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
