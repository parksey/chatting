import type { LoginRequest, LoginResponse } from "../types";

const APP_BASE_URL = "http://localhost:8080/app";

export const login = async (loginData: LoginRequest): Promise<LoginResponse> => {
    const response = await fetch(`${APP_BASE_URL}/login`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(loginData),
    });
  
    if (!response.ok) {
      throw new Error('Login failed');
    }
  
    return response.json();
  };