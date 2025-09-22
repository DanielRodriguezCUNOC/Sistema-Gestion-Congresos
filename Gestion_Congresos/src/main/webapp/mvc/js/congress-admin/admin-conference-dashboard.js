

//* Funcionalidades relacionadas a Creacion de Ponentes Invitados*/

// ==========================
// Cargar formulario Crear Ponente Invitado
// ==========================

async function loadCreateGuestSpeakerForm() {
  try {
    const res = await fetch(`${contextPath}/SVCreateGuestsSpeaker`);
    if (!res.ok) throw new Error("Error al cargar formulario");

    const html = await res.text();
    showContent(html);
  } catch (err) {
    console.error(err);
    alert("Ocurrió un error al cargar el formulario.");
  }
}

// ==========================
// Enviar formulario Crear Ponente Invitado
// ==========================
async function submitCreateGuestSpeaker(form) {
  const formData = new FormData(form);

  try {
    const res = await fetch(`${contextPath}/SVCreateGuestsSpeaker`, {
      method: "POST",
      body: formData,
    });

    if (!res.ok) throw new Error("Error en creación");

    //* El servlet de creación devuelve JSON
    const result = await res.json();

    if (result.success) {
      alert(result.message);

      //! Cargar listado después de crear
      loadGuestSpeakers();
    } else {
      alert(result.message);
    }
  } catch (err) {
    console.error(err);
    alert("Ocurrió un error al enviar el formulario.");
  }
}

// ==========================
// Cargar listado de Ponentes Invitados
// ==========================
async function loadGuestSpeakers() {
  try {
    const res = await fetch(`${contextPath}/SVListGuestsSpeaker`);
    if (!res.ok) throw new Error("Error al cargar listado");

    const html = await res.text();
    showContent(html  + `<button class="btn btn-secondary mt-3" id="btn-back">Regresar</button>`);
  } catch (err) {
    console.error(err);
    alert("Ocurrió un error al cargar el listado.");
  }
}

//* Funcionalidades relacionadas a creacion de Congresos*/

