document.addEventListener("change", async function(event) {
  if (event.target.id === "nameCongress") {
    const congressName = event.target.value;
    const roomSelect = document.getElementById("nameRoom");
    roomSelect.innerHTML = '<option value="">Seleccione un salón</option>';

    if (!congressName) return;

    try {
      const res = await fetch(`${contextPath}/SVListRoomCreateActivity?congress=${encodeURIComponent(congressName)}`);
      if (!res.ok) throw new Error("Error al obtener salones");

      const rooms = await res.json();
      rooms.forEach(name => {
        const option = document.createElement("option");
        option.value = name;
        option.textContent = name;
        roomSelect.appendChild(option);
      });

    } catch (err) {
      console.error(err);
      alert("No se pudieron cargar los salones.");
    }
  }
});
