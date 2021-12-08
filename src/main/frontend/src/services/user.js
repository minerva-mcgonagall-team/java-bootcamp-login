import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/';

class User {
  getPublicContent() {
    return axios.get(API_URL);
  }

  getUserBoard(id) {
    return axios.get(API_URL + 'user-activity/'+id);
  }

}

export default new User();
