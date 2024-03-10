import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const loginUrl = 'http://localhost:8080/login';

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post(loginUrl, { username, password }, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            if (response.status === 200) {
                alert('Inicio de sesión exitoso');
                navigate('/about'); // Redirige al usuario a la página "/about"
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
