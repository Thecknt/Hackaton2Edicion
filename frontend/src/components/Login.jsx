import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/login', {
        username,
        password,
      });

      if (response.data.success) {
        alert('Inicio de sesión exitoso');
        navigate('/about');
      } else {
        alert('Error en el inicio de sesión');
      }
    } catch (error) {
      console.error('Error en el inicio de sesión:', error);
      alert('Error en el inicio de sesión');
    }
  };
  return (
    <>
<section className="h-screen bg-amber-100 dark:bg-gray-900 mt-0"> 
 <div className=" mt-0 dark:text-white">  
  
  
    {/* <!-- Left column container with background--> */}
    <div
      className="g-6 flex  flex-wrap items-center justify-center lg:justify-between">
  <div className="shrink-1 grow-0 basis-auto md:mb-0 md:w-9/12 md:shrink-0 lg:w-6/12 flex justify-center">
  <img
    src="https://img.molachinoviajes.com/gallery/var/albums/logo.png?m=1709405969"
    className=" mt-4 w-50"
    alt="Sample image"
  />
</div>



      {/* <!-- Right column container --> */}
      <div className="mb-12 md:mb-0 md:w-8/12 lg:w-5/12 xl:w-5/12">
        <form className='pr-16' >
         

          {/* <!-- Separator between social media sign in and email/password sign in --> */}
          <div
            className="my-4 flex items-center before:mt-0.5 before:flex-1 before:border-t before:border-neutral-300 
            after:mt-0.5 after:flex-1 after:border-t after:border-neutral-300">
            <p
              className="mx-4 mb-0 text-3xl text-center text-lime-400 font-semibold  dark:text-white">
             Inicio de Sesión
            </p>
          </div>
          <div className="mb-6">
  <label htmlFor="username" className="relative top-2 pointer-events-none text-orange-500 font-bold transition-none border-b border-gray-300">Username</label>
  <input
    type="text"
    id="username"
    className="mt-6 border border-gray-300 text-orange-500 font-bold rounded-md w-full h-10 text-gray-700"
    placeholder="Enter your username"
    onChange={e => setUsername(e.target.value)}
  />
</div>

<div className="mb-6">
  <label htmlFor="password" className="relative top-2 pointer-events-none text-orange-500 font-bold transition-none border-b border-gray-300">Password</label>
  <input
    type="password"
    id="password"
    className="mt-6 border text-orange-500 font-bold border-gray-300 rounded-md w-full h-10 text-gray-700"
    placeholder="Enter your password"
    onChange={e => setPassword(e.target.value)}
  />
</div>


          <div className="mb-6 flex items-center justify-between">
            {/* <!-- Remember me checkbox --> */}
            <div className="mb-[0.125rem] block min-h-[1.5rem] pl-[1.5rem]">
              <input
                className="relative float-left -ml-[1.5rem] mr-[6px] mt-[0.15rem] h-[1.125rem] w-[1.125rem]
                 appearance-none rounded-[0.25rem] border-[0.125rem] border-solid border-neutral-300 outline-none 
                 before:pointer-events-none before:absolute before:h-[0.875rem] before:w-[0.875rem] before:scale-0 
                  before:rounded-full before:bg-transparent before:opacity-0 before:shadow-[0px_0px_0px_13px_transparent]
                   before:content-[''] checked:border-primary checked:text-orange-500 checked:before:opacity-[0.16] 
                    checked:after:absolute checked:after:-mt-px checked:after:ml-[0.25rem] checked:after:block 
                     checked:after:h-[0.8125rem] checked:after:w-[0.375rem] checked:after:rotate-45 
                      checked:after:border-[0.125rem] checked:after:border-l-0 checked:after:border-t-0 
                       checked:after:border-solid checked:after:border-white checked:after:bg-transparent 
                        checked:after:content-[''] hover:cursor-pointer hover:before:opacity-[0.04] 
                         hover:before:shadow-[0px_0px_0px_13px_rgba(0,0,0,0.6)] focus:shadow-none 
                          focus:transition-[border-color_0.2s] focus:before:scale-100 focus:before:opacity-[0.12] 
                           focus:before:shadow-[0px_0px_0px_13px_rgba(0,0,0,0.6)] 
                           focus:before:transition-[box-shadow_0.2s,transform_0.2s] focus:after:absolute focus:after:z-[1] 
                           focus:after:block focus:after:h-[0.875rem] focus:after:w-[0.875rem]
                            focus:after:rounded-[0.125rem] focus:after:content-['']
                             checked:focus:before:scale-100 checked:focus:before:shadow-[0px_0px_0px_13px_#3b71ca] 
                             checked:focus:before:transition-[box-shadow_0.2s,transform_0.2s] checked:focus:after:-mt-px
                              checked:focus:after:ml-[0.25rem] checked:focus:after:h-[0.8125rem] checked:focus:after:w-[0.375rem] 
                              checked:focus:after:rotate-45 checked:focus:after:rounded-none checked:focus:after:border-[0.125rem]
                               checked:focus:after:border-l-0 checked:focus:after:border-t-0 checked:focus:after:border-solid 
                               checked:focus:after:border-white checked:focus:after:bg-transparent
                                dark:border-neutral-600 dark:checked:border-primary dark:checked:bg-primary 
                                dark:focus:before:shadow-[0px_0px_0px_13px_rgba(255,255,255,0.4)] 
                                dark:checked:focus:before:shadow-[0px_0px_0px_13px_#3b71ca]"
                type="checkbox"
                value=""
                id="exampleCheck2" />
              <label
                className="inline-block text-lime-400 font-bold pl-[0.15rem] hover:cursor-pointer"
                htmlFor="exampleCheck2">
                Remember me
              </label>
            </div>

            
            {/* <a href="#!">Forgot password?</a> */}
          </div>


          <div className="text-center lg:text-left">
          <button
          onClick={handleSubmit}
  type="submit"
  className="inline-block rounded bg-amber-500 px-6 pb-2 pt-2.5 
  text-xs font-medium uppercase leading-normal text-white 
  shadow-[0_4px_9px_-4px_#e4a11b] transition duration-150 ease-in-out hover:bg-amber-600 hover:shadow-[0_8px_9px_-4px_rgba(228,161,27,0.3),0_4px_18px_0_rgba(228,161,27,0.2)] focus:bg-warning-600 focus:shadow-[0_8px_9px_-4px_rgba(228,161,27,0.3),0_4px_18px_0_rgba(228,161,27,0.2)] focus:outline-none focus:ring-0 active:bg-warning-700 active:shadow-[0_8px_9px_-4px_rgba(228,161,27,0.3),0_4px_18px_0_rgba(228,161,27,0.2)] dark:shadow-[0_4px_9px_-4px_rgba(228,161,27,0.5)] dark:hover:shadow-[0_8px_9px_-4px_rgba(228,161,27,0.2),0_4px_18px_0_rgba(228,161,27,0.1)] dark:focus:shadow-[0_8px_9px_-4px_rgba(228,161,27,0.2),0_4px_18px_0_rgba(228,161,27,0.1)] dark:active:shadow-[0_8px_9px_-4px_rgba(228,161,27,0.2),0_4px_18px_0_rgba(228,161,27,0.1)]">
  Inicio Sesion
</button>

            <p className="mb-0 mt-2 pt-1 text-sm font-semibold">
              {/* Don't have an account? */}
              <a
                href="#!"
                className="text-lime-400 text-3xl transition duration-150 ease-in-out hover:text-orange-500 
                focus:text-orange-600 active:text-danger-700"
                >Registrate</a
              >
            </p>
          </div>
        </form>
      </div>
    </div>
  </div>
</section>
    </>
  )
}

export default Login