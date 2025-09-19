// ==========================
// Cargar Formulario Editar Actividad
// ==========================

async function loadEditActivityForm(activityId) {
  try {
    const res = await fetch(`${contextPath}/SVCRUDActivity?id=${activityId}`);
    if (!res.ok) throw new Error("Error al cargar formulario");

    const html = await res.text();
    showContent(html);
  } catch (err) {
    console.error(err);
    alert("Ocurrió un error al cargar el formulario.");
  }
}

// ==========================
// Enviar Formulario Editar Actividad
// ==========================

async function submitEditActivity(form) {
  const formData = new FormData(form);
  formData.append("action", "edit");

  const res = await fetch(`${contextPath}/SVCRUDActivity`, {
    method: "POST",
    body: new URLSearchParams(formData),
  });

  if (!res.ok) {
    alert("Error al editar la actividad.");
    return;
  }

  const result = await res.json();
  if (result.success) {
    alert(result.message);
    loadConferences();
  } else {
    alert("Error: " + result.message);
  }
}

  // ==========================
  // Eliminar Actividad
  // ==========================

  async function deleteActivity(activityId) {
    if (!confirm("¿Está seguro de que desea eliminar esta actividad?")) return;
    try {
      const res = await fetch(`${contextPath}/SVCRUDActivity`, {
        method: "POST",
        body: new URLSearchParams({
          action: "delete",
          id: activityId,
        }),
      });

      if (!res.ok) throw new Error("Error al eliminar actividad");

      const result = await res.json();
      if (result.success) {
        alert(result.message);
        loadConferences();
      } else {
        alert("Error: " + result.message);
      }
    } catch (err) {
      console.error(err);
      alert("Ocurrió un error al eliminar la actividad.");
    }
  }


// ==========================
// Cargar Formulario Editar Congreso
// ==========================
async function loadEditCongressForm(congressId) {
  try {
    const res = await fetch(`${contextPath}/SVCRUDCongress?id=${congressId}`);
    if (!res.ok) throw new Error("Error al cargar formulario");

    const html = await res.text();
    showContent(html);
  } catch (err) {
    console.error(err);
    alert("Ocurrió un error al cargar el formulario.");
  }
}

// ==========================
// Enviar Formulario Editar Congreso
// ==========================
async function submitEditCongress(form) {
  const formData = new FormData(form);
  formData.append("action", "edit");

  try {
    const res = await fetch(`${contextPath}/SVCRUDCongress`, {
      method: "POST",
      body: new URLSearchParams(formData),
    });

    if (!res.ok) throw new Error("Error al editar el congreso");

    const result = await res.json();
    if (result.success) {
      alert(result.message);
      loadConferences();
    } else {
      alert("Error: " + result.message);
    }
  } catch (err) {
    console.error(err);
    alert("Ocurrió un error al editar el congreso.");
  }
}

// ==========================
// Delegación de eventos
// ==========================
document.addEventListener("submit", function (event) {
  event.preventDefault();
  switch (event.target.id) {
    case "form-edit-activity":
      submitEditActivity(event.target);
      break;
    case "form-take-attendance":
      submitTakeAttendance(event.target);
      break;
    case "form-edit-congress":
      submitEditCongress(event.target);
      break;
    default:
      console.warn("Formulario no manejado:", event.target.id);
  }
});

//* Clics en botones dinamicos
document.addEventListener("click", function (event) {
     const activityId = null;
     const congressId = null;

  switch (event.target.id) {
  
    case "btn-take-attendance":
      loadTakeAttendanceForm();
      break;
  
      case " btn-delete-activity":

       if( activityId = event.target.getAttribute("data-activity-id") !== null){
           deleteActivity(activityId);
       }
        
        break;
   
        case "btn-edit-activity":
        if( activityId = event.target.getAttribute("data-activity-id") !== null){
        loadEditActivityForm(activityId);
        }
        break;
   
        case "btn-edit-congress":
        if( congressId = event.target.getAttribute("data-congress-id") !== null){
        loadEditCongressForm(congressId);
        }
        break;
   
        default:
      console.warn("Botón no manejado:", event.target.id);
  }
});
