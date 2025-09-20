<%@ page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form
  id="edit-admin-form"
  class="card p-4 shadow-lg"
  style="max-width: 32rem; margin: auto"
>
  <input type="hidden" name="id" value="${admin.idUser}" />

  <h3 class="text-center mb-4">Editar Administrador</h3>

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
    <label for="user" class="form-label">Usuario</label>
    <input
      type="text"
      class="form-control"
      id="user"
      name="user"
      value="${admin.user}"
      required
    />
  </div>

  <div class="mb-3">
    <label for="phone" class="form-label">Teléfono</label>
    <input
      type="text"
      class="form-control"
      id="phone"
      name="phone"
      value="${admin.phone}"
    />
  </div>

  <div class="mb-3">
    <label for="organization" class="form-label">Organización</label>
    <input
      type="text"
      class="form-control"
      id="organization"
      name="organization"
      value="${admin.organization}"
    />
  </div>
  <div class="mb-3">
    <label for="foto" class="form-label">Fotografía (Menor a 5MB)</label>
    <input
      type="file"
      class="form-control"
      id="foto"
      name="photo"
      accept="image/*"
    />
  </div>

  <div class="d-grid gap-2 mt-4">
    <button type="submit" class="btn btn-primary btn-lg">Guardar</button>
    <button type="button" class="btn btn-secondary btn-lg" id="btn-cancel">
      Cancelar
    </button>
  </div>
</form>
