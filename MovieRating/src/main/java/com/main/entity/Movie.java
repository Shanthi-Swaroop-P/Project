package com.main.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Movie")
public class Movie {
	@Id
	private String movieName;
	private Double Rating;
	public Double getRating() {
		return Rating;
	}
	public void setRating(Double rating) {
		Rating = rating;
	}
	
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	
}
