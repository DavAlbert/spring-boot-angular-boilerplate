import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { UserService } from '../services/user.service';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

    constructor(private userServiec: UserService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let accessToken = localStorage.getItem('Access-Token');
        if (accessToken != null) {
            request = request.clone({
                setHeaders: { 
                    Authorization: `Bearer ${accessToken}`
                }
            });
        }
        return next.handle(request).pipe(catchError(err => {
            if (err.status === 401) {
                this.userServiec.setLoggedIn(false);
            }
            const error = err.error.message || err.statusText;
            return throwError(error);
        }));
    }
}