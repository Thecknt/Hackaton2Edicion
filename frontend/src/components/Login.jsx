import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { setToken } from '../components/authSlice';

function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [alertType, setAlertType] = useState('');
    const [alertMessage, setAlertMessage] = useState('');
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
                const username = response.data.Username;
                dispatch(setToken({ token, username }));
                
                console.log('Token guardado en Redux:', token);
                console.log('Nombre de usuario:', username);
                
                setAlertType('success');
                setAlertMessage('Inicio de sesión exitoso');
                navigate('/about');

                setUsername('');
                setPassword('');
            } else {
                setAlertType('error');
                setAlertMessage('Error en el inicio de sesión');
            }
        } catch (error) {
            setAlertType('error');
            if (error.response) {
                console.error('Error en el inicio de sesión:', error.response.data);
                setAlertMessage('Error al iniciar sesión: Credenciales incorrectas');
            } else if (error.request) {
                console.error('Error en el inicio de sesión:', error.request);
                setAlertMessage('Error en el inicio de sesión: Request failed');
            } else {
                console.error('Error en el inicio de sesión:', error.message);
                setAlertMessage('Error en el inicio de sesión: ' + error.message);
            }
        }
    };

    return (
        <div>
            <h2>Iniciar sesión</h2>
            {alertType === 'error' && (
                <div className="p-4 mb-4 text-sm text-red-800 rounded-lg bg-red-50 dark:bg-gray-800 dark:text-red-400" role="alert">
                    <span className="font-medium">¡Error!</span> {alertMessage}
                </div>
            )}
            {alertType === 'success' && (
                <div className="p-4 mb-4 text-sm text-green-800 rounded-lg bg-green-50 dark:bg-gray-800 dark:text-green-400" role="alert">
                    <span className="font-medium">¡Éxito!</span> {alertMessage}
                </div>
            )}
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

<<<<<<< HEAD
export default Login;
=======
export default Login;
>>>>>>> 13051bfc136d19bf7d79acf4011ae32fd600d530
