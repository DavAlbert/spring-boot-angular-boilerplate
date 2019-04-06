import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router) { }

  buildLoginForm() {
    this.loginForm = this.formBuilder.group({
        username: ['', Validators.required],
        password: ['', Validators.required],
      });
  }

  onSubmit() {

  }

  navigateToRegister() {
    this.router.navigate(['register']);
  }

  ngOnInit() {
    this.buildLoginForm();
  }

}
