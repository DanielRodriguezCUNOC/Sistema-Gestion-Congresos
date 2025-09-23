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

// ==========================
// Realizar Pago Congreso
// ==========================
async function payCongress(id) {
    if (!confirm("¿Está seguro de que desea realizar el pago para este congreso?")) return;

    try {
        const res = await fetch(`${contextPath}/SVPaymentCongress`, {
            method: "POST",
            body: new URLSearchParams({
                action: "pago",
                idCongress: id,
            }),
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            }
        });

        if (!res.ok) throw new Error("Error al procesar el pago");

        const result = await res.json();
        if (result.success) {
            alert(result.message);
            loadPaymentCongress();
        } else {
            alert("Error: " + result.message);
        }
    } catch (err) {
        console.error(err);
        alert("Ocurrió un error al procesar el pago.");
    }
}


//* clicks en botones dinamicos
document.addEventListener("click", function(event) {
    switch (event.target.id) {
        
         case "btn-inscription-congress":
            loadParticipantCongresses();
            return;
        case "btn-available-workshop":
            loadAvailableWorkshops();
            return;
        case "btn-pay-congress":
            loadPaymentCongress();
            return;
        case "btn-back":
            showCards();
            return;
    }

});

