function exibeEstatisticas(estatisticas) {
    const { count, avg, max, min, sum } = estatisticas;

    const spanAvg = document.querySelector("#spanAvg");
    const spanCount = document.querySelector("#spanCount");
    const spanMax = document.querySelector("#spanMax");
    const spanMin = document.querySelector("#spanMin");
    const spanSum = document.querySelector("#spanSum");

    spanAvg.textContent = Number(avg).toFixed(2);
    spanCount.textContent = count;
    spanMax.textContent = Number(max).toFixed(2);
    spanMin.textContent = Number(min).toFixed(2);
    spanSum.textContent = Number(sum).toFixed(2);
}

export default exibeEstatisticas;