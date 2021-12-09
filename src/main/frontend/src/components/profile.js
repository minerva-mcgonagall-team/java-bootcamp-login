import React, {Component} from "react";
import {Redirect} from "react-router-dom";
import AuthService from "../services/auth";

export default class Profile extends Component {
    constructor(props) {
        super(props);

        this.state = {
            redirect: null,
            userReady: false,
            currentUser: {username: ""}
        };
    }

    componentDidMount() {
        const currentUser = AuthService.getCurrentUser();

        if (!currentUser) this.setState({redirect: "/home"});
        this.setState({currentUser: currentUser, userReady: true})
    }

    render() {
        if (this.state.redirect) {
            return <Redirect to={this.state.redirect}/>
        }

        const {currentUser} = this.state;

        return (
            <div className="container">
                {(this.state.userReady) ?
                    <div >
                        <header className="jumbotron">
                            <h3>
                                <strong>{currentUser.username}</strong> Profile
                            </h3>
                            <p>
                                <strong>First name:</strong>{" "}
                                {currentUser.firstname}<br />
                                <strong>Email:</strong>{" "}
                                {currentUser.email}<br />
                                <strong>Last name:</strong>{" "}
                                {currentUser.lastname}<br />
                                <strong>Phone Number:</strong>{" "}
                                {currentUser.phoneNumber}
                            </p>
                        </header>



                    </div> : null}
            </div>
        );
    }
}
