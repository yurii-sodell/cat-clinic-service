package be.kdg.pro3.catclinic.presentation.controllers;


import be.kdg.pro3.catclinic.service.CatOwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners")
public class CatOwnerController{
    private CatOwnerService service;

    public CatOwnerController(CatOwnerService service){
        this.service = service;
    }

    @GetMapping
    public String showOwners(Model model){
        model.addAttribute("allOwners", service.getAllOwners());
        return "owners";
    }
}
