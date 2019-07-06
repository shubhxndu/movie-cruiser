package com.shubhendu.moviecruiserserverapplication.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubhendu.moviecruiserserverapplication.domain.Movie;
import com.shubhendu.moviecruiserserverapplication.exception.MovieAlreadyExistsException;
import com.shubhendu.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.shubhendu.moviecruiserserverapplication.service.MovieService;

import io.jsonwebtoken.Jwts;

@CrossOrigin

@RestController
@RequestMapping(path = "/api/v1/movieservice")

public class MovieController {
	
	/**
	 * 
	 */
	private MovieService movieService;
	
	/**
	 * @param movieService
	 */
	@Autowired
	public MovieController(final MovieService movieService){
		this.movieService = movieService;
	}
	
	/**
	 * @param movie
	 * @return
	 */
	@PostMapping("/movie")
	public ResponseEntity<?> saveNewMovie(@RequestBody final Movie movie,HttpServletRequest request,
			HttpServletResponse response){
		ResponseEntity<?> responseEntity;
		final String authHeader = request.getHeader("Authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		System.out.println(userId);
		try{
			movie.setUserId(userId);
			movieService.saveMovie(movie);
			responseEntity = new ResponseEntity<Movie>(movie,HttpStatus.CREATED);
		}catch(MovieAlreadyExistsException e){
			responseEntity = new ResponseEntity<String>("{\"movie\": \"" + e.getMessage() + "\"}",HttpStatus.CONFLICT);
		}
			return responseEntity;
	}
	
	/**
	 * @param id
	 * @param movie
	 * @return
	 */
	@PutMapping(path = "/movie/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable("id") final Integer id, @RequestBody Movie movie){
		 ResponseEntity<?> responseEntity;
		try{
			final Movie fetchedMovie = movieService.updateMovie(movie);
			responseEntity = new ResponseEntity<Movie>(fetchedMovie, HttpStatus.OK);
		}catch(MovieNotFoundException e){
			responseEntity = new ResponseEntity<String> ("{\"movie\": \"" + e.getMessage() + "\"}",HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
	
	
	/**
	 * @param id
	 * @return
	 */
	@DeleteMapping(path = "/movie/{id}")
	public ResponseEntity<?> deleteMovieById(@PathVariable("id") final int id){
		 ResponseEntity<?> responseEntity;
		try{
			 movieService.deleteMovieById(id);
			 responseEntity = new ResponseEntity<String> ("Movie Deleted Successfully",HttpStatus.OK);
		}catch(MovieNotFoundException e){
			responseEntity = new ResponseEntity<String> ("{\"movie\": \"" + e.getMessage() + "\"}",HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	
	/**
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/movie/{id}")
	public ResponseEntity<?> fetchMovieById(@PathVariable("id") final int id){
		 ResponseEntity<?> responseEntity;
		 Movie thisMovie = null;
		try{
			 thisMovie = movieService.getMovieById(id);
			 responseEntity = new ResponseEntity<Movie> (thisMovie,HttpStatus.OK);
		}catch(MovieNotFoundException e){
			responseEntity = new ResponseEntity<String> ("{\"movie\": \"" + e.getMessage() + "\"}",HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	
	/**
	 * @return
	 */
	@GetMapping("/movies")
	public ResponseEntity<List<Movie>> fetchAllMovies(final ServletRequest req, final ServletResponse res){
		final HttpServletRequest request = (HttpServletRequest) req;
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		
		
		return new ResponseEntity<List<Movie>>(movieService.getMyMovies(userId), HttpStatus.OK);
	}
}		
