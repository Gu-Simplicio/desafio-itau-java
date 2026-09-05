import exibeEstatisticas from "../view/exibeEstatisticas.js";

function recebePeriodo() {
    const inPeriodo = document.querySelector("#inPeriodo");

    return inPeriodo.value || 60;
}

async function recebeEstatisticas(){
    const parametro = {
        periodo: recebePeriodo()
    };

    const URL_GET = `http://localhost:8080/unibanco/estatistica?${new URLSearchParams(parametro)}`;

    try {
        const response = await fetch(URL_GET);
        const result = await response.json();

        if(!response.ok || !result.sucesso) {
            throw new Error("Http error status: ", response.status);
        }
        console.log(result)
        const estatistica = result.dados;

        exibeEstatisticas(estatistica);
    } catch( error ) {
        console.error("Erro ao receber estatísticas: ", error);
    }
}

export default recebeEstatisticas;