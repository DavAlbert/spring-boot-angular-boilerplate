import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenResponse } from '../models/responses/token.response';
import { UserRegisterRequest } from '../models/requests/user-register.request';
import { UserLoginRequest } from '../models/requests/user-login.request';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  hostPath = '/api'
  authenticationPath = '/authentication';
  registerPath = '/register';
  loginPath = '/login';

  constructor(private httpClient: HttpClient) { }

  loginUser(UserLoginRequest: UserLoginRequest) {
    return this.httpClient.post(this.hostPath + this.authenticationPath + this.registerPath, UserLoginRequest);
  }

  registerUser(userRegisterRequest: UserRegisterRequest) {
      return this.httpClient.post(this.hostPath + this.authenticationPath + this.loginPath, UserRegisterRequest);
  }
}
