<%@ page contentType="text/html" pageEncoding="UTF-8" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3 class="mb-3">Ponentes Invitados</h3>

<c:if test="${not empty error}">
  <div class="alert alert-danger">${error}</div>
</c:if>

<c:if test="${empty guestsSpeakers}">
  <div class="alert alert-info">No hay Ponentes Invitados Registrados.</div>
</c:if>

<c:if test="${not empty guestsSpeakers}">
  <div class="table-responsive">
    <table class="table table-striped table-bordered align-middle">
      <thead class="table-light">
        <tr>
          <th>Nombre</th>
          <th>Correo</th>
          <th>Teléfono</th>
          <th>Identificación</th>
          <th>Organización</th>
          <th>Estado</th>
          <th>Rol</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="guest" items="${guestsSpeakers}">
          <tr>
            <td>${guest[0]}</td>
            <td>${guest[1]}</td>
            <td>${guest[2]}</td>
            <td>${guest[3]}</td>
            <td>${guest[4]}</td>
            <td>
              <span class="badge 
                <c:choose>
                  <c:when test='${guest[5] == "Activo"}'>bg-success</c:when>
                  <c:otherwise>bg-secondary</c:otherwise>
                </c:choose>">
                ${guest[5]}
              </span>
            </td>
            <td>${guest[6]}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</c:if>
