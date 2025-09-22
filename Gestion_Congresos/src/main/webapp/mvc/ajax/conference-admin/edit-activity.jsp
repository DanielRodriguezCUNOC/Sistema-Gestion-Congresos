<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form id="form-edit-activity" class="needs-validation" novalidate>
  <!-- Campos ocultos -->
  <input type="hidden" name="idActivity" value="${activity.idActividad}" />
  <input type="hidden" name="idTipoActividad" value="${activity.idTipoActividad}" />
  <input type="hidden" name="idSalon" value="${activity.idSalon}" />
  <input type="hidden" name="idCongreso" value="${activity.idCongreso}" />

  <h3 class="text-center mb-4">Editar Actividad</h3>

  <!-- Nombre de la Actividad -->
  <div class="mb-3">
    <label for="nombre_actividad" class="form-label">Nombre de la Actividad</label>
    <input
      type="text"
      class="form-control"
      id="nombre_actividad"
      name="nombre_actividad"
      value="${activity.nombreActividad}"
      maxlength="150"
      required
    />
  </div>

  <!-- Fecha y Horarios -->
  <div class="row">
    <div class="col-md-6 mb-3">
      <label for="fecha" class="form-label">Fecha</label>
      <input
        type="date"
        class="form-control"
        id="fecha"
        name="fecha"
        value="${activity.fecha}"
        required
      />
    </div>
    <div class="col-md-3 mb-3">
      <label for="hora_inicio" class="form-label">Hora de Inicio</label>
      <input
        type="time"
        class="form-control"
        id="hora_inicio"
        name="hora_inicio"
        value="${activity.horaInicio}"
        required
      />
    </div>
    <div class="col-md-3 mb-3">
      <label for="hora_fin" class="form-label">Hora de Fin</label>
      <input
        type="time"
        class="form-control"
        id="hora_fin"
        name="hora_fin"
        value="${activity.horaFin}"
        required
      />
    </div>
  </div>

  <!-- Descripción -->
  <div class="mb-3">
    <label for="descripcion" class="form-label">Descripción</label>
    <textarea
      class="form-control"
      id="descripcion"
      name="descripcion"
      maxlength="255"
      required
    >${activity.descripcion}</textarea>
  </div>

  <!-- Cupo taller -->
  <div class="mb-3">
    <label for="cupo_taller" class="form-label">Cupo (solo si es taller)</label>
    <input
      type="number"
      class="form-control"
      id="cupo_taller"
      name="cupo_taller"
      value="${activity.cupoTaller != null ? activity.cupoTaller : ''}"
      min="1"
      <c:if test="${activity.idTipoActividad != 2}">disabled</c:if>
    />
  </div>

  <!-- Botones -->
  <div class="d-grid gap-2 mt-4">
    <button type="submit" class="btn btn-primary btn-lg">Guardar</button>
    <button type="button" class="btn btn-secondary btn-lg" id="btn-cancel">Cancelar</button>
  </div>
</form>