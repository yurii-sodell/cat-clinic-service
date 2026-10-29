package be.kdg.pro3.catclinic.presentation.controllers;


import be.kdg.pro3.catclinic.domain.CatOwner;
import be.kdg.pro3.catclinic.domain.CatOwnerId;
import be.kdg.pro3.catclinic.domain.PreferredCommunicationLanguage;
import be.kdg.pro3.catclinic.exceptions.OwnerNotFoundException;
import be.kdg.pro3.catclinic.service.CatOwnerService;
import be.kdg.pro3.catclinic.presentation.web.CatOwnerRegistrationForm;
import be.kdg.pro3.catclinic.service.CatService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/owners")
@Slf4j
public class CatOwnerController{
    private CatOwnerService ownerService;
    private CatService catService;

    public CatOwnerController(CatOwnerService ownerService, CatService catService){
        this.ownerService = ownerService;
        this.catService = catService;
    }

    @GetMapping
    public String showOwners(Model model){
        log.info("Received request for all owners!");
        model.addAttribute("owners", ownerService.getAllOwners());
        log.info("All owners are returned!");
        return "owners";
    }

    @GetMapping("/{ownerId}")
    public String getOwnerPage(
            @PathVariable CatOwnerId ownerId,
            Model model){
        log.info("Received request to show certain owner page, id:  {}", ownerId.toString());
        try{
            CatOwner owner = ownerService.getCatOwnerById(ownerId);

            model.addAttribute("ownerOnPage", owner);
            model.addAttribute("catsOfOwnerOnPage", catService.getCatsForOwner(ownerId));
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
            @Valid @ModelAttribute("ownerRegistrationForm") CatOwnerRegistrationForm form,
            BindingResult bindingResult
    ){
        log.info("Received request for new owner with following values: " + form.toString());
        if(bindingResult.hasErrors()){
            log.error("The data in owner form where corrupted");
            return "cat-owner-registration-form";
        }

        ownerService.registerNewOwner(
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