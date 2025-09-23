//================================
// Inscribir a un participante en un congreso
//=================================

async function inscribirse(id) {
    if (!confirm("¿Está seguro de que desea inscribirse en este congreso?")) return;
    
    try {
        const res = await fetch(`${contextPath}/SVParticipantInscription`, {
            method: "POST",
            body: new URLSearchParams({
                action: "inscripcion",
                idCongress: id,
            }),
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            }
        });

        if (!res.ok) throw new Error("Error al inscribirse");

        const result = await res.json();
        if (result.success) {
            alert(result.message);
            loadParticipantCongresses();
        } else {
            alert("Error: " + result.message);
        }
    } catch (err) {
        console.error(err);
        alert("Ocurrió un error al inscribirse.");
    }
}

// ==========================
// Reservar taller
// ==========================
async function reserveWorkshop(id) {
    if (!confirm("¿Está seguro de que desea reservar este taller?")) return;

    try {
        const res = await fetch(`${contextPath}/SVParticipantInscription`, {
            method: "POST",
            body: new URLSearchParams({
                action: "reserva",
                idWorkshop: id,
            }),
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            }
        });

        if (!res.ok) throw new Error("Error al reservar");

        const result = await res.json();
        if (result.success) {
            alert(result.message);
            loadAvailableWorkshops();
        } else {
            alert("Error: " + result.message);
        }
    } catch (err) {
        console.error(err);
        alert("Ocurrió un error al reservar el taller.");
    }
}

async function inscribirseTaller(idTaller) {
        fetch("inscribirTaller", {
          method: "POST",
          headers: { "Content-Type": "application/x-www-form-urlencoded" },
          body: "idTaller=" + idTaller,
        })
          .then((response) => {
            if (response.ok) {
              alert("Inscripción realizada con éxito ✅");
              location.reload();
            } else {
              alert("Error al inscribirse ❌");
            }
          })
          .catch((error) => {
            console.error("Error:", error);
            alert("Error de conexión ❌");
          });
      }


//* clicks en botones dinamicos
document.addEventListener("click", function(event) {
    switch (event.target.id) {
        
         case "btn-inscription-congress":
            loadParticipantCongresses();
            return;
        case "btn-available-workshops":
            loadAvailableWorkshops();
            return;
        case "btn-back":
            showCards();
            return;
    }

});

