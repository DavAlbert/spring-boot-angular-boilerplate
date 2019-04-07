# spring-boot-angular-boilerplate

Das Projekt beinhaltet:

- Login
- Registrierung
- JWT Authentifizierung
- Übersichtsseite
- Benutzer Rollen

> Java version: 11
> Folgendes muss auf dem Host installiert werden:
> NodeJS (NPM), Maven, NGINX

# Installation

```
$ git clone https://github.com/DavAlbert/spring-boot-angular-boilerplate.git --depth 1
$ cd spring-boot-angular-boilerplate
$ mvn clean install
$ cd angular-client
$ npm install
```

# NGINX Konfiguration

```
server {
    listen       80;
    server_name  localhost;
    location ^~ / {
        proxy_pass http://127.0.0.1:4200/;
    }
location ^~ /api/ {
        proxy_pass http://127.0.0.1:8080/;
    }

}
```

# Login per REST Call
![restLogin](https://github.com/DavAlbert/spring-boot-angular-boilerplate/blob/master/screenshots/login-rest.png?raw=true)

# Registrierung per REST Call
![restRegister](https://github.com/DavAlbert/spring-boot-angular-boilerplate/blob/master/screenshots/register-rest.png?raw=true)

# Nutzer Infos per REST Call
![userInfos](https://github.com/DavAlbert/spring-boot-angular-boilerplate/blob/master/screenshots/details-rest.png?raw=true)

# Login Web-Interface
![loginInterface](https://github.com/DavAlbert/spring-boot-angular-boilerplate/blob/master/screenshots/login.png?raw=true)

# Login Web-Interface (Falsches Passwort)
![loginInterfaceWrong](https://github.com/DavAlbert/spring-boot-angular-boilerplate/blob/master/screenshots/fail-login.png?raw=true)

# Registrierung Web-Interface
![registerInterface](https://github.com/DavAlbert/spring-boot-angular-boilerplate/blob/master/screenshots/register.png?raw=true)

# Übersicht Web-Interface
![overviewInterface](https://github.com/DavAlbert/spring-boot-angular-boilerplate/blob/master/screenshots/loggedin.png?raw=true)

Mit diesem Boilerplate können Angular 7 & Spring Boot Web Projekte erzeugt werden.

