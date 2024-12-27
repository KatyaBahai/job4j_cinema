package ru.job4j.cinema.controller;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@ThreadSafe
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/buy")
    public String getPurchasePage(Model model, @ModelAttribute Ticket ticket,  HttpServletRequest request) {
        Optional<Ticket> ticketOptional = ticketService.find(ticket.getRowNumber(), ticket.getPlaceNumber(), ticket.getSessionId());
        if (ticketOptional.isEmpty()) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            ticket.setUserId(user.getId());
            ticketService.save(ticket);
            model.addAttribute("message", "You have successfully purchased a ticket! Check you email.");
            return "/tickets/purchase";
        }
        model.addAttribute("message", " Sorry, this seat has already been chosen by somebody. Please, try another one.");
        return "/tickets/purchase";
    }

}
