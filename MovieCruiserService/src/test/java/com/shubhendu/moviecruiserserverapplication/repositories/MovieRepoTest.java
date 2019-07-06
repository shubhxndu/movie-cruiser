package com.shubhendu.moviecruiserserverapplication.repositories;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shubhendu.moviecruiserserverapplication.domain.Movie;
import com.shubhendu.moviecruiserserverapplication.repository.MovieRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class MovieRepoTest {
	
	/**
	 * 
	 */
	@Autowired
	private transient MovieRepository repository;
	
	/**
	 * @param repository
	 */
	public void setRepo(final MovieRepository repository){
		this.repository = repository;
	}
	/**
	 * 
	 */
	public MovieRepoTest() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void testSaveMovie() throws Exception{
		Movie savedMovie = repository.save(new Movie(1,"1234","superman","Jake123","Good Movie","www.abc.com","2015-03-23",45.5,112));
		final Movie movie = repository.getOne(savedMovie.getId());
		assertThat(movie.getId()).isEqualTo(savedMovie.getId());
	
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void testUpdateMovie() throws Exception{
		Movie savedMovie=repository.save(new Movie(1,"1234","superman","Jake123","Good Movie","www.abc.com","2015-03-23",45.5,112));
		final Movie movie=repository.getOne(savedMovie.getId());
		assertEquals(movie.getTitle(),"superman");
		movie.setComments("hi");
		repository.save(movie);
		final Optional<Movie> tempMovie=repository.findById(savedMovie.getId());
		assertEquals("hi",tempMovie.get().getComments());
		
	}
	
	@Test
	public void testDeleteMovie() throws Exception{
		Movie savedMovie=repository.save(new Movie(1,"1234","superman","Jake123","Good Movie","www.abc.com","2015-03-23",45.5,112));
		final Movie movie=repository.getOne(savedMovie.getId());
		assertEquals(movie.getTitle(),"superman");
		
		repository.delete(movie);
		
		assertEquals(Optional.empty(),repository.findById(savedMovie.getId()));
		
	}
	
	@Test
	public void testGetMovie() throws Exception{
		Movie savedMovie=repository.save(new Movie(1,"1234","superman","Jake123","Good Movie","www.abc.com","2015-03-23",45.5,112));
		final Optional<Movie> movie=repository.findById(savedMovie.getId());
		final Optional<Movie> option=repository.findById(savedMovie.getId());
		Movie fetchedMovie=option.get();
		assertEquals(movie.get().getTitle(),fetchedMovie.getTitle());
		
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testGetMyMovies() throws Exception{
		repository.save(new Movie(1,"1234","superman","Jake123","Good Movie","www.abc.com","2015-03-23",45.5,112));
		repository.save(new Movie(2,"12341","superman2","Jake123","Good Movie","www.abc.com","2015-03-23",45.5,112));
		
		final List<Movie> movies = repository.findByUserId("Jake123");
		assertEquals(movies.get(0).getTitle(),"superman");
		
	
	
	}
//	@After
//    public void tearDown() {
//        repository.deleteAllInBatch();;
//    }
	

}
