import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
/* Hier werden die Komponenten geladen */
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
/* Hier werden PrimeNG Module geladen */
import { ButtonModule } from 'primeng/button';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ButtonModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
