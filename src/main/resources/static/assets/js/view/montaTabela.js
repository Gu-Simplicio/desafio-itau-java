function montaTabela(transacoes) {
    const tabTransacoes = document.querySelector("#tabTransacoes");
    
    // apaga o body atual
    const tbodyAtual = tabTransacoes.querySelector("tbody");
    tbody.remove();

    //cria um novo
    const tbody = document.createElement("tbody");
    tabTransacoes.appendChild(tbody);

    transacoes.forEach(t => {
        // cria a linha
        const tr = document.createElement("tr");
        tbody.appendChild(tr);

        // cria o elemento dos dados
        const valorTd = document.createElement("td");
        valorTd.textContent = t.valor;
        const datahoraTd = document.createElement("td");
        datahoraTd.textContent = t.dataHora;

        tr.appendChild(valorTd);
        tr.appendChild(datahoraTd);
    });
}

export default montaTabela;