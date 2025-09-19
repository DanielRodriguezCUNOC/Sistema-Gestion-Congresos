<%@ page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="card shadow-lg">
  <div class="card-body">
    <h3 class="card-title text-center mb-4">Lista de Actividades</h3>

    <c:if test="${empty activityList}">
      <div class="alert alert-warning text-center">
        No hay actividades registradas.
      </div>
    </c:if>

    <c:if test="${not empty activityList}">
      <div class="table-responsive">
        <table class="table table-striped table-hover align-middle">
          <thead class="table-dark">
            <tr>
              <th>ID</th>
              <th>Congreso</th>
              <th>Tipo</th>
              <th>Nombre</th>
              <th>Descripción</th>
              <th>Fecha</th>
              <th>Hora Inicio</th>
              <th>Hora Fin</th>
              <th>Cupo</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="actividad" items="${activityList}">
              <tr>
                <td>${actividad.idActividad}</td>
                <td>${actividad.idCongreso}</td>
                <td>${actividad.idTipoActividad}</td>
                <td>${actividad.nombreActividad}</td>
                <td>${actividad.descripcion}</td>
                <td>${actividad.fecha}</td>
                <td>${actividad.horaInicio}</td>
                <td>${actividad.horaFin}</td>
                <td>${actividad.cupoTaller}</td>
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
                    data-activity-id="${actividad.idActividad}"
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
