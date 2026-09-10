function montaTabela(transacoes) {
    const tabTransacoes = document.querySelector("#tabTransacoes");
    
    // apaga o body atual
    const tbodyAtual = tabTransacoes.querySelector("tbody");
    tbodyAtual.remove();

    //cria um novo
    const tbody = document.createElement("tbody");
    tabTransacoes.appendChild(tbody);

    transacoes.forEach(t => {
        // trata os valores recebidos  
        const valorTransacao = Number(t.valor).toFixed(2);
        const dataHoraTransacao = new Date(t.dataHora).toLocaleString();

        // cria a linha
        const tr = document.createElement("tr");
        tbody.appendChild(tr);

        // cria o elemento dos dados
        const valorTd = document.createElement("td");
        valorTd.textContent = `R$ ${valorTransacao}`;
        const dataHoraTd = document.createElement("td");
        dataHoraTd.textContent = dataHoraTransacao;

        tr.appendChild(valorTd);
        tr.appendChild(dataHoraTd);
    });
}

export default montaTabela;