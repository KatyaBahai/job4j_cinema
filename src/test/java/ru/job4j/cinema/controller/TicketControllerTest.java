package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.TicketService;
import ru.job4j.cinema.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TicketControllerTest {
    private TicketController ticketController;
    private TicketService ticketService;

    @Test
    @BeforeEach
    public void initServices() {
        ticketService = mock(TicketService.class);
        ticketController = new TicketController(ticketService);
    }

    @Test
    public void whenBuyingNewTicketThenSuccessfulMessage() {
        Ticket ticket = new Ticket(0, 1, 1, 1, 0);
        var ticketCaptor = ArgumentCaptor.forClass(Ticket.class);
        var user = new User(0, "test", "test@gmail.com", "123");
        var mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("wowUser", user);
        when(ticketService.save(ticketCaptor.capture())).thenReturn(Optional.of(ticket));
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(mockHttpSession);

        Model model = new ConcurrentModel();
        String view = ticketController.getPurchasePage(model, ticket, request);

        String expectedMessage = "You have successfully purchased a ticket! Check you email.";
        Ticket expectedTicket = ticketCaptor.getValue();

        assertThat(view).isEqualTo("/tickets/purchase");
        assertThat(ticket).isEqualTo(expectedTicket);
        assertThat(model.getAttribute("message")).isEqualTo(expectedMessage);
    }

    @Test
    public void whenBuyingExistingTicketThenUnsuccessfulMessage() {
        Ticket ticket = new Ticket(0, 1, 1, 1, 0);
        var ticketCaptor = ArgumentCaptor.forClass(Ticket.class);
        var user = new User(0, "test", "test@gmail.com", "123");
        var mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("wowUser", user);
        when(ticketService.save(ticketCaptor.capture())).thenReturn(Optional.empty());
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(mockHttpSession);

        Model model = new ConcurrentModel();
        String view = ticketController.getPurchasePage(model, ticket, request);

        String expectedMessage = "Sorry, this seat has already been chosen by somebody. Please, try another one.";

        assertThat(view).isEqualTo("/tickets/purchase");
        assertThat(model.getAttribute("message")).isEqualTo(expectedMessage);
    }
}