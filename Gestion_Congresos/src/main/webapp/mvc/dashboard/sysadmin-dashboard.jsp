<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
              src="data:image/jpeg;base64,${admin.photoBase64}"
              alt="Foto de ${admin.name}"
              class="rounded-circle border border-white"
              width="80"
              height="80"
            />
          </div>
          <div>
            <h2 class="fw-bold mb-0">Hola, ${admin.name}</h2>
            <p class="mb-0">Panel de administración del sistema</p>
          </div>
        </div>
      </header>

      <section class="py-5">
        <div class="container">
          <div class="row g-4">
            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">👤 Crear Administrador Congreso</h5>
                  <p>
                    Agregar nuevos administradores para congresos específicos.
                  </p>
                  <a href="crear-admin-congreso.jsp" class="btn btn-dark"
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
                  <a href="registrar-institucion.jsp" class="btn btn-dark"
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
                  <a href="administrar-usuarios.jsp" class="btn btn-dark"
                    >Administrar</a
                  >
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </main>
    <jsp:include page="/includes/footer.jsp" />
  </body>
</html>
