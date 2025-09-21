<%@ page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form
  id="form-create-congress"
  class="card p-4 shadow-lg"
  style="max-width: 36rem; margin: auto"
>
  <h3 class="text-center mb-4">Crear Congreso</h3>

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
    <label for="nameInstitution" class="form-label"
      >Institución Encargada</label
    >
    <input
      type="text"
      class="form-control"
      id="nameInstitution"
      name="nameInstitution"
      placeholder="Ingrese el nombre de la institución"
      required
      min="1"
    />
  </div>

  <div class="mb-3">
    <label for="nameCongress" class="form-label">Nombre del Congreso</label>
    <input
      type="text"
      class="form-control"
      id="nameCongress"
      name="nameCongress"
      required
    />
  </div>

  <div class="mb-3">
    <label for="dateInitializing" class="form-label">Fecha de Inicio</label>
    <input
      type="date"
      class="form-control"
      id="dateInitializing"
      name="dateInitializing"
      required
    />
  </div>

  <div class="mb-3">
    <label for="dateEnding" class="form-label">Fecha de Fin</label>
    <input
      type="date"
      class="form-control"
      id="dateEnding"
      name="dateEnding"
      required
    />
  </div>

  <div class="mb-3">
    <label for="priceCongress" class="form-label">Precio</label>
    <input
      type="number"
      class="form-control"
      id="priceCongress"
      name="priceCongress"
      step="0.01"
      min="0"
      required
    />
  </div>

  <div class="form-check form-switch mb-3">
    <input
      class="form-check-input"
      type="checkbox"
      id="acceptConvocations"
      name="acceptConvocations"
      checked
    />
    <label class="form-check-label" for="acceptConvocations"
      >Acepta Convocatoria</label
    >
  </div>

  <div class="form-check form-switch mb-3">
    <input
      class="form-check-input"
      type="checkbox"
      id="statusCongress"
      name="statusCongress"
      checked
    />
    <label class="form-check-label" for="statusCongress">Activo</label>
  </div>

  <div class="mb3">
    <label for="quota" class="form-label">Cupo Máximo de Participantes</label>
    <input type="number" class="form-control" id="quota" name="quota" min="1" />
  </div>

  <div class="d-flex gap-3 mt-4">
    <button type="submit" class="btn btn-primary btn-lg">Guardar</button>
    <button type="button" class="btn btn-secondary btn-lg" id="btn-cancel">
      Cancelar
    </button>
  </div>
</form>
