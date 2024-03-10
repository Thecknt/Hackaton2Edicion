import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { setToken } from '../components/authSlice';

function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const dispatch = useDispatch();
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
                const token = response.data.token;
                const username = response.data.Username; // Aquí obtienes el nombre de usuario del servidor
                dispatch(setToken(token, username)); // Despachar la acción setToken con el token y el nombre de usuario
                
                console.log('Token guardado en Redux:', token); // Imprime el token en la consola
                console.log('Nombre de usuario:', username); // Imprime el nombre de usuario en la consola
                
                alert('Inicio de sesión exitoso');
                navigate('/about');

                // Limpiar campos después del inicio de sesión exitoso
                setUsername('');
                setPassword('');
            } else {
                setError('Error en el inicio de sesión');
            }
        } catch (error) {
            if (error.response) {
                console.error('Error en el inicio de sesión:', error.response.data);
                setError(error.response ? error.response.data : 'Error en el inicio de sesión');
            } else if (error.request) {
                console.error('Error en el inicio de sesión:', error.request);
                setError('Error en el inicio de sesión: Request failed');
            } else {
                console.error('Error en el inicio de sesión:', error.message);
                setError('Error en el inicio de sesión: ' + error.message);
            }
        }
    };

    return (
        <div>
            <h2>Iniciar sesión</h2>
            {error && <div className="error-message">{error}</div>}
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
