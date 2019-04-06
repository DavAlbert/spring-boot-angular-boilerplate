import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { UserLoginRequest } from '../models/requests/user-login.request';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router,
    private userService: UserService) { }

  buildLoginForm() {
    this.loginForm = this.formBuilder.group({
        username: ['', Validators.required],
        password: ['', Validators.required],
      });
  }

  onSubmit() {
    let loginRequest = new UserLoginRequest();
    loginRequest.username = this.loginForm.controls.username.value;
    loginRequest.password = this.loginForm.controls.password.value;

    this.userService.loginUser(loginRequest).subscribe(res => {
      console.log(res);
    }, err => {
      console.log(err);
    });
  }

  navigateToRegister() {
    this.router.navigate(['register']);
  }

  ngOnInit() {
    this.buildLoginForm();
  }

}
