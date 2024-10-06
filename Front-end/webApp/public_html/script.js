var formSignin = document.querySelector('#signin');
var formSignup = document.querySelector('#signup');
var btnColor = document.querySelector('.btnColor');

document.querySelector('#btnSignin').addEventListener('click',() =>{
    formSignin.style.left="25px";
    formSignup.style.left="450px";
    btnColor.style.left="0px";
});

document.querySelector('#btnSignup').addEventListener('click',() =>{
    formSignin.style.left="-450px";
    formSignup.style.left="25px";
    btnColor.style.left="110px";
});

// adm part
let list = document.querySelectorAll(".navigation li");

function activeLink() {
  list.forEach((item) => {
    item.classList.remove("hovered");
  });
  this.classList.add("hovered");
}

list.forEach((item) => item.addEventListener("mouseover", activeLink));

// Menu Toggle
let toggle = document.querySelector(".toggle");
let navigation = document.querySelector(".navigation");
let main = document.querySelector(".main");

toggle.onclick = function () {
  navigation.classList.toggle("active");
  main.classList.toggle("active");
};



