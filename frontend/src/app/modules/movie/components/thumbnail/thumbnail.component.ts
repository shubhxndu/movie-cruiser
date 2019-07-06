import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import {Movie} from '../../movie';
import {HttpClient} from '@angular/common/http';
import { MovieService } from '../../movie.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { MovieDialogComponent } from '../movie-dialog/movie-dialog.component';
@Component({
  selector: 'movie-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {
  @Input()
  movie: Movie;

  @Input()
  useWatchListApi: boolean;

  @Output()
  addMovie = new EventEmitter();

  @Output()
  deleteMovie = new EventEmitter();

  
  constructor(private snackbar: MatSnackBar,private dialog: MatDialog) { 
   
  }

  ngOnInit() {
      
  }

  addToWatchlist(){
    this.addMovie.emit(this.movie);
   
  }

  deleteFromWatchlist(){
    this.deleteMovie.emit(this.movie);
  }

  updateFromWatchlist(actionType){
    let dialogRef = this.dialog.open(MovieDialogComponent, {
      width: '400px',
      data: {obj: this.movie, actionType:actionType}
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    })

  }
}
