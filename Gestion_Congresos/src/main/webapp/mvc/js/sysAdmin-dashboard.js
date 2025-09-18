// ==========================
// Funciones base
// ==========================
function showContent(html) {
  const cards = document.querySelector("#cards-container");
  const content = document.querySelector("#content");

  cards.style.display = "none";
  content.style.display = "block";
  content.innerHTML = html;
}

function showCards() {
  document.querySelector("#cards-container").style.display = "flex";
  document.querySelector("#content").style.display = "none";
  document.querySelector("#content").innerHTML = "";
}

//* Funcionalidades relacionadas a Creacion de Administrador de Congreso*/

// ==========================
// Cargar formulario Crear Admin Congreso
// ==========================
async function loadCreateConferenceAdminForm() {
  try {
    const res = await fetch(`${contextPath}/mvc/sysadmin/create-admin-conference.jsp`);
    if (!res.ok) throw new Error("Error al cargar formulario");

    const html = await res.text();
    showContent(html);

  } catch (err) {
    console.error(err);
    alert("Ocurrió un error al cargar el formulario.");
  }
}

// ==========================
// Enviar formulario Crear Admin Congreso
// ==========================
async function submitCreateConferenceAdmin(form) {
  const formData = new FormData(form);

  try {
    const res = await fetch(`${contextPath}/SVCreateConferenceAdmin`, {
      method: "POST",
      body: formData
    });

    if (!res.ok) throw new Error("Error en creación");

    //* El servlet de creación devuelve JSON
    const result = await res.json();

    if (result.success) {
      alert(result.message);
      
      //! Cargar listado después de crear
      loadConferenceAdmins();
    } else {
      alert("Error: " + result.message);
    }

  } catch (err) {
    console.error(err);
    alert("Error al crear el Administrador de Congreso");
  }
}

// ==========================
// Listar Administradores de Congreso
// ==========================
async function loadConferenceAdmins() {
  try {
    const res = await fetch(`${contextPath}/SVListConferenceAdmin`);
    if (!res.ok) throw new Error("Error en la petición");

    //* El servlet devuelve HTML (JSP renderizado)
    const html = await res.text();
    showContent(html + `<button class="btn btn-secondary mt-3" id="btn-back">Regresar</button>`);

  } catch (err) {
    console.error(err);
    alert("Error al cargar los administradores");
  }
}

//* Funcionalidades relacionadas a creacion de Instituciones*/

// ==========================
// Enviar formulario Crear Institución
// ==========================
async function submitCreateInstitution(form) {
  //! Usar URLSearchParams para enviar como application/x-www-form-urlencoded dado que FormData no funciona bien con servlets
  const formData = new URLSearchParams(new FormData(form));
  try {
    const res = await fetch(`${contextPath}/SVCreateInstitution`, {
      method: "POST",
      body: formData,
       headers: {
    'Content-Type': 'application/x-www-form-urlencoded'
  }
    });

    if (!res.ok) throw new Error("Error en creación");

    //* El servlet de creación devuelve JSON
    const result = await res.json();

    if (result.success) {
      alert(result.message);
      
      //! Cargar listado después de crear
      loadInstitutions();
    } else {
      alert("Error: " + result.message);
    }

  } catch (err) {
    console.error(err);
    alert("Error al crear la Institución");
  }
}

// ==========================
// Cargar Formulario Crear Institución
// ==========================

async function loadCreateInstitutionForm() {
  try {
    const res = await fetch(`${contextPath}/mvc/sysadmin/create-institution.jsp`);
    if (!res.ok) throw new Error("Error al cargar formulario");

    const html = await res.text();
    showContent(html);

  } catch (err) {
    console.error(err);
    alert("Ocurrió un error al cargar el formulario.");
  }
}

// ==========================
// Listar Instituciones
// ==========================
async function loadInstitutions() {
  try {
    const res = await fetch(`${contextPath}/SVListInstitution`);
    if (!res.ok) throw new Error("Error en la petición");

    //* El servlet devuelve HTML (JSP renderizado)
    const html = await res.text();
    showContent(html + `<button class="btn btn-secondary mt-3" id="btn-back">Regresar</button>`);
    
  } catch (err) {
    console.error(err);
    alert("Error al cargar las instituciones");
  }
}

//* Funcionalidades relacionadas a Administracion de Usuario*/

// ==========================
// Listar Administradores
// ==========================
async function loadAdmins() {
  try {
    const res = await fetch(`${contextPath}/SVListAdmin`);
    if (!res.ok) throw new Error("Error en la petición");

    //* El servlet devuelve HTML (JSP renderizado)
    const html = await res.text();
    showContent(html + `<button class="btn btn-secondary mt-3" id="btn-back">Regresar</button>`);
    
  } catch (err) {
    console.error(err);
    alert("Error al cargar los usuarios");
  }
}



// ==========================
// Delegación de eventos
// ==========================
document.addEventListener("submit", (e) => {
  e.preventDefault();
  switch (e.target.id) {
    case "form-create-admin":
      submitCreateConferenceAdmin(e.target);
      break;
    case "form-create-institution":
      submitCreateInstitution(e.target);
      break;
    case "edit-admin-form":
      submitEditAdmin(e.target);
      break;
  }
});

//* Clics en botones dinamicos
document.addEventListener("click", (e) => {
  switch (e.target.id) {
    case "btn-create-conference-admin":
      loadCreateConferenceAdminForm();
      return;
    case "btn-create-institution":
      loadCreateInstitutionForm();
      return;
    case "btn-list-conference-admins":
      loadConferenceAdmins();
      return;
    case "btn-list-institutions":
      loadInstitutions();
      return;
    case "administrate-users":
      loadAdmins();
      return;
    case "btn-cancel":
    case "btn-back":
      showCards();
      return;
  }
});


