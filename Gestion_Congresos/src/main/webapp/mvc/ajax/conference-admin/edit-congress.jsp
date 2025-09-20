<div class="card shadow">
  <div class="card-header bg-primary text-white text-center">
    <h4>Editar Congreso</h4>
  </div>
  <div class="card-body">
    <form id="congresoForm" class="needs-validation" novalidate>
      <div class="mb-3">
        <label for="nombre" class="form-label">Nombre</label>
        <input
          type="text"
          class="form-control"
          id="nombre"
          name="nombre"
          required
        />
      </div>

      <div class="mb-3">
        <label for="descripcion" class="form-label">Descripción</label>
        <textarea
          class="form-control"
          id="descripcion"
          name="descripcion"
          required
        ></textarea>
      </div>

      <div class="row">
        <div class="col-md-6 mb-3">
          <label for="fecha_inicio" class="form-label">Fecha de Inicio</label>
          <input
            type="date"
            class="form-control"
            id="fecha_inicio"
            name="fecha_inicio"
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
        />
        <label class="form-check-label" for="acepta_convocatoria">
          ¿Acepta Convocatoria?
        </label>
      </div>

      <div class="d-grid gap-2 mt-4">
        <button type="submit" class="btn btn-primary btn-lg">Guardar</button>
        <button type="button" class="btn btn-secondary btn-lg" id="btn-cancel">
          Cancelar
        </button>
      </div>
    </form>
    <div id="mensaje" class="mt-3"></div>
  </div>
</div>
