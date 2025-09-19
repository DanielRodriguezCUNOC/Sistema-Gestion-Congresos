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
    <label for="idInstitucion" class="form-label">Institución Encargada</label>
    <input
      type="number"
      class="form-control"
      id="idInstitucion"
      name="idInstitucion"
      placeholder="Ingrese el nombre de la institución"
      required
      min="1"
    />
  </div>

  <div class="mb-3">
    <label for="nombreCongreso" class="form-label">Nombre del Congreso</label>
    <input
      type="text"
      class="form-control"
      id="nombreCongreso"
      name="nombreCongreso"
      required
    />
  </div>

  <div class="mb-3">
    <label for="fechaInicio" class="form-label">Fecha de Inicio</label>
    <input
      type="date"
      class="form-control"
      id="fechaInicio"
      name="fechaInicio"
      required
    />
  </div>

  <div class="mb-3">
    <label for="fechaFin" class="form-label">Fecha de Fin</label>
    <input
      type="date"
      class="form-control"
      id="fechaFin"
      name="fechaFin"
      required
    />
  </div>

  <div class="mb-3">
    <label for="precio" class="form-label">Precio</label>
    <input
      type="number"
      class="form-control"
      id="precio"
      name="precio"
      step="0.01"
      min="0"
      required
    />
  </div>

  <div class="form-check form-switch mb-3">
    <input
      class="form-check-input"
      type="checkbox"
      id="aceptaConvocatoria"
      name="aceptaConvocatoria"
      checked
    />
    <label class="form-check-label" for="aceptaConvocatoria"
      >Acepta Convocatoria</label
    >
  </div>

  <div class="form-check form-switch mb-3">
    <input
      class="form-check-input"
      type="checkbox"
      id="estado"
      name="estado"
      checked
    />
    <label class="form-check-label" for="estado">Activo</label>
  </div>

  <div class="mb3">
    <label for="cupo" class="form-label">Cupo Máximo de Participantes</label>
    <input type="number" class="form-control" id="cupo" name="cupo" min="1" />
  </div>

  <div class="d-grid gap-2 mt-4">
    <button type="submit" class="btn btn-primary btn-lg">
      Guardar Congreso
    </button>
    <button type="button" class="btn btn-secondary btn-lg" id="btn-cancel">
      Cancelar
    </button>
  </div>
</form>
