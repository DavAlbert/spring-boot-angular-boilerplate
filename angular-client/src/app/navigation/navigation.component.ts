import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/components/common/menuitem';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {

  constructor(private userService: UserService) { }

  navItems: MenuItem[];
  navItemsLoggedIn: MenuItem[];

  ngOnInit() {
    this.navItems = [
      { label: 'Einloggen', icon: 'pi pi-fw pi-user', routerLink: 'login' },
      { label: 'Registrieren', icon: 'pi pi-fw pi-key', routerLink: 'register' },
    ];
    this.navItemsLoggedIn = [
      { label: 'Übersicht', icon: 'pi pi-fw pi-eye', routerLink: 'overview' },
      { label: 'Ausloggen', icon: 'pi pi-fw pi-key', command: () => this.userService.setLoggedIn(false) }
    ];
  }

}
