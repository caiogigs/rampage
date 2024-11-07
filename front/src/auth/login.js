import authService from "./AuthService";

class LoginService {
  _url = "http://localhost:8080/auth";

  async login(data) {
    let error = "";

    await fetch(`${this._url}/login`, {
      method: "POST",
      body: JSON.stringify(data),
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
    })
      .then((response) => {
        if (response.status === 403) {
          throw new Error("Falha no login. Verifique suas credenciais.");
        }
        return response.json();
      })
      .then((data) => {
        console.log(data);

        if (data.token) {
          this._setData(data);
        } else {
          error = "Falha no login. Verifique suas credenciais.";
        }
      })
      .catch((err) => {
        error = err.message || "Erro ao conectar com o servidor.";
      });

    console.log(error);

    return error;
  }

  async loginConsumer(formData) {
    let error = "";

    await fetch(`${this._url}/login_consumer`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify(formData), // Envia os dados no formato correto
    })
      .then((response) => {
        if (response.status === 403) {
          throw new Error("Falha no login. Verifique suas credenciais.");
        }
        return response.json();
      })
      .then((data) => {
        console.log(data);

        if (data.token) {
          this._setData(data);
        } else {
          error = "Falha no login. Verifique suas credenciais.";
        }
      })
      .catch((err) => {
        error = err.message || "Erro ao conectar com o servidor.";
      });

    console.log(error);

    return error;
  }

  async _setData(data) {
    localStorage.setItem("authToken", data.token);
    localStorage.setItem("userRole", data.userRole);
    localStorage.setItem("idUser", data.id);
  }

  async logout() {
    localStorage.removeItem("authToken");
    localStorage.removeItem("userRole");
    localStorage.removeItem("idUser");
  }

  // async validateToken(token) {
  //   let error = "";

  //   await fetch(`${this._url}/validate-token?token=${token}`, {
  //     method: "GET",
  //     headers: {
  //       Accept: "application/json",
  //     },
  //   })
  //     .then((response) => response.json())
  //     .then((data) => {
  //       if (data?.message) {
  //         console.log(data.message);
  //         return data.message.valid;
  //       }
  //       alert("Sessão expirada. Por favor, faça login novamente.");
  //       authService.logout();
  //     })
  //     .catch((err) => {
  //       error = err.message || "Erro ao conectar com o servidor.";
  //     });

  //   console.log(error);

  //   return error;
  // }
}

export default LoginService;
