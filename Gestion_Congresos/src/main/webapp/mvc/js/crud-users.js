

async function editUser(userId) {
  try {
    const res = await fetch(`${contextPath}/SVGetUserDetails?id=${userId}`);
    if (!res.ok) throw new Error("Error en la petición");

    const html = await res.text();
    showContent(html + `<button class="btn btn-secondary mt-3" id="btn-back">Regresar</button>`);
  } catch (err) {
    console.error(err);
    alert("Error al cargar los detalles del usuario");
  }
}

async function deactivateUser(userId) {
  if (!confirm("¿Está seguro de que desea desactivar este usuario?")) return;

  try {
    const res = await fetch(`${contextPath}/SVDeactivateUser?id=${userId}`, {
      method: "POST",
    });
    if (!res.ok) throw new Error("Error en la petición");

    alert("Usuario desactivado exitosamente");
    loadUsers(); 
  } catch (err) {
    console.error(err);
    alert("Error al desactivar el usuario");
  }
}

async function activateUser(userId) {
  if (!confirm("¿Está seguro de que desea activar este usuario?")) return;
  
  try {
    const res = await fetch(`${contextPath}/SVActivateUser?id=${userId}`, {
      method: "POST",
    });
    if (!res.ok) throw new Error("Error en la petición");

    alert("Usuario activado exitosamente");
    loadUsers(); 
  } catch (err) {
    console.error(err);
    alert("Error al activar el usuario");
  }
}






//* Clics en botonesde acciones por usuario
document.addEventListener("click", (e) => {

    const target = e.target;

  if (target.classList.contains("btn-edit-user")) {
    const userId = target.dataset.userId;
    editUser(userId); 
  } else if (target.classList.contains("btn-delete-user")) {
    const userId = target.dataset.userId;
    deactivateUser(userId); 
  } else if (target.classList.contains("btn-activate-user")) {
    const userId = target.dataset.userId;
    activateUser(userId);
  }
});