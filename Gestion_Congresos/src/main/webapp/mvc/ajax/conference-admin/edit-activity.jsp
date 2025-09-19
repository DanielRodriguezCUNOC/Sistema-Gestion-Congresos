<@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form id="actividadForm" class="needs-validation" novalidate>
  <input type="hidden" name="id_actividad" value="${activity.idActividad}" />

  <h3 class="text-center mb-4">Editar Actividad</h3>

  <div class="mb-3">
    <label for="nombre_salon" class="form-label">Nombre del Salón</label>
    <input
      type="text"
      class="form-control"
      id="nombre_salon"
      name="nombre_salon"
      required
    />
  </div>

  <div class="mb-3">
    <label for="nombre_congreso" class="form-label">Nombre del Congreso</label>
    <input
      type="text"
      class="form-control"
      id="nombre_congreso"
      name="nombre_congreso"
      required
    />
  </div>

  <div class="mb-3">
    <label for="tipo_actividad" class="form-label">Tipo de Actividad</label>
    <select
      class="form-select"
      id="tipo_actividad"
      name="tipo_actividad"
      required
    >
      <option value="" selected disabled>Seleccione...</option>
      <option value="PONENCIA">PONENCIA</option>
      <option value="TALLER">TALLER</option>
    </select>
  </div>

  <div class="mb-3">
    <label for="nombre_actividad" class="form-label"
      >Nombre de la Actividad</label
    >
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

  <div class="mb-3">
    <label for="descripcion" class="form-label">Descripción</label>
    <textarea
      class="form-control"
      id="descripcion"
      name="descripcion"
      value="${activity.descripcion}"
      maxlength="255"
      required
    ></textarea>
  </div>

  <div class="mb-3">
    <label for="cupo_taller" class="form-label">Cupo (solo si es taller)</label>
    <input
      type="number"
      class="form-control"
      id="cupo_taller"
      name="cupo_taller"
      value="${activity.cupoTaller}"
      min="1"
      disabled
    />
  </div>

  <button type="submit" class="btn btn-success w-100">Guardar</button>
  <button type="button" class="btn btn-secondary btn-lg" id="btn-cancel">
    Cancelar
  </button>
</form>
