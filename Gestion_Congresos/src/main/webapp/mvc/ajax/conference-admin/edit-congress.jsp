<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="card shadow">
  <div class="card-header bg-primary text-white text-center">
    <h4>Editar Congreso</h4>
  </div>
  <div class="card-body">
    <form id="form-edit-congress" class="needs-validation" novalidate>
      <div class="mb-3">
        <label for="nombre" class="form-label">Nombre Congreso</label>
        <input
          type="text"
          class="form-control"
          id="nombre"
          name="nombre"
          value="${congress.nombreCongreso}"
          required
        />
      </div>


      <div class="row">
        <div class="col-md-6 mb-3">
          <label for="fecha_inicio" class="form-label">Fecha de Inicio</label>
          <input
            type="date"
            class="form-control"
            id="fecha_inicio"
            name="fecha_inicio"
            value="${congress.fechaInicio}"
            required
          />
        </div>
        <div class="col-md-6 mb-3">
          <label for="fecha_fin" class="form-label">Fecha de Fin</label>
          <input
            type="date"
            class="form-control"
            id="fecha_fin"
            name="fecha_fin"
            value="${congress.fechaFin}"
            required
          />
        </div>
      </div>

      <div class="mb-3">
        <label for="costo" class="form-label">Costo</label>
        <input
          type="number"
          step="0.01"
          class="form-control"
          id="costo"
          name="costo"
          value="${congress.precio}"
          required
        />
      </div>

      <div class="form-check mb-3">
        <input
          class="form-check-input"
          type="checkbox"
          id="acepta_convocatoria"
          name="acepta_convocatoria"
          value="true"
          <c:if test="${congress.aceptaConvocatoria}">checked</c:if>
        />
        <label class="form-check-label" for="acepta_convocatoria">
          ¿Acepta Convocatoria?
        </label>
      </div>

      <div class="form-check mb-3">
        <input
          class="form-check-input"
          type="checkbox"
          id="estado"
          name="estado"
          value="true"
          <c:if test="${congress.estado}">checked</c:if>
        />
        <label class="form-check-label" for="acepta_convocatoria">
          ¿Está Activo?
        </label>
      </div>

      <div class="d-grid gap-2 mt-4">
        <button type="submit" class="btn btn-primary btn-lg">Guardar</button>
        <button type="button" class="btn btn-secondary btn-lg" id="btn-cancel">
          Cancelar
        </button>
      </div>
      <input type="hidden" name="congressId" value="${congress.idCongreso}" />
    </form>
    <div id="mensaje" class="mt-3"></div>
  </div>
</div>
