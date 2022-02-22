package br.com.letscode.moviesbattle.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String home() {
        return "hello world";
    }

//    @GetMapping("/movies/{imdb}")
//    public MovieDTO imdb(@PathVariable String imdb) {
//        MovieDTO dto = new MovieDTO();
//        String uri = "http://www.omdbapi.com/?apikey=a26fcbc5&i=tt" + imdb;
//        RestTemplate template = new RestTemplate();
//        return template.getForObject(uri, MovieDTO.class);
//    }

}
