package com.shubhendu.moviecruiserserverapplication.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.shubhendu.moviecruiserserverapplication.domain.Movie;
import com.shubhendu.moviecruiserserverapplication.exception.MovieAlreadyExistsException;
import com.shubhendu.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.shubhendu.moviecruiserserverapplication.repository.MovieRepository;
import com.shubhendu.moviecruiserserverapplication.service.MovieServiceImpl;

public class MovieServiceImplTest {
	
	/**
	 * 
	 */
	@Mock
	private transient MovieRepository movieRepository;
	
	/**
	 * 
	 */
	private transient Movie movie;
	
	/**
	 * 
	 */
	@InjectMocks
	private transient MovieServiceImpl movieServiceImpl;
	
	/**
	 * 
	 */
	transient Optional<Movie> options;
	
	/**
	 * 
	 */
	public MovieServiceImplTest() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 */
	@Before
	public void setupMock(){
		MockitoAnnotations.initMocks(this);
		movie = new Movie(1,"1234","superman","Jake123","Good Movie","www.abc.com","2015-03-23",45.5,112);
		options = Optional.of(movie);
	}
	
	/**
	 * 
	 */
	
	@Test
	public void testMockCreation(){
		assertNotNull(movie);
		assertNotNull("jpa repository creation fails: use @injectmocks on movieserviceimpl", movieRepository);
	}
	
	/**
	 * @throws MovieAlreadyExistsException
	 */
	@Test
	public void testSaveMovieSuccess() throws MovieAlreadyExistsException {
		when(movieRepository.save(movie)).thenReturn(movie);
		final boolean flag = movieServiceImpl.saveMovie(movie);
		assertTrue("saving movie failed, the call to movieDAOimpl is returning false",flag);
		verify(movieRepository, times(1)).save(movie);
		verify(movieRepository, times(1)).findById(movie.getId());
	}
	

	/**
	 * @throws MovieAlreadyExistsException
	 */
	@Test(expected = MovieAlreadyExistsException.class)
	public void testSaveMovieFailure() throws MovieAlreadyExistsException {
		when(movieRepository.findById(1)).thenReturn(options);
		when(movieRepository.save(movie)).thenReturn(movie);
		final boolean flag = movieServiceImpl.saveMovie(movie);
		assertFalse("saving movie failed",flag);
		verify(movieRepository, times(1)).save(movie);
		//verify(movieRepository, times(1)).findById(movie.getId());
	}
	
	/**
	 * @throws MovieNotFoundException
	 */
	@Test
	public void testUpdateMovie() throws MovieNotFoundException {
		when(movieRepository.findById(1)).thenReturn(options);
		when(movieRepository.save(movie)).thenReturn(movie);
		movie.setComments("not so good movie");
		final Movie movie1 = movieServiceImpl.updateMovie(movie);
		assertEquals("updating movie failed","not so good movie", movie1.getComments());
		verify(movieRepository,times(1)).save(movie);
		verify(movieRepository,times(1)).findById(movie.getId());
	}
	
	/**
	 * @throws MovieNotFoundException
	 */
	@Test
	public void testDeleteMovieById() throws MovieNotFoundException {
		when(movieRepository.findById(1)).thenReturn(options);
		doNothing().when(movieRepository).delete(movie);		
		
		final boolean flag = movieServiceImpl.deleteMovieById(1);
		assertTrue("deleting movie failed", flag);		
		verify(movieRepository,times(1)).delete(movie);
		verify(movieRepository,times(1)).findById(movie.getId());
	}
	
	/**
	 * @throws MovieNotFoundException
	 */
	@Test
	public void testGetMovieById() throws MovieNotFoundException {
		when(movieRepository.findById(1)).thenReturn(options);
		final Movie movie1 = movieServiceImpl.getMovieById(1);
		assertEquals("updating movie by id failed", movie1, movie);
		verify(movieRepository,times(1)).findById(movie.getId());
	}
	
	/**
	 * @throws MovieNotFoundException
	 */
	@Test
	public void testGetMyMovies() throws MovieNotFoundException {
		final List<Movie> movieList = new ArrayList<>(1);
		when(movieRepository.findByUserId("ashu")).thenReturn(movieList);
		final List<Movie> movies1 = movieServiceImpl.getMyMovies("ashu");
		assertEquals(movieList, movies1);
		verify(movieRepository, times(1)).findByUserId("ashu");
	}
}
