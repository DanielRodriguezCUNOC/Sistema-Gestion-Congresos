// Funciones compartidas para dashboards
// Usar clases Bootstrap d-none para ocultar/mostrar contenedores
function showContent(html) {
  const cards = document.querySelector('#cards-container');
  const content = document.querySelector('#content');
  if (!cards || !content) return;
  cards.classList.add('d-none');
  content.classList.remove('d-none');
  content.innerHTML = html;
}

function showCards() {
  const cards = document.querySelector('#cards-container');
  const content = document.querySelector('#content');
  if (!cards || !content) return;
  cards.classList.remove('d-none');
  content.classList.add('d-none');
  content.innerHTML = '';
}
