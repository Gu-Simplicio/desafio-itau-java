async function recebeEstatisticas(periodo){
    const URL_GET = `http://localhost:8080/unibanco/estatistica?periodo=${periodo}`;
    console.log("API: ", URL_GET);
    try {
        const response = await fetch(URL_GET);
        const result = await response.json();

        console.table(result);
        if(!response.ok || !result.sucesso) {
            throw new Error("Http error status: ", response.status);
        }

        const estatistica = result.dados;

        exibeEstatisticas(estatistica);
    } catch( error ) {
        console.error("Erro ao receber estatísticas: ", error);
    }
}

export default recebeEstatisticas;