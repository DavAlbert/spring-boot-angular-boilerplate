import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TokenResponse } from '../models/responses/token.response';
import { UserRegisterRequest } from '../models/requests/user-register.request';
import { UserLoginRequest } from '../models/requests/user-login.request';
import { UserInfoResponse } from '../models/responses/userinfo.response';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  loggedIn: Boolean = false;

  hostPath = '/api'
  authenticationPath = '/authentication';
  registerPath = '/register';
  loginPath = '/login';
  userPath = '/user';
  userInfoPath = '/details';
  
  constructor(private router: Router, private httpClient: HttpClient) { }

  setLoggedIn(loggedIn: Boolean) {
    if (loggedIn == false) {
      localStorage.removeItem('Access-Token');
      this.router.navigate(['login']);
    }
    this.loggedIn = loggedIn;
  }

  fetchUserInfo() {
    return this.httpClient.get<UserInfoResponse>(this.hostPath + this.userPath + this.userInfoPath);
  }

  loginUser(userLoginRequest: UserLoginRequest) {
    return this.httpClient.post<TokenResponse>(this.hostPath + this.authenticationPath + this.loginPath, userLoginRequest);
  }

  registerUser(userRegisterRequest: UserRegisterRequest) {
    return this.httpClient.post<TokenResponse>(this.hostPath + this.authenticationPath + this.registerPath, userRegisterRequest);
  }
}
