import criaTransacao from "./services/criaTransacao.js"
import deletaTransacoes from "./services/deletaTransacoes.js";
import recebeTransacoes from "./services/recebeTransacoes.js";

const form = document.querySelector("form");
const btnDeletar = document.querySelector("#btnDeletar");

form.addEventListener("submit", (event) => criaTransacao(event));
btnDeletar.addEventListener("click", () => deletaTransacoes());

recebeTransacoes();