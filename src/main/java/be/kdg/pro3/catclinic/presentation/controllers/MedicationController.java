package be.kdg.pro3.catclinic.presentation.controllers;

import be.kdg.pro3.catclinic.domain.Medication;
import be.kdg.pro3.catclinic.service.MedicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
