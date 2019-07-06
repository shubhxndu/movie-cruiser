import { Component, OnInit } from '@angular/core';
import { User } from '../User';
import { AuthenticateService } from '../authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  newUser: User;

  constructor(private authService: AuthenticateService,private router: Router) { 
    this.newUser = new User();
  }

  ngOnInit() {
  }

  loginUser(){
    this.authService.loginUser(this.newUser).subscribe(data => {
      console.log(this.newUser)
      if(data['token']){
        this.authService.setToken(data['token']);
        console.log(data['token'])
        this.router.navigate(['/movies/popular']);
      }
    })
  }


}
