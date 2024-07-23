package com.movieflix.movies.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieflix.movies.auth.entities.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {

    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}