// ==========================
// Enviar formulario Crear Congreso
// ==========================
async function submitCreateConference(form) {
  const formData = new URLSearchParams(new FormData(form));

  try {
    const res = await fetch(`${contextPath}/SVCreateCongress`, {
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
      loadConferences();
    } else {
      alert("Error: " + result.message);
    }
  } catch (err) {
    console.error(err);
    alert("Error al crear el Congreso");
  }
}

// ==========================
// Cargar formulario Crear Congreso
// ==========================

async function loadCreateConferenceForm() {
  try {
    const res = await fetch(`${contextPath}/SVCreateCongress`);
    if (!res.ok) throw new Error("Error al cargar formulario");

    const html = await res.text();
    showContent(html);
  } catch (err) {
    console.error(err);
    alert("Ocurrió un error al cargar el formulario.");
  }
}

//=========================
// Listar Congresos
//=========================
async function loadConferences() {
  try {
    const res = await fetch(`${contextPath}/SVListCongress`);
    if (!res.ok) throw new Error("Error en la petición");

    const html = await res.text();
    showContent(html  + `<button class="btn btn-secondary mt-3" id="btn-back">Regresar</button>`);
  } catch (err) {
    console.error(err);
    alert("Ocurrió un error al cargar el listado de congresos.");
  }
}

  //* Funcionalidades relacionadas a creacion de Actividades*/

  // ==========================
  // Cargar formulario Crear Actividad
  // ==========================
  async function loadCreateActivityForm() {
    try {

      console.log("Lllamando fetch SVCreateActivity");
      const res = await fetch(`${contextPath}/SVCreateActivity`);
      if (!res.ok) throw new Error("Error al cargar formulario");

      const html = await res.text();
      showContent(html);
     
    } catch (err) {
      console.error(err);
      alert("Ocurrió un error al cargar el formulario.");
    }
  }

  // ==========================
  // Enviar formulario Crear Actividad
  // ==========================
  async function submitCreateActivity(form) {
    const formData = new URLSearchParams(new FormData(form));

    try {
         const res = await fetch(`${contextPath}/SVCreateActivity`, {
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
        loadActivities();
      } else {
        alert("Error: " + result.message);
      }
    } catch (err) {
      console.error(err);
      alert("Error al crear la Actividad");
    }
  }

  // ==========================
  // Listar Actividades
  // ==========================
  async function loadActivities() {
    try {
      const res = await fetch(`${contextPath}/SVListActivity`);
      if (!res.ok) throw new Error("Error en la petición");

      const html = await res.text();
      showContent(html  + `<button class="btn btn-secondary mt-3" id="btn-back">Regresar</button>`);
    } catch (err) {
      console.error(err);
      alert("Ocurrió un error al cargar el listado de actividades.");
    }
  }

  //* Funcionalidades relacionadas a creacion de Salones/

  // ==========================
  // Cargar formulario Crear Salon
  // ==========================
  async function loadCreateRoomForm() {
    try {
      const res = await fetch(`${contextPath}/SVCreateRoom`);
      if (!res.ok) throw new Error("Error al cargar formulario");

      const html = await res.text();
      showContent(html);
    } catch (err) {
      console.error(err);
      alert("Ocurrió un error al cargar el formulario.");
    }
  }

// ==========================
// Enviar formulario Crear Salon
// ==========================
async function submitCreateRoom(form) {
    const formData = new URLSearchParams(new FormData(form));
    
    try {
            const res = await fetch(`${contextPath}/SVCreateRoom`, {
      method: "POST",
      body: formData,
       headers: {
    'Content-Type': 'application/x-www-form-urlencoded'
  }
    });

        if (!res.ok) throw new Error("Error en creación");

        const result = await res.json();

        if (result.success) {
            alert(result.message);
            loadRooms();
        } else {
            alert("Error: " + result.message);
        }
    } catch (err) {
        console.error(err);
        alert("Error al crear el Salón");
    }
}

  // ==========================
  // Listar Salones
  // ==========================
  async function loadRooms() {
    try {
      const res = await fetch(`${contextPath}/SVListRoom`);
      if (!res.ok) throw new Error("Error en la petición");

      const html = await res.text();
      showContent(html  + `<button class="btn btn-secondary mt-3" id="btn-back">Regresar</button>`);
    } catch (err) {
      console.error(err);
      alert("Ocurrió un error al cargar el listado de salones.");
    }
  }

  // ==========================
  // Delegación de eventos
  // ==========================
  document.addEventListener("submit", function (event) {
    event.preventDefault();
    switch (event.target.id) {
      case "form-create-guest-speaker":
        submitCreateGuestSpeaker(event.target);
        break;
      case "form-create-congress":
        submitCreateConference(event.target);
        break;
      case "form-create-activity":
        submitCreateActivity(event.target);
        break;
      case "form-create-room":
        submitCreateRoom(event.target);
        break;
      case "form-create-conference-admin":
        submitCreateConferenceAdmin(event.target);
        break;
      default:
        console.warn("Formulario no manejado:", event.target.id);
    }
  });

  //* Clics en botones dinamicos
  document.addEventListener("click", function (event) {
    switch (event.target.id) {
      case "btn-create-guest-speaker":
        loadCreateGuestSpeakerForm();
        break;
      case "btn-list-guest-speakers":
        loadGuestSpeakers();
        break;
      case "btn-create-congress":
        loadCreateConferenceForm();
        break;
      case "btn-list-congress":
        loadConferences();
        break;
      case "btn-create-activity":
        loadCreateActivityForm();
        break;
      case "btn-list-activities":
        loadActivities();
        break;
      case "btn-create-room":
        loadCreateRoomForm();
        break;
      case "btn-list-rooms":
        loadRooms();
        break;
      case "btn-create-conference-admin":
        loadCreateConferenceAdminForm();
        break;
      case "btn-list-conference-admins":
        loadConferenceAdmins();
        break;
      case "btn-administrate-activity":
        loadActivities();
        break;
      case "btn-back":
      case "btn-cancel":
        showCards();
        break;
    }
  });

