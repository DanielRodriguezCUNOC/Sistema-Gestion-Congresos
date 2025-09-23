//==================================
// Cargar Congresos para Inscripcion
// =================================

async function loadParticipantCongresses() {
    try {
        const res = await fetch(`${contextPath}/SVListCongressForParticipant`);
        if (!res.ok) throw new Error("Error en la petición");

        const html = await res.text();
        showContent(html);
    } catch (error) {
        console.error(error);
        alert("Ocurrió un error al cargar el listado de congresos.");
    }
}

// ==========================
// Reservar taller
// ==========================
async function loadAvailableWorkshops() {
    try {
        const res = await fetch(`${contextPath}/SVListWorkshopsForParticipant`);
        if (!res.ok) throw new Error("Error en la petición");

        const html = await res.text();
        showContent(html);
    } catch (error) {
        console.error(error);
        alert("Ocurrió un error al cargar el listado de talleres.");
    }
}




