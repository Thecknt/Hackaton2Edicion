import { createSlice } from '@reduxjs/toolkit';

export const authSlice = createSlice({
  name: 'auth',
  initialState: {
    token: null,
    username: null, // Agregar el nombre de usuario al estado inicial
  },
  reducers: {
    setToken: (state, action) => {
      state.token = action.payload.token;
      state.username = action.payload.username; // Almacenar también el nombre de usuario al establecer el token
      console.log('Token guardado en Redux:', action.payload.token); // Agregar log para depuración
      console.log('Nombre de usuario guardado en Redux:', action.payload.username); // Agregar log para depuración
    },
    clearToken: (state) => {
      state.token = null;
      state.username = null; // Limpiar el nombre de usuario al limpiar el token
      console.log('Token limpiado en Redux'); // Agregar log para depuración
      console.log('Nombre de usuario limpiado en Redux'); // Agregar log para depuración
    },
  },
});

export const { setToken, clearToken } = authSlice.actions;

export default authSlice.reducer;
