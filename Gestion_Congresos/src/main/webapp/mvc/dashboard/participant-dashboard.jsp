<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Dashboard - Agora</title>
    <jsp:include page="/includes/resources.jsp" />
  </head>
  <body class="d-flex flex-column min-vh-100">
    <main class="flex-fill">
      <header class="bg-primary text-white py-4">
        <div class="container d-flex align-items-center">
          <div class="me-3">
            <img
              src="data:image/jpeg;base64,${user.photoBase64}"
              alt="Foto de ${user.name}"
              class="rounded-circle border border-white"
              width="80"
              height="80"
            />
          </div>
          <div>
            <h2 class="fw-bold mb-0">Hola, ${user.name}</h2>
            <p class="mb-0">Bienvenido a tu panel de usuario</p>
          </div>
        </div>
      </header>

      <section class="py-5">
        <div class="container">
          <div class="row g-4">
            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">📝 Inscripción</h5>
                  <p>Elige el congreso en el que deseas inscribirte.</p>
                  <a
                    href="#"
                    id="btn-participant-inscription"
                    class="btn btn-primary"
                    >Inscribirse</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">🛠️ Reservación Taller</h5>
                  <p>Reserva tu lugar en un taller disponible.</p>
                  <a href="reservar-taller.jsp" class="btn btn-primary"
                    >Reservar</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">💳 Recargar Cartera</h5>
                  <p>Agrega saldo a tu cartera digital.</p>
                  <a href="recargar-cartera.jsp" class="btn btn-primary"
                    >Recargar</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">💵 Realizar Pago</h5>
                  <p>Paga la inscripción a un congreso.</p>
                  <a href="pago.jsp" class="btn btn-primary">Pagar</a>
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">📄 Presentar Trabajo</h5>
                  <p>Envía tu trabajo al comité científico.</p>
                  <a href="presentar-trabajo.jsp" class="btn btn-primary"
                    >Presentar</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">📚 Listar Congresos</h5>
                  <p>Consulta los congresos en los que has participado.</p>
                  <a href="listar-congresos.jsp" class="btn btn-primary"
                    >Ver Congresos</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">🎓 Obtener Diploma</h5>
                  <p>Descarga tus diplomas obtenidos.</p>
                  <a href="diplomas.jsp" class="btn btn-primary"
                    >Ver Diplomas</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">📅 Listar Actividades</h5>
                  <p>Revisa las actividades en las que participaste.</p>
                  <a href="listar-actividades.jsp" class="btn btn-primary"
                    >Ver Actividades</a
                  >
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </main>
    <jsp:include page="/includes/footer.jsp" />
    <script>
      const contextPath = "${pageContext.request.contextPath}";
    </script>
    <script src="${pageContext.request.contextPath}/mvc/js/dashboard-base.js"></script>
    <script src="${pageContext.request.contextPath}/mvc/js/participant-dashboard-base.js"></script>
  </body>
</html>
