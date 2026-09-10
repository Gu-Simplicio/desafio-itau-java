import exibeModal from "../view/exibeModal.js";
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
        
        exibeModal("sucesso", "Transações recebidas com sucesso!");
        montaTabela(transacoes);
    } catch(error) {
        exibeModal("erro", "Erro ao receber transações");
        console.error("Erro ao receber transações: ", error);
    }
}
 
export default recebeTransacoes;