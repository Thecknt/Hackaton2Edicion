// authSlice.js

import { createSlice } from '@reduxjs/toolkit';

export const authSlice = createSlice({
  name: 'auth',
  initialState: {
    token: null,
    username: null,
  },
  reducers: {
    setToken: (state, action) => {
      state.token = action.payload.token;
      state.username = action.payload.username;
    },
    clearToken: (state) => {
      state.token = null;
      state.username = null;
    },
  },
});

export const { setToken, clearToken } = authSlice.actions;

export default authSlice.reducer;
