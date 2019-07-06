import { Injectable } from '@angular/core';
import {HttpInterceptor, HttpRequest, HttpHandler, HttpEvent} from '@angular/common/http';
import { AuthenticateService } from '../authentication/authentication.service';
import { Observable } from 'rxjs';
export const TOKEN_NAME: string = 'jwt_token';


@Injectable({
  providedIn: 'root'
})

export class TokenInterceptor implements HttpInterceptor {

    constructor(private auth: AuthenticateService) {
    }

    intercept(request: HttpRequest<any>,next: HttpHandler): Observable<HttpEvent<any>>{

        request = request.clone({
            setHeaders: {
                Authorization: `Bearer ${this.auth.getToken()}`
            }
        })

        return next.handle(request);
    }

}
