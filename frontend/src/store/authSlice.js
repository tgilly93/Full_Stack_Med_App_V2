import { createSlice } from '@reduxjs/toolkit';
import { getAuthToken, getUser } from '../services/authService';

const initialState = {
    token: getAuthToken() || null,
    user: getUser() || null,
    };

const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        login: (state, action) => {
            const { token, user } = action.payload;
            state.token = token;
            state.user = user;
            
            localStorage.setItem('authToken', token);
            localStorage.setItem('user', JSON.stringify(user));
        },

        logout(state) {
            state.token = null;
            state.user = null;
            
            localStorage.removeItem('authToken');
            localStorage.removeItem('user');
        },
    },
});

export const { login, logout } = authSlice.actions;

export default authSlice.reducer;