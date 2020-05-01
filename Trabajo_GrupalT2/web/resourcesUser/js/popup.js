
document.getElementById('botonCrearProyecto').addEventListener('click', function(){
    document.querySelector('.bg-modal1').style.display = "flex";
});
    
document.getElementById('buttonDate').addEventListener('click', function(){
    document.querySelector('.bg-modal1').style.display = "flex";
});

function mostrar(){
     document.querySelector('.modal-contents').style.display = "flex";
};

function cerrarPopUp(){
    document.querySelector('.bg-modal1').style.display = "none";
    document.querySelector('.bg-modal2').style.display = "none";
}

function mostrarCrearAct(){
     document.querySelector('.bg-modal2').style.display = "flex";
};