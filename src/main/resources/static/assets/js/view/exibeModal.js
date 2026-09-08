function exibeModal(tipoModal, msg){
    // tipos: sucesso, erro e ?aviso?
    const modal = document.querySelector(".modal");

    modal.classList.remove("oculto");
    modal.textContent = msg;
    modal.classList.add(tipoModal);
    setTimeout(() => {
        modal.classList.remove(tipoModal);
        modal.classList.add("oculto");
    }, 2500);
}

export default exibeModal;