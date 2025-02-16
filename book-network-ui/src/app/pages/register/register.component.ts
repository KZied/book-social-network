import { Component } from '@angular/core';
import {RegistrationRequest} from "../../services/models/registration-request";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/services/authentication.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  registerRequest: RegistrationRequest = {  email: '', firstname: '', lastname: '', password: ''};
  errorMsgs: Array<string> = [];

  constructor(
    private router: Router,
    private authService: AuthenticationService,
  ) {
  }

  register() {
    this.errorMsgs = [];
    this.authService.register({
      body: this.registerRequest
    }).subscribe({
      next: (res) => {
        this.router.navigate(['activate-account']);
      },
      error: (err) => {
          this.errorMsgs = err.error.validationErrors;
      }
    })
  }

  login() {
    this.router.navigate(['login']);
  }
}
