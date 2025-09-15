<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Dashboard Administrador Congreso - Agora</title>
    <jsp:include page="/includes/resources.jsp" />
  </head>
  <body class="d-flex flex-column min-vh-100">
    <main class="flex-fill">
      <header class="bg-secondary text-white py-4">
        <div class="container d-flex align-items-center">
          <div class="me-3">
            <img
              src="${conferenceAdmin.photoBase64}"
              alt="Foto de ${conferenceAdmin.name}"
              class="rounded-circle border border-white"
              width="80"
              height="80"
            />
          </div>
          <div>
            <h2 class="fw-bold mb-0">Hola, ${conferenceAdmin.name}</h2>
            <p class="mb-0">Panel de administración de congresos</p>
          </div>
        </div>
      </header>

      <!-- Acciones del Dashboard Administrador de Congresos -->
      <section class="py-5">
        <div class="container">
          <div class="row g-4">
            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">📢 Abrir Convocatoria</h5>
                  <p>Inicia la convocatoria para un nuevo congreso.</p>
                  <a href="abrir-convocatoria.jsp" class="btn btn-secondary"
                    >Abrir</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">👩‍🔬 Definir Comité Científico</h5>
                  <p>Asignar miembros al comité científico del congreso.</p>
                  <a href="definir-comite.jsp" class="btn btn-secondary"
                    >Definir</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">🎤 Registrar Ponente Invitado</h5>
                  <p>Agregar ponentes invitados al congreso.</p>
                  <a href="registrar-ponente.jsp" class="btn btn-secondary"
                    >Registrar</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">🏛️ Registrar Congreso</h5>
                  <p>Registrar un nuevo congreso en el sistema.</p>
                  <a href="registrar-congreso.jsp" class="btn btn-secondary"
                    >Registrar</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">📅 Registrar Actividad</h5>
                  <p>Agregar nuevas actividades dentro de un congreso.</p>
                  <a href="registrar-actividad.jsp" class="btn btn-secondary"
                    >Registrar</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">⚙️ Gestionar Actividad</h5>
                  <p>Modificar información de actividades existentes.</p>
                  <a href="gestionar-actividad.jsp" class="btn btn-secondary"
                    >Gestionar</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">📋 Listar Actividades</h5>
                  <p>Consultar todas las actividades de un congreso.</p>
                  <a href="listar-actividades.jsp" class="btn btn-secondary"
                    >Listar</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">❌ Eliminar Actividad</h5>
                  <p>Eliminar actividades no deseadas o canceladas.</p>
                  <a href="eliminar-actividad.jsp" class="btn btn-secondary"
                    >Eliminar</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">📝 Registrar Asistencia</h5>
                  <p>
                    Registrar asistencia de participantes a las actividades.
                  </p>
                  <a href="registrar-asistencia.jsp" class="btn btn-secondary"
                    >Registrar</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">📊 Reporte Participantes</h5>
                  <p>
                    Generar reporte de participantes por congreso o actividad.
                  </p>
                  <a href="reporte-participantes.jsp" class="btn btn-secondary"
                    >Generar</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">📈 Reporte Asistencia</h5>
                  <p>
                    Visualizar asistencia de cada participante a actividades.
                  </p>
                  <a href="reporte-asistencia.jsp" class="btn btn-secondary"
                    >Generar</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">📑 Reporte Reserva Taller</h5>
                  <p>Reporte de reservas realizadas en los talleres.</p>
                  <a href="reporte-reserva-taller.jsp" class="btn btn-secondary"
                    >Generar</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">💰 Reporte Ganancias</h5>
                  <p>Ver ganancias obtenidas de inscripciones y actividades.</p>
                  <a href="reporte-ganancias.jsp" class="btn btn-secondary"
                    >Generar</a
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
