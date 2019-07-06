import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MovieService } from './movie.service';
import { ContainerComponent } from './components/container/container.component';
import { MovieRouterModule } from './movie-router.module';
import {MatCardModule } from '@angular/material/card';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { TmdbContainerComponent } from './components/tmdb-container/tmdb-container.component';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule} from '@angular/material/snack-bar';
import { MatInputModule } from '@angular/material/input';

import { MatDialogModule} from '@angular/material/dialog';
import { MovieDialogComponent } from './components/movie-dialog/movie-dialog.component';
import { FormsModule } from '@angular/forms';
import { SearchComponent } from './components/search/search.component';
import { TokenInterceptor } from './interceptor.service';

@NgModule({
  declarations: [ThumbnailComponent,
     ContainerComponent, 
     WatchlistComponent, 
     TmdbContainerComponent,      
     MovieDialogComponent, SearchComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    MovieRouterModule,
    MatCardModule,
    MatButtonModule,
    MatSnackBarModule,
    MatInputModule,
    MatDialogModule,
    FormsModule
  ],
  exports: [
    MovieRouterModule,
    ThumbnailComponent,
    ContainerComponent, 
    WatchlistComponent, 
    TmdbContainerComponent,      
    MovieDialogComponent,
    SearchComponent
  ],
  providers: [MovieService,{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  }],
  entryComponents: [MovieDialogComponent]
})
export class MovieModule { }
