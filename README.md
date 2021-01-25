# movieApi

- java
- spring
- mvc
- jwst
- jmdb

## Setup and execution

1. Maven clean, maven install
2. `java -jar -Dmovie.api.key={insert_your_api_key_here} app.jar`.
Alternatively, run app.jar.

**test**

`http://localhost:8080/auth?user=randomname&password=test123` 

`http://localhost:8080/movies/shrek?page=1`(with authorization)

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

http://localhost:8080/auth?user=perediaz&password=hiremepls

### Search movies

**url get**

http://localhost:8080/movies/{movie}

**url params**

required:

HEADER:

`Authorization: {token_given_in_auth}`

The token will be the one provided with auth

optional:

`page=[Integer]`

**success response** 

```
Code: 200
Content: [{id: 234, name: "moviename", release_date: "2019", overview: "somebody once told me", popularity: 9.4, voteAverage: 10.0, votes: 13496}, ...]
```

**examples** 

http://localhost:8080/movies/shrek?&page=1

http://localhost:8080/movies/shrek?&page=2


