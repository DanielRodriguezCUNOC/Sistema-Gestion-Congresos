<%@ page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="card shadow-lg">
  <div class="card-body">
    <h3 class="card-title text-center mb-4">Lista de Congresos</h3>

    <c:if test="${empty congressList}">
      <div class="alert alert-warning text-center">
        No hay congresos registrados.
      </div>
    </c:if>

    <c:if test="${not empty congressList}">
      <div class="table-responsive">
        <table class="table table-striped table-hover align-middle">
          <thead class="table-dark">
            <tr>
              <th>ID</th>
              <th>Institución</th>
              <th>Nombre</th>
              <th>Fecha Inicio</th>
              <th>Fecha Fin</th>
              <th>Precio</th>
              <th>Convocatoria</th>
              <th>Estado</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="congreso" items="${congressList}">
              <tr>
                <td>${congreso.idCongreso}</td>
                <td>${congreso.idInstitucion}</td>
                <td>${congreso.nombreCongreso}</td>
                <td>${congreso.fechaInicio}</td>
                <td>${congreso.fechaFin}</td>
                <td>${congreso.precio}</td>
                <td>
                  <span
                    class="badge bg-${congreso.aceptaConvocatoria ? 'success' : 'danger'}"
                  >
                    ${congreso.aceptaConvocatoria ? 'Sí' : 'No'}
                  </span>
                </td>
                <td>
                  <span
                    class="badge bg-${congreso.estado ? 'primary' : 'secondary'}"
                  >
                    ${congreso.estado ? 'Activo' : 'Inactivo'}
                  </span>
                </td>
                <td>
                  <button
                    class="btn btn-sm btn-warning btn-edit"
                    data-id="${congreso.idCongreso}"
                  >
                    Editar
                  </button>
                  <button
                    class="btn btn-sm btn-danger btn-delete"
                    data-id="${congreso.idCongreso}"
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
