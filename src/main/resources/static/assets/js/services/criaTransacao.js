import exibeModal from "../view/exibeModal.js";

async function enviarDados(dados) {
    const URL_POST = "http://localhost:8080/unibanco/transacao";
    
    try {
        const response = await fetch(URL_POST, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dados)
        });

        const result = await response.json();

        if(!response.ok || !result.sucesso) {
            throw new Error("HTTP error status: ", response.status);
        }

        exibeModal("sucesso", "Nova transação criada com sucesso")
        setInterval(window.location.reload(), 2000);
    } catch(error) {
        console.error("Erro ao enviar dados: ", error);
        exibeModal("erro", "Erro ao criar nova transação!");
    }
}

async function criaTransacao(e) {
    e.preventDefault();

    // elementos dos inputs
    const inValorTransacao = document.querySelector("#inValorTransacao");
    const inDataHoraTransacao = document.querySelector("#inDataHoraTransacao");

    const valorTransacao = Number(inValorTransacao.value).toFixed(2);
    const dataHoraTransacao = new Date(inDataHoraTransacao.value).toISOString();

    const agora = new Date().toISOString();

    if(isNaN(valorTransacao) || valorTransacao == undefined || dataHoraTransacao == undefined || valorTransacao == null || dataHoraTransacao == null || dataHoraTransacao > agora)  {
        alert("Valor inválido inserido!");
        return;
    }

    const dados = {
        valor: valorTransacao,
        dataHora: dataHoraTransacao
    }

    enviarDados(dados);
}

export default criaTransacao;