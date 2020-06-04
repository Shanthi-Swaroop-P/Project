package com.main.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Rating")
public class Rating {
	@Id
	@GeneratedValue
	private Integer id;
	private String firstName;
	private String lastName;
	private Integer rating;
	private Double avgRating;
	
	public Double getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(Double avgRating) {
		this.avgRating = avgRating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getRating() {
		return rating;
	}
	

}
