<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
        <th>ID</th>
        <th>Nombre</th>
        <th>Email</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="admin" items="${conferenceAdmins}">
        <tr>
          <td>${admin[0]}</td>
          <td>${admin[1]}</td>
          <td>${admin[2]}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</c:if>

<button class="btn btn-secondary mt-3" id="btn-cancel">Cancelar</button>
