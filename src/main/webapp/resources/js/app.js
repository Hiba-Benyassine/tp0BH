document.addEventListener('click', async (event) => {
  const button = event.target.closest('[data-copy]');
  if (!button) return;
  const field = document.getElementById(button.dataset.copy);
  if (!field) return;
  try {
    await navigator.clipboard.writeText(field.value);
    const original = button.textContent;
    button.textContent = 'Copié';
    window.setTimeout(() => { button.textContent = original; }, 1200);
  } catch (_) {
    field.select();
    document.execCommand('copy');
  }
});
