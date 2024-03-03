import React, { useState } from 'react';
import axios from 'axios';

function Register() {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (password !== confirmPassword) {
      alert("Las contraseñas no coinciden");
      return;
    }

    try {
      const response = await axios.post('http://localhost:8080/createUser', {
        username,
        email,
        password,
        roles: ['CLIENT'] // Especifica el rol del usuario como 'CLIENT'
      });
      console.log(response.data);
      alert("Usuario creado exitosamente");
      // Redirigir a la página de inicio de sesión u otra página
    } catch (error) {
      console.error('Error al crear usuario:', error);
      alert("Error al crear usuario");
    }
  };

  return (
    <>
      <section className="h-screen bg-amber-100 dark:bg-gray-900 mt-0">
        <div className="mt-0 dark:text-white">
          <div className="g-6 flex flex-wrap items-center justify-center lg:justify-between">
            <div className="shrink-1 grow-0 basis-auto md:mb-0 md:w-9/12 md:shrink-0 lg:w-6/12 flex justify-center">
              <img
                src="https://img.molachinoviajes.com/gallery/var/albums/logo.png?m=1709405969"
                className="mt-4 w-50"
                alt="Sample image"
              />
            </div>
            <div className="mb-12 md:mb-0 md:w-8/12 lg:w-5/12 xl:w-5/12">
              <form className="pr-16" onSubmit={handleSubmit}>
                <div className="mb-6">
                  <label htmlFor="username" className="relative top-2 pointer-events-none text-orange-500 font-bold transition-none border-b border-gray-300">
                    Username
                  </label>
                  <input
                    type="text"
                    id="username"
                    className="mt-6 border border-gray-300 text-orange-500 font-bold rounded-md w-full h-10 text-gray-700"
                    placeholder="Enter your username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                  />
                </div>
                <div className="mb-6">
                  <label htmlFor="email" className="relative top-2 pointer-events-none text-orange-500 font-bold transition-none border-b border-gray-300">
                    Email
                  </label>
                  <input
                    type="email"
                    id="email"
                    className="mt-6 border border-gray-300 text-orange-500 font-bold rounded-md w-full h-10 text-gray-700"
                    placeholder="Enter your email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                  />
                </div>
                <div className="mb-6">
                  <label htmlFor="password" className="relative top-2 pointer-events-none text-orange-500 font-bold transition-none border-b border-gray-300">
                    Password
                  </label>
                  <input
                    type="password"
                    id="password"
                    className="mt-6 border text-orange-500 font-bold border-gray-300 rounded-md w-full h-10 text-gray-700"
                    placeholder="Enter your password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                  />
                </div>
                <div className="mb-6">
                  <label htmlFor="confirmPassword" className="relative top-2 pointer-events-none text-orange-500 font-bold transition-none border-b border-gray-300">
                    Confirm Password
                  </label>
                  <input
                    type="password"
                    id="confirmPassword"
                    className="mt-6 border text-orange-500 font-bold border-gray-300 rounded-md w-full h-10 text-gray-700"
                    placeholder="Confirm your password"
                    value={confirmPassword}
                    onChange={(e) => setConfirmPassword(e.target.value)}
                  />
                </div>
                <div className="text-center lg:text-left">
                  <button
                    type="submit"
                    className="inline-block rounded bg-amber-500 px-6 pb-2 pt-2.5 text-xs font-medium uppercase leading-normal text-white shadow-[0_4px_9px_-4px_#e4a11b] transition duration-150 ease-in-out hover:bg-amber-600 hover:shadow-[0_8px_9px_-4px_rgba(228,161,27,0.3),0_4px_18px_0_rgba(228,161,27,0.2)] focus:bg-warning-600 focus:shadow-[0_8px_9px_-4px_rgba(228,161,27,0.3),0_4px_18px_0_rgba(228,161,27,0.2)] focus:outline-none focus:ring-0 active:bg-warning-700 active:shadow-[0_8px_9px_-4px_rgba(228,161,27,0.3),0_4px_18px_0_rgba(228,161,27,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(228,161,27,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(228,161,27,0.2),0_4px_18px_0_rgba(228,161,27,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(228,161,27,0.2),0_4px_18px_0_rgba(228,161,27,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(228,161,27,0.2),0_4px_18px_0_rgba(228,161,27,0.1)]"
                  >
                    Registrarse
                  </button>
                  <p className="mb-0 mt-2 pt-1 text-sm font-semibold">
                    <a
                      href="#!"
                      className="text-lime-400 text-3xl transition duration-150 ease-in-out hover:text-orange-500 focus:text-orange-600 active:text-danger-700"
                    >
                      Iniciar Sesión
                    </a>
                  </p>
                </div>
              </form>
            </div>
          </div>
        </div>
      </section>
    </>
  );
}

export default Register;
