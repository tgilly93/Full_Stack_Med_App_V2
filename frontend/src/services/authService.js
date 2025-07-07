import axios from 'axios';

const BASE_URL = "http://localhost:9000";

const apiClient = axios.create({
    baseURL: BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});

export async function registerUser(payload) {
    const response = await apiClient.post('/register', payload);
    return response.data;
}

export async function loginUser(credentials) {
    const response = await apiClient.post('/login', credentials);
    const { token, user } = response.data;

    setAuthToken(token);
    setUser(user);

    return { token, user };
}

export function setAuthToken(token) {
    localStorage.setItem('authToken', token);
}

export function getAuthToken() {
    return localStorage.getItem('authToken');
}

export function clearAuthToken() {
    localStorage.removeItem('authToken');
}

export function setUser(user) {
    localStorage.setItem('user', JSON.stringify(user));
}

export function getUser() {
    const user = localStorage.getItem('user');
    return user ? JSON.parse(user) : null;
}

export function clearUser() {
    localStorage.removeItem('user');
}

export async function logoutUser() {
    clearAuthToken();
    clearUser();
}

