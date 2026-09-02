function exibeCampoPeriodo(desativar) {
    const campoPeriodo = document.querySelector("#campoPeriodo");

    if(desativar) {
        campoPeriodo.classList.add("oculto");
    } else {
        campoPeriodo.classList.remove("oculto")
    }
}

export default exibeCampoPeriodo;