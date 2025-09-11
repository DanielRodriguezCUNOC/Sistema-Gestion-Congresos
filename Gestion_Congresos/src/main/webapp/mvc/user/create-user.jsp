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

          <c:if test="${not empty sessionScope.error}">
            <div
              class="alert alert-danger alert-dismissible fade show"
              role="alert"
            >
              <strong>Error:</strong> <c:out value="${sessionScope.error}" />
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="alert"
                aria-label="Close"
              ></button>
            </div>
            <c:remove var="error" scope="session" />
          </c:if>

          <c:if test="${not empty sessionScope.success}">
            <div
              class="alert alert-success alert-dismissible fade show"
              role="alert"
            >
              <c:out value="${sessionScope.success}" />
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="alert"
                aria-label="Close"
              ></button>
            </div>
            <c:remove var="success" scope="session" />
          </c:if>

          <form
            action="${pageContext.servletContext.contextPath}/SVCreateUser"
            method="post"
            enctype="multipart/form-data"
          >
            <div class="mb-3">
              <label for="nombre" class="form-label">Nombre completo</label>
              <input
                type="text"
                class="form-control"
                id="nombre"
                name="name"
                required
              />
            </div>

            <div class="mb-3">
              <label for="usuario" class="form-label">Usuario</label>
              <input
                type="text"
                class="form-control"
                id="usuario"
                name="user"
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
                required
              />
            </div>

            <div class="mb-3">
              <label for="foto" class="form-label"
                >Fotografía (menor a 20MB)</label
              >
              <input
                type="file"
                class="form-control"
                id="foto"
                name="photo"
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
