import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import AuthService from "../services/auth";

export default class Profile extends Component {
  constructor(props) {
    super(props);

    this.state = {
      redirect: null,
      userReady: false,
      currentUser: { username: "" }
    };
  }

  componentDidMount() {
    const currentUser = AuthService.getCurrentUser();

    if (!currentUser) this.setState({ redirect: "/home" });
    this.setState({ currentUser: currentUser, userReady: true })
  }

  render() {
    if (this.state.redirect) {
      return <Redirect to={this.state.redirect} />
    }

    const { currentUser } = this.state;

    return (
      <div className="container">
        {(this.state.userReady) ?
        <div>
        <header className="jumbotron">
          <h3>
            <strong>{currentUser.firstname + currentUser.lastname}</strong> 's Profile
          </h3>
        </header>
        <p className="jumbotron">
          <strong>First Name:</strong>{" "}
          {currentUser.firstname}
        </p>
        <p className="jumbotron">
          <strong>Last Name:</strong>{" "}
          {currentUser.lastname}
        </p>
        <p className="jumbotron">
          <strong>Email:</strong>{" "}
          {currentUser.email}
        </p>
          <p className="jumbotron">
            <strong>Phone Number:</strong>{" "}
            {currentUser.phoneNumber}
          </p>
      </div>: null}
      </div>
    );
  }
}
