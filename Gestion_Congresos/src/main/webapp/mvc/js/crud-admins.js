
// --- Cargar formulario de edición ---
async function loadEditAdminForm(adminId) {
  try {
    const url = `${contextPath}/SVAdminCRUD?action=loadEditForm&id=${adminId}`;
    console.log("Fetch URL:", url);

    const res = await fetch(url);
    if (!res.ok) throw new Error("Error al cargar formulario de edición");

    const html = await res.text();
    showContent(html);

    
    const form = document.getElementById("edit-admin-form");
    if (form) {
      form.addEventListener("submit", (e) => {
        e.preventDefault();
        submitEditAdmin(form);
      });
    }

  } catch (err) {
    console.error(err);
    alert("Error al cargar el formulario de edición");
  }
}


async function submitEditAdmin(form) {
  try {
    const formData = new FormData(form);
    formData.append("action", "edit");
    
    const res = await fetch(`${contextPath}/SVAdminCRUD`, {
      method: "POST",
      body: new URLSearchParams(formData)
    });

    if (!res.ok) throw new Error("Error al editar el administrador");

    const result = await res.json();
    console.log("Respuesta del servidor:", result);

    if (result.success) {
      alert(result.message);
      loadAdmins(); 
    } else {
      alert("Error: " + result.message);
    }

  } catch (err) {
    console.error(err);
    alert("Error al editar el administrador");
  }
}


async function activateAdmin(adminId) {
  if (!confirm("¿Está seguro de que desea activar este administrador?")) return;

  try {
    const res = await fetch(`${contextPath}/SVAdminCRUD`, {
      method: "POST",
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
      body: `action=activate&id=${adminId}`
    });

    if (!res.ok) throw new Error("Error en la petición");

    const result = await res.json();
    alert(result.message);
    loadAdmins();

  } catch (err) {
    console.error(err);
    alert("Error al activar el administrador");
  }
}


async function deactivateAdmin(adminId) {
  if (!confirm("¿Está seguro de que desea desactivar este administrador?")) return;

  try {
    const res = await fetch(`${contextPath}/SVAdminCRUD`, {
      method: "POST",
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
      body: `action=deactivate&id=${adminId}`
    });

    if (!res.ok) throw new Error("Error en la petición");

    const result = await res.json();
    alert(result.message);
    loadAdmins();

  } catch (err) {
    console.error(err);
    alert("Error al desactivar el administrador");
  }
}

// ==========================
// Listeners (delegación de eventos)
// ==========================
document.addEventListener("click", (e) => {
  const target = e.target;

  // --- Botón Editar ---
  if (target.classList.contains("btn-edit-admin")) {
    const adminId = target.getAttribute("data-admin-id");
    console.log("Edit Admin ID:", adminId);
    loadEditAdminForm(adminId);
  }

  // --- Botón Activar ---
  if (target.classList.contains("btn-activate-admin")) {
    const adminId = target.getAttribute("data-admin-id");
    activateAdmin(adminId);
  }

  // --- Botón Desactivar ---
  if (target.classList.contains("btn-delete-admin")) {
    const adminId = target.getAttribute("data-admin-id");
    deactivateAdmin(adminId);
  }

  // --- Botón Cancelar en formulario de edición ---
  if (target.id === "btn-cancel") {
    loadAdmins();
  }
});

// --- Delegación para submit de formulario dinámico ---
document.addEventListener("submit", (e) => {
  if (e.target && e.target.id === "edit-admin-form") {
    e.preventDefault();
    submitEditAdmin(e.target);
  }
});
