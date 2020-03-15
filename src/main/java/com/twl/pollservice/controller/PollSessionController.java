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
    public PollSession save(@RequestBody PollSession pollSession) throws Exception {
        return service.save(pollSession);
    }

    @GetMapping("/poll/{pollId}")
    public PollSession getByPollId(@PathVariable String pollId) throws Exception {
        return service.findByPollId(pollId);
    }
}
