package com.shubhendu.moviecruiserserverapplication.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.shubhendu.moviecruiserserverapplication.controller.MovieController;
import com.shubhendu.moviecruiserserverapplication.domain.Movie;
import com.shubhendu.moviecruiserserverapplication.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

	/**
	 * 
	 */
	@Autowired
	private transient MockMvc mvc;

	/**
	 * 
	 */
	@MockBean
	private transient MovieService service;
	
	@InjectMocks
	private MovieController movieController;
	/**
	 * 
	 */
	public MovieControllerTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private transient Movie movie;

	/**
	 * 
	 */
	static List<Movie> movies;

	/**
	 * 
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		movie = new Movie(1563,"2323","ironman","Jake123","Good Movie","www.abc.com","2015-03-23",45.5,112);
		mvc = MockMvcBuilders.standaloneSetup(movieController).build();
		movies = new ArrayList<>();
		movie = new Movie(1,"1234","superman","Jake123","Good Movie","www.abc.com","2015-03-23",45.5,112);
		movies.add(movie);
		movie = new Movie(2,"1235","superman2","Jake123","Good Movie","www.abc.com","2015-03-23",45.5,112);
		movies.add(movie);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testSaveNewMovie() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKYWtlMTIzIiwiaWF0IjoxNTUzNTA2Mzg0fQ.hAFAC1Wk6j0yioqAREp0vqV16xuyR7MTpSy3bZJOg2U";
		
		when(service.saveMovie(movie)).thenReturn(true);
		mvc.perform(post("/api/v1/movieservice/movie").header("authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON).content(jsonToString(movie)))
		
				.andExpect(status().isCreated());
		verify(service,times(1)).saveMovie(Mockito.any(Movie.class));
		verifyNoMoreInteractions(service);
		
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void testUpdateMovieSuccess() throws Exception {
		movie.setComments("not so good movie");
		when(service.updateMovie(movie)).thenReturn(movies.get(0));
		mvc.perform(put("/api/v1/movieservice/movie/{id}",1).contentType(MediaType.APPLICATION_JSON).content(jsonToString(movie)))
		.andExpect(status().isOk());
		verify(service,times(1)).updateMovie(Mockito.any(Movie.class));
		verifyNoMoreInteractions(service);
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void testDeleteMovieById() throws Exception{
		when(service.deleteMovieById(1)).thenReturn(true);
		mvc.perform(delete("/api/v1/movieservice/movie/{id}",1)).andExpect(status().isOk());
		verify(service,times(1)).deleteMovieById(1);
		verifyNoMoreInteractions(service);
	}
	/**
	 * @throws Exception
	 */
	@Test
	public void testFetchMovieById() throws Exception{
		when(service.getMovieById(1)).thenReturn(movies.get(0));
		mvc.perform(get("/api/v1/movieservice/movie/{id}",1)).andExpect(status().isOk());
		verify(service,times(1)).getMovieById(1);
		verifyNoMoreInteractions(service);
	}
	/**
	 * @throws Exception
	 */
	@Test
	public void testGetMyMovies() throws Exception{
		
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKYWtlMTIzIiwiaWF0IjoxNTUzNTA2Mzg0fQ.hAFAC1Wk6j0yioqAREp0vqV16xuyR7MTpSy3bZJOg2U";
		
		when(service.getMyMovies("Jake123")).thenReturn(movies);
		mvc.perform(get("/api/v1/movieservice/movies").header("authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		verify(service,times(1)).getMyMovies("Jake123");
		verifyNoMoreInteractions(service);
	}
	
	/**
	 * @param obj
	 * @return
	 * @throws JsonProcessingException
	 */
	private static String jsonToString(final Object obj) throws JsonProcessingException{
		String result;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			result = jsonContent;
		}catch(JsonProcessingException e){
			result = "Json processing error";
		}
		return result;
	}

}
