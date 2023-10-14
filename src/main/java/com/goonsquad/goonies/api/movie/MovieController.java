package com.goonsquad.goonies.api.movie;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Goonies API")
public class MovieController {

    @GetMapping("/movies")
    @ResponseStatus(HttpStatus.OK)
    public String hello() {
        return "Movies";
    }

}
