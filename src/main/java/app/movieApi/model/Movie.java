package app.movieApi.model;

public class Movie {

	private int id;
	private String name;
	private float popularity;
	private float voteAverage;
	private int votes;
	private String overview;
	private String releaseDate;

	public Movie(int id, String name, float popularity, float voteAverage, int votes, String overview, String releaseDate) {
		this.id = id;
		this.name = name;
		this.popularity = popularity;
		this.voteAverage = voteAverage;
		this.votes = votes;
		this.overview = overview;
		this.releaseDate = releaseDate;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public float getPopularity() {
		return popularity;
	}

	public float getVoteAverage() {
		return voteAverage;
	}

	public int getVotes() {
		return votes;
	}

	public String getOverview() {
		return overview;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

}
