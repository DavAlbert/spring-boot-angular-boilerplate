import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/components/common/menuitem';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {

  constructor() { }

  navItems: MenuItem[];

  ngOnInit() {
    this.navItems = [
      { label: 'Einloggen', icon: 'pi pi-fw pi-user' },
      { label: 'Registrieren', icon: 'pi pi-fw pi-key' },
    ]
  }

}
