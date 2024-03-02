import { useState } from 'react'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from './components/Home';
import About from './components/About';
import Navbar from './components/shared/Navbar';
import Footer from './components/shared/Footer';
import Login from './components/Login';
import './App.css'

function App() {

  return (
    <BrowserRouter>
     <Navbar />
      <Routes>
       
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path='/login' element={<Login />}/>
      </Routes>
      <Footer />
    </BrowserRouter>
  )
}

export default App
