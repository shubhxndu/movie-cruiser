import { Injectable } from '@angular/core';
import { map, retry } from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';
import {Movie} from './movie';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class MovieService {
  tmdbEndpoint: string;
  imagePrefix: string;
  apiKey: string;
  watchlistEndpoint: string;
  springEndPoint: string;
  search: string;
  constructor(private http: HttpClient) {
    this.apiKey=`api_key=f20d283a3159c1fe38ba829814037d4b`;
    this.tmdbEndpoint = `https://api.themoviedb.org/3/movie`;
    this.imagePrefix = `https://image.tmdb.org/t/p/w500`;
    this.springEndPoint = `http://192.168.99.100:8080/api/v1/movieservice/movie`;
    this.watchlistEndpoint = `http://192.168.99.100:8080/api/v1/movieservice/movies`;

    this.search = `https://api.themoviedb.org/3/search/movie`
    }
  searchMovies(searchKey: string):Observable<Array<Movie>>{
   
    if(searchKey.length>0){
      const url = `${this.search}?${this.apiKey}&language=en-US&page=1&include_adult=false&query=${searchKey}`
      console.log(url)
      return this.http.get(url)
      .pipe(retry(3),
      map(this.pickMovieResults),
      map(this.transformPosterPath.bind(this)))
    }
  }
  getMovies(type: string, page:number = 1):Observable<Array<Movie>>{
    const endPoint = `${this.tmdbEndpoint}/${type}?${this.apiKey}&page=${page}`;
    
    return this.http.get(endPoint).pipe(
      retry(3),
      map(this.pickMovieResults),
      map(this.transformPosterPath.bind(this))
      );
  }
  saveWatchListMovies(movie){
    console.log("Movie saved "+JSON.stringify(movie));
    return this.http.post(this.springEndPoint,movie);
  }

  getMyWatchlist():Observable<Array<Movie>>{
    this.springEndPoint =`${this.springEndPoint}`;

    return this.http.get<Array<Movie>>(this.watchlistEndpoint);
  }
   transformPosterPath(movies): Array<Movie>{
     
     return movies.map(movie => {
       movie.poster_path = `${this.imagePrefix}${movie.poster_path}`;
       return movie;
     })
     
   }

   updateComments(movie){
     const url = `${this.springEndPoint}/${movie.id}`
     return this.http.put(url,movie);
   }
   deleteFromMyWatchlist(movie:Movie){
     const url = `${this.springEndPoint}/${movie.id}`;
     return this.http.delete(url,{responseType: 'text'});
   }
   pickMovieResults(response){
     return response['results'];
   }

   addMovieToWatchlist(movie){
    return this.http.post(this.watchlistEndpoint, movie);
   }

   getWatchlistedMovies(): Observable<Array<Movie>>{
    return this.http.get<Array<Movie>>(this.watchlistEndpoint);
   }

}
