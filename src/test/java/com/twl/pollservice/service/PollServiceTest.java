package com.twl.pollservice.service;

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

        when(service.findAll()).thenReturn(Collections.singletonList(mockPoll()));
        assertFalse(service.findAll().isEmpty());
    }

    @Test
    void shouldFailFindAll() {

        when(service.findAll()).thenReturn(new ArrayList<>());
        assertTrue(service.findAll().isEmpty());
    }

    //FIXME consertar testes abaixo
//    @Test
//    void shouldSuccessFindOne() throws Exception {
//
//        when(service.findOne("1")).thenReturn(mockPoll());
//
//        service.save(mockPoll());
//
//        Poll poll = service.findOne("1");
//
//        assertEquals(poll, mockPoll());
//    }
//
//    @Test
//    void shouldFailFindOne() throws Exception {
//
//        when(service.findOne("2")).thenThrow(NotFoundException.class);
//        assertThrows(NotFoundException.class, () -> service.findOne("2"));
//    }

    private Poll mockPoll() {
        return new Poll("1", "Test", "TestPoll");
    }
}