package be.kdg.pro3.catclinic.presentation.controllers;

import be.kdg.pro3.catclinic.domain.Medication;

import be.kdg.pro3.catclinic.service.MedicationService;
import be.kdg.pro3.catclinic.web.MedicationForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/medications")
public class MedicationController{
    private MedicationService service;

    public MedicationController(MedicationService service){
        this.service = service;
    }

    @GetMapping
    public String showMedication(Model model){
        List<Medication> list = service.getAllMedications();
        System.out.println(list.stream().findFirst().get());
        model.addAttribute("allMedications", list);
        return "medications";
    }

    @GetMapping("/add")
    public String addMedication(Model model){
        model.addAttribute("medicationForm", new MedicationForm());
        return "medication-adding-form";
    }

    @PostMapping("/add")
    public String addMedication(
            @ModelAttribute("medicationForm") MedicationForm medicationForm
    ){
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
        return "redirect:/medications";
    }
}
