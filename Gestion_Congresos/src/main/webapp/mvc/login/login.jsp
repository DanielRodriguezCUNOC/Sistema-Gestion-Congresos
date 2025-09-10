<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Login</title>
    <jsp:include page="/includes/resources.jsp" />
  </head>
  <body class="bg-light">
    <!-- Navbar arriba -->
    <jsp:include page="/includes/navbar.jsp" />

    <!-- Contenedor principal centrado -->
    <main
      class="d-flex align-items-center justify-content-center"
      style="min-height: calc(100vh - 56px)"
    >
      <div class="card shadow-lg" style="width: 22rem">
        <div class="card-body">
          <h3 class="card-title text-center mb-4">Iniciar Sesión</h3>
          <form
            action="${pageContext.servletContext.contextPath}/SVLogin"
            method="post"
          >
            <div class="mb-3">
              <label for="inputEmail" class="form-label">Usuario</label>
              <input
                type="email"
                class="form-control"
                id="inputEmail"
                name="user"
                placeholder="Ingrese su correo"
                required
              />
            </div>
            <div class="mb-3">
              <label for="inputPassword" class="form-label">Contraseña</label>
              <input
                type="password"
                class="form-control"
                id="inputPassword"
                name="password"
                placeholder="Ingrese su contraseña"
                required
              />
            </div>
            <div class="d-grid">
              <button type="submit" class="btn btn-primary">Ingresar</button>
            </div>
          </form>
        </div>
      </div>
    </main>

    <c:if test="${not empty error}">
      <div class="alert alert-danger mt-3 text-center">${error}</div>
    </c:if>

    <jsp:include page="/includes/footer.jsp" />
  </body>
</html>
