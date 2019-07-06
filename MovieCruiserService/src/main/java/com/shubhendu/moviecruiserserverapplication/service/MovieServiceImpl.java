package com.shubhendu.moviecruiserserverapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubhendu.moviecruiserserverapplication.domain.Movie;
import com.shubhendu.moviecruiserserverapplication.exception.MovieAlreadyExistsException;
import com.shubhendu.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.shubhendu.moviecruiserserverapplication.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService{

	/**
	 * 
	 */
	private final transient MovieRepository movieRepository;
	
	
	/**
	 * @param movieRepository
	 */
	@Autowired
	public MovieServiceImpl(final MovieRepository movieRepository) {
		super();
		this.movieRepository = movieRepository;
	}

	/* (non-Javadoc)
	 * @see com.cognizant.moviecruiserserverapplication.service.MovieService#saveMovie(com.cognizant.moviecruiserserverapplication.domain.Movie)
	 */
	@Override
	public boolean saveMovie(final Movie movie) throws MovieAlreadyExistsException {
		
		final Optional<Movie> object = movieRepository.findById(movie.getId());
		if(object.isPresent()){
			throw new MovieAlreadyExistsException("Could not save movie. Movie already exists");
		}
		movieRepository.save(movie);
		return true;	
		
	}

	/* (non-Javadoc)
	 * @see com.cognizant.moviecruiserserverapplication.service.MovieService#updateMovie(com.cognizant.moviecruiserserverapplication.domain.Movie)
	 */
	@Override
	public Movie updateMovie(final Movie updateMovie) throws MovieNotFoundException {
		
		final Movie movie = movieRepository.findById(updateMovie.getId()).orElse(null);
		
		if(movie == null){
			throw new MovieNotFoundException("Couldn't update Movie. Movie not found");
		}
		movie.setComments(updateMovie.getComments());
		movieRepository.save(movie);
		return movie;
	}

	/* (non-Javadoc)
	 * @see com.cognizant.moviecruiserserverapplication.service.MovieService#deleteMovieById(int)
	 */
	@Override
	public boolean deleteMovieById(int id) throws MovieNotFoundException {
		
		final Movie movie = movieRepository.findById(id).orElse(null);
		if(movie==null){
			throw new MovieNotFoundException("Could not delete. Movie not found");
		}
		movieRepository.delete(movie);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.cognizant.moviecruiserserverapplication.service.MovieService#getAllMovies()
	 */
	@Override
	public List<Movie> getMyMovies(String userId) {
		
		return movieRepository.findByUserId(userId);
	}

	/* (non-Javadoc)
	 * @see com.cognizant.moviecruiserserverapplication.service.MovieService#getMovieById(int)
	 */
	@Override
	public Movie getMovieById(int id) throws MovieNotFoundException {
		
		final Movie movie = movieRepository.findById(id).get();
		
		if(movie==null){
			throw new MovieNotFoundException("Movie Not Found");
		}
		return movie;
	}

}
