package com.shubhendu.moviecruiserserverapplication.domain;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="movie")
public class Movie {
	
	
	/** 
	 * id for movie 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="movie_id")
	private String movieId;
	/** 
	 * name of the movie 
	 */
	@Column(name="title")
	private String title;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name="user_id")
	private String userId;
	
	
	/**
	 * 
	 */
	@Column(name="comments")
	private String comments;
	
	/**
	 * 
	 */
	@Column(name="poster_path")
	private String poster_path;
	
	/**
	 * 
	 */
	@Column(name="release_date")
	private String release_date;
	
	/**
	 * 
	 */
	@Column(name="vote_average")
	private double vote_average;
	
	/**
	 * 
	 */
	@Column(name="vote_count")
	private int vote_count;
	public Movie() {
		super();
	}
	
	
	public Movie(int id, String movieId, String title, String userId, String comments, String poster_path,
			String release_date, double vote_average, int vote_count) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.title = title;
		this.userId = userId;
		this.comments = comments;
		this.poster_path = poster_path;
		this.release_date = release_date;
		this.vote_average = vote_average;
		this.vote_count = vote_count;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getPoster_path() {
		return poster_path;
	}
	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}
	public String getRelease_date() {
		return release_date;
	}
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}
	public double getVote_average() {
		return vote_average;
	}
	public void setVote_average(double vote_average) {
		this.vote_average = vote_average;
	}
	public int getVote_count() {
		return vote_count;
	}
	public void setVote_count(int vote_count) {
		this.vote_count = vote_count;
	}




	
	
	
	
}
