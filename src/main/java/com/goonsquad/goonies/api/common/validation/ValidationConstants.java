package com.goonsquad.goonies.api.common.validation;

public final class ValidationConstants {
    private ValidationConstants () {
        // util class not instantiatable
    }

    // Movie
    public static final String REQUIRED_MOVIE_TITLE = "Movie title is required";
    public static final String REQUIRED_MOVIE_GENRE = "At least 1 movie genre is required";
    public static final String REQUIRED_MOVIE_RELEASE_DATE = "Movie release date is required";
    public static final String REQUIRED_MOVIE_ORIGIN_COUNTRY = "Movie origin country is required";
    public static final String REQUIRED_MOVIE_STATUS = "Movie status is required";
    public static final String REQUIRED_MOVIE_RATING_MIN = "Lowest possible movie rating is 1";
    public static final String REQUIRED_MOVIE_RATING_MAX = "Highest possible movie rating is 100";
    public static final String MOVIE_ALREADY_EXISTS = "Movie {0} (release date: {1}) already exists";
    public static final String MOVIE_RATING_WITHOUT_LINK = "Movie can't have rating from a site without site link provided as well";

    // Review
    public static final String REQUIRED_REVIEW_MOVIE_RATING = "Movie rating is required";

    // Jwt
    public static final String TOKEN_SIGNATURE_EXCEPTION = "Unable to sign token";
    public static final String TOKEN_PARSING_EXCEPTION = "Unable to parse token";
    public static final String TOKEN_VERIFICATION_EXCEPTION = "Unable to verify token";
    public static final String TOKEN_NOT_VALID = "Token is invalid";

    // User
    public static final String USER_NOT_ACTIVE_OR_NONEXISTENT = "User is not active or doesn't exist";

    // Auth
    public static final String REQUIRED_USERNAME_OR_EMAIL = "Username or email is required";
    public static final String REQUIRED_PASSWORD = "Password is required";
    public static final String REQUIRED_REFRESH_TOKEN = "Refresh token is required";
    public static final String AUTHENTICATION_FAILED = "User authentication failed";
}
