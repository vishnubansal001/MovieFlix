package com.movieflix.movies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieflix.movies.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}