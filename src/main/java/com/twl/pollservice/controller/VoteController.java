package com.twl.pollservice.controller;

import com.twl.pollservice.model.entity.Vote;
import com.twl.pollservice.service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/vote")
public class VoteController {

    private final VoteService service;

    public VoteController(VoteService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vote save(@RequestBody @Valid Vote vote) throws Exception {
        return service.save(vote);
    }
}
