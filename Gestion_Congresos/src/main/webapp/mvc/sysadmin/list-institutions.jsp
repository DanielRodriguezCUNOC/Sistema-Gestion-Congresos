<%@ page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${not empty institutions}">
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Nombre Institución</th>
        <th>Ubicación</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="institution" items="${institutions}">
        <tr>
          <td>${institution.name_institution}</td>
          <td>${institution.address_institution}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</c:if>
