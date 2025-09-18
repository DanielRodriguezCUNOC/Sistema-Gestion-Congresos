<%@ page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form
  id="form-create-room"
  class="card p-4 shadow-lg"
  style="max-width: 36rem; margin: auto"
>
  <h3 class="text-center mb-4">Crear Salón</h3>

  <c:if test="${not empty errorMessage}">
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
      ${errorMessage}
      <button
        type="button"
        class="btn-close"
        data-bs-dismiss="alert"
        aria-label="Close"
      ></button>
    </div>
  </c:if>

  <!-- Institución -->
  <div class="mb-3">
    <label for="idInstitucion" class="form-label">Institución</label>
    <select
      class="form-select"
      id="idInstitucion"
      name="idInstitucion"
      required
    >
      <option value="">Seleccione una institución</option>
      <c:forEach var="inst" items="${institutions}">
        <option value="${inst.idInstitucion}">${inst.nombreInstitucion}</option>
      </c:forEach>
    </select>
  </div>

  <!-- Nombre del salón -->
  <div class="mb-3">
    <label for="nombreSalon" class="form-label">Nombre del Salón</label>
    <input
      type="text"
      class="form-control"
      id="nombreSalon"
      name="nombreSalon"
      required
    />
  </div>

  <!-- Dirección -->
  <div class="mb-3">
    <label for="direccion" class="form-label">Dirección</label>
    <input
      type="text"
      class="form-control"
      id="direccion"
      name="direccion"
      required
    />
  </div>

  <!-- Capacidad -->
  <div class="mb-3">
    <label for="capacidad" class="form-label">Capacidad</label>
    <input
      type="number"
      class="form-control"
      id="capacidad"
      name="capacidad"
      min="1"
      required
    />
  </div>

  <div class="d-grid gap-2 mt-4">
    <button type="submit" class="btn btn-primary btn-lg">Guardar Salón</button>
    <button type="button" class="btn btn-secondary btn-lg" id="btn-cancel">
      Cancelar
    </button>
  </div>
</form>
