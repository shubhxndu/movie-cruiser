import { Component } from '@angular/core';
import { ThumbnailComponent } from './modules/movie/components/thumbnail/thumbnail.component';
import { AuthenticateService } from './modules/authentication/authentication.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'movie-cruiser-capsule-frontend';

  constructor(private auth: AuthenticateService,private routes:Router){

  }

  logout(){
    this.auth.deleteToken();
    this.routes.navigate(['/login']);
  }
}
