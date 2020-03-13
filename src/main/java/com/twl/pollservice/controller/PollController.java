package com.twl.pollservice.controller;

import com.twl.pollservice.exception.NotFoundException;
import com.twl.pollservice.model.entity.Poll;
import com.twl.pollservice.model.entity.PollSession;
import com.twl.pollservice.service.PollService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/poll")
public class PollController {

    private final PollService service;

    public PollController(PollService service) {
        this.service = service;
    }

    @PostMapping
    private Poll save(@RequestBody Poll poll) {
        return service.save(poll);
    }

    @GetMapping
    private List<Poll> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    private Poll getById(@PathVariable String id) throws Exception {
        return service
                .findOne(id)
                .orElseThrow(() -> new NotFoundException("Poll not found."));
    }

    @PatchMapping("/{id}")
    private Poll openPollSession(@PathVariable String id, @RequestBody PollSession session) throws Exception {
        return service.openSession(id, session);
    }
}
