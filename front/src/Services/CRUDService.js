import authService from "../auth/AuthService";

class CrudService {
  _url;
  _path;
  _token;
  _headers;

  constructor(urlControllerEndpoint) {
    this.url = "http://localhost:8080";
    this._path = this.url + urlControllerEndpoint;
    this.token = authService.getToken();
    this._headers = {
      Authorization: `Bearer ${this.token}`,
      "Content-Type": "application/json",
      Accept: "application/json",
    };
  }

  async list(urlEndpoint) {
    return await fetch(this._path + urlEndpoint)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok " + response.statusText);
        }
        return response.json();
      })
      .then((data) => {
        console.log("Fetched data:", data); // Log dos dados recebidos
        return data;
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
        return null;
      });
  }

  async listByData(urlEndpoint, data) {
    return await fetch(this._path + urlEndpoint, {
      method: "POST",
      body: JSON.stringify(data),
      headers: this._headers,
    })
      .then((response) => {
        if (response.status === 403) {
          throw new Error("Falha no requisição.");
        }
        return response.json();
      })
      .then((data) => {
        console.log("Fetched data:", data);
        return data;
      })
      .catch((err) => {
        console.error("Error fetching data:", err);
        return null;
      });
  }

  async listById(urlEndpoint, id) {
    console.log(this._headers);
    
    return await fetch(
      `${this._path}${urlEndpoint}?id=${encodeURIComponent(id)}`,
      {
        method: "GET",
        headers: this._headers,
      }
    )
      .then((response) => {
        if (response.status === 403) {
          throw new Error("Falha no requisição.");
        }
        return response.json();
      })
      .then((data) => {
        console.log("Fetched data:", data);
        return data;
      })
      .catch((err) => {
        console.error("Error fetching data:", err);
        return null;
      });
  }

}

export default CrudService;
