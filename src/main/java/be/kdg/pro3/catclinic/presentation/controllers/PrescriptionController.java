package be.kdg.pro3.catclinic.presentation.controllers;

import be.kdg.pro3.catclinic.domain.CatId;
import be.kdg.pro3.catclinic.domain.Medication;
import be.kdg.pro3.catclinic.domain.MedicationId;
import be.kdg.pro3.catclinic.presentation.web.PrescriptionCreationRequest;
import be.kdg.pro3.catclinic.service.DefaultPrescriptionsService;
import be.kdg.pro3.catclinic.service.PrescriptionService;
import be.kdg.pro3.catclinic.service.exceptions.CatNotFoundException;
import be.kdg.pro3.catclinic.service.exceptions.MedicationNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/prescriptions")
public class PrescriptionController{
    private PrescriptionService service;

    public PrescriptionController(PrescriptionService service){
        this.service = service;
    }

     //since many to many is not solved I cannot add like expiration date
    //to make some of prescriptions expirable
     @GetMapping("/{id}")
    public String showActivePrescriptions(
             @PathVariable("id") CatId catId,
            Model model){

        log.info("Cat page is requested for {}", catId);

         try{
             model.addAttribute("prescriptionRequest",new PrescriptionCreationRequest());
             model.addAttribute("currentCat", catId);
             model.addAttribute("prescriptionForCurrentCat", service.getAllPrescriptionsFor(catId));
             model.addAttribute("allowedToPrescribe", service.getMedicationThatCanBePrescribedFor(catId));
         }catch (CatNotFoundException | MedicationNotFoundException e){
             log.info(e.getMessage());
         }

         log.info("Prescription page is returned");
         return "prescriptions";
    }

    @PostMapping("/{id}")
    public String prescribe(
            @PathVariable("id") CatId catId,
            @ModelAttribute("prescriptionRequest") PrescriptionCreationRequest req
    ){
        log.info("Prescribing medication {} for cat {}", req.getMedicationId(), catId);

        try{
            service.assignMedicationFor(catId, req.getMedicationId());
        }catch (MedicationNotFoundException | CatNotFoundException e){
            log.info(e.getMessage());
        }
        log.info("Prescription page is returned");
        return "redirect:/prescriptions/"+catId;
    }

}
