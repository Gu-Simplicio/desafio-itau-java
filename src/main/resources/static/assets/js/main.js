import criaTransacao from "./criaTransacao.js"

const form = document.querySelector("form");
form.addEventListener("submit", (event) => criaTransacao(event));