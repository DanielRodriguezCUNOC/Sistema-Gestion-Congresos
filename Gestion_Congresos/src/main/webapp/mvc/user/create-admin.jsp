<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Registro de Usuario</title>
    <jsp:include page="/includes/resources.jsp" />
  </head>
  <body class="bg-light">
    <jsp:include page="/includes/navbar.jsp" />

    <main
      class="d-flex align-items-center justify-content-center"
      style="min-height: calc(100vh - 56px)"
    >
      <div class="card shadow-lg" style="width: 32rem">
        <div class="card-body">
          <h3 class="card-title text-center mb-4">Registro de Usuario</h3>

          <c:if test="${not empty errorMessage}">
            <div
              class="alert alert-danger alert-dismissible fade show"
              role="alert"
            >
              ${error}
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="alert"
                aria-label="Close"
              ></button>
            </div>
          </c:if>
          <form
            action="${pageContext.servletContext.contextPath}/SVCreateAdmin"
            method="post"
            enctype="multipart/form-data"
          >
            <div class="mb-3">
              <label for="nombre" class="form-label">Nombre completo</label>
              <input
                type="text"
                class="form-control"
                id="nombre"
                name="nombre"
                value="${param.nombre}"
                required
              />
            </div>

            <div class="mb-3">
              <label for="usuario" class="form-label">Usuario</label>
              <input
                type="text"
                class="form-control"
                id="usuario"
                name="usuario"
                value="${param.usuario}"
                required
              />
            </div>

            <div class="mb-3">
              <label for="password" class="form-label">Contraseña</label>
              <input
                type="password"
                class="form-control"
                id="password"
                name="password"
                value="${param.password}"
                required
              />
            </div>

            <div class="mb-3">
              <label for="correo" class="form-label">Correo</label>
              <input
                type="email"
                class="form-control"
                id="correo"
                name="email"
                value="${param.email}"
                required
              />
            </div>

            <div class="mb-3">
              <label for="identificacion" class="form-label"
                >Identificación personal</label
              >
              <input
                type="text"
                class="form-control"
                id="identificacion"
                name="ID"
                value="${param.ID}"
                required
              />
            </div>

            <div class="mb-3">
              <label for="telefono" class="form-label">Teléfono</label>
              <input
                type="text"
                class="form-control"
                id="telefono"
                name="phone"
                value="${param.phone}"
                required
              />
            </div>

            <div class="mb-3">
              <label for="organizacion" class="form-label">Organización</label>
              <input
                type="text"
                class="form-control"
                id="organizacion"
                name="organization"
                value="${param.organization}"
                required
              />
            </div>

            <div class="mb-3">
              <label for="rol" class="form-label">Rol</label>
              <select class="form-select" id="rol" name="typeUser" required>
                <option value="">Seleccione un rol</option>
                <c:forEach var="rol" items="${rols}">
                  <option value="${rol.id_rol}">${rol.nombre}</option>
                </c:forEach>
              </select>
            </div>

            <div class="mb-3">
              <label for="foto" class="form-label">Fotografía</label>
              <input
                type="file"
                class="form-control"
                id="foto"
                name="fotografia"
                accept="image/*"
                required
              />
            </div>

            <div class="d-grid">
              <button type="submit" class="btn btn-primary">Registrar</button>
            </div>
          </form>
        </div>
      </div>
    </main>

    <jsp:include page="/includes/footer.jsp" />
  </body>
</html>
