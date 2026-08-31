import alteraRadio from "./alteraRadio.js";
import criaTransacao from "./criaTransacao.js"
import deletaTransacoes from "./deletaTransacoes.js";

const form = document.querySelector("form");
const btnDeletar = document.querySelector("#btnDeletar");
const radTodas = document.querySelector("#radTodas");
const radPeriodo = document.querySelector("#radPeriodo");

form.addEventListener("submit", (event) => criaTransacao(event));
btnDeletar.addEventListener("click", () => deletaTransacoes());

radTodas.addEventListener("change", () => alteraRadio())
radPeriodo.addEventListener("change", () => alteraRadio());