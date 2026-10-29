package be.kdg.pro3.catclinic.presentation.controllers;

import be.kdg.pro3.catclinic.service.DefaultPrescriptionsService;
import be.kdg.pro3.catclinic.service.PrescriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/prescriptions")
public class PrescriptionController{
    private PrescriptionService service;

    public PrescriptionController(PrescriptionService service){
        this.service = service;
    }

//    @GetMapping
//    public String showClinicPrescriptionHistory(){
//        return service.get
//    }
}
