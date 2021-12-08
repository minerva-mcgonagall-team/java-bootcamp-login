import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import AuthService from "../services/auth";
import authHeader from "../services/auth-header";
import axios from "axios";

const api_URL = "http://localhost:8080/api/profile/"
export default class UserDetails extends Component {
    constructor(props) {
        super(props);
        this.getProfile = this.getProfile.bind(this);
        this.state = {
            redirect: null,
            userReady: false,
            currentUser: { username: "" },
            firstName: '',
            lastName: '',
            email: '',
            phoneNumber: ''
        };
    }

    componentDidMount() {
        const currentUser = AuthService.getCurrentUser();

        if (!currentUser) this.setState({ redirect: "/home" });
        this.setState({ currentUser: currentUser, userReady: true })

        const profileID = + this.props.match.params.id;
        this.getProfile(profileID);
    }

    getProfile(profileID)
    {axios.get(api_URL + profileID,
            {headers:authHeader()}).then(response => {console.log(response);
            if (response.data != null)
            {
                this.setState({
                    firstName: response.data.firstName,
                    lastName: response.data.lastName,
                    email: response.data.email,
                    phoneNumber: response.data.phoneNumber})
            }
            }).catch((error) => {console.error("error " + error)})}

    render() {
        if (this.state.redirect) {
            return <Redirect to={this.state.redirect} />
        }

        const { currentUser } = this.state;

        return (
            <div className="container">
                {<div>
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
                    </div>}
            </div>
        );
    }
}
