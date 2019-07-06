import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MatButtonModule} from '@angular/material/button';
import { BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MovieModule } from './modules/movie/movie.module';
import { AuthenticationModule } from './modules/authentication/authentication.module';
import { AuthGuardService } from './authGuard.service';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    MovieModule,
    AuthenticationModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule
  ], 
  providers: [AuthGuardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
