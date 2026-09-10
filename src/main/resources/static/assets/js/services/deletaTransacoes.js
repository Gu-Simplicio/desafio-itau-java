import exibeModal from "../view/exibeModal.js";

async function deletaTransacoes() {   
    const URL_DELETE = "http://localhost:8080/unibanco/transacao";

    if(confirm("Tem certeza que deseja deletar as transações?")) {
        try {
            const response = await fetch(URL_DELETE, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                }
            });

            const result = await response.json();

            if(!response.ok || !result.sucesso) {
                throw new Error("Http error status: ", response.status);
            }

            exibeModal("sucesso", "Transações deletadas com sucesso!");
        } catch(e) {
            exibeModal("erro", "Erro ao deletar transações");
            console.error("Erro ao deletar transações: ", e);
        }
    } 
}

export default deletaTransacoes;