<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Dashboard Administrador - Agora</title>
    <jsp:include page="/includes/resources.jsp" />
  </head>
  <body class="d-flex flex-column min-vh-100">
    <main class="flex-fill">
      <header class="bg-dark text-white py-4">
        <div class="container d-flex align-items-center">
          <div class="me-3">
            <img
              src="data:image/jpeg;base64,${sysAdmin.photoBase64}"
              alt="Foto de ${sysAdmin.name}"
              class="rounded-circle border border-white"
              width="80"
              height="80"
            />
          </div>
          <div>
            <h2 class="fw-bold mb-0">Hola, ${sysAdmin.name}</h2>
            <p class="mb-0">Panel de administración del sistema</p>
          </div>
        </div>
      </header>

      <section class="py-5">
        <div class="container">
          <div id="cards-container" class="row g-4">
            <div class="card shadow-sm h-100">
              <div class="card-body text-center">
                <h5 class="fw-bold">🙋 Funciones de Participante</h5>
                <p>
                  Accede al panel de participante para inscribirte, pagar,
                  reservar talleres y más.
                </p>
                <a
                  href="${pageContext.request.contextPath}/SVParticipantUser"
                  class="btn btn-primary"
                  >Ir al Panel</a
                >
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">👤 Crear Administrador Congreso</h5>
                  <p>
                    Agregar nuevos administradores para congresos específicos.
                  </p>
                  <a
                    href="#"
                    id="btn-create-conference-admin"
                    class="btn btn-dark"
                    >Crear</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">💰 Generar Reporte Ganancias</h5>
                  <p>Visualiza las ganancias de los congresos y talleres.</p>
                  <a href="reporte-ganancias.jsp" class="btn btn-dark"
                    >Generar</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">📊 Generar Reporte Congresos</h5>
                  <p>Obtén reportes detallados de los congresos realizados.</p>
                  <a href="reporte-congresos.jsp" class="btn btn-dark"
                    >Generar</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">🏫 Registrar Institución</h5>
                  <p>Agrega nuevas instituciones participantes.</p>
                  <a href="#" id="btn-create-institution" class="btn btn-dark"
                    >Registrar</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">🛠️ Modificar Comisión</h5>
                  <p>
                    Gestiona los miembros de las comisiones de cada congreso.
                  </p>
                  <a href="modificar-comision.jsp" class="btn btn-dark"
                    >Modificar</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">👥 Administrar Usuarios</h5>
                  <p>Gestiona usuarios registrados y sus permisos.</p>
                  <a href="#" id="administrate-users" class="btn btn-dark"
                    >Administrar</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">
                    🧑‍💻 Listar Administradores de Congresos
                  </h5>
                  <p>Gestiona usuarios encargados de los Congresos.</p>
                  <a
                    href="#"
                    id="btn-list-conference-admins"
                    class="btn btn-dark"
                    >Ver Listado</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">🏫 Listar Instituciones</h5>
                  <p>Gestiona las Instituciones que albergan Congresos.</p>
                  <a href="#" id="btn-list-institutions" class="btn btn-dark"
                    >Ver Listado</a
                  >
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      <div id="content" class="container mt-4" style="display: none"></div>
    </main>
    <jsp:include page="/includes/footer.jsp" />
    <script>
      const contextPath = "${pageContext.request.contextPath}";
    </script>
    <script src="${pageContext.request.contextPath}/mvc/js/sysAdmin-dashboard.js"></script>
    <script src="${pageContext.request.contextPath}/mvc/js/crud-admins.js"></script>
  </body>
</html>
