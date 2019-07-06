import { Component, OnInit, Inject } from '@angular/core';
import { Movie } from '../../movie';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MovieService } from '../../movie.service';

@Component({
  selector: 'movie-movie-dialog',
  templateUrl: './movie-dialog.component.html',
  styleUrls: ['./movie-dialog.component.css']
})
export class MovieDialogComponent implements OnInit {
  movie: Movie;
  comments: string;
  actionType: string;

  constructor(public snackBar: MatSnackBar, public dialogRef: MatDialogRef<MovieDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private movieService: MovieService) { 
      this.comments = data.obj.movieComments;
      this.movie = data.obj;
      this.actionType = data.actionType;
    }

    onNoClick(){
      this.dialogRef.close();
    }
    updateComments(){
      this.movie.comments = this.comments;
      this.dialogRef.close();
      this.movieService.updateComments(this.movie).subscribe(movie=> {
        this.snackBar.open("Movie Updated to WatchList Successfully", "",{
          duration: 2000,
        })
      })
    }

  ngOnInit() {
  }

}
