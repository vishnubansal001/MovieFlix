package com.movieflix.movies.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.movieflix.movies.dto.MovieDto;
import com.movieflix.movies.entities.Movie;
import com.movieflix.movies.repositories.MovieRepository;

@Service
public class MovieServiceEmpl implements MovieService {

    private final MovieRepository movieRepository;
    private final FileService fileService;

    public MovieServiceEmpl(MovieRepository movieRepository, FileService fileService) {
        this.movieRepository = movieRepository;
        this.fileService = fileService;
    }

    @Value("${project.poster}")
    private String path;

    @Value("${base.url}")
    private String baseUrl;

    @Override
public MovieDto addMovie(MovieDto movieDto, MultipartFile file) throws IOException {
        String uploadedFileName = fileService.uploadFile(path, file);

        movieDto.setPoster(uploadedFileName);

        Movie movie = new Movie(
                null,
                movieDto.getTitle(),
                movieDto.getDirector(),
                movieDto.getStudio(),
                movieDto.getMovieCast(),
                movieDto.getRelase(),
                movieDto.getPoster()
        );

        Movie savedMovie = movieRepository.save(movie);

        String posterUrl = baseUrl + "/file/" + uploadedFileName;

        MovieDto response = new MovieDto(
                savedMovie.getMovieId(),
                savedMovie.getTitle(),
                savedMovie.getDirector(),
                savedMovie.getStudio(),
                savedMovie.getMovieCast(),
                savedMovie.getRelase(),
                savedMovie.getPoster(),
                posterUrl
        );

        return response;
    }

    @Override
    public MovieDto getMovie(Integer movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found with id = " + movieId));

        String posterUrl = baseUrl + "/file/" + movie.getPoster();

        MovieDto response = new MovieDto(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getStudio(),
                movie.getMovieCast(),
                movie.getRelase(),
                movie.getPoster(),
                posterUrl
        );

        return response;
    }

    @Override
    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();

        List<MovieDto> movieDtos = new ArrayList<>();

        for(Movie movie : movies) {
            String posterUrl = baseUrl + "/file/" + movie.getPoster();
            MovieDto movieDto = new MovieDto(
                    movie.getMovieId(),
                    movie.getTitle(),
                    movie.getDirector(),
                    movie.getStudio(),
                    movie.getMovieCast(),
                    movie.getRelase(),
                    movie.getPoster(),
                    posterUrl
            );
            movieDtos.add(movieDto);
        }

        return movieDtos;
    }
    
}
