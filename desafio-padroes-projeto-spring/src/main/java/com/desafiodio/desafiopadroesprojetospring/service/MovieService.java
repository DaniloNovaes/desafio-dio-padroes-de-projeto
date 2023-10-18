package com.desafiodio.desafiopadroesprojetospring.service;

import com.desafiodio.desafiopadroesprojetospring.client.MovieClientOmdbFeign;
import com.desafiodio.desafiopadroesprojetospring.dto.MovieDTO;
import com.desafiodio.desafiopadroesprojetospring.model.Movie;
import com.desafiodio.desafiopadroesprojetospring.repository.MovieRepository;
import com.desafiodio.desafiopadroesprojetospring.transformer.MovieTransformer;
import com.desafiodio.desafiopadroesprojetospring.vo.MovieOmdb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service //Para o Spring entender que é um Serviço. Essa classe vai implementar a interface do client.

public class MovieService {

    @Value("${imdb.apikey}") //Para buscar a chave no application.properties
    private String apiKey;
    @Autowired //Para não precisar do new().
    private MovieClientOmdbFeign movieClientFeign;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieTransformer movieTransformer;

    public MovieOmdb getMovie(String theme) {
        return movieClientFeign.getMovie(theme, apiKey);
    }

    public Movie save(MovieDTO movieDTO) {
        Movie movie = movieTransformer.transformeToMovie(movieDTO);
        return movieRepository.save(movie);
    }

    public Movie getById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Filme invalido"));
    }

}
