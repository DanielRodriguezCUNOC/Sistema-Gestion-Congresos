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
    <jsp:include page="/includes/navbar.jsp" />

    <main
      class="d-flex align-items-center justify-content-center"
      style="min-height: calc(100vh - 56px)"
    >
      <div class="card shadow-lg" style="width: 22rem">
        <div class="card-body">
          <h3 class="card-title text-center mb-4">Iniciar Sesión</h3>

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
            action="${pageContext.servletContext.contextPath}/SVLogin"
            method="post"
          >
            <div class="mb-3">
              <label for="inputUser" class="form-label">Usuario</label>
              <input
                type="user"
                class="form-control"
                id="inputUser"
                name="user"
                placeholder="Ingrese su usuario"
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
