package app.movieApi.tmdb; 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import app.movieApi.model.Movie;

@Service
public class TmdbRequest {

	private static String MOVIE_SEARCH_URL = "https://api.themoviedb.org/3/search/movie?api_key=%s&language=en-US&query=%s&page=%s&include_adult=false";

	@Value("${movie.api.key}")
	private String apiKey;

	@Cacheable(value="movies")
	public ArrayList<Movie> findMovies(String movieName, int page) {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		JSONObject requestResponse = searchMovie(movieName, page);
		if(requestResponse == null) { return movies; }
		JSONArray results = requestResponse.getJSONArray("results");
		for(int i = 0; i < results.length(); i++) {
			JSONObject tmdbMovie = results.getJSONObject(i);
			Movie movie = extractMovie(tmdbMovie);
			movies.add(movie);
		}
		return movies;
	}

	private Movie extractMovie(JSONObject tmdbMovie) throws JSONException {
		int id = getId(tmdbMovie);
		String name = getTitle(tmdbMovie);
        float popularity = getPopularity(tmdbMovie);
        float voteAverage = getVoteAverage(tmdbMovie);
		int votes = getVotes(tmdbMovie);
		String overview = getOverview(tmdbMovie);
		String releaseDate = getReleaseDate(tmdbMovie);
		Movie movie = new Movie(id, name, popularity, voteAverage, votes, overview, releaseDate);
		return movie;
	}

	private int getId(JSONObject tmdbMovie) throws JSONException {
		int id = -1;
		if(tmdbMovie.has("id")) {
			id = tmdbMovie.getInt("id");
		}
		return id;
	}

	private String getTitle(JSONObject tmdbMovie) throws JSONException {
		String name = "";
        if(tmdbMovie.has("title")) {
            name = tmdbMovie.getString("title");
        }
		return name;
	}

	private float getPopularity(JSONObject tmdbMovie) throws JSONException {
		float popularity = 0;
        if(tmdbMovie.has("popularity")) {
            popularity = tmdbMovie.getFloat("popularity");
        }
		return popularity;
	}

	private float getVoteAverage(JSONObject tmdbMovie) throws JSONException {
		float voteAverage = 0;
        if(tmdbMovie.has("popularity")) {
        	voteAverage = tmdbMovie.getFloat("vote_average");
        }
		return voteAverage;
	}

	private int getVotes(JSONObject tmdbMovie) throws JSONException {
		int votes = -1;
		if(tmdbMovie.has("votes")) {
			votes = tmdbMovie.getInt("votes");
		}
		return votes;
	}

	private String getReleaseDate(JSONObject tmdbMovie) throws JSONException {
		String releaseDate = "";
		if(tmdbMovie.has("release_date")) {
			releaseDate = tmdbMovie.getString("release_date");
		}
		return releaseDate;
	}

	private String getOverview(JSONObject tmdbMovie) throws JSONException {
		String overview = "";
		if(tmdbMovie.has("overview")) {
			overview = tmdbMovie.getString("overview");
		}
		return overview;
	}

	private JSONObject searchMovie(String movie, int page) {
		String query_url = String.format(MOVIE_SEARCH_URL, apiKey, movie, page);
		InputStream is;
		JSONObject json = null;
		try {
			URL url = new URL(query_url);
			is = url.openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			json = new JSONObject(jsonText);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

}
