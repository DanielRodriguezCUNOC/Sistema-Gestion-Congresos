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
    <label for="idCongreso" class="form-label">Congreso</label>
    <select class="form-select" id="idCongreso" name="idCongreso" required>
      <option value="">Seleccione un congreso</option>
      <c:forEach var="congreso" items="${congressList}">
        <option value="${congreso.idCongreso}">
          ${congreso.nombreCongreso}
        </option>
      </c:forEach>
    </select>
  </div>

  <div class="mb-3">
    <label for="idSalon" class="form-label">Salón</label>
    <select class="form-select" id="idSalon" name="idSalon" required>
      <option value="">Seleccione un salón</option>
      <c:forEach var="salon" items="${roomList}">
        <option value="${salon[0]}">${salon[1]} (${salon[2]})</option>
      </c:forEach>
    </select>
  </div>

  <div class="mb-3">
    <label for="idTipoActividad" class="form-label">Tipo de Actividad</label>
    <select
      class="form-select"
      id="idTipoActividad"
      name="idTipoActividad"
      required
    >
      <option value="">Seleccione un tipo</option>
      <c:forEach var="tipo" items="${activityTypes}">
        <option value="${tipo.idTipo}">${tipo.nombreTipo}</option>
      </c:forEach>
    </select>
  </div>

  <div class="mb-3">
    <label for="nombreActividad" class="form-label"
      >Nombre de la Actividad</label
    >
    <input
      type="text"
      class="form-control"
      id="nombreActividad"
      name="nombreActividad"
      required
    />
  </div>

  <div class="mb-3">
    <label for="descripcion" class="form-label">Descripción</label>
    <textarea
      class="form-control"
      id="descripcion"
      name="descripcion"
      rows="3"
    ></textarea>
  </div>

  <div class="mb-3">
    <label for="fecha" class="form-label">Fecha</label>
    <input type="date" class="form-control" id="fecha" name="fecha" required />
  </div>

  <div class="mb-3">
    <label for="horaInicio" class="form-label">Hora de Inicio</label>
    <input
      type="time"
      class="form-control"
      id="horaInicio"
      name="horaInicio"
      required
    />
  </div>

  <div class="mb-3">
    <label for="horaFin" class="form-label">Hora de Fin</label>
    <input
      type="time"
      class="form-control"
      id="horaFin"
      name="horaFin"
      required
    />
  </div>

  <div class="mb-3">
    <label for="cupoTaller" class="form-label">Cupo del Taller</label>
    <input
      type="number"
      class="form-control"
      id="cupoTaller"
      name="cupoTaller"
      min="1"
      required
    />
  </div>

  <div class="d-flex gap-3 mt-4">
    <button type="submit" class="btn btn-primary btn-lg">
      Guardar Actividad
    </button>
    <button type="button" class="btn btn-secondary btn-lg" id="btn-cancel">
      Cancelar
    </button>
  </div>
</form>
