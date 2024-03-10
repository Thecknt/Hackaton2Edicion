// import React, { useState } from 'react';
// import axios from 'axios';
// import { useNavigate } from 'react-router-dom';

// function Login() {
//   const [username, setUsername] = useState('');
//   const [password, setPassword] = useState('');
//   const [ token, setToken] = useState('');
//   const navigate = useNavigate();


//   const handleSubmit = async (e) => {
//     e.preventDefault();
//   const loginUrl = 'http://localhost:8080/login';

//   try {
//     const response = await axios.post(loginUrl, {
//      username,
//      password,
//     });

//     if (response.data.success) {
//       alert('Inicio de sesión exitoso');

//       // Guarda el token en el almacenamiento local
//       localStorage.setItem('token', response.headers['x-auth-token']);

//       // Navega a la página de inicio
//       window.location.href = '/about';
//     } else {
//       alert('Error en el inicio de sesión');
//     }
//   } catch (error) {
//     console.error('Error en el inicio de sesión:', error);
//     alert('Error en el inicio de sesión');
//   }
// };

//   const logout= ()=>{
// localStorage.removeItem('token');
//   }

//   return (
//     <div className="bg-gray-100 min-h-screen flex items-center justify-center">
//       <div className="bg-white p-8 rounded-lg shadow-md w-full max-w-md lg:w-[300px]">
//         <h2 className="text-2xl font-bold mb-6 text-center">Iniciar Sesión</h2>

//         <form onSubmit={handleSubmit}>
//           <div className="mb-4">
//             <label
//               className="block mb-2 text-sm font-bold text-gray-600"
//               htmlFor="username"
//             >
//               Usuario
//             </label>

//             <input
//               className="w-full p-2 border border-gray-300 rounded-lg focus:outline-none focus:border-primary"
//               type="username"
//               id="username"
//               value={username}
//               onChange={(e) => setUsername(e.target.value)}
//               required
//             />
//           </div>

//           <div className="mb-4">
//             <label
//               className="block mb-2 text-sm font-bold text-gray-600"
//               htmlFor="password"
//             >
//               Contraseña
//             </label>

//             <input
//               className="w-full p-2 border border-gray-300 rounded-lg focus:outline-none focus:border-primary"
//               type="password"
//               id="password"
//               value={password}
//               onChange={(e) => setPassword(e.target.value)}
//               required
//             />
//           </div>

//           <div className="mb-4">
//             <input
//               className="mr-2 text-lime-400 border-white checked:border-transparent focus:outline-none focus:ring-0 focus:border-primary dark:border-neutral-600 dark:checked:border-primary dark:focus:before:shadow-[0px_0px_0px_13px_rgba(255,255,255,0.4)] dark:checked:focus:before:shadow-[0px_0px_0px_13px_#3b71ca]"
//               type="checkbox"
//               value=""
//               id="exampleCheck2"
//             />
//             <label
//               className="inline-block text-lime-400 font-bold pl-[0.15rem] hover:cursor-pointer"
//               htmlFor="exampleCheck2"
//             >
//               Recuérdame
//             </label>
//           </div>

//           <div className="text-center lg:text-left">
//             <button
//               onClick={handleSubmit}
//               type="submit"
//               className="inline-block rounded bg-amber-500 px-6 pb-2 pt-2.5 
//               text-xs font-medium uppercase leading-normal text-white 
//               shadow-[0_4px_9px_-4px_#e4a11b] transition duration-150 ease-in-out hover:bg-amber-600 hover:shadow-[0_8px_9px_rgba(255,255,255,0.4)]"
//             >
//               Iniciar Sesión
//             </button>
//           </div>
//         </form>
//       </div>
//     </div>
//   );
// };

// export default Login;


//   //   axios.post(loginUrl, { username, password }, {
//   //     headers: {
//   //       'Content-Type': 'application/json',
//   //     },
//   //   })
//   //   .then(response => {
//   //     if (response.data.success) {
//   //       alert('Inicio de sesión exitoso');
  
//   //       // Guarda el token en el almacenamiento local
//   //       localStorage.setItem('token', response.headers['x-auth-token']);
  
//   //       navigate('/about');
//   //     } else {
//   //       alert('Error en el inicio de sesión');
//   //     }
//   //   })
//   //   .catch(error => {
//   //     console.error('Error en el inicio de sesión:', error);
//   //     alert('Error en el inicio de sesión');
//   //   });
//   // };

import React, { useState } from 'react';
import axios from 'axios';

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/login', {
        username,
        password
      });

      if (response.data.success) {
        alert('Inicio de sesión exitoso');

        // Guarda el token en el almacenamiento local
        localStorage.setItem('token', response.data.token);

        // Redirige al usuario a la página de inicio
        window.location.href = '/about';
      } else {
        alert('Error en el inicio de sesión');
        console.error('Server response:', error.response.data);
      }
    } catch (error) {
      console.error('Error en el inicio de sesión:', error);
      alert('Error en el inicio de sesión');
    }
  };

  return (
    <div className="container mx-auto px-4 h-full">
      <div className="flex content-center items-center justify-center h-full">
        <form className="w-full p-6 mx-auto bg-white rounded shadow-md md:w-1/3 lg:w-1/4">
          <h1 className="text-xl font-semibold mb-6">Iniciar Sesión</h1>

          <div className="mb-4">
            <label className="block mb-1 text-gray-500">Usuario</label>
            <input
              type="text"
              className="w-full p-2 mb-6 border border-gray-200 rounded outline-none"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
          </div>

          <div className="mb-4">
            <label className="block mb-1 text-gray-500">Contraseña</label>
            <input
              type="password"
              className="w-full p-2 mb-6 border border-gray-200 rounded outline-none"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>

          <div className="mb-4">
            <input
              type="checkbox"
              id="exampleCheck2"
              className="mr-2"
            />
            <label htmlFor="exampleCheck2">Recuérdame</label>
          </div>

          <div className="text-center lg:text-left">
            <button
              onClick={handleSubmit}
              type="submit"
              className="inline-block rounded bg-amber-500 px-6 pb-2 pt-2.5 
              text-xs font-medium uppercase leading-normal text-white 
              shadow-[0_4px_9px_-4px_#e4a11b] transition duration-150 ease-in-out hover:bg-amber-600 hover:shadow-[0_8px_9px_rgba(255,255,255,0.4)]"
            >
              Iniciar Sesión
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Login;