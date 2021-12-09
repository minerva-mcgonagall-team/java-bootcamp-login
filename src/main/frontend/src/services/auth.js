import axios from "axios";
//const API_URL = "http://localhost:8080/api/auth/";
const API_URL = "https://java-bootcamp-login.herokuapp.com/api/auth/";

class AuthService {
    login(email, password) {
        return axios
            .post(API_URL + "login", {
                email,
                password
            })
            .then(response => {
                if (response.data.accessToken) {
                    localStorage.setItem("user", JSON.stringify(response.data));
                }
                return response.data;
            });
    }

    logout() {
        localStorage.removeItem("user");
    }

    register(firstname, lastname, email, phoneNumber, password) {
        return axios.post(API_URL + "register", {
            firstname,
            lastname,
            email,
            phoneNumber,
            password
        });
    }

    getCurrentUser() {
        return JSON.parse(localStorage.getItem('user'));
        ;
    }
}

export default new AuthService();
