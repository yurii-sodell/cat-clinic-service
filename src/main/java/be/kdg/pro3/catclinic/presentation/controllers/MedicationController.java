package be.kdg.pro3.catclinic.presentation.controllers;

import be.kdg.pro3.catclinic.domain.Medication;

import be.kdg.pro3.catclinic.domain.MedicationType;
import be.kdg.pro3.catclinic.service.MedicationService;
import be.kdg.pro3.catclinic.web.MedicationRegistrationForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/medications")
public class MedicationController{
    private static Logger log = LoggerFactory.getLogger(MedicationController.class);
    private MedicationService service;

    public MedicationController(MedicationService service){
        this.service = service;
    }

    @GetMapping
    public String showMedication(Model model){
        log.info("All medications request entered");
        List<Medication> list = service.getAllMedications();
        model.addAttribute("medications", list);
        log.info("All medications are successfully returned");
        return "medications";
    }

    @GetMapping("/new")
    public String showMedicationForm(Model model){
        log.info("Medication adding form request entered");
        model.addAttribute("medicationForm", new MedicationRegistrationForm());
        log.info("Medication adding form is successfully returned");
        return "medication-adding-form";
    }

    @ModelAttribute("medicationTypes")
    public MedicationType[] medicationTypes(){
        return MedicationType.values();
    }

    @PostMapping("/new")
    public String addMedication(
            @ModelAttribute("medicationForm") MedicationRegistrationForm medicationForm,
            BindingResult bindingResult
    ){
        log.info("New medication request entered");

        if(bindingResult.hasErrors()) {
            log.error("The data in medication form where corrupted");
            return "medication-adding-form";
        }

        service.registerNewMedication(
                medicationForm.getTitle(),
                medicationForm.getPrice(),
                medicationForm.getWeeksAfterBirthToStartMedication(),
                medicationForm.getMarketReleaseDate(),
                medicationForm.getMedicationType(),
                medicationForm.isPrescriptionNeeded(),
                medicationForm.isCanBeGiftedOnCatsBirthday(),
                medicationForm.getPhoto()
        );

        log.info("New medication is successfully registered");
        return "redirect:/medications";
    }
}