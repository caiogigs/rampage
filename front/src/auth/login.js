class LoginService {
  async login(data) {
    let error = "";

    await fetch("http://localhost:8080/auth/login", {
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
          sessionStorage.setItem("authToken", data.token);
          sessionStorage.setItem("userRole", data.userRole);
          sessionStorage.setItem("idUser", data.id);
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

  async loginConsumer(formData){
    let error = "";

    await fetch("http://localhost:8080/auth/login_consumer", {
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
          sessionStorage.setItem("authToken", data.token);
          sessionStorage.setItem("userRole", "CLIENTE");
          sessionStorage.setItem("idUser", data.id);
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


  async logout() {
    sessionStorage.removeItem("authToken");
    sessionStorage.removeItem("userRole");
    sessionStorage.removeItem("idUser");
  }
}

export default LoginService;
