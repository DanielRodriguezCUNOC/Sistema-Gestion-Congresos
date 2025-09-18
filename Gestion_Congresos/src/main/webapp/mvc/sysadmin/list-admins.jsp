<%@ page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3>Usuarios del Sistema</h3>

<c:if test="${not empty error}">
  <div class="alert alert-danger">${error}</div>
</c:if>

<c:if test="${empty admins}">
  <div class="alert alert-info">No hay usuarios registrados.</div>
</c:if>

<c:if test="${not empty admins}">
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
      <c:forEach var="admin" items="${admins}">
        <tr>
          <td>${admin[0]}</td>
          <td>${admin[1]}</td>
          <td>${admin[2]}</td>
          <td>${admin[3]}</td>
          <td>${admin[4]}</td>
          <td>${admin[5]}</td>
          <td>${admin[6]}</td>
          <td>${admin[7]}</td>
          <td>${admin[8]}</td>
          <td>
            <button
              class="btn btn-sm btn-primary btn-edit-admin"
              data-admin-id="${admin[0]}"
            >
              Editar
            </button>
            <button
              class="btn btn-sm btn-danger btn-delete-admin"
              data-admin-id="${admin[0]}"
            >
              Desactivar
            </button>
            <button
              class="btn btn-sm btn-warning btn-activate-admin"
              data-admin-id="${admin[0]}"
            >
              Activar
            </button>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</c:if>
