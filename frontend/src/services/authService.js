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

    return { token, user };
}

export function getAuthToken() {
    return localStorage.getItem('authToken');
}

export function getUser() {
    const user = localStorage.getItem('user');
    return user ? JSON.parse(user) : null;
}

apiClient.interceptors.request.use((config) => {
    const token = getAuthToken();
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
    });

export default apiClient;
