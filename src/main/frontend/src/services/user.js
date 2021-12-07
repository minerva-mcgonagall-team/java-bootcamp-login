import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/';

class User {
  getPublicContent() {
    return axios.get(API_URL );
  }

  getUserBoard() {
    return axios.get(API_URL + 'user-activity', {
      firstname,
      lastname,
      email,
      phoneNumber,
      password,
     headers: authHeader() });
  }

}

export default new User();
