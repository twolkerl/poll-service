package com.twl.pollservice.service;

import com.twl.pollservice.exception.NotFoundException;
import com.twl.pollservice.model.entity.Poll;
import com.twl.pollservice.repository.PollRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
class PollServiceTest {

    @Mock
    private PollRepository repository;

    @InjectMocks
    private PollService service;

    @BeforeEach
    void setUp() {
        initMocks(this);
        service = new PollService(repository);
    }

    @Test
    void shouldSuccessSave() {

        Poll poll = new Poll("abc", "test", "test");
        when(service.save(poll)).thenReturn(poll);

        Poll savedPoll = service.save(poll);
        assertEquals(savedPoll.getId(), poll.getId());
    }

    @Test
    void shouldFailSave() {

        when(service.save(new Poll())).thenThrow(ConstraintViolationException.class);
        assertThrows(ConstraintViolationException.class, () -> service.save(new Poll()));
    }

    @Test
    void shouldSuccessFindAll() {

        when(repository.findAll()).thenReturn(Collections.singletonList(mockPoll()));
        assertFalse(service.findAll().isEmpty());
    }

    @Test
    void shouldFailFindAll() {

        when(repository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(service.findAll().isEmpty());
    }

    @Test
    void shouldSuccessFindOne() throws Exception {

        when(repository.findById("1")).thenReturn(Optional.of(mockPoll()));
        assertEquals(service.findOne("1"), mockPoll());
    }

    @Test
    void shouldFailFindOne() throws Exception {

        when(repository.findById("2")).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.findOne("2"));
    }

    private Poll mockPoll() {
        return new Poll("1", "Test", "TestPoll");
    }
}