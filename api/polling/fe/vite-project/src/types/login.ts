export interface LoginRequest {
    loginId: string;
    password: string;
  }
  
  export interface LoginResponse {
    token: string;
    user: {
      id: number;
      loginId: string;
      name: string;
    };
  }