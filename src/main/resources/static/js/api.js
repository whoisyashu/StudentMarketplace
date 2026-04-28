// API Configuration
const API_BASE = '/api';

// Token Management
const TokenManager = {
    getToken: () => localStorage.getItem('token'),
    setToken: (token) => localStorage.setItem('token', token),
    clearToken: () => localStorage.removeItem('token'),
    getUserId: () => localStorage.getItem('userId'),
    setUserId: (userId) => localStorage.setItem('userId', userId),
    clearUserId: () => localStorage.removeItem('userId'),
    isAuthenticated: () => !!localStorage.getItem('token')
};

// API Utility
const API = {
    request: async (endpoint, options = {}) => {
        const headers = {
            'Content-Type': 'application/json',
            ...options.headers
        };

        const token = TokenManager.getToken();
        if (token) {
            headers['Authorization'] = `Bearer ${token}`;
        }

        try {
            const response = await fetch(`${API_BASE}${endpoint}`, {
                ...options,
                headers
            });

            if (!response.ok) {
                if (response.status === 401) {
                    TokenManager.clearToken();
                    TokenManager.clearUserId();
                    window.location.href = '/pages/login.html';
                }
                throw new Error(`API Error: ${response.status}`);
            }

            return await response.json();
        } catch (error) {
            console.error('API Request Error:', error);
            throw error;
        }
    },

    get: (endpoint) => API.request(endpoint, { method: 'GET' }),
    post: (endpoint, data) => API.request(endpoint, { method: 'POST', body: JSON.stringify(data) }),
    put: (endpoint, data) => API.request(endpoint, { method: 'PUT', body: JSON.stringify(data) }),
    delete: (endpoint) => API.request(endpoint, { method: 'DELETE' })
};

// UI Utilities
const UI = {
    showAlert: (message, type = 'success') => {
        const alertDiv = document.createElement('div');
        alertDiv.className = `alert alert-${type}`;
        alertDiv.textContent = message;
        
        const container = document.querySelector('.container') || document.body;
        container.insertBefore(alertDiv, container.firstChild);
        
        setTimeout(() => alertDiv.remove(), 3000);
    },

    showLoading: (element) => {
        element.innerHTML = '<div class="loading">Loading...</div>';
    },

    redirectTo: (page) => {
        window.location.href = page;
    }
};

// Auth Utilities
const Auth = {
    login: async (email, password) => {
        try {
            const response = await API.post('/auth/login', { email, password });
            if (response.token) {
                TokenManager.setToken(response.token);
                TokenManager.setUserId(response.userId);
                UI.showAlert('Login successful!', 'success');
                UI.redirectTo('/');
            }
        } catch (error) {
            UI.showAlert('Login failed', 'error');
            throw error;
        }
    },

    register: async (userData) => {
        try {
            const response = await API.post('/auth/register', userData);
            if (response.token) {
                TokenManager.setToken(response.token);
                TokenManager.setUserId(response.userId);
                UI.showAlert('Registration successful!', 'success');
                UI.redirectTo('/');
            }
        } catch (error) {
            UI.showAlert('Registration failed', 'error');
            throw error;
        }
    },

    logout: () => {
        TokenManager.clearToken();
        TokenManager.clearUserId();
        UI.redirectTo('/pages/login.html');
    }
};

// Check authentication on page load
document.addEventListener('DOMContentLoaded', () => {
    const isAuthenticated = TokenManager.isAuthenticated();
    const authButtons = document.querySelector('.auth-buttons');
    
    if (authButtons) {
        if (isAuthenticated) {
            authButtons.innerHTML = `
                <button class="btn btn-secondary" onclick="Auth.logout()">Logout</button>
            `;
        } else {
            authButtons.innerHTML = `
                <a href="/pages/login.html" class="btn btn-secondary">Login</a>
                <a href="/pages/register.html" class="btn btn-primary">Sign Up</a>
            `;
        }
    }
});
