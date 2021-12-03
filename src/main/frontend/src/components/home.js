import React, { Component } from "react";

import UserService from "../services/user";
import {Jumbotron} from "react-bootstrap";

export default class Home extends Component {
  constructor(props) {
    super(props);

    this.state = {
      content: ""
    };
  }

  componentDidMount() {
    UserService.getPublicContent().then(
      response => {
        this.setState({
          content: response.data
        });
      },
      error => {
        this.setState({
          content:
            (error.response && error.response.data) ||
            error.message ||
            error.toString()
        });
      }
    );
  }

  render() {
    return (

    <Jumbotron className="bg-dark text-white">
      <h1>{this.state.content}</h1>
      <blockquote className="blockquote mb-0">
        <p>
          Use the force, Harry
        </p>
        <footer className="blockquote-footer">
          Gandalf
        </footer>
      </blockquote>
    </Jumbotron>
    );
  }
}
