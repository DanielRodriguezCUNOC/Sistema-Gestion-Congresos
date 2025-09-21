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
              src="data:image/jpeg;base64,${conferenceAdmin.photoBase64}"
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
      <section class="py-5">
        <div class="container">
          <!-- Funciones de Participante -->
          <h3 class="mb-4">🙋 Funciones de Participante</h3>
          <div class="row g-4 mb-5">
            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">Panel de Participante</h5>
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
            </div>
          </div>

          <!-- Acciones de Congreso -->
          <h3 class="mb-4">🏛️ Acciones de Congreso</h3>
          
          <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">🏛️ Registrar Congresos</h5>
                  <p>Registrar congresos en el sistema.</p>
                  <a href="#" id="btn-create-congress" class="btn btn-secondary"
                    >Registrar</a
                  >
                </div>
              </div>
            </div>
          </div>

          <div class="row g-4 mb-5">
            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">📢 Abrir Convocatoria</h5>
                  <p>Inicia la convocatoria para un nuevo congreso.</p>
                  <a
                    href="#"
                    id="btn-open-convocatory"
                    class="btn btn-secondary"
                    >Abrir</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">👩‍🔬 Registrar Comité Científico</h5>
                  <p>Asignar miembros del comité científico.</p>
                  <a href="definir-comite.jsp" class="btn btn-secondary"
                    >Registrar</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">🎤 Registrar Ponente Invitado</h5>
                  <p>Agregar ponentes invitados al congreso.</p>
                  <a
                    href="#"
                    id="btn-create-guest-speaker"
                    class="btn btn-secondary"
                    >Registrar</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">👩‍🔬 Gestionar Comité Científico</h5>
                  <p>Administrar miembros del comité científico.</p>
                  <a href="definir-comite.jsp" class="btn btn-secondary"
                    >Gestionar</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">🚪 Registrar Salones</h5>
                  <p>Registrar salones o auditorios.</p>
                  <a href="#" id="btn-create-room" class="btn btn-secondary"
                    >Registrar</a
                  >
                </div>
              </div>
            </div>

             <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">🏛️ Gestionar Congresos</h5>
                  <p>Modificar congresos en el sistema.</p>
                  <a href="#" id="btn-edit-congress" class="btn btn-secondary"
                    >Gestionar</a
                  >
                </div>
              </div>
            </div>
          </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">🚪 Gestionar Salones</h5>
                  <p>Modificar salones o auditorios.</p>
                  <a href="#" id="btn-edit-room" class="btn btn-secondary"
                    >Gestionar</a
                  >
                </div>
              </div>
            </div>

           

          <!-- Acciones de Actividad -->
          <h3 class="mb-4">📅 Acciones de Actividad</h3>
          <div class="row g-4 mb-5">
            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">Registrar Actividad</h5>
                  <p>Agregar nuevas actividades dentro de un congreso.</p>
                  <a href="#" id="btn-create-activity" class="btn btn-secondary"
                    >Registrar</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">Gestionar Actividad</h5>
                  <p>Modificar información de actividades existentes.</p>
                  <a
                    href="#"
                    id="btn-administrate-activity"
                    class="btn btn-secondary"
                    >Gestionar</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">Listar Actividades</h5>
                  <p>Consultar todas las actividades de un congreso.</p>
                  <a href="#" id="btn-list-activities" class="btn btn-secondary"
                    >Listar</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">Eliminar Actividad</h5>
                  <p>Eliminar actividades no deseadas o canceladas.</p>
                  <a href="#" id="btn-delete-activity" class="btn btn-secondary"
                    >Eliminar</a
                  >
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">Registrar Asistencia</h5>
                  <p>
                    Registrar asistencia de participantes a las actividades.
                  </p>
                  <a href="#" id="btn-take-attendance" class="btn btn-secondary"
                    >Registrar</a
                  >
                </div>
              </div>
            </div>
          </div>

          <!-- Reportes -->
          <h3 class="mb-4">📊 Reportes</h3>
          <div class="row g-4">
            <div class="col-md-4">
              <div class="card shadow-sm h-100">
                <div class="card-body text-center">
                  <h5 class="fw-bold">Reporte Participantes</h5>
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
                  <h5 class="fw-bold">Reporte Asistencia</h5>
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
                  <h5 class="fw-bold">Reporte Reserva Taller</h5>
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
                  <h5 class="fw-bold">Reporte Ganancias</h5>
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

      <div id="content" class="container mt-4" style="display: none"></div>
    </main>
    <jsp:include page="/includes/footer.jsp" />
    <script>
      const contextPath = "${pageContext.request.contextPath}";
    </script>
    <script src="${pageContext.request.contextPath}/mvc/js/admin-conference-dashboard.js"></script>
    <script src="${pageContext.request.contextPath}/mvc/js/crud-conference-admins.js"></script>
  </body>
</html>
