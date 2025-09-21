<%@ page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container mt-4">
  <h3 class="mb-3 text-primary">Editar Datos del Salón</h3>

  <form method="post" class="needs-validation" novalidate>
    <div class="mb-3">
      <label for="nombre_institucion" class="form-label"
        >Nombre de la Institución</label
      >
      <input
        type="text"
        class="form-control"
        id="nombre_institucion"
        name="nameInstitution"
        required
      />
      <div class="invalid-feedback">
        Por favor ingrese el nombre de la institución.
      </div>
    </div>

    <div class="mb-3">
      <label for="capacidad" class="form-label">Capacidad</label>
      <input
        type="number"
        class="form-control"
        id="capacidad"
        name="capacity"
        value="${room.capacidad}"
        required
      />
      <div class="invalid-feedback">Por favor ingrese la capacidad.</div>
    </div>

    <div class="mb-3">
      <label for="nombre_salon" class="form-label">Nombre del Salón</label>
      <input
        type="text"
        class="form-control"
        id="nombre_salon"
        name="nameRoom"
        value="${room.nombreSalon}"
        required
      />
      <div class="invalid-feedback">Por favor ingrese el nombre del salón.</div>
    </div>

    <div class="mb-3">
      <label for="ubicacion" class="form-label">Ubicación</label>
      <input
        type="text"
        class="form-control"
        id="ubicacion"
        name="address"
        value="${room.direccion}"
        required
      />
      <div class="invalid-feedback">Por favor ingrese la ubicación.</div>
    </div>

    <div class="d-grid gap-2 mt-4">
      <button type="submit" class="btn btn-primary btn-lg">Guardar</button>
      <button type="button" class="btn btn-secondary btn-lg" id="btn-cancel">
        Cancelar
      </button>
    </div>
  </form>
</div>
