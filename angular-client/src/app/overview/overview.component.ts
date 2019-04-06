import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss']
})
export class OverviewComponent implements OnInit {

  username: string;
  firstName: string;
  lastName: string;
  email: string;

  constructor(private userService: UserService) { }

  fetchUserInfos() {
    this.userService.fetchUserInfo().subscribe(res => {
      this.username = res.username;
      this.firstName = res.firstName;
      this.lastName = res.lastName;
      this.email = res.email;
    }, err => {
      this.userService.setLoggedIn(false);
    });
  }

  ngOnInit() {
    this.fetchUserInfos();
  }

}
