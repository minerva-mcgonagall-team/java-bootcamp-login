import axios from 'axios';
import authHeader from './auth-header';
//const API_URL = 'http://localhost:8080/test/';
const API_URL = 'https://java-bootcamp-login.herokuapp.com/test/';

class User {
    getPublicContent() {
        return axios.get(API_URL);
    }

    getUserBoard() {
        return axios.get(API_URL + 'user', {headers: authHeader()});
    }

}

export default new User();
