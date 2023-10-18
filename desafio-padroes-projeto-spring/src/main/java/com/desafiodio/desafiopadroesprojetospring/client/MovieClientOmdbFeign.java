package com.desafiodio.desafiopadroesprojetospring.client;

import com.desafiodio.desafiopadroesprojetospring.vo.MovieOmdb;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="omdbMovies", url = "https://www.omdbapi.com/")
public interface MovieClientOmdbFeign {

    @RequestMapping(method = RequestMethod.GET)
    MovieOmdb getMovie(@RequestParam("t")String theme, @RequestParam("apiKey") String key);
}
