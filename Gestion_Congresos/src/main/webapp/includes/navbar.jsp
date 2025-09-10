<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-secondary">
  <div class="container">
    <a
      class="navbar-brand fw-bold bg-primary rounded px-2"
      href="${pageContext.servletContext.contextPath}/index.jsp"
    >
      Agora
    </a>
    <button
      class="navbar-toggler"
      type="button"
      data-bs-toggle="collapse"
      data-bs-target="#navbarNav"
      aria-controls="navbarNav"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a
            class="nav-link link-light fw-bold bg-primary rounded"
            href="${pageContext.servletContext.contextPath}/mvc/login/login.jsp"
            >Iniciar Sesión</a
          >
        </li>
        <li class="nav-item mx-2">
          <a
            class="nav-link link-light fw-bold bg-primary rounded"
            href="${pageContext.servletContext.contextPath}/mvc/login/register.jsp"
            >Registrarse</a
          >
        </li>
      </ul>
    </div>
  </div>
</nav>
