import exibeEstatisticas from "../view/exibeEstatisticas.js";
import exibeModal from "../view/exibeModal.js";

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
        console.table(result.dados)
        const estatistica = result.dados;

        exibeModal("sucesso", "Estatísticas recebidas com sucesso!");
        exibeEstatisticas(estatistica);
    } catch( error ) {
        exibeModal("erro", "Erro ao receber estatísticas")
        console.error("Erro ao receber estatísticas: ", error);
    }
}

export default recebeEstatisticas;