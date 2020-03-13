package com.twl.pollservice.controller;

import com.twl.pollservice.model.entity.PollSession;
import com.twl.pollservice.service.PollSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/poll-session")
public class PollSessionController {

    private final PollSessionService service;

    public PollSessionController(PollSessionService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PollSession save(@RequestBody PollSession pollSession) {
        return service.save(pollSession);
    }
}
