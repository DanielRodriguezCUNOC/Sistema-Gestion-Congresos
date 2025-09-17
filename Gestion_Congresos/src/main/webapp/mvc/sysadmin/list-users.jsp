<%@ page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3>Usuarios del Sistema</h3>

<c:if test="${not empty error}">
  <div class="alert alert-danger">${error}</div>
</c:if>

<c:if test="${empty users}">
  <div class="alert alert-info">No hay usuarios registrados.</div>
</c:if>

<c:if test="${not empty users}">
  <table class="table table-striped table-bordered">
    <thead>
      <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Usuario</th>
        <th>Email</th>
        <th>Identificación</th>
        <th>Teléfono</th>
        <th>Organización</th>
        <th>Estado</th>
        <th>Rol</th>
        <th>Acciones</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="user" items="${users}">
        <tr>
          <td>${user[0]}</td>
          <td>${user[1]}</td>
          <td>${user[2]}</td>
          <td>${user[3]}</td>
          <td>${user[4]}</td>
          <td>${user[5]}</td>
          <td>${user[6]}</td>
          <td>${user[7]}</td>
          <td>${user[8]}</td>
          <td>
            <button
              class="btn btn-sm btn-primary btn-edit-user"
              data-user-id="${user[0]}"
            >
              Editar
            </button>
            <button
              class="btn btn-sm btn-danger btn-delete-user"
              data-user-id="${user[0]}"
            >
              Desactivar
            </button>
            <button
              class="btn btn-sm btn-warning btn-activate-user"
              data-user-id="${user[0]}"
            >
              Activar
            </button>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</c:if>
