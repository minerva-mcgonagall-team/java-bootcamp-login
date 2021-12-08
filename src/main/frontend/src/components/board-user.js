import React, { Component } from "react";
import { useParams} from 'react-router-dom'
import UserService from "../services/user";
import AuthService from "../services/auth";


export default class BoardUser extends Component {
  constructor(props) {
    super(props);

    //list of Sessions of the logged in User
    this.state = {
      //content: LoginService.getAllSessions(this.props.user);

      redirect: null,
      userReady: false,
      currentUser: {
        id:""},
      content: ""
    };
  }

  componentDidMount() {
    const currentUser = AuthService.getCurrentUser();

    if (!currentUser) this.setState({ redirect: "/home" });
    this.setState({ currentUser: currentUser, userReady: true })

    UserService.getUserBoard(
        this.state.currentUser.id
    ).then(
        response => {
          this.setState({
            content: response.data
          });
        },
        error => {
          this.setState({
            content:
                (error.response &&
                    error.response.data &&
                    error.response.data.message) ||
                error.message ||
                error.toString()
          });
        }
    );
  }

  render() {
    return (
        <div className="container">
          <header className="jumbotron">
            <div>
              <h1>List Sessions</h1>
              <div className="container">
                <table className="table">
                  <thead>
                  <tr >
                    <th>id</th>
                    <th>start time</th>
                    <th>end time</th>
                  </tr>
                  </thead>

                  <tbody>
                  {
                    this.state.content.map(
                        session =>
                            <tr key={session.id}>
                              <td>{session.id}</td>
                              <td>{session.startSession.toString()}</td>
                              <td>{session.endSession.toString()}</td>

                            </tr>
                    )}</tbody>
                </table>
              </div>
            </div>
          </header>
        </div>
    );
  }
}