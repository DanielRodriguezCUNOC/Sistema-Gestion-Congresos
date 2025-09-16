// ==========================
// Funciones base
// ==========================

// Ocultar cards y mostrar un contenido dinámico
function showContent(html) {
  const cards = document.querySelector("#cards-container");
  const content = document.querySelector("#content");

  cards.style.display = "none";
  content.style.display = "block";
  content.innerHTML = html;
}

// Restaurar las cards
function showCards() {
  document.querySelector("#cards-container").style.display = "flex";
  document.querySelector("#content").style.display = "none";
  document.querySelector("#content").innerHTML = "";
}

// ==========================
// Cargar formulario Crear Admin
// ==========================
async function loadCreateAdminForm() {
  try {
    const res = await fetch(`${contextPath}/mvc/sysadmin/create-admin-conference.jsp`);
    if (!res.ok) throw new Error("Error al cargar formulario");

    const html = await res.text();

    // Insertar HTML + botón cancelar
    showContent(html + `<button class="btn btn-secondary mt-3" id="btn-cancel">Cancelar</button>`);

  } catch (err) {
    console.error(err);
    alert("Ocurrió un error al cargar el formulario.");
  }
}

// ==========================
// Enviar formulario Crear Admin
// ==========================
async function submitCreateAdmin(form) {
  const formData = new FormData(form);

  try {
    const res = await fetch(`${contextPath}/SVCreateConferenceAdmin`, {
      method: "POST",
      body: formData
    });

    if (!res.ok) throw new Error("Error en creación");

    // 👉 parseamos JSON que devuelve el servlet
    const result = await res.json();

    if (result.success) {
      alert(result.message);
      // 👉 después de crear, cargamos el listado dinámicamente
      await loadConferenceAdmins();
    } else {
      alert("Error: " + result.message);
    }

  } catch (err) {
    console.error(err);
    alert("Error al crear el Administrador de Congreso");
  }
}


// ==========================
// Listar Administradores
// ==========================
async function loadConferenceAdmins() {
  try {
    const res = await fetch(`${contextPath}/SVListConferenceAdmin`);
    if (!res.ok) throw new Error("Error en la petición");

    const admins = await res.json(); // array de objetos

    let html = `<h3>Administradores de Congresos</h3>
                <table class="table table-striped">
                  <thead>
                    <tr>
                      <th>Nombre</th>
                      <th>Usuario</th>
                      <th>Correo</th>
                      <th>Identificación</th>
                      <th>Teléfono</th>
                      <th>Organización</th>
                      <th>Estado</th>
                      <th>Rol</th>
                    </tr>
                  </thead>
                  <tbody>`;

    admins.forEach(a => {
      html += `<tr>
                 <td>${a.nombre}</td>
                 <td>${a.usuario}</td>
                 <td>${a.correo}</td>
                 <td>${a.identificacion}</td>
                 <td>${a.telefono}</td>
                 <td>${a.organizacion}</td>
                 <td>${a.estado}</td>
                 <td>${a.rol}</td>
               </tr>`;
    });

    html += `</tbody></table>`;
    html += `<button class="btn btn-secondary mt-3" id="btn-cancel">Cancelar</button>`;

    showContent(html);

  } catch (err) {
    console.error(err);
    alert("Error al cargar los administradores");
  }
}



// ==========================
// Delegación de eventos
// ==========================
document.addEventListener("DOMContentLoaded", () => {
  // Botón Crear Admin
  const btnCreate = document.querySelector("#btn-create-conference-admin");
  if (btnCreate) {
    btnCreate.addEventListener("click", (e) => {
      e.preventDefault();
      loadCreateAdminForm();
    });
  }

  // Botón Listar Admins
  const btnList = document.querySelector("#btn-list-conference-sysAdmin");
  if (btnList) {
    btnList.addEventListener("click", (e) => {
      e.preventDefault();
      loadConferenceAdmins();
    });
  }
});

// Manejo de formularios enviados dinámicamente
document.addEventListener("submit", (e) => {
  if (e.target && e.target.id === "form-create-admin") {
    e.preventDefault();
    submitCreateAdmin(e.target);
  }
});

// Botón cancelar dinámico
document.addEventListener("click", (e) => {
  if (e.target && e.target.id === "btn-cancel") {
    e.preventDefault();
    showCards();
  }
});
