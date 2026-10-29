package be.kdg.pro3.catclinic.service;

import be.kdg.pro3.catclinic.repository.CatRepository;
import be.kdg.pro3.catclinic.repository.MedicationRepository;
import be.kdg.pro3.catclinic.presentation.web.PrescriptionPair;
import org.springframework.stereotype.Service;

@Service
public class DefaultPrescriptionsService implements PrescriptionService{
    private MedicationRepository medicationRepository;
    private CatRepository catRepository;


    @Override
    public PrescriptionPair getAllPrescription(){
        return null;
    }
}
