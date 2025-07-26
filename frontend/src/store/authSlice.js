import { createSlice } from '@reduxjs/toolkit';
import { getAuthToken, setAuthToken, clearAuthToken, getUser, setUser, clearUser } from '../services/authService';

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
            setAuthToken(token);
            setUser(user);
        },

        logout(state) {
            state.token = null;
            state.user = null;
            clearAuthToken();
            clearUser();
        },
    },
});

export const { login, logout } = authSlice.actions;

export default authSlice.reducer;