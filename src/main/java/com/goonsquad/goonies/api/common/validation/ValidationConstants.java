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
    public static final String JWT_SIGNATURE_EXCEPTION = "Unable to sign token";
    public static final String JWT_PARSING_EXCEPTION = "Unable to parse token";
    public static final String JWT_VERIFICATION_EXCEPTION = "Unable to verify token";
}
