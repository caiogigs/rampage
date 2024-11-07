import { Navigate } from "react-router-dom";
import LoginService from "./login";

class AuthService {
  static instance = null;

  constructor() {
    if (AuthService.instance) {
      return AuthService.instance;
    }

    AuthService.instance = this;
    this.authenticated = false;
    this._loginService = new LoginService();
  }

  // async validateToken(){
  //   const token = this.getToken();
  //   if (token) {
  //     const data = await this._loginService.validateToken(token);
  //     if (data){
  //       return true;
  //     }
  //   }

  //   return false;
  // }

  async login(data) {
    const error = await this._loginService.login(data);
    
    if (error.length === 0) {
      this.authenticated = true;
    } else {
      this.authenticated = false;
    }

    return error;
  }

  async loginConsumer(data) {
    const error = await this._loginService.loginConsumer(data);
    
    if (error.length === 0) {
      this.authenticated = true;
    } else {
      this.authenticated = false;
    }

    return error;
  }

  async logout() {
    this.authenticated = false;
    await this._loginService.logout();
    document.location = "/pagina-principal";
  }

  getToken() {
    return localStorage.getItem("authToken");
  }

  getRole() {
    return localStorage.getItem("userRole");
  }

  getIdUser() {
    return localStorage.getItem("idUser");
  }

  async isAuthenticated() {
    // const validToken = await authService.validateToken();
    // console.log(validToken);
    return this.authenticated;
  }
}

const authService = new AuthService();
export default authService;
