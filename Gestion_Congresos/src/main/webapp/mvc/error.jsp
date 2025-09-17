<%@ page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="alert alert-danger">
  <h5 class="fw-bold">¡Ocurrió un error!</h5>
  <p>
    <c:out
      value="${error}"
      default="Error desconocido. Por favor intenta nuevamente."
    />
  </p>
  <button type="button" class="btn btn-secondary mt-2" id="btn-back">
    Regresar
  </button>
</div>
