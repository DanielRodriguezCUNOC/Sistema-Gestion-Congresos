<%@ page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3>Administradores de Congresos</h3>

<c:if test="${not empty error}">
  <div class="alert alert-danger">${error}</div>
</c:if>

<c:if test="${empty conferenceAdmins}">
  <div class="alert alert-info">No hay administradores registrados.</div>
</c:if>

<c:if test="${not empty conferenceAdmins}">
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Nombre</th>
        <th>Usuario</th>
        <th>Correo</th>
        <th>Identificación</th>
        <th>Teléfono</th>
        <th>Organización</th>
        <th>Estado</th>
        <th>Rol</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="admin" items="${conferenceAdmins}">
        <tr>
          <td>${admin[0]}</td>
          <td>${admin[1]}</td>
          <td>${admin[2]}</td>
          <td>${admin[3]}</td>
          <td>${admin[4]}</td>
          <td>${admin[5]}</td>
          <td>${admin[6]}</td>
          <td>${admin[7]}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</c:if>
