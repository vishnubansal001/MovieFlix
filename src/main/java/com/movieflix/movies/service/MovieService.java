package com.movieflix.movies.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.movieflix.movies.dto.MovieDto;

public interface MovieService {

    MovieDto addMovie(MovieDto movieDto, MultipartFile file) throws IOException;

    MovieDto getMovie(Integer movieId);

    List<MovieDto> getAllMovies();

    MovieDto updateMovie(Integer movieId, MovieDto movieDto, MultipartFile file) throws IOException;

    String deleteMovie(Integer movieId) throws IOException;
}
