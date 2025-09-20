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
    <label for="idInstitucion" class="form-label">Institución</label>
    <input
      type="text"
      class="form-control"
      id="idInstitucion"
      name="idInstitucion"
      value="${institution.name}"
      placeholder="Universidad de San Carlos de Guatemala"
      required
    />
  </div>

  <div class="mb-3">
    <label for="nombreSalon" class="form-label">Nombre del Salón</label>
    <input
      type="text"
      class="form-control"
      id="nombreSalon"
      name="nombreSalon"
      placeholder="Salon de Proyecciones"
      required
    />
  </div>

  <div class="mb-3">
    <label for="direccion" class="form-label">Ubicación</label>
    <input
      type="text"
      class="form-control"
      id="direccion"
      name="direccion"
      placeholder="Segundo nivel, Módulo G"
      required
    />
  </div>

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

  <div class="d-flex gap-3 mt-4">
    <button type="submit" class="btn btn-primary btn-lg">Guardar</button>
    <button type="button" class="btn btn-secondary btn-lg" id="btn-cancel">
      Cancelar
    </button>
  </div>
</form>
