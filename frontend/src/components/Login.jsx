import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // Importa useNavigate desde react-router-dom
import axios from 'axios';

function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [token, setToken] = useState('');
    const navigate = useNavigate(); // Utiliza useNavigate para la navegación

    const loginUrl = 'http://localhost:8080/login';

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {

            const response = await axios.post(loginUrl, { username, password }, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            if (response.data) {
                alert('Inicio de sesión exitoso');

                //Aca obtengo el token de la cabecera, lo guardo en la variable y lo pusheo al localStorage
                const token = response.headers['x-auth-token'];
                localStorage.setItem('token', token);

                navigate('/about');
            } else {
                alert('Error en el inicio de sesión');
            }
        } catch (error) {
            if (error.response) {
                console.error('Error en el inicio de sesión:', error.response.data);
                alert(error.response ? error.response.data : 'Error en el inicio de sesión');

            } else if (error.request) {
                console.error('Error en el inicio de sesión:', error.request);
                alert('Error en el inicio de sesión: Request failed');
            } else {
                console.error('Error en el inicio de sesión:', error.message);
                alert('Error en el inicio de sesión: ' + error.message);
            }
        }
    };

    //Esta funcion sebas es para saber si el usuario esta authenticado
    const isAuthenticated = () => {
        return localStorage.getItem('token') != null;
    };

    //Esta funcion es para desloguearse y sacar el token del localStorage
    const logout = () => {
        localStorage.removeItem('token');
    };

    return (
        <div>
            <h2>Iniciar sesión</h2>
            {error && <div>{error}</div>}
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Usuario:</label>
                    <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
                </div>
                <div>
                    <label>Contraseña:</label>
                    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
                </div>
                <button type="submit">Iniciar sesión</button>
            </form>
        </div>
    );
}

export default Login;
