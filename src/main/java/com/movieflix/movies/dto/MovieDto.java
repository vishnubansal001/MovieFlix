package com.movieflix.movies.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private Integer movieId;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "director is required")
    private String director;

    @NotBlank(message = "studio is required")
    private String studio;

    private Set<String> movieCast;

    private Integer relase;

    @NotBlank(message = "poster is required")
    private String poster;

    @NotBlank(message = "posterUrl is required")
    private String posterUrl;
}
