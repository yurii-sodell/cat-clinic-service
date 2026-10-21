document.addEventListener('DOMContentLoaded', () => {
    const slider = document.getElementById('weight-in-kilos');
    const output = document.getElementById('weight-output');

    if (!slider || !output) {
        return;
    }

    slider.addEventListener('input', () => {
        output.textContent = slider.value;
    });
});