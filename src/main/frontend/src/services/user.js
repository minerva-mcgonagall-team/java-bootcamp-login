import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/test';

class User {
  getPublicContent() {
    return axios.get(API_URL );
  }

  getUserBoard() {
    return axios.get(API_URL + 'user', { headers: authHeader() });
  }

}

export default new User();
