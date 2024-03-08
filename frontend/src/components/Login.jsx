import React, { useState } from 'react';
import axios from 'axios';

function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await axios.post('/login', { username, password });
            const { token } = response.data;

            // Guardar el token en el almacenamiento local
            localStorage.setItem('token', token);

            // Redirigir al usuario al index u otra página después de iniciar sesión
            window.location.href = '/index';
        } catch (error) {
            console.error('Error de inicio de sesión:', error);
            setError('Usuario o contraseña incorrectos');
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
