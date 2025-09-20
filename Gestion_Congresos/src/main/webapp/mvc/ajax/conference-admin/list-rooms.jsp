<%@ page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="card shadow-lg">
  <div class="card-body">
    <h3 class="card-title text-center mb-4">Lista de Salones</h3>

    <c:if test="${empty roomList}">
      <div class="alert alert-warning text-center">
        No hay salones registrados.
      </div>
    </c:if>

    <c:if test="${not empty roomList}">
      <div class="table-responsive">
        <table class="table table-striped table-hover align-middle">
          <thead class="table-dark">
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Institución</th>
              <th>Ubicación</th>
              <th>Capacidad</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="room" items="${roomList}">
              <tr>
                <td>${room[0]}</td>
                <td>${room[1]}</td>
                <td>${room[2]}</td>
                <td>${room[3]}</td>
                <td>${room[4]}</td>
                <td class="d-flex gap-2">
                  <button
                    class="btn btn-sm btn-warning btn-edit"
                    data-room-id="${room[0]}"
                  >
                    Editar
                  </button>
                  <button
                    class="btn btn-sm btn-danger btn-delete"
                    data-room-id="${room[0]}"
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
