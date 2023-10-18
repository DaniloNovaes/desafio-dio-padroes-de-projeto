package com.desafiodio.desafiopadroesprojetospring.controller;

import com.desafiodio.desafiopadroesprojetospring.dto.MovieDTO;
import com.desafiodio.desafiopadroesprojetospring.service.MovieService;
import com.desafiodio.desafiopadroesprojetospring.transformer.MovieTransformer;
import com.desafiodio.desafiopadroesprojetospring.vo.MovieOmdb;
import com.desafiodio.desafiopadroesprojetospring.vo.MovieVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController //Para definir como controller.
@RequestMapping("/movie")
public class MovieController {

    @Autowired //Injeção de dependência (Para não usar o new().
    private MovieService movieService;
    @Autowired
    private MovieTransformer movieTransformer;

    @GetMapping("/omdb/{theme}")
    public ResponseEntity<MovieOmdb> getMovie(@PathVariable String theme){
        try {
            MovieOmdb movieOmdb = movieService.getMovie(theme);
            return ResponseEntity.status(HttpStatus.OK).body(movieOmdb);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<MovieVO> saveMovie(@RequestBody MovieDTO movieDTO){
        try {
            MovieVO movieVO = movieTransformer.transformeToMovieVO(movieService.save(movieDTO));

            addHateoas(movieVO);

            return ResponseEntity.status(HttpStatus.CREATED).body(movieVO);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieVO> getById(@PathVariable("id") Long id) {
        try {
            MovieVO movieVO = movieTransformer.transformeToMovieVO(movieService.getById(id));
            return ResponseEntity.ok(movieVO);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    private void addHateoas (MovieVO movieVO){
        movieVO.add(linkTo(methodOn(MovieController.class).getById(movieVO.getId())).withSelfRel());
    }
}
