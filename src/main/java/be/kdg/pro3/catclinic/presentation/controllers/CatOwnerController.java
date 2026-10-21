package be.kdg.pro3.catclinic.presentation.controllers;


import be.kdg.pro3.catclinic.domain.PreferredCommunicationLanguage;
import be.kdg.pro3.catclinic.exceptions.OwnerNotFoundException;
import be.kdg.pro3.catclinic.service.CatOwnerService;
import be.kdg.pro3.catclinic.web.CatOwnerRegistrationForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/owners")
public class CatOwnerController{
    private static Logger log = LoggerFactory.getLogger(CatOwnerController.class);
    private CatOwnerService service;

    public CatOwnerController(CatOwnerService service){
        this.service = service;
    }

    @GetMapping
    public String showOwners(Model model){
        log.info("Received request for all owners!");
        model.addAttribute("owners", service.getAllOwners());
        log.info("All owners are returned!");
        return "owners";
    }

    @GetMapping("/{ownerId}")
    public String getOwnerPage(
            @PathVariable UUID ownerId,
            Model model){
        log.info("Received request to show certain owner page, id:  {}", ownerId.toString());
        try{
            model.addAttribute("ownerOnPage", service.getCatOwnerById(ownerId));
        }catch (OwnerNotFoundException e) {
            log.error(e.getMessage());
            model.addAttribute("errorMessage", e.getMessage());
            return "/error";
        }
        log.info("Owner was found, returning its page");
        return "owner-page";
    }

    @GetMapping("/new")
    public String getOwnerAdding(Model model){
        log.info("Received request for owner registration form!");
        model.addAttribute("ownerRegistrationForm", new CatOwnerRegistrationForm());
        log.info("Owner registration form is returned!");
        return "cat-owner-registration-form";
    }

    @ModelAttribute("preferredCommunicationLanguages")
    public PreferredCommunicationLanguage[] preferredCommunicationLanguage(){
        return PreferredCommunicationLanguage.values();
    }

    @PostMapping("/new")
    public String addOwner(
            @ModelAttribute("ownerRegistrationForm") CatOwnerRegistrationForm form,
            BindingResult bindingResult
    ){
        log.info("Received request for new owner!");
        if(bindingResult.hasErrors()){
            log.error("The data in owner form where corrupted");
            return "cat-owner-registration-form";
        }

        service.registerNewOwner(
                form.getName(),
                form.getFamilyName(),
                form.getLivingSpaceSquareMeters(),
                form.getAmountOfKids(),
                form.getOwnerPersonsPhone(),
                form.getPhotoOfOwnersIdCard(),
                form.getPreferredCommunicationLanguage(),
                form.isSmokesAtHome());
        log.info("New owner is registered!");
        return "redirect:/owners";
    }


}