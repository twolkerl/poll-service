package com.twl.pollservice.controller;

import com.twl.pollservice.model.dto.PollResultResponse;
import com.twl.pollservice.model.entity.PollSession;
import com.twl.pollservice.service.PollSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/poll-session")
public class PollSessionController {

    private final PollSessionService service;

    public PollSessionController(PollSessionService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PollSession save(@RequestBody @Valid PollSession pollSession) throws Exception {
        return service.save(pollSession);
    }

    @GetMapping("/poll/{pollId}")
    public PollSession getByPollId(@PathVariable String pollId) throws Exception {
        return service.findByPollId(pollId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) throws Exception {
        service.delete(id);
    }

    @GetMapping("/results/{id}")
    public PollResultResponse getSessionResult(@PathVariable String id) throws Exception {
        return service.getSessionResult(id);
    }
}
