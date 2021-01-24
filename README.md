# movieApi

- java
- spring
- mvc
- jwst
- jmdb

## setup

Firstly it's mandatory exporting the following environment variable:

`export MOVIE_API_KEY="{insert_provided_api_key}"`

Moving on, now you can maven clean and maven install and `java -jar` the jar encountered in target


## endpoints 

### Authentication

**url post**

http://localhost:8080/auth

**url params**

required:

`user=[String]`

`password=[String]`

**success response** 

```
Code: 200
Content: {user: "username", pwd: null, token}
```

**examples** 

localhost:8080/auth?user=perediaz&password=hiremepls

### Search movies

**url get**

http://localhost:8080/movies/{movie}

**url params**

required:

`token=[String]`

optional:

`page=[Integer]`

**success response** 

```
Code: 200
Content: [{id: 234, name: "moviename", release_date: "2019", overview: "somebody once told me", popularity: 9.4, voteAverage: 10.0, votes: 13496}, ...]
```

**examples** 

localhost:8080/movies/shrek?token=x&page=1


