// ==========================
// Cargar Formulario Editar Actividad
// ==========================

async function loadEditActivityForm(idActivity) {
  try {
    const res = await fetch(
      `${contextPath}/SVCRUDActivity?action=loadEditForm&idActivity=${idActivity}`
    );
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
  const formData = new URLSearchParams(new FormData(form));
  formData.append("action", "edit");
  try {
    const res = await fetch(`${contextPath}/SVActivityCRUD`, {
      method: "POST",
      body: formData,
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
    });

    if (!res.ok) {
      alert("Error al editar la actividad.");
      return;
    }

    const result = await res.json();
    if (result.success) {
      alert(result.message);
      loadActivities();
    } else {
      alert("Error: " + result.message);
    }
  } catch (err) {
    console.error(err);
    alert("Ocurrió un error al editar la actividad.");
  }
}

// ==========================
// Eliminar Actividad
// ==========================

async function deleteActivity(idActivity) {
  if (!confirm("¿Está seguro de que desea eliminar esta actividad?")) return;
  try {
    const res = await fetch(`${contextPath}/SVActivityCRUD`, {
      method: "POST",
      body: new URLSearchParams({
        action: "delete",
        idActivity: idActivity,
      }),
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      }
    });

    if (!res.ok) throw new Error("Error al eliminar actividad");

    const result = await res.json();
    if (result.success) {
      alert(result.message);
      loadActivities();
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
async function loadEditCongressForm(idCongress) {
  try {
    const res = await fetch(
      `${contextPath}/SVCongressCRUD?action=loadEditForm&idCongress=${idCongress}`
    );
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
  const formData = new URLSearchParams(new FormData(form));
  formData.append("action", "edit");

  try {
    const res = await fetch(`${contextPath}/SVCongressCRUD`, {
      method: "POST",
      body: formData,
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
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
// Cargar Formulario Editar Salon
// ==========================

async function loadEditRoomForm(idRoom) {
  try {
    const res = await fetch(
      `${contextPath}/SVRoomCRUD?action=loadEditForm&idRoom=${idRoom}`
    );
    if (!res.ok) throw new Error("Error al cargar formulario");

    const html = await res.text();
    showContent(html);
  } catch (err) {
    console.error(err);
    alert("Ocurrió un error al cargar el formulario.");
  }
}

// ==========================
// Enviar Formulario Editar Salon
// ==========================
async function submitEditRoom(form) {
  const formData = new URLSearchParams(new FormData(form));
  formData.append("action", "edit");

  try {
    const res = await fetch(`${contextPath}/SVRoomCRUD`, {
      method: "POST",
      body: formData,
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
    });

    if (!res.ok) throw new Error("Error al editar el salón");

    const result = await res.json();
    if (result.success) {
      alert(result.message);
      loadRooms();
    } else {
      alert("Error: " + result.message);
    }
  } catch (err) {
    console.error(err);
    alert("Ocurrió un error al editar el salón.");
  }
}

// ==========================
// Eliminar Salón
// ==========================
async function deleteRoom(idRoom) {
  if (!confirm("¿Está seguro de que desea eliminar este salón?")) return;
  try {
    const res = await fetch(`${contextPath}/SVRoomCRUD`, {
      method: "POST",
      body: new URLSearchParams({
        action: "delete",
        idRoom: idRoom,
      }),
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      }
    });

    if (!res.ok) throw new Error("Error al eliminar salón");

    const result = await res.json();
    if (result.success) {
      alert(result.message);
      loadRooms();
    } else {
      alert("Error: " + result.message);
    }
  } catch (err) {
    console.error(err);
    alert("Ocurrió un error al eliminar el salón.");
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
    case "form-edit-room":
      submitEditRoom(event.target);
      break;
    default:
      console.warn("Formulario no manejado:", event.target.id);
  }
});

//* Clics en botones dinamicos
document.addEventListener("click", function (event) {
  const idActivity = null;
  const idCongress = null;

  switch (event.target.id) {
    case "btn-take-attendance":
      loadTakeAttendanceForm();
      break;

    case "btn-delete-activity":
      const idActivityToDelete = event.target.getAttribute("data-activity-id");
      if (idActivityToDelete !== null) {
        deleteActivity(idActivityToDelete);
      }

      break;

    case "btn-edit-activity":
      const idActivity = event.target.getAttribute("data-activity-id");
      if (idActivity !== null) {
        loadEditActivityForm(idActivity);
      }

      break;

    case "btn-edit-congress":
      const idCongress = event.target.getAttribute("data-congress-id");
      if (idCongress !== null) {
        loadEditCongressForm(idCongress);
      }
      break;

    case "btn-edit-room":
      const idRoom = event.target.getAttribute("data-room-id");
      if (idRoom !== null) {
        loadEditRoomForm(idRoom);
      }
      break;

    case "btn-delete-room":
      const idRoomToDelete = event.target.getAttribute("data-room-id");
      if (idRoomToDelete !== null) {
        deleteRoom(idRoomToDelete);
      }
      break;

    default:
      console.warn("Botón no manejado:", event.target.id);
  }
});
