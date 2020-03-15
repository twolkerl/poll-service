package com.twl.pollservice.service;

import com.twl.pollservice.exception.BusinessException;
import com.twl.pollservice.model.entity.Poll;
import com.twl.pollservice.model.entity.PollSession;
import com.twl.pollservice.model.enums.SessionStatus;
import com.twl.pollservice.repository.PollSessionRepository;
import com.twl.pollservice.service.validator.PollSessionValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
class PollSessionServiceTest {

    @Mock
    private PollSessionRepository repository;

    @Mock
    private PollSessionValidator validator;

    @InjectMocks
    private PollSessionService service;

    @BeforeEach
    void setUp() {
        initMocks(this);
        service = new PollSessionService(repository, validator);
    }

    @Test
    void shouldSuccessSave() throws Exception {

        PollSession pollSession = mockSession();
        when(service.save(pollSession)).thenReturn(mockSession());

        PollSession saved = service.save(pollSession);
        assertEquals(saved.getId(), pollSession.getId());
    }

    @Test
    void shouldFailSaveWhenDateRangeIsNotValid() throws Exception {

        PollSession pollSession = mockSession();
        LocalDateTime invalidDate = pollSession.getSessionStart().minusDays(1);
        pollSession.setSessionEnd(invalidDate);
        when(validator.validateDateRange(pollSession)).thenThrow(BusinessException.class);

        assertThrows(BusinessException.class, () -> service.save(pollSession));
    }

    @Test
    void shouldFailSaveWhenSessionAlreadyExists() throws Exception{

        PollSession pollSession = mockSession();
        when(validator.validateSessionExists(pollSession)).thenThrow(BusinessException.class);

        assertThrows(BusinessException.class, () -> service.save(pollSession));
    }

    @Test
    void shouldSuccessFindByPollId() throws Exception {

        when(repository.findByPollId("1")).thenReturn(Optional.of(mockSession()));

        assertEquals(service.findByPollId("1").getId(), mockSession().getId());
    }

    @Test
    void shouldSuccessDelete() throws Exception {

        PollSession pollSession = mockSession();
        when(repository.findById("1")).thenReturn(Optional.of(pollSession));

        service.delete("1");

        verify(repository, times(1)).delete(pollSession);
    }


    private Poll mockPoll() {
        return new Poll("123", "Test", "TestPoll");
    }

    private PollSession mockSession() {
        return new PollSession("1",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1),
                SessionStatus.OPEN,
                Boolean.TRUE,
                mockPoll());
    }
}