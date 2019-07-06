import { Injectable } from '@angular/core';
import {HttpInterceptor, HttpRequest, HttpHandler, HttpEvent} from '@angular/common/http';
import { Router, CanActivate } from '@angular/router';
import { AuthenticateService } from './modules/authentication/authentication.service';


@Injectable({
  providedIn: 'root'
})

export class AuthGuardService implements CanActivate {

    constructor(private route: Router,private authService: AuthenticateService) {

    }

    canActivate(){
    if(!this.authService.isTokenExpired()){
      console.log("token valid");
        return true;
    }
    this.route.navigate(['/login']);
    console.log("token invalid or unavailable");
    return false;
    }

}
