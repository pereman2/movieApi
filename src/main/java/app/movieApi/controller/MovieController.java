package app.movieApi.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import app.movieApi.model.Movie;
import app.movieApi.tmdb.TmdbRequest;

@RestController
public class MovieController {


	@Autowired
	private TmdbRequest tmbdRequest;

	/** 
	 * Searches for a list of movies with the name provided 
	 * 
	 * @param String movie Movie to find
	 * @param Optional<String> page Optional page request parameter
	 * @return List<Movie> movies 
	 */
	@GetMapping("/movies/{movie}")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public ArrayList<Movie> getMovie(@PathVariable String movie,
			@RequestParam Optional<String> page) {
		int searchPage = 1;
		if(page.isPresent()) {
			searchPage = Integer.parseInt(page.get());
		}
		
		return tmbdRequest.findMovies(movie, searchPage);
	}

}
