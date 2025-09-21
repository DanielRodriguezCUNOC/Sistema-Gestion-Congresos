<%@ page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form
  id="form-create-activity"
  class="card p-4 shadow-lg"
  style="max-width: 36rem; margin: auto"
>
  <h3 class="text-center mb-4">Crear Actividad</h3>

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
    <label for="nameCongress" class="form-label">Nombre Congreso</label>
    <input
      type="text"
      class="form-control"
      id="nameCongress"
      name="nameCongress"
      placeholder="COMPDES 2025"
    />
  </div>

  <div class="mb-3">
    <label for="nameRoom" class="form-label">Salón</label>
    <input type="text" class="form-control" id="nameRoom" name="nameRoom" />
  </div>

  <div class="mb-3">
    <label for="tipeActivity" class="form-label">Tipo de Actividad</label>
    <select class="form-select" id="tipeActivity" name="tipeActivity" required>
      <option value="">Seleccione un tipo</option>
      <option value="1">PONENCIA</option>
      <option value="2">TALLER</option>
    </select>
  </div>

  <div class="mb-3">
    <label for="nameActivity" class="form-label">Nombre de la Actividad</label>
    <input
      type="text"
      class="form-control"
      id="nameActivity"
      name="nameActivity"
      required
    />
  </div>

  <div class="mb-3">
    <label for="descriptionActivity" class="form-label">Descripción</label>
    <textarea
      class="form-control"
      id="descriptionActivity"
      name="descriptionActivity"
      rows="3"
    ></textarea>
  </div>

  <div class="mb-3">
    <label for="dateActivity" class="form-label">Fecha</label>
    <input
      type="date"
      class="form-control"
      id="dateActivity"
      name="dateActivity"
      required
    />
  </div>

  <div class="mb-3">
    <label for="timeStarting" class="form-label">Hora de Inicio</label>
    <input
      type="time"
      class="form-control"
      id="timeStarting"
      name="timeStarting"
      required
    />
  </div>

  <div class="mb-3">
    <label for="timeEnding" class="form-label">Hora de Fin</label>
    <input
      type="time"
      class="form-control"
      id="timeEnding"
      name="timeEnding"
      required
    />
  </div>

  <div class="mb-3">
    <label for="workshopQuota" class="form-label">Cupo del Taller</label>
    <input
      type="number"
      class="form-control"
      id="workshopQuota"
      name="workshopQuota"
    />
  </div>

  <div class="d-flex gap-3 mt-4">
    <button type="submit" class="btn btn-primary btn-lg">Guardar</button>
    <button type="button" class="btn btn-secondary btn-lg" id="btn-cancel">
      Cancelar
    </button>
  </div>
</form>
