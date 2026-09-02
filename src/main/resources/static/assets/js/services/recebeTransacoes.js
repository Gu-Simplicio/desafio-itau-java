async function recebeTransacoes(rota) {
    console.log("Entrou no GET")
    const URL_GET = `http://localhost:8080/unibanco/${rota}`;

    try {
        const response = await fetch(URL_GET);

        const result = await response.json();

        if(!response.ok || !result.sucesso) {
            throw new Error("Http error status: ", response.status);
        }

        return result.dados;
    } catch(error) {
        console.error("Erro ao receber transações: ", error);
    }
}

export default recebeTransacoes;