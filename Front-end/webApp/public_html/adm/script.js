const formProduto = document.querySelector("formProduto");

const Inome = document.querySelector(".nome");
const Idescricao = document.querySelector(".descricao");
const Ipreco = document.querySelector(".preco");
const Iquantidade = document.querySelector(".quantidade");

form.addEventListener('submit',function(event){
    event.preventDefault();

    console.log(Inome.value);
});