package be.kdg.pro3.catclinic.presentation.controllers;


import be.kdg.pro3.catclinic.service.CatOwnerService;
import be.kdg.pro3.catclinic.web.CatOwnerRegistrationForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/add")
    public String getOwnerAdding(Model model){
        System.out.println("I got there");
        model.addAttribute("ownerRegistrationForm", new CatOwnerRegistrationForm());
        return "cat-owner-registration-form";
    }

    @PostMapping("/add")
    public String addOwner(
            @ModelAttribute("ownerRegistrationForm") CatOwnerRegistrationForm form
            ){
        service.registerNewOwner(
                form.getName(),
                form.getFamilyName(),
                form.getLivingSpaceSquareMeters(),
                form.getAmountOfKids(),
                form.getOwnerPersonsPhone(),
                form.getPhotoOfOwnersIdCard(),
                form.getPreferredCommunicationLanguage(),
                form.isSmokesAtHome());
        return "redirect:/owners";
    }
}
