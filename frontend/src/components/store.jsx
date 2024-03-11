// store.js

import { configureStore } from '@reduxjs/toolkit';
import authReducer from '../components/authSlice';

export default configureStore({
  reducer: {
    auth: authReducer,
  },
});

// Agregar log para depuración
console.log('Configuración de la tienda Redux completada');
