import { Component, OnInit, Input } from '@angular/core';
import {Movie} from '../../movie';
import { MovieService } from '../../movie.service';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'movie-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {
  
  @Input()
  movies: Array<Movie>;

  @Input()
  useWatchListApi:boolean;

  movieType: string;
  constructor(private snackbar: MatSnackBar, private movieService: MovieService ) { 
   
    
  }

  ngOnInit() {

  }

  addToWatchList(movie){
      let message = `${movie.title} added to watchlist`;
     this.movieService.saveWatchListMovies(movie).subscribe(movie => {
      this.snackbar.open(message,'',{
        duration: 1000
      })
     });
   
  }

  deleteFromWatchList(movie){
    let message = `${movie.title} deleted from watchlist`;
    
    // for(var i=0;i<this.movies.length;i++){
    //   if(this.movies[i].title === movie.title){
    //     this.movies.splice(i,1);
    //   }
    // }
    this.movieService.deleteFromMyWatchlist(movie).subscribe(movie => {
     this.snackbar.open(message,'',{
       duration: 1000
     })
    });

    const index = this.movies.indexOf(movie);
    this.movies.splice(index,1);
  }
}
