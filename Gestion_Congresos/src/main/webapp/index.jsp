<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Agora</title>
    <jsp:include page="/includes/resources.jsp" />
  </head>
  <body class="d-flex flex-column min-vh-100">
    <jsp:include page="/includes/navbar.jsp" />
    <main class="flex-fill">
      <header class="bg-light py-5">
        <div class="container text-center">
          <h1 class="fw-bold">Bienvenido al Sistema de Gestión de Congresos</h1>
          <p class="lead mt-3">
            Regístrate y participa en congresos, ponencias y talleres diseñados
            para impulsar el conocimiento.
          </p>
          <a
            href="${pageContext.servletContext.contextPath}/mvc/user/create-user.jsp"
            class="btn btn-primary btn-lg mt-3"
          >
            Comenzar Ahora
          </a>
        </div>
      </header>

      <section class="py-5">
        <div class="container">
          <div class="row text-center">
            <div class="col-md-4">
              <div class="card shadow-sm border-0 h-100">
                <div class="card-body">
                  <h5 class="card-title fw-bold">📚 Ponencias</h5>
                  <p class="card-text">
                    Aprende de expertos en diversas áreas a través de charlas y
                    conferencias inspiradoras.
                  </p>
                </div>
              </div>
            </div>
            <div class="col-md-4 mt-4 mt-md-0">
              <div class="card shadow-sm border-0 h-100">
                <div class="card-body">
                  <h5 class="card-title fw-bold">🛠️ Talleres</h5>
                  <p class="card-text">
                    Participa en talleres prácticos para aplicar y reforzar tus
                    conocimientos.
                  </p>
                </div>
              </div>
            </div>
            <div class="col-md-4 mt-4 mt-md-0">
              <div class="card shadow-sm border-0 h-100">
                <div class="card-body">
                  <h5 class="card-title fw-bold">🌐 Congresos</h5>
                  <p class="card-text">
                    Conéctate con profesionales, estudiantes y académicos en
                    congresos de nivel nacional e internacional.
                  </p>
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
