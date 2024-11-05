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
          localStorage.setItem("authToken", data.token);
          localStorage.setItem("userRole", data.userRole);
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
    localStorage.removeItem("authToken");
    localStorage.removeItem("userRole");
  }
}

export default LoginService;
