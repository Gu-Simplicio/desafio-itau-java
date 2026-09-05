import criaTransacao from "./services/criaTransacao.js"
import deletaTransacoes from "./services/deletaTransacoes.js";
import recebeEstatisticas from "./services/recebeEstatisticas.js";
import recebeTransacoes from "./services/recebeTransacoes.js";

const form = document.querySelector("form");
const btnDeletar = document.querySelector("#btnDeletar");
const btnEstatisticas = document.querySelector("#btnEstatisticas");

form.addEventListener("submit", (event) => criaTransacao(event));
btnDeletar.addEventListener("click", () => deletaTransacoes());
btnEstatisticas.addEventListener("click", () => recebeEstatisticas(60));

recebeTransacoes();