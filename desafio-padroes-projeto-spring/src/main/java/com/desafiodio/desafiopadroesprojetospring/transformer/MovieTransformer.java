package com.desafiodio.desafiopadroesprojetospring.transformer;

import com.desafiodio.desafiopadroesprojetospring.dto.MovieDTO;
import com.desafiodio.desafiopadroesprojetospring.model.Movie;
import com.desafiodio.desafiopadroesprojetospring.vo.MovieVO;
import org.springframework.stereotype.Component;

@Component
public class MovieTransformer {

    public Movie transformeToMovie(MovieDTO movieDTO){
        Movie movie = new Movie();
        movie.setTitle(movieDTO.getTitle());
        movie.setYear(movieDTO.getYear());
        return movie;

    }

    public MovieVO transformeToMovieVO(Movie movie) {
        MovieVO movieVO = new MovieVO();
        movieVO.setTitle(movie.getTitle());
        movieVO.setYear(movie.getYear());;
        return movieVO;
    }
}
