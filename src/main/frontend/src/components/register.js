import React, { Component } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import { isEmail } from "validator";

import AuthService from "../services/auth";
import {Button} from "react-bootstrap";

const required = value => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

const validateEmail = value => {
  if (!isEmail(value)) {
    return (
      <div className="alert alert-danger" role="alert">
        This is not a valid email.
      </div>
    );
  }
};

const validateName = value => {
  if (value.length < 3 || value.length > 20) {
    return (
      <div className="alert alert-danger" role="alert">
        The name must be between 3 and 20 characters.
      </div>
    );
  }
};

const validatePassword = value => {
  if (value.length < 6 || value.length > 40) {
    return (
      <div className="alert alert-danger" role="alert">
        The password must be between 6 and 40 characters.
      </div>
    );
  }
};

export default class Register extends Component {
  constructor(props) {
    super(props);
    this.handleRegister = this.handleRegister.bind(this);
    this.onChangeFirstName = this.onChangeFirstName.bind(this);
    this.onChangeLastName = this.onChangeLastName.bind(this);
    this.onChangePhoneNumber = this.onChangePhoneNumber.bind(this);
    this.onChangeEmail = this.onChangeEmail.bind(this);
    this.onChangePassword = this.onChangePassword.bind(this);
/*
there is also an alternative with useState hooks but I didn't dive in that deeper enough tho
 */
    this.state = {
      firstname: "",
      lastname:"",
      phoneNumber:"",
      email: "",
      password: "",
      successful: false,
      message: ""
    };
  }

  onChangeFirstName(e) {
    this.setState({
      firstname: e.target.value
    });
  }
  onChangeLastName(e) {
    this.setState({
      lastname: e.target.value
    });
  }
  onChangePhoneNumber(e) {
    this.setState({
      phoneNumber: e.target.value
    });
  }
  onChangeEmail(e) {
    this.setState({
      email: e.target.value
    });
  }

  onChangePassword(e) {
    this.setState({
      password: e.target.value
    });
  }

  handleRegister(e) {
    e.preventDefault();

    this.setState({
      message: "",
      successful: false
    });

    this.form.validateAll();

    if (this.checkBtn.context._errors.length === 0) {
      AuthService.register(
        this.state.firstname,
        this.state.lastname,
        this.state.email,
        this.state.phoneNumber,
        this.state.password
      ).then(
        response => {
          this.setState({
            message: response.data.message,
            successful: true
          });
        },
        error => {
          const resMessage =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();

          this.setState({
            successful: false,
            message: resMessage
          });
        }
      );
    }
  }

  render() {
    return (
      <div className="col-md-12">
        <div className="card card-container">
          <img
            src="https://i.fbcd.co/products/resized/resized-750-500/1622-0217e58ea40d860a0e01b5a492884a87e7530ed8f72d1f9b0405159a56090150.jpg"
            alt="profile-img"
            className="profile-img-card"
          />

          <Form
            onSubmit={this.handleRegister}
            ref={c => {
              this.form = c;
            }}
          >
            {!this.state.successful ? (
              <div>
                <div className="form-group">
                  <label htmlFor="firstname">First name</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="firstname"
                    value={this.state.firstname}
                    onChange={this.onChangeFirstName}
                    validations={[required, validateName]}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="lastname">Last name</label>
                  <Input
                      type="text"
                      className="form-control"
                      name="lastname"
                      value={this.state.lastname}
                      onChange={this.onChangeLastName}
                      validations={[required, validateName]}
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="email">Email</label>
                  <Input
                      type="email"
                      className="form-control"
                      name="lastname"
                      value={this.state.email}
                      onChange={this.onChangeEmail}
                      validations={[required, validateEmail]}
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="phoneNumber">Phone number</label>
                  <Input
                      type="tel"
                      className="form-control"
                      name="phoneNumber"
                      value={this.state.phoneNumber}
                      onChange={this.onChangePhoneNumber}
                      validations={[required]}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="password">Password</label>
                  <Input
                    type="password"
                    className="form-control"
                    name="password"
                    value={this.state.password}
                    onChange={this.onChangePassword}
                    validations={[required, validatePassword]}
                  />
                </div>

                <div className="form-group">
                  <button className="btn btn-primary btn-block">Sign Up</button>
                </div>
              </div>
            ): (
                <div>
                  <h3 style={{textAlign: 'center'}}> Register was successful! </h3>
                  <br></br>
                  <div className="form-group">
                    <button className="btn btn-primary btn-block" onClick={() => {
                      window.location='/index.html';
                    }}>Proceed to application</button>
                  </div>
                </div>
            )}
            {this.state.message && (
                <div className="form-group">
                  <div
                      className={
                        this.state.successful
                            ? "alert alert-success"
                            : "alert alert-danger"
                      }
                      role="alert"
                  >
                    {this.state.message}
                  </div>
                </div>
            )}
            <CheckButton
                style={{ display: "none" }}
                ref={c => {
                  this.checkBtn = c;
                }}
            />
          </Form>

        </div>
      </div>
    );
  }
}
