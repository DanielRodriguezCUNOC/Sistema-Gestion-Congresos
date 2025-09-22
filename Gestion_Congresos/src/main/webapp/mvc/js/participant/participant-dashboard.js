//==================================
// Cargar Congresos para Inscripcion
// =================================

async function loadParticipantCongresses() {

    try {
        const res = await fetch(`${contextPath}/SVListCongressForParticipant`);
        if (!res.ok) throw new Error("Error en la petición");

        const html = await res.text();
        showContent(html + `<button class="btn btn-secondary mt-3" id="btn-back">Regresar</button>`);
    } catch (error) {
        console.error(err);
        alert("Ocurrió un error al cargar el listado de congresos.");
    }
}






//* Clics en botones dinamicos
document.addEventListener("click",
function (event) {

    switch (event.target.id) {
        case "btn-participant-inscription":
            loadParticipantCongresses();
            return;

    }

    

});