import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/services/authentication.service";

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrls: ['./activate-account.component.scss']
})
export class ActivateAccountComponent {

  message = '';
  isOkay = true;
  submitted = false;

  constructor(
    private router: Router,
    private authService: AuthenticationService
  ) {
  }

  onCodeCompleted(token: string) {
    this.confirmAccount(token);

  }

  redirectToLogin() {
    this.router.navigate(['login']);
  }

  private confirmAccount(token: string) {
    this.authService.confirm({
      token
    }).subscribe({
      next: () => {
        this.message = "You account has been successfully activated. Now you can proceed to log in ! ";
        this.submitted = true;
        this.isOkay = true;
      },
      error: (err) => {
        this.message = "Token has been expired ! ";
        this.submitted = true;
        this.isOkay = false;
      }
    })
  }
}


