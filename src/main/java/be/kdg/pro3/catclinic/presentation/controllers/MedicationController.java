package be.kdg.pro3.catclinic.presentation.controllers;

import be.kdg.pro3.catclinic.domain.Medication;

import be.kdg.pro3.catclinic.service.MedicationService;
import be.kdg.pro3.catclinic.web.MedicationForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


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

    @PostMapping
    public void addMedication(
            @RequestParam MedicationForm form
    ){
        service.registerNewMedication(
                form.getTitle(),
                form.getPrice(),
                form.getWeeksAfterBirthToStartMedication(),
                form.getMarketReleaseDate(),
                form.getMedicationType(),
                form.isPrescriptionNeeded(),
                form.isCanBeGiftedOnCatsBirthday(),
                form.getPhoto()
        );
    }
}
