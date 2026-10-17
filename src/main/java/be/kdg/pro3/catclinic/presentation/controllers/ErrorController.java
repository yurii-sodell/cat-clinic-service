package be.kdg.pro3.catclinic.presentation.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/error")
public class ErrorController{
    @GetMapping
    public String showError(){
        return "error";
    }
}
