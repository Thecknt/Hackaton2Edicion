import React from 'react';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Provider } from 'react-redux';
import store from './components/store'; // Importa el store de Redux
import Home from './components/Home';
import About from './components/About';
import Navbar from './components/shared/Navbar';
import Footer from './components/shared/Footer';
import Login from './components/Login';
import Register from './components/register';
import Profile from './components/Profile';
import './App.css'

function App() {
  return (
    <Provider store={store}> {/* Proveedor de Redux que envuelve toda la aplicación */}
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/about" element={<About />} />
          <Route path='/login' element={<Login />} />
          <Route path='/register' element={<Register />} />
          <Route path='/profile' element={<Profile />} />
        </Routes>
        <Footer />
      </BrowserRouter>
    </Provider>
  )
}

export default App;
