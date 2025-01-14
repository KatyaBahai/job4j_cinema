package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import ru.job4j.cinema.dto.SessionDto;

import ru.job4j.cinema.service.SessionService;

import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SessionControllerTest {
    private SessionController sessionController;
    private SessionService sessionService;

    @BeforeEach
    public void initServices() {
        sessionService = mock(SessionService.class);
        sessionController = new SessionController(sessionService);
    }

    @Test
    public void whenGetAllSessionsThenSessionsList() {
        SessionDto sessionDto1 = new SessionDto(
                0, "Finding Nemo", "big", now(), 120, 500, 2, 2);
        SessionDto sessionDto2 = new SessionDto(
                0, "Shrek", "small", now(), 120, 500, 2, 2);
        List<SessionDto> expectedSessionDtos = List.of(sessionDto1, sessionDto2);
        when(sessionService.getAllSessions()).thenReturn(expectedSessionDtos);

        Model model = new ConcurrentModel();
        String view = sessionController.getAll(model);
        var actualSessionDtos = model.getAttribute("sessionDtos");

        assertThat(view).isEqualTo("/sessions/list");
        assertThat(actualSessionDtos).isEqualTo(expectedSessionDtos);
    }

    @Test
    public void whenOpenOneSessionByIdThenGetThisSession() {
        int id = 2;
        ArgumentCaptor<Integer> idCaptor = ArgumentCaptor.forClass(Integer.class);
        SessionDto sessionDto = new SessionDto(
                0, "Finding Nemo", "big", now(), 120, 500, 2, 2);
        when(sessionService.getSessionById(idCaptor.capture())).thenReturn(Optional.of(sessionDto));

        var model = new ConcurrentModel();
        var view = sessionController.getById(model, id);
        var actualSessionDto = model.getAttribute("sessionDto");

        assertThat(view).isEqualTo("/tickets/buy");
        assertThat(actualSessionDto).isEqualTo(sessionDto);
    }

    @Test
    public void whenNoSessionFoundThenErrorMessage() {
        int id = 2;
        ArgumentCaptor<Integer> idCaptor = ArgumentCaptor.forClass(Integer.class);
        when(sessionService.getSessionById(idCaptor.capture())).thenReturn(Optional.empty());

        Model model = new ConcurrentModel();
        String view = sessionController.getById(model, id);
        var actualMessage = model.getAttribute("message");
        String expectedMessage = "There's no such film session, sorry!";

        assertThat(view).isEqualTo("/errors/404");
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

}