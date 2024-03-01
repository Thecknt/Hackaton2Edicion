
function validarContraseñas() {
    let password = document.getElementById("floating_password").value;
            let repeatPassword = document.getElementById("floating_repeat_password").value;
            if (password !== repeatPassword) {
                document.getElementById("errorContraseñas").style.display = "block";
                return false;
            } else {
                document.getElementById("errorContraseñas").style.display = "none";
                return true;
            }
}
document.getElementById("username").oninput = function(event) {
    console.log(event.target.value);
}
document.getElementById("floating_email").oninput = function(event) {
    console.log(event.target.value);
}
document.getElementById("floating_password").oninput = function(event) {
    console.log(event.target.value);
}

function registerUser() {
    let username = document.getElementById("username").value;
    console.log(username.value)
    let email = document.getElementById("floating_email").value;
    console.log(email)
    let password = document.getElementById("floating_password").value;
    console.log(password)

    let user = {
        username: jorge,
        email: email,
        password: password,
        roles: ["CLIENT"]
    };
    
    fetch('http://localhost:8080/createUser', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(user),
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
        alert("Usuario creado con exito!")
     //   window.location.href = "/";
    })
    .catch((error) => {
        console.error('Error:', error);
       alert("Error al crear el usuario!")
    });
}
