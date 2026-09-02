import exibeCampoPeriodo from "./view/exibeCampoPeriodo.js";
import criaTransacao from "./services/criaTransacao.js"
import deletaTransacoes from "./services/deletaTransacoes.js";
import recebeTransacoes from "./services/recebeTransacoes.js";

const form = document.querySelector("form");
const btnDeletar = document.querySelector("#btnDeletar");
const radTodas = document.querySelector("#radTodas");
const radPeriodo = document.querySelector("#radPeriodo");

form.addEventListener("submit", (event) => criaTransacao(event));
btnDeletar.addEventListener("click", () => deletaTransacoes());

radTodas.addEventListener("change", () => exibeCampoPeriodo(true))
radPeriodo.addEventListener("change", () => exibeCampoPeriodo(false));


recebeTransacoes("transacao");