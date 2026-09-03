import montaTabela from "../view/montaTabela.js";

async function recebeTransacoes() {
    const URL_GET = `http://localhost:8080/unibanco/transacao`;

    try {
        const response = await fetch(URL_GET);

        const result = await response.json();

        if(!response.ok || !result.sucesso) {
            throw new Error("Http error status: ", response.status);
        }

        const transacoes = result.dados;
        
        montaTabela(transacoes);
    } catch(error) {
        console.error("Erro ao receber transações: ", error);
    }
}

export default recebeTransacoes;