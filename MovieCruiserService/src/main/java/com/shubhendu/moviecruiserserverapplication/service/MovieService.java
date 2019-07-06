package com.shubhendu.moviecruiserserverapplication.service;

import java.util.List;

import com.shubhendu.moviecruiserserverapplication.domain.Movie;
import com.shubhendu.moviecruiserserverapplication.exception.MovieAlreadyExistsException;
import com.shubhendu.moviecruiserserverapplication.exception.MovieNotFoundException;

public interface MovieService {
	/**
	 * @param movie
	 * @return
	 * @throws MovieAlreadyExistsException
	 */
	boolean saveMovie(Movie movie) throws MovieAlreadyExistsException;
	/**
	 * @param movie
	 * @return
	 * @throws MovieNotFoundException
	 */
	Movie updateMovie(Movie movie) throws MovieNotFoundException;
	
	/**
	 * @param id
	 * @return
	 * @throws MovieNotFoundException
	 */
	boolean deleteMovieById(int id) throws MovieNotFoundException;
	/**
	 * @param id
	 * @return
	 * @throws MovieNotFoundException
	 */
	public Movie getMovieById(final int id) throws MovieNotFoundException;
	/**
	 * @return
	 */
	List<Movie> getMyMovies(String userId);
}
