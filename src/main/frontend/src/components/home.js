import React, {Component} from "react";

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
                        This app saved me a lot of time
                    </p>
                    <footer className="blockquote-footer">
                        Florin, a happy customer
                    </footer>
                </blockquote>
            </Jumbotron>
        );
    }
}
