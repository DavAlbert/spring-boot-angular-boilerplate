import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { UserLoginRequest } from '../models/requests/user-login.request';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router,
    private userService: UserService, private messageService: MessageService) { }

  showError(message: string) {
    this.messageService.clear();
    this.messageService.add({ severity:'error', detail: message });
  }

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
      localStorage.setItem('Access-Token', res.access_token);
      this.userService.setLoggedIn(true);
      this.router.navigate(['overview']);
    }, err => {
      this.showError('Benutzername oder Kennwort falsch!');
    });
  }

  navigateToRegister() {
    this.router.navigate(['register']);
  }

  ngOnInit() {
    this.buildLoginForm();
  }

}
