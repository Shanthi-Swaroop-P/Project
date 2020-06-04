package com.main.api;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.main.entity.Movie;
import com.main.entity.Rating;
import com.main.repository.MovieRepository;
import com.main.repository.RatingRepository;

@RestController
public class RatingController {
	
	@Autowired
	private  MovieRepository movieRepo;
	@Autowired
	private  RatingRepository ratingRepo;
	
	@GetMapping("api/rest/{customerid}/rate/{rating}")
	public String getRatings(@PathVariable("customerid") int customerId,
			@PathVariable("rating") Integer rating,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("movieName") String movieName)
	{

		// Find Details of Rating using ID and update values
		
		Optional<Rating> oldrate=ratingRepo.findById(customerId);
		Rating rate=new Rating();
		rate.setRating(rating);
		rate.setFirstName(firstName);
		rate.setLastName(lastName);
		//calculate and set average rating of the customer and update in repository
		rate.setAvgRating((oldrate.get().getAvgRating()+rating)/2);
		ratingRepo.delete(oldrate.get());
		ratingRepo.saveAndFlush(rate);
		
			
		//Also update in the Movie with rating
		
		Optional<Movie> oldmovie=movieRepo.findById(movieName);
		Movie movie=new Movie();
		double newAverage=(oldmovie.get().getRating()+rating)/2;
		movieRepo.delete(oldmovie.get());
		movie.setMovieName(movieName);
		movie.setRating(newAverage);
		movieRepo.saveAndFlush(movie);
		return "Movie Rating added successfully";
	}

	@GetMapping("/api/highest_rated_movie")
	public Movie getHighestRatedMovie()
	{	
		//Find All the movies first
		List<Movie> movielist=movieRepo.findAll();
		
		// Sorting using Sort function to get Highest Value
		movielist.sort((o1, o2) -> Double.compare(o2.getRating(), o1.getRating()));
		return movielist.get(0);
	}
	
	
	@GetMapping("/api/highest_rated_customer")
	public Rating getRatedCustomer()
	{	
		////find all the rating first
		List<Rating> ratelist=ratingRepo.findAll();
		
		// Sorting using Sort function to get Highest Value
		ratelist.sort((o1, o2) -> Double.compare(o2.getAvgRating(), o1.getAvgRating()));
		return ratelist.get(0);
	
	}
}