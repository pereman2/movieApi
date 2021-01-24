package app.movieApi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import app.movieApi.model.Movie;
import app.movieApi.tmdb.TmdbRequest;

@RestController
public class MovieController {

	/** 
	 * Searches for a list of movies with the name provided 
	 * 
	 * @param String movie Movie to find
	 * @param Optional<String> page Optional page request parameter
	 * @return List<Movie> movies 
	 */
	@GetMapping("/movies/{movie}")
	ArrayList<Movie> getMovie(@PathVariable String movie,
			@RequestParam String token,
			@RequestParam Optional<String> page) {
		int searchPage = 1;
		if(page.get().length() > 0) {
			searchPage = Integer.parseInt(page.get());
		}
		return TmdbRequest.findMovies(movie, searchPage);
	}

	@GetMapping("/login}")
	String getMovie(@RequestParam String username,
			@RequestParam String password) {
		return "myToken";
	}

}
