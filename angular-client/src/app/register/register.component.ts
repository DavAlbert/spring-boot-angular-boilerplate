import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../services/user.service';
import { UserRegisterRequest } from '../models/requests/user-register.request';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router,
    private userService: UserService) { }

  
  buildRegisterForm() {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  ngOnInit() {
    this.buildRegisterForm();
  }

  onSubmit() {
    let registerRequest = new UserRegisterRequest();
    registerRequest.username = this.registerForm.controls.username.value;
    registerRequest.firstName = this.registerForm.controls.firstName.value;
    registerRequest.lastName = this.registerForm.controls.lastName.value;
    registerRequest.email = this.registerForm.controls.email.value;
    registerRequest.password = this.registerForm.controls.password.value;
    
    this.userService.registerUser(registerRequest).subscribe(res => {
      console.log(res);
    }, err => {
      console.log(err);
    })
  }

  navigateToLogin() {
    this.router.navigate(['login']);
  }

}
