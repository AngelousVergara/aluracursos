// El principal objetivo de este desafío es fortalecer tus habilidades en lógica de programación. Aquí deberás desarrollar la lógica para resolver el problema.
let amigos = [];

function agregarAmigo() {
    var nombre = document.getElementById("amigo").value;
    if (nombre !== "") {
        amigos.push(nombre);
        document.getElementById("listaAmigos").innerHTML += "<li>" + nombre + "</li>";
        document.getElementById("amigo").value = "";
    }

}

function sortearAmigo() {
    if (amigos.length >= 2) {
        var indice = Math.floor(Math.random() * amigos.length);
        var amigoSorteado = amigos[indice];
        document.getElementById("resultado").innerHTML = "El amigo secreto es: " + amigoSorteado;
        amigos.splice(indice, 1);
    } else {
        document.getElementById("resultado").innerHTML = "No hay suficientes amigos para sortear.";
    }

}