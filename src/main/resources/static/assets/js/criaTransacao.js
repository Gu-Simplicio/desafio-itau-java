const API_URL = "http://localhost:8080/unibanco";

async function enviarDados(dados) {
    try {
        const response = await fetch(`${API_URL}/transacao`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dados)
        });
        
        if(!response.ok) {
            throw new Error("HTTP error status: ", response.status);
        }

        return true;
    } catch(error) {
        console.error("Erro ao enviar dados: ", error);
    }
}

async function criaTransacao(e) {
    e.preventDefault();

    // elementos dos inputs
    const inValorTransacao = document.querySelector("#inValorTransacao");
    const inDataHoraTransacao = document.querySelector("#inDataHoraTransacao");

    const valorTransacao = Number(inValorTransacao.value).toFixed(2);
    const dataHoraTransacao = new Date(inDataHoraTransacao.value).toISOString();

    if(isNaN(valorTransacao) || valorTransacao == undefined || dataHoraTransacao == undefined || valorTransacao == null || dataHoraTransacao == null)  {
        alert("Valor inválido inserido!");
        return;
    }

    const dados = {
        valor: valorTransacao,
        dataHora: dataHoraTransacao
    }

    if(enviarDados(dados)) {
        alert("Tudo certo!");
    }
}

export default criaTransacao;