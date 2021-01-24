package app.movieApi.tmdb; 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;

import app.movieApi.model.Movie;

public class TmdbRequest {

	private static String MOVIE_SEARCH_URL = "https://api.themoviedb.org/3/search/movie?api_key=%s&language=en-US&query=%s&page=%s&include_adult=false";

	private static String getApiKey() {
		//return System.getenv("MOVIE_API_KEY");
		return "16549def09deeb51b0336adff5d70307";
	}

	@Cacheable(value="movies")
	public static ArrayList<Movie> findMovies(String movieName, int page) {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		JSONObject requestResponse = TmdbRequest.searchMovie(movieName, page);
		if(requestResponse == null) { return movies; }
		JSONArray results = requestResponse.getJSONArray("results");
		for(int i = 0; i < results.length(); i++) {
			JSONObject tmdbMovie = results.getJSONObject(i);
			int id = tmdbMovie.getInt("id");
			String name = tmdbMovie.getString("original_title");
			Movie movie = new Movie(i, name);
			System.out.println(id + " " + name);
			movies.add(movie);
		}
		return movies;
	}

	private static JSONObject searchMovie(String movie, int page) {
		String query_url = String.format(MOVIE_SEARCH_URL, getApiKey(), movie, page);
		InputStream is;
		JSONObject json = null;
		try {
			URL url = new URL(query_url);
			is = url.openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			json = new JSONObject(jsonText);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		String movie = "wolve";
		findMovies(movie, 1);
	}

}
