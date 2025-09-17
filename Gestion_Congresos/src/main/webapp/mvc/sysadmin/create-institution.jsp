<%@ page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3>Registrar Institución</h3>

<c:if test="${not empty error}">
  <div class="alert alert-danger">${error}</div>
</c:if>

<c:if test="${not empty success}">
  <div class="alert alert-success">${success}</div>
</c:if>

<form id="form-create-institution" action="SVCreateInstitution" method="post">
  <div class="mb-3">
    <label for="nombreInstitucion" class="form-label fw-bold"
      >Nombre de la Institución</label
    >
    <input
      type="text"
      class="form-control"
      id="nombreInstitucion"
      name="name_institution"
      placeholder="Ejemplo: Universidad San Carlos de Guatemala"
      required
    />
  </div>

  <div class="mb-3">
    <label for="ubicacion" class="form-label fw-bold">Ubicación</label>
    <input
      type="text"
      class="form-control"
      id="ubicacion"
      name="address_institution"
      placeholder="Ejemplo: Ciudad, País"
      required
    />
  </div>

  <div class="d-grid">
    <button type="submit" class="btn btn-primary">Registrar</button>
  </div>
  <div class="d-grid">
    <button type="button" id="btn-cancel" class="btn btn-secondary mt-2">
      Cancelar
    </button>
  </div>
</form>
