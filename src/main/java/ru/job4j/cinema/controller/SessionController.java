package ru.job4j.cinema.controller;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.service.SessionService;

@ThreadSafe
@Controller
@RequestMapping("/sessions")
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService basicSessionService) {
        this.sessionService = basicSessionService;
    }

    @GetMapping("/list")
    public String getAll(Model model) {
        model.addAttribute("sessionDtos", sessionService.getAllSessions());
        return "/sessions/list";
    }

    @GetMapping("/{sessionId}")
    public String getById(Model model, @PathVariable int sessionId) {
        var sessionOptional = sessionService.getSessionById(sessionId);
        if (sessionOptional.isEmpty()) {
            model.addAttribute("message", "There's no such film session, sorry!");
            return "/errors/404";
        }
        model.addAttribute("sessionDto", sessionOptional.get());
        return "/tickets/buy";
    }
}
