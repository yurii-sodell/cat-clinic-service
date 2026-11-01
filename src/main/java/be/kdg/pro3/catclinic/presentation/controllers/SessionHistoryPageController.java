package be.kdg.pro3.catclinic.presentation.controllers;

import be.kdg.pro3.catclinic.domain.SessionHistory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/history")
public class SessionHistoryPageController{
    private SessionHistory history;

    public SessionHistoryPageController(SessionHistory history){
        this.history = history;
    }

    @GetMapping
    public String getHistoryForCurrentSession(Model model){
        model.addAttribute("sessionHistory", history.getHistory());
        return "session-history";
    }
}
