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

  <div class="mb-3">
    <label for="nameInstitution" class="form-label">Institución</label>
    <input
      type="text"
      class="form-control"
      id="nameInstitution"
      name="nameInstitution"
      placeholder="Universidad de San Carlos de Guatemala"
      required
    />
  </div>

  <div class="mb-3">
    <label for="nameRoom" class="form-label">Nombre del Salón</label>
    <input
      type="text"
      class="form-control"
      id="nameRoom"
      name="nameRoom"
      placeholder="Salon de Proyecciones"
      required
    />
  </div>

  <div class="mb-3">
    <label for="addressRoom" class="form-label">Ubicación</label>
    <input
      type="text"
      class="form-control"
      id="addressRoom"
      name="addressRoom"
      placeholder="Segundo nivel, Módulo G"
      required
    />
  </div>

  <div class="mb-3">
    <label for="capacityRoom" class="form-label">Capacidad</label>
    <input
      type="number"
      class="form-control"
      id="capacityRoom"
      name="capacityRoom"
      min="1"
      required
    />
  </div>

  <div class="d-flex gap-3 mt-4">
    <button type="submit" class="btn btn-primary btn-lg">Guardar</button>
    <button type="button" class="btn btn-secondary btn-lg" id="btn-cancel">
      Cancelar
    </button>
  </div>
</form>
